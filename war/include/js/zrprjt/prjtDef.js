function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['prjtNo']);
		else
			removeCheckedCustomer(this.records[rowid]['prjtNo']);
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
	/*if (findCheckedCustomer(rowdata.typId) == -1)
		return false;
	return true;*/
	return false;
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


function showtips(from,to){
	var param = document.getElementById(from).value;
	var thisEle = document.getElementById(to);
	var $thisEle = $(thisEle);
	
	
	if (!(param!=null && param.trim()!='' && param.length>0 && param.length<5)) {
		return;
	}
	
	$.ajax({
	       type:"post",  
	       url:"Usr!selectUser.shtml?usr.usrName="+encodeURI(encodeURI(param)),
	       dataType:"json",
	       success: function fun1(jsonData) {  
	   			$thisEle.empty();
	   			$.each(jsonData,function(i,item){
					var dept = "";
					if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
						dept = " -- " + item.deptNm;
					}
					$thisEle.append("<option value='"+item.id+"'>" + item.usrName + dept+"</option>");  
				});
	   			//alert("showtips");
	       }
	});
}



function res() {
	if ($("#typId").length > 0)
		$("#typId").val("");
	if ($("#leve").length > 0)
		$("#leve").val("");
	if ($("#scope").length > 0)
		$("#scope").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#startPlanStaDate").length > 0)
		$("#startPlanStaDate").val("");
	if ($("#endPlanStaDate").length > 0)
		$("#endPlanStaDate").val("");
	if ($("#planStaDate").length > 0)
		$("#planStaDate").val("");
	if ($("#startPlanOveDate").length > 0)
		$("#startPlanOveDate").val("");
	if ($("#endPlanOveDate").length > 0)
		$("#endPlanOveDate").val("");
	if ($("#planOveDate").length > 0)
		$("#planOveDate").val("");
	if ($("#startStaDate").length > 0)
		$("#startStaDate").val("");
	if ($("#endStaDate").length > 0)
		$("#endStaDate").val("");
	if ($("#staDate").length > 0)
		$("#staDate").val("");
	if ($("#startOveDate").length > 0)
		$("#startOveDate").val("");
	if ($("#endOveDate").length > 0)
		$("#endOveDate").val("");
	if ($("#oveDate").length > 0)
		$("#oveDate").val("");
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
	if ($("#prjtNo").length > 0)
		$("#prjtNo").val("");
	if ($("#prjtNm").length > 0)
		$("#prjtNm").val("");
	if ($("#remark").length > 0)
		$("#remark").val("");
	if ($("#perce").length > 0)
		$("#perce").val("");

}
function add() {
	$.ligerDialog.open({title:'新增对话框：PrjtDef', height:300, width: 580,url: './PrjtDef!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'编辑对话框：PrjtDef', height: 300, width: 580,url: './PrjtDef!edit.shtml?prjtDef.prjtNo='+data.prjtNo,showMax: true, showToggle: true, showMin: true, isResize: true});
}

//var viewDig;
function vie(data) {
	//viewDig = $.ligerDialog.open({title:'查看对话框：PrjtDef', height: 560, width: 780,url: './PrjtDef!view.shtml?prjtDef.prjtNo='+data.prjtNo,showMax: true, showToggle: true, showMin: true, isResize: true});
	// d.max();
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'prjtDef.typId', value: $("#typId").val()},
			{name: 'prjtDef.leve', value: $("#leve").val()},
			{name: 'prjtDef.scope', value: $("#scope").val()},
			{name: 'prjtDef.status', value: $("#status").val()},
			/**{name: 'prjtDef.createBy', value: $("#createBy").val()},
			{name: 'prjtDef.lastUpd', value: $("#lastUpd").val()},
			{name: 'prjtDef.startPlanStaDate', value: $("#startPlanStaDate").val()},
			{name: 'prjtDef.endPlanStaDate', value: $("#endPlanStaDate").val()},
			{name: 'prjtDef.startPlanOveDate', value: $("#startPlanOveDate").val()},
			{name: 'prjtDef.endPlanOveDate', value: $("#endPlanOveDate").val()},
			{name: 'prjtDef.startStaDate', value: $("#startStaDate").val()},
			{name: 'prjtDef.endStaDate', value: $("#endStaDate").val()},
			{name: 'prjtDef.startOveDate', value: $("#startOveDate").val()},
			{name: 'prjtDef.endOveDate', value: $("#endOveDate").val()},
			{name: 'prjtDef.startCreateDate', value: $("#startCreateDate").val()},
			{name: 'prjtDef.endCreateDate', value: $("#endCreateDate").val()},
			{name: 'prjtDef.startLastDate', value: $("#startLastDate").val()},
			{name: 'prjtDef.endLastDate', value: $("#endLastDate").val()},
			{name: 'prjtDef.perce', value: $("#perce").val()}
			{name: 'prjtDef.remark', value: $("#remark").val()},
			**/
			{name: 'prjtDef.prjtNo', value: $("#prjtNo").val()},
			{name: 'prjtDef.prjtNm', value: $("#prjtNm").val()}

		]
	});
	gridManager.loadData();

}

function sav() {
	//$("form").submit();
	if(checknull()){
		saver();
		/**
		$.ligerDialog.confirm('确定要保存记录？', 
				function (type) { 
			if(type) {
				$("#prjtForm").action="PrjtDef!sav.shtml";
				$("#prjtForm").submit();
				
			}
		}
		
		);
		**/
	}
}


function checknull() {
	
		if($("#typId").val()=="") {
			alert("项目分类不能为空。");
			$("#typId").focus();
			return false;
		}
	
	
	if($("#prjtNm").val()=="") {
		alert("项目名称不能为空。");
		$("#prjtNm").focus();
		return false;
	}
	
	if($("#prjtNo").length > 0){
	}else{
			
		if($("#prjtDefDocFile").val()=="") {
			alert("产品定义书附件不能为空。");
			$("#prjtDefDocFile").focus();
			return false;
		}
	}
	return true;
}

function saver() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				
				var str = '{';
				str += '"prjtDef.typId":"'+$("#typId").val()+'",';
				if($("#status").length > 0){
				    str += '"prjtDef.status":"'+$("#status").val()+'",';
				}
				
				if($("#devDeptNameID").length > 0){
				    str += '"prjtDef.devDeptNameID":"'+$("#devDeptNameID").val()+'",';
				}
				
				
				if($("#prjtNo").length > 0){
					str += '"prjtDef.prjtNo":"'+$("#prjtNo").val()+'",';
				}
				str += '"prjtDef.prjtNm":"'+$("#prjtNm").val()+'",';
				str += '"prjtDef.remark":"'+$("#remark").val()+'"';
				str += '}';
				$.post("PrjtDef!sav.shtml",
					JSON.parse(str),
					function(data) {
						/*if(data.msg.indexOf("请上传产品定义书")>0){
							window.parent.parent.reloadPjtInfo();
							window.location = "./PrjtDef!upPrjtDefFile.shtml?prjtDef.prjtNo="+data.prjtNo;
						}else{
							$.ligerDialog.success(data.msg);
						}*/
				    	window.parent.parent.reloadPjtInfo();
						//window.parent.parent.frames["createPrjt"].location="PrjtDef.shtml";
				    	window.parent.gridManager.loadData();
				    	$.ligerDialog.success(data.msg);
					},
					"json"
				);
			}
		}
	);
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
           // saver();
        }
    });
    
   // $("form").ligerForm();
 
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
				$.post("PrjtDef!voi.shtml",
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
				$.post("PrjtDef!del.shtml",
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
				$.post("PrjtDef!sub.shtml",
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
				$.post("PrjtDef!can.shtml",
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
				$.post("PrjtDef!chk.shtml",
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
				$.post("PrjtDef!rev.shtml",
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
				$.post("PrjtDef!con.shtml",
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
				$.post("PrjtDef!sta.shtml",
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
				$.post("PrjtDef!stp.shtml",
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
				$.post("PrjtDef!ove.shtml",
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
				$.post("PrjtDef!iss.shtml",
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
				$("#form1").attr("action","PrjtDef!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './PrjtDef!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：PrjtDef', height: 560, width: 780,url: './PrjtDef!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","PrjtDef!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}