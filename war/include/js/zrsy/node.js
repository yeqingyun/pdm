function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['id']);
		else
			removeCheckedCustomer(this.records[rowid]['id']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(id) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == id) return i;
	}
	return -1;
}
function addCheckedCustomer(id) {
	if(findCheckedCustomer(id) == -1)
		checkedCustomer.push(id);
}
function removeCheckedCustomer(id){
	var i = findCheckedCustomer(id);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.id) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.id);
	else
		removeCheckedCustomer(data.id);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#syId").length > 0)
		$("#syId").val("");
	if ($("#parent").length > 0)
		$("#parent").val("");
	if ($("#menuId").length > 0)
		$("#menuId").val("");
	if ($("#nodeWidth").length > 0)
		$("#nodeWidth").val("");
	if ($("#checkBox").length > 0)
		$("#checkBox").val("");
	if ($("#leve").length > 0)
		$("#leve").val("");
	if ($("#sort").length > 0)
		$("#sort").val("");
	if ($("#text").length > 0)
		$("#text").val("");
	if ($("#url").length > 0)
		$("#url").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：Node', height: 560, width: 780,url: './Node!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：Node', height: 560, width: 780,url: './Node!edit.shtml?node.id='+data.id,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：Node', height: 560, width: 780,url: './Node!view.shtml?node.id='+data.id,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'node.syId', value: $("#syId").val()},
			{name: 'node.parent', value: $("#parent").val()},
			{name: 'node.menuId', value: $("#menuId").val()},
			{name: 'node.leve', value: $("#leve").val()},
			{name: 'node.sort', value: $("#sort").val()},
			{name: 'node.text', value: $("#text").val()},
			{name: 'node.url', value: $("#url").val()}

		]
	});
	gridManager.loadData();
}
function check() {
	$.metadata.setType("attr", "validate");
    var v = $("form").validate({
        //调试状态，不会提交数据的
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
function sav() {
	 $("form").submit();
}
function saver() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				str += '"node.id":"'+$("#id").val()+'",';
				str += '"node.syId":"'+$("#syId").val()+'",';
				str += '"node.parent":"'+$("#parent").val()+'",';
				str += '"node.menuId":"'+$("#menuId").val()+'",';
				str += '"node.nodeWidth":"'+$("#nodeWidth").val()+'",';
				str += '"node.checkBox":"'+$("#checkBox").val()+'",';
				str += '"node.leve":"'+$("#leve").val()+'",';
				str += '"node.sort":"'+$("#sort").val()+'",';
				str += '"node.text":"'+$("#text").val()+'",';
				str += '"node.url":"'+$("#url").val()+'"';
				
				var count=0;
				var arrChk0 = $("input[name='_btns0']");
			    for (var i=0;i<arrChk0.length;i++) {
			         if(arrChk0[i].checked == true){
			        	 str += ',"pgBtns['+count+'].pgTyp":"0"';
			        	 str += ',"pgBtns['+count+'].btnId":"'+arrChk0[i].value+'"';
			        	 count++;
			         }
			    }
			    var arrChk1 = $("input[name='_btns1']");
			    for (var j=0;j<arrChk1.length;j++) {
			         if(arrChk1[j].checked == true){
			        	 str += ',"pgBtns['+count+'].pgTyp":"1"';
			        	 str += ',"pgBtns['+count+'].btnId":"'+arrChk1[j].value+'"';
			        	 count++;
			         }
			    }
				var arrChk3 = $("input[name='_btns3']");
			    for (var k=0;k<arrChk3.length;k++) {
			         if(arrChk3[k].checked == true){
			        	 str += ',"pgBtns['+count+'].pgTyp":"3"';
			        	 str += ',"pgBtns['+count+'].btnId":"'+arrChk3[k].value+'"';
			        	 count++;
			         }
			    }
				str += '}';
				$.post("Node!sav.shtml",
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
				$.post("Node!voi.shtml",
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
				$.post("Node!del.shtml",
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
				$.post("Node!sub.shtml",
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
				$.post("Node!can.shtml",
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
				$.post("Node!chk.shtml",
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
				$.post("Node!rev.shtml",
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
				$.post("Node!con.shtml",
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
				$.post("Node!sta.shtml",
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
				$.post("Node!stp.shtml",
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
				$.post("Node!ove.shtml",
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
				$.post("Node!iss.shtml",
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
				$("#form1").attr("action","Node!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './Node!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：Node', height: 560, width: 780,url: './Node!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","Node!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}