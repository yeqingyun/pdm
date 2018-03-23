function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['cfgId']);
		else
			removeCheckedCustomer(this.records[rowid]['cfgId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(cfgId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == cfgId) return i;
	}
	return -1;
}
function addCheckedCustomer(cfgId) {
	if(findCheckedCustomer(cfgId) == -1)
		checkedCustomer.push(cfgId);
}
function removeCheckedCustomer(cfgId){
	var i = findCheckedCustomer(cfgId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.cfgId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.cfgId);
	else
		removeCheckedCustomer(data.cfgId);
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
	if ($("#cfgId").length > 0)
		$("#cfgId").val("");
	if ($("#userId").length > 0)
		$("#userId").val("");
	if ($("#mailAddr").length > 0)
		$("#mailAddr").val("");
	if ($("#pwd").length > 0)
		$("#pwd").val("");
	if ($("#mailName").length > 0)
		$("#mailName").val("");
	if ($("#mailSign").length > 0)
		$("#mailSign").val("");
	if ($("#servIpAddr").length > 0)
		$("#servIpAddr").val("");
	if ($("#smpt").length > 0)
		$("#smpt").val("");
	if ($("#pop3").length > 0)
		$("#pop3").val("");
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
	$.ligerDialog.open({title:'新增对话框：MailCfg', height: 560, width: 780,url: './MailCfg!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：MailCfg', height: 560, width: 780,url: './MailCfg!edit.shtml?mailCfg.cfgId='+data.cfgId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：MailCfg', height: 560, width: 780,url: './MailCfg!view.shtml?mailCfg.cfgId='+data.cfgId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'mailCfg.cfgId', value: $("#cfgId").val()},
			{name: 'mailCfg.userId', value: $("#userId").val()},
			{name: 'mailCfg.mailAddr', value: $("#mailAddr").val()},
			{name: 'mailCfg.pwd', value: $("#pwd").val()},
			{name: 'mailCfg.mailName', value: $("#mailName").val()},
			{name: 'mailCfg.mailSign', value: $("#mailSign").val()},
			{name: 'mailCfg.servIpAddr', value: $("#servIpAddr").val()},
			{name: 'mailCfg.smpt', value: $("#smpt").val()},
			{name: 'mailCfg.pop3', value: $("#pop3").val()},
			{name: 'mailCfg.remark', value: $("#remark").val()},
			{name: 'mailCfg.status', value: $("#status").val()},
			{name: 'mailCfg.createBy', value: $("#createBy").val()},
			{name: 'mailCfg.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'mailCfg.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'mailCfg.lastUpd', value: $("#lastUpd").val()},
			{name: 'mailCfg.startLastUpdDate', value: $("#startLastUpdDate").val()},
			{name: 'mailCfg.endLastUpdDate', value: $("#endLastUpdDate").val()}

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
				str += '"mailCfg.cfgId":"'+$("#cfgId").val()+'",';
				str += '"mailCfg.userId":"'+$("#userId").val()+'",';
				str += '"mailCfg.mailAddr":"'+$("#mailAddr").val()+'",';
				str += '"mailCfg.pwd":"'+$("#pwd").val()+'",';
				str += '"mailCfg.mailName":"'+$("#mailName").val()+'",';
				str += '"mailCfg.mailSign":"'+$("#mailSign").val()+'",';
				str += '"mailCfg.servIpAddr":"'+$("#servIpAddr").val()+'",';
				str += '"mailCfg.smpt":"'+$("#smpt").val()+'",';
				str += '"mailCfg.pop3":"'+$("#pop3").val()+'",';
				str += '"mailCfg.remark":"'+$("#remark").val()+'",';
				str += '"mailCfg.status":"'+$("#status").val()+'",';
				str += '"mailCfg.createBy":"'+$("#createBy").val()+'",';
				str += '"mailCfg.createDate":"'+$("#createDate").val()+'",';
				str += '"mailCfg.lastUpd":"'+$("#lastUpd").val()+'",';
				str += '"mailCfg.lastUpdDate":"'+$("#lastUpdDate").val()+'"';
				str += '}';

				$.post("MailCfg!sav.shtml",
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
				$.post("MailCfg!voi.shtml",
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
				$.post("MailCfg!del.shtml",
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
				$.post("MailCfg!sub.shtml",
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
				$.post("MailCfg!can.shtml",
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
				$.post("MailCfg!chk.shtml",
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
				$.post("MailCfg!rev.shtml",
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
				$.post("MailCfg!con.shtml",
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
				$.post("MailCfg!sta.shtml",
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
				$.post("MailCfg!stp.shtml",
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
				$.post("MailCfg!ove.shtml",
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
				$.post("MailCfg!iss.shtml",
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
				$("#form1").attr("action","MailCfg!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './MailCfg!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：MailCfg', height: 560, width: 780,url: './MailCfg!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","MailCfg!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}