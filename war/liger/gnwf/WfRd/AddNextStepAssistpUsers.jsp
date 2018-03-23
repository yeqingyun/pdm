<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" / >
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrprjt/prjtUsr.js" type="text/javascript"></script>

<script type="text/javascript">


/* var wfNoVal = window.parent.parent.document.getElementById("wfRd.wfNo").value; */
$(function(){
	//获取已经选取的人员名单加载到”人员列表“中
	var userIndex = "<%=request.getAttribute("userNameIDIt")%>";
	
	var parentUserIDs = window.parent.document.getElementById("userId"+userIndex).value;
	var parentUserNames = window.parent.document.getElementById("userName"+userIndex).value;
	var objmailUser = document.getElementById("mailUser");
	
	if(parentUserIDs != '' && parentUserIDs != null ){
	var parentUserIDsSplit = parentUserIDs.split(",");
	var parentUserNamesSplit = parentUserNames.split(",");
	 for(i=0;i<parentUserIDsSplit.length;i++){
        		var optz = document.createElement("option");
        		optz.value = parentUserIDsSplit[i];
        		optz.text = parentUserNamesSplit[i];
        		objmailUser.add(optz);
    } 
	}
	
	/* initPro(wfNoVal); */
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
			     $("#mailUser").html(options);
			    //alert(data.allUsr.length); 
			}
	});
   } 



function ckaddto(p0,p1) {
	var obj0 = document.getElementById(p0);
	var obj1 = document.getElementById(p1);

	for(i=0;i<obj0.length;i++){
        if(obj0.options[i].selected){
        	if(!findoptz(p1,obj0.options[i].text)) {
        		var optz = document.createElement("option");
        		optz.value = obj0.options[i].value;
        		optz.text = obj0.options[i].text;

        		obj1.add(optz);
        	}
        }
    }
}

function findoptz(p2,value) {
	var obj2 = document.getElementById(p2);
	for(n=0; n<obj2.length; n++) {
		if(obj2.options[n].text == value)
			return true;
	}
	return false;
}


function ckdelopt(p3) {
	var obj3 = document.getElementById(p3);
	
	for(m=obj3.length-1;m>=0;m--){   
        if(obj3.options[m].selected){
        	obj3.remove(m);
        }
    }
}


function saveMailUser(id) {
	var usrIds;
	var usrString;
	var oldUsrIds;
	var oldUsrString;
	
	var userNameIDIt = "<%=request.getAttribute("userNameIDIt")%>"
	usrIds = $("userId"+userNameIDIt, window.parent.document).val();
	usrString = $("#userName"+userNameIDIt, window.parent.document).val();
	
	if(usrString == null || usrString == ''){
		//alert("null");
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
	}else{
		//alert("notnull");
		for(i=0; i<document.getElementById(id).options.length; i++) {
			
				usrString += "," + document.getElementById(id).options[i].text;
			    usrIds += "," + document.getElementById(id).options[i].value;
			
		}
	}
	//协办人
	window.parent.selectAssistpProjectUserForFlow(usrIds,usrString,userNameIDIt);
	
	 window.parent.closepop();
}


$(function () {
	 $(".intro").click(function(){
	       
		 alert("f_onClose");
		 /**
     	var x=document.getElementById("roleId");
     	if(x.size>0){
     		alert("项目还有角色没有配置人员！不能关闭"); 
     		 return false;
     	}
     	 return true;
	        **/
	 });
	 
	 check();
	
	 if(document.getElementById("prjtUsr.roleTyp").value==1){
		 
		 document.getElementById("prjtNoLable").innerHTML = "项目编号:";
		 document.getElementById("prjtNo").style.display = "";
		 document.getElementById("prjtTypeId").style.display = "none";
		/**
		 $("#prjtNo").ligerComboBox({url:"PrjtUsr!getpDefs.shtml",  width:186, textField:"prjtNm", valueField:"prjtNo", isMultiSelect: false,
				onSelected: function (newvalue)
		        {
					$.post("PrjtUsr!getpRoles.shtml",
							{'prjtNo':newvalue,'prjtRole.roleTyp':1},
							function(data) {
					            $("#roleId").ligerGetComboBoxManager().setData(data);
					            document.getElementById("prjtUsr.prjtNo").value = newvalue;
							},
							"json"
						);
		        }
		  });
		**/
	 }else {
		 
		 document.getElementById("prjtNoLable").innerHTML = "项目类型:";
		 document.getElementById("prjtNo").style.display = "none";
		 document.getElementById("prjtTypeId").style.display = "";
		 
		 $("#prjtTypeId").ligerComboBox({url:"PrjtUsr!getprjtTypes.shtml",  width:186, textField:"typNm", valueField:"typId", isMultiSelect: false,
				onSelected: function (newvalue)
		        {
				    document.getElementById("prjtUsr.prjtTypId").value = newvalue;
				  
					$.post("PrjtUsr!getpRoles.shtml",
							{'prjtRole.prjtTypId':newvalue,'prjtRole.roleTyp':0},
							function(data) {
								
								$("#roleId").empty();
					   			$.each(data,function(i,item){
					   				$("#roleId").append("<option value='"+item.roleId+"'>" + item.roleNm +"</option>");  
								});
							},
							"json"
						);
				  
		        }
		  });
		
	 }
	 
	 
	 
	 /**
	 $("#roleId").ligerComboBox({data:null,  width:186, textField:"roleNm", valueField:"roleId", isMultiSelect: false});
	 
	 
	 
	 $("#comId").ligerComboBox({url:"PrjtUsr!getpComs.shtml",  width:186, textField:"comNm", valueField:"comId", isMultiSelect: false,
			onSelected: function (newvalue)
	        {
				var str = '{';
				str += '"usr.comId":"'+newvalue+'",';
				str += '"usr.deptId":"'+$("#depId").ligerGetComboBoxManager().getValue()+'"';
				str += '}';
				
				$.post("PrjtUsr!getpUsers.shtml",
						JSON.parse(str),
						function(data) {
				            $("#usrId").ligerGetComboBoxManager().setData(data);
						},
						"json"
					);
	        }
	  });
	 
	 $("#depId").ligerComboBox({url:"PrjtUsr!getpDepts.shtml",  width:186, textField:"deptNm", valueField:"deptId", isMultiSelect: false,
			onSelected: function (newvalue)
	        {
				var str = '{';
				str += '"usr.deptId":"'+newvalue+'",';
				str += '"usr.comId":"'+$("#comId").ligerGetComboBoxManager().getValue()+'"';
				str += '}';
				
				$.post("PrjtUsr!getpUsers.shtml",
						JSON.parse(str),
						function(data) {
				            $("#usrId").ligerGetComboBoxManager().setData(data);
						},
						"json"
				);
	        }
	  });
	 
	 
	 $("#usrId").ligerComboBox({data:null,  width:186, textField:"usrName", valueField:"id", isMultiSelect: false});
	**/
	 var statusData =
		    [{ id: 0, text: '无效' },
		    { id: 1, text: '有效' }
		   ];
	 $("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
	 $.ligerui.get("status").selectValue(1);
	
	if ($("#priority").length > 0){
		$("#priority").ligerTextBox();
		$("#priority").val(1);
	}
	
	 $.ligerui.get("prjtNo").selectValue(document.getElementById("prjtUsr.prjtNo").value);
});


//用户名模糊搜索


</script>
</head>
<body>


<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
   <input type="hidden" id="prjtUsr.prjtNo" name ="prjtUsr.prjtNo" value="<c:out value="${prjtUsr.prjtNo}"/>"/>
   <input type="hidden" id="isAdd" name ="isAdd" value="<c:out value="${isAdd}"/>"/>
   
   <input type="hidden" id="prjtUsr.roleTyp" name ="prjtUsr.roleTyp" value="<c:out value="${prjtUsr.roleTyp}"/>"/>
   
    <input type="hidden" id="prjtUsr.prjtTypId" name ="prjtUsr.prjtTypId" value="<c:out value="${prjtUsr.prjtTypId}"/>"/>
    <input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
  	<input type="hidden" id="wfRdwfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
<form>
	<table width="100%"  >
		<tr>
		 <td height="280" valign="top" align="center" >
			<table width="100%" border="0">
				<tr>
				<td height="25" align="left">
					<font color="blue">按名字搜索:</font><br>
					<input id="txt" name="usrName"  size="22" value="">&nbsp;
					<input id="txtBtnId" type="button"  value="搜索" onclick="showtips('txt','usrId');" 
					 	<c:if test="${!empty constantUsers}">disabled="disabled"</c:if>>
				</td>
				</tr>
				
				<tr>
				<td id="selectTd" align="left">
					<br>
					<font color="blue">待选人员列表:</font>
					<br>
					<c:if test="${empty constantUsers}">
						<select id="usrId" name="prjtUsr.usrId" style="width:186px" multiple size=13></select>
					</c:if>
					
				</td>
				</tr>
			</table>
		</td>
		
		<td valign="" align="center">
			<input type="button" value="添加->" onclick="ckaddto('usrId','mailUser')"/><br>
			<input type="button" value="<-删除" onclick="ckdelopt('mailUser')"/>
		</td>
		
		<td height="280" valign="top" align="center" >
			<table width="100%" border="0">
				<tr>
				<td id="selectTd" align="left">
					<br>
					<font color="blue">人员列表:</font>
					<br>
					<select id="mailUser" name="mailUser" style="width:186px" multiple size=15></select>
				</td>
				</tr>
			</table>
		</td>
	</table>
	
	 <table width="100%">
	  <tr>
	  <td></td>
	  <td align="right"><input type="button" class="wfbigbtn2" value="  确定   " onclick="saveMailUser('mailUser')"/></td>
	  </tr>
	 </table>
</form>
</html>