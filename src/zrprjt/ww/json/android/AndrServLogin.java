package zrprjt.ww.json.android;

import gnwf.facade.WfQuesFacade;
import gnwf.facade.WfRdTaskFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.frm.comn.CryptPwd;

import zrprjt.facade.PrjtDefFacade;
import zrprjt.vo.PrjtDef;
import zrsy.facade.GpFacade;
import zrsy.facade.GpSyFacade;
import zrsy.facade.SyDefFacade;
import zrsy.facade.SyLogFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Gp;
import zrsy.vo.SyDef;
import zrsy.vo.SyLog;
import zrsy.vo.Usr;
import zrsy.ww.BasicAction;
import zrsy.ww.MSG;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.webwork.ServletActionContext;

public class AndrServLogin extends BasicAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usr usr;
	private zrsy.vo.SyDef syDef;
	private Map<String, Object> resMap;

	/**
	 * <b>返回json规范</b> <br/>
	 * result 登录成功状态 <br/>
	 * 1：登录成功! <br/>
	 * 2：用户名或密码错误，请重试。 <br/>
	 * 3：系统繁忙，请稍后重试。 <br/>
	 * 4：校验失败。 <br/>
	 * 5：您无此系统访问授权。 <br/>
	 * user中 <br/>
	 * <b>OrgTyp</b> <br/>
	 * 1: 代理商 <br/>
	 * 2：门店 <br/>
	 * 0：品专部 <br/>
	 * <b>AgentId</b> <br/>
	 * 用户所属代理商id <br/>
	 * <b>ShopId</b> <br/>
	 * 用户所属门店 id
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		List<PrjtDef> prjtDefs = null ;
		int mytskCount = 0;
		int myQuesCount = 0;
		resMap = new HashMap<String, Object>();
		int result = 3;
		Usr res_user = null;
		String pwd = usr.getPwd();
		
		if (StringUtils.isBlank(usr.getLogin()) || StringUtils.isBlank(pwd)
				|| syDef.getSyId() == null) {
			result = 4;
		} else {
//			// 明文判断
//			res_user = new UsrFacade().login(usr);
//			// 密文解密判断
//			if (res_user == null) {
//				CryptPwd cryptPwd = new CryptPwd();
//				usr.setPwd(cryptPwd.getEncry(pwd));
//				res_user = new UsrFacade().login(usr);
//			}
			//不加密查询
			res_user = new UsrFacade().login(usr);
			//旧版的加密查询
			if(res_user == null) {
				usr.setPwd(new org.frm.comn.MD5().getMD5ofStr(pwd.trim()));
				res_user = new UsrFacade().login(usr);
			}
			//可解码的加密
			if(res_user == null) {
				CryptPwd cryptPwd = new CryptPwd();
				usr.setPwd(cryptPwd.getEncry(pwd));
				res_user = new UsrFacade().login(usr);
			}
			if (res_user == null) {
				// 用户名或密码错误
				result = 2;
			} else {
				// 登录成功
				result = 1;
				if (res_user != null && res_user.getId() != null) {
					int amount = new GpSyFacade()
							.amount("select count(*) as amount "
									+ " from GpSy inner join GpUsr on(GpSy.gpId = GpUsr.gpId) "
									+ " where GpUsr.usrId = "
									+ res_user.getId() + " and GpSy.syId = "
									+ syDef.getSyId());
					//查找出自己相关的项目
					String findGpsSQL ="select "+Gp.SELF_FIELDS+" from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where GpUsr.UsrId = "+res_user.getId();
					List<Gp> gps = new  ArrayList<Gp>();
					gps= new GpFacade().find(findGpsSQL, Gp.SELF_FIELDS);
					res_user.setGps(gps);
					//判断是不是超级用户
					Boolean selAllPrjts = false;
					for( Gp e: gps){
						if(e.getGpName().indexOf("超级用户")>-1){
							selAllPrjts = true;
							break;
						}else if(e.getGpName().indexOf("不随项目变更角色")>-1){
							selAllPrjts = true;
							break;
						}
					}
					String findPrjtDefsSql = null;
					String prjtDef_FIELDS = "PrjtDef.PrjtNo,PrjtDef.PrjtNm,PrjtDef.Status,PrjtDef.CreateBy,PrjtDef.CreateDate";
					if(selAllPrjts){
						findPrjtDefsSql = "select "+prjtDef_FIELDS+" from PrjtDef where (TypId = ( select TypId from PrjtTyp where TypNm  = '研发管理') )  order by Status asc , CreateDate desc" ;
					}else {
						findPrjtDefsSql = "select "+prjtDef_FIELDS+" from PrjtDef where (PrjtNo in ( select PrjtNo from PrjtUsr where UsrId  = "+ res_user.getId()+") ) or CreateBy = "+res_user.getId() +" order by Status asc , CreateDate desc" ;
					}
				   prjtDefs = new PrjtDefFacade().find(findPrjtDefsSql,prjtDef_FIELDS);
				   
				   //查找出未办理的任务个数
				   String findMytskCount = "select count(*) as amount from WfRdTask where WfRdTask.acceptBy="+res_user.getId()+" and WfRdTask.status<=1 ";
				   mytskCount = new WfRdTaskFacade().amount(findMytskCount);
					
				   //查找出未解决的问题个数
				   String findMyQuesCount = "select count(*) as amount from WfQues left join Usr on WfQues.UserId = Usr.Id inner join  QuesResp on WfQues.QuesId = QuesResp.QuesId" 
				                          +" where QuesResp.UsrId = "+res_user.getId()+" and (QuesResp.Status =0 or QuesResp.Status =-1) and QuesResp.RespType=1";
				   myQuesCount = new WfQuesFacade().amount(findMyQuesCount);
					/**
					 * 当前系统，及登录日志
					 */
					if (amount > 0) {
						syDef = new SyDefFacade().findById(syDef);
						res_user.setSyId(syDef.getSyId());
						res_user.setSyNm(syDef.getSyName());
						String gpNames = " ";
						for( Gp e: gps){
							gpNames=gpNames+","+e.getGpName();
						}
						res_user.setGpNames(gpNames);
						addCookies(usr);
						setUsrSession(res_user);
						setMsg(zrsy.ww.MSG.S_LOG);

						SyLog syLog = new SyLog();
						setLogCode(UUID.randomUUID().toString());
						syLog.setLogCode(getLogCode());
						syLog.setUserId(res_user.getId());
						syLog.setLogNm("系统登录");
						syLog.setLogText(res_user.getUsrName());
						syLog.setLogDate(new Date());
						syLog.setIpAddr(getIpAddr(ServletActionContext
								.getRequest()));
						syLog.setLogType(1);
						new SyLogFacade().save(syLog);
//						syDef = new SyDefFacade().findById(syDef);
//						res_user.setSyId(syDef.getSyId());
//						res_user.setSyNm(syDef.getSyName());
						//在session中保存自己的所有权限角色名字
					} else {
						// 无系统授权
						result = 5;
					}
				}
			}
		}
		resMap.put("result", result);
		resMap.put("prjtDefs", prjtDefs);
		resMap.put("mytskCount", mytskCount);
		resMap.put("myQuesCount", myQuesCount);
		resMap.put("user", res_user);
		resMap.put("log_code", getLogCode());
		JSONObject jsonObject = new JSONObject(resMap);
		setJson(jsonObject.toJSONString());
		return SUCCESS;
	}
	
	protected void addCookies(Usr user) {
		Cookie login = new Cookie("login",user.getLogin());
		login.setMaxAge(1000*60*60*20);
		Cookie pwd = new Cookie("pwd",user.getPwd());
		pwd.setMaxAge(1000*60*60*20);
		ServletActionContext.getResponse().addCookie(login);
		ServletActionContext.getResponse().addCookie(pwd);
	}
	/**
	 * 
	 * @return result 1:退出成功 2:无效操作3退出失败
	 * @throws Exception
	 */
	public String logout() throws Exception {
		resMap = new HashMap<String, Object>();
		int result = 0;
		try {
			if(StringUtils.isNotBlank(getLogCode())) {
				new SyLogFacade().update(
						"insert into SyLog select UserId,'0001','系统登出',LogText,GETDATE(),'"
								+getIpAddr(ServletActionContext.getRequest())+ "',1,null from SyLog where LogCode = '"+getLogCode()+"';" +
								"update SyLog set LogCode = '0000' where LogCode = '"+getLogCode()+"';" 
						);
				result = 1;
			} else {
				result = 2;
			}
		}
		
		catch(Exception e) {
			Logger.getLogger(this.getClass()).error("AndrServLogin Exception", e);
			result = 3;
		}
		resMap.put("result", result);
		JSONObject jsonObject = new JSONObject(resMap);
		setJson(jsonObject.toJSONString());
		return SUCCESS;
	}

	public Usr getUsr() {
		return usr;
	}

	public void setUsr(Usr usr) {
		this.usr = usr;
	}

	public zrsy.vo.SyDef getSyDef() {
		return syDef;
	}

	public void setSyDef(zrsy.vo.SyDef syDef) {
		this.syDef = syDef;
	}
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
