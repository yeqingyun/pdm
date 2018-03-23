package zrprjt.ww;

import gnwf.ww.MSG;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.frm.comn.CryptPwd;
import org.frm.comn.MD5;
import org.frm.ww.BasicActionSupport;
import org.safehaus.uuid.UUID;

import zrsy.facade.GpFacade;
import zrsy.facade.GpSyFacade;
import zrsy.facade.SyDefFacade;
import zrsy.facade.SyLogFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Gp;
import zrsy.vo.SyDef;
import zrsy.vo.SyLog;
import zrsy.vo.Usr;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class BasicAction extends BasicActionSupport {
	
	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "message";
	public static final String PGADD = "add";
	public static final String PGEDI = "edit";
	public static final String PGVIE = "view";
	public static final String PGLIS = "list";
	public static final String EXCEL = "excel";
	public static final String PRINT = "print";
	public static final String PGUPL = "upload";
	public static final String PGJSON = "json";
	protected SyDef syDef;

	public Usr getUsrSession() {
		//如果是adnr端,则根据logCode返回 usr
		try {
			SyLog syLog = getSyLog();
			if(syLog!=null){
				Usr qUsr =new Usr();
				qUsr.setId(syLog.getUserId());
				Usr usr = new zrsy.facade.UsrFacade().findById(qUsr);
				if(usr!=null){
					//查找出自己相关的项目
					String findGpsSQL ="select "+Gp.SELF_FIELDS+" from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where GpUsr.UsrId = "+usr.getId();
					List<Gp> gps = new  ArrayList<Gp>();
					gps= new GpFacade().find(findGpsSQL, Gp.SELF_FIELDS);
					usr.setGps(gps);
				}
				return usr;
			}
		} catch (Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error(this.getClass().getSimpleName()+" Exception", e);
		}
		return (Usr)ActionContext.getContext().getSession().get("SYUSR");
	}
    // get SyLog
    public SyLog getSyLog() throws Exception{
    	String logCode="";
    	if(logCode==null||"".equals(logCode.trim()))
    		logCode = ServletActionContext.getRequest().getParameter("logCode");
		if (logCode != null) {
			try {
				UUID.valueOf(logCode.trim());
			} catch (Exception e) {
				setMsg(MSG.F_SEA);
				Logger.getLogger(this.getClass()).error(this.getClass().getSimpleName()+" Exception", e);
				return null;
			}
			SyLog syLog = new SyLog();
			syLog.setLogCode(logCode.trim());
			SyLog dbSyLog = new SyLogFacade().findBy(syLog);
			return dbSyLog;
		}
		return null;
    }
	@SuppressWarnings("unchecked")
	public void setUsrSession(Usr usr) {
		ActionContext.getContext().getSession().put("SYUSR",usr);
	}
	
	
	//登录认证码,用于移动终端使用
		protected String logCode;
	public String hasPermission() {
		
		
		//HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST) ;
		//移动终端 访问拦截判断
		logCode = ServletActionContext
				.getRequest().getParameter("logCode");
		if(StringUtils.isNotBlank(logCode)) {
			SyLog syLog = new SyLog();
			syLog.setLogCode(logCode);
			syLog.setLogType(1);
			try {
				List<SyLog> sls = new SyLogFacade().find(syLog);
				if(sls != null && sls.size() > 0) {
					return PERMISSION;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
    	Usr session = (Usr)ActionContext.getContext().getSession().get("SYUSR");
    	if(session == null) {
    		String login = null;
    		String pwd = null;
    		Cookie c[] = ServletActionContext.getRequest().getCookies();
    		if(c!=null){
    			for(int x=0;x<c.length;x++){
    				if("login".equals(c[x].getName())){
    					login = c[x].getValue();
    					continue;
    				}
    				if("pwd".equals(c[x].getName())){
    					pwd = c[x].getValue();
    					continue;
    				}
    			}
    		}
    		
    		if(login!=null && pwd!=null){
    			Usr u = new Usr();
        		u.setLogin(login);
        		u.setPwd(pwd);
    			try {
    				session = new UsrFacade().login(u);
	    			
	    			if(session == null) {
	    				u.setPwd(new MD5().getMD5ofStr(pwd.trim()));
	    				session = new UsrFacade().login(u);
	    			}
	    			
	    			if(session == null) {
	    				u.setPwd(new CryptPwd().getEncry(pwd));
	    				session = new UsrFacade().login(u);
	    			}
    			} catch (Exception e) {
    				setMsg(MSG.F_LOG);
    			}
    		}
    		
    		if(session!=null && session.getId() != null){
    			try {
    				syDef = new SyDef();
    				syDef.setSyId(6);
					int amount = new GpSyFacade().amount("select count(*) as amount " +
							" from GpSy inner join GpUsr on(GpSy.gpId = GpUsr.gpId) " +
							" where GpUsr.usrId = "+session.getId()+
							" and GpSy.syId = " + syDef.getSyId());
					if(amount >0) {
						syDef = new SyDefFacade().findById(syDef);
						session.setSyId(syDef.getSyId());
						session.setSyNm(syDef.getSyName());
						setSessionGps(session);
						setUsrSession(session);
						setMsg(MSG.S_LOG);
					}
					else {
						setMsg("您无此系统访问授权。");
						return ERROR;
					}
				} catch (Exception e) {
					setMsg(MSG.F_LOG);
					Logger.getLogger(this.getClass()).error("hasPermission Exception", e);
					return ERROR;
				}
    		}else{
    			setMsg("您还没有登录系统，请首先登录。");
        		return NOLOGGEDIN;
    		}
    	}
		return PERMISSION;
    }
	
	
	
	protected void setSessionGps(Usr session) throws Exception {
		String sql ="select "+Gp.SELF_FIELDS+" from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where GpUsr.UsrId = "+session.getId();
		List<Gp> gps = new  ArrayList<Gp>();
		gps= new GpFacade().find(sql, Gp.SELF_FIELDS);
		session.setGps(gps);
		//在session中保存自己的所有权限角色名字
		String gpNames = " "; 
		for( Gp e: gps){
			gpNames=gpNames+","+e.getGpName();
		}
		session.setGpNames(gpNames);
	}
	
	
	
	
	
	private String json;
	private String json1;
	private String json2;
	private Object jsonObj;

	private String msg;
	private String uri;
	private Integer page;
	private Integer pageSize;
	private Integer pagesize;

	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Object getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(Object jsonObj) {
		this.jsonObj = jsonObj;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getJson1() {
		return json1;
	}
	public void setJson1(String json1) {
		this.json1 = json1;
	}
	public String getJson2() {
		return json2;
	}
	public void setJson2(String json2) {
		this.json2 = json2;
	}
}
