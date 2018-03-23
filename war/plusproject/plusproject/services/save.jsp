<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,PluSoft.Utils.*"%>
<% 	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");	
	
	String projectJSON = request.getParameter("project");
	 
	Map dataProject = (Map)PluSoft.Utils.JSON.Decode(projectJSON);
	
	String projectUID = PluSoft.DBProject.SaveProject(dataProject);
	
	response.getWriter().write(projectUID);		
	
%>