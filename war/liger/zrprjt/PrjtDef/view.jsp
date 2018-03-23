<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<script src="./plusgantt/scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
<script type="text/javascript">
        $(function ()
        {
        	
        	$("#layout1").ligerLayout({
    			topHeight:25,
    			minLeftWidth:80,
    			minRightWidth:80,
    			leftWidth: 135});

    		//$("#framecenter").ligerTab(); 
    		
    		//tab = $("#framecenter").ligerGetTabManager();
    		
    		$("#tree1").ligerTree({ 
    			     url:'./PrjtDef!loadPrjtTree.shtml', 
		             idFieldName :'prjtNo',
		             textFieldName :'prjtNm',
		             iconFieldName :'iconUri',
		             checkbox :false,
		             onSelect: function (node, e)
		                      { 
		            	        var  prjtNo = node.data.prjtNo;
		            	        document.getElementById("prjtNo").value=prjtNo;
		            	        document.getElementById("prjtNm").value= node.data.prjtNm;
		            	        document.getElementById("taskVersion").value= node.data.taskVersion;
		            	        if(node.data.prjtPointVersion){
			            	        document.getElementById("prjtPointVersion").value= node.data.prjtPointVersion;
		            	        }
		            	       // alert(node.data.prjtPointVersion);
		            	        /* document.getElementById("devDeptNameID").value= node.data.devDeptNameID;
		            	         */
		            	        if ($("#projInfor").length > 0){
				            	  document.getElementById("projInfor").src="./PrjtDef!projInfor.shtml?prjtDef.prjtNo="+prjtNo; 
		            	        }
		            	         if ($("#taskManger").length > 0){
				         	      document.getElementById("taskManger").src="./PrjtDef!taskManger.shtml"; 
		            	         }
		            	         if ($("#projtDoc").length > 0){
				       			   document.getElementById("projtDoc").src="./PrjtDef!projtDoc.shtml?prjtNo="+prjtNo; 
		            	         }
		            	         
		            	         if ($("#scheList").length > 0){
				       			  document.getElementById("scheList").src="./WfQues!scheList.shtml?prjtNo="+prjtNo; 
		            	         }
		            	         
		            	        if ($("#gotoselectWfRd").length > 0){
				       			   document.getElementById("gotoselectWfRd").src="./PrjtDef!gotoselectWfRd.shtml?prjtNo="+prjtNo; 
		            	        }
		            	        if ($("#projtSurvey").length > 0){
		            	        document.getElementById("projtSurvey").src="./PrjtDef!projtSurvey.shtml?prjtNo="+prjtNo; 
		            	        }
		            	        if ($("#PrjtPointFrameAnnex").length > 0){
			            	        document.getElementById("PrjtPointFrameAnnex").src="./PrjtDef!prjtPointFrameAnnex.shtml?prjtNo="+prjtNo; 
			            	        }
		            	    // if ($("#PrjtPointFrame").length > 0){
					         	      document.getElementById("PrjtPointFrame").src="./PrjtDef!prjtPointManger.shtml"; 
			            	 //     }
					         	     
				       			
				       			document.getElementById("pNm").innerHTML = node.data.prjtNm; 
				       			document.getElementById("pNo").innerHTML = node.data.prjtNo; 
				       			if(node.data.status==0){
				       				document.getElementById("pStatus").innerHTML = "无效";
				       			}else if(node.data.status==1){
				       				document.getElementById("pStatus").innerHTML = "新创建";
				       			}else if(node.data.status==2){
				       				document.getElementById("pStatus").innerHTML = "已启动";
				       			}else if(node.data.status==3){
				       				document.getElementById("pStatus").innerHTML = "暂停";
				       			}else if(node.data.status==4){
				       				document.getElementById("pStatus").innerHTML = "已结束";
				       			}
				       			//viewDig.title= "LLLLLLLLLLLLLLL";
		                      }
    			 
    		 });
    		tree = $("#tree1").ligerGetTreeManager();
    		tree.selectNode(document.getElementById("prjtNo").value);
    		
    		//alert(document.getElementById("slePrjNo").value);
    		//tree.selectNode(document.getElementById("slePrjNo").value);
        	
            $("#navProjectTab").ligerTab({contextmenu :false}); 
            tab = $("#navProjectTab").ligerGetTabManager();
        });
        
        
        
        
        
        function f_click(){
        	$("#navProjectTab").ligerTab();
            var navtab = $("#navProjectTab").ligerGetTabManager();
            navtab.selectTabItem("startFlowTab");
        }
        
        
        function refresh(){
        	document.getElementById("projInfor").src="./PrjtDef!projInfor.shtml?prjtDef.prjtNo="+document.getElementById("prjtNo").value; 
        }
        
        function reloadPjtInfo(){
        	//alert("reload");
        	//var sel = tree.getSelected();
        	//alert(sel);
        	//var tree = $("#tree1").ligerGetTreeManager();
        	tree.clear();
        	tree.loadData(null, './PrjtDef!loadPrjtTree.shtml', null) 
        	//alert(document.getElementById("prjtNo").value);
        	document.getElementById("pStatus").innerHTML = "已启动";
        }
        
        
       
       function resizeWin() {
   		var h_tol = $('#layout1').height();
   		var h_top = $('#top').height();
   		$('#left').height(h_tol-h_top-30);
   	}
   	
   	$(function(){
   		resizeWin();
   		window.onresize = function(){  
   			var h_tol = $('#layout1').height();
   			var h_top = $('#top').height();
   			$('#left').height(h_tol-h_top-30);
           }  
   	});
   	
   	function uploadFile() {
   		var gngile_uploadURL = $('#gngile_upload_URL').val();
   		var server_URL = $('#server_URL').val();
   		
   		var url = gngile_uploadURL
   		      //'http://gnfile.gionee.com:28080/gnfs/GnFileService!upload.shtml?' //文件服务器的上传的URl
   		      /**RUL后面跟的参数 **/
   	  	         +'?syId='+$("#syId").val()    //系统ID
   	  	         +'&syNm='+$("#syNm").val()   //系统名字
   	  	         +'&usrId='+$("#usrId" ).val() //用户ID
   	  	         +'&usrNm='+$("#usrNm" ).val() //用户名字
   	  	         +'&diyFolder='+"PDM//WFDOC" //文件上传的目录中自定义部分
   	  	        // +'&uploadType='+"BaseLib" 
   		         //上传的是ProcFile，取值只能是ProcFile或者BaseLib
   		         //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
   		        // +'&fileVersion='+$("#taskVersion" , window.parent.document).val() //文件版本号，自己在业务逻辑中确定
   	  	         +'&callBackUrl='+server_URL+"/PrjtDef!afterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
   	             +'&tempParams='+"prjtNo:"+$('#prjtNo').val()+",prjtDefFileType:PrjtTaskFile";  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
   	          /**RUL后面跟的参数 **/
   		url=encodeURI(url);  //encodeURI
   		//var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
   		var dialog = $.ligerDialog.open({ url: url, height: 200,width: 490, buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
   		
   		
   		//alert($(".l-dialog-winbtns>div.eq(5)"));
   		//监听Dialog关闭事件
   		$(".l-dialog-winbtns>div.eq(0)").click(function(){
   			   alert("close");
   		        //var taskMangerWin = window.top.document.getElementById("taskManger").contentWindow;
   	            //通过获取到的window对象操作HTML元素，这和普通页面一样
   		        // taskManger.document.getElementById("exit").style.visibility = "visible";
   			
   				//window.location = "./WfReply!add.shtml?WfReply.quesId="+$("#quesId").val()+"&reload=1";
   				
   				//$("#tree1").ligerGetTreeManager().selectNode($('#prjtNo', window.parent.document).val());
   				
   			document.getElementById('taskManger').contentWindow.getProjectTaskFile();
   			
   			
   				
   				
   		});
   	}
   	
   	

    function iFrameHeight(id) {
    	var iframeid=document.getElementById(id); //iframe id
    	if (document.getElementById){
    	   if (iframeid && !window.opera){
    	   if (iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight){
    	   iframeid.height = iframeid.contentDocument.body.offsetHeight;
    	   }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
    	   iframeid.height = iframeid.Document.body.scrollHeight;
    	   }
    	   }
    	}
    	
    }
    </script>
</head>

 <style type="text/css">
   .mt {
	    border-bottom: 1px dotted #EED97C;
	    height: 25px;
	    line-height: 25px;
	    padding: 4px 8px;
	}
 </style>

<body style="padding:10px">


	<div class="mt">
	 <strong>
	    <label  >项目名称: </label> <label id = "pNm"></label> 
	    <label style="margin-left: 30px">项目编号: </label> <label id = "pNo" ></label>
	    <label style="margin-left: 30px">状态：</label> 
	      <span class="ftx14">  <label id = "pStatus" ></label> </span>
     </strong>
	</div>
	
<div id="layout1">

	<div position="left" style="overflow:auto;overflow-x: hidden;" id="left" title="项目列表" class="l-scroll">
		<ul id="tree1"></ul>
	</div>

	<div position="center"  id="navProjectTab" name ="navProjectTab"   style="width: 100%;overflow:hidden; border:1px solid #A3C0E8">
	
		
		   <c:if test="${fn:contains(sessionScope.SYUSR.gpNames,'项目经理')}">
	        <div  id = "createPrjtTab"  title="创建项目"    style="width: 100%;height:100%; display: none"  >
			 <iframe id="createPrjt"  frameborder="0" name="createPrjt"  style="width: 100%;height:450px" src="./PrjtDef.shtml" ></iframe>
			</div>
           </c:if>
           
           
			<div   title="项目组"    style="width: 100%;height:100%"  >
			<iframe id="projInfor"  frameborder="0" name="projInfor"  style="width: 100%;height:525px" ></iframe>
			</div>
			
			<div   title="产品定义书"   style="width: 100%;height:100%" >
			<iframe id = "projtSurvey" frameborder="0" name="showmessage"  style="width: 100%;height:450px" ></iframe>  
			</div>
			
	      	<div   title="项目进度表"   style="width: 100%;height:100%" >
			<iframe id = "PrjtPointFrameAnnex" frameborder="0" name="PrjtPointFrameAnnex"  style="width: 100%;height:450px" ></iframe>  
			</div>
			
		     <!-- <div  id = "PrjtPointTab"  title="项目进度表"  lselected="true"   style="width: 100%;height:100%; display: none"  >
			 <iframe id="PrjtPointFrame"  frameborder="0" name="PrjtPointFrame"  style="width: 100%;height:450px" ></iframe>
			 </div> -->
			
		   
		   <c:if test="${usrId==14938}">
		   
		    <c:if test="${fn:contains(sessionScope.SYUSR.gpNames,'项目经理')}">
			<div    title="进度计划"  lselected="true" style="width: 100%;height:100%"  >
			<iframe id = "taskManger" frameborder="0" name="showmessage" style="width: 100%;height:450px" ></iframe>
			</div>
           </c:if>	
			
			<div  title="进度执行情况"  style="width: 100%;height:100%"  >
			   <iframe id = "gotoselectWfRd"  frameborder="0" style="width: 100%;height:400px" ></iframe>
			</div>
			
			<div    title="问题清单" style="width: 100%;height:100%"  >
			<iframe id = "scheList"   frameborder="0" name="showmessage" style="width: 100%;height:450px" ></iframe>
			</div>
			
			<div   title="交付件" style="width: 100%;height:100%" >
			<iframe id = "projtDoc" frameborder="0" name="showmessage"  style="width: 100%;height:380px" ></iframe>  
			</div>
		</c:if>
			
	</div>
</div> 



<!--input type="hidden" id="slePrjNo" name="slePrjNo" size="30" value="<c:out value="${slePrjNo}"/>" /  -->



<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
<%-- <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" /> --%>
<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
<input type="hidden" id="prjtNo" name="prjtNo" size="30" value="<c:out value="${prjtDef.prjtNo}"/>" />
<input type="hidden" id="prjtNm" name="prjtNm" size="30" value="<c:out value="${prjtDef.prjtNm}"/>" />
<input type="hidden" id="taskVersion" name="taskVersion" size="30" value="<c:out value="${prjtDef.taskVersion}"/>" />
<input type="hidden" id="prjtPointVersion" name="prjtPointVersion" size="30"value="<c:out value="${prjtDef.prjtPointVersion}"/>" />

<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" />
<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" />
<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" /> 
<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />


<input type="hidden" id="taskId" name="taskId" size="30"/>
</body>
</html>