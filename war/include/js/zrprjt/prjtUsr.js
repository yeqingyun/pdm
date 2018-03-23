/**
function addUserto(to,id) {
	if(to!=null && to.trim()!=''){
		document.getElementById("prjtUsr.usrId").value = to;
	}else{
		document.getElementById("prjtUsr.usrId").value = "";
	}
}
function addUsercc(cc,name) {
	if(cc!=null && cc.trim()!=''){
		document.getElementById("usrName").value = cc;
	}else{
		document.getElementById("usrName").value = "&nbsp;";
	}
}
**/


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
		if(checkedCustomer[i] == roleId) return i;
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
	if ($("#roleId").length > 0)
		$("#roleId").val("");
	if ($("#usrId").length > 0)
		$("#usrId").val("");
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
	if ($("#prjtNo").length > 0)
		$("#prjtNo").val("");
	if ($("#priority").length > 0)
		$("#priority").val("");
	
	

}
function add() {
	$.ligerDialog.open({title:'新增对话框：PrjtUsr', height: 560, width: 780,url: './PrjtUsr!add.shtml',showMax: true, showToggle: true, showMin: true, isResize: true});
}
function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	//alert('./PrjtUsr!edit.shtml?prjtUsr.prjtNo='+data.prjtNo+'&prjtUsr.roleId='+data.roleId+'&prjtUsr.usrId='+data.usrId);
	//$.ligerDialog.open({title:'编辑对话框：PrjtUsr', height: 560, width: 780,url: './PrjtUsr!edit.shtml?prjtUsr.prjtNo='+data.prjtNo+'&prjtUsr.roleId='+data.roleId+'&prjtUsr.usrId='+data.usrId,showMax: true, showToggle: true, showMin: true, isResize: true});
	$.ligerDialog.open({title:'编辑对话框：PrjtUsr', height: 560, width: 780,url: './PrjtUsr!edit.shtml?prjtUsr.id='+data.id,showMax: true, showToggle: true, showMin: true, isResize: true});


}
function vie(data) {
	$.ligerDialog.open({title:'查看对话框：PrjtUsr', height: 560, width: 780,url: './PrjtUsr!view.shtml?prjtUsr.id='+data.id,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'prjtUsr.roleId', value: $("#roleId").val()},
			{name: 'prjtUsr.usrName', value: $("#usrName").val()},
			{name: 'prjtUsr.status', value: $("#status").val()},
			{name: 'prjtUsr.priority', value: $("#priority").val()},
			{name: 'prjtUsr.prjtNo', value: $("#prjtNo").val()}
			

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
    	//alert("saver");
        saver();
    }
});
// $("form").ligerForm();

}
function sav() {
	//alert("submit")
	$("form").submit();
}
/*function _b()
{
if(event.keyCode ==13)
	showtips('txt','usrId');
}*/
function showtips(from,to){
	var param = document.getElementById(from).value;
	var thisEle = document.getElementById(to);
	var $thisEle = $(thisEle);
	//浏览器兼容判断
	if(navigator.appName != "Microsoft Internet Explorer"){
		if (!(param!=null && param.trim()!='' && param.length>0 && param.length<5)) {
			return;
		}
	}
	
	$.ajax({
	       type:"post",  
	       url:"Usr!selectUser.shtml?usr.usrName="+encodeURI(encodeURI(param)),
	       dataType:"json",
	       success: function fun1(jsonData) {  
	    	   //alert(JSON.stringify(jsonData.dept[0].deptNm));
	   			$thisEle.empty();
	   			$.each(jsonData.usrs,function(i,item){
					var dept = "";
						dept = " -- " + jsonData.dept[i].deptNm;
					$thisEle.append("<option value='"+item.id+"'>" + item.usrName +dept+"</option>");  
				});
	       }
	});
}

function saver() {
	$.ligerDialog.confirm('确定要保存记录？', 
		function (type) { 
			if(type) {
				var str = '{';
				if(!document.getElementById("prjtUsr.id")){//新增
					str += '"prjtUsr.prjtTypId":"'+document.getElementById("prjtUsr.prjtTypId").value+'",';
					if(document.getElementById("prjtUsr.prjtNo").value!=""){
						str += '"prjtUsr.prjtNo":"'+document.getElementById("prjtUsr.prjtNo").value+'",';
					}
					
					if(document.getElementById("roleId").value==""){
			    		alert("角色不能为空，请选择角色！");
			    		return;
			    	}else{
			    		str += '"prjtUsr.roleId":"'+document.getElementById("roleId").value+'",';
			    	}
					
					str += '"prjtUsr.priority":"'+$("#priority").val()+'",';
				}else{
					str += '"prjtUsr.oldUsrId":"'+document.getElementById("prjtUsr.oldUsrId").value+'",';
					str += '"prjtUsr.oldStatus":"'+document.getElementById("prjtUsr.oldStatus").value+'",';
					str += '"prjtUsr.id":"'+document.getElementById("prjtUsr.id").value+'",';
					
					
				}
				if(document.getElementById("usrId").value==""){
					alert("人员不能为空，请选择人员！");
		    		return;
				}else{
					str += '"prjtUsr.usrId":"'+document.getElementById("usrId").value+'",';
				}
				str += '"prjtUsr.status":"'+$("#status").ligerGetComboBoxManager().getValue()+'"';
				str += '}';

				$.post("PrjtUsr!sav.shtml",
					JSON.parse(str),
					function(data) {
						$.ligerDialog.success(data);
						if(document.getElementById("prjtUsr.roleTyp").value==1 && data.indexOf("保存记录完成") > 0){
			        		var x=document.getElementById("roleId");
			        		x.remove(x.selectedIndex);
			        		
			        	}
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
				$.post("PrjtUsr!voi.shtml",
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
				$.post("PrjtUsr!del.shtml",
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
				$.post("PrjtUsr!sub.shtml",
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
				$.post("PrjtUsr!can.shtml",
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
				$.post("PrjtUsr!chk.shtml",
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
				$.post("PrjtUsr!rev.shtml",
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
				$.post("PrjtUsr!con.shtml",
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
				$.post("PrjtUsr!sta.shtml",
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
				$.post("PrjtUsr!stp.shtml",
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
				$.post("PrjtUsr!ove.shtml",
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
				$.post("PrjtUsr!iss.shtml",
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
				$("#form1").attr("action","PrjtUsr!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './PrjtUsr!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：PrjtUsr', height: 560, width: 780,url: './PrjtUsr!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","PrjtUsr!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}