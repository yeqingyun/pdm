﻿﻿﻿<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="renderer" content="webkit"></meta>
    <title></title>
    <link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />
    <link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>   
    <style type="text/css">
        .l-case-title
        {
            font-weight: bold;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        body, html
        {
            width: 100%;
            height: 100%;
        }
        *
        {
            margin: 0;
            padding: 0;
        }
        #winlinks
        {
            position: absolute;
            left: 20px;
            top: 20px;
            width: 100%;
        }
        #winlinks ul
        {
            position: relative;
        }
        #winlinks li
        {
            width: 70px;
            cursor: pointer;
            height: 80px;
            position: absolute;
            z-index: 101;
            list-style: none;
            text-align: center;
        }
        #winlinks li img
        {
            width: 36px;
            height: 36px;
        }
        #winlinks li span
        {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0.3);
            border-radius: 10px 10px 10px 10px;
            display: block;
            font-size: 12px;
            margin-top: 1px;
            color: White;
            line-height: 18px;
            text-align: center;
        }
        #winlinks li.l-over div.bg
        {
            display: block;
        }
        #winlinks li div.bg
        {
            display: none;
            position: absolute;
            top: -2px;
            left: -2px;
            z-index: 0;
            width: 75px;
            height: 64px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            background: #000;
            opacity: 0.1;
            filter: alpha(opacity=10);
        }
        .l-taskbar-task-icon
        {
            top: 3px; left: 6px; background-image:none;
        } 
        .l-taskbar-task-content{ margin-left:30px;}
        .l-taskbar-task-icon img
        {
            width: 22px;
            height: 22px;
        }
    </style>
    
    
    
     <style type="text/css">
        .l-case-title { font-weight: bold; margin-top: 20px; margin-bottom: 20px; }
        body, html { width: 100%; height: 100%; }
        * { margin: 0; padding: 0; }
        #winlinks { position: absolute; left: 20px; top: 30px; width: 100%; }
            #winlinks ul { position: relative; }
            #winlinks li { width: 70px; cursor: pointer; height: 80px; position: absolute; z-index: 101; list-style: none; text-align: center; }
                #winlinks li img { width: 60px; height: 60px; }
                #winlinks li span { background: none repeat scroll 0 0 rgba(0, 0, 0, 0.3); border-radius: 10px 10px 10px 10px; display: block; font-size: 12px; margin-top: 1px; color: White; line-height: 18px; text-align: center; }
                #winlinks li.l-over div.bg { display: block; }
                #winlinks li div.bg { display: none; position: absolute; top: -2px; left: -2px; z-index: 0; width: 75px; height: 64px; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; background: #000; opacity: 0.1; filter: alpha(opacity=10); }
        .l-taskbar-task-icon { top: 3px; left: 6px; background-image: none; }
        .l-taskbar-task-content { margin-left: 30px; }
        .l-taskbar-task-icon img { width: 22px; height: 22px; }
        .l-taskbar { height: 32px; }
        #topnav { text-align: right; padding: 5px 30px; font-weight: bold; top: 0px; }
            #topnav ul li { float: right; text-decoration: none; margin: 5px 10px; padding: 1px 3px; }


        /*new*/

        body { overflow: hidden; background: url(images/applebg.jpg) repeat-x center center; }

        .sublinks {width: 90%;padding:20px 20px; }
            .sublinks li { width: 70px; cursor: pointer; height: 100px;z-index: 101; list-style: none; text-align: center; }
                .sublinks li img { width: 60px; height: 60px; }
                .sublinks li span { background: none repeat scroll 0 0 rgba(0, 0, 0, 0.3); border-radius: 10px 10px 10px 10px; display: block; font-size: 12px; margin-top: 1px; color: White; line-height: 18px; text-align: center; }
                .sublinks li.l-over div.bg { display: block; }
                .sublinks li div.bg { display: none; position: absolute; top: -2px; left: -2px; z-index: 0; width: 75px; height: 64px; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; background: #000; opacity: 0.1; filter: alpha(opacity = 10); }
            .sublinks ul li { float: left !important; margin: 0px 10px;  }
                .sublinks ul li:hover {background-color:#f8f2f2;opacity:.9;  }

        #toolbar {width:90%;position:fixed;right:20px;font-size:14px;  }
            #toolbar ul { float:right;}
        #toolbar ul li { float:left; margin:5px  }
        
        
         #onlineHelp {width:90%;position:fixed;right:330px;font-size:14px;bottom:30px  }
            #onlineHelp ul { float:right;}
        #onlineHelp ul li { float:left; margin:5px  }
        

    </style>
    
</head>
<body style="overflow: hidden; background-image: url(./include/images/applebg.jpg);background-color: #103272;background-repeat: no-repeat;background-position: center center;">
    <div id="winlinks">
        <ul>
        </ul>
    </div>
    
    <div id="toolbar">
        <ul>
            <li style="color:#FFF">欢迎,<c:out value="${SYUSR.comNm}"/></li>
            <li style="color:#FFF"><c:out value="${SYUSR.deptNm}"/></li>
            <li style="color:#FFF"><c:out value="${SYUSR.usrName}"/></li>
            <li><a onclick="return confirm('你要退出系统?');" href="Login!logout.shtml" style="color:#FFF"><c:out value="退出"/></a></li>
        </ul>
    </div>
    
    <input type="hidden" id="gpNames" name="gpNames" size="30" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>" />
    <input type="hidden" id="wfNo" name="wfNo" size="30" value="<c:out value="${wfRd.wfNo}"/>" />
    
    <div class="foot">
	
	</div>
    <div id="onlineHelp">
     <ul>
      	
      	<li style="color: White">资源下载： </li>
		<!-- 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <img align="middle" src="./include/img/workflow/ff.png"/>
    	 <a href="./Login!downLoad.shtml?filePath=/include/firefoxBrowser.rar&fileNm=火狐浏览器.rar">火狐浏览器</a>google.jpg
        &nbsp;&nbsp;&nbsp;&nbsp;
        -->
    	 
    	 <!-- <img align="middle" height="25"  width="25" src="./include/img/workflow/google1.png"/> -->
    	 <img align="middle" height="25"  width="25" src="./include/img/workflow/360.jpg"/>
    	 <a style="color:white;font-weight:bold;" href="./Login!downLoad.shtml?filePath=/include/360se7.1.1.588.exe&fileNm=360se7.1.1.588.exe"> 360浏览器</a> 
	     &nbsp;&nbsp;&nbsp;&nbsp;
    	 <img align="middle" height="25"  width="25" src="./include/img/workflow/HttpUpload.png"/>
    	 <a style="color:white;font-weight:bold;" href="./Login!downLoad.shtml?filePath=/include/HttpUploader5.rar&fileNm=大附件上传控件.rar"> 大附件上传控件</a>
    	 &nbsp;&nbsp;&nbsp;&nbsp;
    	  
    	   <img align="middle" height="25"  width="25" src="./include/img/workflow/pdf_logo.jpg"/>
    	  <a style="color:white;font-weight:bold;" href="./Login!downLoad.shtml?filePath=/include/PDM问题管理系统上线操作说明V1.1_20141225.pdf&fileNm=PDM问题管理系统上线操作说明V1.1_20141225.pdf"> 问题管理操作说明</a>
    	 &nbsp;&nbsp;&nbsp;&nbsp;
    	  <img align="middle" height="25"  width="25" src="./include/img/workflow/pdf_logo.jpg"/>
    	  <a style="color:white;font-weight:bold;" href="./Login!downLoad.shtml?filePath=/include/PDM流程管理系统上线操作说明V1.1_20150303.pdf&fileNm=PDM流程管理系统上线操作说明V1.1_20150303.pdf"> 流程管理操作说明</a>
    	 <!-- <a href="./Login!downLoad.shtml?filePath=/include/template/question_template.xls&fileNm=question_template.xls"> 问题导入模板</a>  -->
    	   
    	  </ul>
	     
	      <ul>
	      <li style="color: White">技术支持： </li>
	        <li> <a target="_blank" style="color: White" href="http://wpa.qq.com/msgrd?v=1&amp;uin=7016441&amp;site=qq&amp;menu=yes"><img border="0" style="float:left;" src="http://wpa.qq.com/pa?p=2:7016441:45" alt="信息开发江工" title="信息开发江工"/>江工</a>&nbsp; </li>
	        <!-- <li> <a target="_blank" style="color: White" href="http://wpa.qq.com/msgrd?v=1&amp;uin=287347589&amp;site=qq&amp;menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:287347589:45" alt="信息开发王工" title="信息开发王工">王工</a>&nbsp; </li> -->
	       <!--  <li style="color: White">电话分机号：5283</li> -->
	       <li style="color: White">郤工邮箱：quezg@gionee.com</li>
	        <li style="color: White">©版权所有：深圳市金立通信设备有限公司&nbsp;&nbsp;软件开发：金立集团信息中心开发部 </li>
	      </ul>
    </div>
    
</body>
<script type="text/javascript">

  

    var tit = "您有新的";
    var url ="";
	var cc ="";
	var tip;

$(function () {
	
	//url="./WfRd!myTaskList.shtml?wfRd.selectType=1"
			//./WfRd!selectTask.shtml
			
		
		   $.post("WfRd!myTaskAndQues.shtml",
				function(data) {
			        data = JSON.parse(data);
			        var task = data.Task;
			        var ques = data.Ques;
					tit = "您有<span color ='red'>"+task.Total+"</span>个任务未完成，"+ques.Total+"个问题未解决";
					
					$.each(task.Rows,function(i,item){
						url= './WfRdView!wfTaskView.shtml?wfRd.wfNo='+item.wfNo+"&currentTaskId="+item.taskId+"&taskStepId="+item.taskStepId;
						cc = cc+"任务:"+"<a href="+url+" target='_blank'>"+item.wfName+"<a/> <br>";
						//+row.wfNo+"',"+row.taskId+","+row.taskStepId
						//WfRdView!wfTaskView.shtml?wfRd.wfNo=B0914100021&currentTaskId=3534&taskStepId=16
					

						                        //url= "./WfRdView!wfTaskView.shtml?wfRd.wfNo="+item.wfNo+"&currentTaskId="+item.taskId+"&taskStepId="+item.taskStepId;
												//dd ="http://www.pizzahut.com.cn/";
												//cc = cc+"任务:"+"<a href='#' onClick='window.open(\""+url+"\",\"任务办理\",\"width=800,height=600\")'  >"+item.wfName+"<a/> <br>";	
					
					
					});
					
					$.each(ques.Rows,function(i,item){
						//url= './WfReply!add.shtml?WfReply.quesId='+item.quesId;
						url= './WfQues!managerQues.shtml?wfQues.quesId='+item.quesId;
						//cc = cc+"问题:"+"<a href="+url+" target='_blank'>"+item.title+"<a/> <br>";
						cc = cc+"问题:"+"<a href="+url+" target='_blank'>"+item.quesId+"<a/> <br>";
					});
					
					cc=cc+"  <br>";
					cc=cc+"  <br>";
					 if (!tip) {
					        tip = $.ligerDialog.tip({ title: tit, content: cc });
					 }
					 else {
					        var visabled = tip.get('visible');
					        if (!visabled) tip.show();
					       
					}
				},
				"json"
			);
});



    var LINKWIDTH = 90, LINKHEIGHT = 90, TASKBARHEIGHT = 43;
    var winlinksul =  $("#winlinks ul");
    //项目总览
    function f_open(url, title, icon,id) {
    	//判断是否为"项目总览"模块，是的直接弹新页面
    	if(title=='项目总览'){
    		 window.open('./wfppReport.shtml');
    	}else{
	    	url =encodeURI(url);
	    	var win = $.ligerDialog.open(
	    	        {id:id,isHidden:false, height: 600, url: url, width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
	    	        });
	    	       
	        var task = jQuery.ligerui.win.tasks[win.id];
	        if (task) {
	            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
	        }
       	 	return win;
    	 }
    }
    function f_open_ossUpload(url, title, icon, sourceUrl, sourceWindow) {
        var cls = new Date().getTime()+ "";
        url = encodeURI(url);
        var win = $.ligerDialog.open(
            {
                isHidden:false, height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false, cls: cls,
                buttons: [{
                    text: '完成',
                    onclick: function (item, dialog) {
                        dialog.close();
                        sourceWindow.location = sourceUrl;
                    }
                }]
            });
        var task = jQuery.ligerui.win.tasks[win.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        //关闭刷新绑定
        $("." + cls + " .l-dialog-close")[0].addEventListener("click", function(){
            sourceWindow.location = sourceUrl;
        })
        return win;
    }

    function f_open_img_ossUpload(url, title, icon, callback) {
        var cls = new Date().getTime()+ "";
        url = encodeURI(url);
        var win = $.ligerDialog.open(
            {
                isHidden:false, height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false, cls: cls,
                buttons: [{
                    text: '完成',
                    onclick: function (item, dialog) {
                        dialog.close();
                        callback(dialog);
                    }
                }]
            });
        var task = jQuery.ligerui.win.tasks[win.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        //关闭刷新绑定
        $("." + cls + " .l-dialog-close")[0].addEventListener("click", function(){
            callback();
        })
        return win;
    }
    
    
    function f_open_upload(url, title, icon,id) {
   	 url =encodeURI(url);
       var win = $.ligerDialog.open(
       {id:id,isHidden:false, height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
       });
       var task = jQuery.ligerui.win.tasks[win.id];
       if (task) {
           $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
       }
       $(".l-dialog-winbtns").click(function(){
  			alert("gg");
  			document.getElementById('taskManger').contentWindow.getProjectTaskFile();
  		});
       return win;
   }
    
    
    
    function f_open_gnFile(url, title, icon,id) {
        var win = $.ligerDialog.open(
        {id:id,isHidden:false, height: 600, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
        	/**
        	, buttons: [
            { text: '确定', onclick: function (item, Dialog, index) {
                win.hide();
            }
            }
        ]
        **/
        });
        var task = jQuery.ligerui.win.tasks[win.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        return win;
    }
    
    
    //我的任务
     var taskWinow;
    function f_openWorkFlow(url, title, icon) {
    	taskWinow = $.ligerDialog.open(
        { height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
        });
        var task = jQuery.ligerui.win.tasks[taskWinow.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        return taskWinow;
    } 
    
  //我的问题
    var quesWinow;
   function f_openQuesFlow(url, title, icon) {
	   quesWinow = $.ligerDialog.open(
       { height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
       });
       var task = jQuery.ligerui.win.tasks[quesWinow.id];
       if (task) {
           $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
       }
       return quesWinow;
   } 
   
 //问题管理
   var quesManagerWinow;
  function f_openQuesManagerFlow(url, title, icon) {
	  quesManagerWinow = $.ligerDialog.open(
      { height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
      });
      var task = jQuery.ligerui.win.tasks[quesManagerWinow.id];
      if (task) {
          $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
      }
      return quesManagerWinow;
  } 
  
  //风险管理
  var riskWinow;
  function f_openRiskFlow(url, title, icon) {
	  riskWinow = $.ligerDialog.open(
      { height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
      });
      var task = jQuery.ligerui.win.tasks[riskWinow.id];
      if (task) {
          $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
      }
      return riskWinow;
  } 
  
    //流程管理
    var wflistWinow;
    function f_openWorkFlowList(url, title, icon) {
    	wflistWinow = $.ligerDialog.open(
        { height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
        });
        var task = jQuery.ligerui.win.tasks[wflistWinow.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        return wflistWinow;
    } 
    
    //项目管理
    var projectWinow;
    function f_openProjectFlow(url, title, icon) {
    	projectWinow = $.ligerDialog.open(
        { height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
        });
        var task = jQuery.ligerui.win.tasks[projectWinow.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        return projectWinow;
    } 
    
    var wfProcessWinow;
    function f_open_WfProcess(url, title, icon) {
    	wfProcessWinow = $.ligerDialog.open(
        { height: 400, url: url, width: 1000, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
        });
        var task = jQuery.ligerui.win.tasks[wfProcessWinow.id];
        if (task) {
            $(".l-taskbar-task-icon:first", task).html('<img src="' + icon + '" />');
        }
        return wfProcessWinow;
    }
    
    
    
    
    var links = [
                 /**
            { icon: './include/images/3DSMAX.png', title: '我的任务', url: '../case/week.htm' },
            { icon: './include/images/Xp-G5 006.png', title: '项目规划', url: './PrjtDef!prjtInfor.shtml' },
            { icon: './include/images/Program Files Folder.png', title: '创建项目', url: './PrjtDef!add.shtml' },
            { icon: './include/images/3DSMAX.png', title: '项目管理', url: './PrjtDef!view.shtml' },
            { icon: './include/images/question.ico', title: '问题管理', url: './WfQues.shtml' },
            { icon: './include/images/Alien Folder.png', title: '文档管理', url: '../grid/mulheader/grid2.htm' }
           
             { icon: './include/images/Alien Folder.png', title: '树', url: '../tree/draggable.htm' },
             { icon: './include/images/Xp-G5 006.png', title: '下拉框', url: '../comboBox/comboBoxGrid.htm' },
             { icon: './include/images/Xp-G5 006.png', title: '下拉框', url: '../comboBox/comboBoxGrid.htm' },
             { icon: './include/images/Alien Folder.png', title: 'layout', url: '../layout/layoutFullHeight.htm' },
             { icon: './include/images/Alien Folder.png', title: 'menu', url: '../menu/menubar.htm' },
             { icon: './include/images/Xp-G5 006.png', title: 'tab', url: '../tab/tabHtml.htm' },
             { icon: './include/images/3DSMAX.png', title: '分组', url: '../grid/groupable/checkbox.htm' }
             **/
             
             //url: './WfRd!myTaskList.shtml?wfRd.flowId=21'
            //url: './WfRd!myTaskList.shtml?wfRd.selectType=1'
        ];
    
    var myTask = { icon: './include/images/mach.png', title: '我的任务', url: './WfRd!myTaskList.shtml?wfRd.selectType=1',id:'myTaskListId'};
     links.push(myTask);
     
   /** var prjtDesi = { icon: './include/images/Xp-G5 006.png', title: '项目规划', url: './PrjtDef!prjtInfor.shtml' };
    var newPrjt = { icon: './include/images/Program Files Folder.png', title: '创建项目', url: './PrjtDef.shtml' };
    if(document.getElementById("gpName").value.indexOf("产品经理") > -1){
    	//links.push(prjtDesi);
    	links.push(newPrjt);
    } 
   **/ 
   
    var myQues = { icon: './include/images/myQues.png', title: '我的问题', url: './WfQues!myTaskList.shtml?wfQues.selectType=1' ,id:'myQuesId'};
	links.push(myQues);
   
	
	 var workflow = { icon: './include/images/guihua.png', title: '产品规划',  url: './WfRd!guihauList.shtml',id:'cpghId' };
	 if(document.getElementById("gpNames").value.indexOf("产品经理") > -1){
	    links.push(workflow);
	 }
	
    var prjtManage = { icon: './include/images/3DSMAX.png', title: '项目管理', url: './PrjtDef!view.shtml' ,id:'prjtDefManagId'};
    links.push(prjtManage);
    
    var quesManage = { icon: './include/images/visit.png', title: '问题管理', url: './WfQues!quesManager.shtml' ,id:'wfQuesId'};
   /*  if(document.getElementById("gpNames").value.indexOf("DQA") > -1||document.getElementById("gpNames").value.indexOf("项目经理") > -1){ */
    	links.push(quesManage);
   /*   } */
    
   // var docManager = { icon: './include/images/statquery.png', title: '风险流程', url: './WfRd!rickList.shtml?wfRd.flowId=21',id:'rickListId'};
    //links.push(docManager);
   // var docManager = { icon: './include/images/statquery.png', title: '风险管理', url: './WfRd!rickList.shtml?wfRd.flowId=21',id:'rickListId'};
   // links.push(docManager);
    
   
    //var xxxManager = { icon: './include/images/statquery.png', title: 'XXXXXX', url: './WfQues!riskQuesManagerUI.shtml?wfQues.isRiskQues=1',id:'isRiskQues'};
    //links.push(xxxManager);
   // var xxxManager = { icon: './include/images/statquery.png', title: 'XXXXXX', url: './WfQues!riskQuesManagerUI.shtml?wfQues.isRiskQues=1',id:'isRiskQues'};
  //  links.push(xxxManager);
    
    var riskManager = { icon: './include/images/statquery.png', title: '风险管理', url: './WfRisk!riskListUI.shtml',id:'RiskId'};
    links.push(riskManager);
    
    var docManager = { icon: './include/images/communal.png', title: '文档管理', url: './PrjtDef!doc.shtml',id:'wfDocId'};
    if(document.getElementById("gpNames").value.indexOf("不随项目变更角色") > -1){
    	links.push(docManager);
	 }
    


  //  var docManager1 = { icon: './include/images/communal.png', title: '文档管理', url: './WfDoc!main.shtml',id:'wfDocId'};
  //  links.push(docManager1);
    
   
    
    var workflow = { icon: './include/images/wf_icon.png', title: '流程管理', url: './WfRd.shtml',id:'wfRdManagId' };
    if(document.getElementById("gpNames").value.indexOf("海外项目人员") > -1){
        links.push(workflow);
	 }

    

    var workflow = { icon: './include/images/xls_filetype.png', title: '项目总览', url: './wfppReport.shtml',id:'flow' };
    if(document.getElementById("gpNames").value.indexOf("不随项目变更角色") > -1){
    	links.push(workflow);
	 }
    


    var workflowhelp = { icon: './include/images/baseLib.png', title: '帮助', url: './WfRd!desc.shtml',id:'wfWorflowDes' };
    if(document.getElementById("gpNames").value.indexOf("不随项目变更角色") > -1){
    	links.push(workflowhelp);
	 }
    
     
     
     var workflowhelp = { icon: './include/images/baseLib.png', title: '基线库下载权限设置', url: './DocRole!view.shtml',id:'docRole' };
     if(document.getElementById("gpNames").value.indexOf("不随项目变更角色") > -1){
    	 links.push(workflowhelp);
 	 }
     
    
  //  var cqDefectManage = { icon: './include/images/visit.png', title: 'CQ缺陷管理', url: './CQDefect!defectManager.shtml' ,id:'defectId'};
  //  links.push(cqDefectManage);
    
    
    
    
             
    function onResize() {
        var linksHeight = $(window).height() - TASKBARHEIGHT;
        var winlinks = $("#winlinks");
        winlinks.height(linksHeight);
        var colMaxNumber = parseInt(linksHeight / LINKHEIGHT);//一列最多显示几个快捷方式
        for (var i = 0, l = links.length; i < l; i++) {
            var link = links[i];
            var jlink = $("li[linkindex=" + i + "]", winlinks);
            var top = (i % colMaxNumber) * LINKHEIGHT, left = parseInt(i / colMaxNumber) * LINKWIDTH;
            if (isNaN(top) || isNaN(left)) continue;
            jlink.css({ top: top, left: left });
        }
    }
   
    function linksInit() {
        for (var i = 0, l = links.length; i < l; i++) {
            var link = links[i];
            var jlink;
            var jlink = $("<li></li>");
            jlink.attr("linkindex", i);
            jlink.append("<img src='" + link.icon + "' />");
            jlink.append("<span>" + link.title + "</span>");
            jlink.append("<div class='bg'></div>");
            jlink.hover(function () {
                $(this).addClass("l-over");
            }, function () {
                $(this).removeClass("l-over");
            }).click(function () {
                var linkindex = $(this).attr("linkindex");
                var link = links[linkindex];
                if(link.title=='我的任务'){
                	f_openWorkFlow(link.url, link.title, link.icon);
                }else if(link.title=='流程管理'){
                	f_openWorkFlowList(link.url, link.title, link.icon);
                }else if(link.title=='我的问题'){
                	f_openQuesFlow(link.url, link.title, link.icon);
                }else if(link.title=='问题管理'){
                	f_openQuesManagerFlow(link.url, link.title, link.icon);
                }else if(link.title=='风险管理'){
                	f_openRiskFlow(link.url, link.title, link.icon);
                }else if(link.title=='项目管理'){
                	f_openProjectFlow(link.url, link.title, link.icon);
                }
                else if(link.id.toString().indexOf("wxh")>0){
                	window.open(link.url,link.title,"width=800,height=600");
                }
                else{
                	f_open(link.url, link.title, link.icon,link.id);
                }
                
            });
            jlink.appendTo(winlinksul);
        }

    }

    $(window).resize(onResize);
    $.ligerui.win.removeTaskbar = function () { }; //不允许移除
    $.ligerui.win.createTaskbar(); //页面加载时创建任务栏

    linksInit();
    onResize();
    
    $(function (){
    	var wfNo = document.getElementById("wfNo");
    	if(wfNo!=null && wfNo.value!=null && wfNo.value!=''){
    		f_open("./WfRdView.shtml?wfRd.wfNo="+wfNo.value, "工作流"+wfNo.value, "./include/images/wf_icon.png");
    	}
    });

</script>

    
</html>
