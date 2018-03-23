<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<head>
<title>Untitled</title>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>
	
<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>
<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>




<script type="text/javascript"> 

Date.prototype.format = function(f){
    var o ={
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(f))f=f.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(f))f = f.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));return f
}
$(function(){

$.post("WfQues!quesAndRisk.shtml",{'wfNo':$('#wfRd_wfNo').val()},
			function(data) {
			/* alert(JSON.stringify(data));
			alert(data.Rows[0].usrName);  */
			  if(data.Rows[0]){
				  if(data.Rows[0].usrName){
					  document.getElementById("wfQues_usrName").value = data.Rows[0].usrName; 
				  }
				  if(data.Rows[0].createUsr){
					  document.getElementById("wfQues_createUsr").value = data.Rows[0].createUsr; 
				  }
				  if(data.Rows[0].quesText){
					  document.getElementById("wfQues_quesText").value = data.Rows[0].quesText; 
				  }
				  if(data.Rows[0].fileName){
					  document.getElementById("wfQues_fileName").value = data.Rows[0].fileName; 
				  }
				  if(data.Rows[0].fileName){
						 document.getElementById("wfQues_fileName_a").href = "'javascript:downloadOrViewFile("+data.Rows[0].quesId+","+data.Rows[0].fileName+")'"; 
				  }
				  if(data.Rows[0].fileIcon){
					  document.getElementById("wfQues_fileName_img").src = "./include/img/workflow/"+data.Rows[0].fileIcon ;
				  }
				  if(data.Rows[0].status){
					  document.getElementById("wfQues_status").value = data.Rows[0].status; 
				  }
				  if(data.Rows[0].scheId){
					  document.getElementById("wfQues_scheId").value = data.Rows[0].scheId; 
				  }
				  if(data.Rows[0].sourceID){
					  document.getElementById("wfQues_sourceID").value = data.Rows[0].sourceID; 
				  }
				  if(data.Rows[0].quesTypeID){
					  document.getElementById("wfQues_quesTypeID").value = data.Rows[0].quesTypeID; 
				  }
				 
		   } 
			},
			"json"
	      );
	      
$.post("WfQues!quesAndRisk2.shtml",{'wfNo':$('#wfRd_wfNo').val()},
		function(data) {
	
		//alert(JSON.stringify(data)); 
		//alert(data.Rows[0].impTime);
		  if(data.Rows[0]){
			  if(data.Rows[0].riskConsequence){
				  document.getElementById("wfRisk_riskConsequence").value = data.Rows[0].riskConsequence;  
			  }
			  if(data.Rows[0].preventiveMeasures){
				  document.getElementById("wfRisk_preventiveMeasures").value = data.Rows[0].preventiveMeasures;   
			  }
			  if(data.Rows[0].impTime){
				  document.getElementById("wfRisk_impTime").value = data.Rows[0].impTime;   
			  }
			  /* if(data.Rows[0].impTime){
				  var t=data.Rows[0].impTime;
				  var d=new Date();
				  d.setTime(t);
				  var s=d.format('yyyy-MM-dd');

				  document.getElementById("wfRisk_impTime").value =s;  
			  } */
			
	   } 
		},
		"json"
      );
});
   
    function uploadFile(qr_id,upFielType,textId) {
        var gngile_uploadURL = $('#gngile_upload_URL').val();
    	var server_URL = $('#server_URL').val();
    	var url = gngile_uploadURL
          /**RUL后面跟的参数 **/
    	         +'?syId='+$("#syId").val()    //系统ID
    	         +'&syNm='+$("#syNm").val()  //系统名字
    	         +'&usrId='+$("#usrId").val() //用户ID
    	         +'&usrNm='+$("#usrNm").val() //用户名字
    	         +'&diyFolder='+"QuesFile" //文件上传的目录中自定义部分
    	         +'&uploadType='+"BaseLib" 
             //上传的是ProcFile，取值只能是ProcFile或者BaseLib
             //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
             +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
    	         +'&callBackUrl='+server_URL+"/afterUploadAction!quesRespAfterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
             +'&tempParams='+"quesResp.id:"+qr_id+",upFielType:"+upFielType;  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
          /**RUL后面跟的参数 **/
    url=encodeURI(url);  //encodeURI
    //var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
    var dialog = $.ligerDialog.open({ url: url, height: 150,width: 490, buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
    //监听Dialog关闭事件
    $(".l-dialog-winbtns").click(function(){
    	//alert("./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val());
    	//	window.location = "./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val();
    	textId= textId+qr_id;
    	var text = encodeURI(document.getElementById(textId).value);
    	 //alert(document.getElementById(textId).value);
    	 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val()+"&text="+text+"&textId="+textId;
    });
    }
function downloadOrViewFile(wfQuesId,fileName) {
	var picfixArr = ["jpg","bmp","jpeg","png","gif"];
	var fix = fileName.substring(fileName.indexOf(".") + 1,fileName.length);
	if(picfixArr.indexOf(fix) > -1) {
		window.parent.parent.f_open('./WfQues!viewPic.shtml?wfQues.quesId=' + wfQuesId,"预览");
	}else {
		window.location.href = './WfQues!downloadFile.shtml?wfQues.quesId=' + wfQuesId;
	}
}

</script>
</head>

<body>
	<input type="hidden" id="wfRd_wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
     <input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
    <input type="hidden" id="text" name="text" size="30" value="<c:out value="${text}"/>" />
    <input type="hidden" id="textId" name="textId" size="30" value="<c:out value="${textId}"/>" />

	<input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
	
	<input type="hidden" id="wfQues_sourceID" name="wfQues_sourceID" value="<c:out value="${wfQues.sourceID}"/>">
		
	<input type="hidden" id="wfQues_quesId" name="wfQues_quesId" value="<c:out value="${wfQues.quesId}"/>">
	<input type="hidden" id="wfQues_prjtNo" name="wfQues.prjtNo" value="<c:out value="${wfQues.prjtNo}"/>">
	<input type="hidden" id="gpName" name="gpName" size="30" value="<c:out value="${gp.gpName}"/>" />
	<input type="hidden" id="wfQues_status" name="wfQues_status" size="30" value="<c:out value="${wfQues.status}"/>" />
	
	<input type="hidden" id="wfQues_title" name="wfQues_title" size="30" value="<c:out value="${wfQues.title}"/>" />
	
	<input type="hidden" id="sessionScope_SYUSR_id" name="sessionScope_SYUSR_id" size="30" value="<c:out value="${sessionScope.SYUSR.id}"/>" />

	

        
<div class="listTable clearfix" style="margin:10px 20px;" >
		<table cellpadding=0 cellspacing=0.5 width=700px
			style="background: #9FC2E5;" class="innerTable" align=center>
			
			<tr>
				<th nowrap="nowrap">项目名称</th>
				<td colspan="3"  align=center><c:out value="${prjtDef.prjtNm}" /></td>

			</tr>
			<tr>
				<th nowrap="nowrap">提出阶段</th>
				<td nowrap="nowrap"><span >
				<select name="scheId"  id = "wfQues_scheId" disabled>
					<option value="23">小批试产(T1)</option>
								<!-- <option value="44">小批试产(T1-1)</option>
								<option value="45">小批试产(T1-2)</option> -->	
								<option value="28">小批试产(T2)</option>
								<option value="52">小批试产(T3)</option>
								<!-- <option value="46">小批试产(T2-1)</option>
								<option value="47">小批试产(T2-2)</option> -->
								<option value="48">中批试产(PP)</option>
								<!-- <option value="49">中批试产(PP-1)</option>
								<option value="50">中批试产(PP-2)</option> -->
								<option value="51">批量(MP)</option>
				</select>
				</span></td>
				
				<th nowrap="nowrap">问题来源</th>
				<td nowrap="nowrap" id="responsible">
					<select name="scheId"  id = "wfQues_sourceID" disabled>
					<option value="1">
						硬件测试
					</option>
					<option value="2">
						试产组装
					</option>
					<option value="3">
						 试产贴片
					</option>
					<option value="4">
						白盒测试
					</option>
					<option value="5">
						整机测试
					</option>
					<option value="6">
						工厂测试
					</option>
				</select>
				</td>

			</tr>
			<tr>
				<th nowrap="nowrap">问题类别</th>
				<td nowrap="nowrap"><span class="padLR5">
				<select name="scheId"  id = "wfQues_quesTypeID" disabled>
					<!-- <option value="1">
						基带
					</option> -->
					<option value="2">
						射频
					</option>
					<!-- <option value="3">
						音频
					</option> -->
					<option value="4">
						结构
					</option>
					<option value="5">
						外观工艺
					</option>
					<option value="6">
						软件
					</option>
					<option value="7">
						贴片工艺
					</option>
					<option value="8">
						组装工艺
					</option>
					<option value="9">
						物料
					</option>
					<option value="10">
						附配件测试
					</option>
					<option value="11">
						硬件
					</option>
				</select>
				</span></td>
				
				
				<th nowrap="nowrap">状态</th>
				<td nowrap="nowrap"><span class="padLR5">
				<select name="scheId"  id = "wfQues_status" disabled>
					<option value="21" disabled>
						已挂起
					</option>
					<option value="30" disabled>
						已关闭
					</option>
					<option value="40" disabled>
						转风险
					</option>
					<option value="1" disabled>
						待解决
					</option>
					<option value="9" disabled>
						验证未通过
					</option>
					<option value="10" disabled>
						待验证
					</option>
					<option value="11" disabled>
						验证通过
					</option>
				</select>
				</span></td>
			</tr>
			<tr>
				<th>问题描述</th>
				<td colspan="3">
							<input type="text" style="width: 600px; height: 50px;"  readonly id="wfQues_quesText" name="wfQues_quesText" size="30" value="<c:out value="${wfQues.quesText}"/>" />
						
				</td>
			</tr>

			<tr>
				<th nowrap="nowrap">责任人</th>
				<td nowrap="nowrap" id="responsible">
					<input type="text"  readonly id="wfQues_usrName" name="wfQues_usrName" size="30" value="<c:out value="${wfQues.usrName}"/>" />
				</td>


				<th nowrap="nowrap">问题提出者</th>
				<td nowrap="nowrap">
					<input type="text"  readonly id="wfQues_createUsr" name="wfQues_createUsr" size="30" value="<c:out value="${wfQues.createUsr}"/>" />
				</td>



			 <%-- <tr>
                            <th nowrap="nowrap" >附件</th>
                            <td nowrap="nowrap">
                                  <img align="middle"  src="./include/img/workflow/<c:out value="${wfQues.fileIcon}"/>"/>
                                  	<c:choose>
                                  		<c:when test="${fn:contains(wfQues.fileNo,'/')}">
                                  			<a class="colorBlue padLR5" href='javascript:downloadOrViewFile("<c:out value="${wfQues.quesId}"/>","<c:out value="${wfQues.fileName}"/>");' >
                                  		</c:when>
                                  		<c:otherwise>
                                  			<a class="colorBlue padLR5" href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.fileNo}"/>' >
                                  		</c:otherwise>
                                  	</c:choose>
							    	
							    	 <c:out value="${wfQues.fileName}"/>
                                    </a>
                            </td>
                        </tr> --%>
			
			<tr>
				<th nowrap="nowrap">附件</th>
				<td colspan="3">
				<img align="middle" id = "wfQues_fileName_img"/>
					<input type="text"  readonly id="wfQues_fileName" name="wfQues_fileName" size="30" value="<c:out value="${wfQues.fileName}"/>" />
					</td>
			</tr>

		<tr>
				<th>风险说明及风险后果</th>
				<td colspan="3">
					<input type="text" style="width: 600px; height: 50px;"  readonly id="wfRisk_riskConsequence" name="wfRisk_riskConsequence" size="30" value="<c:out value="${wfRisk.riskConsequence}"/>" />
				</td>
				
			</tr>
			<tr>
				<th>拟采取的预防措施</th>
				<td colspan="3">
					<input type="text" style="width: 600px; height: 50px;"  readonly id="wfRisk_preventiveMeasures" name="wfRisk_preventiveMeasures" size="30" value="<c:out value="${wfRisk.preventiveMeasures}"/>" />
				</td>
			</tr>
			<tr>
				<th >拟导入时间</th>
				<td colspan="3">
					<input type="text"   readonly id="wfRisk_impTime" name="wfRisk_impTime" size="30" value="<c:out value="${wfRisk.impTime}"/>" />
				</td>
			</tr>
		</table>
		
		</div></body>
