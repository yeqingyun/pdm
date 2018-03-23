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


//ckaddto('usrId','mailUser')
function ckaddto(p0,p1) {
	var obj0 = document.getElementById(p0);//usrId
	var obj1 = document.getElementById(p1);//mailUser

	for(i=0;i<obj0.length;i++){
        if(obj0.options[i].selected){
        	if(!findoptz(p1,obj0.options[i].text)) {
        		var optz = document.createElement("option");
        		optz.value = obj0.options[i].value;
        		optz.text = obj0.options[i].text.split("--")[0];
        		obj1.add(optz);
        	}
        }
    }
}

function findoptz(p2,value) {
	var obj2 = document.getElementById(p2);
	for(n=0; n<obj2.length; n++) {
		//alert(obj2.options[n].text+"11111"+value.split("--")[0]);
		if(obj2.options[n].text == value.split("--")[0])
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


//通过名字查询责任人
function showtipsForPjUser(from,to){
	var param = document.getElementById(from).value;
	var thisEle = document.getElementById(to);
	var $thisEle = $(thisEle);
	if(encodeURI(encodeURI(param)) == null || encodeURI(encodeURI(param)) == ''){
		showAllPjUser('txt','usrId');
	}else{
		// alert(param); 
		//浏览器兼容判断
		if(navigator.appName != "Microsoft Internet Explorer"){
			if (!(param!=null && param.trim()!='' && param.length>0 && param.length<5)) {
				return;
			}
		}
		
		$.ajax({
		       type:"post",  
		       url:"PrjtUsr!selectprjtUsrlist.shtml?prjtNo="+$("#prjtNo").val()+"&usr.usrName="+encodeURI(encodeURI(param)),
		       dataType:"json",
		       success: function fun1(jsonData) {  
		    	   //alert(JSON.stringify(jsonData.usrs[0]));
		   			$thisEle.empty();
		   			$.each(jsonData.usrs,function(i,item){
							var roleNm = "";
							roleNm = "--" + jsonData.usrs[i].roleNm;
						$thisEle.append("<option value='"+item.usrId+"'>" + item.usrName +roleNm +"</option>");  
					});
		       }
		});
		
	}
	
}
//获取已经选取的人员名单加载到”人员列表“中
$(function(){
	
	showAllPjUser('txt','usrId');
	var parentUserIDs = window.parent.document.getElementById("responsibleUID").value;
	var parentUserNames = window.parent.document.getElementById("wfQues_usrName").value;
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

//加载所有项目成员列表
function showAllPjUser(from,to){
	/* alert("2222"); */
	var param = document.getElementById(from).value;
	/* alert(param); */
	var thisEle = document.getElementById(to);
	/* alert(thisEle); */
	var $thisEle = $(thisEle);
	// alert(param); 
	//浏览器兼容判断
	/* if(navigator.appName != "Microsoft Internet Explorer"){
		if (!(param!=null && param.trim()!='' && param.length>0 && param.length<5)) {
			return;
		}
	} */
	/* alert("333"); */
	$.ajax({
	       type:"post",  
	       url:"PrjtUsr!selecAllUsrlist.shtml?prjtNo="+$("#prjtNo").val(),
	       dataType:"json",
	       success: function fun1(jsonData) {  
	    	   //alert(JSON.stringify(jsonData.usrs[0]));
	   			$thisEle.empty();
	   			$.each(jsonData.usrs,function(i,item){
						var roleNm = "";
						roleNm = "--" + jsonData.usrs[i].roleNm;
					$thisEle.append("<option value='"+item.usrId+"'>" + item.usrName +roleNm +"</option>");  
				});
	       }
	});
}

//保存选中的成员到责任人中

function saveMailUser(id) {
	var usrIds;
	var usrString;
	var oldUsrIds;
	var oldUsrString;
	
	usrIds = $("#responsibleUID", window.parent.document).val();
	usrString = $("#wfQues_usrName", window.parent.document).val();
	 
		if(document.getElementById(id).options.length == 0){
			usrString = "" ;
		    usrIds = "";
			
		}else{
			usrString = "" ;
		    usrIds = "";
			
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
		}
	window.parent.setUsrData(usrIds,usrString);
	window.parent.dialog33.close();
}





</script>
</head>
<body>


<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<input type="hidden" id="prjtNo" name ="prjtNo" value="<c:out value="${prjtNo}"/>"/>
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
					<input id="txtBtnId" type="button"  value="搜索" onclick="showtipsForPjUser('txt','usrId');" 
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