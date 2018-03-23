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

<script src="./include/js/common.js" type="text/javascript"></script>




<script src="./include/js/zrprjt/schCfg.js?v=1.1" type="text/javascript"></script>

<script type="text/javascript">
var flowIdmanager;

$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'SchCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	check();
	
	if ($("#schNo").length > 0)
		$("#schNo").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#cfgTime").length > 0)
		$("#cfgTime").ligerTextBox();
	//if ($("#status").length > 0)
	//	$("#status").ligerComboBox();
	if ($("#schNm").length > 0)
		$("#schNm").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	
	
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:"typNm", valueField:"typId", width:186, isMultiSelect: false,
		onSelected: function (newvalue)
        {
			$.post("SchCfg!getPreSchCfgs.shtml",
					{'schCfg.typId':newvalue},
					function(data) {
			            $("#parent").ligerGetComboBoxManager().setData(data);
					},
					"json"
				);
        }
    });
	
    $("#parent").ligerComboBox({data: null, textField:"schNm", width:186, valueField:"schId",isMultiSelect: false,
    	onSelected: function (newvalue)
        {
    		if(newvalue==""){
    			
    		 $("#leve").val(0);
    		}else {
    			 $("#leve").val(1);
    		}
        }
    });
    
 $("#flowId").ligerComboBox({url:"SchCfg!getWfs.shtml",  width:186, textField:"flowName", valueField:"flowId", isMultiSelect: false});
 flowIdmanager = $("#flowId").ligerGetComboBoxManager();
    
var statusData =
    [{ id: 0, text: '无效' },
    { id: 1, text: '有效' }
   ];
$("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
var statusmanager = $("#status").ligerGetComboBoxManager();


var manualStartData =
    [{ id: 0, text: '否' },
    { id: 1, text: '是' }
   ];
$("#manualStart").ligerComboBox({ data: manualStartData, width:186, isMultiSelect: false });
var manualStartmanager = $("#manualStart").ligerGetComboBoxManager();


   
var criticalData =
    [{ id: 0, text: '否' },
    { id: 1, text: '是' }
   ];
$("#critical").ligerComboBox({ data: criticalData, width:186, isMultiSelect: false });
var criticalmanager = $("#critical").ligerGetComboBoxManager();
   
var milestoneData =
    [{ id: 0, text: '否' },
    { id: 1, text: '是' }
   ];
$("#milestone").ligerComboBox({ data: milestoneData, width:186, isMultiSelect: false });
var milestonemanager = $("#milestone").ligerGetComboBoxManager();
    

});


$("#workFlow").ligerComboBox();


function  aa(){
	alert(flowIdmanager.getValue());
}

/**function setChgTyp(from,to) {
	
	$.post("SchCfg!sav.shtml",
			JSON.parse(str),
			function(data) {
				$.ligerDialog.success(data);
			},
			"text"
		);
	try {
		var param = document.getElementById(from).value;
		var xmlhttp = sendXmlhttpRequest("GET","HrEmpXml.shtml?hrEmp.deptId="+param);
		
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var thisEle=document.getElementById(to);
            var $thisEle=$(thisEle);
   			$thisEle.empty();
			var jsonData = eval(xmlhttp.responseText);
			$thisEle.append("<option value=''>请选择</option>");  
			$.each(jsonData,function(i,item){
				$thisEle.append("<option value='"+item.empID+"'>" + item.empName+"</option>");  
			});
		}

	}
	catch(e) {
		alert("nodechange exception!");
		return null;
	}
}


$("#typId").
**/
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>

  <input type="hidden" id="prjtTypJson" name ="prjtTypJson" value="<c:out value="${prjtTypJson}"/>"/>
  

	<table width="90%">
		<tr>
		   <td height="24" width="90" >项目分类：</td>
			<td>
			  <input type="text" id="typId" name="schCfg.typId" size="30" validate="{required:true}" value="<c:out value="${schCfg.typId}"/>"/>
			  
			    <!--select id="typId" name="schCfg.typId" style="width:135px" onchange="setChgTyp('typId','parent')">
					<option value="">请选择</option>
					<c:forEach items="${prjtTyps}" var="prjtTyp">
						<option value="<c:out value="${prjtTyp.typId}"/>"><c:out value="${prjtTyp.typNm}"/></option>
					</c:forEach>
				</select-->
			</td>
			
			<td height="24" width="90" >父级进度：</td>
			   <td>
			   <input type="text" id="parent" name="schCfg.parent" size="30" value="<c:out value="${schCfg.parent}"/>"/>
			   
			   <!--select id="parent" name="schCfg.parent" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${schCfgs}" var="schCfg">
						<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
					</c:forEach>
				</select-->
			 </td>
			
		</tr><tr>
			<td height="24" width="90" >任务名称：</td><td><input type="text" id="schNm" name="schCfg.schNm" size="30" validate="{required:true}" value="<c:out value="${schCfg.schNm}"/>"/></td>
			<td height="24" width="90" >序号：</td><td><input type="text" id="schNo" name="schCfg.schNo" size="30" value="<c:out value="${schCfg.schNo}"/>"/></td>
		</tr><tr>
			<!--td height="24" width="90" >级别：</td><td><input type="text" id="leve" name="schCfg.leve" size="30" value="<c:out value="${schCfg.leve}"/>" /></td-->
		    <!--td height="24" width="90" >级别：</td><td><input type="text" id="leve" name="schCfg.leve" size="30" value="0" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td-->
			<td height="24" width="90" >工时：</td><td><input type="text" id="cfgTime" name="schCfg.cfgTime" size="30" value="<c:out value="${schCfg.cfgTime}"/>"/></td>
		  <td height="24" width="90" >工作流：</td>
		  <td>
			   <!--input type="text" id="workFlow" name="schWf.flowId" size="30" value="<c:out value="${schWf.flowId}"/>"/-->
			    <input type="text" id="flowId" name="schCfg.schWf.flowId" size="30"  value="<c:out value="${schCfg.schWf.flowId}"/>" />
			   
			    <!--select id="flowId" name="schCfg.schWf.flowId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${workFlows}" var="wfCfg">
						<option value="<c:out value="${wfCfg.flowId}"/>"><c:out value="${wfCfg.flowName}"/></option>
					</c:forEach>
				</select  -->
			 </td>
		</tr><tr>
			<td height="24" width="90" >状态：</td>
			  <td>
			  
			   <input type="text" id="status" name="schCfg.status" size="30" validate="{required:true}" value="<c:out value="${schCfg.status}"/>"/>
			   
			   <!--select id="status" name="schCfg.status" style="width: 196px">
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			   <input type="text" id="status" name="schCfg.status" size="30" value="<c:out value="${schCfg.status}"/>"/  -->
			  </td>
			  
			  <td height="24" width="90" >关键进度：</td>
			  <td>
			    <input type="text" id="critical" name="schCfg.critical" size="30" validate="{required:true}" value="<c:out value="${schCfg.critical}"/>"/>
			   <!--select id="critical" name="schCfg.Critical" style="width: 196px">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			   <input type="text" id="status" name="schCfg.status" size="30" value="<c:out value="${schCfg.status}"/>"/  -->
			  </td>
		</tr><tr>
		
		     <td height="24" width="90" >里程碑：</td>
			  <td>
			   <input type="text" id="milestone" name="schCfg.milestone" size="30" validate="{required:true}" value="<c:out value="${schCfg.milestone}"/>"/>
			   <!--select id="milestone" name="schCfg.Milestone" style="width: 196px">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			   <input type="text" id="status" name="schCfg.status" size="30" value="<c:out value="${schCfg.status}"/>"/  -->
			  </td>
			<td height="24" width="90" >备注：</td><td><input type="text" id="remark" name="schCfg.remark" size="30" value="<c:out value="${schCfg.remark}"/>"/></td>
		</tr>
		
		<tr>
			<td height="24" width="90" >手工启动：</td>
			  <td>
			   <input type="text" id="manualStart" name="schCfg.manualStart" size="30" validate="{required:true}" value="<c:out value="${schCfg.manualStart}"/>"/>
			  </td>
		</tr>

	</table>
</form>

</div>

</body>
</html>