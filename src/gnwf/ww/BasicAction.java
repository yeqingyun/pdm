package gnwf.ww;

import gnwf.facade.QuesRespFacade;
import gnwf.vo.QuesResp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
import Utils.DateUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
	private ToKenProcessor toKenProcessor;
	protected String checkReSub;
	
//	public String hasPermission() {
//		//HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST) ;
//		//移动终端 访问拦截判断
//		logCode = ServletActionContext
//				.getRequest().getParameter("logCode");
//		if(StringUtils.isNotBlank(logCode)) {
//			SyLog syLog = new SyLog();
//			syLog.setLogCode(logCode);
//			syLog.setLogType(1);
//			try {
//				List<SyLog> sls = new SyLogFacade().find(syLog);
//				if(sls != null && sls.size() > 0) {
//					return PERMISSION;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//    	Usr session = (Usr)ActionContext.getContext().getSession().get("SYUSR");
//    	if(session == null) {
//    		String login = null;
//    		String pwd = null;
//    		Cookie c[] = ServletActionContext.getRequest().getCookies();
//    		if(c!=null){
//    			for(int x=0;x<c.length;x++){
//    				if("login".equals(c[x].getName())){
//    					login = c[x].getValue();
//    					continue;
//    				}
//    				if("pwd".equals(c[x].getName())){
//    					pwd = c[x].getValue();
//    					continue;
//    				}
//    			}
//    		}
//    		
//    		if(login!=null && pwd!=null){
//    			Usr u = new Usr();
//        		u.setLogin(login);
//        		u.setPwd(pwd);
//    			try {
//    				session = new UsrFacade().login(u);
//	    			
//	    			if(session == null) {
//	    				u.setPwd(new MD5().getMD5ofStr(pwd.trim()));
//	    				session = new UsrFacade().login(u);
//	    			}
//	    			
//	    			if(session == null) {
//	    				u.setPwd(new CryptPwd().getEncry(pwd));
//	    				session = new UsrFacade().login(u);
//	    			}
//    			} catch (Exception e) {
//    				setMsg(MSG.F_LOG);
//    			}
//    		}
//    		
//    		if(session!=null && session.getId() != null){
//    			try {
//    				syDef = new SyDef();
//    				syDef.setSyId(6);
//					int amount = new GpSyFacade().amount("select count(*) as amount " +
//							" from GpSy inner join GpUsr on(GpSy.gpId = GpUsr.gpId) " +
//							" where GpUsr.usrId = "+session.getId()+
//							" and GpSy.syId = " + syDef.getSyId());
//					if(amount >0) {
//						syDef = new SyDefFacade().findById(syDef);
//						session.setSyId(syDef.getSyId());
//						session.setSyNm(syDef.getSyName());
//						setSessionGps(session);
//						setUsrSession(session);
//						setMsg(MSG.S_LOG);
//					}
//					else {
//						setMsg("您无此系统访问授权。");
//						return ERROR;
//					}
//				} catch (Exception e) {
//					setMsg(MSG.F_LOG);
//					Logger.getLogger(this.getClass()).error("hasPermission Exception", e);
//					return ERROR;
//				}
//    		}else{
//    			setMsg("您还没有登录系统，请首先登录。");
//        		return NOLOGGEDIN;
//    		}
//    	}
//		return PERMISSION;
//    }
	
	public HttpServletRequest getRequest() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		return request;
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
	/**
	 * 文件上传
	 * @param fileInp 上传的文件
	 * @param savePath 保存路径
	 * @throws IOException 
	 */
	protected void uploadFile(File fileInp, String savePath) throws IOException {
		File saveFile = new File(savePath);
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		InputStream is = new FileInputStream(fileInp);
		OutputStream os = new FileOutputStream(new File(saveFile.getAbsolutePath() + "/" + fileInp.getName()));
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}

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
		ActionContext.getContext().getSession().put("SYUSR",usr);;
	}
	//登录认证码,用于移动终端使用
	protected String logCode;
	protected SyDef syDef;
//	public String hasPermission() {
//		return PERMISSION;
//	}
	private String json;
	private String json1;
	private String json2;
	private Object jsonObj;

	private String msg;
	private String uri;
	private Integer page;
	private Integer pageSize;
	private Integer pagesize;
	private  String redirect;
	
	public void sendMessage(String message,String uri) {
		setMsg(message);
		setRedirect(uri);
	}
	
	public String getTokenKey() throws Exception{
		toKenProcessor=ToKenProcessor.getInstance();
		return toKenProcessor.getToken();
	}
	
    public boolean checkReSub() throws Exception{
        toKenProcessor=ToKenProcessor.getInstance();
        System.out.println("getCurToken:"+toKenProcessor.getCurToken());
        System.out.println("getCheckReSub:"+getCheckReSub());
      
    	if(toKenProcessor.getCurToken().trim().equals(getCheckReSub().trim())){
    		toKenProcessor.resetToken();
    		toKenProcessor.saveToken();
    		return true;
    	}else{
    		toKenProcessor.resetToken();
    		toKenProcessor.saveToken();
    		return false;
    	}
    }
    /**
     * 获取URL(如http://192.168.119.217:8080/zrprjt)
     * @return
     */
    public String getAppcationURL() {
    	ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		String contextPath = request.getContextPath();
		String url = request.getRequestURL().toString();
		int index = url.indexOf(contextPath);
		String res_url = url.substring(0, index) + contextPath;
		return res_url;
    }
    
    /**
	 * 根据quesResp的status判断wfques的状态
	 * @param quesResp
	 * @return
	 * @throws Exception
	 */
	public int getWfQuesStatus(String quesId) throws Exception {
		int quesResp_status_return = 0;//退回
		int quesResp_status_invalid = 0;//无效
		int quesResp_status_0 = 0;//新建
		int quesResp_status_2 = 0;//有效
		int quesResp_status_3 = 0;//挂起
		String sql = "select " + QuesResp.SELF_FIELDS + " from QuesResp where RespType = 1 and quesId = '" + quesId+"'";
		QuesRespFacade facade = new QuesRespFacade();
		List<QuesResp> quesResps = facade.find(sql, QuesResp.SELF_FIELDS);
		for(QuesResp qr : quesResps) {
			if(qr.getStatus() == -1) {
				quesResp_status_invalid++;
			}else if(qr.getStatus() == 0) {
				quesResp_status_0++;
			}else if(qr.getStatus() == 2) {
				quesResp_status_2++;
			}else if(qr.getStatus() == 3) {
				quesResp_status_3++;
			}else if(qr.getStatus() == -2) {
				quesResp_status_return++;
			}
		}
		if(quesResp_status_3 > 0) {
			return MSG.WFQUES_STATUS_21;
		}else if(quesResp_status_invalid > 0) {
			return MSG.WFQUES_STATUS_9;
		}else if(quesResp_status_2 == quesResps.size()) {
			return MSG.WFQUES_STATUS_11;
		}else if(quesResp_status_0 == quesResps.size()) {
			return MSG.WFQUES_STATUS_1;
		}else if(quesResp_status_2 > 0 && quesResp_status_0 > 0 && (quesResp_status_2 + quesResp_status_0) == quesResps.size()) {
			return MSG.WFQUES_STATUS_1;
		}else if(quesResp_status_return > 0) {
			return MSG.WFQUES_STATUS_8;
		}
		return MSG.WFQUES_STATUS_10;
	}
	
	/**
	 * 通过反射获取指定字段的是值来组装JSON
	 * @param wfQuesList 问题集合
	 * @param total 查询的总记录数
	 * @param fields 字段名数组
	 * @return
	 * @throws Exception
	 */
	protected <T> String getAndroidJson(List<T> list,Integer total,String[] fields) throws Exception {
		JSONArray ja = new JSONArray();
		for(T t : list) {
			Map<String,Object> map = new HashMap<String,Object>();
			for(String field : fields) {
				Method[] childMethods = t.getClass().getDeclaredMethods();
				for(Method m : childMethods) {
					if(m.getName().toLowerCase().equals(("get"+field).toLowerCase())) {
						if(m.getReturnType().getName().contains("Date")) {
							if(m.invoke(t) != null){
								map.put(field, DateUtil.format((Date)m.invoke(t),"yyyy-MM-dd"));
							}else {
								map.put(field,"");
							}
						}else {
							if(m.invoke(t) != null){
								map.put(field, m.invoke(t));
							}else {
								map.put(field,"");
							}
						}
					}
				}
				Method[] fatherMethods = t.getClass().getSuperclass().getDeclaredMethods();
				for(Method m : fatherMethods) {
					if(m.getName().toLowerCase().equals(("get"+field).toLowerCase())) {
						if(m.getReturnType().getName().contains("Date")) {
							if(m.invoke(t) != null){
								map.put(field, DateUtil.format((Date)m.invoke(t),"yyyy-MM-dd"));
							}else {
								map.put(field,"");
							}
						}else {
							if(m.invoke(t) != null){
								map.put(field, m.invoke(t));
							}else {
								map.put(field,"");
							}
						}
					}
				}
			}
			ja.add(map);
		}
		if(total != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Rows", ja);
			map.put("Total", total);
			JSONObject jsonObject = new JSONObject(map);
			return jsonObject.toJSONString();
		}
		return ja.toJSONString();
	}
    
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
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	public ToKenProcessor getToKenProcessor() {
		return toKenProcessor;
	}
	public void setToKenProcessor(ToKenProcessor toKenProcessor) {
		this.toKenProcessor = toKenProcessor;
	}
	public String getCheckReSub() {
		return checkReSub;
	}
	public void setCheckReSub(String checkReSub) {
		this.checkReSub = checkReSub;
	}
	public String getLogCode() {
		return logCode;
	}
	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}
	public SyDef getSyDef() {
		return syDef;
	}
	public void setSyDef(SyDef syDef) {
		this.syDef = syDef;
	}
}
