package zrsy.ww;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.frm.comn.CryptPwd;

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

public class LoginAction extends BasicAction {
	private static final long serialVersionUID = 1L;
	
	private Usr usr;
	private java.util.List<zrsy.vo.SyDef> syDefs;

	public String execute() throws Exception {
		try {
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef where SyId > 1",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_SEA);
			Logger.getLogger(this.getClass()).error("IndexAction Exception", e);
			return ERROR;
		}
		return INITIALIZES;
	}

	public String login() throws Exception {
		try {
//			if(syDef.getSyId() == null) {
//				setMsg("请选择访问的系统！");
//				return ERROR;
//			}
			
			setSyDef(new SyDef());
			getSyDef().setSyId(6);
			
			if(usr.getLogin().trim().equals("")) {
				setMsg("登录账号不能为空！");
				return ERROR;
			}
			if(usr.getPwd().trim().equals("")) {
				setMsg("登录密码不能为空！");
				return ERROR;
			}
			//不加密查询
			Usr session = new UsrFacade().login(usr);
			String pwd = usr.getPwd();
			
			//旧版的加密查询
			if(session == null) {
				usr.setPwd(new org.frm.comn.MD5().getMD5ofStr(pwd.trim()));
				session = new UsrFacade().login(usr);
			}
			
			//可解码的加密
			if(session == null) {
				CryptPwd cryptPwd = new CryptPwd();
				usr.setPwd(cryptPwd.getEncry(pwd));
				session = new UsrFacade().login(usr);
			}

			if(session != null && session.getId() != null) {
				int amount = new GpSyFacade().amount("select count(*) as amount " +
						" from GpSy inner join GpUsr on(GpSy.gpId = GpUsr.gpId) " +
						" where GpUsr.usrId = "+session.getId()+
						" and GpSy.syId = " + getSyDef().getSyId());
				
				if(amount >0) {
					setSyDef(new SyDefFacade().findById(getSyDef()));
					session.setSyId(getSyDef().getSyId());
					session.setSyNm(getSyDef().getSyName());
					setSessionGps(session);
					SyLog syLog = new SyLog();
					syLog.setLogCode("0000");
					syLog.setUserId(session.getId());
					syLog.setLogNm("系统登录");
					syLog.setLogText(session.getUsrName());
					syLog.setLogDate(new Date());
					syLog.setIpAddr(this.getRemoteIpaddress());
					new SyLogFacade().save(syLog);
					
					addCookies(usr);
					setUsrSession(session);
					
					setMsg(MSG.S_LOG);
				}
				else {
					setMsg("您无此系统访问授权。");
					return ERROR;
				}
			}
			else {
				setMsg(MSG.F_LOG);
				return ERROR;
			}
		}
		catch(Exception e) {
			setMsg(MSG.F_LOG);
			Logger.getLogger(this.getClass()).error("LoginAction Exception", e);
			return ERROR;
		}
		
		
		
		return SUCCESS;
	}

//	private void setSessionGps(Usr session) throws Exception {
//		String sql ="select "+Gp.SELF_FIELDS+" from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where GpUsr.UsrId = "+session.getId();
//		List<Gp> gps = new  ArrayList<Gp>();
//		gps= new GpFacade().find(sql, Gp.SELF_FIELDS);
//		session.setGps(gps);
//		//在session中保存自己的所有权限角色名字
//		String gpNames = " "; 
//		for( Gp e: gps){
//			gpNames=gpNames+","+e.getGpName();
//		}
//		session.setGpNames(gpNames);
//	}
//	
	
	

	private String j_username;
	private String j_password;
	private String url;
	private Integer sysId =6;
	private String loginResult;
	public void unionLogin() throws Exception {
		try {
			
			if(getSyDef()==null){
			setSyDef(new SyDef());
			}
			getSyDef().setSyId(6);
			
			
			if(usr==null){
				usr = new Usr();
			}
			usr.setLogin(j_username);
			usr.setPwd(j_password);
			
			
			if(usr.getLogin().trim().equals("")) {
				loginResult = ERROR;
			}
			if(usr.getPwd().trim().equals("")) {
				loginResult = ERROR;
			}
			//不加密查询
			Usr session = new UsrFacade().login(usr);
			String pwd = usr.getPwd();
			
			//旧版的加密查询
			if(session == null) {
				usr.setPwd(new org.frm.comn.MD5().getMD5ofStr(pwd.trim()));
				session = new UsrFacade().login(usr);
			}
			//可解码的加密
			if(session == null) {
				CryptPwd cryptPwd = new CryptPwd();
				usr.setPwd(cryptPwd.getEncry(pwd));
				
				session = new UsrFacade().login(usr);
			}

			if(session != null && session.getId() != null) {
				int amount = new GpSyFacade().amount("select count(*) as amount " +
						" from GpSy inner join GpUsr on(GpSy.gpId = GpUsr.gpId) " +
						" where GpUsr.usrId = "+session.getId()+
						" and GpSy.syId = " + getSyDef().getSyId());
				
				if(amount >0) {
					setSyDef(new SyDefFacade().findById(getSyDef()));
					session.setSyId(getSyDef().getSyId());
					session.setSyNm(getSyDef().getSyName());
					
					setSessionGps(session);
					
					SyLog syLog = new SyLog();
					syLog.setLogCode("0000");
					syLog.setUserId(session.getId());
					syLog.setLogNm("系统登录");
					syLog.setLogText(session.getUsrName());
					syLog.setLogDate(new Date());
					syLog.setIpAddr(this.getRemoteIpaddress());
					new SyLogFacade().save(syLog);
					
					addCookies(usr);
					setUsrSession(session);
					loginResult = SUCCESS;
				}
				else {
					setMsg("您无此系统访问授权。");
					loginResult = ERROR;
				}
			}
			else {
				loginResult = ERROR;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass()).error("LoginAction Exception", e);
			loginResult = ERROR;
		}
		//loginResult = SUCCESS;
		
		
		HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		
		if (loginResult.equals(SUCCESS)) {
			response.setHeader("P3P","CP=CAO PSA OUR");
			response.sendRedirect("./loged.jsp");
		}
		else {
			response.setHeader("P3P","CP=CAO PSA OUR");
			response.sendRedirect(url);
		}

	}
	
	
	
	
	
	
	
	
	//保存登录Cookie
	protected void addCookies(Usr user) {
		Cookie login = new Cookie("login",user.getLogin());
		login.setMaxAge(1000*60*60*20);
		Cookie pwd = new Cookie("pwd",user.getPwd());
		pwd.setMaxAge(1000*60*60*20);
		ServletActionContext.getResponse().addCookie(login);
		ServletActionContext.getResponse().addCookie(pwd);
	}

	public void logout() throws Exception {
		try {
			if(this.getUsrSession() != null) {
				SyLog syLog = new SyLog();
				
				syLog.setLogCode("0001");
				syLog.setUserId(getUsrSession().getId());
				syLog.setLogNm("系统登出");
				syLog.setLogText(getUsrSession().getUsrName());
				syLog.setLogDate(new Date());
				syLog.setIpAddr(this.getRemoteIpaddress());
				
				new SyLogFacade().save(syLog);
				//System.out.println(getUsrSession().getUsrName()+"22222@");
				setUsrSession(null);
				
				ActionContext ctx = ActionContext.getContext();
				//ServletContext servletContext=(ServletContext)ctx.getContext();
			   // String value=servletContext.getInitParameter("server_URL");
				String server_URL = ServletActionContext.getServletContext().getInitParameter("server_URL");
                System.out.println("!!!!!!!!!"+server_URL);
				HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
				
				 /*Enumeration em = request.getSession().getAttributeNames();
				  while(em.hasMoreElements()){
				   request.getSession().removeAttribute(em.nextElement().toString());
				  }*/
				request.getSession().invalidate();
			}
			
			syDefs = new zrsy.facade.SyDefFacade().find("select "+zrsy.vo.SyDef.SELF_FIELDS+" from SyDef Where SyId > 1",zrsy.vo.SyDef.SELF_FIELDS);
		}
		catch(Exception e) {
			setMsg(MSG.F_LOG);
			Logger.getLogger(this.getClass()).error("LoginAction Exception", e);
			//return ERROR;
		}
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		request.getSession().invalidate();
		//Map params = ctx.getParameters();
		String server_URL = ServletActionContext.getServletContext().getInitParameter("server_URL");
		
		String s[] = server_URL.split("//");
		//response.sendRedirect("http://192.168.0.96/cas/logout?redirect="+s[1]);//本地测试
		//response.sendRedirect("https://test.auth.gionee.com/logout?redirect="+s[1]);//205测试
		response.sendRedirect("https://auth.gionee.com/logout?redirect="+s[1]);//生产环境
		//return INITIALIZES;
	}
	
	
	
	private String filePath;
	private String fileNm;
	public void downLoad() throws Exception{
		
//		File file = new File(filePath) ;
//		String realPath = file.getAbsolutePath() ;
//		String srcPath = realPath.substring(0, realPath.lastIndexOf("\\")+1) ;
		
		
		ActionContext ctx = ActionContext.getContext();
		
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		String serPath = request.getSession().getServletContext().getRealPath(request.getRequestURI());
          System.out.println(serPath);
		//serPath= serPath.substring(0, serPath.lastIndexOf("\\war")+4); 		  //tomcat 
		
		serPath= serPath.substring(0, serPath.lastIndexOf("\\zrprjt"));       //glassfish
		String path = serPath+filePath;
		path = path.replace("\\", "/");
		System.out.println(path);
		File f = new File(path);
		if(!f.exists()){
			response.sendError(404,"File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		
		response.reset(); //非常重要
		//纯下载方式
		response.addHeader("Content-Length", "" + f.length());
		response.setContentType("application/octet-stream");
		response.setContentType("application/x-msdownload"); 
		response.setContentType("text/html; charset=iso-8859-1"); 
		response.setCharacterEncoding("iso-8859-1");
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileNm.getBytes("GBK"),"iso-8859-1"));
		OutputStream out = response.getOutputStream();
		while((len = br.read(buf)) >0) {
			out.write(buf,0,len);
		}
		br.close();
		out.close();
	}
	
	
	
	
	public String hasPermission() {
		return PERMISSION;
    }
	public Usr getUsr() {
		return usr;
	}
	public void setUsr(Usr usr) {
		this.usr = usr;
	}	
	public java.util.List<zrsy.vo.SyDef> getSyDefs() {
		return syDefs;
	}
	public void setSyDefs(java.util.List<zrsy.vo.SyDef> syDefs) {
		this.syDefs = syDefs;
	}
	public zrsy.vo.SyDef getSyDef() {
		return syDef;
	}
	public void setSyDef(zrsy.vo.SyDef syDef) {
		this.syDef = syDef;
	}
	protected SyDef syDef;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
}
