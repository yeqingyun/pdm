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

<style type="text/css" rel="stylesheet" >
.allBox{width:988px;margin:10px auto;overflow:hidden;}
.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{height:32px;line-height:32px;color:#78503d;font-size:14px;}
.allTit p b{padding:0 10px;}
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
.table01 {
width:900px; 
margin:10px auto;
color:#666;
border-collapse:collapse;
border-spacing:0;
border-left:1px solid #ccc;
border-top:1px solid #ccc;
background:#fff;
}

.table01 a{text-decoration:none;}
.table01 a:hover{color:#ab0000;text-decoration:underline;}
.table01 th , .table01 td{padding:0px 10px;border-right:1px solid #ccc;text-align:center;font-size:12px;text-align:center;height:32px;line-height:32px;border-bottom:1px solid #ccc;}
.table01 th{font-weight:bold;background:#eee;}
.colorRed{color:#b35a48;}
.colorBlue{color:#3f94cf;}
.butList{float:right;}
.butList li{float:left;}
.butList btn{margin:0 5px;color:#fff;background:#F58541;border:#b76e27 solid 1px;height:30px;padding:0 10px; border-radius:3px;cursor:pointer;}
.butList btn:hover{background:#db9a72;}
.padLR5{padding:0 5px;}
.wfbigbtn1 {
	text-align:center
}
</style>

<script type="text/javascript">
	function scall() {
	}
	window.onscroll = scall;
	//附件上传--新
	//附件上传--新
	function preUploadFile(prjtNo,wfNo,taskId,cateId) {
		
		var gngile_uploadURL = $('#gngile_upload_URL').val();
		var server_URL = $('#server_URL').val();
		
		var tempPar = '&tempParams='+"wfNo:"+wfNo+",taskId:"+taskId+",cateId:"+cateId+",uploadType:"+$("#uploadType" ).val(); 
   		if(prjtNo){
   			tempPar =tempPar+",prjtNo:"+prjtNo;
   		}
   		
   		var url = gngile_uploadURL
   		      /**RUL后面跟的参数 **/
   	  	         +'?syId='+$("#syId").val()    //系统ID
   	  	         +'&syNm='+$("#syNm").val()   //系统名字
   	  	         +'&usrId='+$("#usrId" ).val() //用户ID
   	  	         +'&usrNm='+$("#usrNm" ).val() //用户名字
   	  	         +'&diyFolder='+"PDM//WFDOC" //文件上传的目录中自定义部分
   	  	       //  +'&uploadType='+$("#uploadType" ).val() //文件上传类型 
   	  	    
   	  	         +'&callBackUrl='+server_URL+"/afterUploadAction!wfAfterUpload.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
   		         +tempPar;
   	  	         //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
   	          /**RUL后面跟的参数 **/
	    	alert(url);
   		url=encodeURI(url);  //encodeURI
   		//var dialog = $.ligerDialog.open({title:'上传附件', height: 200, width: 470,url:url,isResize: true, name :'dlgUploader'});
      	var dialog = $.ligerDialog.open({ url: url, height: 200,width: 490, buttons: [ { text: '完成', 
      	onclick: function (item, dialog) { dialog.close(); 
      	 window.location = "./WfRdView!viewDocCate.shtml?wfRd.wfNo="+wfNo+"&currentTaskId="+$("#currentTaskId").val()+"&taskStepId="+$("#taskStepId").val();
      	 } } ] });
		
		$(".l-dialog-close").click(function() {
			 window.location = "./WfRdView!viewDocCate.shtml?wfRd.wfNo="+wfNo+"&currentTaskId="+$("#currentTaskId").val()+"&taskStepId="+$("#taskStepId").val();
		});
		
	}
	
	/**
	function dowDoc(docId){
		$.get("WfDoc!checkDocAuth.shtml?wfDoc.docId="+docId,
		function(data) {
			var oj = JSON.parse(data);
			if(oj.result==-1){
				alert("下载错误");
			}else if(oj.result==0){
				alert("没有权限下载");
			}else if(oj.result==1){
				 window.location ="./WfDoc!dow.shtml?wfDoc.docId="+docId;
			}
		}, "text");
	}
	**/
</script>
<script src="./include/js/dowWfDoc.js" type="text/javascript"></script>
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
</head>
<body>
   
 <form id="wfRd-form" name="wfRd-form">
    <input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
    <input type="hidden" id="stepId" name="wfStep.stepId" value="<c:out value="${currentTask.stepId}"/>">
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
	<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>"/>
	<input type="hidden" id="cateId" />
	
	<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>">
	<input type="hidden" id="currentTaskId" name="currentTaskId" value="<c:out value="${currentTaskId}"/>">
	<input type="hidden" id="wfRd.projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>">
	
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" />
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" />
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" /> 
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
	
	<input type="hidden" id="uploadType" name="uploadType" size="30" value="<c:out value="${uploadType}"/>" />
	<input type="hidden" id="wfDocId" name="wfDocId" size="30" value="<c:out value="${wfDocId}"/>" />
    <table class="table01">
    	<thead>
          <tr>
		    <th>最终附件类别</th>
		    <th>附件(点击可下载)</th>
		    <th>步骤</th>
		    <th>版本</th>
			<th>上传人</th>
			<th>上传时间</th>
         </tr>
       </thead>
       <tbody>
        <c:forEach items="${wfDocs}" var="wfDoc">
			<tr>
  				<td>
			       <c:if test="${wfDoc.cateName!=null}">
			         <c:out value="${wfDoc.cateName}"/>
			         <c:if test="${wfDoc.isCurrntDoc==1}">
			            <font color="red">&nbsp;&nbsp;*</font>
			         </c:if>
			         <input type="hidden" id="cate<c:out value="${wfDoc.cateId}"/>"  value="<c:out value="${wfDoc.cateName}"/>"  />
			      </c:if>
      			 <c:if test="${wfDoc.docName==null&&wfDoc.isCurrntDoc==1}">
        			 <input type="hidden" id="griddoccate<c:out value="${wfDoc.cateId}"/>"  name= "docCate"  value="<c:out value="${wfDoc.cateId}"/>"/>
                 </c:if>
    		</td>
			<td>
          	 <c:if test="${wfDoc.docName!=null}">
	         	 <img align="top" src="./include/img/workflow/<c:out value="${wfDoc.icon}"/>"/>
			 	 <a href="javascript:dowDoc(<c:out value="${wfDoc.docId}"/>)">  
			 	
			         <c:choose>
			             <c:when test="${fn:length(wfDoc.docName) > 45}">
			              	<c:out value="${fn:substring(wfDoc.docName, 0, 45)}..." />
			    		 </c:when>
			        	<c:otherwise>
			      			<c:out value="${wfDoc.docName}" />
			     	    </c:otherwise>
		            </c:choose>
				</a>
          </c:if>
		</td>

		<td>
	        <c:choose>
			    <c:when test="${wfDoc.stepName==null}"></c:when>
		        <c:otherwise>
			    	<c:out value="${wfDoc.stepName}"/>
		        </c:otherwise>
	        </c:choose>
        </td>
	    <td><c:out value="${wfDoc.docVer}"/></td>
        <td><c:out value="${wfDoc.createName}"/></td>
        <td>
			<c:if test="${wfDoc.docId != null}">
	        	<fmt:formatDate  type="both"  value="${wfDoc.createDate}"/>
			</c:if>
		</td>
		
	</tr>
        </c:forEach>
    </tbody>
 </table>

 


	<%@ include file="userpop.jsp"%>
  </form>
</body>
</html>