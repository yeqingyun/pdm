﻿<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html>

<head>
  <title>普加甘特图</title>
    <script src="./plusgantt/scripts/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="./plusgantt/scripts/miniui/miniui.js" type="text/javascript"></script>    
    <script src="./plusgantt/scripts/miniui/locale/zh_CN.js" type="text/javascript"></script>

    <link href="./plusgantt/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
    <link href="./plusgantt/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
    <link href="./plusgantt/scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
   
   
    <script src="./plusgantt/scripts/plusgantt/GanttMenu.js" type="text/javascript"></script>
    <script src="./plusgantt/scripts/plusgantt/GanttSchedule.js" type="text/javascript"></script>
    <script src="./plusgantt/scripts/plusgantt/GanttService.js" type="text/javascript"></script>
    
   
   <script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>
   <link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
   <script type="text/javascript">
/* 业务代码
-----------------------------------------------------------------------------*/
function getProjectTaskFile(){
	    $.post("PrjtDef!loadProjectTaskFile.shtml?prjtDef.prjtNo="+$('#prjtNo', window.parent.document).val(),
				function(data) {
			    	var PrjtTaskFileIcon = data.PrjtTaskFileIcon;
		        	var PrjtTaskFileNo = data.PrjtTaskFileNo;
		        	var PrjtTaskFileName = data.PrjtTaskFileName;
		        	if(PrjtTaskFileName){
			        	document.getElementById("PrjtTaskFileBtn").value="修改进度附件";
			            document.getElementById("PrjtTaskFile").innerHTML
			            = "<img align='top' src='./include/img/workflow/"+PrjtTaskFileIcon+"'/>"
			            + "<a  href='PrjtDef!download.shtml?fileNo="+PrjtTaskFileNo+"&fileName="+PrjtTaskFileName+"'>"+PrjtTaskFileName+"</a>";
		        	}
				},
				"json"
	  );
}

//选中设置任务
function resetTasks(){
	document.getElementById("preSave").value= "reset";
	gantt.setReadOnly(false);
	if($('#rbtnl_0').val()==1){
		document.getElementById("startBtn").style.display = "none";
		document.getElementById("savBtn").style.display = "";
		
		columns.splice(1,1);
		//gantt.setMultiSelect(false);  
		gantt.getColumns().remove(0);
	    gantt.loading();
	    $.ajax({
	        url: "./PrjtDef!loadProjectTasks.shtml?task.prjtNo="+$('#prjtNo', window.parent.document).val()+"&loadTaskType=1",
	        cache: false,
	        success: function (text) {
	        	
	        	var oj = JSON.parse(text);
	        	var PrjtTaskFileIcon = oj.PrjtTaskFileIcon;
	        	var PrjtTaskFileNo = oj.PrjtTaskFileNo;
	        	var PrjtTaskFileName = oj.PrjtTaskFileName;
	            var tasks = oj.Tasks;
	            var data = mini.decode(tasks);
	            if(PrjtTaskFileName){
		            document.getElementById("PrjtTaskFile").innerHTML
		            = "<img align='top' src='./include/img/workflow/"+PrjtTaskFileIcon+"'/>"
		            + "<a  href='PrjtDef!download.shtml?fileNo="+PrjtTaskFileNo+"&fileName="+PrjtTaskFileName+"'>"+PrjtTaskFileName+"</a>";
		           
	            }
	            //列表转树形
	            data = mini.arrayToTree(data, "children", "UID", "ParentTaskUID");
	            gantt.loadTasks(data);
	            for(var i= 0;i<data.length;i++){
	            	/**
	            	if(data[i].Status==1){
	            		gantt.select(data[i]);   
	            	}else{
	            		gantt.deselect(data[i]); 
	            	}
	            	**/
	            	if(data[i].Status==3&&data[i].Summary==1){
			            gantt.collapse(data[i]);
	            	}
	            }
	            gantt.unmask();            
	        }
	    });
	  
	}
}

//选中任务管理
function taskManager(){
	document.getElementById("preSave").value= "save";
	document.getElementById("savBtn").style.display = "none";
	document.getElementById("startBtn").style.display = "";
	columns.push(idColumn);
	gantt.setMultiSelect(false);  
	gantt.loading();
    $.ajax({
        url: "./PrjtDef!loadProjectTasks.shtml?task.prjtNo="+$('#prjtNo', window.parent.document).val()+"&loadTaskType=2",
        cache: false,
        success: function (text) {
        	 if(typeof JSON == 'undefined'){
                 $('head').append($("<script type='text/javascript' src='include/js/json2.js'>"));
             }

        	var oj = JSON.parse(text);
        	var PrjtTaskFileIcon = oj.PrjtTaskFileIcon;
        	var PrjtTaskFileNo = oj.PrjtTaskFileNo;
        	var PrjtTaskFileName = oj.PrjtTaskFileName;
            var tasks = oj.Tasks;
            var data = mini.decode(tasks);
            if(PrjtTaskFileName){
	            document.getElementById("PrjtTaskFile").innerHTML
	            = "<img align='top' src='./include/img/workflow/"+PrjtTaskFileIcon+"'/>"
	            + "<a  href='PrjtDef!download.shtml?fileNo="+PrjtTaskFileNo+"&fileName="+PrjtTaskFileName+"'>"+PrjtTaskFileName+"</a>";
            }
            //列表转树形
            data = mini.arrayToTree(data, "children", "UID", "ParentTaskUID");
            gantt.loadTasks(data);
            for(var i= 0;i<data.length;i++){
            	if(data[i].Status==1){
            		gantt.select(data[i]);   
            	}else{
            		gantt.deselect(data[i]); 
            	}
            }
            gantt.unmask();            
        }
    });
    gantt.setReadOnly(true);
}

//启动任务
function startTask(){
	var task = gantt.getSelected();
	if(task.length<1){
		alert("请选择任务启动！");
	}
	if(task.Status==2){
		window.confirm("!该任务已启动");
		return;
	}else if(task.Status==3){
		window.confirm("!该任务已结束");
		return;
	}
	var params = '{';
	params += '"taskUID":"' + task.UID + '",';
	params += '"prjtDef.prjtNo":"' + $('#prjtNo', window.parent.document).val() + '",';
	params += '"prjtDef.TypId":"' + $('#typId').val() + '",';
	params += '"prjtDef.prjtNm":"' + $('#prjtNm',window.parent.document).val() + '"';
	params += '}';
	//?taskUID="+task.UID+"&prjtDef.prjtNo="+$('#prjtNo', window.parent.document).val()+"&prjtDef.TypId="+$('#typId').val()+"&prjtDef.prjtNm="+$('#prjtNm',window.parent.document).val()
    $.post("PrjtDef!createScheWfRd.shtml",eval('(' + params + ')'),function(text) {
    	if(text.indexOf("项目成员未组建完成") > 0){
    		window.confirm(text);
    	}else if(text.indexOf("noPerson") > 0){
    		window.confirm("找不到接受该任务的人，任务启动失败，请配置人员接受该任务");
    	}else{
        	 var data = mini.decode(text);
             //列表转树形
             data = mini.arrayToTree(data, "children", "UID", "ParentTaskUID");
             gantt.loadTasks(data);
             window.parent.reloadPjtInfo();
             window.confirm("启动成功");
    	}  	
    },"text");
}


function save() {
    //获取当前任务树形数据
     var tasktree 
    if($("#preSave").val()=="save"){
      tasktree = gantt.getTaskList();
    }else if($("#preSave").val()=="reset"){
	   tasktree = gantt.getTaskList();
    }
    //将数据转换为JSON字符串
    /**
    if( gantt.getSelecteds().length<1){
    	
    	window.confirm("请选择任务！");
    	return;
    }
    **/
    
    var taskJSON = mini.encode(tasktree);
	var str = "[";
   for(var i= 0;i<tasktree.length;i++) {
		var obj = "";
		  if(tasktree[i].Status!=3){
		      if(tasktree[i]){
			      obj = "{";
			      obj += "taskNo:'" +tasktree[i].UID+"',";
			      obj += "taskNm:'" +tasktree[i].Name+"',";
			      obj += "status:'" +tasktree[i].Status+"',";
			      obj += "duration:'" +tasktree[i].Duration+"',";
			      obj += "planStaDate:'" +tasktree[i].Start.getFullYear() + "-" + (tasktree[i].Start.getMonth() + 1) + "-" + tasktree[i].Start.getDate()+"',";
			      obj += "planOveDate:'" +tasktree[i].Finish.getFullYear() + "-" + (tasktree[i].Finish.getMonth() + 1) + "-" + tasktree[i].Finish.getDate()+"',";
			      obj += "perce:'" +tasktree[i].PercentComplete+"',";
			      obj += "summary:'" +tasktree[i].Summary+"',";
			      obj += "critical:'" +tasktree[i].Critical+"',";
			      obj += "milestone:'" +tasktree[i].Milestone+"',";
			     // alert(JSON.stringify(tasktree[i].PredecessorLink));
			      obj += "predecessorLink:'" + JSON.stringify(tasktree[i].PredecessorLink)+"',";
			      obj += "parent:'" +tasktree[i].ParentTaskUID+"'";
		          obj +="},";
		          
		      }
		  }
	    str += obj;
	}
		str= str.substring(0,str.length-1);
	str +="]";
	  
    var params = {
        taskjson: str,
        //removeds: remvedJSON
    };
  //  使用jQuery的ajax，把任务数据，发送到服务端进行处理
  
	  var url =  "./PrjtDef!sav.shtml?prjtDef.prjtNo="+$('#prjtNo', window.parent.document).val();
	  if($('#taskVersion', window.parent.document).val()>0.0){
		  url = url+"&prjtDef.taskVersion="+$('#taskVersion', window.parent.document).val()
	  }
    $.ajax({
        url: url,
        type: "POST",
        data: params,
        success: function (text) {
        	 if(typeof JSON == 'undefined'){
                 $('head').append($("<script type='text/javascript' src='include/js/json2.js'>"));
             }
        
        	var oj = JSON.parse(text);
        	window.confirm(oj.msg);
        }
    });
}


</script>
</head>
<body style="background:white;font-size:13px;width:95%;height:75%;">
<input type="hidden" id="preSave" name="preSave" size="30" value="save" />
<input type="hidden" id="prjtNo" name="prjtNo" size="30" value="<c:out value="${prjtDef.prjtNo}"/>" />
<input type="hidden" id="typId" name="typId" size="30" value="<c:out value="${prjtDef.typId}"/>" />
<input type="hidden" id="prjtNm" name="prjtNm" size="30" value="<c:out value="${prjtDef.prjtNm}"/>" />
<input type="hidden" id="gpName" name="gpName" size="30" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>" />

    顶层时间刻度：
    <select style="margin-right:20px;" onchange="changeTopTimeScale(this.value)">
        <option value="year">年</option>
        <option value="halfyear">半年</option>
        <option value="quarter">季度</option>
        <option value="month">月</option>
        <option value="week" selected>周</option>
        <option value="day">日</option>
        <option value="hour">时</option>
    </select>
    底层时间刻度：
    <select onchange="changeBottomTimeScale(this.value)" style="margin-right:20px;" >
    <!-- 
        <option value="year">年</option>
        <option value="halfyear">半年</option>
     -->
        <option value="quarter">季度</option>
        <option value="month">月</option>
        <option value="week">周</option>
        <option value="day" selected>日</option>
        <option value="hour">时</option>
        
        
    </select>
   <input type="button" class="wfbigbtn2"  value="放大" onclick="zoomIn()" /> 
   <input type="button" class="wfbigbtn2"  value="缩小" onclick="zoomOut()" />
   <input type="button" class="wfbigbtn2"  onclick="createBaseline()" value="创建比较基准"/  style="margin-left: 80px" />
   <input type="button" class="wfbigbtn2"  onclick="clearBaseline()" value="清除比较基准" />
   <input type="checkbox" id="trackcheck" onclick="oncheckedchanged(this)"/><label for="trackcheck">跟踪甘特图</label>     
  
   <br />
       <div id ="taskStart">
		    <input id="rbtnl_0" type="radio" name="rbtnl" value="1"  onclick="resetTasks()"  /><label for="rbtnl_0">设置任务</label> 
		    <input id="rbtnl_1" type="radio" name="rbtnl" value="2" checked="checked" onclick="taskManager()" /><label for="rbtnl_1">任务管理</label>
		    <input type="button" class="wfbigbtn2"  id = "savBtn" style="width:80px;" value="保存" onclick="save()"/>
		    <input type="button" class="wfbigbtn2"  id = "startBtn" style="width:80px;" value="启动任务" onclick="startTask()"/>
		    <label id="PrjtTaskFile" style="margin-left: 290px" ></label>
            <input id="PrjtTaskFileBtn" class="wfbigbtn2"  type="button"  value="上传进度附件" onclick="window.parent.uploadFile()" />
      </div>
      
       <div id ="taskStart2">
            <label for="PrjtTaskFile2">进度计划附件</label> 
            <label id="PrjtTaskFile2"></label>
       </div>
   <!--div id="ResultFile" style="display:none"> 
     <img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
  	<a href="javescript: downLoad('<c:out value="${wfQues.resultFileNo}"/>');">
  	 <c:out value="${wfQues.resultFileName}"/>
  	</a>
   </div-->
   
      
    
    <!-- 
   <br />  
	    <label style="margin-left: 300px" >项目名称:</label> <label ><c:out value="${prjtDef.prjtNm}"/></label> 
	    <label style="margin-left: 30px">项目编号:</label> <label  ><c:out value="${prjtDef.prjtNo}"/></label>
	    <label style="margin-left: 30px">状态：</label> 
	        <label>
				    <c:choose>
			           <c:when test="${prjtDef.status==0}">无效</c:when>
					   <c:when test="${prjtDef.status==1}">新创建</c:when>
					   <c:when test="${prjtDef.status==2}">已启动</c:when>
					   <c:when test="${prjtDef.status==3}">暂停</c:when>
					   <c:when test="${prjtDef.status==4}">已结束</c:when>
			           <c:otherwise></c:otherwise>
		           </c:choose>
			</label> 
    <br />    
    <br />  
    -->
</body>
</html>

<script type="text/javascript">

     //重写window的alert方法  ,屏蔽掉alert甘特图过期
    window.alert=function(msg){
	 /** if(msg.indexof("试用到期") > 0 || msg.indexof("www.miniui.com") > 0) {
	   }
	  else {
	  alert(msg);
	  }
	 **/
	 } 

    document.getElementById("taskStart").style.display = "none";
    document.getElementById("taskStart2").style.display = "none";
    if(( document.getElementById("gpName").value.indexOf("项目经理") > -1)||(document.getElementById("gpName").value.indexOf("超级用户")>-1)){
    	document.getElementById("taskStart").style.display = "";
    	document.getElementById("taskStart2").style.display = "none";
    	
	}else{
		document.getElementById("taskStart").style.display = "none";
		//window.confirm(document.getElementById("PrjtTaskFile2").innerHTML.length);
		if(document.getElementById("PrjtTaskFile2").innerHTML.length>0){
			document.getElementById("taskStart2").style.display = "";
		}
	}
     

/* 创建甘特图对象，设置列配置
-----------------------------------------------------------------------------*/

var gantt = new CreateGantt();
//var gantt = new PlusProject();
gantt.setStyle("width:100%;height:100%; margin:0px;");

//自定义column
var columns = [];



//IdColumn
var idColumn = { 
		header: "", 
		field: "ID", 
		width: 30,
		cellCls: "mini-indexcolumn",
		align: "center"
		};
columns.push(idColumn);

//CheckBoxColumn
var checkBoxColumn = {
		field: "Status", 
		type: "checkcolumn"
};
columns.push(checkBoxColumn);

//NameColumn
var nameColumn = {
    name: "name",
    header: "任务名称<br/>String",
    field: "Name",
    readOnly:true,
    width: 150,
    editor: {
        type: "textbox"
    }
};
columns.push(nameColumn);

//statusColumn
var statusColumn = {
    header: "状态",
    field: "Status",
    width: 50,
    renderer: function (e) {
        var status = e.value;
     
        	   if (status==0){
               	return "<font color='gray'>未配置</font>";
               	
               }else if (status==1){
               	return "<font color='gray'>未启动</font>";
               } else if(status==2){
              		return "<font color='blue'>进行中</font>";
               }else if(status==3){
              		return "<font color='green'>已结束</font>";
               }
       
     
    },
    editor: {
        type: "textbox"
    }
};
columns.push(statusColumn);

/**
//PredecessorLinkColumn
var predecessorLinkColumn={ 
		header: "前置任务", 
		field: "PredecessorLink",
		width: 50,
		editor: {
		      type: "textbox"
		}
};
columns.push(predecessorLinkColumn);

**/

//DurationColumn
var durationColumn = {
    name: "duration",
    header: "工期<br/>String",
    field: "Duration",
    width: 50,
    editor: {
        type: "textbox"
    }
};
columns.push(durationColumn);

//StartColumn
var startColumn = {
    header: "开始日期<br/>Date",
    field: "Start",
    width: 100,
    renderer: function (e) {
        var date = e.value;
        if (!date) return "";
        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    editor: {
        type: "datepicker"
    }
};
columns.push(startColumn);

//FinishColumn
var finishColumn = {
    header: "结束日期<br/>Date",
    field: "Finish",
    width: 100,
    renderer: function (e) {
        var date = e.value;
        if (!date) return "";
        return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    },
    editor: {
        type: "datepicker"
    }
};
columns.push(finishColumn);

//PercentCompleteColumn
var percentCompleteColumn = {
    header: "进度<br/>Number",
    field: "PercentComplete",
    width: 50,
    editor: {
        type: "spinner", minValue: 0, maxValue: 100
    }
};
columns.push(percentCompleteColumn);

//将列集合数组设置给甘特图
gantt.setColumns(columns);
gantt.setTreeColumn("name");
gantt.render(document.body);
gantt.setStyle("width:100%;height:100%; margin:10px;");


//右键菜单
//var ganttMenu = new GanttMenu();
//gantt.setContextMenu(ganttMenu);

//加载列表数据
function loadList() {
    gantt.loading();
   // alert($('#prjtNo', window.parent.document));
    //alert("./PrjtDef!loadProjectTasks.shtml?task.prjtNo="+$('#prjtNo', window.parent.document).val());
    $.ajax({
    	// url: "data/taskList.txt",
        url: "./PrjtDef!loadProjectTasks.shtml?task.prjtNo="+$('#prjtNo', window.parent.document).val()+"&loadTaskType=2",
        cache: false,
        success: function (text) {
        	
        	  if(typeof JSON == 'undefined'){
                  $('head').append($("<script type='text/javascript' src='include/js/json2.js'>"));
              }

        	var oj = JSON.parse(text);
        	var PrjtTaskFileIcon = oj.PrjtTaskFileIcon;
        	var PrjtTaskFileNo = oj.PrjtTaskFileNo;
        	var PrjtTaskFileName = oj.PrjtTaskFileName;
            var tasks = oj.Tasks;
            var data = mini.decode(tasks);
            if(PrjtTaskFileName){
	            document.getElementById("PrjtTaskFile").innerHTML
	            = "<img align='top' src='./include/img/workflow/"+PrjtTaskFileIcon+"'/>"
	            + "<a  href='PrjtDef!download.shtml?fileNo="+PrjtTaskFileNo+"&fileName="+PrjtTaskFileName+"'>"+PrjtTaskFileName+"</a>";
	           
	            
	            
	            document.getElementById("PrjtTaskFile2").innerHTML
	            = "<img align='top' src='./include/img/workflow/"+PrjtTaskFileIcon+"'/>"
	            + "<a  href='PrjtDef!download.shtml?fileNo="+PrjtTaskFileNo+"&fileName="+PrjtTaskFileName+"'>"+PrjtTaskFileName+"</a>";
	           
            }
            
            //列表转树形
            data = mini.arrayToTree(data, "children", "UID", "ParentTaskUID");
            gantt.loadTasks(data);
            for(var i= 0;i<data.length;i++){
            	if(data[i].Status==1){
            		gantt.select(data[i]);   
            	}else{
            		gantt.deselect(data[i]); 
            	}
            }
            document.getElementById("savBtn").style.display = "none";
        	document.getElementById("startBtn").style.display = "";
            gantt.unmask();            
        }
    });
    
    gantt.setReadOnly(true);
}



loadList();     //这个方式，服务端只需要生成列表数据就可以。

function changeTopTimeScale(value) {
    gantt.setTopTimeScale(value)
}
function changeBottomTimeScale(value) {
    gantt.setBottomTimeScale(value)
}
function zoomIn() {
    gantt.zoomIn();
}
function zoomOut() {
    gantt.zoomOut();
}

function addTask() {
    var newTask = gantt.newTask();
    newTask.Name = '<新增任务>';    //初始化任务属性

    var selectedTask = gantt.getSelected();
    if (selectedTask) {
        gantt.addTask(newTask, "before", selectedTask);   //插入到到选中任务之前
        //project.addTask(newTask, "add", selectedTask);       //加入到选中任务之内            
    } else {
        gantt.addTask(newTask);
    }
}
function removeTask() {
    var task = gantt.getSelected();
    if (task) {
        if (confirm("确定删除任务 \"" + task.UID + "\" ？")) {
            gantt.removeTask(task);
        }
    } else {
    	window.confirm("请选中任务");
    }
}
function updateTask() {
    var selectedTask = gantt.getSelected();
    window.confirm("编辑选中任务:" + selectedTask.Name);    
}
function upgradeTask() {
    var task = gantt.getSelected();
    if (task) {
        gantt.upgradeTask(task);
    } else {
    	window.confirm("请选选中任务");
    }
}
function downgradeTask() {
    var task = gantt.getSelected();
    if (task) {
        gantt.downgradeTask(task);
    } else {
    	window.confirm("请选选中任务");
    }
}





gantt.setMultiSelect(false);                   

function getSelecteds() {
    var tasks = gantt.getSelecteds();
    window.confirm(tasks.length);
}
function setSelecteds() {
    gantt.deselectAll();  //清除所有选择

    var tasks = gantt.findTasks(function (task) {
        return task.Duration > 2;
    });     //查询出任务

    gantt.selects(tasks);       //选择
}


//跟踪甘特图
function oncheckedchanged(ck) {
    var checked = ck.checked;
    gantt.setViewModel(checked ? "track" : "gantt");
}
function createBaseline() {

    var tasklist = gantt.getTaskList();
    for (var i = 0, l = tasklist.length; i < l; i++) {
        var task = tasklist[i];
        if (!task.ActrStart || !task.ActrFinish) continue;

        var baseline0 = {
            Start: new Date(task.ActrStart.getTime()),
            Finish: new Date(task.ActrFinish.getTime())
        };

        task.Baseline = [];
        task.Baseline.push(baseline0);
    }
    gantt.refresh();
}
function clearBaseline() {
    var tasklist = gantt.getTaskList();
    for (var i = 0, l = tasklist.length; i < l; i++) {
        var task = tasklist[i];
        delete task.Baseline;
    }
    gantt.refresh();
}


function SaveProject(project, callback, params) {

    project.mask("数据保存中，请稍后...");
    var dataProject = project.getData();
    dataProject.RemovedTasks = project.getRemovedTasks();
    var json = mini.encode(dataProject);   
    if (!params) params = {};
    params.project = json;

    $.ajax({
        url: SaveProjectUrl,
        type: "post",
        data: params,
        success: function (text) {
        	window.confirm("保存成功,项目UID：" + text);
            project.acceptChanges();
            if (callback) callback(project);
            project.unmask();
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	window.confirm("保存失败, 错误码：" + textStatus);
            project.unmask();
        }
    });
}


//设置关联选项
gantt.on("beforeselect", function (e) {
	if(gantt.multiSelect){
	    e.cancel = true;
	    var task = e.task;
	
	    var isSelected = this.isSelected(task);
	    if (isSelected) {
	        //任务和子任务全部取消选择
	        var tasks = this.getAllChildTasks(task);
	        tasks.push(task);
	        this.deselects(tasks);
	
	        //?如果父任务下没有选中的任务，则父任务也取消选择
	
	    } else {
	        //任务、父任务、子任务选中4
	        var tasks = this.getAncestorTasks(task);
	        tasks.push(task);
	        var childs = this.getAllChildTasks(task);
	        tasks.addRange(childs);
	
	        this.selects(tasks);
	
	    }
	}

});




</script>
