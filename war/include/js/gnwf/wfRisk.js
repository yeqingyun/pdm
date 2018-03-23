function f_onCheckAllRow(checked) {
	for ( var rowid in this.records) {
		if (checked)
			addCheckedCustomer(this.records[rowid]['riskId']);
		else
			removeCheckedCustomer(this.records[rowid]['riskId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(riskId) {
	for (var i = 0; i < checkedCustomer.length; i++) {
		if (checkedCustomer[i] == riskId)
			return i;
	}
	return -1;
}
function addCheckedCustomer(riskId) {
	if (findCheckedCustomer(riskId) == -1)
		checkedCustomer.push(riskId);
}
function removeCheckedCustomer(riskId) {
	var i = findCheckedCustomer(riskId);
	if (i == -1)
		return;
	checkedCustomer.splice(i, 1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.riskId) == -1)
		return false;
	return true;
}

var checkedWfNo;

function f_onCheckRow(checked, data) {
	if (checked) {
		addCheckedCustomer(data.riskId);
		checkedWfNo = data.wfNo;
	} else
		removeCheckedCustomer(data.riskId);
}
function f_onDblClickRow(data) {
	rep(data)
}
function check(rflag) {
	$.metadata.setType("attr", "validate");
	var v = $("form")
			.validate(
					{
						errorPlacement : function(lable, element) {

							if (element.hasClass("l-textarea")) {
								element.addClass("l-textarea-invalid");
							} else if (element.hasClass("l-text-field")) {
								element.parent().addClass("l-text-invalid");
							}

							var nextCell = element.parents("td:first").next(
									"td");
							nextCell.find("div.l-exclamation").remove();
							$(
									'<div class="l-exclamation" title="'
											+ lable.html() + '"></div>')
									.appendTo(nextCell).ligerTip();
						},
						success : function(lable) {
							var element = $("#" + lable.attr("for"));
							var nextCell = element.parents("td:first").next(
									"td");
							if (element.hasClass("l-textarea")) {
								element.removeClass("l-textarea-invalid");
							} else if (element.hasClass("l-text-field")) {
								element.parent().removeClass("l-text-invalid");
							}
							nextCell.find("div.l-exclamation").remove();
						},
						submitHandler : function() {
							if (rflag) {
								rsave();
							} else {
								saver();
							}
						}
					});
	// $("form").ligerForm();
}
function res() {
	if ($("#comId").length > 0)
		$("#comId").val("");
	if ($("#deptId").length > 0)
		$("#deptId").val("");
	if ($("#userId").length > 0)
		$("#userId").val("");
	if ($("#quesLevel").length > 0)
		$("#quesLevel").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#wfNo").length > 0)
		$("#wfNo").val("");
	if ($("#isRisk").length > 0)
		$("#isRisk").val("");
	if ($("#quesText").length > 0)
		$("#quesText").val("");
	if ($("#result").length > 0)
		$("#result").val("");
	if ($("#ryUsrs").length > 0)
		$("#ryUsrs").val("");
	if ($("#ryUsrNms").length > 0)
		$("#ryUsrNms").val("");
	if ($("#title").length > 0)
		$("#title").val("");
	if ($("#riskConsequence").length > 0)
		$("#riskConsequence").val("");
	if ($("#preventiveMeasures").length > 0)
		$("#preventiveMeasures").val("");
	if ($("#impTime").length > 0)
		$("#impTime").val("");
	if ($("#riskMonitor").length > 0)
		$("#riskMonitor").val("");

}

var DialogMgr = (function() {
	var dialog;
	return {
		create : function(title, url) {
			dialog = $.ligerDialog.open({
				title : title,
				height : 460,
				width : 810,
				url : url,
				showMax : true,
				showToggle : true,
				showMin : true,
				isResize : true
			})
		},
		close : function() {
			if (dialog) {
				dialog.close();
			}
		}
	};
})();

function add() {
	DialogMgr.create('新增对话框：WfRisk', './WfRisk!addRiskUI.shtml');
}
function savDeal(msg) {
	$.ligerDialog.success(msg);
	reloadGrid();
	DialogMgr.close();
}

function rep(data) {
	if (window.parent) {
		if (window.parent.f_open) {
			window.parent.f_open("./WfRisk!managerRisk.shtml?wfRisk.riskId="
					+ data.riskId, "风险管理：" + data.riskId,
					"./include/images/Alien Folder.png");
		} else {
			DialogMgr.create('管理对话框：WfRisk',
					'./WfRisk!managerRisk.shtml?wfRisk.riskId=' + data.riskId);
		}
	} else {
		DialogMgr.create('管理对话框：WfRisk',
				'./WfRisk!managerRisk.shtml?wfRisk.riskId=' + data.riskId);
	}
}
function sea() {
	if (gridManager) {
		gridManager.setOptions({
			parms : [
					{
						name : 'wfRisk.scheId',
						value : ($("#scheId").length > 0 && $("#scheId")
								.ligerComboBox().selectedValue) ? $("#scheId")
								.ligerComboBox().selectedValue : '' ? $(
								"#scheId").ligerComboBox().selectedValue : ''
					},
					{
						name : 'wfRisk.status',
						value : ($("#status").length > 0) ? $("#status").val()
								: ''
					},{
						name : 'wfRisk.responsibleUserName',
						value : ($("#responsibleUserName").length > 0) ? $("#responsibleUserName").val()
								: ''
					},
					{
						name : 'wfRisk.prjtNo',
						value : ($("#prjtNo").length > 0) ? $("#prjtNo").val()
								: ''
					},
					{
						name : 'wfRisk.title',
						value : ($("#title").length > 0) ? $("#title").val()
								: ''
					}, {
						name : 'wfNo',
						value : ($("#wfNo").length > 0) ? $("#wfNo").val() : ''
					},
					{
						name : 'wfRisk.riskCategory',
						value : ($("#riskCategory").length > 0) ? $("#riskCategory").val() : ''
					},
					{
						name : 'wfRisk.deptName',
						value : ($("#deptName").length > 0) ? $("#deptName").val() : ''
					},
					{
						name : 'wfRisk.riskConsequence',
						value : ($("#riskConsequence").length > 0) ? $("#riskConsequence").val() : ''
					},
					{
						name : 'wfRisk.riskMonitor',
						value : ($("#riskMonitor").length > 0) ? $("#riskMonitor").val() : ''
					}]
		});
		gridManager.loadData();
	}
}
// 启动风险流程
function goRisk() {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择风险记录。');
		return false;
	}
	if(document.getElementById("gpName").value.indexOf('项目经理') <= -1) {
		for(var i=0;i<selectRow.length;i++) {
			if(selectRow[i].responsibleUserId.indexOf($("#sys_usrid").val()) <= -1) {
				$.ligerDialog.warn('当前用户必须是责任人才能执行此操作！');
				return;
			}
		}
   	}
	
	for(var i=0;i<selectRow.length;i++) {
		if(selectRow[i].status == 2) {
			$.ligerDialog.warn('不能重复启动！');
			return;
		}
	}
	
	$.ligerDialog.confirm('确定要启动风险流程？', function(type) {
		if (type) {
			$.post("WfRisk!goRisk.shtml", {
				'riskIds' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}
//作废风险
function invalidRisk() {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择需要作废的风险记录。');
		return false;
	}
	for(var i=0;i<selectRow.length;i++) {
		
		if(document.getElementById("gpName").value.indexOf('项目经理') <= -1) {
			if(selectRow[i].createUserId != $("#sys_usrid").val()) {
				$.ligerDialog.warn('只能作废自己提出的风险！');
				return;
			}
		}
		if(selectRow[i].status != 1) {
			$.ligerDialog.warn('只能作废未评估的风险！');
			return;
		}
	}
	$.ligerDialog.confirm('确定要作废？', function(type) {
		if (type) {
			$.post("WfRisk!invalidRisk.shtml", {
				'riskIds' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}


//关闭风险
function closeRisk() {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择需要关闭的风险记录。');
		return false;
	}
	for(var i=0;i<selectRow.length;i++) {
		
		if(document.getElementById("gpName").value.indexOf('DQA') <= -1) {
			
			if(selectRow[i].createUserId != $("#sys_usrid").val()) {
				$.ligerDialog.warn('只能作废自己提出的风险！');
				return;
			}
		}
		if(selectRow[i].status != 1) {
			$.ligerDialog.warn('只能关闭未评估的风险！');
			return;
		}
	}
	$.ligerDialog.confirm('确定要关闭？', function(type) {
		if (type) {
			$.post("WfRisk!closeRisk.shtml", {
				'riskIds' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				sea();
			}, "text");
		}
	});
}

var gridManager;
$(function() {
	if ($("#quesText").val()) {
		// var tmp = $("#quesText").val().replace(/<br\/>/g, "\n");
		// $("#quesText").val(tmp);
	}
	if ($("#result").val()) {
		var tmp = $("#result").val().replace(/<br\/>/g, "\n");
		$("#result").val(tmp);
	}
	var flag = false;
	if ($("#startCreateDate").length > 0) {
		flag = true;
	}
	$("#maingrid").ligerGrid({
		columns : [ {
			display : '提出部门',
			name : 'deptName',
			align : 'left',
			width : 100,
		}, {
			display : '风险类别',
			name : 'riskCategory',
			align : 'left',
			width : 100,
		},{
			display : '风险状态',
			name : 'status',
			align : 'left',
			width : 100,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.status == 1) {
					innerHtml += "green";
					v = "OPEN";//未评估
				} else if (row.status == 2) {
					innerHtml += "green";
					v = "OPEN";//评估中
				} else if (row.status == 3) {
					innerHtml += "green";
					v = "OPEN";//已评估
				} else if (row.status == 4) {
					innerHtml += "blue";
					v = "作废";
				}else if (row.status == 5) {
					innerHtml += "red";
					v = "CLOSE";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},{
			display : '问题描述',
			name : 'description',
			align : 'left',
			width : 250,
		},{
			display : '风险说明及后果',
			name : 'riskConsequence',
			align : 'left',
			width : 250,
		},{
			display : '拟采取措施',
			name : 'preventiveMeasures',
			align : 'left',
			width : 250,
		},{
			display : '拟导入日期',
			name : 'impTime',
			align : 'left',
			width : 100,
		},{
			display : '责任人',
			name : 'responsibleUserName',
			align : 'left',
			width : 100,
		},{
			display : '风险监控结果',
			name : 'riskMonitor',
			align : 'left',
			width : 250,
		},{
			display : '确认日期',
			name : 'executionDate',
			align : 'left',
			width : 100,
		}, {
			display : '风险编号',
			name : 'riskId',
			align : 'left',
			width : 95,
		},{
			display : '建议措施',
			name : 'riskText',
			align : 'left',
			width : 250,
			hide : true
		},{
			display : '项目名称',
			name : 'prjtNm',
			align : 'left',
			width : 120,
			hide : true,
		},
		{
			display : '项目进度ID',
			name : 'scheId',
			align : 'left',
			width : 120,
			hide : true
		}, {
			display : '风险标题',
			name : 'title',
			align : 'left',
			width : 180,
			hide : true
		}, {
			display : '风险等级',
			name : 'riskLevel',
			align : 'left',
			width : 70,
			hide : true,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.riskLevel == 1) {
					innerHtml += "#9D2602";
					v = "致命_A";
				} else if (row.riskLevel == 2) {
					innerHtml += "red";
					v = "严重_B";
				} else if (row.riskLevel == 3) {
					innerHtml += "blue";
					v = "一般_C";
				} else if (row.riskLevel == 4) {
					innerHtml += "green";
					v = "提示_D";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},{
			display : '状态',
			name : 'status',
			align : 'left',
			width : 75,
			hide : true,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.status == 1) {
					innerHtml += "#FF44AA";
					v = "风险标注";
				} else if (row.status == 2) {
					innerHtml += "#CC0000";
					v = "走风险";
				} else if (row.status == 3) {
					innerHtml += "green";
					v = "已关闭";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '创建人ID',
			name : 'createUserId',
			align : 'left',
			width : 70,
			hide : true
		}, {
			display : '责任人ID',
			name : 'responsibleUserId',
			align : 'left',
			width : 100,
			hide : true
		}, {
			display : '工作流名称',
			name : 'wfName',
			align : 'left',
			width : 280,
			hide : true
		} ],
		checkbox : true,
		rownumbers : true,
		pageSize : 20,
		url : './WfRisk!riskList.shtml',
		usePager : true,
		delayLoad : true, // 初始化不加载数据
		width : '99.5%',
		height : '98%',
		isChecked : f_isChecked,
		onCheckRow : f_onCheckRow,
		onCheckRow : f_onCheckRow,
		onDblClickRow : f_onDblClickRow,
		onCheckAllRow : f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	sea();
})

function reloadGrid() {
	gridManager.loadData();
}

function sav() {
	$("form").submit();
}
/**
 * 保存风险记录
 */
function saver() {
	$.ligerDialog.confirm('确定要保存风险记录？',function(type) {
		if (type) {
			var str = '{';
			if ($("#prjtNo").length > 0) {
				str += '"wfRisk.prjtNo":"'
						+ prjtNomanager.getValue() + '",';
			} else {
				str += '"wfRisk.prjtNo":"'
						+ $("#wfRisk_prjtNo").val() + '",';
			}
			if ($("#riskCategory").length > 0) {
				str += '"wfRisk.riskCategory":"' + $("#riskCategory").val()
						+ '",';
			}
			if ($("#executionDate").length > 0) {
				str += '"wfRisk.executionDate":"' + $("#executionDate").val()
						+ '",';
			}
			if ($("#deptName").length > 0) {
				str += '"wfRisk.deptName":"' + $("#deptName").val()
						+ '",';
			}
			if ($("#description").length > 0) {
				str += '"wfRisk.description":"'
						+ ($("#description").val()).replace(
								/\n/g, "").replace(/"/g, "'")
						+ '",';
			}
			if ($("#responsibleUID").length > 0) {
				str += '"responsibleUID":"'
						+ $("#responsibleUID").val() + '",';
			}
			if ($("#responsibleUserName").length > 0) {
				str += '"wfRisk.responsibleUserName":"'
						+ $("#responsibleUserName")
								.val() + '"';
			}
			str += '}';
			$.post("WfRisk!saveRisk.shtml",
				eval('(' + str + ')'),
				function(data) {
					var s = data.split(":");
					if (s[0].indexOf("完成") > -1) {
						$("#riskId").val(s[1].trim());
						document.getElementById("savBtn").style.display = "none";
						document.getElementById("closeBtn").style.display = "";
						document.getElementById("uploadBut").disabled = false;
						document.getElementById("uploadBut").className = "wfbigbtn2";
					}
					$.ligerDialog.success(s[0]);
					if(window.parent.parent.riskWinow){
						window.parent.parent.riskWinow.frame.gridManager.loadData();
						document.getElementById("savBtn").style.display = "none";
						document.getElementById("closeBtn").style.display = "";
						document.getElementById("uploadBut").disabled = false;
						document.getElementById("uploadBut").className = "wfbigbtn2";
					}
					if(window.parent.riskWinow){
						window.parent.riskWinow.frame.gridManager.loadData();
						document.getElementById("savBtn").style.display = "none";
						document.getElementById("closeBtn").style.display = "";
						document.getElementById("uploadBut").disabled = false;
						document.getElementById("uploadBut").className = "wfbigbtn2";
					}
				}, "text");
		}
	});
}
/**
 * 导出风险
 */
function exportRisk() {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length == 0) {
		var str = '';
		
		str += "wfRisk.prjtNo="+ $("#prjtNo").val();
		str += "&riskCategory=" + $("#riskCategory").val();
		str += "&responsibleUserName="+ $("#responsibleUserName").val();
		str += "&deptName="+ $("#deptName").val();
		if($("#wfNo").length > 0 && $("#wfNo").val() != '') {
			str += "&wfNo="+ $("#wfNo").val();
		}
		var url = "WfRisk!export.shtml?" + str;
		window.location = url;
	}else {
		$.ligerDialog.confirm('确定要导出记录？', function(type) {
			if (type) {
				window.location = "WfRisk!export.shtml?choices="
						+ checkedCustomer.join(',');
			}
		});
	}
}

/**
 * 导入新风险或导入已有风险
 */
function impRisk() {
	var prjtNo = $("#prjtNo").val();
	$.ligerDialog.confirm('确定要导入风险？', function(type) {
		if (type) {
			var dialog = $.ligerDialog.open({
				title : '对话框：文件导入',
				height : 160,
				width : 500,
				url : './WfRisk!impRisk.shtml?prjtNo=' + prjtNo,
				buttons: [ { text: '完成', onclick: function (item, dialog) { 
					dialog.close();
					sea();
				} } ]
			});
		}
	});
}

function editorRisk() {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择需要编辑的风险记录。');
		return;
	}
	if(selectRow.length > 1) {
		$.ligerDialog.warn('只能编辑一条记录。');
		return;
	}
	if(document.getElementById("gpName").value.indexOf('DQA') <= -1) {
		//alert(document.getElementById("gpName").value);
	if(selectRow[0].createUserId != $("#sys_usrid").val()) {
		$.ligerDialog.warn('只能编辑自己提出的风险');
		return;
	}
	}
	DialogMgr.create('编辑对话框：WfRisk', './WfRisk!editorRiskUI.shtml?wfRisk.riskId=' + selectRow[0].riskId);
}

// ===============================下拉输入选择框=================================
var oldParam;

function showtips(from, to) {
	var param = document.getElementById(from).value;
	var sel = document.getElementById(to);
	var j = 0;

	if (param != null && param != ' ') {
		if (param.length > 0 && param.length < 5 && oldParam != param) {

			$.ajax({
				type : "post",
				url : "Usr!selectUser.shtml?usr.usrName="
						+ encodeURI(encodeURI(param)),
				dataType : "json",
				success : function fun1(jsonData) {
					$(".op_id").remove();
					$.each(jsonData, function(i, item) {
						var dept = "";
						if (item.deptNm != null && item.deptNm != 'undefined'
								&& item.deptNm.length > 0) {
							dept = " -- " + item.deptNm;
						}
						$("#" + to).append(
								"<option value='" + item.id
										+ "' class='op_id'>" + item.usrName
										+ dept + "</option>");
						j++;
					});

					if (jsonData.length > 0) {
						sel.size = (j > 1) ? j : 2;
						sel.size = (sel.size > 17) ? 17 : sel.size;
						sel.style.display = '';
					} else {
						c();
					}
				}
			});

		}
	} else {
		c();
	}
	oldParam = param;
}

function enterTips(sel_ipt) {
	e = event.keyCode;
	var sel = document.getElementById(sel_ipt);
	if (sel.style.display != 'none') {
		if (e == 13)
			event.srcElement.value = sel.value, sel.style.display = 'none';
		if (e == 40)
			sel.focus();
	}
}
function rv(sel, txt) {
	var sel = document.getElementById(sel);
	var txt = document.getElementById(txt);
	oldParam = sel.options[sel.selectedIndex].text;
	txt.value = oldParam.substring(0, oldParam.indexOf(' -- '));
	c(sel);
}
function c(sel_ipt) {
	var sel = document.getElementById(sel_ipt);
	if (sel != null) {
		sel.style.display = 'none';
	}
}
document.onclick = function() {
	c('sel');
	c('sel2');
}

function getSelRes(sel, id) {
	$('#' + id).val($('#' + sel).val());
}

function blurDataClear(dom, hideId) {
	if (dom.value == null || dom.value == '') {
		$('#' + hideId).val('');
	}
}
