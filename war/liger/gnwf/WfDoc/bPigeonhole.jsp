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


$(function () {
	var table = $("#table1");
	var rows = JSON.parse(decodeURIComponent($("#tjson").val()));
	var ri=0;
	table.add=function(row){
		var tr = $("<tr>");
		var td1=$("<td>");
		var td2=$("<td>");
		var text=$("<input type='text' id='it"+ri+"' name='usrNames' />");
		var value=$("<input type='hidden' id='iv"+ri+"' name='usrIds' />");
		var catet=$("<input type='hidden' id='ict"+ri+"' name='cateNames' />");
		var catev=$("<input type='hidden' id='icv"+ri+"' name='cateIds' />");
		td1.html(row.cateName);
		catet.val(row.cateName);
		catev.val(row.cateId);
		if(row.userRole>0){
			text.val(row.usrName);
			text.attr("disabled",true);
			value.val(row.usrId);
		}
		td2.append(text);
		td2.append(value);
		td2.append(catet);
		td2.append(catev);
		tr.append(td1);
		tr.append(td2);
		this.append(tr);
		text.bind("click",function(){selcResponsible(text,value);});
		ri=ri+1;
	}
	table.init=function(){
		table.empty();
		ri=0;
		var tr = $("<tr>");
		var td1=$("<th width='200'>");
		var td2=$("<th width='200'>");
		td1.html("文档类别");
		td2.html("归档提交人");
		tr.append(td1);
		tr.append(td2);
		table.append(tr);
	}
	function init(){
		table.init();
		for(var i=0;i<rows.length;i=i+1){
			table.add(rows[i]);
		}
	}
	$("#pageloading").hide();
	init();
});

function save(){
	var usrIds=$("input[name=usrIds]");
	var usrNames=$("input[name=usrNames]");
	var cateIds=$("input[name=cateIds]");
	var cateNames=$("input[name=cateNames]");

	var cns = "";
	for(var i=0;i<usrIds.length;i=i+1){
		if($(usrIds[i]).val().length<1){
			if(cns.length>0){
				cns+=",";
			}
			cns+='"'+$(cateNames[i]).val()+'"';
		}
	}
	if(cns.length>0){
		alert(cns+" 还没有择用户");
		return false;
	}
	
	var array=new Array();
	for(var i=0;i<cateIds.length;i=i+1){
		array[array.length]={
				'cateId':$(cateIds[i]).val(),
				'cateName':$(cateNames[i]).val(),
				'usrId':$(usrIds[i]).val(),
				'usrName':$(usrNames[i]).val()
		};
	}
	
	 //alert(JSON.stringify(array));
	// alert($("#prjtNo").val());
	//启动归档流程
	 var userstr = JSON.stringify(array);
	 var params = {
			 "userMap": userstr,"prjtNo":$("#prjtNo").val()
     };
	 $.ajax({
	        url:"WfDoc!archiveBaseLib.shtml",
	        type: "POST",
	        data: params,
	        success: function (text) {
	        	userstr = null;
	        	$.ligerDialog.success(text);
	        	window.parent.flush();//涮新父窗口数据
	        	window.parent.closeWin("pigeon");//关闭页面
	        }
	 });
}

function selcResponsible(text,value){
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

		        	   text.val(srow.usrName);
		        	   value.val(srow.usrId);
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
<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<input type="hidden" id="prjtNo" name=wfDoc.projectNo value="<c:out value="${wfDoc.projectNo}"/>" />
<input type="hidden" id="tjson" name="tjson" value="<c:out value="${tjson}"/>" />
<div class="l-loading" style="display: block" id="pageloading"></div>
<div width="100%" align="center"><center><table width="100%"><tr><td align="center">
<table align="center"><tr><td align="right">
	<table id="table1" align="center" style=" border-collapse:separate;border-spacing:10px;">
	</table>
	<input type="button" value="&nbsp;确定&nbsp;" onclick="save()" ></input>
	</td></tr></table>
</td></tr></table></center></div>
</div>
</form>
</div>
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>