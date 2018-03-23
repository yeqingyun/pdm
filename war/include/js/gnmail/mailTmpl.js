function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['tmplId']);
		else
			removeCheckedCustomer(this.records[rowid]['tmplId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(tmplId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == tmplId) return i;
	}
	return -1;
}
function addCheckedCustomer(tmplId) {
	if(findCheckedCustomer(tmplId) == -1)
		checkedCustomer.push(tmplId);
}
function removeCheckedCustomer(tmplId){
	var i = findCheckedCustomer(tmplId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.tmplId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.tmplId);
	else
		removeCheckedCustomer(data.tmplId);
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
	if ($("#tmplId").length > 0)
		$("#tmplId").val("");
	if ($("#cfgId").length > 0)
		$("#cfgId").val("");
	if ($("#userId").length > 0)
		$("#userId").val("");
	if ($("#tmplName").length > 0)
		$("#tmplName").val("");
	if ($("#tmplText").length > 0)
		$("#tmplText").val("");
	if ($("#type").length > 0)
		$("#type").val("");
	if ($("#isAuto").length > 0)
		$("#isAuto").val("");
	if ($("#startAutoStart").length > 0)
		$("#startAutoStart").val("");
	if ($("#endAutoStart").length > 0)
		$("#endAutoStart").val("");
	if ($("#autoStart").length > 0)
		$("#autoStart").val("");
	if ($("#autoCycle").length > 0)
		$("#autoCycle").val("");
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
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").val("");
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").val("");
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：MailTmpl', height: 560, width: 780,url: './MailTmpl!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：MailTmpl', height: 560, width: 780,url: './MailTmpl!edit.shtml?mailTmpl.tmplId='+data.tmplId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：MailTmpl', height: 560, width: 780,url: './MailTmpl!view.shtml?mailTmpl.tmplId='+data.tmplId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'mailTmpl.tmplId', value: $("#tmplId").val()},
			{name: 'mailTmpl.cfgId', value: $("#cfgId").val()},
			{name: 'mailTmpl.userId', value: $("#userId").val()},
			{name: 'mailTmpl.tmplName', value: $("#tmplName").val()},
			{name: 'mailTmpl.tmplText', value: $("#tmplText").val()},
			{name: 'mailTmpl.type', value: $("#type").val()},
			{name: 'mailTmpl.isAuto', value: $("#isAuto").val()},
			{name: 'mailTmpl.startAutoStart', value: $("#startAutoStart").val()},
			{name: 'mailTmpl.endAutoStart', value: $("#endAutoStart").val()},
			{name: 'mailTmpl.autoCycle', value: $("#autoCycle").val()},
			{name: 'mailTmpl.status', value: $("#status").val()},
			{name: 'mailTmpl.createBy', value: $("#createBy").val()},
			{name: 'mailTmpl.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'mailTmpl.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'mailTmpl.lastUpd', value: $("#lastUpd").val()},
			{name: 'mailTmpl.startLastUpdDate', value: $("#startLastUpdDate").val()},
			{name: 'mailTmpl.endLastUpdDate', value: $("#endLastUpdDate").val()}

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
				str += '"mailTmpl.tmplId":"'+$("#tmplId").val()+'",';
				str += '"mailTmpl.cfgId":"'+$("#cfgId").val()+'",';
				str += '"mailTmpl.userId":"'+$("#userId").val()+'",';
				str += '"mailTmpl.tmplName":"'+$("#tmplName").val()+'",';
				str += '"mailTmpl.tmplText":"'+$("#tmplText").val()+'",';
				str += '"mailTmpl.type":"'+$("#type").val()+'",';
				str += '"mailTmpl.isAuto":"'+$("#isAuto").val()+'",';
				str += '"mailTmpl.autoStart":"'+$("#autoStart").val()+'",';
				str += '"mailTmpl.autoCycle":"'+$("#autoCycle").val()+'",';
				str += '"mailTmpl.status":"'+$("#status").val()+'",';
				str += '"mailTmpl.createBy":"'+$("#createBy").val()+'",';
				str += '"mailTmpl.createDate":"'+$("#createDate").val()+'",';
				str += '"mailTmpl.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"mailTmpl.lastUpdDate":"'+$("#lastUpdDate").val()+'"';
				str += '}';

				$.post("MailTmpl!sav.shtml",
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
				$.post("MailTmpl!voi.shtml",
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
				$.post("MailTmpl!del.shtml",
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
				$.post("MailTmpl!sub.shtml",
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
				$.post("MailTmpl!can.shtml",
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
				$.post("MailTmpl!chk.shtml",
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
				$.post("MailTmpl!rev.shtml",
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
				$.post("MailTmpl!con.shtml",
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
				$.post("MailTmpl!sta.shtml",
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
				$.post("MailTmpl!stp.shtml",
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
				$.post("MailTmpl!ove.shtml",
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
				$.post("MailTmpl!iss.shtml",
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
				$("#form1").attr("action","MailTmpl!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './MailTmpl!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：MailTmpl', height: 560, width: 780,url: './MailTmpl!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","MailTmpl!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}