<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>

<script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>
<script type="text/javascript" src="./include/editor/xheditor-1.1.14-zh-cn.min.js"></script>
<script type="text/javascript">
$(function () {
	/**
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtDef'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	**/
	
	function syncdata() {
		$("#docDesc").val();
	} 
});

function sendMail(){
	
	var str = '{';
	str += '"usrIds":"'+$("#usrIds").val()+'",';
	str += '"mailTitle":"'+$("#mailTitle").val()+'",';
	str += '"mailContent":"'+$("#mailContent").val()+'"';
	str += '}';
	$.post("PrjtDef!sendMail.shtml",
			{"usrIds":$("#usrIds").val(),"mailTitle":$("#mailTitle").val(),"mailContent":$("#mailContent").val()},
			function(data) {
				$.ligerDialog.success("发送成功！");
			},
			"text"
		);
}


</script>

 

</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form id ="prjtForm" name ="prjtForm" action="PrjtDef!sav.shtml"  method="post" enctype="multipart/form-data">

    <input type="button" onclick="sendMail()" value="发送" />
    
    <input type="hidden"  id ="usrIds" name ="usrIds"   value="<c:out value="${usrIds}"/>" />

	<table width="90%">
	   
	   <tr >
	     <td  width="10%">收件人</td>
			<td bgcolor="#ffffff" width="90%" height="24">
				<input type="text" id="usrString" name="usrString"  value="<c:out value="${usrString}"/>" style="width:750px;height:18px" readonly="true"/>
			</td>
	   </tr>
	   <tr >
	     <td width="10%">主题</td>
			<td bgcolor="#ffffff" width="90%" height="24">
				<textarea id="mailTitle" name="mailTitle" style="width:750px;height:18px"  rows="1">
			    </textarea>
			</td>
	   </tr>
		<tr>
		   <td width="10%">正文</td>
           <td width="80%">
			<textarea id="mailContent" name="mailContent" class="xheditor {skin:'o2007blue',width:'600',height:'350'}">
		         <c:out value="${doc.docDesc}"/>
			</textarea>
		  </td>
	   </tr>

	</table>


</form>

</div>

</body>
</html>