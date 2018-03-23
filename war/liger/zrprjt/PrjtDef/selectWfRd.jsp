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

<script src="./include/js/gnwf/wfRd.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	/**
	$.post("ligerToolBar0.shtml",
			{parm:'WfRd'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	**/
	
	var statusData = [{ Status: 0, text: '进行中' }, { Status: 1, text: '进行中'},{ Status: 2, text: '已完成'},{ Status: 3, text: '已终止'},{ Status: 4, text: '已作废'}];

	
	$("#maingrid").ligerGrid({
		columns: [

            { display: '进度名称', name: 'taskNm', align: 'left', width: 180 },
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 120 },
			{ display: '工作流标题', name: 'wfName', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				//editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) {
                    	return "<font color='blue'>进行中</font>";
                    }else if(parseInt(item.status) == 1){
                    	return "<font color='blue'>进行中</font>";
                    }else if(parseInt(item.status) == 2){
                    	return "<font color='green'>已完成</font>";
                    }else if(parseInt(item.status) == 3){
                    	return "<font color='gray'>已终止</font>";
                    }else if(parseInt(item.status) == 4){
                    	return "<font color='gray'>已作废</font>";
                    }
                    
                }},
			{ display: '计划开始时间', name: 'planSDate', align: 'left', width: 120 },
			{ display: '计划完成时间', name: 'planEDate', align: 'left', width: 120 },
			{ display: '实际开始时间', name: 'factSDate', align: 'left', width: 120 },
			{ display: '实际完成时间', name: 'factEDate', align: 'left', width: 120 }
			
                
			//{ display: '创建人', name: 'createName', align: 'left', width: 120 },
			//{ display: '创建日期', name: 'createDate', align: 'left', width: 120 }
		],
		
		
		

		 allowHideColumn: false, 
		 colDraggable: true, 
		 rowDraggable: true,
         alternatingRow: false, 
		tree: { columnName: 'taskNm',single:true },
		//checkbox: true,
		rownumbers:true,
		delayLoad:true,		//初始化不加载数据
		pageSize:20,
		//url: './PrjtDef!selectWfRd.shtml?task.prjtNo='+$('#prjtNo', window.parent.document).val()+'&task.taskNo='+$('#taskId', window.parent.document).val()',
		url: './PrjtDef!selectWfRd.shtml?task.prjtNo='+$('#prjtNo', window.parent.document).val(),
		//url: './WfRd!list.shtml?wfRd.selectType='+$("#selectType").val()+'&wfRd.flowId='+$("#flowId").val(),
		usePager:true,
		width: '99.5%',
		height:'95%',
		//isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: showWfProcess,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	sea();
});

function showWfProcess(data){
	   // alert(data);
		if(data.wfNo){
		    if(window.parent) {
				if(window.parent.parent.f_open) {
		    	//alert("FF");
					window.parent.parent.f_open_WfProcess(
						"./WfRdView!wfRdView.shtml?wfRd.wfNo="+data.wfNo+"&isfromPrjtDef=1",
						"工作流"+data.wfNo,
						"./include/images/Alien Folder.png"
					);
				}
			}
		}
	
		//$.ligerDialog.open({title:'查看对话框：PrjtUsr', height: 560, width: 780,url: './WfRdView.shtml?wfRd.wfNo='+data.wfNo,showMax: true, showToggle: true, showMin: true, isResize: true});
}

$(function(){
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#createName").length > 0)
		$("#createName").ligerTextBox();
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerComboBox();
	if ($("#selectType").length > 0)
		$("#selectType").ligerComboBox();
	
	
	 $("#scheId").ligerComboBox({url: './PrjtDef!getPreSchCfgs.shtml?task.prjtNo='+$('#prjtNo', window.parent.document).val(), textField:"schNm", width:186, valueField:"schId",isMultiSelect: false,
	    	onSelected: function (newvalue)
	        {
	    		
	    		$("#maingrid").ligerGetGridManager().setOptions({
	    			parms: [
	    				{name: 'task.schId', value: newvalue}
	    			]
	    		});
	    		$("#maingrid").ligerGetGridManager().loadData();
	    		
	    		/**
	    		alert(newvalue);
	    		$.post("PrjtDef!selectWfRd.shtml",
						{'task.prjtNo':$('#prjtNo', window.parent.document).val(),'task.schId':newvalue},
						function(data) {
							$("#maingrid").ligerGetGridManager().setData(data);
						},
						"json"
				);
	    		**/
	        }
	  });
});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">
<br>
<!--
<div id="sform" style="margin:10px;height:30px;">
</div>
	<table>
	</table>
  -->
		<!--tr>
		    <tr>
		       <td height="24" width="90" >进度：</td>
				   <td>
				   <input type="text" id="scheId" name="wfRd.scheId" size="30" />
				 </td>
			 </tr>
			<td height="24" width="120" align="center">创建日期始：</td>
			<td><input type="text" id="startCreateDate" name="wfRd.startCreateDate"/></td>
			<td height="24" width="120" align="center">创建日期止：</td>
			<td><input type="text" id="endCreateDate" name="wfRd.endCreateDate"/></td>
			<td height="24" width="120" align="center">状态：</td>
			<td>
				<select id="status" name="wfRd.status">
					<option value="">请选择</option>
					<option value="0" <c:if test="${wfRd.status=='0'}">selected</c:if>>待处理</option>
					<option value="1" <c:if test="${wfRd.status=='1'}">selected</c:if>>办理中</option>
					<option value="2" <c:if test="${wfRd.status=='2'}">selected</c:if>>已完成</option>
					<option value="3" <c:if test="${wfRd.status=='3'}">selected</c:if>>已关闭</option>
					<option value="4" <c:if test="${wfRd.status=='4'}">selected</c:if>>已作废</option>
				</select>
			</td>
			<td height="24" width="120" align="center">办理种类</td>
			<td>
				<select id="selectType">
					<option value="0" <c:if test="${wfRd.selectType==0}">selected</c:if>>全部流程</option>
					<option value="1" <c:if test="${wfRd.selectType==1}">selected</c:if>>我的任务</option>
					<option value="2" <c:if test="${wfRd.selectType==2}">selected</c:if>>我发起的流程</option>
					<option value="3" <c:if test="${wfRd.selectType==3}">selected</c:if>>我参与的流程</option>
					<option value="4" <c:if test="${wfRd.selectType==4}">selected</c:if>>代理人的流程</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="120" align="center">创建人：</td><td><input type="text" id="createName" name="wfRd.createName"/></td>
			<td height="24" width="120" align="center">工作流标题：</td><td><input type="text" id="wfName" name="wfRd.wfName"/></td>
			<td height="24" width="120" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfRd.wfNo"/></td>
			<td height="24" width="120" align="center">流程种类</td>
			<td>
				<select id="flowId">
					<option value="">请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${wfRd.flowId==cfg.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr  -->

	

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>