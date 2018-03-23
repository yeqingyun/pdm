<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,PluSoft.Utils.*"%>
<% 		
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
    String projectuid = request.getParameter("projectuid");
	
    Map dataProject = PluSoft.DBProject.LoadProject(projectuid);
	
    Date sss = new Date();
    
    String json = PluSoft.Data.Project.Encode(dataProject);
    
    Long t = new Date().getTime() - sss.getTime();
    
    System.out.print(t);
    
    response.getWriter().write(json);
%>