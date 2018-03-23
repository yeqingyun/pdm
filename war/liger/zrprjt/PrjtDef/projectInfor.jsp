<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

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
<script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>

<script type="text/javascript">
	var autoid=1;
	var editgridManager;
	var statusData = [{ Status: -1, text: '未配置人员'},{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
	var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];
	var upTypData = [{ UpTyp: 1, text: '加入项目组' }, { UpTyp: 0, text: '离开项目组'}];
$(function () {
	if( (document.getElementById("gpName").value.indexOf("产品经理") > -1) || (document.getElementById("gpName").value.indexOf("项目经理") > -1)||(document.getElementById("gpName").value.indexOf("超级用户")>-1)){
		$.post("ligerToolBar0.shtml",
				{parm:'PrjtDef!projInfor'},
				function(data) {
					$("#toolbar").ligerToolBar(data);
				},
				"json"
		);
	}
	check();
	
	$("#editgrid").ligerGrid({
		columns: [
			{ display: '角色名', name: 'roleNm', align: 'left', width: 130 },
			{ display: '用户名', name: 'usrName', align: 'left', width: 130, 
				render: function (item,i)
                {
			         var id = autoid++;
					 var txtId = "txt"+id; 
				     var selId = "sel"+id; 
				     var rolenm = item.roleNm;
				     //alert(rolenm);
				     //disabled
				      if(rolenm == "CMO"||rolenm == "测试部软件测试工程师"||rolenm == "RF专家组"||rolenm == "结构评审部总监"||rolenm == "研发副总"||rolenm == "公司意见"){
				    	  var html= '<input disabled id="'+txtId+'" value="'+item.usrName+'" size="38" onBlur="textOnBlur('+ id +')" onkeydown="enterTips('+id+')"  onkeyup="showtips2('+id+');if(event.keyCode==27)c();">';
						  html+='<BR>';
						  html+='<select  id="'+selId+'" style="width:230px; position:absolute; z-index:1000;DISPLAY: none" data-roleId='+ item.roleId +'  data-id='+ item.id +' onkeydown=if(event.keyCode==13)rv('+id+')  onclick=rv('+id+')></select>';
						     
				     } else{
					     var html= '<input  id="'+txtId+'" value="'+item.usrName+'" size="38" onBlur="textOnBlur('+ id +')" onkeydown="enterTips('+id+')"  onkeyup="showtips2('+id+');if(event.keyCode==27)c();">';
					     html+='<BR>';
					     html+='<select  id="'+selId+'" style="width:230px; position:absolute; z-index:1000;DISPLAY: none" data-roleId='+ item.roleId +'  data-id='+ item.id +' onkeydown=if(event.keyCode==13)rv('+id+')  onclick=rv('+id+')></select>';
					    }
				     return html;
					
                }	
			
			},
                { display: '电话', name: 'mobile', align: 'left', width: 100 },
                { display: '邮箱地址', name: 'mailAddr', align: 'left', width: 160 },
			{ display: '状态', name: 'status', align: 'left', width: 80 ,type:'int',
				editor: {data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    if (parseInt(item.status) == -1) return '未配置人员';
                    return '有效';
                }}
		],
		/* checkbox: true, */
		rownumbers:true,
		pageSize:100,
		url:"./PrjtDef!projectUsers.shtml?prjtDef.prjtNo="+$('#prjtNo', window.parent.document).val()+'&prjtDef.typId='+document.getElementById("prjtDef.typId").value,
		//usePager:true,
		 width: '75%',
		height:'70%', 
		usePager:true,
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow,
		enabledEdit: true,
		//onAfterEdit: f_onAfterEdit,
	    frozen:false,
		//pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
        showTitle: false,width:'90%',columnWidth:120,
        detail: { onShowDetail: f_showOrder,height:'auto' },
        onError: function (a, b)
        { 
        }
        
	});
	$("#pageloading").hide();
	editgridManager = $("#editgrid").ligerGetGridManager();
	
});


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


function addProjtUser(){
	/**
	alert("fff");
	if(updataArr==null || updataArr.length==0){
		if(isNameExit) {
			isNameExit = false;
			alert(" 用户不存在");
		}else {
			alert(" 你没有添加或修改任何项目组成员");
		}
		return;
	}else {
		if(isNameExit) {
			isNameExit = false;
			alert(" 用户不存在");
			return;
		}
	}
	**/
	//var userstr =eval("(" + updataArr+")");
	var userstr = JSON.stringify(updataArr);
	 var params = {
			 "choices": userstr,
           };
	 $.ajax({
	        url: "./PrjtUsr!batchSavPrjtUsr.shtml?prjtUsr.prjtNo="
				+$('#prjtNo').val()+"&prjtUsr.prjtTypId="+document.getElementById("prjtDef.typId").value+"&prjtUsr.roleTyp=1",
	        type: "POST",
	        data: params,
	        success: function (text) {
	        	userstr = null;
	        	updataArr = [];
	        	$.ligerDialog.success(text);
	        	location.reload() ;
	        }
	 });
}

function ediProjtUser() {
	var data = editgridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	
	
	if(typeof(data.prjtNo)=='undefined')
	{
		$.ligerDialog.warn('"'+data.roleNm+'"'+'属于不随项目变更的角色，该用户不能修改或删除');
		return;
	}else{
		$.ligerDialog.open({title:'编辑对话框：PrjtUsr', height: 560, width: 780,url: './PrjtUsr!edit.shtml?prjtUsr.id='+data.id,showMax: true, showToggle: true, showMin: true, isResize: true});
	}
}




function delProjtUser() {
	var data1 = editgridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择删除的记录。');
		return;
	}
	
	var choices="[";
	for(var i =0;i<checkedCustomer.length;i++) {
		
		if(typeof(checkedCustomer[i].prjtNo)=='undefined')
		{
			$.ligerDialog.warn('"'+checkedCustomer[i].roleNm+'"'+'属于不随项目变更的角色，该用户不能修改或删除');
			return;
		}else{
			choices = choices+JSON.stringify(checkedCustomer[i])+",";
		}
		
	}
	choices= choices.substring(0,choices.length-1);
	choices +="]";
	$.ligerDialog.confirm('确定要删除记录？', 
		function (type) { 
			if(type) {
				$.post("PrjtUsr!del.shtml",
					{'choices': choices},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}

var oldParam;

var sel;
var txt;

function showtips2(index){
	var param = document.getElementById("txt"+index).value;
	txt = document.getElementById("txt"+index);
	sel = document.getElementById("sel"+index)
	var j = 0;
	if (param != null && param!=' ') {
		if(param.length>0 && param.length<5 && oldParam!=param){
			$.ajax({
				 type:"post",  
			       url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
			       dataType:"json",
			       success: function fun1(jsonData) { 
			    	    $("#"+"sel"+index).empty();
			   			$.each(jsonData,function(i,item){
							var dept = "";
							if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
								dept = " -- " + item.deptNm;
							}
							//15*n px;
							if(i>10){
								$("#"+"sel"+index).css("height","200px");
							}else
								{
								$("#"+"sel"+index).css("height", ((i+1)*20) + "px");
							}
							$("#"+"sel"+index).append("<option value='"+item.id+"'>" + item.usrName + dept+"</option>");  
						});
			   			if(jsonData.length>0){
							sel.size = (j > 1) ? j : 2;
							sel.size = (sel.size>17) ? 17 :sel.size;
							sel.style.display = '';
							txt.style.border="1px solid #B5CBF7";
						}else{
							txt.style.border="1px solid red";
							c();
						}
			       }
			});
		}
	}else{
		c();
	}
	oldParam = param;
}
var isNameExit = false;
function textOnBlur(index) {
	txt = document.getElementById("txt"+index);
	sel = document.getElementById("sel"+index);
	if(txt.value == null || txt.value.replace(/[ ]/g,"") == '') {
		txt.style.border="1px solid #B5CBF7";
		//alert("ddd");
		isNameExit = false;
	}else {
		if(sel.value == null || sel.value == ''){
			isNameExit = true;
		}
	}
}

function enterTips(index) {
	sel = document.getElementById("sel"+index);
	txt = document.getElementById("txt"+index)
	e = event.keyCode;
	//var sel = document.getElementById("sel");
	if (sel.style.display != 'none') {
		if (e == 13)
			event.srcElement.value = sel.value, sel.style.display = 'none';
		if (e == 40)
			sel.focus();
	}
}

var updataArr=[];
var updataItem;

function rv(index) {
	sel = document.getElementById("sel"+index);
	txt = document.getElementById("txt"+index)
    txt.value = sel.options[sel.selectedIndex].text;
    var currentid=sel.options[sel.selectedIndex].value;
    var roleid=$("#sel"+index).attr("data-roleId");
    var rowid=$("#sel"+index).attr("data-id");
    $(updataArr).each(function(i,item){
    		//{id:1,roleId:1,usrId:777}
    		if(item.id==rowid){
    			item.usrId=currentid;
    			updataItem=item;
    		}
    	});

    	if(updataItem!=null){
    		//updataItem.usrId=currentid;
    		
    	}else{
    		if(rowid == "undefined"){
    			updataItem={roleId:roleid,usrId:currentid};
    	}else{
    			updataItem={id:rowid,roleId:roleid,usrId:currentid};
    	}
    		//相同的角色只能有一个项目组成员
   		var exist = false;
   		 $(updataArr).each(function(i,item){
   			 if(item.roleId == updataItem.roleId){
   				 item.usrId =  updataItem.usrId;
   				 exist= true;
   			 }
   		 });
   		 if(!exist){
    		updataArr.push(updataItem);
   		 }
   		updataItem = null;
    }
    editgridManager.endEdit(index);
	oldParam = txt.value;
	c();
}
function c() {
	if(sel!=null){
		sel.style.display = 'none';
	}
}
document.onclick = function() {
	c();
}

 function  showSendMailDailog(){
	 
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
	if(window.parent) {
		if(window.parent.parent) {
			window.parent.parent.f_open(
				"./PrjtDef!showSendMail.shtml?usrIds="+usrIds+"&usrString="+usrString,
				"发邮件",
				"./include/images/Alien Folder.png"
			);
		}
	}
}

</script>
</head>
<body>

<div id="toolbar"></div>
<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<input type="hidden" id="prjtNo" name="prjtNo" size="30" value="<c:out value="${prjtDef.prjtNo}"/>" />
<input type="hidden" id="prjtDef.typId" name="prjtDef.typId" size="30" value="<c:out value="${prjtDef.typId}"/>"/>
<input type="hidden" id="gpName" name="gpName" size="30" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>" />
<form id="inforForm" >
    <!-- 
    <input type="button" onclick="showSendMailDailog()" value="发邮件" />
     -->
  <div id="editgrid" style="margin-top:1px;margin-left:1px;"></div>
</form>
</div>

</body>
</html>