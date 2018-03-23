<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfDoc.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/oa.js"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js"></script>
<script type="text/javascript" src="./include/js/gnwf/NextStepPage.js"></script>
<script type="text/javascript" src="./include/cal/WdatePicker.js"></script>

<style type="text/css" rel="stylesheet">
.titBox1 {
	background: solid 1px;
}

.titBox1 h3 {
	font-size: 16px;
	float: left;
	color: #515c68;
	font-weight: 700;
}

.titBox1 .butonList1 {
	margin-top: 4px;
	float: right;
}

.titBox1 .butonList1 li {
	float: left;
}

.titBox1 .butonList1 li a {
	line-height: 19px;
	padding: 0 10px;
	text-decoration: none;
	margin: 0 5px;
	font-size: 12px;
	color: #fff;
	display: block;
	height: height:19px;
	border: #23537d solid 1px;
	border-radius: 3px;
	background: #3670F2;
}

.titBox1 .butonList1 li a:hover {
	background: #669bca;
}
</style>
<script type="text/javascript">
	/*
	 var tree;
	 $(function() {
	 $("#tree1").ligerTree({
	 url : './PrjtDef!loadPrjtTree.shtml?loadPrjtTreeType=doc',
	 idFieldName : 'prjtNo',
	 textFieldName : 'prjtNm',
	 iconFieldName : 'iconUri',
	 checkbox : false,
	 onSelect : function(node, e) {
	 var prjtNo = node.data.prjtNo;
	 $("#projectNo").val(prjtNo);
	 flush();
	 }
	 });
	 tree = $("#tree1").ligerGetTreeManager();
	 tree.selectNode(document.getElementById("projectNo").value);
	 });
	 */

	 var gridManager;
	 $(function () {
			$("#maingrid").ligerGrid({
				columns: [
					{ display: '文档类别', name: 'docName', align: 'left', width: 160},
					{ display: '可下载的角色', name: 'docId', align: 'left', width: 600,
						render: function (row){
							if((typeof row.prjtRoles)=='undefined')
								return "";
							var names="";
							for(var i=0;i<row.prjtRoles.length;i++){
								if(i>0){
									names+=",";
								}
								names+=row.prjtRoles[i].roleNm;
							}
							return names;
						}	
					},
					{ display: '操作', name: 'docId', align: 'left', width: 80,
						render: function (row){
							var innerHtml = "<a href='javascript:openSelectWin("+row.docId+")'>选择角色</a>"
							return innerHtml;
						}	
					}
				],
				rownumbers:true,
				url:'./DocRole!wfScheCfgDocList.shtml',
				usePager:false,
				width: '99.5%',
				height:'99%'
			});
			gridManager= $("#maingrid").ligerGetGridManager();
			$("#pageloading").hide();
	 });
	 function openSelectWin(docId){
		 var div = $("<div>");
		 div.ligerGrid({
				columns: [
							{ display: '角色', name: 'roleNm', align: 'left', width: 160}
						],
				        checkbox :true,
					    frozen:false,
						rownumbers:true,
						url:'./PrjtRole!listAll.shtml',
						usePager:false,
						width: '99.5%',
						height:'550'
					});

			var dialog = $.ligerDialog.open({
				target:div,
				title:'选择角色',
				width:300,height:600,
				isResize: true,
				name :'openSelectWin',
				buttons: [ { text: '完成', onclick: function (item, dialog) {
					var m = div.ligerGetGridManager();
					var rows = m.getSelectedRows();
					edit(docId,rows);
					dialog.close();
					}},
					{ text: '取消', onclick: function (item, dialog) { dialog.close(); }}]
			});
	 }
	 function edit(docId,rows){
		 var s="";
		 for(var i=0;i<rows.length;i++){
			 if(s.length>0){
				 s+=",";
			 }
			 s+=rows[i].roleId;
		 }
		$.post("./DocRole!edit.shtml",
				{"docId":docId,'roleIds':s},
				function(data) {
					$.ligerDialog.warn(data);
					gridManager.loadData();
				},
				"text"
		);
	 }
</script>
</head>

<body style="padding: 0px;">

	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" />
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" />
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" />
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
	<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />

	<div id="layout1" style="overflow-x: hidden; overflow-y: hidden;">
		<!-- <div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
		</div> -->
		<div position="center" id="framecenter">

			<div class="l-loading" style="display: block" id="pageloading"></div>

			<div id="sform" style="margin: 2px; height: 30px;">

				<%-- <table>
		<tr>
		 <td height="24" width="50" >工作流：</td>
			  <td>
			    <input type="text" id="flowId"  size="30"   />
			  </td>
			  
			  <td height="24" width="60"  >文档类别：</td>
			  <td>
			    <input type="text" id="docCateId"  size="30"  />
			  </td>
			<td height="24" width="60"  align="center">文档名称：</td>
			<td><input type="text" id="docName" name="wfDoc.docName"/></td>
			<td>
	            <div class="titBox1">
	                <h3>&nbsp;&nbsp;&nbsp;&nbsp;</h3>
	                <ul class="butonList1">
	                   <li><c:if test="${pigeonBl}"><a name="showWfFormsBtn" id="showWfFormsBtn" href="javascript:piclick()">归档</a></c:if></li>
	                   <li><c:if test="${shareBt}"><a name="showWfFormsBtn" id="showWfFormsBtn" href="javascript:showFroms()">发布</a></c:if></li>
	                </ul>
	            </div>
			</td>
		</tr>
	</table> --%>
			</div>
			<div id="maingrid" style="margin-top: 1px; margin-left: 1px;"></div>

		</div>
	</div>
	<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>