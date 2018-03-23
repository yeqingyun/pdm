package com.gionee.casclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.validation.Assertion;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

import zrsy.facade.GpFacade;
import zrsy.facade.GpSyFacade;
import zrsy.facade.SyDefFacade;
import zrsy.facade.SyLogFacade;
import zrsy.facade.UsrFacade;
import zrsy.vo.Gp;
import zrsy.vo.SyLog;
import zrsy.vo.Usr;
import zrsy.ww.MSG;

public class UserInitializeFilter implements Filter {

	public static final String excludedPages = "/afterUploadAction!";

	/**
	 * Default constructor.
	 */
	public UserInitializeFilter() {
	}

	/**
	 * `
	 * 
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中， 如果不存在使用用户查询接口查询出用户对象并设置在Session中
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean goIndex = false;
		System.out.println("yyyyyyy:"
				+ ((HttpServletRequest) request).getServletPath());
		if (((HttpServletRequest) request).getServletPath().endsWith(".dhtml")) {
			if (!((HttpServletRequest) request).getServletPath().contains(
					excludedPages)) { // 判断是否在过滤url之外
				
				goIndex= true;
				
			}
		}

		
		if(goIndex){
			httpResponse.sendRedirect("./index.shtml");
		}else{
			// _const_cas_assertion_是CAS中存放登录用户名的session标志
			Object object = httpRequest.getSession().getAttribute(
					"_const_cas_assertion_");
			// System.out.println("==================>" + object.getClass() + ":" +
			// object.toString());

			// ---------------------设置session并跳转到http://localhost:8080/zrprjt/index.shtml
			// 如果当前系统中，用户在 SESSIOIN 中不存在
			if (httpRequest.getSession().getAttribute("SYUSR") == null) {
				Assertion assertion = (Assertion) object;
				// System.out.println("=========== Username : " +
				// assertion.getPrincipal().getName());
				String acounnt = assertion.getPrincipal().getName();
				Usr usr = new Usr();
				usr.setLogin(acounnt);
				try {
					usr = new UsrFacade().findBy(usr);

					if (usr != null) {

						int amount = new GpSyFacade()
								.amount("select count(*) as amount "
										+ " from GpSy inner join GpUsr on(GpSy.gpId = GpUsr.gpId) "
										+ " where GpUsr.usrId = " + usr.getId()
										+ " and GpSy.syId = 6");

						if (amount > 0) {
							setSessionGps(usr);
							SyLog syLog = new SyLog();
							syLog.setLogCode("0000");
							syLog.setUserId(usr.getId());
							syLog.setLogNm("系统登录");
							syLog.setLogText(usr.getUsrName());
							syLog.setLogDate(new Date());
							// syLog.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
							new SyLogFacade().save(syLog);
							// System.out.println(ActionContext.getContext().getSession());
							// ActionContext.getContext().getSession().put("SYUSR",usr);
							usr.setSyId(6);
							usr.setSyNm("金立研发管理系统");
							httpRequest.getSession().putValue("SYUSR", usr);
						} else {
							// TODO
							System.out.println("你没有登录系统的权限");
							// response.sendRedirect("./login.jsp");
						}
					} else {
						// TODO
						System.out.println("用户不存在");
						// response.sendRedirect("./noPermission.jsp");
					}
					httpResponse
					.sendRedirect("http://pdm.gionee.com/zrprjt/index.shtml");//生产环境
							//.sendRedirect("http://localhost:8080/zrprjt/index.shtml");//本地测试
							//.sendRedirect("http://192.168.119.205:28080/zrprjt/index.shtml");//205测试环境
							

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			chain.doFilter(request, response);
		}
		

		// ---------------------设置session并跳转到http://localhost:8080/zrprjt/index.shtml

	}

	protected void setSessionGps(Usr session) throws Exception {
		String sql = "select "
				+ Gp.SELF_FIELDS
				+ " from Gp inner join GpUsr on (Gp.GpId = GpUsr.GpId) where GpUsr.UsrId = "
				+ session.getId();
		List<Gp> gps = new ArrayList<Gp>();
		gps = new GpFacade().find(sql, Gp.SELF_FIELDS);
		session.setGps(gps);
		// 在session中保存自己的所有权限角色名字
		String gpNames = " ";
		for (Gp e : gps) {
			gpNames = gpNames + "," + e.getGpName();
		}
		session.setGpNames(gpNames);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
