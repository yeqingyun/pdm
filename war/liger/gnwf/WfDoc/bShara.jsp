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
	$("#selectUsrsName").css("font-weight","bold");
	$("#pageloading").hide();
});



var checkedCustomer = [];
function findCheckedCustomer(data) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == data) return i;
	}
	return -1;
}
function addCheckedCustomer(data) {
	if(findCheckedCustomer(data) == -1)
		checkedCustomer.push(data);
}
function removeCheckedCustomer(data){
	var i = findCheckedCustomer(data);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked){
			addCheckedCustomer(data);
	}
	else
		removeCheckedCustomer(data);
}


function save(){
	if($("#selectUsrs").val().length<1){
		alert("请选择接收人");
		return false;
	}
	 $.ajax({
	        url:"WfDoc!releaseBaseLib.shtml?selectUsrs="+$("#selectUsrs").val()+"&wfDoc.docId="+$("#wfDoc_docId").val(),
	        type: "POST",
	        success: function (text) {
	        	$.ligerDialog.success(text);//弹出信息
	        	window.parent.getWin("bdoc")[0].flush();//刷新数据
	        	window.parent.closeWin("bshara");//关闭本页面
	        }
	 });
}
function selcResponsible(){
	var div = $("<div>");
	div.ligerGrid({
		columns: [

			{ display: '角色名', name: 'roleNm', align: 'left', width: 150 },
			{ display: '用户名', name: 'usrName', align: 'left', width: 150 }
		],
		
		checkbox: true,
		rownumbers:true,
		url:'./PrjtUsr!prjtUsrlist.shtml?prjtNo='+$("#prjtNo").val(),
		usePager:true,
		width: '99.5%',
		height:'99%',
		onCheckRow: f_onCheckRow,
	    frozen:false,
		pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
        onError: function (a, b)
        { 
        }
	});
	$.ligerDialog.open( { target:div,title:"选择用户",
		width:400,height:300,
		buttons: [ 
		           { text: '确定', onclick: function (item, dialog) {
		        	   var srow = div.ligerGetGridManager().getSelectedRow();
		        	   if(srow==null){
		        		   alert("请选择一个用户");
		        		   return;
		        	   }
		        	   
		        	    var usrString="";
		        		var usrIds="";
		        		for(var i =0;i<checkedCustomer.length;i++) {
		        				if(checkedCustomer[i].usrId){
		        					usrIds+=checkedCustomer[i].usrId+",";
		        				}
		        				if(checkedCustomer[i].usrName){
		        					usrString=usrString+checkedCustomer[i].usrName+",";
		        				}
		        		}
		        		usrIds= usrIds.substring(0,usrIds.length-1);
		        	   $("#selectUsrsName").html(usrString);
		        	   $("#selectUsrs").val(usrIds);
		        	   dialog.close();
		        	} },
		           { text: '取消', onclick: function (item, dialog) { dialog.close(); } } 
		         ]
	});
	
}

</script>
</head>

<body style="padding:0px;">

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="prjtNo" name=wfDoc.projectNo value="<c:out value="${wfDoc.projectNo}"/>" />
<input type="hidden" id="wfDoc_docId" name="wfDoc_docId" value="<c:out value="${wfDoc.docId}"/>" />
<input type="hidden" id="selectUsrs" name="selectUsrs"  value="<c:out value="${selectUsrs}"/>"/>

  <div class="listTable clearfix" style="margin:10px 20px;" >
		   <table cellpadding=0 cellspacing=0 width=300px align=center >
				<tr>
					<th>文件类别：</th>
					<td  ><c:out value="${wfDoc.cateName}"/></td>
				</tr>
				
				<tr>
					<th>文件名称：</th>
					<td  ><c:out value="${wfDoc.docName}"/></td>
				</tr>
				
				<tr>
					<th>版本号：</th>
					<td  ><c:out value="${wfDoc.docVer}"/></td>
				</tr>
				
				<tr>
					<th>接收人：</th>
					<td  >
					<a href="javascript:selcResponsible();">选择接收人</a>
					</td>
				</tr>
				<tr><td colspan="2" id="selectUsrsName">
				</td></tr>
				<tr><td  align="right" colspan="2"><input  type="button" value="发布 " class="wfbigbtn"  onclick="save();" /></td></tr>
				
		</table>
</div>
</form>
</div>
</body>
</html>