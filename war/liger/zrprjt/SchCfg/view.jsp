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
<script src="./include/js/zrprjt/schCfg.js?v=1.1" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'SchCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();
	
	if ($("#schId").length > 0)
		$("#schId").ligerTextBox();
	//if ($("#parent").length > 0)
	//	$("#parent").ligerComboBox();
	//if ($("#flowId").length > 0)
	//	$("#flowId").ligerTextBox();
	//if ($("#typId").length > 0)
	//	$("#typId").ligerComboBox();
	if ($("#schNo").length > 0)
		$("#schNo").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#cfgTime").length > 0)
		$("#cfgTime").ligerTextBox();
	//if ($("#status").length > 0)
	//	$("#status").ligerComboBox();
	
	/**if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	**/
	if ($("#schNm").length > 0)
		$("#schNm").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	
	
	
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:"typNm",  width:186, valueField:"typId",  isMultiSelect: false,
		onSelected: function (newvalue)
        {
			document.getElementById("schCfg.typId").value = newvalue;
			$.post("SchCfg!getPreSchCfgs.shtml",
					{'schCfg.typId':newvalue},
					function(data) {
			            $("#parent").ligerGetComboBoxManager().setData(data);
					},
					"json"
				);
        }
    });
	
    $("#parent").ligerComboBox({data: null, textField:"schNm",  width:186, valueField:"schId",isMultiSelect: false,
    	onSelected: function (newvalue)
        {
    		
    		document.getElementById("schCfg.parent").value = newvalue;
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
        
    
  //  alert(document.getElementById("schCfg.typId").value);
  //  $("#typId").selectValue(1);
  
    $.ligerui.get("typId").selectValue(document.getElementById("schCfg.typId").value);
    $.ligerui.get("parent").selectValue(document.getElementById("schCfg.parent").value);
    $.ligerui.get("flowId").selectValue(document.getElementById("schCfg.schWf.flowId").value);
    $.ligerui.get("status").selectValue(document.getElementById("schCfg.status").value);
    $.ligerui.get("critical").selectValue(document.getElementById("schCfg.critical").value);
    $.ligerui.get("milestone").selectValue(document.getElementById("schCfg.milestone").value);
    $.ligerui.get("manualStart").selectValue(document.getElementById("schCfg.manualStart").value);
    //alert(document.getElementById("aa").value)
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<input type="hidden" id="schCfg.schId" name="schCfg.schId" size="30" value="<c:out value="${schCfg.schId}"/>"/>
	
	
	<input type="hidden" id="schCfg.parent" name="schCfg.parent" size="30" value="<c:out value="${schCfg.parent}"/>" />
    <input type="hidden" id="schCfg.typId" name="schCfg.typId" size="30" value="<c:out value="${schCfg.typId}"/>" />
    
    
    <input type="hidden" id="schCfg.schWf.flowId" name="schCfg.schWf.flowId" size="30" value="<c:out value="${schCfg.schWf.flowId}"/>" />
    <input type="hidden" id="schCfg.status" name="schCfg.status" size="30" value="<c:out value="${schCfg.status}"/>" />
    
     <input type="hidden" id="schCfg.critical" name="schCfg.critical" size="30" value="<c:out value="${schCfg.critical}"/>" />
    <input type="hidden" id="schCfg.milestone" name="schCfg.milestone" size="30" value="<c:out value="${schCfg.milestone}"/>" />
	 <input type="hidden" id="schCfg.manualStart" name="schCfg.manualStart" size="30" value="<c:out value="${schCfg.manualStart}"/>" />
    
    
	
	<input type="hidden" id="aa" name="aa" size="30" value="<c:out value="${schCfg.schWf.flowId}"/>"/>
	
	<table width="90%">
		<tr> 
		   <td height="24" width="90" >项目分类：</td>
			 <td>
		      <input type="text" id="typId" name="schCfg.typId" size="30" />
			</td>
			
			<td height="24" width="90" >父级进度：</td>
			   <td>
			   <input type="text" id="parent" name="schCfg.parent" size="30" />
			   </td>
		</tr><tr>
		    <td height="24" width="90" >进度名称：</td><td><input type="text" id="schNm" name="schCfg.schNm" size="30" value="<c:out value="${schCfg.schNm}"/>"/></td>
			<td height="24" width="90" >序号：</td><td><input type="text" id="schNo" name="schCfg.schNo" size="30" value="<c:out value="${schCfg.schNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" >级别：</td><td><input type="text" id="leve" name="schCfg.leve" size="30" value="<c:out value="${schCfg.leve}"/>"/></td>
			<td height="24" width="90" >工时：</td><td><input type="text" id="cfgTime" name="schCfg.cfgTime" size="30" value="<c:out value="${schCfg.cfgTime}"/>"/></td>
		
		</tr>
		<tr>
		
		   <td height="24" width="90" >工作流：</td>
		     <td>
			    <input type="text" id="flowId" name="schCfg.schWf.flowId" size="30" validate="{required:true}" value="<c:out value="${schCfg.schWf.flowId}"/>" />
			 </td>
		    <td height="24" width="90" >状态：</td>
			 <td>
			  <input type="text" id="status" name="schCfg.status" size="30" validate="{required:true}" value="<c:out value="${schCfg.status}"/>"/>
			</td>
		</tr>
		<tr>
		  <td height="24" width="90" >关键进度：</td>
			  <td>
			   <input type="text" id="critical" name="schCfg.critical" size="30" validate="{required:true}" value="<c:out value="${schCfg.critical}"/>"/>
			  </td>
	
		     <td height="24" width="90" >里程碑：</td>
			  <td>
			   <input type="text" id="milestone" name="schCfg.milestone" size="30" validate="{required:true}" value="<c:out value="${schCfg.milestone}"/>"/>
			  </td>
         </tr>
         <tr>
			<td height="24" width="90" >备注：</td><td><input type="text" id="remark" name="schCfg.remark" size="30" value="<c:out value="${schCfg.remark}"/>"/></td>
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