function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['schDtlId']);
		else
			removeCheckedCustomer(this.records[rowid]['schDtlId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(schDtlId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == schDtlId) return i;
	}
	return -1;
}
function addCheckedCustomer(schDtlId) {
	if(findCheckedCustomer(schDtlId) == -1)
		checkedCustomer.push(schDtlId);
}
function removeCheckedCustomer(schDtlId){
	var i = findCheckedCustomer(schDtlId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.schDtlId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.schDtlId);
	else
		removeCheckedCustomer(data.schDtlId);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#schDtlId").length > 0)
		$("#schDtlId").val("");
	if ($("#schId").length > 0)
		$("#schId").val("");
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
	if ($("#docNm").length > 0)
		$("#docNm").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：SchDtl', height: 560, width: 780,url: './SchDtl!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	 $.ligerDialog.open({title:'编辑对话框：SchDtl', height: 560, width: 780,url: './SchDtl!edit.shtml?schDtl.schDtlId='+data.schDtlId,showMax: true, showToggle: true, showMin: true, isResize: true});
	
}
function vie(data) {
	
	 $.ligerDialog.open({title:'查看对话框：SchDtl', height: 560, width: 780,url: './SchDtl!view.shtml?schDtl.schDtlId='+data.schDtlId,showMax: true, showToggle: true, showMin: true, isResize: true});

}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'schDtl.schId', value: $("#schId").val()},
			{name: 'schDtl.status', value: $("#status").val()},
			/**{name: 'schDtl.schDtlId', value: $("#schDtlId").val()},
			{name: 'schDtl.createBy', value: $("#createBy").val()},
			{name: 'schDtl.lastUpd', value: $("#lastUpd").val()},
			{name: 'schDtl.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'schDtl.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'schDtl.startLastDate', value: $("#startLastDate").val()},
			{name: 'schDtl.endLastDate', value: $("#endLastDate").val()},
			{name: 'schDtl.remark', value: $("#remark").val()}
			**/
			{name: 'schDtl.docNm', value: $("#docNm").val()}

		]
	});
	gridManager.loadData();
}
function sav() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				if(document.getElementById("schDtl.schDtlId")){
					str += '"schDtl.schDtlId":"'+document.getElementById("schDtl.schDtlId").value+'",';
				}
				//str += '"schDtl.schDtlId":"'+$("#schDtlId").val()+'",';
				str += '"schDtl.schId":"'+$("#schId").val()+'",';
				str += '"schDtl.status":"'+$("#status").val()+'",';
				//str += '"schDtl.createBy":"'+$("#createBy").val()+'",';
				//str += '"schDtl.lastUpd":"'+$("#lastUpd").val()+'",';
			//	str += '"schDtl.createDate":"'+$("#createDate").val()+'",';
			//	str += '"schDtl.lastDate":"'+$("#lastDate").val()+'",';
				str += '"schDtl.docNm":"'+$("#docNm").val()+'",';
				str += '"schDtl.remark":"'+$("#remark").val()+'"';
				str += '}';

				$.post("SchDtl!sav.shtml",
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
				$.post("SchDtl!voi.shtml",
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
				$.post("SchDtl!del.shtml",
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
				$.post("SchDtl!sub.shtml",
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
				$.post("SchDtl!can.shtml",
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
				$.post("SchDtl!chk.shtml",
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
				$.post("SchDtl!rev.shtml",
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
				$.post("SchDtl!con.shtml",
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
				$.post("SchDtl!sta.shtml",
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
				$.post("SchDtl!stp.shtml",
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
				$.post("SchDtl!ove.shtml",
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
				$.post("SchDtl!iss.shtml",
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
				$("#form1").attr("action","SchDtl!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './SchDtl!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：SchDtl', height: 560, width: 780,url: './SchDtl!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","SchDtl!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}