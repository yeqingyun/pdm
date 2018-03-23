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

<script src="./include/js/zrprjt/schDtl.js?V=1.0" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'SchDtl'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#schDtlId").length > 0)
		$("#schDtlId").ligerTextBox();
	if ($("#schId").length > 0)
		$("#schId").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#docNm").length > 0)
		$("#docNm").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="schDtl.schDtlId" name="schDtl.schDtlId" size="30" value="<c:out value="${schDtl.schDtlId}"/>"/>
	
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">进度配置：</td>
			<td>
				<select id="schId" name="schDtl.schId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${schCfgs}" var="schCfg">
						<option value="<c:out value="${schCfg.schId}"/>" <c:if test="${schCfg.schId==schDtl.schId}">selected</c:if>><c:out value="${schCfg.schNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">交附件：</td><td><input type="text" id="docNm" name="schDtl.docNm" size="30" value="<c:out value="${schDtl.docNm}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td>
			  <td>
			   <select id="status" name="schDtl.status" style="width: 196px">
					<option value = "1" <c:if test="${schDtl.status==1}">selected</c:if>>有效</option>
					<option value = "0" <c:if test="${schDtl.status==0}">selected</c:if>>无效</option>
				</select>
			  </td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="schDtl.remark" size="30" value="<c:out value="${schDtl.remark}"/>"/></td>
		</tr>
		<!--tr>
			<input type="text" id="status" name="schDtl.status" size="30" value="<c:out value="${schDtl.status}"/>"/>
			<td height="24" width="90" align="center">ID：</td><td><input type="text" id="schDtlId" name="schDtl.schDtlId" size="30" value="<c:out value="${schDtl.schDtlId}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="schDtl.createBy" size="30" value="<c:out value="${schDtl.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="schDtl.lastUpd" size="30" value="<c:out value="${schDtl.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="schDtl.createDate" size="30" value="<c:out value="${schDtl.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="schDtl.lastDate" size="30" value="<c:out value="${schDtl.lastDate}"/>"/></td>
		</tr><tr>
		</tr><tr>
		</tr-->

	</table>
</form>

</div>

</body>
</html>