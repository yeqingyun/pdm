function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['matlProId']);
		else
			removeCheckedCustomer(this.records[rowid]['matlProId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(matlProId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == matlProId) return i;
	}
	return -1;
}
function addCheckedCustomer(matlProId) {
	if(findCheckedCustomer(matlProId) == -1)
		checkedCustomer.push(matlProId);
}
function removeCheckedCustomer(matlProId){
	var i = findCheckedCustomer(matlProId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.matlProId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.matlProId);
	else
		removeCheckedCustomer(data.matlProId);
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
	if ($("#startPurRevDate").length > 0)
		$("#startPurRevDate").val("");
	if ($("#endPurRevDate").length > 0)
		$("#endPurRevDate").val("");
	if ($("#purRevDate").length > 0)
		$("#purRevDate").val("");
	if ($("#startMatRevDate").length > 0)
		$("#startMatRevDate").val("");
	if ($("#endMatRevDate").length > 0)
		$("#endMatRevDate").val("");
	if ($("#matRevDate").length > 0)
		$("#matRevDate").val("");
	if ($("#startManagerRevDate").length > 0)
		$("#startManagerRevDate").val("");
	if ($("#endManagerRevDate").length > 0)
		$("#endManagerRevDate").val("");
	if ($("#managerRevDate").length > 0)
		$("#managerRevDate").val("");
	if ($("#matlProId").length > 0)
		$("#matlProId").val("");
	if ($("#isPurPass").length > 0)
		$("#isPurPass").val("");
	if ($("#isMatPass").length > 0)
		$("#isMatPass").val("");
	if ($("#isManagerPass").length > 0)
		$("#isManagerPass").val("");
	if ($("#wfNo").length > 0)
		$("#wfNo").val("");
	if ($("#proName").length > 0)
		$("#proName").val("");
	if ($("#designName").length > 0)
		$("#designName").val("");
	if ($("#manageName").length > 0)
		$("#manageName").val("");
	if ($("#proDesc").length > 0)
		$("#proDesc").val("");
	if ($("#curStep").length > 0)
		$("#curStep").val("");
	if ($("#managerReview").length > 0)
		$("#managerReview").val("");
	if ($("#managerRemark").length > 0)
		$("#managerRemark").val("");
	if ($("#curVersion").length > 0)
		$("#curVersion").val("");
	if ($("#proCost").length > 0)
		$("#proCost").val("");
	if ($("#purReview").length > 0)
		$("#purReview").val("");
	if ($("#purRemark").length > 0)
		$("#purRemark").val("");
	if ($("#matReveiw").length > 0)
		$("#matReveiw").val("");
	if ($("#matRemark").length > 0)
		$("#matRemark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：WfMatlPro', height: 560, width: 780,url: './WfMatlPro!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：WfMatlPro', height: 560, width: 780,url: './WfMatlPro!edit.shtml?wfMatlPro.matlProId='+data.matlProId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：WfMatlPro', height: 560, width: 780,url: './WfMatlPro!view.shtml?wfMatlPro.matlProId='+data.matlProId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfMatlPro.startPurRevDate', value: $("#startPurRevDate").val()},
			{name: 'wfMatlPro.endPurRevDate', value: $("#endPurRevDate").val()},
			{name: 'wfMatlPro.startMatRevDate', value: $("#startMatRevDate").val()},
			{name: 'wfMatlPro.endMatRevDate', value: $("#endMatRevDate").val()},
			{name: 'wfMatlPro.startManagerRevDate', value: $("#startManagerRevDate").val()},
			{name: 'wfMatlPro.endManagerRevDate', value: $("#endManagerRevDate").val()},
			{name: 'wfMatlPro.matlProId', value: $("#matlProId").val()},
			{name: 'wfMatlPro.isPurPass', value: $("#isPurPass").val()},
			{name: 'wfMatlPro.isMatPass', value: $("#isMatPass").val()},
			{name: 'wfMatlPro.isManagerPass', value: $("#isManagerPass").val()},
			{name: 'wfMatlPro.wfNo', value: $("#wfNo").val()},
			{name: 'wfMatlPro.proName', value: $("#proName").val()},
			{name: 'wfMatlPro.designName', value: $("#designName").val()},
			{name: 'wfMatlPro.manageName', value: $("#manageName").val()},
			{name: 'wfMatlPro.proDesc', value: $("#proDesc").val()},
			{name: 'wfMatlPro.curStep', value: $("#curStep").val()},
			{name: 'wfMatlPro.managerReview', value: $("#managerReview").val()},
			{name: 'wfMatlPro.managerRemark', value: $("#managerRemark").val()},
			{name: 'wfMatlPro.curVersion', value: $("#curVersion").val()},
			{name: 'wfMatlPro.proCost', value: $("#proCost").val()},
			{name: 'wfMatlPro.purReview', value: $("#purReview").val()},
			{name: 'wfMatlPro.purRemark', value: $("#purRemark").val()},
			{name: 'wfMatlPro.matReveiw', value: $("#matReveiw").val()},
			{name: 'wfMatlPro.matRemark', value: $("#matRemark").val()}

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
				str += '"wfMatlPro.purRevDate":"'+$("#purRevDate").val()+'",';
				str += '"wfMatlPro.matRevDate":"'+$("#matRevDate").val()+'",';
				str += '"wfMatlPro.managerRevDate":"'+$("#managerRevDate").val()+'",';
				str += '"wfMatlPro.matlProId":"'+$("#matlProId").val()+'",';
				str += '"wfMatlPro.isPurPass":"'+$("#isPurPass").val()+'",';
				str += '"wfMatlPro.isMatPass":"'+$("#isMatPass").val()+'",';
				str += '"wfMatlPro.isManagerPass":"'+$("#isManagerPass").val()+'",';
				str += '"wfMatlPro.wfNo":"'+$("#wfNo").val()+'",';
				str += '"wfMatlPro.proName":"'+$("#proName").val()+'",';
				str += '"wfMatlPro.designName":"'+$("#designName").val()+'",';
				str += '"wfMatlPro.manageName":"'+$("#manageName").val()+'",';
				str += '"wfMatlPro.proDesc":"'+$("#proDesc").val()+'",';
				str += '"wfMatlPro.curStep":"'+$("#curStep").val()+'",';
				str += '"wfMatlPro.managerReview":"'+$("#managerReview").val()+'",';
				str += '"wfMatlPro.managerRemark":"'+$("#managerRemark").val()+'",';
				str += '"wfMatlPro.curVersion":"'+$("#curVersion").val()+'",';
				str += '"wfMatlPro.proCost":"'+$("#proCost").val()+'",';
				str += '"wfMatlPro.purReview":"'+$("#purReview").val()+'",';
				str += '"wfMatlPro.purRemark":"'+$("#purRemark").val()+'",';
				str += '"wfMatlPro.matReveiw":"'+$("#matReveiw").val()+'",';
				str += '"wfMatlPro.matRemark":"'+$("#matRemark").val()+'"';
				str += '}';

				$.post("WfMatlPro!sav.shtml",
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
				$.post("WfMatlPro!voi.shtml",
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
				$.post("WfMatlPro!del.shtml",
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
				$.post("WfMatlPro!sub.shtml",
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
				$.post("WfMatlPro!can.shtml",
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
				$.post("WfMatlPro!chk.shtml",
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
				$.post("WfMatlPro!rev.shtml",
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
				$.post("WfMatlPro!con.shtml",
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
				$.post("WfMatlPro!sta.shtml",
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
				$.post("WfMatlPro!stp.shtml",
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
				$.post("WfMatlPro!ove.shtml",
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
				$.post("WfMatlPro!iss.shtml",
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
				$("#form1").attr("action","WfMatlPro!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfMatlPro!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfMatlPro', height: 560, width: 780,url: './WfMatlPro!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfMatlPro!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}