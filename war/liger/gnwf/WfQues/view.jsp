<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<%@page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link href="./include/css/rep.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	if ($("#title").length > 0)
		$("#title").ligerTextBox();
	if ($("#result").length > 0)
		$("#result").ligerTextBox();
	/* $("#userIdVal").ligerComboBox({textField:'text', valueField:'id',isMultiSelect: false,
		onSelected: function (newtext)
        {
			$("#userId").val(newtext);
        }	
	}); */
	
	
});
</script>
</head>
<body>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<input type="hidden" id="quesId" name="wfQues.quesId" size="30" value="<c:out value="${wfQues.quesId}"/>"/>
	<input type="hidden" id="scheId" name="wfQues.scheId" size="30" value="<c:out value="${wfQues.scheId}"/>"/>
	<input type="hidden" id="taskId" name="wfQues.taskId" size="30" value="<c:out value="${wfQues.taskId}"/>"/>
	<div class="wrap">
  <div class="wrap_1">
    <h1><c:out value="${wfQues.title}"/></h1>
    <p class="p1"><c:out value="${wfQues.quesText}"  escapeXml="false"/></p>
    <span>创建人：<c:out value="${wfQues.createUsr}"/>
	</span>
    <div class="clear"></div>
  </div>
  <div class="line"></div>
  <div class="wrap_3">
		<ul>
		  <li class="li_rep">
			<div class='title'>
				责任人-解决措施
				<font style="font-size: 12px">
				(
					<c:choose>
						<c:when test="${wfQues.userId == 0}">待定</c:when>
						<c:otherwise>
							<c:out value="${wfQues.usrName}"/>
						</c:otherwise>
					</c:choose>
				)
				</font>
			</div>
			<p class="p1"><c:out value="${wfQues.result}" escapeXml="false"/></p>
			
			<!-- p class="p1"><a href='http://gnfile.gionee.com:28080/gnfs/ProcFile!dow.shtml?fileNo="+row.fileNo+"'>附件下载</a></p  -->
			 <c:if test="${wfQues.showResFileIcon}">
						      	<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
						    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
						    	 <c:out value="${wfQues.resultFileName}"/>
						    	</a>
			</c:if> 
		  </li>
		</ul>
	 </div>
	  <div class="wrap_3">
			<ul>
			  <li class="li_rep">
				<div class='title'>
					验证人-验证效果
					<font style="font-size: 12px">
					(
					<c:choose>
						<c:when test="${wfQues.idtfBy == 0}">待定</c:when>
						<c:otherwise>
							<c:out value="${wfQues.idtfUsr}"/>
						</c:otherwise>
					</c:choose>
				   )
				   </font>
				</div>
				<p class="p1"><c:out value="${wfQues.idtfRes}" escapeXml="false"/></p>
				 <c:if test="${wfQues.showIdFileIcon}">
					      	<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.idFileIcon}"/>"/>
					    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.idtfResFileNo}"/>'>
					    	 <c:out value="${wfQues.idtfResFileName}"/>
					    	</a>
				</c:if>
			  </li>
			</ul>
		</div>
  
  <div class="wrap_3">
	    <ul>
	      <li class="li_t"><span class="title">记录</span></li>
	      <c:forEach items="${wfReplys}" var="reply">
	      <li>
	      	<div class="ico"></div>
	        <p class="p2"><c:out value="${reply.replyText}" escapeXml="false"/></p>
	         <c:if test="${reply.showFileIcon}">
	      	<img align="middle" src="./include/img/workflow/<c:out value="${reply.fileIcon}"/>"/>
	         <a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${reply.fileNo}"/>'>
	    	 <c:out value="${reply.fileName}"/>
	    	</a>
		   </c:if>
	        <span><c:out value="${reply.usrName}"/>|<fmt:formatDate pattern="yyyy-MM-dd" value="${reply.createDate}" type="both"/></span></li>
		   <c:choose>
			 <c:when test="${reply.groupId==oldGpId}">
		        <li style="background-color: #00224C"></span></li>
			 </c:when>
		     <c:otherwise>
		       <c:set var="oldGpId" value="${reply.replyId}"/>
			 </c:otherwise>
		 </c:choose>
	       </c:forEach>
	    </ul>
	  </div>
  
</div>
</form>

</div>

</body>
</html>