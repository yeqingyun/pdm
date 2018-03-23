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

<script src="./include/js/gnwf/wfCate.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'WfCate'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '分类ID', name: 'cateId', align: 'left', width: 120 },
			{ display: '分类名称', name: 'cateName', align: 'left', width: 120 },
			{ display: '分类级别', name: 'cateLevel', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.status == 0) {
						innerHtml += "red";
						v = "无效";
					} else if(row.status == 1){
						innerHtml += "blue";
						v = "有效";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			}
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfCate!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
	if ($("#cateParent").length > 0)
		$("#cateParent").ligerComboBox();
	if ($("#cateLevel").length > 0)
		$("#cateLevel").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#cateName").length > 0)
		$("#cateName").ligerTextBox();

});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:30px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">上级分类：</td>
			<td>
				<select id="cateParent" name="wfCate.cateParent">
					<option value="" selected>-请选择-</option>
					<c:forEach items="${cateList}" var="cate">
						<option value="<c:out value="${cate.cateId}"/>" <c:if test="${cate.cateId==wfCate.cateParent}">selected</c:if>><c:out value="${cate.cateName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">分类名称：</td>
			<td>
				<input type="text" id="cateName" name="wfCate.cateName"/>
			</td>
			<td height="24" width="90" align="center">分类级别：</td>
			<td>
				<select id="cateLevel" name="wfCate.cateLevel">
					<option value="">全部</option>
					<option value="1" <c:if test="${wfCate.cateLevel==1}">selected</c:if>>1级</option>
					<option value="2" <c:if test="${wfCate.cateLevel==2}">selected</c:if>>2级</option>
					<option value="3" <c:if test="${wfCate.cateLevel==3}">selected</c:if>>3级</option>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="wfCate.status">
					<option value="1" <c:if test="${wfCate.status==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${wfCate.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
			
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>