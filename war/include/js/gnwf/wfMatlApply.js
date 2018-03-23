function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['matlId']);
		else
			removeCheckedCustomer(this.records[rowid]['matlId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(matlId) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == matlId) return i;
	}
	return -1;
}
function addCheckedCustomer(matlId) {
	if(findCheckedCustomer(matlId) == -1)
		checkedCustomer.push(matlId);
}
function removeCheckedCustomer(matlId){
	var i = findCheckedCustomer(matlId);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.matlId) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.matlId);
	else
		removeCheckedCustomer(data.matlId);
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
	if ($("#matlId").length > 0)
		$("#matlId").val("");
	if ($("#nORMT").length > 0)
		$("#nORMT").val("");
	if ($("#fERTH").length > 0)
		$("#fERTH").val("");
	if ($("#eXTWG").length > 0)
		$("#eXTWG").val("");
	if ($("#vOLUM").length > 0)
		$("#vOLUM").val("");
	if ($("#vPRSV").length > 0)
		$("#vPRSV").val("");
	if ($("#mATNR").length > 0)
		$("#mATNR").val("");
	if ($("#bSTRF").length > 0)
		$("#bSTRF").val("");
	if ($("#wERKS").length > 0)
		$("#wERKS").val("");
	if ($("#nTGEW").length > 0)
		$("#nTGEW").val("");
	if ($("#gEWEI").length > 0)
		$("#gEWEI").val("");
	if ($("#mATKL").length > 0)
		$("#mATKL").val("");
	if ($("#bSTUZ").length > 0)
		$("#bSTUZ").val("");
	if ($("#mEINS").length > 0)
		$("#mEINS").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");
	if ($("#wRKST").length > 0)
		$("#wRKST").val("");
	if ($("#dISLS").length > 0)
		$("#dISLS").val("");
	if ($("#kAUSF").length > 0)
		$("#kAUSF").val("");
	if ($("#gROES").length > 0)
		$("#gROES").val("");
	if ($("#dISPO").length > 0)
		$("#dISPO").val("");
	if ($("#mTART").length > 0)
		$("#mTART").val("");
	if ($("#bSTME").length > 0)
		$("#bSTME").val("");
	if ($("#bKLAS").length > 0)
		$("#bKLAS").val("");
	if ($("#bSTUN").length > 0)
		$("#bSTUN").val("");
	if ($("#mAKTX").length > 0)
		$("#mAKTX").val("");
	if ($("#bSTMI").length > 0)
		$("#bSTMI").val("");
	if ($("#sTPRS").length > 0)
		$("#sTPRS").val("");
	if ($("#dISMM").length > 0)
		$("#dISMM").val("");
	if ($("#mMSTA").length > 0)
		$("#mMSTA").val("");
	if ($("#pLIFZ").length > 0)
		$("#pLIFZ").val("");
	if ($("#bSTMA").length > 0)
		$("#bSTMA").val("");
	if ($("#sOBSL").length > 0)
		$("#sOBSL").val("");
	if ($("#sTAWN").length > 0)
		$("#sTAWN").val("");
	if ($("#bRGEW").length > 0)
		$("#bRGEW").val("");
	if ($("#lGFSB").length > 0)
		$("#lGFSB").val("");
	if ($("#bESKZ").length > 0)
		$("#bESKZ").val("");
	if ($("#vOLEH").length > 0)
		$("#vOLEH").val("");
	if ($("#infoId").length > 0)
		$("#infoId").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：WfMatlApply', height: 560, width: 780,url: './WfMatlApply!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：WfMatlApply', height: 560, width: 780,url: './WfMatlApply!edit.shtml?wfMatlApply.matlId='+data.matlId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：WfMatlApply', height: 560, width: 780,url: './WfMatlApply!view.shtml?wfMatlApply.matlId='+data.matlId,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfMatlApply.matlId', value: $("#matlId").val()},
			{name: 'wfMatlApply.nORMT', value: $("#nORMT").val()},
			{name: 'wfMatlApply.fERTH', value: $("#fERTH").val()},
			{name: 'wfMatlApply.eXTWG', value: $("#eXTWG").val()},
			{name: 'wfMatlApply.vOLUM', value: $("#vOLUM").val()},
			{name: 'wfMatlApply.vPRSV', value: $("#vPRSV").val()},
			{name: 'wfMatlApply.mATNR', value: $("#mATNR").val()},
			{name: 'wfMatlApply.bSTRF', value: $("#bSTRF").val()},
			{name: 'wfMatlApply.wERKS', value: $("#wERKS").val()},
			{name: 'wfMatlApply.nTGEW', value: $("#nTGEW").val()},
			{name: 'wfMatlApply.gEWEI', value: $("#gEWEI").val()},
			{name: 'wfMatlApply.mATKL', value: $("#mATKL").val()},
			{name: 'wfMatlApply.bSTUZ', value: $("#bSTUZ").val()},
			{name: 'wfMatlApply.mEINS', value: $("#mEINS").val()},
			{name: 'wfMatlApply.remark', value: $("#remark").val()},
			{name: 'wfMatlApply.wRKST', value: $("#wRKST").val()},
			{name: 'wfMatlApply.dISLS', value: $("#dISLS").val()},
			{name: 'wfMatlApply.kAUSF', value: $("#kAUSF").val()},
			{name: 'wfMatlApply.gROES', value: $("#gROES").val()},
			{name: 'wfMatlApply.dISPO', value: $("#dISPO").val()},
			{name: 'wfMatlApply.mTART', value: $("#mTART").val()},
			{name: 'wfMatlApply.bSTME', value: $("#bSTME").val()},
			{name: 'wfMatlApply.bKLAS', value: $("#bKLAS").val()},
			{name: 'wfMatlApply.bSTUN', value: $("#bSTUN").val()},
			{name: 'wfMatlApply.mAKTX', value: $("#mAKTX").val()},
			{name: 'wfMatlApply.bSTMI', value: $("#bSTMI").val()},
			{name: 'wfMatlApply.sTPRS', value: $("#sTPRS").val()},
			{name: 'wfMatlApply.dISMM', value: $("#dISMM").val()},
			{name: 'wfMatlApply.mMSTA', value: $("#mMSTA").val()},
			{name: 'wfMatlApply.pLIFZ', value: $("#pLIFZ").val()},
			{name: 'wfMatlApply.bSTMA', value: $("#bSTMA").val()},
			{name: 'wfMatlApply.sOBSL', value: $("#sOBSL").val()},
			{name: 'wfMatlApply.sTAWN', value: $("#sTAWN").val()},
			{name: 'wfMatlApply.bRGEW', value: $("#bRGEW").val()},
			{name: 'wfMatlApply.lGFSB', value: $("#lGFSB").val()},
			{name: 'wfMatlApply.bESKZ', value: $("#bESKZ").val()},
			{name: 'wfMatlApply.vOLEH', value: $("#vOLEH").val()},
			{name: 'wfMatlApply.infoId', value: $("#infoId").val()}

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
				str += '"wfMatlApply.matlId":"'+$("#matlId").val()+'",';
				str += '"wfMatlApply.nORMT":"'+$("#nORMT").val()+'",';
				str += '"wfMatlApply.fERTH":"'+$("#fERTH").val()+'",';
				str += '"wfMatlApply.eXTWG":"'+$("#eXTWG").val()+'",';
				str += '"wfMatlApply.vOLUM":"'+$("#vOLUM").val()+'",';
				str += '"wfMatlApply.vPRSV":"'+$("#vPRSV").val()+'",';
				str += '"wfMatlApply.mATNR":"'+$("#mATNR").val()+'",';
				str += '"wfMatlApply.bSTRF":"'+$("#bSTRF").val()+'",';
				str += '"wfMatlApply.wERKS":"'+$("#wERKS").val()+'",';
				str += '"wfMatlApply.nTGEW":"'+$("#nTGEW").val()+'",';
				str += '"wfMatlApply.gEWEI":"'+$("#gEWEI").val()+'",';
				str += '"wfMatlApply.mATKL":"'+$("#mATKL").val()+'",';
				str += '"wfMatlApply.bSTUZ":"'+$("#bSTUZ").val()+'",';
				str += '"wfMatlApply.mEINS":"'+$("#mEINS").val()+'",';
				str += '"wfMatlApply.remark":"'+$("#remark").val()+'",';
				str += '"wfMatlApply.wRKST":"'+$("#wRKST").val()+'",';
				str += '"wfMatlApply.dISLS":"'+$("#dISLS").val()+'",';
				str += '"wfMatlApply.kAUSF":"'+$("#kAUSF").val()+'",';
				str += '"wfMatlApply.gROES":"'+$("#gROES").val()+'",';
				str += '"wfMatlApply.dISPO":"'+$("#dISPO").val()+'",';
				str += '"wfMatlApply.mTART":"'+$("#mTART").val()+'",';
				str += '"wfMatlApply.bSTME":"'+$("#bSTME").val()+'",';
				str += '"wfMatlApply.bKLAS":"'+$("#bKLAS").val()+'",';
				str += '"wfMatlApply.bSTUN":"'+$("#bSTUN").val()+'",';
				str += '"wfMatlApply.mAKTX":"'+$("#mAKTX").val()+'",';
				str += '"wfMatlApply.bSTMI":"'+$("#bSTMI").val()+'",';
				str += '"wfMatlApply.sTPRS":"'+$("#sTPRS").val()+'",';
				str += '"wfMatlApply.dISMM":"'+$("#dISMM").val()+'",';
				str += '"wfMatlApply.mMSTA":"'+$("#mMSTA").val()+'",';
				str += '"wfMatlApply.pLIFZ":"'+$("#pLIFZ").val()+'",';
				str += '"wfMatlApply.bSTMA":"'+$("#bSTMA").val()+'",';
				str += '"wfMatlApply.sOBSL":"'+$("#sOBSL").val()+'",';
				str += '"wfMatlApply.sTAWN":"'+$("#sTAWN").val()+'",';
				str += '"wfMatlApply.bRGEW":"'+$("#bRGEW").val()+'",';
				str += '"wfMatlApply.lGFSB":"'+$("#lGFSB").val()+'",';
				str += '"wfMatlApply.bESKZ":"'+$("#bESKZ").val()+'",';
				str += '"wfMatlApply.vOLEH":"'+$("#vOLEH").val()+'",';
				str += '"wfMatlApply.infoId":"'+$("#infoId").val()+'"';
				str += '}';

				$.post("WfMatlApply!sav.shtml",
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
				$.post("WfMatlApply!voi.shtml",
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
				$.post("WfMatlApply!del.shtml",
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
				$.post("WfMatlApply!sub.shtml",
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
				$.post("WfMatlApply!can.shtml",
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
				$.post("WfMatlApply!chk.shtml",
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
				$.post("WfMatlApply!rev.shtml",
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
				$.post("WfMatlApply!con.shtml",
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
				$.post("WfMatlApply!sta.shtml",
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
				$.post("WfMatlApply!stp.shtml",
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
				$.post("WfMatlApply!ove.shtml",
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
				$.post("WfMatlApply!iss.shtml",
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
				$("#form1").attr("action","WfMatlApply!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfMatlApply!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfMatlApply', height: 560, width: 780,url: './WfMatlApply!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfMatlApply!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}