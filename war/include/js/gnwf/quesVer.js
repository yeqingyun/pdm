//导出问题
function exp() {
	var row = gridManager.getRow(0);
	if(row == null) {
		$.ligerDialog.warn('没有记录可导出');
		return;
	}
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length == 0) {
		var param = "";
		if($("#selectType").val() != undefined && $("#selectType").val() != '') {
			param += "wfQues.selectType=" + $("#selectType").val();
		}else {
			if($("#quesId").length > 0)param += "wfQues.quesId=" + $("#quesId").val();
			if($("#questitle").length > 0)param += "&title=" + $("#questitle").val();
			if($("#status").length > 0)param += "&wfQues.status=" + $("#status").val();
			if($("#scheId").length > 0)param += "&wfQues.scheId=" + $("#scheId").val();
			if($("#createBy").length > 0)param += "&wfQues.createBy=" + $("#createBy").val();
			if($("#usrName").length > 0)param += "&usrName=" + $("#usrName").val();
			if($("#completedDate").length > 0)param += "&wfQues.completedDate=" + $("#completedDate").val();
			if($("#cateId").length > 0)param += "&wfQues.cateId=" + $("#cateId").val();
			if($("#wfNo").length > 0)param += "&wfQues.wfNo=" + $("#wfNo").val();
		}
		if($("#prjtNo").length > 0)param += "&wfQues.prjtNo=" + $("#prjtNo").val();
		$.ligerDialog.confirm('确定要导出记录？', function(type) {
			if(type) {
				window.location = "WfQues!export.shtml?" + param;
			}
		});
	}else {
		$.ligerDialog.confirm('确定要导出记录？', function(type) {
			if (type) {
				var uri = "WfQues!export.shtml?choices=" + checkedCustomer.join(',');
				if($("#selectType").val() != undefined && $("#selectType").val() != '') {
					uri = uri + "&wfQues.selectType=" + $("#selectType").val();
				}
				window.location =  uri;
			}
		});
	}
}

//导入新问题
function importNewQues() {
	$.ligerDialog.confirm('确定要导入新记录？', function(type) {
		if (type) {
				var url ='./WfQues!importNewQues.shtml' + '?wfQues.prjtNo='+ document.getElementById("prjtNo").value+'&wfQues.prjtNm='+document.getElementById("pNm").innerHTML;
				
				var dialog = $.ligerDialog.open({ url: url, height: 300,width: 600, 
					buttons: [ { text: '完成', onclick: function (item, dialog) { relodeQuesManager(); dialog.close(); } } ] });
		}
	});
}
//提交任务成功后刷新问题管理
function relodeQuesManager() {
		if(dialog.parent.quesManagerWinow){
			dialog.parent.quesManagerWinow.frame.gridManager.loadData();
		}
}

//导入问题
function impSovleQues(){
	$.ligerDialog.confirm('确定要导入记录？', function(type) {
		if(type) {
			var url ='./WfQues!impSovleQues.shtml';
			if($("#fromQuesManger").val() == 'fromQuesManger') {
				url = url + "?fromQuesManger=" + $("#fromQuesManger").val();
			}
			var dialog = $.ligerDialog.open({ url: url, height: 300,width: 600, 
				buttons: [ { text: '完成', onclick: function (item, dialog) { 
					dialog.close();
					sea();
				} } ] });
		}
	});
}



//DQA批量给出解决措施
function impSovleQuesByDQA(){
	$.ligerDialog.confirm('确定要导入记录？', function(type) {
		if(type) {
			var url ='./WfQues!impSovleQuesByDQA.shtml';
			if($("#fromQuesManger").val() == 'fromQuesManger') {
				url = url + "?fromQuesManger=" + $("#fromQuesManger").val();
			}
			var dialog = $.ligerDialog.open({ url: url, height: 300,width: 600, 
				buttons: [ { text: '完成', onclick: function (item, dialog) { 
					dialog.close();
					sea();
				} } ] });
		}
	});
}


function batchSaveResult(){
	if(!verification('measures')) {
		return;
	}
	$.ligerDialog.confirm('确定要批量提交解决措施？', function(type) {
		if (type) {
			$.post("QuesResp!batchSaveResult.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}
function bSaveResult(choices) {
	
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
//批量验证无效
function batchReject(){
	if(!verification('reject')) {
		return;
	}
	$.ligerDialog.confirm('确定无效验证吗？', function(type) {
		if (type) {
			$.post("QuesResp!batchReject.shtml", {
				'quesRespIds' : getQuesRespIds().join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}
//批量挂起
function batchHangup() {
	if(!verification('hangup')) {
		return;
	}
	$.ligerDialog.confirm('确定挂起问题吗？', function(type) {
		if (type) {
			$.post("QuesResp!batchHangup.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}

function editQues() {
	if(!verification('editQues')) {
		return;
	}
	window.parent.f_open('./WfQues!editQuesUI.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId ,'编辑对话框：WfQues');
	//DialogMgr.create('编辑对话框：WfQues', './WfQues!editQuesUI.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId);
	//dialog33 = $.ligerDialog.open({title:'编辑对话框：WfQues', height: 450, width: 850,url: './WfQues!editQuesUI.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId});
}


function verificationByClose(type) {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择相关问题记录。');
		return false;
	}
	for(var i=0;i<selectRow.length;i++) {
		if(type == 'closeQues') {
			if(selectRow[i].status == 30 && selectRow[i].crDefectId == null) {
				$.ligerDialog.warn('该问题已关闭！');
				return false;
		}
	}
	return true;
}
}


function verification(type) {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择相关问题记录。');
		return false;
	}
	for(var i=0;i<selectRow.length;i++) {
		if(type == 'openQues') {
			if(selectRow[i].status != 30) {
				$.ligerDialog.warn('只能开启已关闭的问题！');
				return false;
			}
		}else if(selectRow[i].status == 40 || selectRow[i].status == 30) {
			 
			$.ligerDialog.warn('已转风险评估的问题或已关闭的问题不能执行此操作！');
			return false;
		}
		if(type == 'measures') {
			if(selectRow[i].status == 21) {
				$.ligerDialog.warn('已挂起的问题不能再给解决措施了！');
				return false;
			}
			if(selectRow[i].usrName.indexOf($("#userName").val()) == -1) {
				$.ligerDialog.warn('提交解决措施的问题责任人必须是当前用户！');
				return false;
			}
		}
		/*else if(type == 'approve' || type == 'reject') {
			alert(selectRow[i].idtfBy + "\\" + $("#sys_usrid").val());
			if(selectRow[i].idtfBy != $("#sys_usrid").val()) {
				$.ligerDialog.warn('当前用户必须是验证人才能执行此操作！');
				return false;
			}
		}*/
		else if(type == 'hangup') {
			if(selectRow[i].status == 21) {
				$.ligerDialog.warn('已挂起的问题不能执行此操作！');
				return false;
			}
		}else if(type == 'closeQues') {
			if(selectRow[i].status == 30 && selectRow[i].crDefectId == null) {
				$.ligerDialog.warn('该问题已关闭！');
				return false;
		}else if(type == 'gotoRisk') {
			if(selectRow[i].status != 1 && selectRow[i].crDefectId == null) {
				$.ligerDialog.warn('只有待解决的问题才能转风险！');
				return false;
				}
		}else if(type == 'bathUpdateQuesUsrs') {
			if(selectRow[i].status != 1) {
				$.ligerDialog.warn('只有待解决的问题才能转交责任人！');
				return false;
			}
		}else if(type == 'editQues') {
			if(selectRow.length > 1) {
				$.ligerDialog.warn('只能编辑一条记录！');
				return false;
			}
			if(selectRow[i].status != 1) {
				$.ligerDialog.warn('只能编辑待解决的问题！');
				return false;
			}
		}
	}
	return true;
}

//关闭问题
function bathCloseQues() {
	if(!verification('closeQues')) {
		return;
	}
	$.post("WfQues!bathCloseQues.shtml", {
		'choices' : checkedCustomer.join(',')
	}, function(data) {
		$.ligerDialog.success(data);
		sea();
	}, "text");
}



function getQuesRespIds() {
	var quesRespIds = [];
	var selectRow = gridManager.getSelectedRows();
	alert(selectRow);
	for(var i=0;i<selectRow.length;i++){
		quesRespIds.push(selectRow[i].quesRespId);
	}
	return quesRespIds;
}

var dialog22;
function bathUpdateQuesUsrs(){
	if($("#prjtNo").val() == '') {
		$.ligerDialog.warn("请先选择项目");
		return;
	}
	if(!verification('bathUpdateQuesUsrs')) {
		return;
	}
	$("#updateQuesIds").val(checkedCustomer.join(','));
	dialog22 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#prjtNo").val()+'&isFromWf=0'});
}

	
}
