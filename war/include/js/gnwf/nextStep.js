/*============================================================*/
 var oldParam;
 var sel;
 var txt;
 function showtips2(index){
 	var param = document.getElementById("userName"+index).value;
 	txt = document.getElementById("userName"+index);
 	sel = document.getElementById("sel"+index)
 	var j = 0;
 	
 	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){//兼容IE
		
	}else{//兼容其他浏览器
		if (param != null && param!=' ') {
	 		if(param.length>0 && param.length<5 && oldParam!=param){
	 			$.ajax({
	 				 type:"post",  
	 			       url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
	 			       dataType:"json",
	 			       success: function fun1(jsonData) { 
	 			    	    $("#"+"sel"+index).empty();
	 			   			$.each(jsonData,function(i,item){
	 							var dept = "";
	 							if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
	 								dept = " -- " + item.deptNm;
	 							}
	 							
	 							//15*n px;
	 							if(i>10){
	 								$("#"+"sel"+index).css("height","200px");
	 							}else
	 								{
	 								$("#"+"sel"+index).css("height", ((i+1)*20) + "px");
	 							}
	 							$("#"+"sel"+index).append("<option value='"+item.id+"' onclick='rv("+index+")'>" + item.usrName + dept+"</option>");  
	 							
	 							
	 						});
	 			   			
	 			   			if(jsonData.length>0){
	 							sel.size = (j > 1) ? j : 2;
	 							sel.size = (sel.size>17) ? 17 :sel.size;
	 							sel.style.display = '';
	 						}else{
	 							c();
	 						}
	 			       }
	 			});
	 			
	 			
	 		}
	 	}else{
	 		c();
	 	}
	}
 	
 	
 	oldParam = param;
 	}



 function enterTips(index) {
 	sel = document.getElementById("sel"+index);
 	txt = document.getElementById("userName"+index)
 
 	//var sel = document.getElementById("sel");
 	if(txt.value.length<=0){
 		sel.style.display = 'none';
 	}
	var code;
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){
		code = event.keyCode;
	}else{
		code = enterTips.caller.arguments[0].which;
	}
 	if (sel.style.display != 'none') {
 		if (code == 13)
 			event.srcElement.value = sel.value, sel.style.display = 'none';
 		if (code == 40)
 			sel.focus();
 	}
 }

 var updataArr=[];
 var updataItem;
 var mainUserCount = 0;
 function rv(index) {
	 //alert(index);
	sel = document.getElementById("sel"+index);
 	txt = document.getElementById("userName"+index)
 		sel.style.display = 'none';
 	    txt.value = sel.options[sel.selectedIndex].text;
 	    document.getElementById("userId"+index).value = sel.options[sel.selectedIndex].value;
		
 	   var count = taskCount.value
		var td1 = document.getElementById("task"+index);
		//alert("td1"+td1);
		//if(td1!=null){
			//if(td1.innerHTML!="&nbsp;" && td1.innerHTML!=""){
				//alert("主办人只能增加一个。。");
			//}else{
 	  var nextStepsIndex = Number(index)/2;
				var hiddens = "";
				hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].stepId\" value=\""+document.getElementById("nextSteps["+nextStepsIndex+"].stepId").value+"\">";
				hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].acceptBy\" value=\""+sel.options[sel.selectedIndex].value+"\">";
				hiddens += "<input type=\"hidden\" name=\"wfTasks["+count+"].taskType\" value=\""+1+"\">";
				//mainUserCount++;
				count++;
				taskCount.value = count;
				td1.innerHTML =  hiddens;
			//}
		//}
		
 	    var currentid=sel.options[sel.selectedIndex].value;
 	    var roleid=$("#sel"+index).attr("data-roleId");
 	    var rowid=$("#sel"+index).attr("data-id");
 	    $(updataArr).each(function(i,item){
 	    		//{id:1,roleId:1,usrId:777}
 	    		if(item.id==rowid){
 	    			item.usrId=currentid;
 	    			updataItem=item;
 	    		}
 	    	});

 	    	if(updataItem!=null){
 	    		//updataItem.usrId=currentid;
 	    		
 	    	}else{
 	    		if(rowid == "undefined"){
 	    			updataItem={roleId:roleid,usrId:currentid};
 	    		}else{
 	    			updataItem={id:rowid,roleId:roleid,usrId:currentid};
 	    		}
 	    		//相同的角色只能有一个项目组成员
 	    		var exist = false;
 	    		 $(updataArr).each(function(i,item){
 	    			 if(item.roleId == updataItem.roleId){
 	    				 item.usrId =  updataItem.usrId;
 	    				 exist= true;
 	    			 }
 	    		 });
 	    		 if(!exist){
 		    		updataArr.push(updataItem);
 	    		 }
 	    		updataItem = null;
 	    	}
     //editgridManager.endEdit(index);
 	oldParam = txt.value;
 	c();
 }
 
 
 
 function c() {
 	if(sel!=null){
 		sel.style.display = 'none';
 	}
 	if(xbsel!=null){
		xbsel.style.display = 'none';
	}
 }
 document.onclick = function() {
 	c();
 }
/*==========================================================================*/
var xbipt;
var xbsel;
function showxbSel(index){
	

	var j = 0;
	xbipt = document.getElementById("xbipt"+index);
	xbsel = document.getElementById("xbsel"+index);
	var param = document.getElementById("xbipt"+index).value;
	if(param.length>0){
		$.ajax({
			type:"post",  
		    url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
		    dataType:"json",
		    success: function fun1(jsonData){
		        $("#"+"xbsel"+index).empty();
	   			$.each(jsonData,function(i,item){
					var dept = "";
					if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
						dept = " -- " + item.deptNm;
					}
					
					//15*n px;
					if(i>10){
						$("#"+"xbsel"+index).css("height","200px");
					}else
						{
						$("#"+"xbsel"+index).css("height", ((i+1)*20) + "px");
					}
					$("#"+"xbsel"+index).append("<option value='"+item.id+"' onclick='setxbV("+index+")'>" + item.usrName + dept+"</option>");  
					
					
				});
		   			if(jsonData.length>0){
						xbsel.size = (j > 1) ? j : 2;
						xbsel.size = (xbsel.size>17) ? 17 :xbsel.size;
						xbsel.style.display = '';
					}else{
						if(xbsel!=null){
					 		xbsel.style.display = 'none';
					 	}
					}
		    }
		});
	}
	if(xbipt.value.length<=1){
 		xbsel.style.display = 'none';
 	}
}
function toxbSelFocus(index){
	xbsel = document.getElementById("xbsel"+index);
 	xbipt = document.getElementById("xbipt"+index)
 
 	//var sel = document.getElementById("sel");
 	if(xbipt.value.length<=0){
 		xbsel.style.display = 'none';
 	}
	var code;
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){
		code = event.keyCode;
	}else{
		code = toxbSelFocus.caller.arguments[0].which;
	}
 	if (xbsel.style.display != 'none') {
 		if (code == 13)
 			event.srcElement.value = xbsel.value, xbsel.style.display = 'none';
 		if (code == 40)
 			xbsel.focus();
 	}
}
function setxbV(index){
 	xbsel = document.getElementById("xbsel"+index);
 	xbipt = document.getElementById("xbipt"+index)
 	xbsel.style.display = 'none';
 	xbipt.value = xbsel.options[xbsel.selectedIndex].text;
    var currentid=xbsel.options[xbsel.selectedIndex].value;
    var roleid=$("#xbsel"+index).attr("data-roleId");
    var rowid=$("#xbsel"+index).attr("data-id");
}

function selectprojectUser(id,name) {
	//alert(id);
	//alert(name);
	$("#userId"+selectedUIndex).val(id);
	$("#userName"+selectedUIndex).val(name);
	var td1 = document.getElementById("task"+selectedUIndex);	
	var hiddens = "";
	hiddens += "<input type=\"hidden\" id=\"wfTasks["+index+"].stepId\" name=\"wfTasks["+selectedUIndex+"].stepId\" value=\""+document.getElementById("nextSteps["+selectedUIndex+"].stepId").value+"\">";
	hiddens += "<input type=\"hidden\" id=\"wfTasks["+index+"].acceptBy\" name=\"wfTasks["+selectedUIndex+"].acceptBy\" value=\""+id+"\">";
	hiddens += "<input type=\"hidden\" id=\"wfTasks["+index+"].taskType\" name=\"wfTasks["+selectedUIndex+"].taskType\" value=\""+1+"\">";
	td1.innerHTML =  hiddens;
} 
// function hidexbSel(index){
//  	xbsel = document.getElementById("xbsel"+index);
//  	xbsel.style.display = 'none';
// }


//弹出选择主办人面板
function selectNextStepUsers(i,j){
	//alert("22222222222222");
	if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		
		selectPrjtUsers2();
	}else{
		//alert(j);
		 pop =  $.ligerDialog.open({title:'选择该步骤接收人员', height: 410, width: 320,url: './WfRd!addNextStepUsers.shtml?userNameIDIt='+j,
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}

//选择下一步主办人
function selectprojectUserForFlow(id,name,index) {
	/*alert(id);
	alert(name);
	alert(index);*/
	
	$("#userId"+index).val(id);
	$("#userName"+index).val(name);
	var td1 = document.getElementById("task"+index);	
	var hiddens = "";
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){//兼容IE
		var count = document.getElementById("taskCount").value;
	}else{//兼容其他浏览器
	
	var count = taskCount.value;
	}
	var nextStepsIndex = Number(index)/2;
	if(td1!=null && td1!='undefined' ){
		
	hiddens += "<input type=\"hidden\" id=\"wfTasks["+count+"].stepId\" name=\"wfTasks["+count+"].stepId\" value=\""+document.getElementById("nextSteps["+nextStepsIndex+"].stepId").value+"\">";
	hiddens += "<input type=\"hidden\" id=\"wfTasks["+count+"].acceptBy\" name=\"wfTasks["+count+"].acceptBy\" value=\""+id+"\">";
	hiddens += "<input type=\"hidden\" id=\"wfTasks["+count+"].taskType\" name=\"wfTasks["+count+"].taskType\" value=\""+1+"\">";
	count++;
	
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){//兼容IE
	document.getElementById("taskCount").value=count;
	}else{//兼容其他浏览器
	
		taskCount.value = count;
	}
	
	td1.innerHTML =  hiddens;
	}
} 


//弹出选择协办人面板
function selectNextStepAssistpUsers(i,j){
	//alert("22222222222222");
	if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		
		selectPrjtUsers2();
	}else{
		//alert(j);
		 pop =  $.ligerDialog.open({title:'选择该步骤接收人员', height: 410, width: 500,url: './WfRd!addNextStepAssistpUsers.shtml?userNameIDIt='+j,
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}
//选择下一步协办人
function selectAssistpProjectUserForFlow(id,name,index) {
	$("#userId"+index).val(id);
	$("#userName"+index).val(name);
	/*alert(id);
	alert(name);
	alert(index);*/
	
	var IDarray = id.split(",");

	var td1 = document.getElementById("task"+index);	
	var hiddens = "";
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){//兼容IE
		var count = document.getElementById("taskCount").value;
	}else{//兼容其他浏览器
	var count = taskCount.value;
	}
	var nextStepsIndex = (Number(index)-Number('1'))/2;
	
	for(i=0; i<IDarray.length; i++) {
		 //alert(parseInt(IDarray[i]));
		hiddens += "<input type=\"hidden\" id=\"wfTasks["+count+"].stepId\" name=\"wfTasks["+count+"].stepId\" value=\""+document.getElementById("nextSteps["+nextStepsIndex+"].stepId").value+"\">";
		hiddens += "<input type=\"hidden\" id=\"wfTasks["+count+"].acceptBy\" name=\"wfTasks["+count+"].acceptBy\" value=\""+parseInt(IDarray[i])+"\">";
		hiddens += "<input type=\"hidden\" id=\"wfTasks["+count+"].taskType\" name=\"wfTasks["+count+"].taskType\" value=\""+2+"\">";
		count++;
	}
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){//兼容IE
	document.getElementById("taskCount").value=count;
	}else{//兼容其他浏览器
		taskCount.value = count;
	}
	if(id == null ||id == ''){
		td1.innerHTML =null;
	}else{
		td1.innerHTML =  hiddens;
	}
	
	
} 



//选择项目组成员
$(function() {
	function syncdata() {
		$("#docDesc").val();
	}
});

var autoid=1;
var editgridManager;
var statusData = [{ Status: -1, text: '未配置人员'},{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];
var upTypData = [{ UpTyp: 1, text: '加入项目组' }, { UpTyp: 0, text: '离开项目组'}];


var selectWin =null;
//open select window


var pop;
var selectedUIndex;
function selectPrjtUsers(element){
	//alert("222222");
	var domName = $(element).parent().parent().find("[id^=userName]").attr("id");
	var keyName = "userName";
	selectedUIndex = domName.substring(keyName.length,domName.length);
	//rv(<c:out value="${i}"/>)
	//alert(selectedUIndex);
	
	 if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		//alert("fff");
		// alert("2222377777222");
		selectPrjtUsers2();
	}else{ 
		
		pop =  $.ligerDialog.open({title:'选择项目组成员', height: 390, width: 500,/*  url: './PrjtUsr!addMailUser.shtml',  */
			  url : './WfRd!OveSeaUsrList.shtml?wfRd.wfNo='
				+ $('#wfRdwfNo').val()  ,
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}




function closepop(){
	pop.close();
}