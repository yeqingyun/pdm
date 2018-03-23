<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
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

    </style>
    
</head>
<body style="overflow: hidden;">
    <div id="winlinks">
        <ul>
        </ul>
    </div>
    
    <input type="hidden" id="gpName" name="gpName" size="30" value="<c:out value="${gp.gpName}"/>" />
    <input type="hidden" id="wfNo" name="wfNo" size="30" value="<c:out value="${wfRd.wfNo}"/>" />
    
</body>
<script type="text/javascript">

    var LINKWIDTH = 90, LINKHEIGHT = 90, TASKBARHEIGHT = 43;
    var winlinksul =  $("#winlinks ul");
    function f_open(url, title, icon,id) {
        var win = $.ligerDialog.open(
        {id:id,isHidden:false, height: 500, url: url, width: 1046, showMax: true, showToggle: true, showMin: true, isResize: true, modal: false, title: title, slide: false
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
    
  
    
    var docManager = { icon: './include/images/procFile.png', title: '过程文档', url: './ProcFileWfDoc.shtml',id:'procFileId'};
    links.push(docManager);
    
    var docManager = { icon: './include/images/baseLib.png', title: '基线库', url: './BaseLibWfDoc.shtml',id:'baseLibId'};
    links.push(docManager);
    
    var workflow = { icon: './include/images/shareLib.png', title: '发布库', url: './ShareLibWfDoc.shtml',id:'shareLibId' };
    links.push(workflow);
   
             
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
                window.parent.f_open_gnFile(link.url, link.title, link.icon,link.id);
               // f_openWorkFlowList(link.url, link.title, link.icon);
               /**
                if(link.title=='我的任务'){
                	f_openWorkFlow(link.url, link.title, link.icon);
                }else if(link.title=='流程管理'){
                	f_openWorkFlowList(link.url, link.title, link.icon);
                }else{
                	f_open(link.url, link.title, link.icon,link.id);
                }
               **/
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
