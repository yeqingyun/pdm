<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/editor/xheditor-1.1.14-zh-cn.min.js"></script>
<script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>

<script type="text/javascript">

var usrIds;
var usrString;
var wfNoVal = window.parent.document.getElementById("wfRd.wfNo").value;
$(function(){
	//alert(wfNoVal);
	initPro(wfNoVal);
	
})

function initPro(wfNo){
 	//alert($('#wfRdwfNo').val()); 		
     $.ajax({
			type: "POST",
			url:  "./WfRd!OveSeaUsrList1.shtml",
			data: {"wfRdwfNo":wfNoVal},
			dataType:'json',
			success	: function(data){

			     var options = '';
			     for(var i=0;i<data.allUsr.length;i++){
			    	 options +=  ("<option  value="+data.allUsr[i].wfusrid+">"+data.allUsr[i].usrname+"</option>");
			     }
			     $("#mailUser1").html(options);
			     saveMailUser("mailUser1");
			    //alert(data.allUsr.length); 
			}
	});
   } 


function saveMailUser(id) {
	//alert("11111");
	var usrIds;
	var usrString;
	//alert(document.getElementById(id).options.length);
	for(i=0; i<document.getElementById(id).options.length; i++) {
		if(i == 0){
			usrString = document.getElementById(id).options[i].text;
		    usrIds = document.getElementById(id).options[i].value;
		}
		else{
			usrString += "," + document.getElementById(id).options[i].text;
		    usrIds += "," + document.getElementById(id).options[i].value;
		}
	}
	//alert(usrString);
	//alert(usrIds);
	$("#usrIds", window.document).val(usrIds);
	$("#usrString", window.document).val(usrString);
	
	
}


	$(function() {
		function syncdata() {
			$("#docDesc").val();
		}
	});

	var autoid=1;
	var editgridManager;
	var statusData = [{ Status: -1, text: '未配置人员'},{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
	var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];
	var upTypData = [{ UpTyp: 1, text: '加入项目组' }, { UpTyp: 0, text: '离开项目组'}];


	var selectWin =null;
	//open select window
	
	
	var pop;
	function selectPrjtUsers(){
		//alert("233333333333333333"+window.parent.document.getElementById("wfRd.wfNo").value);
		if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
			//alert("fff");
			selectPrjtUsers2();
		}else{
			//alert("ddd");
			pop =  $.ligerDialog.open({title:'添加邮件接收人', height: 390, width: 500,url: './PrjtUsr!addMailUser.shtml',
				showMax: true, showToggle: true, showMin: true, isResize: true
			});
		}
	}
	
	function closepop(){
		pop.close();
	}
	
	
	
	function selectPrjtUsers2(){
		
		if(selectWin!=null){
			selectWin.show();
		}else{
			$("#editgrid").ligerGrid({
				columns: [
					{ display: '角色名', name: 'roleNm', align: 'left', width: 110 },
					{ display: '用户名', name: 'usrName', align: 'left', width: 220}
				],
				checkbox: true,
				rownumbers:true,
				//pageSize:20,
				url:"./PrjtDef!projectUsers.shtml?prjtDef.prjtNo="+$('#prjtNo').val()+'&prjtDef.typId='+$('#typId').val(),
				//usePager:true,
				width: '90%',
				height:'89%',
				usePager:false,
				isChecked: f_isChecked,
				onCheckRow: f_onCheckRow,
				onDblClickRow: f_onDblClickRow,
				onCheckAllRow: f_onCheckAllRow,
				enabledEdit: true,
				//onAfterEdit: f_onAfterEdit,
			    frozen:false,
				//pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
		        showTitle: false,columnWidth:120,
		        //detail: { onShowDetail: f_showOrder,height:'auto' },
		        onError: function (a, b)
		        { 
		        }
		        
			});

			editgridManager = $("#editgrid").ligerGetGridManager();
			
			selectWin = $.ligerDialog.open({title:"选择接收邮件用户", target: $("#editgrid"),width:500,
				buttons: [ {text:'确定',onclick:function(item, dialog){
					  var usrString="";
					  var usrIds="";
					 // var choices="[";
					    if(checkedCustomer.length<1){
					    	alert("请选择收件人");
					    	return;
					    }
						for(var i =0;i<checkedCustomer.length;i++) {
							if(checkedCustomer[i].usrId){
								usrIds+=checkedCustomer[i].usrId+",";
							}
							if(checkedCustomer[i].usrName){
								usrString=usrString+checkedCustomer[i].usrName+"<"+checkedCustomer[i].roleNm+">"+";";
							}
						}
					  usrIds.substring(0,usrIds.length-1);
					  $("#usrIds").val(usrIds);
					  $("#usrString").val(usrString);
					  dialog.hide();
				}},{ text: '取消', onclick: function (item, dialog) { dialog.hide(); } } ] });
			
			
			
			
			
		}
	}
	//send email
	function sendEmail() {
		var str = '{';
		str += '"usrIds":"' + $("#usrIds").val() + '",';
		str += '"mailTitle":"' + $("#mailTitle").val() + '",';
		str += '"mailContent":"' + $("#mailContent").val() + '"';
		str += '}';
		$.post("./WfRd!sendMail.shtml", {
			"usrIds" : $("#usrIds").val(),
			"mailTitle" : $("#mailTitle").val(),
			"mailContent" : $("#mailContent").val()
		}, function(data) {
			$.ligerDialog.success(data);
			if(window.parent&&window.parent.showSendEmailWin){
				setTimeout(function (){
					window.parent.showSendEmailWin.hide();
                }, 3000);
			}
		}, "text");
	}


	function f_showOrder(row, detailPanel,callback)
	{
	    var grid = document.createElement('div'); 
	    $(detailPanel).append(grid);
	    $(grid).css('margin',10).ligerGrid({
	        columns:
	                    [
	                     { display: '名字', name: 'usrName', width: 200 },
	                    { display: '记录类型', name: 'upTyp',  width: 200 ,type:'int',
	        				editor: { type: 'select', data: roleTypData, valueColumnName: 'upTyp' },
	                        render: function (item)
	                        {
	                            if (parseInt(item.upTyp) == 1) return '加入项目组';
	                            if (parseInt(item.upTyp) == 0) return '离开项目组';
	                        }},
	                    { display: '时间', name: 'createDate' , width: 200 }
	                    ], isScroll: false, showToggleColBtn: false, width: '90%',
	       
	       url:'./PrjtUsr!getPUsRecs.shtml?prjtUsrId='+row.id,
	        
	        showTitle: false, columnWidth: 100 ,usePager:false
	         , onAfterShowData: callback,frozen:false
	    });  
	}


	function f_onCheckAllRow(checked) {
		for (var rowid in this.records) {
			if(checked)
				addCheckedCustomer(this.records[rowid]);
			else
				removeCheckedCustomer(this.records[rowid]);
		}
	}

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
</script>



</head>
<body>
	<div id="eform" style="margin-left: 15px; margin-right: 15px; margin-top: 15px;">
		<form id="prjtForm" name="prjtForm" action="PrjtDef!sav.shtml" method="post" enctype="multipart/form-data">
			<input type="hidden" id="usrIds" name="usrIds" value="<c:out value="${usrIds}"/>" />
			<input type="hidden" id="prjtNo" name="prjtDef.prjtNo" size="30" value="<c:out value="${prjtDef.prjtNo}"/>" />
			<input type="hidden" id="typId" name="prjtDef.typId" size="30" value="<c:out value="${prjtDef.typId}"/>"/>
		<input type="hidden" id="wfRd.wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
			<table width="90%">
				<tr>
					<td width="10%">收件人</td>
					<td bgcolor="#ffffff" width="80%" height="24">
					<input type="text" id="usrString" name="usrString" 
					value="<c:out value="${usrString}"/>" style="width: 650px; height: 18px" readonly="true" /> 
					<input type="button" value="选择接收人" onclick="selectPrjtUsers();"></input></td>
				</tr>
				<tr>
					<td width="10%">主题</td>
					<td bgcolor="#ffffff" width="90%" height="24"><textarea id="mailTitle" name="mailTitle" style="width: 750px; height: 18px" rows="1">
			    </textarea></td>
				</tr>
				<tr>
					<td width="10%">正文</td>
					<td width="80%"><textarea id="mailContent" name="mailContent" class="xheditor {skin:'o2007blue',width:'600',height:'350'}">
		         <c:out value="${doc.docDesc}" />
			</textarea></td>
				</tr>
				<tr><td></td><td align="right"><input type="button" value="发送" onclick="sendEmail();" ></input></td></tr>
			</table>
		</form>
	</div>
<select style="display:none" id="mailUser1" name="mailUser1"  multiple size=15></select>
  <div id="editgrid" style="margin-top:1px;margin-left:1px;"></div>
</body>
</html>