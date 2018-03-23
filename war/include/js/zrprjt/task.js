function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['schId']);
		else
			removeCheckedCustomer(this.records[rowid]['schId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(schId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == schId) return i;
	}
	return -1;
}
function addCheckedCustomer(schId) {
	if(findCheckedCustomer(schId) == -1)
		checkedCustomer.push(schId);
}
function removeCheckedCustomer(schId){
	var i = findCheckedCustomer(schId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.schId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.schId);
	else
		removeCheckedCustomer(data.schId);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#schId").length > 0)
		$("#schId").val("");
	if ($("#tasker").length > 0)
		$("#tasker").val("");
	if ($("#sender").length > 0)
		$("#sender").val("");
	if ($("#schNo").length > 0)
		$("#schNo").val("");
	if ($("#leve").length > 0)
		$("#leve").val("");
	if ($("#grade").length > 0)
		$("#grade").val("");
	if ($("#perce").length > 0)
		$("#perce").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#startPlanStaDate").length > 0)
		$("#startPlanStaDate").val("");
	if ($("#endPlanStaDate").length > 0)
		$("#endPlanStaDate").val("");
	if ($("#planStaDate").length > 0)
		$("#planStaDate").val("");
	if ($("#startPlanOveDate").length > 0)
		$("#startPlanOveDate").val("");
	if ($("#endPlanOveDate").length > 0)
		$("#endPlanOveDate").val("");
	if ($("#planOveDate").length > 0)
		$("#planOveDate").val("");
	if ($("#startStaDate").length > 0)
		$("#startStaDate").val("");
	if ($("#endStaDate").length > 0)
		$("#endStaDate").val("");
	if ($("#staDate").length > 0)
		$("#staDate").val("");
	if ($("#startOveDate").length > 0)
		$("#startOveDate").val("");
	if ($("#endOveDate").length > 0)
		$("#endOveDate").val("");
	if ($("#oveDate").length > 0)
		$("#oveDate").val("");
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").val("");
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").val("");
	if ($("#createDate").length > 0)
		$("#createDate").val("");
	if ($("#startLastDate").length > 0)
		$("#startLastDate").val("");
	if ($("#endLastDate").length > 0)
		$("#endLastDate").val("");
	if ($("#lastDate").length > 0)
		$("#lastDate").val("");
	if ($("#taskNo").length > 0)
		$("#taskNo").val("");
	if ($("#prjtNo").length > 0)
		$("#prjtNo").val("");
	if ($("#parent").length > 0)
		$("#parent").val("");
	if ($("#taskNm").length > 0)
		$("#taskNm").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：Task', height: 560, width: 780,url: './Task!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：Task', height: 560, width: 780,url: './Task!edit.shtml?task.schId='+data.schId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：Task', height: 560, width: 780,url: './Task!view.shtml?task.schId='+data.schId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'task.schId', value: $("#schId").val()},
			{name: 'task.tasker', value: $("#tasker").val()},
			{name: 'task.sender', value: $("#sender").val()},
			{name: 'task.schNo', value: $("#schNo").val()},
			{name: 'task.leve', value: $("#leve").val()},
			{name: 'task.grade', value: $("#grade").val()},
			{name: 'task.perce', value: $("#perce").val()},
			{name: 'task.status', value: $("#status").val()},
			{name: 'task.createBy', value: $("#createBy").val()},
			{name: 'task.lastUpd', value: $("#lastUpd").val()},
			{name: 'task.startPlanStaDate', value: $("#startPlanStaDate").val()},
			{name: 'task.endPlanStaDate', value: $("#endPlanStaDate").val()},
			{name: 'task.startPlanOveDate', value: $("#startPlanOveDate").val()},
			{name: 'task.endPlanOveDate', value: $("#endPlanOveDate").val()},
			{name: 'task.startStaDate', value: $("#startStaDate").val()},
			{name: 'task.endStaDate', value: $("#endStaDate").val()},
			{name: 'task.startOveDate', value: $("#startOveDate").val()},
			{name: 'task.endOveDate', value: $("#endOveDate").val()},
			{name: 'task.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'task.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'task.startLastDate', value: $("#startLastDate").val()},
			{name: 'task.endLastDate', value: $("#endLastDate").val()},
			{name: 'task.taskNo', value: $("#taskNo").val()},
			{name: 'task.prjtNo', value: $("#prjtNo").val()},
			{name: 'task.parent', value: $("#parent").val()},
			{name: 'task.taskNm', value: $("#taskNm").val()},
			{name: 'task.remark', value: $("#remark").val()}

		]
	});
	gridManager.loadData();
}
function sav() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				str += '"task.schId":"'+$("#schId").val()+'",';
				str += '"task.tasker":"'+$("#tasker").val()+'",';
				str += '"task.sender":"'+$("#sender").val()+'",';
				str += '"task.schNo":"'+$("#schNo").val()+'",';
				str += '"task.leve":"'+$("#leve").val()+'",';
				str += '"task.grade":"'+$("#grade").val()+'",';
				str += '"task.perce":"'+$("#perce").val()+'",';
				str += '"task.status":"'+$("#status").val()+'",';
			/**	str += '"task.createBy":"'+$("#createBy").val()+'",';
				str += '"task.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"task.planStaDate":"'+$("#planStaDate").val()+'",';
				str += '"task.planOveDate":"'+$("#planOveDate").val()+'",';
				str += '"task.staDate":"'+$("#staDate").val()+'",';
				str += '"task.oveDate":"'+$("#oveDate").val()+'",';
				str += '"task.createDate":"'+$("#createDate").val()+'",';
				str += '"task.lastDate":"'+$("#lastDate").val()+'",';
				**/
				str += '"task.taskNo":"'+$("#taskNo").val()+'",';
				str += '"task.prjtNo":"'+$("#prjtNo").val()+'",';
				str += '"task.parent":"'+$("#parent").val()+'",';
				str += '"task.taskNm":"'+$("#taskNm").val()+'",';
				str += '"task.remark":"'+$("#remark").val()+'"';
				str += '}';

				$.post("Task!sav.shtml",
					JSON.parse(str),
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function voi() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择作废的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要作废记录？', 
		function (type) { 
			if(type) {
				$.post("Task!voi.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function del() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择删除的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要删除记录？', 
		function (type) { 
			if(type) {
				$.post("Task!del.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function sub() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择提交的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要提交记录？', 
		function (type) { 
			if(type) {
				$.post("Task!sub.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function can() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择撤消的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要撤消记录？', 
		function (type) { 
			if(type) {
				$.post("Task!can.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function chk() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择审核的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要审核记录？', 
		function (type) { 
			if(type) {
				$.post("Task!chk.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function rev() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择复核的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要复核记录？', 
		function (type) { 
			if(type) {
				$.post("Task!rev.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function con() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择确认的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要确认记录？', 
		function (type) { 
			if(type) {
				$.post("Task!con.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function sta() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择开始的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要开始记录？', 
		function (type) { 
			if(type) {
				$.post("Task!sta.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function stp() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择中止的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要中止记录？', 
		function (type) { 
			if(type) {
				$.post("Task!stp.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function ove() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择完成的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要完成记录？', 
		function (type) { 
			if(type) {
				$.post("Task!ove.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function iss() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择发布的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要发布记录？', 
		function (type) { 
			if(type) {
				$.post("Task!iss.shtml",
					{'choices': checkedCustomer.join(',')},
					function(data) {
						$.ligerDialog.success(data);
					},
					"text"
				);
			}
		}
	);
}
function exp() {
	$.ligerDialog.confirm('确定要导出记录？', 
		function (type) { 
			if(type) {
				$("#form1").attr("action","Task!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './Task!imp.shtml'});
			}
		}
	);
}
function prn() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择打印的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要打印记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'打印对话框：Task', height: 560, width: 780,url: './Task!prn.shtml?choices='+checkedCustomer.join(',')});
			}
		}
	);
}
function dow() {
	var data1 = gridManager.getSelected();
	if(!data1) {
		$.ligerDialog.warn('请选择下载的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要下载记录？', 
		function (type) { 
			if(type) {
				$("#form1").attr("action","Task!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}