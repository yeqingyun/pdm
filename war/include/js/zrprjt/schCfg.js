function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['schId']);
		else
			removeCheckedCustomer(this.records[rowid]['schId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(schId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == schId) return i;
	}
	return -1;
}
function addCheckedCustomer(schId) {
	if(findCheckedCustomer(schId) == -1)
		checkedCustomer.push(schId);
}
function removeCheckedCustomer(schId){
	var i = findCheckedCustomer(schId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.schId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.schId);
	else
		removeCheckedCustomer(data.schId);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#schId").length > 0)
		$("#schId").val("");
	if ($("#parent").length > 0)
		$("#parent").val("");
	if ($("#flowId").length > 0)
		$("#flowId").val("");
	if ($("#typId").length > 0)
		$("#typId").val("");
	if ($("#schNo").length > 0)
		$("#schNo").val("");
	if ($("#leve").length > 0)
		$("#leve").val("");
	if ($("#cfgTime").length > 0)
		$("#cfgTime").val("");
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
	if ($("#schNm").length > 0)
		$("#schNm").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：SchCfg', height: 560, width: 780,url: './SchCfg!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：SchCfg', height: 560, width: 780,url: './SchCfg!edit.shtml?schCfg.schId='+data.schId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	 $.ligerDialog.open({title:'查看对话框：SchCfg', height: 560, width: 780,url: './SchCfg!view.shtml?schCfg.schId='+data.schId,showMax: true, showToggle: true, showMin: true, isResize: true});
   

}
function sea() {
	gridManager.setOptions({
		parms: [
            {name: 'isSea', value: true},
			{name: 'schCfg.parent', value: document.getElementById("schCfg.parent").value},
			{name: 'schCfg.typId', value: document.getElementById("schCfg.typId").value},
			{name: 'schCfg.schNo', value: $("#schNo").val()},
			//{name: 'schCfg.leve', value: $("#leve").val()},
			{name: 'schCfg.status', value: $("#status").val()},
			{name: 'schCfg.schNm', value: $("#schNm").val()}
		]
	});
	
	gridManager.loadData();
}

function check() {
	  $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        //调试状态，不会提交数据的
        debug: true,
        errorPlacement: function (lable, element)
        {

            if (element.hasClass("l-textarea"))
            {
                element.addClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field"))
            {
                element.parent().addClass("l-text-invalid");
            }
            $(element).removeAttr("title").ligerHideTip();
            $(element).attr("title", lable.html()).ligerTip();
        },
        success: function (lable)
        {
            var element = $("#" + lable.attr("for"));
            if (element.hasClass("l-textarea"))
            {
                element.removeClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field"))
            {
                element.parent().removeClass("l-text-invalid");
            }
            $(element).removeAttr("title").ligerHideTip();
        },
        submitHandler: function ()
        {
            saver();
        }
    });
   // $("form").ligerForm();
 
}
function sav() {
	$("form").submit();
}
function saver() {
	
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				
				if(document.getElementById("schCfg.schId")){
					str += '"schCfg.schId":"'+document.getElementById("schCfg.schId").value+'",';
				}
				str += '"schCfg.parent":"'+$("#parent").ligerGetComboBoxManager().getValue()+'",';
				str += '"schCfg.schWf.flowId":"'+$("#flowId").ligerGetComboBoxManager().getValue()+'",';
				str += '"schCfg.typId":"'+$("#typId").ligerGetComboBoxManager().getValue()+'",';
				str += '"schCfg.schNo":"'+document.getElementById("schNo").value+'",';
				//str += '"schCfg.leve":"'+$("#leve").val()+'",';
				str += '"schCfg.cfgTime":"'+$("#cfgTime").val()+'",';
				str += '"schCfg.status":"'+$("#status").ligerGetComboBoxManager().getValue()+'",';
				str += '"schCfg.manualStart":"'+$("#manualStart").ligerGetComboBoxManager().getValue()+'",';
				
				//str += '"schCfg.createBy":"'+$("#createBy").val()+'",';
				//str += '"schCfg.lastUpd":"'+$("#lastUpd").val()+'",';
				//str += '"schCfg.createDate":"'+$("#createDate").val()+'",';
				//str += '"schCfg.lastDate":"'+$("#lastDate").val()+'",';
				str += '"schCfg.schNm":"'+$("#schNm").val()+'",';
				str += '"schCfg.critical":"'+$("#critical").ligerGetComboBoxManager().getValue()+'",';
				str += '"schCfg.milestone":"'+$("#milestone").ligerGetComboBoxManager().getValue()+'",';
				str += '"schCfg.remark":"'+$("#remark").val()+'"';
				str += '}';
				$.post("SchCfg!sav.shtml",
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
				$.post("SchCfg!voi.shtml",
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
				$.post("SchCfg!del.shtml",
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
				$.post("SchCfg!sub.shtml",
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
				$.post("SchCfg!can.shtml",
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
				$.post("SchCfg!chk.shtml",
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
				$.post("SchCfg!rev.shtml",
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
				$.post("SchCfg!con.shtml",
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
				$.post("SchCfg!sta.shtml",
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
				$.post("SchCfg!stp.shtml",
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
				$.post("SchCfg!ove.shtml",
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
				$.post("SchCfg!iss.shtml",
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
				$("#form1").attr("action","SchCfg!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './SchCfg!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：SchCfg', height: 560, width: 780,url: './SchCfg!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","SchCfg!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}