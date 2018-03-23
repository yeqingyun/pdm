function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['msgId']);
		else
			removeCheckedCustomer(this.records[rowid]['msgId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(msgId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == msgId) return i;
	}
	return -1;
}
function addCheckedCustomer(msgId) {
	if(findCheckedCustomer(msgId) == -1)
		checkedCustomer.push(msgId);
}
function removeCheckedCustomer(msgId){
	var i = findCheckedCustomer(msgId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.msgId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.msgId);
	else
		removeCheckedCustomer(data.msgId);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#msgId").length > 0)
		$("#msgId").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
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
	if ($("#reply").length > 0)
		$("#reply").val("");
	if ($("#taskNo").length > 0)
		$("#taskNo").val("");
	if ($("#prjtNo").length > 0)
		$("#prjtNo").val("");
	if ($("#wfNo").length > 0)
		$("#wfNo").val("");
	if ($("#title").length > 0)
		$("#title").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：TaskMsg', height: 560, width: 780,url: './TaskMsg!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：TaskMsg', height: 560, width: 780,url: './TaskMsg!edit.shtml?taskMsg.msgId='+data.msgId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：TaskMsg', height: 560, width: 780,url: './TaskMsg!view.shtml?taskMsg.msgId='+data.msgId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'taskMsg.msgId', value: $("#msgId").val()},
			{name: 'taskMsg.status', value: $("#status").val()},
			{name: 'taskMsg.createBy', value: $("#createBy").val()},
			{name: 'taskMsg.lastUpd', value: $("#lastUpd").val()},
			{name: 'taskMsg.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'taskMsg.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'taskMsg.startLastDate', value: $("#startLastDate").val()},
			{name: 'taskMsg.endLastDate', value: $("#endLastDate").val()},
			{name: 'taskMsg.reply', value: $("#reply").val()},
			{name: 'taskMsg.taskNo', value: $("#taskNo").val()},
			{name: 'taskMsg.prjtNo', value: $("#prjtNo").val()},
			{name: 'taskMsg.wfNo', value: $("#wfNo").val()},
			{name: 'taskMsg.title', value: $("#title").val()}

		]
	});
	gridManager.loadData();
}
function sav() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
			/**	str += '"taskMsg.msgId":"'+$("#msgId").val()+'",';
				str += '"taskMsg.createBy":"'+$("#createBy").val()+'",';
				str += '"taskMsg.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"taskMsg.createDate":"'+$("#createDate").val()+'",';
				str += '"taskMsg.lastDate":"'+$("#lastDate").val()+'",';
				str += '"taskMsg.reply":"'+$("#reply").val()+'",';
				**/
				str += '"taskMsg.status":"'+$("#status").val()+'",';
				str += '"taskMsg.taskNo":"'+$("#taskNo").val()+'",';
				str += '"taskMsg.prjtNo":"'+$("#prjtNo").val()+'",';
				str += '"taskMsg.wfNo":"'+$("#wfNo").val()+'",';
				str += '"taskMsg.title":"'+$("#title").val()+'"';
				str += '}';

				$.post("TaskMsg!sav.shtml",
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
				$.post("TaskMsg!voi.shtml",
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
				$.post("TaskMsg!del.shtml",
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
				$.post("TaskMsg!sub.shtml",
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
				$.post("TaskMsg!can.shtml",
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
				$.post("TaskMsg!chk.shtml",
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
				$.post("TaskMsg!rev.shtml",
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
				$.post("TaskMsg!con.shtml",
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
				$.post("TaskMsg!sta.shtml",
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
				$.post("TaskMsg!stp.shtml",
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
				$.post("TaskMsg!ove.shtml",
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
				$.post("TaskMsg!iss.shtml",
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
				$("#form1").attr("action","TaskMsg!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './TaskMsg!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：TaskMsg', height: 560, width: 780,url: './TaskMsg!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","TaskMsg!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}