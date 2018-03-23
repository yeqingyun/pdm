<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

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
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>

<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfQues'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#idtfDate").length > 0) {
		$("#idtfDate").ligerDateEditor({showTime: false}).setValue('<fmt:formatDate pattern="yyyy-MM-dd" value="${wfQues.idtfDate}" type="both"/>');
	}
	check();
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:400});
	if ($("#idtfRes").length > 0)
		$("#idtfRes").ligerTextBox({width:135});
	if ($("#cateId").length > 0)
		$("#cateId").ligerComboBox({width:135});
});

//KindEditor网页编辑器
KindEditor.ready(function(K) {
	var editor = K.create('textarea[name="quesText"]',{
		resizeMode : 1,
		shadowMode : false,
		allowPreviewEmoticons : false,
		urlType : 'absolute',
		uploadJson : './WfQues!uploadImg.shtml',
		afterCreate : function() {
			this.sync();
		},
        afterBlur:function(){
            this.sync();
        },
        items : [ 'bold', 'italic', 'underline', 'strikethrough',
                'removeformat', '|', 'insertorderedlist',
                'insertunorderedlist', '|', 'forecolor',
                'fontname', 'fontsize', '|', 'link', 'unlink',
                'emoticons', 'code', 'image', 'flash', 'quote',
                'attach', '|', 'fullscreen','selectall', 'source', 'about' ]
	});
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<input type="hidden" id="quesId" name="wfQues.quesId" size="30" value="<c:out value="${wfQues.quesId}"/>"/>
	<input type="hidden" id="scheId" name="wfQues.scheId" size="30" value="<c:out value="${wfQues.scheId}"/>"/>
	<input type="hidden" id="taskId" name="wfQues.taskId" size="30" value="<c:out value="${wfQues.taskId}"/>"/>
	<input type="hidden" id="wfNo" name="wfQues.wfNo" size="30" value="<c:out value="${wfQues.wfNo}"/>"/>
	<input type="hidden" id="taskCount"/>
	<table width="750" cellpadding="10" cellspacing="10">
		<tr>
			<td height="24" width="100" align="center">标题：</td><td colspan="3"><input type="text" id="title" name="wfQues.title" size="30" value="<c:out value="${wfQues.title}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="100" align="center">分类：</td>
			<td>
				<select id="cateId" name="wfQues.cateId">
					<option value="">请选择</option>
					<option value="1"<c:if test="${1==wfQues.cateId}">selected</c:if>>A</option>
					<option value="2"<c:if test="${2==wfQues.cateId}">selected</c:if>>B</option>
					<option value="3"<c:if test="${3==wfQues.cateId}">selected</c:if>>C</option>
					<option value="4"<c:if test="${4==wfQues.cateId}">selected</c:if>>D</option>
				</select>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td height="24" width="100" align="center">责任人：</td>
			<td>
				<input type="hidden" id="userId" name="wfQues.userId" size="30" value="<c:out value="${wfQues.userId}"/>"/>
				<input id="txt2" name="wfQues.usrName" value="<c:out value="${wfQues.usrName}"/>" onblur="blurDataClear(this,'userId')" size="20" onkeydown="enterTips('sel2')"  onkeyup="showtips('txt2','sel2');if(event.keyCode==27)c('sel2');"/>
				<BR>
				<select id="sel2" style="width:220px; position:absolute; z-index:1000;DISPLAY: none" onkeydown=if(event.keyCode==13)rv('sel2','txt2') onclick=rv('sel2','txt2') onchange="getSelRes('sel2','userId')"></select>
			</td>
			<td height="24" width="90" align="center">验证人：</td>
			<td>
				<input type="hidden" id="idtfBy" name="wfQues.idtfBy" size="30" value="<c:out value="${wfQues.idtfBy}"/>"/>
				<input id="txt" name="wfQues.idtfUsr" value="<c:out value="${wfQues.idtfUsr}"/>" onblur="blurDataClear(this,'idtfBy')" size="19" onkeydown="enterTips('sel')"  onkeyup="showtips('txt','sel');if(event.keyCode==27)c('sel');"/>
				<BR>
				<select id="sel" style="width:220px; position:absolute; z-index:1000;DISPLAY: none" onkeydown=if(event.keyCode==13)rv('sel','txt') onclick=rv('sel','txt') onchange="getSelRes('sel','idtfBy')"></select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">验证效果：</td>
			<td>
				<input type="text" id="idtfRes" name="wfQues.idtfRes" ltype="text" value="<c:out value="${wfQues.idtfRes}"/>"/>
			</td>
			<td height="24" width="90" align="center">验证日期：</td>
			<td><input type="text" id="idtfDate" name="wfQues.idtfDate" size="30" value=""/></td>
		</tr>
		<tr>
			<td height="24" width="100" align="center">问题描述：</td>
			<td colspan="3">
				<textarea id="quesText" name="quesText" style="width:600px;height: 260px" validate="{required:true}"><c:out value="${wfQues.quesText}"/></textarea>
			</td>
		</tr>
		<tr>
			<td height="24" width="100" align="center">解决方法：</td>
			<td colspan="3">
				<textarea id="result" name="wfQues.result" style="width:600px;height: 50px"><c:out value="${wfQues.result}"/></textarea>
			</td>
		</tr>
	<%--
		<tr>
			<td height="24" width="100" align="center">回复人：</td>
			<td colspan="5">
				<input type="hidden" id="ryUsrs" name="wfQues.ryUsrs" size="30" value="<c:out value="${wfQues.ryUsrs}"/>"/>
				<input type="text" id="ryUsrNms" name="wfQues.ryUsrNms" size="30" readonly="readonly" value="<c:out value="${wfQues.ryUsrNms}"/>"
					onclick="openPop('WFSelectUser.shtml?taskId=<c:out value="${wfQues.taskId}"/>');"/>
			</td>
		</tr>
		<td height="24" width="100" align="center">问题等级：</td><td><input type="text" id="quesLevel" name="wfQues.quesLevel" size="30" validate="{required:true}" value="<c:out value="${wfQues.quesLevel}"/>"/></td>
		<td height="24" width="100" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfQues.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfQues.wfNo}"/>"/></td>
		<td height="24" width="100" align="center">是否风险流程：</td><td><input type="text" id="isRisk" name="wfQues.isRisk" size="30" validate="{required:true}" value="<c:out value="${wfQues.isRisk}"/>"/></td>
		<td height="24" width="100" align="center">分类ID：</td><td><input type="text" id="cateId" name="wfQues.cateId" size="30" validate="{required:true}" value="<c:out value="${wfQues.cateId}"/>"/></td>
		
	 --%>
	</table>
</form>
</div>
</body>
</html>