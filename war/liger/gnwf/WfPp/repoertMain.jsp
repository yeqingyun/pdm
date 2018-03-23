<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String projects=(String)request.getAttribute("wfPpReports");
%>
<html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'repoertMain.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/include/js/codebase/dhtmlxtabbar.css">
	<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/include/js/codebase/dhtmlxtabbar_inner.css">
	<script src="<%=request.getContextPath()%>/include/js/jquery-1.8.1.min.js"></script>

	

	<script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxcommon.js"></script>
	<script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxlayout.js"></script>
	<script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxtabbar_start.js"></script>
    <script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxcontainer.js"></script>
	<script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxtoolbar.js"></script>
	<script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxcommon.js"></script>
	 <script src="<%=request.getContextPath()%>/include/js/codebase/dhtmlxtabbar.js"></script>
<script >

var roId = "${roId}";
var dhxLayout;
var projects=eval(<%=projects%>);

$(function () {
   //dhtmlx.skin = "dhx_skyblue";
   var tabbar;
   init();
 });
  
 var tab;
 function init(){
	 
    tab = new dhtmlXTabBar(document.body, "top",30  );
  	tab.setSkin("default");
    tab.setImagePath("<%=request.getContextPath()%>/include/js/codebase/imgs/");
    tab.setHrefMode("iframes-on-demand");
    //存在研发项目>0
      tab.addTab(1,'<font size="3" >研   一</font>',"90px");
      tab.setContentHref(1, '<%=request.getContextPath()%>/wfppReport!findReportByDevid.shtml?devDeptNameID=1&time='+new Date().getTime);
      tab.addTab(2,'<font size="3" >研  二</font>',"90px");
      //tab.setContentHref(2, '<%=request.getContextPath()%>/wfppReport!findReportByDevid.shtml?devDeptNameID=2&time='+new Date().getTime);
      tab.addTab(3,'<font size="3" >研  三</font>',"90px");
      //tab.setContentHref(3, '<%=request.getContextPath()%>/wfppReport!findReportByDevid.shtml?devDeptNameID=3&time='+new Date().getTime);
      tab.addTab(4,'<font size="3" >海  外</font>',"90px");
      //tab.setContentHref(4, '<%=request.getContextPath()%>/wfppReport!findReportByDevid.shtml?devDeptNameID=4&time='+new Date().getTime);
      tab.addTab(5,'<font size="3" >收  藏</font>',"90px");
     // tab.setContentHref(5, '<%=request.getContextPath()%>/wfppReport!findReportByShou.shtml?time ='+new Date().getTime);
      tab.setTabActive(1);
      tab.attachEvent("onSelect", sheetChange);
  }
 function sheetChange(id){
	 
	 if(id!=5){
		// alert(id);
		tab.cells(id).attachURL('<%=request.getContextPath()%>/wfppReport!findReportByDevid.shtml?devDeptNameID='+id+'&time='+new Date().getTime);
	 }
	 if(id==5){
		 tab.cells(id).attachURL('<%=request.getContextPath()%>/wfppReport!findReportByShou.shtml?time='+new Date().getTime);
		
	 }
// 	 $(".dhx_tab_element_active span").css("color","white");
	 return true;
 }
</script>
  </head>
  <body>
  </body>
</html>
