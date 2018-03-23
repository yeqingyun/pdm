<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
.innerTable th,.innerTable td {
	border-right: 1px solid #9FC2E5;
	border-bottom: 1px solid #9FC2E5;
	background: #fff;
	text-align: left;
	padding: 0 7px;
	
}
.innerTable th{
font-size: 14px;
font-weight: bold;
}
</style>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="./include/js/oa.js"></script>
<script type="text/javascript">


var gridManager;
$(function () {
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '用户名', name: 'usrName', align: 'left', width: 150 },
			{ display: '创建日期', name: 'createDate', align: 'center', width: 100 },
			{ display: '操作',  align: 'center', width: 100,
				render: function (row){
					if((typeof row.status)=='undefined')
						return "";
					var v="";
					if(row.status==1){//已发布
						v+="<a href='#' onclick='shara(\""+row.wfDocId+"\",\""+row.usrId+"\",2)'>收回发布</a>";
					}if(row.status==2){//收了发布
						v+="<a href='#' onclick='shara(\""+row.wfDocId+"\",\""+row.usrId+"\",1)'>发布</a>";
					}
					return v;
				}
			}
		],
		rownumbers:true,
		url:'./WfDoc!baseSharaUserList.shtml?prjtNo='+$("#prjtNo").val()+"&wfDoc.docId="+$("#docId").val(),
		usePager:true,
		width: '400px',
		height:'99%',
	    frozen:false,
		pageSizeOptions: [3,10, 20, 30, 40, 50, 100],
		pageSize:20
	});
	gridManager = $("#maingrid").ligerGetGridManager();
	$("#pageloading").hide();
});

function shara(docId,usrId,status){
	$.post("./WfDoc!sharaStatus.shtml?shareRela.wfDocId="+docId+"&shareRela.usrId="+usrId+"&shareRela.status="+status,
			{},
			function(data) {
				alert(data);
				gridManager.loadData();
			},
			"text"
	);
}

</script>
</head>

<body style="padding:0px;">

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="prjtNo" name=wfDoc.projectNo value="<c:out value="${wfDoc.projectNo}"/>" />
<input type="hidden" id="wfDoc_docId" name="wfDoc_docId" value="<c:out value="${wfDoc.docId}"/>" />
<input type="hidden" id="docId" name="wfDoc.docId" value="<c:out value="${wfDoc.docId}"/>" />
<input type="hidden" id="selectUsrs" name="selectUsrs"  value="<c:out value="${selectUsrs}"/>"/>
<div class="l-loading" style="display: block" id="pageloading"></div>
<div  width="100%" align="center"><center><table width="100%"><tr><td align="center">
<table width="400px">
<colgroup>
<col width="60px" />
<col width="100px" />
<col width="50px" />
<col />
<col width="50px" />
<col width="30px" />
</colgroup>
<tr>
<th>文档类别:</th><td><c:out value="${wfDoc.cateName}"/></td>
<th>文件名:</th><td><c:out value="${wfDoc.docName}"/></td>
<th>版本号: </th><td><c:out value="${wfDoc.docVer}"/></td>
</tr></table>
<div id="maingrid"></div>
</td></tr></table></center></div>
</form>
</div>
</body>
</html>