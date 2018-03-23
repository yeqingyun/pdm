function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['driveId']);
		else
			removeCheckedCustomer(this.records[rowid]['driveId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(driveId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == driveId) return i;
	}
	return -1;
}
function addCheckedCustomer(driveId) {
	if(findCheckedCustomer(driveId) == -1)
		checkedCustomer.push(driveId);
}
function removeCheckedCustomer(driveId){
	var i = findCheckedCustomer(driveId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.driveId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.driveId);
	else
		removeCheckedCustomer(data.driveId);
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
	if ($("#driveId").length > 0)
		$("#driveId").val("");
	if ($("#driveNo").length > 0)
		$("#driveNo").val("");
	if ($("#driveNm").length > 0)
		$("#driveNm").val("");
	if ($("#flowId").length > 0)
		$("#flowId").val("");
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
	if ($("#startLastDate").length > 0)
		$("#startLastDate").val("");
	if ($("#endLastDate").length > 0)
		$("#endLastDate").val("");
	if ($("#lastDate").length > 0)
		$("#lastDate").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：Driver', height: 600, width: 820,url: './Driver!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：Driver', height: 600, width: 820,url: './Driver!edit.shtml?driver.driveId='+data.driveId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：Driver', height: 560, width: 780,url: './Driver!edit.shtml?driver.driveId='+data.driveId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'driver.driveNo', value: $("#driveNo").val()},
			{name: 'driver.driveNm', value: $("#driveNm").val()},
			{name: 'driver.flowId', value: $("#flowId").val()}
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
				str += '"driver.driveId":"'+$("#driveId").val()+'",';
				str += '"driver.driveNo":"'+$("#driveNo").val()+'",';
				str += '"driver.flowId":"'+$("#flowId").val()+'",';
				str += '"driver.remark":"'+$("#remark").val()+'",';
				if($("#stepId").ligerComboBox().selectedValue) {
					str += '"driver.stepId":"'+$("#stepId").ligerComboBox().selectedValue+'",';
				}
				str += '"driver.driveNm":"'+$("#driveNm").val()+'"';
				
				if(gridManager && gridManager.getData() && gridManager.getData() != '') {
					$.each(gridManager.getData(),function(index,d){
						for(var key in d) {
							if(key != "__status" ) {
									str += ',"driverDtls['+index+'].'+key+'":"'+d[key]+'"';
							}
						}
				    })
				}
				
				str += '}';
				$.post("Driver!sav.shtml",
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
				$.post("Driver!voi.shtml",
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
				$.post("Driver!del.shtml",
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
				$.post("Driver!sub.shtml",
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
				$.post("Driver!can.shtml",
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
				$.post("Driver!chk.shtml",
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
				$.post("Driver!rev.shtml",
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
				$.post("Driver!con.shtml",
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
				$.post("Driver!sta.shtml",
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
				$.post("Driver!stp.shtml",
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
				$.post("Driver!ove.shtml",
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
				$.post("Driver!iss.shtml",
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
				$("#form1").attr("action","Driver!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './Driver!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：Driver', height: 560, width: 780,url: './Driver!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","Driver!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}

//--------------------------------------------------选择物料明细弹窗调用function----------------------------------------------//
function f_selectItem() { 
	itemDialog = $.ligerDialog.open({ title: '选择驱动步骤', width: 720, height: 500, url: 'WfStep.shtml', buttons: [
        { text: '确定', onclick: f_selectItemOK },
        { text: '取消', onclick: f_selectItemCancel }
    ]
    });
}
function f_selectItemOK(item, dialog)
{
    var data = dialog.frame.f_select();
    if (!data)
    {
        alert('请选择行!');
        return;
    }
    selectRow(data);
}

function notExists(stepId) {
	//获取grid已经选取过的物料
	var data = gridManager.getData();
	for(var i = 0 ; i <data.length; i ++) {
		if(data[i].stepId == stepId) {
    		return false;
    	}
	}
    return true;
}

function selectRow(obj) {
	var row = gridManager.getSelectedRow();
    var exists_item= '';
    var data = new Array();;
    //处理弹窗双击选择事件
    if(obj.length > 0) {
    	data = obj;
    } else {
    	data.push(obj);
    }
    for(var i = 0 ; i < data.length ; i ++) {
    	if(notExists(data[i].stepId)) {
    	    gridManager.addRow({stepId: data[i].stepId,
    	    	flowId: data[i].flowId,
    	    	flowName: data[i].flowName,
    	    	stepType: data[i].stepType,
    	    	stepName: data[i].stepName
    	    	}, row, true);
    	    row ++ ;
    	}
    }
    gridManager.endEdit();
    itemDialog.close();
}
function f_selectItemCancel(item, dialog)
{
    dialog.close();
}

function deleteRow()
{ 
    gridManager.deleteSelectedRow();
}
function addNewRow()
{
	f_selectItem();   
}