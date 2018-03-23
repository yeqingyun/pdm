function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['matlId']);
		else
			removeCheckedCustomer(this.records[rowid]['matlId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(matlId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == matlId) return i;
	}
	return -1;
}
function addCheckedCustomer(matlId) {
	if(findCheckedCustomer(matlId) == -1)
		checkedCustomer.push(matlId);
}
function removeCheckedCustomer(matlId){
	var i = findCheckedCustomer(matlId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.matlId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.matlId);
	else
		removeCheckedCustomer(data.matlId);
}
function f_onDblClickRow(data){
	vie(data);
}
function check() {
	$.metadata.setType("attr", "validate");
    var v = $("form").validate({
        errorPlacement: function (lable, element) {

            if (element.hasClass("l-textarea")) {
                element.addClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field")) {
                element.parent().addClass("l-text-invalid");
            }

            var nextCell = element.parents("td:first").next("td");
            nextCell.find("div.l-exclamation").remove(); 
            $('<div class="l-exclamation" title="' + lable.html() + '"></div>').appendTo(nextCell).ligerTip(); 
        },
        success: function (lable) {
            var element = $("#" + lable.attr("for"));
            var nextCell = element.parents("td:first").next("td");
            if (element.hasClass("l-textarea")) {
                element.removeClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field")) {
                element.parent().removeClass("l-text-invalid");
            }
            nextCell.find("div.l-exclamation").remove();
        },
        submitHandler: function () {
        	saver();
        }
    });
    $("form").ligerForm();
}
function res() {
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").val("");
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").val("");
	if ($("#createDate").length > 0)
		$("#createDate").val("");
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").val("");
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").val("");
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").val("");
	if ($("#matlId").length > 0)
		$("#matlId").val("");
	if ($("#matlType").length > 0)
		$("#matlType").val("");
	if ($("#isSubs").length > 0)
		$("#isSubs").val("");
	if ($("#risk").length > 0)
		$("#risk").val("");
	if ($("#lotSize").length > 0)
		$("#lotSize").val("");
	if ($("#isPanel").length > 0)
		$("#isPanel").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#matlName").length > 0)
		$("#matlName").val("");
	if ($("#matlDesc").length > 0)
		$("#matlDesc").val("");
	if ($("#supplier").length > 0)
		$("#supplier").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");
	if ($("#supNo").length > 0)
		$("#supNo").val("");
	if ($("#matlEvalDesc").length > 0)
		$("#matlEvalDesc").val("");
	if ($("#matlNo").length > 0)
		$("#matlNo").val("");
	if ($("#wfNo").length > 0)
		$("#wfNo").val("");
	if ($("#matlLevel").length > 0)
		$("#matlLevel").val("");
	if ($("#historyLevel").length > 0)
		$("#historyLevel").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：WfMatl', height: 560, width: 780,url: './WfMatl!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：WfMatl', height: 560, width: 780,url: './WfMatl!edit.shtml?wfMatl.matlId='+data.matlId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：WfMatl', height: 560, width: 780,url: './WfMatl!view.shtml?wfMatl.matlId='+data.matlId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfMatl.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'wfMatl.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'wfMatl.startLastUpdDate', value: $("#startLastUpdDate").val()},
			{name: 'wfMatl.endLastUpdDate', value: $("#endLastUpdDate").val()},
			{name: 'wfMatl.matlId', value: $("#matlId").val()},
			{name: 'wfMatl.matlType', value: $("#matlType").val()},
			{name: 'wfMatl.isSubs', value: $("#isSubs").val()},
			{name: 'wfMatl.risk', value: $("#risk").val()},
			{name: 'wfMatl.lotSize', value: $("#lotSize").val()},
			{name: 'wfMatl.isPanel', value: $("#isPanel").val()},
			{name: 'wfMatl.status', value: $("#status").val()},
			{name: 'wfMatl.createBy', value: $("#createBy").val()},
			{name: 'wfMatl.lastUpd', value: $("#lastUpd").val()},
			{name: 'wfMatl.matlName', value: $("#matlName").val()},
			{name: 'wfMatl.matlDesc', value: $("#matlDesc").val()},
			{name: 'wfMatl.supplier', value: $("#supplier").val()},
			{name: 'wfMatl.remark', value: $("#remark").val()},
			{name: 'wfMatl.supNo', value: $("#supNo").val()},
			{name: 'wfMatl.matlEvalDesc', value: $("#matlEvalDesc").val()},
			{name: 'wfMatl.matlNo', value: $("#matlNo").val()},
			{name: 'wfMatl.wfNo', value: $("#wfNo").val()},
			{name: 'wfMatl.matlLevel', value: $("#matlLevel").val()},
			{name: 'wfMatl.historyLevel', value: $("#historyLevel").val()}

		]
	});
	gridManager.loadData();
}
function sav() {
	 $("form").submit();
}
function saver() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				str += '"wfMatl.createDate":"'+$("#createDate").val()+'",';
				str += '"wfMatl.lastUpdDate":"'+$("#lastUpdDate").val()+'",';
				str += '"wfMatl.matlId":"'+$("#matlId").val()+'",';
				str += '"wfMatl.matlType":"'+$("#matlType").val()+'",';
				str += '"wfMatl.isSubs":"'+$("#isSubs").val()+'",';
				str += '"wfMatl.risk":"'+$("#risk").val()+'",';
				str += '"wfMatl.lotSize":"'+$("#lotSize").val()+'",';
				str += '"wfMatl.isPanel":"'+$("#isPanel").val()+'",';
				str += '"wfMatl.status":"'+$("#status").val()+'",';
				str += '"wfMatl.createBy":"'+$("#createBy").val()+'",';
				str += '"wfMatl.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"wfMatl.matlName":"'+$("#matlName").val()+'",';
				str += '"wfMatl.matlDesc":"'+$("#matlDesc").val()+'",';
				str += '"wfMatl.supplier":"'+$("#supplier").val()+'",';
				str += '"wfMatl.remark":"'+$("#remark").val()+'",';
				str += '"wfMatl.supNo":"'+$("#supNo").val()+'",';
				str += '"wfMatl.matlEvalDesc":"'+$("#matlEvalDesc").val()+'",';
				str += '"wfMatl.matlNo":"'+$("#matlNo").val()+'",';
				str += '"wfMatl.wfNo":"'+$("#wfNo").val()+'",';
				str += '"wfMatl.matlLevel":"'+$("#matlLevel").val()+'",';
				str += '"wfMatl.historyLevel":"'+$("#historyLevel").val()+'"';
				str += '}';

				$.post("WfMatl!sav.shtml",
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
				$.post("WfMatl!voi.shtml",
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
				$.post("WfMatl!del.shtml",
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
				$.post("WfMatl!sub.shtml",
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
				$.post("WfMatl!can.shtml",
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
				$.post("WfMatl!chk.shtml",
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
				$.post("WfMatl!rev.shtml",
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
				$.post("WfMatl!con.shtml",
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
				$.post("WfMatl!sta.shtml",
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
				$.post("WfMatl!stp.shtml",
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
				$.post("WfMatl!ove.shtml",
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
				$.post("WfMatl!iss.shtml",
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
				$("#form1").attr("action","WfMatl!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfMatl!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfMatl', height: 560, width: 780,url: './WfMatl!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfMatl!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}