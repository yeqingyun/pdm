function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['deptId']);
		else
			removeCheckedCustomer(this.records[rowid]['deptId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(deptId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == deptId) return i;
	}
	return -1;
}
function addCheckedCustomer(deptId) {
	if(findCheckedCustomer(deptId) == -1)
		checkedCustomer.push(deptId);
}
function removeCheckedCustomer(deptId){
	var i = findCheckedCustomer(deptId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.deptId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.deptId);
	else
		removeCheckedCustomer(data.deptId);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#deptId").length > 0)
		$("#deptId").val("");
	if ($("#comId").length > 0)
		$("#comId").val("");
	if ($("#parent").length > 0)
		$("#parent").val("");
	if ($("#leve").length > 0)
		$("#leve").val("");
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
	if ($("#deptNo").length > 0)
		$("#deptNo").val("");
	if ($("#deptNm").length > 0)
		$("#deptNm").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：Dept', height: 560, width: 780,url: './Dept!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：Dept', height: 560, width: 780,url: './Dept!edit.shtml?dept.deptId='+data.deptId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：Dept', height: 560, width: 780,url: './Dept!view.shtml?dept.deptId='+data.deptId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'dept.deptId', value: $("#deptId").val()},
			{name: 'dept.comId', value: $("#comId").val()},
			{name: 'dept.parent', value: $("#parent").val()},
			{name: 'dept.leve', value: $("#leve").val()},
			{name: 'dept.status', value: $("#status").val()},
			{name: 'dept.createBy', value: $("#createBy").val()},
			{name: 'dept.lastUpd', value: $("#lastUpd").val()},
			{name: 'dept.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'dept.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'dept.startLastDate', value: $("#startLastDate").val()},
			{name: 'dept.endLastDate', value: $("#endLastDate").val()},
			{name: 'dept.deptNo', value: $("#deptNo").val()},
			{name: 'dept.deptNm', value: $("#deptNm").val()},
			{name: 'dept.remark', value: $("#remark").val()}

		]
	});
	gridManager.loadData();
}
function sav() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				str += '"dept.deptId":"'+$("#deptId").val()+'",';
				str += '"dept.comId":"'+$("#comId").val()+'",';
				str += '"dept.parent":"'+$("#parent").val()+'",';
				str += '"dept.leve":"'+$("#leve").val()+'",';
				str += '"dept.status":"'+$("#status").val()+'",';
				str += '"dept.createBy":"'+$("#createBy").val()+'",';
				str += '"dept.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"dept.createDate":"'+$("#createDate").val()+'",';
				str += '"dept.lastDate":"'+$("#lastDate").val()+'",';
				str += '"dept.deptNo":"'+$("#deptNo").val()+'",';
				str += '"dept.deptNm":"'+$("#deptNm").val()+'",';
				str += '"dept.remark":"'+$("#remark").val()+'"';
				str += '}';

				$.post("Dept!sav.shtml",
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
				$.post("Dept!voi.shtml",
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
				$.post("Dept!del.shtml",
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
				$.post("Dept!sub.shtml",
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
				$.post("Dept!can.shtml",
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
				$.post("Dept!chk.shtml",
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
				$.post("Dept!rev.shtml",
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
				$.post("Dept!con.shtml",
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
				$.post("Dept!sta.shtml",
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
				$.post("Dept!stp.shtml",
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
				$.post("Dept!ove.shtml",
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
				$.post("Dept!iss.shtml",
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
				$("#form1").attr("action","Dept!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './Dept!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：Dept', height: 560, width: 780,url: './Dept!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","Dept!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}