function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['replyId']);
		else
			removeCheckedCustomer(this.records[rowid]['replyId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(replyId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == replyId) return i;
	}
	return -1;
}
function addCheckedCustomer(replyId) {
	if(findCheckedCustomer(replyId) == -1)
		checkedCustomer.push(replyId);
}
function removeCheckedCustomer(replyId){
	var i = findCheckedCustomer(replyId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.replyId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.replyId);
	else
		removeCheckedCustomer(data.replyId);
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
	if ($("#replyId").length > 0)
		$("#replyId").val("");
	if ($("#quesId").length > 0)
		$("#quesId").val("");
	if ($("#userId").length > 0)
		$("#userId").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#replyText").length > 0)
		$("#replyText").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：WfReply', height: 560, width: 780,url: './WfReply!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：WfReply', height: 560, width: 780,url: './WfReply!edit.shtml?wfReply.replyId='+data.replyId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：WfReply', height: 560, width: 780,url: './WfReply!view.shtml?wfReply.replyId='+data.replyId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfReply.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'wfReply.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'wfReply.startLastUpdDate', value: $("#startLastUpdDate").val()},
			{name: 'wfReply.endLastUpdDate', value: $("#endLastUpdDate").val()},
			{name: 'wfReply.replyId', value: $("#replyId").val()},
			{name: 'wfReply.quesId', value: $("#quesId").val()},
			{name: 'wfReply.userId', value: $("#userId").val()},
			{name: 'wfReply.status', value: $("#status").val()},
			{name: 'wfReply.createBy', value: $("#createBy").val()},
			{name: 'wfReply.lastUpd', value: $("#lastUpd").val()},
			{name: 'wfReply.replyText', value: $("#replyText").val()}

		]
	});
	gridManager.loadData();
}
function sav() {
	// alert("sav");
	 $("form").submit();
}
$(function(){
//	if($("#replyText").val()) {
//		 var tmp = $("#replyText").val().replace(/<br\/>/g, "\n");
//		 $("#replyText").val(tmp);
//	}
//	if($("#result").val()) {
//		 var tmp = $("#result").val().replace(/<br\/>/g, "\n");
//		 $("#result").val(tmp);
//	}
//	if($("#idtfRes").val()) {
//		 var tmp = $("#idtfRes").val().replace(/<br\/>/g, "\n");
//		 $("#idtfRes").val(tmp);
//	}
})

String.prototype.Trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
} 

function dealQues(id) {
	var v = $('#' + id).val();
	if(v != null) {
		var tmp = v.Trim();
		if(tmp == '') {
			$.ligerDialog.warn('内容不能为空。');
			return;
		}
	} else {
		$.ligerDialog.warn('内容不能为空。');
			return;
	}
	$.ligerDialog.confirm('确定要进行此操作？', 
		function (type) { 
			if(type) {
				var str = '{';
				str += '"wfQues.quesId":"'+$("#quesId").val()+'",';
				str += '"wfQues.'+id+'":"'+$("#"+id).val().replace(/\n/g, "<br\/>")+'"';
				str += '}';
				$.post("WfQues!sav.shtml",
					JSON.parse(str),
					function(data) {
						if(window.parent.savDeal) {
							window.parent.savDeal(data);
						} else {
							$.ligerDialog.success(data);
							if(window.parent.reloadGrid) {
								window.parent.reloadGrid();
							}
						}
					},
					"text"
				);
			}
		}
	);
}
function saver() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
		   // alert(document.getElementById("s1").checked);
		  //  alert($("#s2").val());
			if(type) {
				var str = '{';
				if($("#s1").length > 0){
					if(document.getElementById("s1").checked){//通过
						str += '"wfQues.status":"'+11+'",';
					}else if(document.getElementById("s2").checked){//不通过
						str += '"wfQues.status":"'+9+'",';
					}else if(document.getElementById("s3").checked){//挂起
						str += '"wfQues.status":"'+21+'",';
					}
				}else{//变成已回复
					str += '"wfQues.status":"'+10+'",';
				}
				
				str += '"wfQues.userId":"'+$("#wfQues_userId").val()+'",';
				str += '"wfQues.idtfBy":"'+$("#wfQues_idtfBy").val()+'",';
				if($("#reload").val()==1){
					if($("#ResultFile").length > 0){//上传的是解决人的附件
						if($("#resultFileNo").val().length > 0){
							str += '"wfReply.fileNo":"'+$("#resultFileNo").val()+'",';
							str += '"wfReply.fileName":"'+$("#resultFileName").val()+'",';
						}
					}else{//上传的是验证人的附件
						if($("#idtfResFileNo").val().length > 0){
							str += '"wfReply.fileNo":"'+$("#idtfResFileNo").val()+'",';
							str += '"wfReply.fileName":"'+$("#idtfResFileName").val()+'",';
						}
					}
				}
				str += '"wfReply.replyId":"'+$("#replyId").val()+'",';
				str += '"wfReply.quesId":"'+$("#quesId").val()+'",';
//				str += '"wfReply.userId":"'+$("#userId").val()+'",';
				//wfRepLyType 判定当前是验证，还是给出解决措施
				str += '"wfRepLyType":"'+$("#wfRepLyType").val()+'",';
				
				str += '"wfReply.replyText":"'+$("#replyText").val().replace(/\n/g, "").replace(/"/g, "'")+'"';
				str += '}';
				//alert(str);
				$.post("WfReply!sav.shtml",
					eval('('+str+')'),
					function(data) {
						if(window.parent.savDeal) {
							window.parent.savDeal(data);
						} else {
							$.ligerDialog.success(data);
							if(window.parent.reloadGrid) {
								window.parent.reloadGrid();
							}
						}
					},
					"text"
				);
			}
			
			/**
			
			if(type) {
				var str = '{';
				str += '"wfQues.quesId":"'+$("#quesId").val()+'",';
				str += '"wfQues.'+id+'":"'+$("#"+id).val().replace(/\n/g, "<br\/>")+'"';
				str += '}';
				$.post("WfQues!sav.shtml",
					JSON.parse(str),
					function(data) {
						if(window.parent.savDeal) {
							window.parent.savDeal(data);
						} else {
							$.ligerDialog.success(data);
							if(window.parent.reloadGrid) {
								window.parent.reloadGrid();
							}
						}
					},
					"text"
				);
			}
			
			**/
			
			
			
			
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
				$.post("WfReply!voi.shtml",
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
				$.post("WfReply!del.shtml",
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
				$.post("WfReply!sub.shtml",
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
				$.post("WfReply!can.shtml",
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
				$.post("WfReply!chk.shtml",
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
				$.post("WfReply!rev.shtml",
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
				$.post("WfReply!con.shtml",
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
				$.post("WfReply!sta.shtml",
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
				$.post("WfReply!stp.shtml",
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
				$.post("WfReply!ove.shtml",
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
				$.post("WfReply!iss.shtml",
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
				$("#form1").attr("action","WfReply!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfReply!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfReply', height: 560, width: 780,url: './WfReply!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfReply!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}