function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['stepId']);
		else
			removeCheckedCustomer(this.records[rowid]['stepId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(stepId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == stepId) return i;
	}
	return -1;
}
function addCheckedCustomer(stepId) {
	if(findCheckedCustomer(stepId) == -1)
		checkedCustomer.push(stepId);
}
function removeCheckedCustomer(stepId){
	var i = findCheckedCustomer(stepId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.stepId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.stepId);
	else
		removeCheckedCustomer(data.stepId);
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
	if ($("#stepId").length > 0)
		$("#stepId").val("");
	if ($("#flowId").length > 0)
		$("#flowId").val("");
	if ($("#preStep").length > 0)
		$("#preStep").val("");
	if ($("#stepType").length > 0)
		$("#stepType").val("");
	if ($("#sort").length > 0)
		$("#sort").val("");
	if ($("#isAuto").length > 0)
		$("#isAuto").val("");
	if ($("#isUpdForm").length > 0)
		$("#isUpdForm").val("");
	if ($("#isSysFinsh").length > 0)
		$("#isSysFinsh").val("");
	if ($("#timeQty").length > 0)
		$("#timeQty").val("");
	if ($("#selectCom").length > 0)
		$("#selectCom").val("");
	if ($("#isLastStep").length > 0)
		$("#isLastStep").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#selectDept").length > 0)
		$("#selectDept").val("");
	if ($("#waitAuxiliary").length > 0)
		$("#waitAuxiliary").val("");
	if ($("#stepName").length > 0)
		$("#stepName").val("");
	if ($("#stepDesc").length > 0)
		$("#stepDesc").val("");
	if ($("#stepUri").length > 0)
		$("#stepUri").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：WfStep', height: 560, width: 780,url: './WfStep!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：WfStep', height: 560, width: 780,url: './WfStep!edit.shtml?wfStep.stepId='+data.stepId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：WfStep', height: 560, width: 780,url: './WfStep!view.shtml?wfStep.stepId='+data.stepId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfStep.flowId', value: $("#flowId").val()}
		]
	});
	gridManager.loadData();
}
function sav() {
	 $("form").submit();
}
function saver() {
	if($("#flowId").val()=='-1'){
		alert("请选择工作流");
		return;
	}
	
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				str += '"wfStep.flowId":"'+$("#flowId").val()+'",';
				str += '"wfStep.stepName":"'+$("#stepName").val()+'",';
				str += '"wfStep.stepType":"'+$("#stepType").val()+'",';
				str += '"wfStep.sort":"'+$("#sort").val()+'"';
				
				if($("#stepId").length>0){
					str += ',"wfStep.stepId":"'+$("#stepId").val()+'",';
					str += '"wfStep.isAuto":"'+$("#isAuto").val()+'",';
					str += '"wfStep.isUpdForm":"'+$("#isUpdForm").val()+'",';
					str += '"wfStep.isSysStartUp":"'+$("#isSysStartUp").val()+'",';
					str += '"wfStep.isSysFinsh":"'+$("#isSysFinsh").val()+'",';
					str += '"wfStep.timeQty":"'+$("#timeQty").val()+'",';
					str += '"wfStep.stepDesc":"'+$("#stepDesc").val()+'",';
					str += '"wfStep.isLastStep":"'+$("#isLastStep").val()+'",';
					str += '"wfStep.status":"'+$("#status").val()+'",';
					str += '"wfStep.waitAuxiliary":"'+$("#waitAuxiliary").val()+'",';
					str += '"wfStep.stepUri":"'+$("#stepUri").val()+'"';
				}
				
				var users = $("input[name='userList']");
			    for (var i=0;i<users.length;i++) {
			    	str += ',"wfStepUsers['+i+'].userId":"'+users[i].value+'"';
				        
				    var type = $("input[name='userType"+users[i].id+"']");
				    var typeValue;
				    for(var j=0;j<type.length;j++){
				        if(type[j].checked == true){
				        	typeValue = type[j].value;
				        }
				    }
				    str += ',"wfStepUsers['+i+'].userType":"'+typeValue+'"';
			    }
			    
			    
				var roles = $("input[name='roleList']");
				for (var i=0;i<roles.length;i++) {
				    str += ',"wfStepUsers['+i+'].prjtRoleId":"'+roles[i].value+'"';
				    
				    var type = $("input[name='userType"+roles[i].id+"']");
				    var typeValue;
				    for(var j=0;j<type.length;j++){
				        if(type[j].checked == true){
				        	typeValue = type[j].value;
				        }
				    }
				    str += ',"wfStepUsers['+i+'].userType":"'+typeValue+'"';
				}
			    
				var arrChk0 = $("input[name='wfStepNexts']");
			    for (var i=0;i<arrChk0.length;i++) {
			         if(arrChk0[i].checked == true){
			        	 str += ',"wfStepNexts['+i+'].nextId":"'+arrChk0[i].value+'"';
			         }
			    }
				str += '}';

				$.post("WfStep!sav.shtml",
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
				$.post("WfStep!voi.shtml",
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
				$.post("WfStep!del.shtml",
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
				$.post("WfStep!sub.shtml",
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
				$.post("WfStep!can.shtml",
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
				$.post("WfStep!chk.shtml",
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
				$.post("WfStep!rev.shtml",
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
				$.post("WfStep!con.shtml",
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
				$.post("WfStep!sta.shtml",
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
				$.post("WfStep!stp.shtml",
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
				$.post("WfStep!ove.shtml",
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
				$.post("WfStep!iss.shtml",
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
				$("#form1").attr("action","WfStep!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfStep!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfStep', height: 560, width: 780,url: './WfStep!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfStep!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}



//添加用户
function addUser() {
	var userId = $("#acceptBy").val();

	if (userId == null || userId=='') {
		alert("请选择用户 ");
		return;
	}
	var userName = $("#userName").val();
	
	var tblItems = document.getElementById("leadertable");
	var i = tblItems.rows.length - 1;
	var row = tblItems.insertRow(tblItems.rows.length);
	row.id = "idRow" + i;
	row.style.backgroundColor = "#FFFFFF";

	col = row.insertCell();
	col.innerHTML = "<input type='hidden' id='"+i+"' name='userList' value='" +userId+ "'>" + userName;
	
	col = row.insertCell();
	col.innerHTML = "<input type='radio' name='userType"+i+"' value='1' checked>主办 &nbsp;&nbsp;" 
				  + "<input type='radio' name='userType"+i+"' value='2' >协办";

	col = row.insertCell();
	col.innerHTML = "<a href='javascript:;' onclick ='delUser(\"leadertable\",\"idRow" +i+ "\")'>删除</a>";
}

//删除用户 或者 项目角色
function delUser(tableId, curIdRow){
	var obj = document.getElementById(tableId);
	var tr = obj.rows[curIdRow];
	obj.deleteRow(tr.rowIndex);
}

//添加角色
function addRole() {
	var roleId = $("#roleId").ligerGetComboBoxManager().getValue();
	if (roleId == null || roleId=='') {
		alert("选择项目角色 ");
		return;
	}
	var roleName = $("#roleId").find("option:selected").text();
	
	var tblItems = document.getElementById("leadertable");
	var i = tblItems.rows.length - 1;
	var row = tblItems.insertRow(tblItems.rows.length);
	row.id = "idRow" + i;
	row.style.backgroundColor = "#FFFFFF";

	col = row.insertCell();
	col.innerHTML = "<input type='hidden' id='"+i+"' name='roleList' value='" +roleId+ "'>" + roleName;
	
	col = row.insertCell();
	col.innerHTML = "<input type='radio' name='userType"+i+"' value='1' checked>主办 &nbsp;&nbsp;&nbsp;" 
				  + "<input type='radio' name='userType"+i+"' value='2' >协办";

	col = row.insertCell();
	col.innerHTML = "<a href='javascript:;' onclick ='delUser(\"leadertable\",\"idRow" +i+ "\")'>删除</a>";
}
