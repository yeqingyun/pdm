function addUserto(to,id) {
	if(to!=null && to.trim()!=''){
		document.getElementById(id).value = to;
	}else{
		document.getElementById(id).value = "";
	}
}
function addUsercc(cc,name) {
	if(cc!=null && cc.trim()!=''){
		document.getElementById(name).innerHTML = cc;
	}else{
		document.getElementById(name).innerHTML = "&nbsp;";
	}
}
function addTask(value,task) {
	if(value!=null && value.trim()!=''){
		document.getElementById(task).innerHTML = value;
	}else{
		document.getElementById(task).innerHTML = "";
	}
}

String.prototype.trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}

function getUserto(id) {
	return document.getElementById(id).value;
}
function getUsercc(name) {
	return document.getElementById(name).innerHTML;
}



function backJobPage(){	//返回办理页面
	document.getElementById("wfRd-form").action="WfRdView.shtml";;
	document.getElementById("wfRd-form").target="_self";
	document.getElementById("wfRd-form").submit();
}

function sendTask(){		//转交任务
	if(sendTaskCheck()){
		document.getElementById("wfRd-form").action="WfRdView!sendTask.shtml";
		document.getElementById("wfRd-form").submit();
	}
}

function sendTaskCheck(){	//转交前检查
	var count = document.getElementById("n");
	if(count!=null && count.value>0){
		var stepCount = 0;			//计数选中几个
		for(var i=0;i<count.value;i++){
			var step = document.getElementById("nextSteps["+i+"].stepId");
			if(step!=null && step.checked==true){
				stepCount++;
				var userId = document.getElementById("userId"+(i));
				alert(userId.value)
				if(userId!=null && (userId.value=='' || userId.value.trim()=='')){	//
					alert("主办人不能为空哦。。");
					return false;
				}
			}
		}
		if(stepCount==0){			//一个都没选
			
			var isLastStep = document.getElementById("currentTask.isLastStep");
			alert(isLastStep);
			if(isLastStep!=null && isLastStep.value!=1){	//不是最后一个步骤
				alert("请至少勾选一个要转交的步骤。。");
				return false;
			}
		}
	}
	return true;
}

function selectUser(td,stedId,type){
	var td1 = document.getElementById(td);
	if(td1!=null){
		if(td1.innerHTML!="&nbsp;" && td1.innerHTML!=""){
			alert("主办人只能增加一个。。");
		}else{
			var mainUserCount = 0;
			var str = '超级用户<a style="CURSOR:hand" onclick="removeUser(\''+td+'\')"><img align="middle" src="./include/img/workflow/remove.png"/></a>';
			var hiddens = "";
			hiddens += "<input type=\"hidden\" name=\"wfTasks["+mainUserCount+"].stepId\" value=\""+stedId+"\">";
			hiddens += "<input type=\"hidden\" name=\"wfTasks["+mainUserCount+"].acceptBy\" value=\""+1+"\">";
			hiddens += "<input type=\"hidden\" name=\"wfTasks["+mainUserCount+"].taskType\" value=\""+1+"\">";
			mainUserCount++;
			
			td1.innerHTML = str + hiddens;
			//document.getElementById("mstHiddens").innerHTML=hiddens;
			//alert(hiddens);
		}
	}
}

function removeUser(td){
	var td1 = document.getElementById(td);
	td1.innerHTML = "&nbsp;";
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

//邮件部分
function syncdata() {
	//document.getElementById("mail.mailText").value = editor.getSource();
}
function addto(to) {
	//document.getElementById("mail.to").value = to;
	if(to!=null && to.trim()!=''){
		document.getElementById("to").innerText = to;
		document.getElementById("mail.to").value = to;
	}else{
		document.getElementById("to").innerText = "";
		document.getElementById("mail.to").value = "";
	}
}
function addcc(cc) {
	if(cc!=null && cc.trim()!=''){
		document.getElementById("cc").innerText = cc;
		document.getElementById("mail.cc").value = cc;
	}else{
		document.getElementById("cc").innerText = "";
		document.getElementById("mail.cc").value = "";
	}
}
function getto() {
	return document.getElementById("to").innerText.trim();
}
function getcc() {
	return document.getElementById("cc").innerText.trim();
}
