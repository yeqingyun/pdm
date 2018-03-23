<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="./include/css/global.css" />
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" / >
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/gnwf/wfRd.js"></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>

<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>

<%@ include file="IncudeExtendField.jsp"%>
<link type="text/css" rel="stylesheet" href="./include/css/filed.css"/>
<!-- 冲突 -->
<!-- <script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script> -->
<link rel="stylesheet" href="./include/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="./include/kindeditor-4.1.10/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="./include/kindeditor-4.1.10/lang/zh_CN.js"></script>

<script type="text/javascript">
//同时初始化多个kindeditor
//KindEditor网页编辑器
//如果有属性editor="true",则说明它是KindEditor
//如果有disabled="disabled"属性,则是不可编辑状态
$(function () {
	KindEditor.ready(function(K) {
		$('textarea[editor="true"]').each(function(){
			var k = K.create(this, {
				resizeType :1,
				shadowMode : false,
				allowPreviewEmoticons : false,
				urlType : 'absolute',
				uploadJson : './WfQues!uploadImg.shtml',
				fileManagerJson : './WfQues!fileManager.shtml',
				height :"500",
				width:"680",
				allowFileManager : true,
				afterCreate : function() {
					this.sync();
				},
		        afterBlur:function(){
		            this.sync();
		        }
			});
			if(typeof( $(this).attr("readonly")) != "undefined"){
				k.readonly($(this).attr("readonly"));//设置只读属性
			}else if(typeof( $(this).attr("disabled")) != "undefined"){
				k.readonly($(this).attr("disabled"));//设置只读属性
			}
		});
	});
	
	var readonlyColor="#F0F0F0";//不可编辑颜色
	var editColor="#FFFFFF";//可以编辑的颜色
	$('select, input:text, input:checkbox, input:password, textarea[editor!="true"]').each(function(){
		var readonly=true;
		if(typeof( $(this).attr("readonly")) != "undefined"){
			//$(this).attr('disabled',$(this).attr("readonly"));//如果是只读,则设置为不可编辑
			if($(this).attr("readonly")){//如果是只读,则设置为灰色
				$(this).css("background-color",readonlyColor);//
				readonly=false;
			}else{
				$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
			}
		}else{
			$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
		}
		if(readonly){
			if(typeof( $(this).attr("disabled")) != "undefined"){
				//$(this).attr('disabled',$(this).attr("readonly"));//如果是只读,则设置为不可编辑
				if($(this).attr("disabled")){//如果是只读,则设置为灰色
					$(this).css("background-color",readonlyColor);//
				}else{
					$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
				}
			}else{
				$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
			}
		}
	});
});
</script>



<style type="text/css" rel="stylesheet" >
.allBox{width:988px;margin:10px auto;overflow:hidden;}
.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{padding:0 10px;line-height:32px;color:#78503d;font-size:14px;}
/* .allTit p b{padding:0 10px;} */
.allTit p span{padding-right:40px;}
.borBRed{border-bottom:#ffd4c1 1px solid;}
.area01{margin:10px 0;border:#ccc solid 1px;width:986px;}
.titBox{background:url(./include/img/workflow/rpx.png) repeat-x 0 0;padding:0 10px;height:32px;line-height:32px;border-bottom:#b7cee2 solid 1px;}
.titBox h3{font-size:16px;float:left;color:#515c68;font-weight:700;}
.titBox .butonList{margin-top:4px;float:right;}
.titBox .butonList li{float:left;}
.titBox .butonList li a{line-height:19px;padding:0 10px;text-decoration:none;margin:0 5px;font-size:12px;color:#fff; display:block;height:height:19px;border:#23537d solid 1px; border-radius:3px;background:#3670F2;}
.titBox .butonList li a:hover{background:#669bca;}
.processPicture{width:944px;text-align:center;margin:10px auto;margin-top:20px;}
.processSection{width:118px;display:inline-block;margin:0 -4px;margin-bottom:10px;}
.PSTit{font-size:12px;height:66px;overflow:hidden;color:#666;line-height:22px;padding:0 17px;width:90px;}
*+ html .processSection { display:inline;zoom:1;}
.innerTable {margin:0 auto;}
.table01 {width:947px; margin:10px auto;color:#666;border-collapse:collapse;border-spacing:0;border-left:1px solid #ccc;border-top:1px solid #ccc;background:#fff;}
.table01 a{text-decoration:none;}
.table01 a:hover{color:#ab0000;text-decoration:underline;}
.table01 th , .table01 td{padding:0px 10px;border-right:1px solid #ccc;text-align:center;font-size:12px;text-align:center;height:32px;line-height:32px;border-bottom:1px solid #ccc;}
.table01 th{font-weight:bold;background:#eee;}
.colorRed{color:#b35a48;}
.colorBlue{color:#3f94cf;}
.butList{float:right;}
.butList li{float:left;}
.butList btn{margin:0 5px;color:#fff;background:#F58541;border:#b76e27 solid 1px;height:50px;padding:0 10px; border-radius:3px;cursor:pointer;}
.butList btn:hover{background:#db9a72;}
.padLR5{padding:0 5px;}
</style>

<script type="text/javascript">
	function scall() {/*
		var top = 30;
		var scTop = $('body,html').scrollTop();
		if(scTop==null || scTop==''){
			scTop = document.body.scrollTop;	//google chrome取值
		}
		
		if(scTop>=top){
			document.getElementById('toolbar').style.position='fixed';
			document.getElementById('toolbar').style.width='100%';
			document.getElementById('toolbar').style.top='0px';
		}else{
			document.getElementById('toolbar').style.position='';
			document.getElementById('toolbar').style.top='';
		}*/
	}
	window.onscroll = scall;

	$(function() {
		$.post("ligerToolBar!custom.shtml", {
			parm : 'WfRd',
			pageType : '11'
		}, function(data) {
			$("#toolbar").ligerToolBar(data);
		}, "json");
	});

	
	var childListGridManager;
	$(function() {
		$("#childListGrid")
				.ligerGrid(
						{
							columns : [
									{
										display : '流程编号',
										name : 'wfNo',
										align : 'center',
										width : 110,
										render : function(row) {
											var innerHtml = "<a href='#' onclick=\"openRelateFlow('"
													+ row.wfNo
													+ "')\">"
													+ row.wfNo + "</a>";
											return innerHtml;
										}
									}, {
										display : '流程名称',
										name : 'wfName',
										align : 'left',
										width : 120
									}, {
										display : '实际开始时间',
										name : 'factSDate',
										align : 'center',
										width : 100
									}, {
										display : '实际完成时间',
										name : 'factEDate',
										align : 'center',
										width : 100
									}, {
										display : '状态',
										name : 'status',
										align : 'center',
										width : 60,
										render : function(row) {
											var innerHtml = "<font color='";
											var v;
											if (row.status == 0) {
												innerHtml += "red";
												v = "待处理";
											} else if (row.status == 1) {
												innerHtml += "blue";
												v = "办理中";
											} else if (row.status == 2) {
												innerHtml += "green";
												v = "已完成";
											} else if (row.status == 3) {
												innerHtml += "red";
												v = "已关闭";
											} else if (row.status == 4) {
												innerHtml += "red";
												v = "已作废";
											}
											innerHtml += "'>" + v + "</font>";
											return innerHtml;
										}
									}, {
										display : '发起人',
										name : 'createName',
										align : 'center',
										width : 90
									}, {
										display : '发起时间',
										name : 'createDate',
										align : 'center',
										width : 100
									} ],
							url : './WfRd!selChildList.shtml?wfRd.wfNo='
									+ $("#wfNo").val(),
							usePager : false,
							width : '700',
							isChecked : f_isChecked,
							onCheckRow : f_onCheckRow,
							onCheckRow : f_onCheckRow,
							onCheckAllRow : f_onCheckAllRow
						});
		childListGridManager = $("#childListGrid").ligerGetGridManager();
	});

	$(function() {
		$("#navProjectTab").ligerTab({
			onBeforeSelectTabItem : function(tabid) {
				if (tabid == '4') {
					window.frames["quesIframe"].reloadGrid();
				}
				if (tabid == '2') {
					wfdocGridManager.loadData();
				}
			}
		});
	});

	function f_click(tabid) {
		$("#navProjectTab").ligerTab();
		var navtab = $("#navProjectTab").ligerGetTabManager();
		navtab.selectTabItem(tabid);
	}
	
	function addQuestion(isFromWf) {
		if(isFromWf){
			if ($('#wfNo') && $('#wfNo').val() && $('#wfNo').val() != '') {
				DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?wfQues.taskId='
						+ $('#taskStepId').val() + '&wfQues.wfNo=' + document.getElementById("wfRd.wfNo").value+'&wfQues.prjtNo='+document.getElementById("wfRd.projectNo").value+'&isFromWf=' + isFromWf);
			} else {
				$.ligerDialog.warn('没有对应流程,不能添加问题。');
			}
		}else{
			DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?isFromWf='+isFromWf);
		}
	}
	
	var DialogMgr = (function() {
		var dialog;
		return {
			create : function(title, url) {
				dialog = $.ligerDialog.open({
					title : title,
					height : 560,
					width : 810,
					url : url,
					showMax : true,
					showToggle : true,
					showMin : true,
					isResize : true
				})

				// }
			},
			close : function() {
				if (dialog) {
					dialog.close();
				}
			}
		};
	})();
	
	
	
	function importNewQues() {
		$.ligerDialog.confirm('确定要导入新记录？', function(type) {
			if (type) {
				if (document.getElementById("wfRd.wfNo") && document.getElementById("wfRd.wfNo").value && document.getElementById("wfRd.wfNo").value != '') {
					var url ='./WfQues!importNewQues.shtml' + '?wfQues.wfNo='+ document.getElementById("wfRd.wfNo").value;
					var dialog = $.ligerDialog.open({ url: url, height: 300,width: 600, 
						buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
				} else {
					$.ligerDialog.warn('没有对应流程,不能添加问题。');
				}
			}
		});
	}
	
	  
	function uplTemp() {
		window.location.href = './include/template/question_template.xls';
	}
	
	
	
	function stopWfinfo(){
		$.ligerDialog.confirm('确定要终止流程？', function(type) {
			if (type) {
				document.getElementById("wfRd-form").action="WfRdView!stopWfinfo.shtml";
				document.getElementById("wfRd-form").target="_self";
				document.getElementById("wfRd-form").submit();
			}
		});
		
	}
	
	
</script>
<script type="text/javascript">
	$(function() {
		//clickShow($("#applicationBtn1"), $("#applicationForm1"),true);
		//clickShow($("#applicationBtn2"), $("#applicationForm2"),true);
		//clickShow($("#applicationBtn3"), $("#applicationForm3"));
		//clickShow($("#applicationBtn4"), $("#applicationForm4"),true);
		//clickShow($("#applicationBtn5"), $("#applicationForm5"));
		//clickShow($("#applicationBtn6"), $("#applicationForm6"));
		//clickShow($("#applicationBtn7"), $("#applicationForm7"));
		//clickShow($("#applicationBtn9"), $("#applicationForm9"));
	})
	function backJob2(wfNo,taskId){			//主板面板收回任务
		if(confirm("您真的要收回任务吗？")){
			//document.getElementById("currentTask.remark").value=str;
			//alert(document.getElementById("currentTask.remark").value);
			document.getElementById("wfRd-form").action="WfRdView!newBackJobforma.shtml?taskId="+taskId
			document.getElementById("wfRd-form").target="_self";
			document.getElementById("wfRd-form").submit();
		}
	}
	function clickShow(btn, con, show) {
		if (show) {
			con.show();
			btn.html("点击隐藏");
		} else {
			con.hide(); //默认隐藏
			btn.html("点击展开");
		}
		btn.bind('click', function() {
			if (con.is(":hidden")) {
				con.slideDown();
				$(this).html("点击隐藏");
			} else {
				con.slideUp();
				$(this).html("点击展开");
			}
		})
	}
	var showQuesManager = false;
	function showQues(){
		 if(showQuesManager){
			    document.getElementById("ques").style.display = "none";
				document.getElementById("showQuesBtn").innerHTML= "展开风险";
	    		//window.frames["managerQuesIframe"].reloadGrid();
	    		showQuesManager = false;
		 }else{
			    document.getElementById("ques").style.display = "";
				document.getElementById("showQuesBtn").innerHTML= "收起风险";
				showQuesManager = true;
		 }
	}
</script>
<style type="text/css">
.u32_line {
	background-image: url('./include/images/line2.png');
	width: 912px;
	height: 3px;
}

.l-form {
	MARGIN-LEFT: auto;
	MARGIN-RIGHT: auto;
	width: 700px;
	background-color: #FFFEF8;
	width: 700px;
}

.lbcs {
	font-weight: bold
}

.innerTable th,.innerTable td {
	border-right: 1px solid #9FC2E5;
	border-bottom: 1px solid #9FC2E5;
	background: #fff;
	text-align: left;
	padding: 0 7px;
	
}
.innerTable th{
font-size: 14px;
font-weight: bold;
}
</style>
<script type="text/javascript">
var showSendEmailWin=null;
//打开发送窗口
function openEmail(){
	var prjtNo=$(document.getElementById("wfRd.projectNo")).val();
	if(prjtNo && (""+prjtNo).length>0){
		var url = './WfRd!showSendMail.shtml?prjtDef.prjtNo='+prjtNo
		+"&prjtDef.typId=1";//1 研发管理
		showSendEmailWin = $.ligerDialog.open({
			title : '发送邮件窗口',
			height : 600,
			width : 1000,
			url : url
		});
	}else{
		$.ligerDialog.warn('没有对项目,不能发邮件。');
	}
}
$(function(){
	//如果有 项目编号, 则可以发邮件,显示button
	var prjtNo=$(document.getElementById("wfRd.projectNo")).val();
	if(prjtNo && (""+prjtNo).length>0){
		$("#openEmailBtn").show();
	}else{
		$("#openEmailBtn").hide();
	}
})
</script>
</head>




<body>
    <input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
    <input type="hidden" id="stepId" name="wfStep.stepId" value="<c:out value="${currentTask.stepId}"/>">
    <br>
 <form id="wfRd-form" name="wfRd-form"   method="post">
<!-- 写入可以编辑的fieldId -->
		<c:forEach items="${fieldContents}" var="step" varStatus="s">
			<c:choose>
				<c:when test="${step.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${step.count}"/>].fieldId" name="fieldContents[<c:out value="${step.count}"/>].fieldId" value="<c:out value="${step.fieldId}"/>">
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
	<input type="hidden" id="wfRd.wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
	<input type="hidden" id="wfRd.flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>">
	<input type="hidden" id="currentTask.taskId" name="currentTask.taskId" value="<c:out value="${currentTask.taskId}"/>">
	<input type="hidden" id="currentTask.preTaskId" name="currentTask.preTaskId" value="<c:out value="${currentTask.preTaskId}"/>">
	<input type="hidden" id="currentTask.stepId" name="currentTask.stepId" value="<c:out value="${currentTask.stepId}"/>">
	<input type="hidden" id="currentTask.sort" name="currentTask.sort" value="<c:out value="${currentTask.sort}"/>">
	<input type="hidden" id="currentTask.createBy" name="currentTask.createBy" value="<c:out value="${currentTask.createBy}"/>">
	<input type="hidden" id="docNames" name="currentTask.docNames" value="<c:out value="${currentTask.docNames}"/>">
	<input type="hidden" id="checkReSub" name="checkReSub" value="<c:out value="${checkReSub}"/>">
	<input type="hidden" id="agentBy" name="agentBy" value="<c:out value="${agentBy}"/>">
	<input type="hidden" id="isFillQues" value="<c:out value="${currentTask.isFillQues}"/>">
	<input type="hidden" id="isDQAJob" value="<c:out value="${currentTask.isDQAJob}"/>">
	<input type="hidden" id="tips" value="<c:out value="${tips}"/>">
	<input type="hidden" id="curUserId" name="curUserId" value="<c:out value="${user.id}"/>">
	<input type="hidden" id="docFileId" name="wfDoc.docId" value="">
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
	<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>"/>
	<input type="hidden" id="cateId" />
	
	<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>">
	<input type="hidden" id="currentTaskId" name="currentTaskId" value="<c:out value="${currentTaskId}"/>">
	<input type="hidden" id="wfRd.projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>">
	<input type="hidden" id="isFromMyTask" name="isFromMyTask" size="30" value="<c:out value="${isFromMyTask}"/>" />
	
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" />
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" />
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" /> 
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
	
	<input type="hidden" id="uploadType" name="uploadType" size="30" value="<c:out value="${uploadType}"/>" />
	<input type="hidden" id="wfDocId" name="wfDocId" size="30" value="<c:out value="${wfDocId}"/>" />
	<!--  
	wfRd.flowId ：<c:out value="${wfRd.flowId}" />
	-->
	<div class="allBox">
     
		 <c:choose>
          <c:when test="${prjtDef.prjtNm!=null}">
             <div class="allTit" id = "台头">
            <p class="borBRed">
                <span><b>项目名称：</b><c:out value="${prjtDef.prjtNm}" /></span>
                <span><b>所属工作流：</b><c:out value="${cfg.flowName}" /></span>
                <span><b>发起人：</b><c:if test="${wfRd.createBy==-1}">
								系统
							</c:if> <c:if test="${wfRd.createBy!=-1}">
									<c:out value="${wfRd.createName}" />
								</c:if></span>
                <span><b>状态：</b><c:choose>
									<c:when test="${currentTask.status==0}">
										待处理
									</c:when>
									<c:when test="${currentTask.status==1}">
										办理中
									</c:when>
									<c:when test="${currentTask.status==2}">
										已完成
									</c:when>
									<c:when test="${currentTask.status==3}">
										已关闭
									</c:when>
									<c:when test="${currentTask.status==4}">
										已作废
									</c:when>
							</c:choose></span>
                <span><b>流程编号：</b><c:out value="${wfRd.wfNo}" /></span>
            </p>
           </div>
          </c:when>
          <c:otherwise>
          
              <div class="allTit" id = "台头">
            <p class="borBRed">
            	<span><b>流程编号：</b><c:out value="${wfRd.wfNo}" /></span>
                <span><b>工作流名称：</b><c:out value="${wfRd.wfName}" /></span>
                <span><b>发起人：</b><c:if test="${wfRd.createBy==-1}">
								系统
							</c:if> <c:if test="${wfRd.createBy!=-1}">
									<c:out value="${wfRd.createName}" />
								</c:if></span>
                				<span><b>状态：</b><c:choose>
									<c:when test="${wfRd.status==0}">
										待处理
									</c:when>
									<c:when test="${wfRd.status==1}">
										办理中
									</c:when>
									<c:when test="${wfRd.status==2}">
										已完成
									</c:when>
									<c:when test="${wfRd.status==3}">
										已关闭
									</c:when>
									<c:when test="${wfRd.status==4}">
										已作废
									</c:when>
							</c:choose></span>
                
                </br><span><b>工作流描述：</b><c:out value="${wfRd.wfDesc}" /></span>
            </p>
        </div>
          
          </c:otherwise>
       </c:choose>
		
        

        <div class="area01" id="流程进度">
            <div class="titBox">
                <h3>流程进度</h3>
            </div>
            
            <div class="area01Con">
                <div class="processPicture">
                          <c:forEach items="${imgStepList}" var="step" varStatus="s">
	                           <div class="processSection">
		                             <c:choose>
										<c:when test="${step.isCurrent==1}"><img src="./include/img/workflow/step02.png"/></c:when>
										<c:when test="${step.isCurrent!=1 && !empty step.taskTime}"><img src="./include/img/workflow/step01.png"/></c:when>
										<c:when test="${step.isCurrent!=1 && empty step.taskTime}"><img src="./include/img/workflow/step03.png"/></c:when>
									 </c:choose>
			                         <div   class="PSTit">
			                            <c:out value="${step.stepName}" /><br/>
			                            <fmt:formatDate pattern="yyyy-MM-dd" value="${step.taskTime}" type="both" />
			                         </div>
			                    </div>
						</c:forEach>
						
						<c:if test="${fn:length(imgStepList)>8}">
						    <c:set var="imgStepListSize" value="${fn:length(imgStepList)}" />
							<c:forEach begin="0" end="${24-imgStepListSize}" >
							          <div class="processSection">
							          </div>
							</c:forEach>
						</c:if>
						
                </div>
                
                <table class="table01">
                <p align=center><font size="3" color="black" ><c:out value="${cfg.flowName}" /></font></p>
                <p align=center><font size="3" color="red" >注：此页面为预览页面，如果有需要您操作的步骤请到"我的任务"模块！</font></p>
                    <thead>
                        <tr>
                            <th>步骤次序</th>
                            <th>步骤名称</th>
                            <th>分发人</th>
                            <th>分发时间</th>
                            <th>主办人</th>
                            <th>完成时间</th>
                            <th>状态/操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${taskList}" var="wfRdTask">
						<tr>
							<td >第<c:out value="${wfRdTask.sort}"/>步</td>
							<td style="text-align:left;" ><c:out value="${wfRdTask.stepName}"/><c:if test="${wfRdTask.taskType==2}">(协办)</c:if></td>
							
							<td ><c:out value="${wfRdTask.createrName}"/></td>
							<td ><fmt:formatDate  type="both"  value="${wfRdTask.createDate}"/></td>
							<td>
							  <c:out value="${wfRdTask.acceptName}"/>
							  <c:if test="${wfRdTask.acptRoleName!=null}">--<c:out value="${wfRdTask.acptRoleName}"/></c:if>
							</td>
							<td ><fmt:formatDate type="both"   value="${wfRdTask.endDate}"/></td>
							<td >
							     <c:choose>
										<c:when test="${wfRdTask.status==0}">
											<font color="gray">待接收</font>
										</c:when>
										<c:when test="${wfRdTask.status==1}">
											<font color="blue">办理中</font>
										</c:when>
										<c:when test="${wfRdTask.status==2}">
											<font color="green">已完成</font>
										</c:when>
										<c:when test="${wfRdTask.status==3}">
											<font color="red">已完成</font> 
										</c:when>
										<c:when test="${wfRdTask.status==4}">
											<font color="red">已作废</font>
										</c:when>
										<c:when test="${wfRdTask.status==5}">
											<font color="green">已收回</font>
										</c:when>
										<c:when test="${wfRdTask.status==6}">
											<font color="green">已退回</font>
										</c:when>
								</c:choose>
							    <c:if test="${wfRdTask.status <= 1 && wfRdTask.acceptBy!= sessionScope.SYUSR.id}">
							     <!--  
							     <input type="button" value=" 催办" class="wfbigbtn2"
							      onclick="pushMail2('<c:out value="${wfRd.wfNo}"/>',<c:out value="${wfRdTask.taskId}"/>);" />
							     -->
							      <a href="javascript:pushMail2('<c:out value="${wfRd.wfNo}"/>',<c:out value="${wfRdTask.taskId}"/>);" class="colorBlue padLR5">催办</a>
							    </c:if>
							     <c:if test="${wfRdTask.status ==0 && wfRdTask.taskType!=2 && wfRdTask.createBy == sessionScope.SYUSR.id}">
											<a href="javascript:backJob2('<c:out value="${wfRd.wfNo}"/>',<c:out value="${wfRdTask.taskId}"/>);"
													class="colorBlue padLR5">收回</a>
											</c:if>   
							      <%--  <c:if test="${currentTask.taskId==wfRdTask.taskId}">
							          <c:if test="${currentTask.status==0}">
							          <!--  
									    <input type="button" value=" 接收" class="wfbigbtn2" 
									    onclick="pushMail(<c:out value="${wfRd.wfNo}"/>,<c:out value="${wfRdTask.taskId}"/>);" />
										<input type="hidden" id="currentTask.taskId" name="currentTask.taskId" value="<c:out value="${unAcceptTask.taskId}"/>">
									  --> 
									    <a href="javascript:acceptJob();" class="colorBlue padLR5">接收</a>
							          </c:if> 
								   </c:if>  --%>
							</td>
						</tr>
					</c:forEach>
                    </tbody>
                </table>
                	<!-- 退回原因 --> <c:if test="${!empty backTaskssList}">
										<p style="font-size: 16px; font-family: serif;"
											title="所转交的任务被退回来了">
											<font color="red">退回原因:
											<c:forEach items="${backTaskssList}" var="back"
												varStatus="s">
												<c:out value="${s.count}" />.<c:out value="${back.remark}" />[<c:out
													value="${back.createrName}" />]；&nbsp;
											</c:forEach></font>
										</p>
										<br>
									</c:if> <!-- 退回原因 --> 
            </div>
        </div>
        
 
	        <div  id = "填写表单">
			  <c:if test="${!empty includeJspUri}">
	            <div class="titBox">
	                <h3>填写表单</h3>
	                <!--<c:out value="${includeJspUri}"/>-->
	                <ul class="butonList">
	                   <li><a name="showWfFormsBtn" id="showWfFormsBtn" href="javascript:showFroms()">收起表单</a></li>
	                  
	                    <li><a name="#applicationBtn5" id="applicationBtn5" href="javascript:printForm()">打印表单</a></li>
	                </ul>
	            </div>
	            
	            
	            <div >
	                <div id="wfForms" >
						<div>
						
						    <jsp:include flush="true" page="${includeJspUri}"></jsp:include>
							
							<br>
						</div>
					</div>
	            </div>
	            
	            
	          </c:if>
	        </div>
	        
	        
	        <!-- 会签记录显示 -->
	          <c:if test="${!empty signList}">
				<div class="area01" id = "会签记录">
            <div class="titBox">
                <h3>会签与意见</h3>
         	</div>
   	
         	<div class="area01Con">
			   
													<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="90%" align=center>
													 <!--  <tr class=TableHeader align="left" >
													    <td>会签与意见</td>
													 </tr> -->
													  <c:forEach items="${signList}" var="sign" varStatus="s">
													  	<tr <c:if test="${s.count%2!=1}">class=TableContent</c:if> <c:if test="${s.count%2==1}">class=TableData</c:if> height="50">
														    <td>第<c:out value="${s.count}"/>步【会签】 
														    	<c:out value="${sign.stepName}"/> 
														    	<U style="CURSOR: hand" title=部门：<c:out value="${sign.deptName}"/>><c:out value="${sign.userName}"/></U>：<br/>
														      	<c:out value="${sign.signText}"/>&nbsp;&nbsp;&nbsp;
														     	<I><fmt:formatDate value="${sign.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></I><br/>
														    </td>
														</tr>
													  </c:forEach>
													</table>
													<br/>
													
            </div>
        </div>
		</c:if>											
       	<!-- 会签记录显示 -->
	        
	        <c:if test="${wfRd.flowId == 41}">
	        <div class="area01" id = "查看风险">
	        	  <div class="titBox">
	                <h3>查看风险</h3>
	                <ul class="butonList">
	                    <li><a name="showQuesBtn" id="showQuesBtn" href="javascript:showQues()">展开风险</a></li>
	                </ul>
	            </div>
	        	 <div class="area01Con">
				     <div id="ques" style="display:none">
						<div  class="listTable clearfix" style="margin: 10px 20px;">
							<table cellpadding=0 cellspacing=0.5 width=98% style="background: #9FC2E5;" class="innerTable" align=center>
								<tr>
									<td colspan="6">
											<div tabid="4" title="风险查看" style="width: 100%; height: 620px;">
												<iframe id="managerQuesIframe" name="managerQuesIframe" frameborder="0"  
												style="width: 100%; height: 100%" 
												src="./WfRisk!wfRdRiskListUI.shtml?wfNo=<c:out value="${wfRd.wfNo}"/>"></iframe>
										</div>
									</td>
								</tr>
							</table>
						</div>
			         </div>
	            </div>
	        </div>
			</c:if>
			
			
		<%-- <c:if test="${showDoc}"> --%>
	     <div class="area01" id = "上传附件">
            <div class="titBox">
                <h3>上传附件</h3>
         	</div>
         	<div class="area01Con">
			     <div id="doc">
					<div  class="listTable clearfix" style="margin: 10px 20px;">
						<table cellpadding=0 cellspacing=0.5 width=100% style="background: #9FC2E5;" class="innerTable" align=center>
							<tr>
								<td colspan="6">
									<div tabid="4" title="上传附件" style="width: 100%; height: 250px;">
										<iframe id="uploadIframe" name="uploadIframe" frameborder="0"  
												style="width: 100%; height: 100%;overflow-x : hidden" 
												src="./WfRdView!wfRdViewWfDoc.shtml?currentTask.status=${currentTask.status}&wfRd.wfNo=<c:out value="${wfRd.wfNo}"/>&currentTaskId=<c:out value="${currentTaskId}"/>&taskStepId=<c:out value="${taskStepId}"/>"></iframe>
									</div>
								</td>
							</tr>
					  </table>
					</div>
		         </div>
            </div>
        </div>
		<%-- </c:if> --%>
	
		
	<c:if test="${wfRd.needQues==1}">
		 <div class="area01" id = "问题列表">
	            <div class="titBox">
	                <h3>提出问题</h3>&nbsp;&nbsp;
	                <input id="uplTempBtn" class="wfbigbtn2" type="button" onclick="javascript:uplTemp()" value="下载问题模板"></input>
	                <ul class="butonList">
	                    <li><a name="showQuesListBtn" id="showQuesListBtn" href="javascript:showQuesList()">展开问题列表</a></li>
	                </ul>
	            </div>
	            <div class="area01Con">
				     <div id="quesList" style="display:none">
						<div  class="listTable clearfix" style="margin: 10px 20px;">
							<table cellpadding=0 cellspacing=0.5 width=95% style="background: #9FC2E5;" class="innerTable" align=center>
								<tr>
									<td colspan="6"><c:if test="${cfg.hasQuesMoudle==0}">
											<div tabid="4" title="问题列表" style="width: 100%; height: 620px;">
												<iframe id="quesIframe" name="quesIframe" frameborder="0" name="showmessage" style="width: 100%; 
												height: 100%" src="./WfQues!taskList.shtml?wfNo=<c:out value="${wfRd.wfNo}"/>"></iframe>
											</div>
										</c:if></td>
								</tr>
							</table>
						</div>
			         </div>
	            </div>
	        </div>
	    </c:if>
	    
	    
	    
	    	
<table width="100%">
        <tr><td>
	    </td><td align="right">
	    <c:forEach items="${taskList}" var="wfRdTask">
	    <c:if test="${wfRdTask.sort==1 && wfRdTask.status <= 1 && wfRdTask.acceptBy == sessionScope.SYUSR.id}">
	       <input type="button"  id = "stopWfinfoBtn"  class="wfbigbtn"  value="终止流程" onclick="stopWfinfo();" />
	       </c:if>
	    </c:forEach>
	    </td></tr></table>
	    
	</div>
	<!-- context -->


	<%@ include file="userpop.jsp"%>
  </form>
  <br>
  <br>
</body>
</html>