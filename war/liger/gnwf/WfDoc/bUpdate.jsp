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
		//var text=$("<input type='text' id='it"+ri+"' name='usrNames' />");
		//var value=$("<input type='hidden' id='iv"+ri+"' name='usrIds' />");
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
		td2.html("上传的文件");
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

function uploadFile(projectNo,cateId,upFielType,textId) {
	
    var gngile_uploadURL = $('#gngile_upload_URL').val();
	var server_URL = $('#server_URL').val();
var url = gngile_uploadURL
      /**RUL后面跟的参数 **/
	         +'?syId='+$("#syId").val()    //系统ID
	         +'&syNm='+$("#syNm").val()  //系统名字
	         +'&usrId='+$("#usrId").val() //用户ID
	         +'&usrNm='+$("#usrNm").val() //用户名字
	         +'&diyFolder='+"QuesFile" //文件上传的目录中自定义部分
	         +'&uploadType='+"BaseLib" 
         //上传的是ProcFile，取值只能是ProcFile或者BaseLib
         //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
         +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
	         +'&callBackUrl='+server_URL+"/BaseLibWfDoc!afterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
         +'&tempParams='+"wfDoc.projectNo:"+projectNo+",wfDoc.cateId:"+cateId;  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
      /**RUL后面跟的参数 **/
url=encodeURI(url);  //encodeURI
var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
/*
//监听Dialog关闭事件
$(".l-dialog-winbtns").click(function(){
	//alert("./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val());
	//	window.location = "./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val();
	textId= textId+qr_id;
	var text = encodeURI(document.getElementById(textId).value);
	 //alert(document.getElementById(textId).value);
	 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val()+"&text="+text+"&textId="+textId;
	 	
	 
});
*/
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

	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
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