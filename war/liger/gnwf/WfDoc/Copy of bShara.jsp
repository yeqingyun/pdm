<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfDoc.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/oa.js"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js" ></script>
<script type="text/javascript" src="./include/js/gnwf/NextStepPage.js"></script>
<script type="text/javascript" src="./include/cal/WdatePicker.js"></script>

<script type="text/javascript" src="./include/js/gnwf/MailTo.js"></script>

<script type="text/javascript">


var gridManager;
$(function () {
	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 178
	});

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
		td2.append(text);
		td2.append(value);
		td2.append(catet);
		td2.append(catev);
		tr.append(td1);
		tr.append(td2);
		this.append(tr);
		text.bind("click",function(){
			gridManager.loadData();
			$.ligerDialog.open( { target:$("#maingrid"),title:"选择用户",
				width:400,height:300,
				buttons: [ 
				           { text: '确定', onclick: function (item, dialog) {
				        	   var srow = gridManager.getSelectedRow();
				        	   if(srow==null){
				        		   alert("请选择一个用户");
				        		   return;
				        	   }
				        	   text.val(srow.usrName);
				        	   value.val(srow.usrId);
				        	   dialog.hide();
				        	} },
				           { text: '取消', onclick: function (item, dialog) { dialog.hide(); } } 
				         ]
			});
		});
		ri=ri+1;
	}
	table.init=function(){
		table.empty();
		ri=0;
		var tr = $("<tr>");
		var td1=$("<th width='200'>");
		var td2=$("<th width='200'>");
		td1.html("文档类别");
		td2.html("指定发布人");
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
	$("#maingrid").hide();
	$("#maingrid").ligerGrid({
		columns: [

			{ display: '角色名', name: 'roleNm', align: 'left', width: 150 },
			{ display: '用户名', name: 'usrName', align: 'left', width: 150 }
		],
		checkbox: true,
		rownumbers:true,
		pageSize:10,
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
	gridManager = $("#maingrid").ligerGetGridManager();
	$("#pageloading").hide();
	init();
});

function save(){
	 $.ajax({
	        url:"WfDoc!releaseBaseLib.shtml?selectUsrs="+$("#selectUsrs").val()+"&wfDoc.docId="+$("#selectUsrs").val(),
	        type: "POST",
	        success: function (text) {
	        	$.ligerDialog.success(text);
	        }
	 });
}



function save2(){
	var usrIds=$("input[name=usrIds]");
	var usrNames=$("input[name=usrNames]");
	var cateIds=$("input[name=cateIds]");
	var cateNames=$("input[name=cateNames]");
	var array=new Array();
	for(var i=0;i<cateIds.length;i=i+1){
		array[array.length]={
				'cateId':$(cateIds[i]).val(),
				'cateName':$(cateNames[i]).val(),
				'usrId':$(usrIds[i]).val(),
				'usrName':$(usrNames[i]).val()
		};
	}
	alert(JSON.stringify(array));
	//XXX
	
	
	
}

</script>
</head>

<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="center" id="framecenter">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="sform" style="margin:2px;height:30px;">
<input type="hidden" id="prjtNo" name=wfDoc.projectNo value="<c:out value="${wfDoc.projectNo}"/>" />
<input type="hidden" id="tjson" name="tjson" value="<c:out value="${tjson}"/>" />


<input type="hidden" id="selectUsrs" name="selectUsrs" value="<c:out value="${selectUsrs}"/>" />
<input type="hidden" id="wfDoc.docId" name="wfDoc.docId" value="<c:out value="${wfDoc.docId}"/>" />
<table align="center"><tr><td align="right">
	<table id="table1" align="center">
	</table>
	<input type="button" value="确定" onclick="save()" ></input>
	</td></tr></table>
</div>
<div id="maingrid"></div>
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>