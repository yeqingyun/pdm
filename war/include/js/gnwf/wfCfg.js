function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['flowId']);
		else
			removeCheckedCustomer(this.records[rowid]['flowId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(flowId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == flowId) return i;
	}
	return -1;
}
function addCheckedCustomer(flowId) {
	if(findCheckedCustomer(flowId) == -1)
		checkedCustomer.push(flowId);
}
function removeCheckedCustomer(flowId){
	var i = findCheckedCustomer(flowId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.flowId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.flowId);
	else
		removeCheckedCustomer(data.flowId);
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
	if ($("#flowId").length > 0)
		$("#flowId").val("");
	if ($("#comId").length > 0)
		$("#comId").val("");
	if ($("#deptId").length > 0)
		$("#deptId").val("");
	if ($("#cateId").length > 0)
		$("#cateId").val("");
	if ($("#isFirstStep").length > 0)
		$("#isFirstStep").val("");
	if ($("#sphere").length > 0)
		$("#sphere").val("");
	if ($("#relateId").length > 0)
		$("#relateId").val("");
	if ($("#limit").length > 0)
		$("#limit").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#hasQuesMoudle").length > 0)
		$("#hasQuesMoudle").val("");
	if ($("#scheCfgId").length > 0)
		$("#scheCfgId").val("");
	if ($("#flowCode").length > 0)
		$("#flowCode").val("");
	if ($("#flowName").length > 0)
		$("#flowName").val("");
	if ($("#flowUri").length > 0)
		$("#flowUri").val("");
	if ($("#printUri").length > 0)
		$("#printUri").val("");
	if ($("#specialClass").length > 0)
		$("#specialClass").val("");
	if ($("#addRdExtendUri").length > 0)
		$("#addRdExtendUri").val("");
	if ($("#flowDesc").length > 0)
		$("#flowDesc").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：WfCfg', height: 560, width: 780,url: './WfCfg!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：WfCfg', height: 560, width: 780,url: './WfCfg!edit.shtml?wfCfg.flowId='+data.flowId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：WfCfg', height: 560, width: 780,url: './WfCfg!view.shtml?wfCfg.flowId='+data.flowId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfCfg.cateId', value: $("#cateId").val()},
			{name: 'wfCfg.flowName', value: $("#flowName").val()}
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
				
				str += '"wfCfg.flowCode":"'+$("#flowCode").val()+'",';
				str += '"wfCfg.flowName":"'+$("#flowName").val()+'",';
				str += '"wfCfg.status":"'+$("#status").val()+'",';
				str += '"wfCfg.cateId":"'+$("#cateId").val()+'"';
				
				if($("#flowId").length>0){
					str += ',"wfCfg.flowId":"'+$("#flowId").val()+'",';
					str += '"wfCfg.comId":"'+$("#comId").val()+'",';
					str += '"wfCfg.deptId":"'+$("#deptId").val()+'",';
					str += '"wfCfg.sphere":"'+$("#sphere").val()+'",';
					str += '"wfCfg.limit":"'+$("#limit").val()+'",';
					str += '"wfCfg.hasQuesMoudle":"'+$("#hasQuesMoudle").val()+'",';
					str += '"wfCfg.flowUri":"'+$("#flowUri").val()+'",';
					str += '"wfCfg.printUri":"'+$("#printUri").val()+'",';
					str += '"wfCfg.specialClass":"'+$("#specialClass").val()+'",';
					str += '"wfCfg.addRdExtendUri":"'+$("#addRdExtendUri").val()+'",';
					str += '"wfCfg.flowDesc":"'+$("#flowDesc").val()+'"';
				}
				
				var arrChk0 = $("input[name='wfDepts']");
			    for (var i=0;i<arrChk0.length;i++) {
			         if(arrChk0[i].checked == true){
			        	 str += ',"wfDepts['+i+'].deptId":"'+arrChk0[i].value+'"';
			         }
			    }
			    
				var arrChk0 = $("input[name='wfRelates']");
			    for (var i=0;i<arrChk0.length;i++) {
			         if(arrChk0[i].checked == true){
			        	 str += ',"wfRelates['+i+'].relateId":"'+arrChk0[i].value+'"';
			         }
			    }
			    
			    var arrChk0 = $("input[name='wfLeaders']");
			    for (var i=0;i<arrChk0.length;i++) {
			        str += ',"wfLeaders['+i+'].userId":"'+$(arrChk0[i]).val()+'"';
			    }	
			    
				str += '}';
				
				$.post("WfCfg!sav.shtml",
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
				$.post("WfCfg!voi.shtml",
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
				$.post("WfCfg!del.shtml",
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
				$.post("WfCfg!sub.shtml",
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
				$.post("WfCfg!can.shtml",
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
				$.post("WfCfg!chk.shtml",
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
				$.post("WfCfg!rev.shtml",
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
				$.post("WfCfg!con.shtml",
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
				$.post("WfCfg!sta.shtml",
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
				$.post("WfCfg!stp.shtml",
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
				$.post("WfCfg!ove.shtml",
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
				$.post("WfCfg!iss.shtml",
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
				$("#form1").attr("action","WfCfg!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfCfg!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfCfg', height: 560, width: 780,url: './WfCfg!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfCfg!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}

function aa(from,to){
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:'typNm', valueField:'typId',  isMultiSelect: false,
		onSelected: function (newvalue)
	    {
			$.post("SchCfg!getPreSchCfgs.shtml",
					{'schCfg.typId':newvalue},
					function(data) {
						//alert(data);
						//$("#parent").value = null;
			            $("#parent").ligerGetComboBoxManager().setData(data);
					},
					"json"
				);
	    }
	});
}

//添加用户
function addleader() {
	var userId = $("#acceptBy").ligerGetComboBoxManager().getValue();
	
	if (userId == null || userId=='' || userId=='-1') {
		alert("请选择用户 ");
		return;
	}
	
	var userName = $("#acceptBy").ligerGetComboBoxManager().findTextByValue(userId);
	
	var tblItems = document.getElementById("leadertable");
	var i = tblItems.rows.length - 1;
	var row = tblItems.insertRow(tblItems.rows.length);
	row.id = "idRow" + i;
	row.style.backgroundColor = "#FFFFFF";

	col = row.insertCell();
	col.innerHTML = "<input type='hidden' name='wfLeaders' value='" +userId+ "'>" + userName;

	col = row.insertCell();
	col.innerHTML = "<a href='javascript:;' onclick ='delwfleader(\"leadertable\",\"idRow" +i+ "\")'>删除</a>";
}

//删除用户
function delwfleader(tableId, curIdRow){
	var obj = document.getElementById(tableId);
	var tr = obj.rows[curIdRow];
	obj.deleteRow(tr.rowIndex);
}




