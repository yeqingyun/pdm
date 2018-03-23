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

<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<script src="./include/js/gnwf/quesVer.js" type="text/javascript"></script>
<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#completedDate").ligerDateEditor({labelWidth: 200,format: "yyyy-MM-dd"});
	var data = [];
	data.push({'children':[{id: 1,status:1,text: '待解决'},{id: 2,status:9,text: '验证未通过'},
		{id: 3,status:10,text: '待验证'},{id: 4,status:11,text: '验证通过'}],
		id:9,status:100,text:'未关闭'});    
	data.push({id: 5,status:21,text: '已挂起'});
	data.push({id: 6,status:30,text: '已关闭'});
	data.push({id: 7,status:40,text: '转风险'});
    $("#treeStatus").ligerComboBox({
        width : 130, 
        selectBoxWidth: 160,
        selectBoxHeight: 180, valueField: 'text', treeLeafOnly: false,
        tree: {data: data,checkbox: false, onClick:function(data,target){
        	if($("#status").val() == data.data.status) {
        		$("#status").val("");
        	}else {
        		$("#status").val(data.data.status);
        	}
        }}
    });
});

//催办全部责任人
function myRush(userid,quesId,quesRespId){
	var str = '{"quesResp.usrId":'+ userid + ',"wfQues.quesId":' + quesId
		+ ',"quesResp.id":' + quesRespId + '}';
	$.post("WfQues!myRush.shtml",eval('(' + str + ')'),function(data){
		$.ligerDialog.success(data.msg);
		if(undefined != data.rushDate) {
			$("#rushDate" + quesRespId).text('上次催办时间：' + data.rushDate);
		}
	},"json");
}

//催办问题提出者
function RushForIdtfBy(userid,quesId,quesRespId){
	var str = '{"quesResp.usrId":'+ userid + ',"wfQues.quesId":' + quesId
		+ ',"quesResp.id":' + quesRespId + '}';
	$.post("WfQues!RushForIdtfBy.shtml",eval('(' + str + ')'),function(data){
		$.ligerDialog.success(data.msg);
		if(undefined != data.rushDate) {
			$("#rushDate" + quesRespId).text('上次催办时间：' + data.rushDate);
		}
	},"json");
}

var gridManager;
$(function() {
	if ($("#quesText").val()) {
		// var tmp = $("#quesText").val().replace(/<br\/>/g, "\n");
		// $("#quesText").val(tmp);
	}
	if ($("#result").val()) {
		var tmp = $("#result").val().replace(/<br\/>/g, "\n");
		$("#result").val(tmp);
	}
	var flag = false;
	if ($("#startCreateDate").length > 0) {
		flag = true;
	}
	$("#maingrid1").ligerGrid({

		columns : [ {
			display : '问题编号',
			name : 'quesId',
			align : 'left',
			width : 50,
		},{
			display : 'CQ编号',
			name : 'crDefectId',
			align : 'left',
			width : 50,
		}, {
			display : 'userId',
			name : 'userId',
			hide : true
		},{
			display : 'flowId',
			name : 'flowId',
			hide : true
		}, {
			display : 'roleDQAId',
			name : 'roleDQAId',
			hide : true
		}, {
			display : '项目进度ID',
			name : 'scheId',
			align : 'left',
			width : 120,
			hide : true
		}, {
			display : '工作任务ID',
			name : 'taskId',
			align : 'left',
			width : 120,
			hide : true
		},{
			display : '问题标题',
			name : 'title',
			align : 'left',
			width : 120
		}, {
			display : '问题描述',
			name : 'quesText',
			align : 'left',
			width : 180
		},{
			display : '问题等级',
			name : 'cateId',
			align : 'left',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
				if (row.cateId == 1) {
					innerHtml += "#9D2602";
					v = "S";
				} else if (row.cateId == 2) {
					innerHtml += "red";
					v = "B";
				} else if (row.cateId == 3) {
					innerHtml += "blue";
					v = "C";
				} else if (row.cateId == 4) {
					innerHtml += "green";
					v = "D";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '催办',
			name : 'quesText',
			align : 'center',
			width : 70,
			render: function (row){
				//var v = "<a href='#' onClick=\"wfTaskView('"+row.wfNo+"',"+row.taskId+","+row.taskStepId+")\">催办</a>";
				//rush(userid,quesId,quesRespId) var v = "<a href='#' onClick=\"rush('15441','2027','4694')\">催办责任人</a>";
				if(row.status == 1 || row.status == 9){
					var v = "<a href='#' onClick=\"myRush('99999','"+row.quesId+"','9999')\">催办责任人</a>";
				}else if (row.status == 10) {
					var v = "<a href='#' onClick=\"RushForIdtfBy('99999','"+row.quesId+"','9999')\">催办提出者</a>";
				}else{
					
				}
				return v;
			}
		},{
			display : '状态',
			name : 'status',
			align : 'left',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.status == 21) {
					innerHtml += "green";
					v = "已挂起";
				} else if (row.status == 30) {
					innerHtml += "green";
					v = "已关闭";
				}else if(row.status == 40){
					innerHtml += "red";
					v = "转风险";
				}else if(row.status == 1){
					innerHtml += "";
					v = "待解决";
				}else if(row.status == 9){
					innerHtml += "";
					v = "验证未通过";
				}else if(row.status == 10){
					innerHtml += "";
					v = "待验证";
				}else if(row.status == 11){
					innerHtml += "";
					v = "验证通过";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '责任人',
			name : 'usrName',
			align : 'left',
			width : 100
		}, {
			display : '要求完成日期',
			name : 'completedDate',
			align : 'left',
			width : 120	
		},{
			display : '措施',
			name : 'quesMeasures',
			align : 'left',
			width : 200,
		},{
			display : '验证人',
			name : 'createUsr',
			align : 'left',
			width : 70
		},{
			display : '录入人',
			name : 'createUsr',
			align : 'left',
			width : 120,
			hide : true
		},{
			display : '验证日期',
			name : 'idtfDate',
			align : 'left',
			width : 120
		}, {
			display : '项目名称',
			name : 'prjtNm',
			align : 'left',
			width : 100,
		}, {
			display : '阶段名段',
			name : 'schNm',
			align : 'left',
			width : 150,
			hide:true
		}, {
			display : '工作流名称',
			name : 'wfName',
			align : 'left',
			width : 250
		}],
		checkbox : true,
		rownumbers : true,
		pageSize : 20,
		url : './WfQues!list.shtml?1=1',
		usePager : true,
		delayLoad : true, // 初始化不加载数据
		width : '99.5%',
		height : '95%',
		isChecked : f_isChecked,
		onCheckRow : f_onCheckRow,
		onCheckRow : f_onCheckRow,
		onDblClickRow : f_onDblClickRow,
		onCheckAllRow : f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid1").ligerGetGridManager();
	sea();
})

function sea() {
	if (gridManager) {
		gridManager
				.setOptions({
					parms : [
							{
								name : 'wfQues.scheId',
								value : ($("#scheId").length > 0 && $("#scheId")
										.ligerComboBox().selectedValue) ? $(
										"#scheId").ligerComboBox().selectedValue
										: '' ? $("#scheId").ligerComboBox().selectedValue
												: ''
							},
							{
								name : 'wfQues.typId',
								value : ($("#typId").length > 0 && $("#typId")
										.ligerComboBox().selectedValue) ? $(
										"#typId").ligerComboBox().selectedValue
										: ''
							},
							{
								name : 'wfQues.status',
								value : ($("#status").length > 0) ? $("#status").val(): ''
							},
							{
								name : 'wfQues.wfNo',
								value : ($("#wfNo").length > 0) ? $("#wfNo")
										.val() : ''
							},
							{
								name : 'wfQues.prjtNo',
								value : ($("#prjtNo").length > 0) ? $("#prjtNo")
										.val()
										: ''
							},
							{
								name : 'wfQues.userId',
								value : ($("#userId").length > 0) ? $("#userId")
										.val()
										: ''
							},
							{
								name : 'wfQues.selectType',
								value : ($("#selectType").length > 0) ? $(
										"#selectType").val() : ''
							},
							{
								name : 'wfQues.usrName',
								value : ($("#usrName").length > 0) ? $(
										"#usrName").val() : ''
							},
							{
								name : 'wfQues.quesId',
								value : ($("#quesId").length > 0) ? $(
										"#quesId").val() : ''
							},
							{
								name : 'wfQues.title',
								value : ($("#questitle").length > 0) ? $(
										"#questitle").val() : ''
							},
							{
								name : 'wfQues.cateId',
								value : ($("#cateId").length > 0) ? $(
										"#cateId").val() : ''
							},
							{
								name : 'wfQues.completedDate',
								value : ($("#completedDate").length > 0) ? $(
										"#completedDate").val() : ''
							},
							{
								name : 'wfQues.createBy',
								value : ($("#createBy").length > 0) ? $(
										"#createBy").val() : ''
							},
							{
								name : 'wfQues.idtfDate',
								value : ($("#idtfDate").length > 0) ? $(
										"#idtfDate").val() : ''
							},{
								name : 'wfQues.crDefectId',
								value : ($("#crDefectId").length > 0) ? $(
										"#crDefectId").val() : ''
							},{
								name : 'wfQues.category',
								value : ($("#category").length > 0) ? $(
										"#category").val() : ''
							}
							]
				});
		gridManager.loadData();
	}
}
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
<input type="hidden" id="taskId" value="<c:out value="${taskId}"/>"/>
<input type="hidden" id="wfNo" value="<c:out value="${wfNo}"/>"/>
<input type="hidden" id="prjtNo" value="<c:out value="${prjtNo}"/>"/>
<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="left">问题编号：</td>
			<td height="24" width="90" align="left">
				<input id="quesId" name="wfQues.quesId" style="width:80px"/>
			</td>
			<td height="24" width="90" align="left">CQ编号：</td>
			<td height="24" width="90" align="left">
				<input id="crDefectId" name="wfQues.crDefectId" style="width:80px"/>
			</td>
			<td height="24" width="90" align="left">问题标题：</td>
			<td height="24" width="90" align="left">
				<input id="questitle" name="wfQues.title" style="width:80px"/>
			</td>
			<td height="24" width="90" align="center">状态：</td>
			<td >
				<input type="text" id="treeStatus"/>
				<input type="hidden" id="status" name="wfQues.status"/>
			</td>
			<td height="24" width="90" align="center">责任人：</td><td>
				<select id="usrName" name="wfQues.usrName" style="width:80px">
					<option value="">全部</option>
					<c:forEach items="${usrs}" var="usr">
						<option value="<c:out value="${usr.usrName}"/>"><c:out value="${usr.usrName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="left">验证人：</td>
			<td>
				<select id="createBy" name="wfQues.createBy" style="width:80px">
					<option value="">全部</option>
					<c:forEach items="${usrs}" var="usr">
						<option value="<c:out value="${usr.id}"/>"><c:out value="${usr.usrName}"/></option>
					</c:forEach>
				</select>
				
			</td>
			<td height="24" width="90" align="left">问题等级：</td>
			<td >
				<select id="cateId" name="wfQues.cateId" style="width:70px">
					<option value="">全部</option>
					<!-- <option value="1">S</option> -->
					<option value="2">B</option>
					<option value="3">C</option>
					<option value="4">D</option>
				</select>
			</td>
			<td height="24" width="100" align="left">要求完成日期：</td>
			<td><input type="text" id="completedDate" name="wfQues.completedDate"/></td>
			<td height="24" width="90" align="left">问题类别：</td>
			<td >
				<select id="category" name="wfQues.category" style="width:135px">
					<option value="0">全部</option>
					<option value="1">CQ问题</option>
					<option value="2">PDM问题</option>
				</select>
			</td>
	</tr>
	<tr>
		<td height="24" width="90" align="center" colspan="7">
			 <input id="upBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
			&nbsp;&nbsp; <input id="upBtn" type="button" value=" 导出" class="wfbigbtn2"  onclick="exp();" />
			&nbsp;&nbsp; <input id="upBtn" type="button" value=" 删除" class="wfbigbtn2"  onclick="del();" />
		</td>
	 </tr>
	</table>
</div>
<div id="maingrid1" style="margin-top:1px;margin-left:1px;"></div>
</form>
</body>
</html>