function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['matlNo']);
		else
			removeCheckedCustomer(this.records[rowid]['matlNo']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(matlNo) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == matlNo) return i;
	}
	return -1;
}
function addCheckedCustomer(matlNo) {
	if(findCheckedCustomer(matlNo) == -1)
		checkedCustomer.push(matlNo);
}
function removeCheckedCustomer(matlNo){
	var i = findCheckedCustomer(matlNo);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.matlNo) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.matlNo);
	else
		removeCheckedCustomer(data.matlNo);
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
	if ($("#matlNo").length > 0)
		$("#matlNo").val("");
	if ($("#gpCode").length > 0)
		$("#gpCode").val("");
	if ($("#matlRev").length > 0)
		$("#matlRev").val("");
	if ($("#matlNm").length > 0)
		$("#matlNm").val("");
	if ($("#matlDesc").length > 0)
		$("#matlDesc").val("");
	if ($("#optTyp").length > 0)
		$("#optTyp").val("");
	if ($("#matlTyp").length > 0)
		$("#matlTyp").val("");
	if ($("#lotSize").length > 0)
		$("#lotSize").val("");
	if ($("#isPanel").length > 0)
		$("#isPanel").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").val("");
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").val("");
	if ($("#createDate").length > 0)
		$("#createDate").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#startLastDate").length > 0)
		$("#startLastDate").val("");
	if ($("#endLastDate").length > 0)
		$("#endLastDate").val("");
	if ($("#lastDate").length > 0)
		$("#lastDate").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：OptStore', height: 560, width: 780,url: './OptStore!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：OptStore', height: 560, width: 780,url: './OptStore!edit.shtml?optStore.matlNo='+data.matlNo,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：OptStore', height: 560, width: 780,url: './OptStore!view.shtml?optStore.matlNo='+data.matlNo,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'optStore.matlNo', value: $("#matlNo").val()},
			{name: 'optStore.gpCode', value: $("#gpCode").val()},
			{name: 'optStore.matlRev', value: $("#matlRev").val()},
			{name: 'optStore.matlNm', value: $("#matlNm").val()},
			{name: 'optStore.matlDesc', value: $("#matlDesc").val()},
			{name: 'optStore.optTyp', value: $("#optTyp").val()},
			{name: 'optStore.matlTyp', value: $("#matlTyp").val()},
			{name: 'optStore.lotSize', value: $("#lotSize").val()},
			{name: 'optStore.isPanel', value: $("#isPanel").val()},
			{name: 'optStore.status', value: $("#status").val()},
			{name: 'optStore.createBy', value: $("#createBy").val()},
			{name: 'optStore.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'optStore.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'optStore.lastUpd', value: $("#lastUpd").val()},
			{name: 'optStore.startLastDate', value: $("#startLastDate").val()},
			{name: 'optStore.endLastDate', value: $("#endLastDate").val()}

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
				str += '"optStore.matlNo":"'+$("#matlNo").val()+'",';
				str += '"optStore.gpCode":"'+$("#gpCode").val()+'",';
				str += '"optStore.matlRev":"'+$("#matlRev").val()+'",';
				str += '"optStore.matlNm":"'+$("#matlNm").val()+'",';
				str += '"optStore.matlDesc":"'+$("#matlDesc").val()+'",';
				str += '"optStore.optTyp":"'+$("#optTyp").val()+'",';
				str += '"optStore.matlTyp":"'+$("#matlTyp").val()+'",';
				str += '"optStore.lotSize":"'+$("#lotSize").val()+'",';
				str += '"optStore.isPanel":"'+$("#isPanel").val()+'",';
				str += '"optStore.status":"'+$("#status").val()+'",';
				str += '"optStore.createBy":"'+$("#createBy").val()+'",';
				str += '"optStore.createDate":"'+$("#createDate").val()+'",';
				str += '"optStore.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"optStore.lastDate":"'+$("#lastDate").val()+'"';
				str += '}';

				$.post("OptStore!sav.shtml",
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
				$.post("OptStore!voi.shtml",
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
				$.post("OptStore!del.shtml",
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
				$.post("OptStore!sub.shtml",
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
				$.post("OptStore!can.shtml",
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
				$.post("OptStore!chk.shtml",
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
				$.post("OptStore!rev.shtml",
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
				$.post("OptStore!con.shtml",
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
				$.post("OptStore!sta.shtml",
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
				$.post("OptStore!stp.shtml",
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
				$.post("OptStore!ove.shtml",
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
				$.post("OptStore!iss.shtml",
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
				$("#form1").attr("action","OptStore!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './OptStore!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：OptStore', height: 560, width: 780,url: './OptStore!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","OptStore!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}