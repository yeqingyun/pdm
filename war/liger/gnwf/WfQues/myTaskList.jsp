<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/js/gnwf/quesVer.js" type="text/javascript"></script>
<script type="text/javascript">
$(function (){
	if($("#selectType").val() == 1) {
		$("#batchRejectBtn").hide();
		$("#batchApproveBtn").hide();
		$("#updateQuesUsersBtn").show();
	}
   	$("#layout1").ligerLayout({
   		topHeight:25,
   		minLeftWidth:80,
   		minRightWidth:80,
   		leftWidth: 155
   	});
   	
   	$("#tree1").ligerTree({ 
   	     url:'./PrjtDef!loadPrjtTree.shtml', 
         idFieldName :'prjtNo',
         textFieldName :'prjtNm',
         iconFieldName :'iconUri',
         checkbox :false,
         onSelect: function (node, e){
         	var prjtNo = node.data.prjtNo;
         	$("#prjtNo").val(prjtNo);
 	        sea();
         }
   	});
});
var gridManager;
$(function () {
	$("#delButton").hide();
	$("#editQuesBtn").hide();
	$("#maingrid").ligerGrid({
		columns : [ {
			display : '最终状态',
			name : 'status',
			align : 'Central',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.status == 21) {
					innerHtml += "green";
					v = "OPEN";//已挂起
				} else if (row.status == 30) {
					innerHtml += "red";
					v = "CLOSE";
				}else if(row.status == 40){
					innerHtml += "blue";
					v = "Risk";
				}else if(row.status == 1){
					innerHtml += "green";
					v = "OPEN";//待解决
				}else if(row.status == 9){
					innerHtml += "green";
					v = "OPEN";//验证未通过
				}else if(row.status == 10){
					innerHtml += "green";
					v = "OPEN";//待验证
				}else if(row.status == 11){
					innerHtml += "green";
					v = "OPEN";//验证通过
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '等级',
			name : 'cateId',
			align : 'Central',
			width : 45,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
				if  (row.cateId == 1) {
					innerHtml += "#9D2602";
					v = "S";
				} else if  (row.cateId == 2) {
					innerHtml += "red";
					v = "A";
				} else if (row.cateId == 3) {
					innerHtml += "blue";
					v = "B";
				} else if (row.cateId == 4) {
					innerHtml += "green";
					v = "C";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},{
			display : '阶段',
			name : 'schNm',
			align : 'Central',
			width : 100,
		},{
			display : '测试项',
			name : 'testItemName',
			align : 'Central',
			width : 120
		},
		{
			display : '问题描述',
			name : 'quesText',
			align : 'left',
			width : 180
		}, {
			display : '不良比例',
			name : 'fractionDefective',
			align : 'left',
			width : 55,
			
		},  {
			display : '原因分析',
			name : 'quesAnalysis',
			align : 'left',
			width : 180
		},{
			display : '改善措施',
			name : 'quesMeasures',
			align : 'left',
			width : 200,
		},{
			display : '验证说明',
			name : 'IdtfRes',
			align : 'left',
			width : 200,
		},{
			display : '责任人',
			name : 'usrName',
			align : 'left',
			width : 100
		},{
			display : '责任部门',
			name : 'deptNm',
			align : 'left',
			width : 100
		}, {
			display : '提交时间',
			name : 'createDate',
			align : 'left',
			width : 90
		},{
			display : '解决时长',
			name : '',
			align : 'left',
			width : 50,
		},{
			display : '分类',
			name : 'quesTypeID',
			align : 'Central',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
				/* if (row.quesTypeID == 1) {
					innerHtml += "black";
					v = "基带";
				} else */ if (row.quesTypeID == 2) {
					innerHtml += "black";
					v = "射频";
				} /* else if (row.quesTypeID == 3) {
					innerHtml += "black";
					v = "音频";
				} */ else if (row.quesTypeID == 4) {
					innerHtml += "black";
					v = "结构";
				} else if (row.quesTypeID == 5) {
					innerHtml += "black";
					v = "外观工艺";
				} else if (row.quesTypeID == 6) {
					innerHtml += "black";
					v = "软件";
				}else if (row.quesTypeID == 7) {
					innerHtml += "black";
					v = "贴片工艺";
				}else if (row.quesTypeID == 8) {
					innerHtml += "black";
					v = "组装工艺";
				}else if (row.quesTypeID == 9) {
					innerHtml += "black";
					v = "物料";
				}else if (row.quesTypeID == 10) {
					innerHtml += "black";
					v = "附配件测试";
				}else if (row.quesTypeID == 11) {
					innerHtml += "black";
					v = "硬件";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},{
			display : '状态备注',
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
		},{
			display : '问题编号',
			name : 'quesId',
			align : 'left',
			width : 100,
		} ,{
			display : '问题标题',
			name : 'title',
			align : 'left',
			width : 120
		} ,{
			display : '验证人',
			name : 'createUsr',
			align : 'left',
			width : 70
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
		}
		/*, {
			display : '要求完成日期',
			name : 'completedDate',
			align : 'left',
			width : 120,
			hide : true
		} , {
			display : 'CQ编号',
			name : 'crDefectId',
			align : 'left',
			width : 50,
			hide : true
		} ,{
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
			display : '录入人',
			name : 'createUsr',
			align : 'left',
			width : 120,
			hide : true
		}, {
			display : '阶段名段',
			name : 'schNm',
			align : 'left',
			width : 150,
			hide:true
		} , {
			display : '工作流名称',
			name : 'wfName',
			align : 'left',
			width : 250,
			hide : true
		}**/
		],
		checkbox : true,
		rownumbers : true,
		pageSize : 20,
		url : './WfQues!mylist.shtml?1=1'
		,
		usePager : true,
		delayLoad : true, // 初始化不加载数据
		width : '99.5%',
		height : '98%',
		
		isChecked : f_isChecked,
		onCheckRow : f_onCheckRow,
		onCheckRow : f_onCheckRow,
		onDblClickRow : f_onDblClickRow,
		onCheckAllRow : f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	sea();
});
$(function(){
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:'typNm', valueField:'typId', isMultiSelect: false,
		onSelected: function (newvalue){
			$.post("SchCfg!getPreSchCfgs.shtml",{'schCfg.typId':newvalue},function(data) {
				$("#scheId").ligerGetComboBoxManager().setData(data);
			},"json");
		}});
	$("#scheId").ligerComboBox({textField:'schNm', valueField:'schId', isMultiSelect: false});
});

function del() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择删除的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要删除记录？', function(type) {
		if (type) {
			$.post("WfQues!del.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}
//转交
var dialog33;
function bathUpdateQuesUsrs(){
	if($("#prjtNo").val() == '') {
		$.ligerDialog.warn("请先选择项目");
		return;
	}
	if(!verification('bathUpdateQuesUsrs')) {
		return;
	}
	$("#updateQuesIds").val(checkedCustomer.join(','));
	dialog33 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#prjtNo").val()+'&isFromWf=0'});
}
function setUsrData(usrId, usrName) {
	var str = '{';
	str += '"updateQuesIds":"' + $("#updateQuesIds").val() + '",';
	str += '"wfQues.usrName":"' + usrName + '",';
	str += '"responsibleUID":"' + usrId + '"';
	str += '}';
	$.post("WfQues!bathUpdateResponers.shtml", eval('(' + str + ')'),
			function(data) {
				$.ligerDialog.success(data, "提示", function() {
					sea();
				});
			}, "text");
}

function selecttype(selectType){
	checkedCustomer = [];
	$("#selectType").val(selectType);
	if(selectType == 3){
		$("#delButton").show();
		$("#editQuesBtn").show();
	}else {
		$("#delButton").hide();
		$("#editQuesBtn").hide();
	}
	if(selectType == 1) {
		$("#importBtn").show();
		$("#batchRejectBtn").hide();
		$("#batchApproveBtn").hide();
		$("#updateQuesUsersBtn").show();
	}else if(selectType == 2 || selectType == 3) {
		$("#batchRejectBtn").hide();
		$("#batchApproveBtn").hide();
		$("#updateQuesUsersBtn").hide();
		$("#importBtn").hide();
	}else if(selectType = 4) {
		$("#batchRejectBtn").show();
		$("#batchApproveBtn").show();
		$("#importBtn").hide();
		$("#updateQuesUsersBtn").hide();
	}
	sea();
}

function f_onCheckAllRow(checked) {
	for ( var rowid in this.records) {
		if (checked)
			addCheckedCustomer(this.records[rowid]['quesId']);
		else
			removeCheckedCustomer(this.records[rowid]['quesId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(quesId) {
	for (var i = 0; i < checkedCustomer.length; i++) {
		if (checkedCustomer[i] == quesId)
			return i;
	}
	return -1;
}
function addCheckedCustomer(quesId) {
	if (findCheckedCustomer(quesId) == -1)
		checkedCustomer.push(quesId);
}
function removeCheckedCustomer(quesId) {
	var i = findCheckedCustomer(quesId);
	if (i == -1)
		return;
	checkedCustomer.splice(i, 1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.quesId) == -1)
		return false;
	return true;
}

var checkedWfNo;

function f_onCheckRow(checked, data) {
	if (checked) {
		addCheckedCustomer(data.quesId);
		checkedWfNo = data.wfNo;
	} else
		removeCheckedCustomer(data.quesId);
}
function f_onDblClickRow(data) {
	if(data.status == 40) {
		$.ligerDialog.warn('已转风险评估的问题，不能在此操作，请转到风险管理进行处理');
		return;
	}
	if(data.crDefectId == null) {
		rep(data);
	}else {
		repCQ(data);
	}
}


//批量验证有效
function batchApprove() {
	if(!verification('approve')) {
		return;
	}
	$.ligerDialog.confirm('确定有效验证吗？', function(type) {
		if (type) {
			$.post("QuesResp!batchApprove.shtml", {
				'quesRespIds' : getQuesRespIds().join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}


function getQuesRespIds() {
	var quesRespIds = [];
	var selectRow = gridManager.getSelectedRows();
	
	for(var i=0;i<selectRow.length;i++){
		quesRespIds.push(selectRow[i].quesRespId);
	}
	//alert(quesRespIds);
	return quesRespIds;
	
}
function gotoRisk() {
	if (!verification('gotoRisk')) {
		return;
	}
	
	window.parent.f_open('./WfQues!managerQuesGoRiskTask.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId);
	//window.parent.f_open('./WfQues!editQuesUI.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId);
	/* DialogMgr.create('编辑对话框：WfQues', './WfQues!editQuesUI.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId); */
	/* $.post("WfQues!gotoRisk.shtml", {
		'choices' : checkedCustomer.join(',')
	}, function(data) {
		$.ligerDialog.success(data);
		sea();
	}, "text"); */
}

function rep(data) {
	if(window.parent) {
		if(window.parent.f_open) {
			var st = $("#selectType").val();
			window.parent.f_open(
			    "./WfQues!managerQues.shtml?wfQues.quesId="+data.quesId,
				"问题管理："+data.quesId,
				"./include/images/Alien Folder.png"
			);
		}
	}
}
function repCQ(data) {
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
			    "./CQDefect!managerDefect.shtml?defect.id="+data.crDefectId,"CQ缺陷视图："+data.crDefectId,"./include/images/Alien Folder.png"
			);
		}
	}
}
var DialogMgr = (function() {
	var dialog;
	return {
		create : function(title, url) {
			dialog = $.ligerDialog.open({
				title : title,
				height : 560,
				width : 810,
				url : url,
				showMax : true,
				showToggle : true,
				showMin : true,
				isResize : true
			})
		},
		close : function() {
			if (dialog) {
				dialog.close();
			}
		}
	};
})();

function sea() {
	if (gridManager) {
		gridManager
				.setOptions({
					parms : [
							{
								name : 'wfQues.selectType',
								value : ($("#selectType").length > 0) ? $(
										"#selectType").val() : ''
							},
							{
								name : 'wfQues.prjtNo',
								value : ($("#prjtNo").length > 0) ? $("#prjtNo")
										.val()
										: ''
							}
							
							]
				});
		gridManager.loadData();
	}
}
</script>
</head>
<body style="padding:10px;">
<div id="layout1" style="overflow-x: hidden;">
	<div position="left" id="left" title="项目列表" style="height: 95%;width: 98%;overflow:auto;" class="l-scroll">
		<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
	<div id="toolbar"></div>
	<div id="sform" style="margin:10px;height:20px;">
		<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
		<input type="hidden" id="taskCount"/>
		<input type="hidden" id="prjtNo" name="wfQues.prjtNo" value="<c:out value="${def.prjtNo}"/>"/>
		<input type="hidden" id="wfNo" name="wfQues.wfNo" value="<c:out value="${wfQues.wfNo}"/>"/>
		<input type="hidden" id="status" name="wfQues.status" />
		<input type="hidden" id="startCreateDate" name="wfQues.startCreateDate"/>
		<input type="hidden" id="endCreateDate" name="wfQues.endCreateDate"/>
		<input type="hidden" id="selectType" name="wfQues.selectType" value="<c:out value="${wfQues.selectType}"/>"/>
		<input id="userName" type="hidden" value="<c:out value="${sessionScope.SYUSR.usrName}"/>" />
		
		<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
		<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
		<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
		<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
		<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
	    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
		<input type="hidden" id="updateQuesIds" name="updateQuesIds" size="30"/>
		<input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
		<table>
			<tr>
			<td>
		&nbsp;&nbsp;<input type="radio" name="check" onclick="selecttype('1');" checked="checked"/><font color="blue">待我解决问题</font>
		&nbsp;&nbsp;<input type="radio" name="check" onclick="selecttype('4');"/><font color="blue">待我验证的问题</font>
		&nbsp;&nbsp;<input type="radio" name="check" onclick="selecttype('2');"/><font color="blue">我已解决问题</font>
		&nbsp;&nbsp;<input type="radio" name="check" onclick="selecttype('3');"/><font color="blue">我提出的问题</font>
		
		
		&nbsp;&nbsp;<input id="delButton" type="button" value=" 删除" class="wfbigbtn2"  onclick="del();" />
		&nbsp;&nbsp;<input id="editQuesBtn" type="button" value=" 编辑" class="wfbigbtn2"  onclick="editQues();"/>
		<!-- 
		&nbsp;&nbsp;<input id="batchSaveResultBtn" type="button" value=" 批量给出解决措施" class="wfbigbtn2"  onclick="batchSaveResult();" />
		 &nbsp;&nbsp;<input id="batchHangupBtn" type="button" value=" 挂起" class="wfbigbtn2"  onclick="batchHangup();" />
		 -->
		&nbsp;&nbsp;<input id="batchRejectBtn" type="button" value=" 验证有效" class="wfbigbtn2"  onclick="batchApprove();" />
		&nbsp;&nbsp;<input id="batchApproveBtn" type="button" value=" 验证无效" class="wfbigbtn2"  onclick="batchReject();" />
		
		&nbsp;&nbsp;<input id="importBtn" type="button" value=" 导入解决措施" class="wfbigbtn2"  onclick="impSovleQues();" />
		&nbsp;&nbsp;<input id="exportBtn" type="button" value=" 导出" class="wfbigbtn2"  onclick="exp();" />
		&nbsp;&nbsp;<input id="updateQuesUsersBtn" type="button" style="display: none" value=" 转交" class="wfbigbtn2"  onclick="bathUpdateQuesUsrs();"  />
		&nbsp;&nbsp;<input id="gotoRiskBtn" type="button" value=" 转风险" class="wfbigbtn2"  onclick="gotoRisk();"  />
			</td>
			</tr>
		</table>
	</div>
	
	<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
			
</form>
</div>
</div>
</body>
</html>