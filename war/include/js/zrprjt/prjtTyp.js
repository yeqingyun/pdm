function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['typId']);
		else
			removeCheckedCustomer(this.records[rowid]['typId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(typId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == typId) return i;
	}
	return -1;
}
function addCheckedCustomer(typId) {
	if(findCheckedCustomer(typId) == -1)
		checkedCustomer.push(typId);
}
function removeCheckedCustomer(typId){
	var i = findCheckedCustomer(typId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.typId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.typId);
	else
		removeCheckedCustomer(data.typId);
}
function f_onDblClickRow(data){
	vie(data);
}
function res() {
	if ($("#typId").length > 0)
		$("#typId").val("");
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
	if ($("#typNm").length > 0)
		$("#typNm").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：PrjtTyp', height: 560, width: 780,url: './PrjtTyp!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：PrjtTyp', height: 560, width: 780,url: './PrjtTyp!edit.shtml?prjtTyp.typId='+data.typId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：PrjtTyp', height: 560, width: 780,url: './PrjtTyp!view.shtml?prjtTyp.typId='+data.typId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			//{name: 'prjtTyp.typId', value: $("#typId").val()},
			//{name: 'prjtTyp.status', value: $("#status").val()},
			//{name: 'prjtTyp.createBy', value: $("#createBy").val()},
			//{name: 'prjtTyp.lastUpd', value: $("#lastUpd").val()},
			//{name: 'prjtTyp.startCreateDate', value: $("#startCreateDate").val()},
			//{name: 'prjtTyp.endCreateDate', value: $("#endCreateDate").val()},
			//{name: 'prjtTyp.startLastDate', value: $("#startLastDate").val()},
			//{name: 'prjtTyp.endLastDate', value: $("#endLastDate").val()},
			{name: 'prjtTyp.typNm', value: $("#typNm").val()}
			//{name: 'prjtTyp.remark', value: $("#remark").val()}

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
				if(document.getElementById("prjtTyp.typId")){
					str += '"prjtTyp.typId":"'+document.getElementById("prjtTyp.typId").value+'",';
				}
				str += '"prjtTyp.status":"'+$("#status").val()+'",';
				str += '"prjtTyp.typNm":"'+$("#typNm").val()+'",';
				str += '"prjtTyp.remark":"'+$("#remark").val()+'"';
				str += '}';

				$.post("PrjtTyp!sav.shtml",
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
				$.post("PrjtTyp!voi.shtml",
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
				$.post("PrjtTyp!del.shtml",
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
				$.post("PrjtTyp!sub.shtml",
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
				$.post("PrjtTyp!can.shtml",
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
				$.post("PrjtTyp!chk.shtml",
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
				$.post("PrjtTyp!rev.shtml",
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
				$.post("PrjtTyp!con.shtml",
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
				$.post("PrjtTyp!sta.shtml",
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
				$.post("PrjtTyp!stp.shtml",
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
				$.post("PrjtTyp!ove.shtml",
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
				$.post("PrjtTyp!iss.shtml",
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
				$("#form1").attr("action","PrjtTyp!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './PrjtTyp!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：PrjtTyp', height: 560, width: 780,url: './PrjtTyp!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","PrjtTyp!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}