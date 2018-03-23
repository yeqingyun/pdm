<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/js/gnwf/defect.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<script type="text/javascript">
$("#layout1").ligerLayout({
	topHeight:25,
	minLeftWidth:80,
	minRightWidth:80,
	leftWidth: 178
});
$(function(){
	$("#submit_date").ligerDateEditor({labelWidth: 200,format: "yyyy-MM-dd"});
})
</script>
</head>
<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
<div position="center" id="framecenter">
<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
	<div id="toolbar"></div>
	<div id="sform" style="margin:10px;height:50px;">
		<table>
			<tr>
				<td height="24" width="90" align="left">id：</td>
				<td height="24" width="90" align="left">
					<input id="id" name="defect.id" style="width:100px"/>
				</td>
				<td height="24" width="90" align="left">owner：</td>
				<td height="24" width="90" align="left">
					<input id="login_name" name="defect.login_name" style="width:100px"/>
				</td>
				<td height="24" width="90" align="left">project：</td>
				<td height="24" width="90" align="left">
					<input id="prjName" name="defect.prjName" style="width:100px"/>
				</td>
				
				<td height="24" width="100" align="left">submit_date：</td>
				<td><input type="text" id="submit_date" name="defect.submit_date"/></td>
				<td height="24" width="90" align="left">state：</td>
				<td>
					<select id="state" name="defect.state" style="width:100px">
						<option value="">全部</option>
						<c:forEach items="${stateList}" var="state">
							<option value="<c:out value="${state.id}"/>"><c:out value="${state.name}"/></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td height="24" width="90" align="left" colspan="6">
				 	<input id="queryBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
				 </td>
		  </tr>
		</table>
	</div>
	<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
</form>
</div>
</div>
</body>
</html>