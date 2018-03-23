//附件
var i=0;
function addFile() {
	var sel = document.getElementById("checkDoc");
	var cateId = 0;
	var cateName = "";
	if(sel!=null && sel.checked){
		var cateId = getRadioValue('docCate');
		var cateName = document.getElementById("cate"+cateId).value;
	}
	     currRow = document.getElementById("attachtab").insertRow();
	     currRow.id = "attachtabDtl"+ currRow.rowIndex;
	     cellc = currRow.insertCell();
	   	 var delobj = '<input type="file" size="40" id="files'+i+'" name="files['+i+']">&nbsp;&nbsp;'
	   	 	+'<input type="hidden" id="fileCates'+i+'" name="fileCates['+i+']" value="'+cateId+'">'
	   	 	+'<font color="red">'+cateName+'</font>'
	   	 	+"&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"removeFile('attachtab'"+",'"+currRow.id+"');\">删除</a>";
	     cellc.innerHTML=delobj;
	     i++;
	
	//无任务页面时加上传按钮
	var submitFileBtn = document.getElementById("submitFileBtn");
	if(submitFileBtn!=null && submitFileBtn.style.display=='none'){
		submitFileBtn.style.display="block";
	}
}
function removeFile(table,id){
	if(table=="oldAttachtab"){
		document.getElementById("delAttach").value=document.getElementById("delAttach").value+","
			+document.getElementById(table).rows[document.getElementById(id).rowIndex].title;
	}
	document.getElementById(table).deleteRow(document.getElementById(id).rowIndex);
	
	//无任务页面时去掉上传按钮
	if(document.getElementById("attachtab").rows.length<=0){
		submitFileBtn.style.display="none";
	}
}


//附件上传--新
function uploadFile(prjtNo,prjtNm,wfId,wfNm,usrId,usrNm,wfNo,taskId) {
	var gngile_uploadURL = $('#gngile_upload_URL').val();
	var server_URL = $('#server_URL').val();
	var cateId = getRadioValue('docCate');
	var httpUrl =  gngile_uploadURL
		+'?syId=1&syNm=PDM&usrId='+usrId+'&usrNm='+usrNm+'&diyFolder='+"aaa"
		+'&callBackUrl='+server_URL+"/WfDoc!addfile.shtml"
		+'&tempParams='+"wfId:"+wfId+",wfNm:"+wfNm+",wfNo:"+wfNo;
	httpUrl = makeUrl(httpUrl,",prjtNo:",prjtNo);
	httpUrl = makeUrl(httpUrl,",taskId:",taskId);
	httpUrl = makeUrl(httpUrl,",cateId:",cateId);
	//httpUrl = makeUrl(httpUrl,"&prjtNm=",prjtNm);
	//alert(httpUrl);
	httpUrl = encodeURI(httpUrl);
	var uploadDialog = $.ligerDialog.open({title:'上传附件', height: 450, width: 470,
			url: httpUrl,showToggle: true, showMin: true, isResize: true});
	
	$(".l-dialog-close").click(function(){
		var grid = $("#wfdocgrid").ligerGetGridManager().loadData();
		if(grid){
			grid.loadData();
		}
	});
}
function makeUrl(httpUrl,str,value){
	if(value!=null && value!='' && value!=-1){
		httpUrl = httpUrl+str+value;
	}
	return httpUrl;
}

function submitFile(){			//上传附件
	if(confirm("您确定要上传附件吗？")){
		var form = document.getElementById("view-form");
		form.action='WfRdView!addFiles.shtml';
		form.target="_self";
		form.submit();
	}
}
function deleteFile(docId){			//删除附件
	if(confirm("您真的要删除此附件吗？")){
		document.getElementById("docFileId").value = docId;
		
		var form = document.getElementById("view-form");
		if(!form){
			form = document.getElementById("wfRd-form");
		}
		
		form.action='WfRdView!deleteFile.shtml';
		form.target="_self";
		form.submit();
	}
}


function deleteFile2(docId){			//删除附件
		$.ligerDialog.confirm('您确定要删除此附件？', 
				function (type) {
					if(type) {
						$.post("WfRdView!deleteFile.shtml?wfDoc.docId="+docId,
							function(data) {
								if(data.toString().indexOf("成功")>0){
									alert("删除成功");
									window.location = "./WfRdView!viewDocCate.shtml?wfRd.wfNo="
										+$("#wfNo").val()+"&currentTaskId="+$("#currentTaskId").val()+"&taskStepId="+$("#taskStepId").val();
								}else if(data.toString().indexOf("失败")>0){
									$.ligerDialog.success("删除失败");
								}
							},
							"text"
							
//							
//							function(data) {
//								if(data.toString().indexOf("完成")>0){
//									$.ligerDialog.success("催办完成");
//								}else if(data.toString().indexOf("失败")>0){
//									$.ligerDialog.success("催办失败");
//								}
//							},
//							"text"
						);
					}
				}
		);
}


function baseFile(docId){
	if(confirm("您确定要归档附件吗？")){
		document.getElementById("docFileId").value = docId;
		
		var form = document.getElementById("view-form");
		if(!form){
			form = document.getElementById("wfRd-form");
		}
		form.action='WfRdView!baseFile.shtml';
		form.target="_self";
		form.submit();
	}
}

//展开
function operatorHelp() {
	if (document.getElementById("operatorDiv").style.display == 'none') {
		document.getElementById("operatorDiv").style.display = "block";
		document.getElementById("operatorBtn").innerText = "关闭"; 
	} else {
		document.getElementById("operatorDiv").style.display = "none";
		document.getElementById("operatorBtn").innerText = "展开";
	}
}
// 模糊搜索--列出用户
function listUser(from) {
	try {
		var param = document.getElementById(from).value;
		if (param != null) {
			var xmlhttp = sendXmlhttpRequest("GET","SyUserXml!listUser.shtml?syUser.userName="+encodeURI(encodeURI(param)));
			if (xmlhttp.readyState == 4) {
				var xmldom = getXmldomObject();
				xmldom.loadXML(xmlhttp.responseText);

				var nodes = getXmldocNodes(xmldom, "METADATA/ITEM");
				document.getElementById(to).length = 0;

				document.getElementById(to).add(document.createElement("option"));
				document.getElementById(to).options[0].value = "";
				document.getElementById(to).options[0].text = "请选择";
				
				if (isIe() > 0) {	//非ie浏览器
					for(i=1;i<=nodes.length;i++) {
						document.getElementById(to).add(document.createElement("option"));
						document.getElementById(to).options[i].value = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/userId");
						document.getElementById(to).options[i].text = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/userName");
					}
				}
				else {         		//ie浏览器
					for(i=0;i<nodes.length;i++) {
						document.getElementById(to).add(document.createElement("option"));
						document.getElementById(to).options[i+1].value = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/userId");
						document.getElementById(to).options[i+1].text = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/userName");
					}
				}
			}
		}
	} catch (e) {
		alert(e);
		return null;
	}
}

var oldParam;
function showtips(from,to) {
//	try {
		var param = document.getElementById(from).value;
		var sel = document.getElementById(to);
		var j = 0;
		
		if (param != null && param!=' ') {
			if(param.length>0 && param.length<5 && oldParam!=param){
				var xmlhttp = sendXmlhttpRequest("GET","SyUserXml!listUser.shtml?syUser.userName="+encodeURI(encodeURI(param)));
				if (xmlhttp.readyState == 4) {
					$("#"+to).empty();
					var jsonData = eval(xmlhttp.responseText);
					$.each(jsonData,function(i,item){
						var dept = "";
						if(item.deptName!=null && item.deptName!='undefined' && item.deptName.length>0){
							dept = " -- " + item.deptName;
						}
						$("#"+to).append("<option value='"+item.userName+"'>" + item.userName + dept+"</option>");
						j++;
					});
					
					if(jsonData.length>0){
						sel.size = (j > 1) ? j : 2;
						sel.size = (sel.size>17) ? 17 :sel.size;
						sel.style.display = '';
					}else{
						c();
					}
				}
			}
		}else{
			c();
		}
		oldParam = param;
//	} catch (e) {
//		alert(e);
//		return null;
//	}
}

/*function enterTips() {
	e = event.keyCode;
	var sel = document.getElementById("sel");
	if (sel.style.display != 'none') {
		if (e == 13)
			event.srcElement.value = sel.value, sel.style.display = 'none';
		if (e == 40)
			sel.focus();
	}
}
function rv() {
	var sel = document.getElementById("sel");
	var txt = document.getElementById("txt");
	txt.value = sel.value;
	oldParam = txt.value;
	c();
}*/
function c() {
	var sel = document.getElementById("sel");
	if(sel!=null){
		sel.style.display = 'none';
	}
	//var txt = document.getElementById("txt");
	//txt.focus();
}
document.onclick = function() {
	c();
}



function backJob(taskId){			//旧版收回任务
	if(confirm("您真的要收回任务吗？")){
		//setEditFrmSrc('WfRdView!backJob.shtml?wfRd.wfNo='+wfNo+'&currentTask.taskId='+taskId);
		document.getElementById("rowTaskId").value = taskId;
		document.getElementById("view-form").action='WfRdView!backJob.shtml';
		document.getElementById("view-form").target="_self";
		document.getElementById("view-form").submit();
	}
}
function pushMail(taskId){			//旧版催办
	//var wfNo = document.getElementById("wfNo").value;
	if(confirm("您真的要催办吗？")){
		document.getElementById("rowTaskId").value = taskId;
		document.getElementById("view-form").action='WfRdView!sendMail.shtml';
		document.getElementById("view-form").target="_self";
		document.getElementById("view-form").submit();
	}
}


function pushMail2(wfNo,taskId){			//新版催办
	$.ligerDialog.confirm('您真的要催办吗？', 
			function (type) {
				if(type) {
					$.post("WfRdView!sendMail.shtml?currentTask.taskId="+taskId+"&wfRd.wfNo="+wfNo,
						function(data) {
							if(data.toString().indexOf("完成")>0){
								$.ligerDialog.success("催办完成");
							}else if(data.toString().indexOf("失败")>0){
								$.ligerDialog.success("催办失败");
							}
						},
						"text"
					);
				}
			}
	);
}


/*function rejectJob(){		//退回任务
	if(document.getElementById("currentTask.createBy").value==-1){
		alert("系统所发任务不可退回!");
	}else{
		alert("111111");
		var str=prompt("您确定要退回任务吗？","请写退回原因(字数不得超过50个字)，谢谢");
		var taskId = document.getElementById("currentTask.taskId").value;
		if(str){
			document.getElementById("wfRd-form").action="WfRdView!reject.shtml?taskId="+taskId;
			document.getElementById("wfRd-form").target="_self";
			document.getElementById("wfRd-form").submit();
		}
	}
	
//	var str=prompt("您确定要退回任务吗？","请写退回原因(字数不得超过50个字)，谢谢");
//	if(str){
//		document.getElementById("currentTask.remark").value=str;
//		document.getElementById("wfRd-form").action="WfRdView!reject.shtml?taskId="+taskId;
//		document.getElementById("wfRd-form").target="_self";
//		document.getElementById("wfRd-form").submit();
//	}
}*/

function printForm(){		//打印表单
	document.getElementById("wfRd-form").action="WfRdView!prn.shtml";
	document.getElementById("wfRd-form").target="_blank";
	document.getElementById("wfRd-form").submit();
}

function stopWfinfo() {		//中止本流程
	if(confirm("您确定中止本流程吗？ （中止后如需重新启动流程，请找流程相关主导人）")) {
		var form = document.getElementById("wfRd-form");
		form.target="_self";
		form.action="WfRdView!stopWfinfo.shtml";
		form.submit();
	}
}

//启动子流程
function startRelateFlow(wfNo,projectNo){
	var id = getRadioValue("relate");
	if(id!=-1 && wfNo!=''){
		if(confirm("您确定触发子流程吗？")) {
			dlgClose(id,wfNo,projectNo);
			//parent.$.ligerDialog.open({title:'新增对话框：WfRd', height: 560, width: 780,url: './WfRd!add.shtml?wfRd.flowId='+id,showMax: true, showToggle: true, showMin: true, isResize: true});
		}
	}else{
		alert("当前无子流程!");
	}
}

function dlgClose(flowId,wfNo,projectNo){
	//	parent.window.document.getElementById("roleName").focus();	//调用父窗口某一个文件框，获取焦点
	//	parent.window.initBaseInfoGrid();				//调父窗口某个方法
	//parent.$(".l-dialog,.l-window-mask").remove(); 	//关闭遮罩层
	//parent.$.ligerDialog.close();
	parent.$.ligerDialog.open({title:'启动子流程：WfRd', height: 560, width: 780,
		url: './WfRd!add.shtml?wfRd.flowId='+flowId+'&wfRd.preWfNo='+wfNo+'&wfRd.projectNo='+projectNo,
		showMax: true, showToggle: true, showMin: true, isResize: true});
}


//取radio checked值
function getRadioValue(name) {
   var radioObject = document.getElementsByName(name);             
   for (var i = 0; i < radioObject.length; i++){
	   if(radioObject[i].checked == true){           
		   return radioObject[i].value;
	   }
   }
   return -1;
}

function openRelateFlow(wfNo){
//	if(confirm("确定前往流程"+wfNo+"？")) {
//		if(window.parent.parent) {
//			if(window.parent.parent.addtab) {
//				window.parent.parent.addtab(
//					wfNo,
//					wfNo + "流程",
//					"WfRdView!view.shtml?wfRd.wfNo=" + wfNo
//				);
//			}
//		}
//	}
	
	if(confirm("确定前往流程"+wfNo+"？")) {
		if(window.parent) {
			if(window.parent.f_open) {
				window.parent.f_open(
					"./WfRdView.shtml?wfRd.wfNo="+wfNo,
					"工作流"+wfNo,
					"./include/images/Alien Folder.png"
				);
			}
		}
	}
	
}

function transmgr() {
	if(window.parent.parent.parent) {
		if(window.parent.parent.parent.addtab) {
			window.parent.parent.parent.addtab(
				$("#wfNo").val(),
				$("#wfNo").val() + "流程问题",
				"./WfQues!tasklistmgr.shtml?wfNo=" + $("#wfNo").val()
			);
		}
	}
}


/*function exportXls(){
	document.getElementById("wfRd-form").action="WfRdView!exp.shtml";
	document.getElementById("wfRd-form").target="_blank";
	document.getElementById("wfRd-form").submit();
}*/
/*function exportXls(){
	alert("exportXls——sp");
	alert(document.getElementById("wfRd-form"));
	document.getElementById("wfRd-form").action="WfRdView!exp1.shtml";
	document.getElementById("wfRd-form").target="_blank";
	document.getElementById("wfRd-form").submit();
}*/

//=点击展开关闭效果=
function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
	var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
	var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
	var openTip = oOpenTip || "";
	var shutTip = oShutTip || "";
	if(targetObj.style.display!="none"){
		if(shutAble) return;
		targetObj.style.display="none";
		if(openTip && shutTip){
			sourceObj.innerHTML = shutTip; 
		}
	} else {
		targetObj.style.display="block";
		if(openTip && shutTip){
			sourceObj.innerHTML = openTip; 
		}
	}
}

$(function(){
	clickShow($(".applicationBtn"),$(".applicationForm"));
	clickShow($(".subprocessesBtn"),$(".subprocessesForm"));
	$(".uploadForm").find("p:odd").css("background","#eff7ff");
	$(".workFlow").find("tr").find("td:last").css("border-right","1px solid #dbdbdb");
})
function clickShow(btn,con){
	con.hide();	//默认隐藏
	btn.bind('click',function(){
		if(con.is(":hidden")){
			con.slideDown();$(this).html("点击隐藏");
		}else{
			con.slideUp();$(this).html("点击展开");
		}
	})
}


//DCC其它变更----动态行插入
function AddRow5() {
	var numI = document.getElementById("numI1");
	var num = Number(document.getElementById("numI1").value);
	
	var countNum = document.getElementById("fcount");
	var fcount = Number(document.getElementById("fcount").value);
	
	var obj=document.getElementById("OwnershipStructure");
	var tr = obj.rows["StrRight"];
	var tr = obj.insertRow(tr.rowIndex + (2*num) - 1);
	
	var td0 = tr.insertCell(0);
	td0.setAttribute("colSpan","3");
	td0.setAttribute("rowSpan","2");
	td0.setAttribute("align","center");
	td0.innerHTML = num;
	
	var td1 = tr.insertCell(1);
	td1.setAttribute("colSpan","4");
	td1.setAttribute("height","40");
	td1.setAttribute("align","center");
	td1.innerHTML = '更改前';
	
	
	var td2 = tr.insertCell(2);
	td2.setAttribute("colSpan","6");
	td2.innerHTML = 
		"<textarea rows=4 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='969' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";

	fcount++;
	var td3 = tr.insertCell(3);
	td3.setAttribute("colSpan","7");
	td3.innerHTML = 
		"<textarea rows=4 style=\"width:94%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='970' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td4 = tr.insertCell(4);
	td4.setAttribute("colSpan","14");
	td4.innerHTML = 
		"<textarea rows=4 maxlength=\"40\" style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='971' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td5 = tr.insertCell(5);
	td5.setAttribute("colSpan","14");
	td5.innerHTML = 
		"<textarea rows=4 maxlength=\"40\" style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1053' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td6 = tr.insertCell(6);
	td6.setAttribute("colSpan","9");
	td6.innerHTML = 
		"<textarea rows=4 maxlength=\"30\" style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='972' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td7 = tr.insertCell(7);
	td7.setAttribute("colSpan","8");
	td7.innerHTML = 
		"<textarea rows=4 style=\"width:95%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='973' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td8 = tr.insertCell(8);
	td8.setAttribute("colSpan","6");
	td8.innerHTML = 
		"<textarea rows=4  style=\"width:92%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='974' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td9 = tr.insertCell(9);
	td9.setAttribute("colSpan","6");
	td9.innerHTML = 
		"<textarea rows=4 maxlength=\"18\" style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='975' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td10 = tr.insertCell(10);
	td10.setAttribute("colSpan","6");
	td10.innerHTML = 
		"<textarea rows=4 maxlength=\"18\" style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='976' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td11 = tr.insertCell(11);
	td11.setAttribute("colSpan","6");
	td11.innerHTML = 
		"<textarea rows=4 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='977' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td12 = tr.insertCell(12);
	td12.setAttribute("colSpan","10");
	td12.innerHTML = 
		"<textarea rows=4 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='978' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	//下一行
	
	var tr2 = obj.rows["StrRight"];
	var tr2 = obj.insertRow(tr2.rowIndex + (2*num));
	var td20 = tr2.insertCell(0);
	td20.setAttribute("colSpan","4");
	td20.setAttribute("height","40");
	td20.setAttribute("align","center");
	td20.innerHTML = '更改后';
	
	fcount++;
	var td21 = tr2.insertCell(1);
	td21.setAttribute("colSpan","6");
	td21.innerHTML = 
		"<textarea rows=4 style=\"width:93%;overflow:hidden;background-color: rgb(255, 255, 255);\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='979' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";

	fcount++;
	var td22 = tr2.insertCell(2);
	td22.setAttribute("colSpan","7");
	td22.innerHTML = 
		"<textarea rows=4 style=\"width:94%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='980' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td23 = tr2.insertCell(3);
	td23.setAttribute("colSpan","14");
	td23.innerHTML = 
		"<textarea rows=4 maxlength=\"40\" style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='981' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td24 = tr2.insertCell(4);
	td24.setAttribute("colSpan","14");
	td24.innerHTML = 
		"<textarea rows=4 maxlength=\"40\" style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1054' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td25 = tr2.insertCell(5);
	td25.setAttribute("colSpan","9");
	td25.innerHTML = 
		"<textarea rows=4 maxlength=\"30\" style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='982' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td26 = tr2.insertCell(6);
	td26.setAttribute("colSpan","8");
	td26.innerHTML = 
		"<textarea rows=4 style=\"width:95%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1045' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td27 = tr2.insertCell(7);
	td27.setAttribute("colSpan","6");
	td27.innerHTML = 
		"<textarea rows=4  style=\"width:92%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1046' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td28 = tr2.insertCell(8);
	td28.setAttribute("colSpan","6");
	td28.innerHTML = 
		"<textarea rows=4 maxlength=\"18\" style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1047' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	numI.setAttribute("value",num+1);
	countNum.setAttribute("value",fcount+1);

	fcount++;
	var td29 = tr2.insertCell(9);
	td29.setAttribute("colSpan","6");
	td29.innerHTML = 
		"<textarea rows=4 maxlength=\"18\" style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1048' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	numI.setAttribute("value",num+1);
	countNum.setAttribute("value",fcount+1);
	
	fcount++;
	var td30 = tr2.insertCell(10);
	td30.setAttribute("colSpan","6");
	td30.innerHTML = 
		"<textarea rows=4 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1049' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	numI.setAttribute("value",num+1);
	countNum.setAttribute("value",fcount+1);
	
	fcount++;
	var td31 = tr2.insertCell(11);
	td31.setAttribute("colSpan","10");
	td31.innerHTML = 
		"<textarea rows=4 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText onblur=\"checkTheDifferentInput();\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1050' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	numI.setAttribute("value",num+1);
	countNum.setAttribute("value",fcount+1);
}

//DCC其它变更删除行
function DelRow5(numI,strRight){ 
	var hiddenNumI = document.getElementById(numI);
	var num = Number(document.getElementById(numI).value);
	if(num>2){
		var obj = document.getElementById("OwnershipStructure"); 
		var tr = obj.rows[strRight]; 
		obj.deleteRow(tr.rowIndex+(2*(num-1)));
		obj.deleteRow(tr.rowIndex+(2*(num-1))-1);
		hiddenNumI.setAttribute("value",num-1);
	}
}





//中试部计划管理
function AddRow48() {
	var flag = DelRow48("numI1","StrRight");	//删除原有
	
	if(!flag){
		return;
	}
	var userName = document.getElementById("userName").value;
	
	var r = document.getElementsByName("checkbox1");  
	for(var i=0;i<r.length;i++){
		if(r[i].checked){
			var numI = document.getElementById("numI1");
			var num = Number(document.getElementById("numI1").value);
			
			var countNum = document.getElementById("fcount");
			var fcount = Number(document.getElementById("fcount").value);
			
			var obj=document.getElementById("OwnershipStructure");
			var tr = obj.rows["StrRight"];
			
			var tr = obj.insertRow(tr.rowIndex+num);		//插入行
			var td0 = tr.insertCell(0);
			td0.setAttribute("colSpan","4");
			td0.setAttribute("align","center");
			td0.innerHTML = num;
			
			fcount++;
			var td1 = tr.insertCell(1);
			td1.setAttribute("colSpan","10");
			td1.innerHTML = 
				userName + "<input type=hidden name=\"fieldContents["+fcount+"].fieldText\" value='"+userName+"'/>"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='479'/>" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"'/>";
			
			fcount++;
			var td2 = tr.insertCell(2);
			td2.setAttribute("colSpan","20");
			td2.setAttribute("bgColor","#ffffff");
			td2.innerHTML = 
				"<textarea rows=4 style=\"width:95%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
					"name=fieldContents["+fcount+"].fieldText>"+r[i].nextSibling.nodeValue+"</textarea>"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='480' />" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
			fcount++;
			var td3 = tr.insertCell(3);
			td3.setAttribute("colSpan","14");
			td3.innerHTML = "<textarea rows=4 style=\"width:94%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText ></textarea>"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='481' />" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
			fcount++;
			var td4 = tr.insertCell(4);
			td4.setAttribute("colSpan","12");
			td4.innerHTML = "<textarea rows=4 style=\"width:92%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
					"name=fieldContents["+fcount+"].fieldText onblur=\"A30fun2(this);\"></textarea>"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='482' />" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
			fcount++;
			var td5 = tr.insertCell(5);
			td5.setAttribute("colSpan","12");
			td5.innerHTML = "<textarea rows=4 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
					"name=fieldContents["+fcount+"].fieldText onblur=\"A30fun2(this);\"></textarea>"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='483' />" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
			fcount++;
			var td6 = tr.insertCell(6);
			td6.setAttribute("colSpan","10");
			td6.innerHTML = "<input type=hidden name=fieldContents["+fcount+"].fieldText value='' />"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='484' />" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
			fcount++;
			var td7 = tr.insertCell(7);
			td7.setAttribute("colSpan","18");
			td7.innerHTML = "<input type=hidden name=fieldContents["+fcount+"].fieldText value='' />"+
				"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='485' />" +
				"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
			numI.setAttribute("value",num+1);
			countNum.setAttribute("value",fcount+1);
		}
	}
}

function DelRow48(numI,strRight){ 
	var hiddenNumI = document.getElementById(numI);
	var num = Number(document.getElementById(numI).value);
	if(num>1){
		if(confirm("确定去掉已有数据？")){
			var hiddenNumI = document.getElementById(numI);
			var num = Number(document.getElementById(numI).value);
			
			var countNum = document.getElementById("fcount");
			var fcount = Number(document.getElementById("fcount").value);
					
			for(var i=num;i>=2;i--){
				var obj=document.getElementById("OwnershipStructure"); 
				var tr= obj.rows[strRight]; 
				obj.deleteRow(tr.rowIndex+i-1);  
			}
			hiddenNumI.setAttribute("value",1); 
			return true;
		}
		return false;
	}
	return true;
}

function displayCate(cateId){
	for(var i=1;i<=9;i++){
		var tr = document.getElementById("tr"+i);
		tr.style.display = 'none';
	}
	var sel = document.getElementById(cateId);
	sel.style.display = 'block';
}


//SAP主数据维护流程
function AddRow53() {
	//alert("AddRow_sp111111");
	var numI = document.getElementById("numI1");
	var num = Number(document.getElementById("numI1").value);
	
	var countNum = document.getElementById("fcount");
	var fcount = Number(document.getElementById("fcount").value);
	
	//alert(num+"---"+fcount);
	
	var obj=document.getElementById("OwnershipStructure");
	var tr = obj.rows["StrRight"];
	var tr = obj.insertRow(tr.rowIndex+num);
	
	var td0 = tr.insertCell(0);
	td0.setAttribute("colSpan","3");
	td0.setAttribute("align","center");
	
	td0.innerHTML = num;
	
	
	fcount++;
	var td1 = tr.insertCell(1);
	td1.setAttribute("colSpan","5");
	td1.innerHTML = 
		"<textarea rows=4 style=\"width:90%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='989' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td2 = tr.insertCell(2);
	td2.setAttribute("colSpan","4");
	td2.setAttribute("bgColor","#ffffff");
	td2.innerHTML = "<select name=\"fieldContents["+fcount+"].fieldText\" onchange=\"A30fun1(this);\">" + 
	 	"<option value=-1>请选择</option>"+document.getElementById("matlCate").value + "</select>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='990' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td3 = tr.insertCell(3);
	td3.setAttribute("colSpan","8");
	td3.innerHTML = "<textarea rows=4 style=\"width:90%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText ></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='991' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td4 = tr.insertCell(4);
	td4.setAttribute("colSpan","14");
	td4.innerHTML = "<textarea rows=4 maxlength=40 style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
			"name=fieldContents["+fcount+"].fieldText onblur=\"A30fun2(this);\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='992' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td5 = tr.insertCell(5);
	td5.setAttribute("colSpan","13");
	td5.innerHTML = "<textarea rows=4 maxlength=40 style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
			"name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1052' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td6 = tr.insertCell(6);
	td6.setAttribute("colSpan","10");
	td6.innerHTML = "<textarea rows=4 maxlength=30 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
			"name=fieldContents["+fcount+"].fieldText onblur=\"A30fun2(this);\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='993' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td7 = tr.insertCell(7);
	td7.setAttribute("colSpan","5");
	td7.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='994' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td8 = tr.insertCell(8);
	td8.setAttribute("colSpan","9");
	td8.innerHTML = "<textarea rows=4 maxlength=18 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='995' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td9 = tr.insertCell(9);
	td9.setAttribute("colSpan","9");
	td9.innerHTML = "<textarea rows=4 maxlength=18 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='996' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td10 = tr.insertCell(10);
	td10.setAttribute("colSpan","4");
	td10.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='997' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td11 = tr.insertCell(11);
	td11.setAttribute("colSpan","4");
	td11.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='998' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td12 = tr.insertCell(12);
	td12.setAttribute("colSpan","4");
	td12.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='999' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td13 = tr.insertCell(13);
	td13.setAttribute("colSpan","4");
	td13.innerHTML = "<textarea rows=4 style=\"width:84%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1000' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td14 = tr.insertCell(14);
	td14.setAttribute("colSpan","4");
	td14.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1001' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
		
		

	/*fcount++;
	var td14 = tr.insertCell(14);
	td14.setAttribute("colSpan","5");
	td14.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1002' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
	fcount++;
	var td15 = tr.insertCell(15);
	td15.setAttribute("colSpan","5");
	td15.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1003' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td16 = tr.insertCell(16);
	td16.setAttribute("colSpan","5");
	td16.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1004' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
	fcount++;
	var td17 = tr.insertCell(17);
	td17.setAttribute("colSpan","5");
	td17.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1005' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
				
	fcount++;
	var td18 = tr.insertCell(18);
	td18.setAttribute("colSpan","5");
	td18.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1006' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";*/
	numI.setAttribute("value",num+1);
	countNum.setAttribute("value",fcount+1);
}




//通用增加行
function AddRow() {
	//alert("AddRow_sp111111");
	var numI = document.getElementById("numI1");
	var num = Number(document.getElementById("numI1").value);
	
	var countNum = document.getElementById("fcount");
	var fcount = Number(document.getElementById("fcount").value);
	
	//alert(num+"---"+fcount);
	
	var obj=document.getElementById("OwnershipStructure");
	var tr = obj.rows["StrRight"];
	var tr = obj.insertRow(tr.rowIndex+num);
	
	var td0 = tr.insertCell(0);
	td0.setAttribute("colSpan","3");
	td0.setAttribute("align","center");
	
	td0.innerHTML = num;
	
	
	fcount++;
	var td1 = tr.insertCell(1);
	td1.setAttribute("colSpan","4");
	td1.innerHTML = 
		"<textarea rows=4 style=\"width:90%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='989' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td2 = tr.insertCell(2);
	td2.setAttribute("colSpan","5");
	td2.setAttribute("bgColor","#ffffff");
	td2.innerHTML = "<select name=\"fieldContents["+fcount+"].fieldText\" onchange=\"A30fun1(this);\">" + 
	 	"<option value=-1>请选择</option>"+document.getElementById("matlCate").value + "</select>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='990' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td3 = tr.insertCell(3);
	td3.setAttribute("colSpan","6");
	td3.innerHTML = "<textarea rows=4 style=\"width:90%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText ></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='991' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td4 = tr.insertCell(4);
	td4.setAttribute("colSpan","20");
	td4.innerHTML = "<textarea rows=4 maxlength=40 style=\"width:96%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
			"name=fieldContents["+fcount+"].fieldText onblur=\"A30fun2(this);\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='992' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td5 = tr.insertCell(5);
	td5.setAttribute("colSpan","12");
	td5.innerHTML = "<textarea rows=4 maxlength=30 style=\"width:93%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" " +
			"name=fieldContents["+fcount+"].fieldText onblur=\"A30fun2(this);\"></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='993' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td6 = tr.insertCell(6);
	td6.setAttribute("colSpan","5");
	td6.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='994' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td7 = tr.insertCell(7);
	td7.setAttribute("colSpan","10");
	td7.innerHTML = "<textarea rows=4 maxlength=18 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='995' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td8 = tr.insertCell(8);
	td8.setAttribute("colSpan","10");
	td8.innerHTML = "<textarea rows=4 maxlength=18 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='996' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td9 = tr.insertCell(9);
	td9.setAttribute("colSpan","5");
	td9.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='997' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td10 = tr.insertCell(10);
	td10.setAttribute("colSpan","5");
	td10.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='998' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td11 = tr.insertCell(11);
	td11.setAttribute("colSpan","5");
	td11.innerHTML = "<textarea rows=4 style=\"width:86%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='999' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td12 = tr.insertCell(12);
	td12.setAttribute("colSpan","5");
	td12.innerHTML = "<textarea rows=4 style=\"width:84%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1000' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td13 = tr.insertCell(13);
	td13.setAttribute("colSpan","5");
	td13.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1001' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
		
		

	/*fcount++;
	var td14 = tr.insertCell(14);
	td14.setAttribute("colSpan","5");
	td14.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1002' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
	fcount++;
	var td15 = tr.insertCell(15);
	td15.setAttribute("colSpan","5");
	td15.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1003' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
	
	fcount++;
	var td16 = tr.insertCell(16);
	td16.setAttribute("colSpan","5");
	td16.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1004' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
			
	fcount++;
	var td17 = tr.insertCell(17);
	td17.setAttribute("colSpan","5");
	td17.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1005' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";
				
	fcount++;
	var td18 = tr.insertCell(18);
	td18.setAttribute("colSpan","5");
	td18.innerHTML = "<textarea rows=4 style=\"width:88%;overflow:hidden\" id=\"fieldContents["+fcount+"].fieldText\" name=fieldContents["+fcount+"].fieldText></textarea>"+
		"<input type=hidden name=\"fieldContents["+fcount+"].fieldId\" value='1006' />" +
		"<input type=hidden name=\"fieldContents["+fcount+"].rowId\" value='"+num+"' />";*/
	numI.setAttribute("value",num+1);
	countNum.setAttribute("value",fcount+1);
}



//通用删除行
function DelRow(numI,strRight){ 
	//alert("DelRow_sp");
	var hiddenNumI = document.getElementById(numI);
	var num = Number(document.getElementById(numI).value);
	
	var countNum = document.getElementById("fcount");
	var fcount = Number(document.getElementById("fcount").value);
	
	if(num>2){
		var obj=document.getElementById("OwnershipStructure"); 
		var tr= obj.rows[strRight]; 
		obj.deleteRow(tr.rowIndex+num-1);  
		hiddenNumI.setAttribute("value",num-1); 
		
		countNum.setAttribute("value",fcount-3);
	}
}

