function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['mailId']);
		else
			removeCheckedCustomer(this.records[rowid]['mailId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(mailId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == mailId) return i;
	}
	return -1;
}
function addCheckedCustomer(mailId) {
	if(findCheckedCustomer(mailId) == -1)
		checkedCustomer.push(mailId);
}
function removeCheckedCustomer(mailId){
	var i = findCheckedCustomer(mailId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.mailId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.mailId);
	else
		removeCheckedCustomer(data.mailId);
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
	if ($("#mailId").length > 0)
		$("#mailId").val("");
	if ($("#wfNO").length > 0)
		$("#wfNO").val("");
	if ($("#taskId").length > 0)
		$("#taskId").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").val("");
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").val("");
	if ($("#createDate").length > 0)
		$("#createDate").val("");
	if ($("#acceptBy").length > 0)
		$("#acceptBy").val("");
	if ($("#startAcceptDate").length > 0)
		$("#startAcceptDate").val("");
	if ($("#endAcceptDate").length > 0)
		$("#endAcceptDate").val("");
	if ($("#acceptDate").length > 0)
		$("#acceptDate").val("");
	if ($("#title").length > 0)
		$("#title").val("");
	if ($("#mailText").length > 0)
		$("#mailText").val("");
	if ($("#taskUri").length > 0)
		$("#taskUri").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：MailAutoM', height: 560, width: 780,url: './MailAutoM!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：MailAutoM', height: 560, width: 780,url: './MailAutoM!edit.shtml?mailAutoM.mailId='+data.mailId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：MailAutoM', height: 560, width: 780,url: './MailAutoM!view.shtml?mailAutoM.mailId='+data.mailId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'mailAutoM.mailId', value: $("#mailId").val()},
			{name: 'mailAutoM.wfNO', value: $("#wfNO").val()},
			{name: 'mailAutoM.taskId', value: $("#taskId").val()},
			{name: 'mailAutoM.createBy', value: $("#createBy").val()},
			{name: 'mailAutoM.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'mailAutoM.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'mailAutoM.acceptBy', value: $("#acceptBy").val()},
			{name: 'mailAutoM.startAcceptDate', value: $("#startAcceptDate").val()},
			{name: 'mailAutoM.endAcceptDate', value: $("#endAcceptDate").val()},
			{name: 'mailAutoM.title', value: $("#title").val()},
			{name: 'mailAutoM.mailText', value: $("#mailText").val()},
			{name: 'mailAutoM.taskUri', value: $("#taskUri").val()},
			{name: 'mailAutoM.status', value: $("#status").val()},
			{name: 'mailAutoM.remark', value: $("#remark").val()}

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
				str += '"mailAutoM.mailId":"'+$("#mailId").val()+'",';
				str += '"mailAutoM.wfNO":"'+$("#wfNO").val()+'",';
				str += '"mailAutoM.taskId":"'+$("#taskId").val()+'",';
				str += '"mailAutoM.createBy":"'+$("#createBy").val()+'",';
				str += '"mailAutoM.createDate":"'+$("#createDate").val()+'",';
				str += '"mailAutoM.acceptBy":"'+$("#acceptBy").val()+'",';
				str += '"mailAutoM.acceptDate":"'+$("#acceptDate").val()+'",';
				str += '"mailAutoM.title":"'+$("#title").val()+'",';
				str += '"mailAutoM.mailText":"'+$("#mailText").val()+'",';
				str += '"mailAutoM.taskUri":"'+$("#taskUri").val()+'",';
				str += '"mailAutoM.status":"'+$("#status").val()+'",';
				str += '"mailAutoM.remark":"'+$("#remark").val()+'"';
				str += '}';

				$.post("MailAutoM!sav.shtml",
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
				$.post("MailAutoM!voi.shtml",
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
				$.post("MailAutoM!del.shtml",
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
				$.post("MailAutoM!sub.shtml",
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
				$.post("MailAutoM!can.shtml",
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
				$.post("MailAutoM!chk.shtml",
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
				$.post("MailAutoM!rev.shtml",
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
				$.post("MailAutoM!con.shtml",
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
				$.post("MailAutoM!sta.shtml",
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
				$.post("MailAutoM!stp.shtml",
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
				$.post("MailAutoM!ove.shtml",
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
				$.post("MailAutoM!iss.shtml",
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
				$("#form1").attr("action","MailAutoM!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './MailAutoM!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：MailAutoM', height: 560, width: 780,url: './MailAutoM!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","MailAutoM!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}