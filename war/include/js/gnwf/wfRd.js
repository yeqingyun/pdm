function f_onCheckAllRow(checked) {
	for (var rowid in this.records) {
		if(checked)
			addCheckedCustomer(this.records[rowid]['wfNo']);
		else
			removeCheckedCustomer(this.records[rowid]['wfNo']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(wfNo) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == wfNo) return i;
	}
	return -1;
}
function addCheckedCustomer(wfNo) {
	if(findCheckedCustomer(wfNo) == -1)
		checkedCustomer.push(wfNo);
}
function removeCheckedCustomer(wfNo){
	var i = findCheckedCustomer(wfNo);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.wfNo) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked)
		addCheckedCustomer(data.wfNo);
	else
		removeCheckedCustomer(data.wfNo);
}
function f_onDblClickRow(data){
	wfTaskView(data.wfNo,data.taskId,data.taskStepId);
	//wfRdView2(data.wfNo);
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
	if ($("#startPlanSDate").length > 0)
		$("#startPlanSDate").val("");
	if ($("#endPlanSDate").length > 0)
		$("#endPlanSDate").val("");
	if ($("#planSDate").length > 0)
		$("#planSDate").val("");
	if ($("#startPlanEDate").length > 0)
		$("#startPlanEDate").val("");
	if ($("#endPlanEDate").length > 0)
		$("#endPlanEDate").val("");
	if ($("#planEDate").length > 0)
		$("#planEDate").val("");
	if ($("#startFactSDate").length > 0)
		$("#startFactSDate").val("");
	if ($("#endFactSDate").length > 0)
		$("#endFactSDate").val("");
	if ($("#factSDate").length > 0)
		$("#factSDate").val("");
	if ($("#startFactEDate").length > 0)
		$("#startFactEDate").val("");
	if ($("#endFactEDate").length > 0)
		$("#endFactEDate").val("");
	if ($("#factEDate").length > 0)
		$("#factEDate").val("");
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
	if ($("#scheId").length > 0)
		$("#scheId").val("");
	if ($("#flowId").length > 0)
		$("#flowId").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#wfName").length > 0)
		$("#wfName").val("");
	if ($("#wfDesc").length > 0)
		$("#wfDesc").val("");
	if ($("#wfNo").length > 0)
		$("#wfNo").val("");
	if ($("#projectNo").length > 0)
		$("#projectNo").val("");
	if ($("#preWfNo").length > 0)
		$("#preWfNo").val("");

}

function add() {
	var flowId = $("#flowId").val();
	//$.ligerDialog.open({title:'新建流程', height: 560, width: 780,url: './WfRd!add.shtml?wfRd.flowId='+flowId,showMax: true, showToggle: true, showMin: true, isResize: true});
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
				"./WfRd!add.shtml?wfRd.flowId="+flowId,
				"新建流程",
				"./include/images/Alien Folder.png"
			);
		}
	}
}

function edi() {
	var data = gridManager.getSelected();
	if(!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	$.ligerDialog.open({title:'流程更改：'+data.wfNo, height: 560, width: 780,url: './WfRd!edit.shtml?wfRd.wfNo='+data.wfNo,showMax: true, showToggle: true, showMin: true, isResize: true});
}
function vie(data) {
	var dialog = $.ligerDialog.open({title:'工作流：'+data.wfNo, height: 560, width: 780,url: './WfRdView.shtml?wfRd.wfNo='+data.wfNo,showMax: true, showToggle: true, showMin: true, isResize: true});
	dialog.max();
}

function wfRdView2(wfNo) {
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
				//"./WfRdView.shtml?wfRd.wfNo="+wfNo,
			    "./WfRdView!wfRdView.shtml?wfRd.wfNo="+wfNo,
				"工作流"+wfNo,
				"./include/images/Alien Folder.png"
			);
		}
	}
}

//selectType
function wfTaskView(wfNo,taskId,taskStepId) {
	//alert("./WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfNo+"&taskStepId="+stepId);
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
				//"./WfRdView.shtml?wfRd.wfNo="+wfNo,
			    "./WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfNo+"&currentTaskId="+taskId+"&taskStepId="+taskStepId,
				"工作流"+wfNo,
				"./include/images/Alien Folder.png"
			);
		}
	}
}

//进入编辑页面http://localhost:8080/zrprjt/WfRd!edit.shtml?wfRd.wfNo=B5614B00001
function wfEditTaskView(wfNo,taskId,taskStepId) {
	//alert("./WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfNo+"&taskStepId="+stepId);
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
				//"./WfRdView.shtml?wfRd.wfNo="+wfNo,
			    "./WfRd!edit.shtml?wfRd.wfNo="+wfNo
			);
		}
	}
}


function select(type){
	$("#selectType").val(type);
	sea();
}

function sea() {
	gridManager.setOptions({
		parms: [
			{name: 'wfRd.startCreateDate', value: getVal("startCreateDate")},
			{name: 'wfRd.endCreateDate', value: getVal("endCreateDate")},
			{name: 'wfRd.status', value: getVal("status")},
			{name: 'wfRd.createName', value: getVal("createName")},
			{name: 'wfRd.wfName', value: getVal("wfName")},
			{name: 'wfRd.wfNo', value: getVal("wfNo")},
			{name: 'wfRd.flowId', value: getVal("flowId")},
			{name: 'wfRd.projectNo', value: getVal("projectNo")},
			{name: 'wfRd.selectType', value: getVal("selectType")}
		]
	});
	gridManager.loadData();
}
function selectWfRd() {
	gridManager.setOptions({
		parms: [
			{name: 'wfRd.flowId', value: getVal("flowId")},
			{name: 'wfRd.projectNo', value: getVal("projectNo")},
			{name: 'wfRd.selectType', value: getVal("selectType")}
		]
	});
	gridManager.loadData();
}

function collapseAll(){
	treeManager.collapseAll();
}

function sav() {
	 $("form").submit();
}
function saver() {
	if(!($("#wfNo").length>0)){
		document.getElementById("wfRd-form").action="WfRd!sav.shtml";
		var isForFlow = $("#isForFlow").val();
		if(isForFlow == "1"){
			$("#wfRd-form").ajaxSubmit({
				success:function(data){
					 window.parent.postMessage(JSON.stringify(data),"*");
				}
			});
		}else{
			document.getElementById("wfRd-form").submit();
		}
	}else{
		$.ligerDialog.confirm('确定要保存记录？', 
			function (type) {
				if(type) {
					var str = '{';saver
					str += '"wfRd.wfName":"'+$("#wfName").val()+'",';
					str += '"wfRd.wfDesc":"'+$("#wfDesc").val()+'",';
					str += '"wfRd.planSDate":"'+$("#planSDate").val()+'",';
					str += '"wfRd.planEDate":"'+$("#planEDate").val()+'",';
					//str += '"wfRd.flowId":"'+$("#flowId").val()+'"';
					str += '"usrIds":"'+$("#usrIds").val()+'"';
					

					if($("#wfNo").length>0){
						str += ',"wfRd.wfNo":"'+$("#wfNo").val()+'"';
						if($("#preWfNo").val()!=null && $("#preWfNo").val()!='undefined'){
							str += ',"wfRd.preWfNo":"'+$("#preWfNo").val()+'"';
						}
						if($("#status").val()!=null && $("#status").val()!='undefined'){
							str += ',"wfRd.status":"'+$("#status").val()+'"';
						}
					}
					str += '}';
	
					$.post("WfRd!sav.shtml",
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
				$.post("WfRd!voi.shtml",
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
				$.post("WfRd!del.shtml",
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
				$.post("WfRd!sub.shtml",
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
				$.post("WfRd!can.shtml",
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
				$.post("WfRd!chk.shtml",
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
				$.post("WfRd!rev.shtml",
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
				$.post("WfRd!con.shtml",
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
				$.post("WfRd!sta.shtml",
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
				$.post("WfRd!stp.shtml",
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
				$.post("WfRd!ove.shtml",
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
				$.post("WfRd!iss.shtml",
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
				$("#form1").attr("action","WfRd!exp.shtml");
				$("#form1").submit();
			}
		}
	);
}
function imp() {
	parent.$.ligerDialog.confirm('确定要导入记录？', 
		function (type) { 
			if(type) {
				parent.$.ligerDialog.open({title:'对话框：文件导入', height: 160, width: 500,url: './WfRd!imp.shtml'});
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
				$.ligerDialog.open({title:'打印对话框：WfRd', height: 560, width: 780,url: './WfRd!prn.shtml?choices='+checkedCustomer.join(',')});
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
				$("#form1").attr("action","WfRd!dow.shtml");
				$("#form1").submit();
			}
		}
	);
}

function acceptJob(){		//接收任务
	document.getElementById("wfRd-form").action="WfRdView!accept.shtml";
	document.getElementById("wfRd-form").target="_self";
	document.getElementById("wfRd-form").submit();
}

function showTips(){	//显示提示信息
	var tips = document.getElementById("tips");
	if(tips!=null && tips.value.trim()!=''){
		$("#tips").val("");
		alert(tips.value);
	}
}

function restartWfinfo() {		//重启本流程
	if(confirm("您确定重启本流程吗？ ")) {
		var form = document.getElementById("wfRd-form");
		form.target="_self";
		form.action="WfRdView!restartWfinfo.shtml";
		form.submit();
	}
}

String.prototype.trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}

function saveJob() {		//暂存任务
	//alert("saveJob进入");
	//if(saveCheck()){
		//alert("saveCheck进入");
		var form = document.getElementById("wfRd-form");
		form.target="_self";
		form.action="WfRdView!saveJob.shtml";
		//alert("WfRdView!saveJob.shtml进入");
		var isForFlow = $("#isForFlow").val();
		if(isForFlow == "1"){
			$("#wfRd-form").ajaxSubmit({
				success:function(data){
					 window.parent.postMessage(JSON.stringify(data),"*");
				}
			});
		}else{
			form.submit();
		}
	//}
}
function saveCheck() {
	var str = document.getElementById("notNullStr");
	//alert("str"+str);
	if(str!=null && str.value!=null && str.value.trim()!=''){
		var s = str.value.split(",");
		//alert("s"+s);
		for(var i=0;i<(s.length-1);i++){
			var field = document.getElementById("fieldContents["+s[i]+"].fieldText");
			alert(field);
			if(field!=null && field.value.trim()==''){
				alert("表单没有填写完整。。。");
				return false;
			}
		}
	}
	return true;
}

function selectStep(stepId,stepType) {
	var select = document.getElementById('nextSteps['+stepId+'].stepId');
	var count = document.getElementById('n').value;
	
	if(select.checked==true){	//选中
		if(stepType==2){		//分支任务,清除别的所有，并屏蔽
			for(var i=0;i<count;i++){
				var s = document.getElementById('nextSteps['+i+'].stepId');
				if(s!=null){
					s.checked=false;
					//s.disabled="disabled"
				}
			}
			select.checked=true;
			//select.disabled=""
		}
	}else{
//		if(stepType==2){
//			for(var i=0;i<count;i++){
//				var s = document.getElementById('nextSteps['+i+'].stepId');
//				if(s!=null){
//					s.disabled=""
//				}
//			}
//		}
	}
}


function checkDocSize(count){
	var flag='ture';
	var check=1;
	var taskStepId= $("#taskStepId").val();
	 $.ajaxSetup({
	        async: false
	  });
	 
	 for(var i=0;i<count.value;i++){
			var step = document.getElementById("nextSteps["+i+"].stepId");
			if(step!=null && step.checked==true){
				if(step.value<taskStepId){
					check=0;
				}
				}
			}
	if(check==1){
	$.post("WfRdView!checkDocSize.shtml",
			{"wfRd.wfNo":$("#wfNo").val(),"taskStepId":$("#taskStepId").val()},
			function(data) {
				var oj = JSON.parse(data);
				if(oj.result==0){
					alert('本流程需要上传'+oj.info+'才可进行下一步');
					flag='false';
				}
			},
			"text"
		);
	 }
	return flag;
}

function checkDcclevel(){
	var flag='true';
	var value=$("input[type='checkbox']:checked").val();
	var flagDcc=$("#flagDcc").val();
	if(flagDcc=='1'&&value==357){
		alert("物料降级需走策略工程师审核步骤!");
		flag='false';
	}
	if(flagDcc==''&&value==374){
		alert("物料没有降级不需走策略工程师审核步骤!");
		flag='false';
	}
	
	return flag;
}
var isnotselect = 0;
function nextStepPage(){	//转交到下一步,下一步骤页面
	var count = document.getElementById("n");
	var counti = document.getElementById("i");
	//alert(count.value);
	var  flag=checkDocSize(count);
	if(flag!='false'){
	if($("#stepId").val()!=null&&$("#stepId").val()==356){
		flag=checkDcclevel();
	}
	}
	
	
/*	if($("#flagDcc").val()!=null&&$("#flagDcc").val()!=''){
		alert($("#flagDcc").val());
	}*/
	
	
	if(flag=='false'){
		return;
	}
	if(count!=null && count.value>0){
		var stepCount = 0;			//计数选中几个
		var checkstepCount = 0;
		var selectpeople = 0;
		for(var i=0;i<count.value;i++){
			var step = document.getElementById("nextSteps["+i+"].stepId");
			//alert(step.checked);
			if(step!=null && step.checked==true){
				
				if(i == 0){
					stepCount++;
					//alert("userId"+(i));
					var userId = document.getElementById("userId"+(i));
					//alert("userId"+userId.value);
					if(userId!=null && (userId.value=='' || userId.value.trim()=='')){	//
						alert("主办人不能为空哦。。");
						selectpeople = 1;
					}
				}else{
					var j = i*2;
					stepCount++;
					//alert("userId"+(i));
					var userId = document.getElementById("userId"+(j));
					//alert("userId"+userId.value);
					if(userId!=null && (userId.value=='' || userId.value.trim()=='')){	//
						alert("主办人不能为空哦。。");
						selectpeople = 1;
				}
				
			}
		}
		}
		if(stepCount==0){			//一个都没选
			var isFillQues = document.getElementById("isFillQues").value;
			var sort =document.getElementById("currentTask.sort").value;
			var wfRdFlowId =document.getElementById("wfRd.flowId").value;
			//alert(sort);
			//alert(wfRdFlowId);
			if(wfRdFlowId == 53 && sort ==4){//判断是否为SAP主数据流程第四步
				if ($("[type=checkbox]:checked").length == 0&&$("[type=radio]:checked").length == 0) {
					$.ligerDialog.confirm('（注：确定提交该流程会直接更新为"已完成"状态）还没有勾选下一步任务！',
					function(yes) {
						if(yes){
							isnotselect = 1;
							submitStep(1);
						}
						
					});
				}
			}else{
			if ($("[type=checkbox]:checked").length == 0&&$("[type=radio]:checked").length == 0) {
				$.ligerDialog.confirm('还没有勾选下一步任务！请勾选下一步任务！',
				function(yes) {
					if(yes){

					}
					
				});
			}
			}
		}
		if(stepCount!=0 && selectpeople ==0){
			//alert(count.value);
			for(var i=0;i<count.value;i++){
				var step1 = document.getElementById("nextSteps["+i+"].stepId");
				//alert(step1.checked);
				if(step1 !=null && step1.checked==false){
				if(i == 0){
				document.getElementById("task"+i).innerHTML='';
				document.getElementById("userId"+i).value='';
				document.getElementById("userName"+i).value='';
				var j = i +1;
				document.getElementById("task"+j).innerHTML='';
				document.getElementById("userId"+j).value='';
				document.getElementById("userName"+j).value='';
				}else{
					var j = i*2;
					document.getElementById("task"+j).innerHTML='';
					document.getElementById("userId"+j).value='';
					document.getElementById("userName"+j).value='';
					var k = j +1;
					document.getElementById("task"+k).innerHTML='';
					document.getElementById("userId"+k).value='';
					document.getElementById("userName"+k).value='';
				}
				}
			}
			submitStep(0);
		}
		
	}
	

}

function nextStepPageForSuss(){	//完成任务
var count=0;	
var  flag=checkDocSize(count);
	
	if(flag=='false'){
		return;
	}
	var isFillQues = document.getElementById("isFillQues").value;
	submitStep(0);
}

function submitStep(isnotselect){
	if(saveJobCheck()){
		//alert(isnotselect+"最终");
		//document.getElementById("wfRd-form").action="WfRdView!nextStepPage.shtml";
		if(isnotselect == 0){
			document.getElementById("wfRd-form").action="WfRdView!saveAndSendTask.shtml";
		}else{
			document.getElementById("wfRd-form").action="WfRdView!saveAndSendTaskForNextPage.shtml";
		}
		sendEmail1();
		document.getElementById("wfRd-form").target="_self";
		var isForFlow = document.getElementById("isForFlow").value;
		if(isForFlow == "1"){
			$("#wfRd-form").ajaxSubmit({
				success:function(data){
					 window.parent.postMessage(JSON.stringify(data),"*");
				}
			});
		}else{
			document.getElementById("wfRd-form").submit();
		}
	}
}

function sendEmail1() {
	if($("#usrIds").val() != null && $("#usrIds").val() != '') {
		var data = { 'wfRd.wfNo':$('#wfNo').val(),'wfRd.usrIDs':$("#usrIds").val() };	 
		$.ajax({type:"post",url:"WfRdView!sendMailForReaderNew.shtml",data:data,async:false});	
	}
	
	/*var str = '{';
	str += '"usrIds":"' + $("#usrIds").val() + '",';
	str += '"mailTitle":"' + $("#mailTitle").val() + '",';
	str += '"mailContent":"' + $("#mailContent").val() + '"';
	str += '}';*/
	//alert($("#usrIds").val());
	//alert(document.getElementById('wfRd.wfNo').value);
	/*$.post("./WfRd!sendMailForReader.shtml", {
		"usrIds" : $("#usrIds").val(),
		"mailTitle" : $("#mailTitle").val(),
		"mailContent" : $("#mailContent").val(),
		"wfRd.WfNo":document.getElementById('wfRd.wfNo').value
	}*/  /*, function(data) {
		$.ligerDialog.success(data);
		if(window.parent&&window.parent.showSendEmailWin){
			setTimeout(function (){
				window.parent.showSendEmailWin.hide();
            }, 3000);
		}
	}, "text"*/  //);
}

function saveJobCheck() {
	var str = document.getElementById("notNullStr");
	//alert(str.value);//0,1,2,3,4,6,7,8,9,10,11
	if(str!=null && str.value!=null && str.value.trim()!=''){
		var s = str.value.split(",");
		for(var i=0;i<(s.length-1);i++){
			var field = document.getElementById("fieldContents["+s[i]+"].fieldText");
			var myObj=document.getElementsByName("fieldContents["+s[i]+"].fieldText");
			//alert(i+"~~~"+field);
			if(field!=null && field.value.trim()==''){
				alert("表单没有填写完整。。。");
				showWfForms = false;
				showFroms();
				return false;
			}else if(field ==null){
				//alert(myObj.length);
				var j;
				for(j=0;j<myObj.length;j++){
				if(myObj[j].checked)break;
				};
				if(j>=myObj.length){
					alert("表单没有勾选选项。。。");
					showWfForms = false;
					showFroms();
					return false;
				} 
			}
		}
	}
	//check交付件是否都添加了
	if( $("#uploadIframe").length > 0){
		var radioObject = document.getElementById("uploadIframe").contentWindow.document.getElementsByName('docCate');
		if(radioObject!=null && radioObject.length>0){
			for (var i = 0; i < radioObject.length; i++){
				var cateId = radioObject[i].value;
				var flag = true;
				var gridFile = document.getElementById("uploadIframe").contentWindow.document.getElementById("griddoccate"+cateId)
				if(gridFile!=null){ 	//grid中已有
					flag = true;
				}
				if(flag){
					var cateName = document.getElementById("uploadIframe").contentWindow.document.getElementById("cate"+cateId).value;
					alert("还有附件《"+cateName+"》未上传!");
					return false;
				}
			}
		}
	}
	return true;
}

function checkQues(){
	var flowId = document.getElementById("wfRd.flowId").value;
	var wfNo = document.getElementById("wfRd.wfNo").value;
	jQuery.ajax({
	       type:"post",  
	       url:"WfRdView!showAjaxInfo.shtml?flowId="+flowId+"&ajaxList[0]=countQues&ajaxList[1]="+wfNo,  
	       dataType:"json",
	       success: function fun1(jsonData) {
				if (jsonData=='0') {
					if (confirm("提示：您没有填写任何问题，是否继续提交?")) {
						submitStep();
					}
				} else {
					submitStep();
				}
	       }
	});
}
function checkDQA(){
	var flowId = document.getElementById("wfRd.flowId").value;
	var wfNo = document.getElementById("wfRd.wfNo").value;
	/**
	jQuery.ajax({
	       type:"post",  
	       url:"WfRdView!showAjaxInfo.shtml?flowId="+flowId+"&ajaxList[0]=countDQA&ajaxList[1]="+wfNo,  
	       dataType:"json",
	       success: function fun1(jsonData) {
				if (jsonData=='0') {
					submitStep();
				} else {
					alert("提示：请先关闭问题列表中全部问题，再提交!");
				}
	       }
	});
	**/
	$.post(
		   "WfRdView!showAjaxInfo2.shtml?flowId="+flowId+"&ajaxList[0]=countDQA&ajaxList[1]="+wfNo,
	       function(data) {
			   if(data.indexOf("0") > -1){
			    //if (jsonData=='0') {
					submitStep();
				} else {
					alert("提示：请先关闭问题列表中全部问题，再提交!");
					showQuesList();
				}
	       },
	      "text"
   );
}

var showWfForms = false;
function showFroms(){
	//alert(showWfForms);
	if(!showWfForms){
		// alert("none");
	    document.getElementById("wfForms").style.display = "none";
		document.getElementById("showWfFormsBtn").innerHTML= "展开表单";
		showWfForms = true;
	 }else{
		// alert("show");
		    document.getElementById("wfForms").style.display = "";
			document.getElementById("showWfFormsBtn").innerHTML= "收起表单";
			showWfForms = false;
	 }
	
}

var showQues2 = false;
function showQuesList(){
	//alert(showQues2);
	 if(!showQues2){
		    document.getElementById("quesList").style.display = "none";
			document.getElementById("showQuesListBtn").innerHTML= "展开问题列表";
			//document.getElementById('quesIframe').contentWindow.reloadGrid();
			//alert("ffff");
			//frames["quesIframe"].reloadGrid();
    		window.frames["quesIframe"].reloadGrid();
    		showQues2 = true;
	 }else{
		    document.getElementById("quesList").style.display = "";
			document.getElementById("showQuesListBtn").innerHTML= "收起问题列表";
			showQues2 = false;
	 }
}


function wfRdView(){		//预览进度
	document.getElementById("wfRd-form").action="WfRdView!view.shtml";
	document.getElementById("wfRd-form").target="_blank";
	document.getElementById("wfRd-form").submit();
}

function selectDoc(){
	var sel = document.getElementById("checkDoc");
	var table = document.getElementById("docTable");
	if(sel.checked){
		table.style.display = "block";
	}else{
		table.style.display = "none";
	}
}

//阻止冒泡
function stopBubble(e)  {
	if (e && e.stopPropagation){
		e.stopPropagation();
	}else{
		window.event.cancelBubble=true;
	}
}
$(function(){
	$(".lcbtn").click(function(event){
		$(this).addClass("hover").find(".detai").show().parents("tr").siblings("tr").find(".lcbtn").removeClass("hover").find(".detai").hide();
		stopBubble(event);
	})
	$("body").click(function(){
		$(".lcbtn").removeClass("hover").find(".detai").hide();
	})
})


//设置任务代办人
function setAgentBy(){
	var taskId = document.getElementById("currentTask.taskId").value;
	if(taskId==null){
		alert("任务ID为空,不可设置");
		return;
	}
	document.getElementById("wfRd-form").action="WfRdView!setAgentByUI.shtml";
	document.getElementById("wfRd-form").submit();
}

//任务代办人选取
function addUserto(to,id) {
	if(to!=null){
		document.getElementById("agentBy").value=to;
		
		var form = document.getElementById("wfRd-form");
		form.target="_self";
		form.action="WfRdView!setAgentBy.shtml";
		form.submit();
	}
}


function wfdesc(){
	var flowId = document.getElementById("wfRd.flowId");
	if(flowId==null){
		flowId = document.getElementById("flowId");
	}
	if(flowId!=null && flowId.value!=null){
		var dialog = $.ligerDialog.open({title:'流程说明', height: 560, width: 780,url: './WfRdView!previewDesc.shtml?wfRd.flowId='+flowId.value,showMax: true, showToggle: true, showMin: true, isResize: true});
	}
}
function getVal(id){
	var v = $("#"+id+"").val();
	if(v==null)
		return '';
	return v;
}

function exportXls(){
	//alert("2222");
	document.getElementById("wfRd-form").action="WfRdView!exp1.shtml";
	//document.getElementById("wfRd-form").target="_blank";
	document.getElementById("wfRd-form").target="_self";
	document.getElementById("wfRd-form").submit();
}
//-------------------------------------------
/*function check() {
//	if (document.getElementById("wfRd.wfCode").value == "") {
//		alert("请输入wfCode内容。");
//		document.getElementById("wfRd.wfCode").focus();
//		return false;
//	}
	//alert("aaa");
	return true;
}



*/

var pop;
function selectPrjtUsers(){
	//alert("233333333333333333"+window.parent.document.getElementById("wfRd.wfNo").value);
	if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		//alert("fff");
		selectPrjtUsers2();
	}else{
		//alert("ddd");
		pop =  $.ligerDialog.open({title:'添加邮件接收人', height: 390, width: 500,url: './PrjtUsr!addMailUser.shtml',
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}

