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
	if ($("#cfgId").length > 0)
		$("#cfgId").val("");
	if ($("#oexId").length > 0)
		$("#oexId").val("");
	if ($("#senderId").length > 0)
		$("#senderId").val("");
	if ($("#sender").length > 0)
		$("#sender").val("");
	if ($("#acceptId").length > 0)
		$("#acceptId").val("");
	if ($("#accept").length > 0)
		$("#accept").val("");
	if ($("#title").length > 0)
		$("#title").val("");
	if ($("#mailText").length > 0)
		$("#mailText").val("");
	if ($("#startMailDate").length > 0)
		$("#startMailDate").val("");
	if ($("#endMailDate").length > 0)
		$("#endMailDate").val("");
	if ($("#mailDate").length > 0)
		$("#mailDate").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");
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
	$.ligerDialog.open({title:'新增对话框：Mail', height: 560, width: 780,url: './Mail!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：Mail', height: 560, width: 780,url: './Mail!edit.shtml?mail.mailId='+data.mailId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：Mail', height: 560, width: 780,url: './Mail!view.shtml?mail.mailId='+data.mailId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'mail.mailId', value: $("#mailId").val()},
			{name: 'mail.cfgId', value: $("#cfgId").val()},
			{name: 'mail.oexId', value: $("#oexId").val()},
			{name: 'mail.senderId', value: $("#senderId").val()},
			{name: 'mail.sender', value: $("#sender").val()},
			{name: 'mail.acceptId', value: $("#acceptId").val()},
			{name: 'mail.accept', value: $("#accept").val()},
			{name: 'mail.title', value: $("#title").val()},
			{name: 'mail.mailText', value: $("#mailText").val()},
			{name: 'mail.startMailDate', value: $("#startMailDate").val()},
			{name: 'mail.endMailDate', value: $("#endMailDate").val()},
			{name: 'mail.remark', value: $("#remark").val()},
			{name: 'mail.status', value: $("#status").val()},
			{name: 'mail.createBy', value: $("#createBy").val()},
			{name: 'mail.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'mail.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'mail.lastUpd', value: $("#lastUpd").val()},
			{name: 'mail.startLastUpdDate', value: $("#startLastUpdDate").val()},
			{name: 'mail.endLastUpdDate', value: $("#endLastUpdDate").val()}

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
				str += '"mail.mailId":"'+$("#mailId").val()+'",';
				str += '"mail.cfgId":"'+$("#cfgId").val()+'",';
				str += '"mail.oexId":"'+$("#oexId").val()+'",';
				str += '"mail.senderId":"'+$("#senderId").val()+'",';
				str += '"mail.sender":"'+$("#sender").val()+'",';
				str += '"mail.acceptId":"'+$("#acceptId").val()+'",';
				str += '"mail.accept":"'+$("#accept").val()+'",';
				str += '"mail.title":"'+$("#title").val()+'",';
				str += '"mail.mailText":"'+$("#mailText").val()+'",';
				str += '"mail.mailDate":"'+$("#mailDate").val()+'",';
				str += '"mail.remark":"'+$("#remark").val()+'",';
				str += '"mail.status":"'+$("#status").val()+'",';
				str += '"mail.createBy":"'+$("#createBy").val()+'",';
				str += '"mail.createDate":"'+$("#createDate").val()+'",';
				str += '"mail.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"mail.lastUpdDate":"'+$("#lastUpdDate").val()+'"';
				str += '}';

				$.post("Mail!sav.shtml",
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
				$.post("Mail!voi.shtml",
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
				$.post("Mail!del.shtml",
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
				$.post("Mail!sub.shtml",
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
				$.post("Mail!can.shtml",
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
				$.post("Mail!chk.shtml",
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
				$.post("Mail!rev.shtml",
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
				$.post("Mail!con.shtml",
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
				$.post("Mail!sta.shtml",
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
				$.post("Mail!stp.shtml",
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
				$.post("Mail!ove.shtml",
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
				$.post("Mail!iss.shtml",
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
				$("#form1").attr("action","Mail!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './Mail!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：Mail', height: 560, width: 780,url: './Mail!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","Mail!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}