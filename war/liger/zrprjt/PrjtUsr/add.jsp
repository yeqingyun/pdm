<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

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

<script src="./include/js/zrprjt/prjtUsr.js" type="text/javascript"></script>

<script type="text/javascript">
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
	 
	 
	
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtUsr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
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

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
   <input type="hidden" id="prjtUsr.prjtNo" name ="prjtUsr.prjtNo" value="<c:out value="${prjtUsr.prjtNo}"/>"/>
   <input type="hidden" id="isAdd" name ="isAdd" value="<c:out value="${isAdd}"/>"/>
   
   <input type="hidden" id="prjtUsr.roleTyp" name ="prjtUsr.roleTyp" value="<c:out value="${prjtUsr.roleTyp}"/>"/>
   
    <input type="hidden" id="prjtUsr.prjtTypId" name ="prjtUsr.prjtTypId" value="<c:out value="${prjtUsr.prjtTypId}"/>"/>
  
<form>

	<table width="90%"  >
	   
	   <tr>
		    
		    <td height="24" class="intro" width="90" id="prjtNoLable">项目编号：</td>
			<td>
			
			    <input type="text" id="prjtTypeId" name="prjtTypeId" size="30" />
			    <input type="text" id="prjtNo" name="prjtUsr.prjtNo" size="30" disabled="disabled" value="<c:out value="${prjtUsr.prjtNo}"/>" />
			</td>
		</tr>
	   
		<tr>
			<td height="24" width="90" >角色：</td>
			<td height="280" valign="top" >
			
			      <select id="roleId" name="prjtUsr.roleId" style="width:186px" multiple size=17>
						<c:forEach items="${prjtRoles}" var="prjtRole" varStatus="s">
							<option value="<c:out value="${prjtRole.roleId}"/>" <c:if test="${s.count==1}">selected</c:if>>
								<c:out value="${prjtRole.roleNm}"/>
							</option>
						</c:forEach>
					</select >
			
			
			   <!--input type="text" id="roleId" name="prjtUsr.roleId" size="30" validate="{required:true}" /  -->
			</td>
		
		 <td height="24" width="90" >人员：</td>	
		 <td height="280" valign="top" align="center" >
			<table width="100%" border="0">
				<tr>
				<td height="25" align="left">
					<font color="blue">按名字搜索:</font><br>
					<input id="txt" name="usrName"  size="22" value="">&nbsp;
					<input id="txtBtnId" type="button" class="wfbtn" value="搜索" onclick="showtips('txt','usrId');" 
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
					<c:if test="${!empty constantUsers}">
						<select id="usrId" name="prjtUsr.usrId" style="width:186px" multiple size=13>
							<c:forEach items="${constantUsers}" var="u" varStatus="s">
								<option value="<c:out value="${u.userId}"/>" <c:if test="${s.count==1}">selected</c:if>>
									<c:out value="${u.usrName}"/>
								</option>
							</c:forEach>
						</select>
					</c:if>
				</td>
				</tr>
			</table>
		</td>
			 
		</tr>
		
		
		<tr>
			<td height="24" width="90" >优先级别：</td>
			<td><input type="text" id="priority" validate="{required:true}" name="prjtUsr.priority" size="30" /></td>
			<td height="24" width="90" >状态：</td>
			   <td>
			   <input type="text" id="status" name="prjtUsr.status" size="30" validate="{required:true}" />
			  </td>
		</tr>
		

	</table>
</form>

</div>
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>