function check() {
//	if(document.getElementById("mailTo.mailId").value=="") {
//		alert("请输入mailId内容。");
//		document.getElementById("mailTo.mailId").focus();
//		return false;
//	}
	return true;
}

function closePop() {
	window.parent.closePop();
}

//确定按钮
function retnPop(id,name,task) {
	if(window.parent.addUserto) {
		window.parent.addUserto(getOptv("to"),id);
	} 
	if(window.parent.addUsercc) {
		window.parent.addUsercc(getOptz("to"),name);
	}
	if(window.parent.addTask) {
		window.parent.addTask(getOptTask("to"),task);
	}
	closePop();
}

//取值,并在本页面填充
function getPop(id,name) {
	var _to,_cc;
	if(window.parent.getUserto) {
		var _to =window.parent.getUserto(id)
	}
	if(window.parent.getUsercc) {
		var _cc =window.parent.getUsercc(name)
	}
	setOptz("to",_to,_cc);
}
function setOptz(id,_to,_cc) {
	if(_to!= null && _to!= "" && _to!= "undefined") {
		var _tos = _to.split(",");
		var _ccs = _cc.split(",");
		
		for(i=0; i<_tos.length; i++) {
			var _optz = new Option(_ccs[i],_tos[i]);
			document.getElementById(id).options.add(_optz);
		}
	}
}

//组名字
function getOptz(id) {
	var zz = "";
	if(document.getElementById(id).options.length == 0) 
		return zz;
//	if(document.getElementById(id).options.length == 1) {
//		zz = document.getElementById(id).options[0].text;
//	}
	else {
		for(i=0; i<document.getElementById(id).options.length; i++) {
			if(i == 0)
				zz = document.getElementById(id).options[i].text;
			else
				zz += "," + document.getElementById(id).options[i].text;
		}
	}
	return zz;
}

//组userId
function getOptv(id) {
	var zz = "";
	if(document.getElementById(id).options.length == 0) 
		return zz;
//	if(document.getElementById(id).options.length == 1) {
//		zz = document.getElementById(id).options[0].value;
//	}
	else {
		for(i=0; i<document.getElementById(id).options.length; i++) {
			if(i == 0)
				zz = document.getElementById(id).options[i].value;
			else
				zz += "," + document.getElementById(id).options[i].value;
		}
	}
	return zz;
}

//组转交任务
function getOptTask(id) {
	var hiddens = "";
	if(document.getElementById(id)!=null) {
		var taskType = document.getElementById('taskType').value;
		var stepId = document.getElementById('stepId').value;
		//var date = document.getElementById('date1').value;
		var taskCount = window.parent.document.getElementById('taskCount');
		var count = taskCount.value
		
		for(i=0; i<document.getElementById(id).options.length; i++) {
			var userId = document.getElementById(id).options[i].value;
			
			hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].stepId\" value=\""+stepId+"\">";
			hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].acceptBy\" value=\""+userId+"\">";
			hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].taskType\" value=\""+taskType+"\">";
			//hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].reqDate\" value=\""+date+"\">";
			count++;
		}
		taskCount.value = count;
	}
	return hiddens;
}

function chgmailg() {
	if(document.getElementById("mailg").value == 1) {
		seaProj();
	}
	else if(document.getElementById("mailg").value == 2) {
		seaAddr();
	}
	else if(document.getElementById("mailg").value == 3) {
		seaMail();
	}
}

//按项目组查找
function seaProj() {
	try {
		var xmlhttp = sendXmlhttpRequest("GET","MailToXml.shtml?mailTo.type="+param);

		if(xmlhttp.readyState == 4) {
			var xmldom = getXmldomObject();
			xmldom.loadXML(xmlhttp.responseText);
			
			var _is = getXmldocNodes(xmldom,"METADATA/ITEM1");

			for(i=0;i<_is.length;i++) {
				var btnCode = getXmldocNode(xmldom,"METADATA/ITEM1["+i+"]/btnCode");
				var btnJs = getXmldocNode(xmldom,"METADATA/ITEM1["+i+"]/btnJs");
				var btnName = getXmldocNode(xmldom,"METADATA/ITEM1["+i+"]/btnName");
			}
		}
	}
	catch(e) {
		alert("toolbars exception!");
		return null;
	}
}

//添加按钮
function ckaddto(p0,p1) {
	var obj0 = document.getElementById(p0);
	var obj1 = document.getElementById(p1);
	
	var taskType = document.getElementById('taskType');
	if(taskType.value=='1' && obj1.length>0){
		alert("主办人只能选一个哦。。。");
	}else{
		for(i=0;i<obj0.length;i++){
	        if(obj0.options[i].selected && obj0.options[i].value!='' && obj0.options[i].value!='-1'){
	        	if(!findoptz(p1,obj0.options[i].value)) {
	        		var optz = document.createElement("option");
	        		optz.value = obj0.options[i].value;
	        		
	        		var sss = obj0.options[i].text.split("--");
	        		optz.text = sss[0].trim();
	
	        		obj1.add(optz);
	        		if(taskType.value=='1'){
	        			break;
	        		}
	        	}
	        }
	    }
	}
}
function findoptz(p2,value) {
	var obj2 = document.getElementById(p2);
	for(n=0; n<obj2.length; n++) {
		if(obj2.options[n].value.trim() == value.trim()){
			return true;
		}
	}
	return false;
}

String.prototype.trim = function() { 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
}

//删除按钮
function ckdelopt(p3) {
	var obj3 = document.getElementById(p3);
	
	for(m=obj3.length-1;m>=0;m--){   
        if(obj3.options[m].selected){
        	obj3.remove(m);
        }
    }
}

//按邮件分组，查找
function seaMail() {
	
}

//单选按钮
function selectFromDept(){
	document.getElementById('comId').disabled = "";
	document.getElementById('deptId').disabled = "";
	document.getElementById('txtBtnId').disabled = "";
	document.getElementById('deptId').selectedIndex = 0;
	
	document.getElementById('mailAddr').length = 0;
}
function selectFromWfinfo(){
	document.getElementById('comId').disabled = "disabled";
	document.getElementById('deptId').disabled = "disabled";
	document.getElementById('txtBtnId').disabled = "disabled";
	
	var selectValue = document.getElementById('wfinfoSelect').value;
	document.getElementById('selectTd').innerHTML = selectValue;
}
function selectFromLeader(){
	document.getElementById('comId').disabled = "disabled";
	document.getElementById('deptId').disabled = "disabled";
	
	var selectValue = document.getElementById('leaderSelect').value;
	document.getElementById('selectTd').innerHTML = selectValue;
}
function selectFromProject(){
	document.getElementById('comId').disabled = "disabled";
	document.getElementById('deptId').disabled = "disabled";
	
	var selectValue = document.getElementById('projectSelect').value;
	document.getElementById('selectTd').innerHTML = selectValue;
}


//用户名模糊搜索
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
	       }
	});
}