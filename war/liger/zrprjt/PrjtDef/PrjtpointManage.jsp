﻿<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<style type="text/css">
        li
        {
        cursor:pointer;
        list-style:none;
        width:200px;
        height:50px;
        border:1px solid #999;
        }
    </style>
<head>
<title>普加甘特图</title>
<script src="./plusgantt/scripts/jquery-1.6.2.min.js"
	type="text/javascript"></script>
<script src="./plusgantt/scripts/miniui/miniui.js"
	type="text/javascript"></script>
<script src="./plusgantt/scripts/miniui/locale/zh_CN.js"
	type="text/javascript"></script>

<link href="./plusgantt/scripts/miniui/themes/default/miniui.css"
	rel="stylesheet" type="text/css" />
<link href="./plusgantt/scripts/miniui/themes/blue/skin.css"
	rel="stylesheet" type="text/css" />
<link href="./plusgantt/scripts/miniui/themes/icons.css"
	rel="stylesheet" type="text/css" />

<script src="./plusgantt/scripts/plusgantt/GanttMenu.js"
	type="text/javascript"></script>
<script src="./plusgantt/scripts/plusgantt/GanttSchedule.js"
	type="text/javascript"></script>
<script src="./plusgantt/scripts/plusgantt/GanttService.js"
	type="text/javascript"></script>

<script src="./include/js/zrprjt/prjtDef.js?v=1.0"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<script type="text/javascript">
	 $(function (){
		 $.post("PrjtDef!loadProjectPointsforVersion.shtml",{'prjtDef.prjtNo':$('#prjtNo', window.parent.document).val()},
		 		function(data) {
		 		$("#prjtPointVersion").val(data.Rows[0].prjtPointVersion);
		 		 
		 		},
		 		"json"
		       );
		 
	/* 	$("#prjtPointVersion").val($('#prjtPointVersion', window.parent.document).val()); */
	}); 

	function save() {
		//获取当前任务树形数据
		
		if($("#responsibleUID").val()==""){
			window.confirm("版本号不能为空！");
			return;
		}
		var tasktree;
		tasktree = gantt.getTaskList();
		var taskJSON = mini.encode(tasktree);
		var str = "[";
		for ( var i = 0; i < tasktree.length; i++) {
			var obj = "";
			if (tasktree[i].Status != 3) {
				if (tasktree[i]) {
					obj = "{";
					
					obj += "pointName:'" + tasktree[i].Name + "',";
					
					if(tasktree[i].Remark){
						obj += "remark:'" + tasktree[i].Remark + "',";
					}
					if (tasktree[i].OriginalPlanOveDate) {
						obj += "originalPlanOveDate:'"
								+ tasktree[i].OriginalPlanOveDate.getFullYear()
								+ "-"
								+ (tasktree[i].OriginalPlanOveDate.getMonth() + 1)
								+ "-"
								+ tasktree[i].OriginalPlanOveDate.getDate()
								+ "',";
					}
					if (tasktree[i].ActualOverDate) {
						obj += "actualOverDate:'"
								+ tasktree[i].ActualOverDate.getFullYear()
								+ "-"
								+ (tasktree[i].ActualOverDate.getMonth() + 1)
								+ "-" + tasktree[i].ActualOverDate.getDate()
								+ "',";
					}
					if (tasktree[i].PlanOverDate) {
						obj += "planOverDate:'"
								+ tasktree[i].PlanOverDate.getFullYear() + "-"
								+ (tasktree[i].PlanOverDate.getMonth() + 1)
								+ "-" + tasktree[i].PlanOverDate.getDate()
								+ "',";
					}
					if(tasktree[i].ResponsUser){
						obj += "responsUser:'" + tasktree[i].ResponsUser + "',";
					}
					
					if(tasktree[i].DelayTime){
						obj += "delayTime:'" + tasktree[i].DelayTime + "',";
					}
					
					if(tasktree[i].DelayReson){
						obj += "delayReson:'" + tasktree[i].DelayReson + "',";
					}
					
					if(tasktree[i].Parent){
						obj += "parent:'" + tasktree[i].Parent + "'";
					}
					if(!tasktree[i].UID.length>10){
						obj += "pointId:'" + tasktree[i].UID + "',";
					}
					obj += "pointIndex:'" + tasktree[i].ID + "'";
					obj += "},";
				}
			}
			str += obj;
		}
		str = str.substring(0, str.length - 1);
		str += "]";

		//window.confirm(str);

		var params = {
			prjtPointjson : str,
		//removeds: remvedJSON
		};
		//  使用jQuery的ajax，把任务数据，发送到服务端进行处理

		var url = "./PrjtDef!updatePrjtPoint.shtml?prjtDef.prjtNo="
				+ $('#prjtNo', window.parent.document).val();
		url = url + "&prjtDef.prjtPointVersion=" + $('#prjtPointVersion').val();
		//window.confirm($('#prjtPointVersion').val());
		$.ajax({
					url : url,
					type : "POST",
					data : params,
					success : function(text) {
						if (typeof JSON == 'undefined') {
							$('head')
									.append(
											$("<script type='text/javascript' src='include/js/json2.js'>"));
						}
						var oj = JSON.parse(text);
						window.confirm(oj.msg);
					}
				});
	}
</script>


</head>
<body
	style="background: white; font-size: 13px; width: 95%; height: 75%;">
	<input type="hidden" id="preSave" name="preSave" size="30" value="save" />
	<input type="hidden" id="prjtNo" name="prjtNo" size="30"
		value="<c:out value="${prjtDef.prjtNo}"/>" />
	<input type="hidden" id="typId" name="typId" size="30"
		value="<c:out value="${prjtDef.typId}"/>" />
	<input type="hidden" id="prjtNm" name="prjtNm" size="30"
		value="<c:out value="${prjtDef.prjtNm}"/>" />
	
	<input type="hidden" id="gpName" name="gpName" size="30"
		value="<c:out value="${sessionScope.SYUSR.gpNames}"/>" />

    <br>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="增加" onclick="addTask()" />
	<input type="button" value="删除" onclick="removeTask()" />
	<input type="button" value="插入" onclick="insertPoint()" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	版本号：
	<input id="prjtPointVersion" type="text" style="width: 40px;"
		value="<c:out value="${prjtDef.prjtPointVersion}"/>" />
	<input id = "savPrjtPointBtn" type="button" style="width: 80px;" value="保存"
		onclick="save()" />

</body>
</html>

<script type="text/javascript">
	//重写window的alert方法  ,屏蔽掉alert甘特图过期
	window.alert = function(msg) {
		/** if(msg.indexof("试用到期") > 0 || msg.indexof("www.miniui.com") > 0) {
		  }
		 else {
		 alert(msg);
		 }
		 **/
	}
	
	
	
	var gantt = new CreateGantt();

	document.getElementById("savPrjtPointBtn").style.display = "none";
	if ((document.getElementById("gpName").value.indexOf("项目经理") > -1)
			|| (document.getElementById("gpName").value.indexOf("超级用户") > -1)) {
		document.getElementById("savPrjtPointBtn").style.display = "";
		gantt.setReadOnly(false);
	} else {
		document.getElementById("savPrjtPointBtn").style.display = "none";
		gantt.setReadOnly(true);
	}

	/* 创建甘特图对象，设置列配置
	 -----------------------------------------------------------------------------*/
	var columns = [];

	var idColumn = {
		header : "",
		field : "ID",
		width : 30,
		cellCls : "mini-indexcolumn",
		align : "center"
	};
	columns.push(idColumn);

	var nameColumn = {
		name : "name",
		header : "节点名称<br/>String",
		field : "Name",
		width : 200,
		editor : {
			type : "textbox"
		}
	};
	columns.push(nameColumn);

	var orgstartColumn = {
		header : "原始计划完成时间<br/>Date",
		field : "OriginalPlanOveDate",
		width : 110,
		renderer : function(e) {
			var date = e.value;
			if (!date)
				return "";
			return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
					+ date.getDate();
		},
		editor : {
			type : "datepicker"
		}
	};
	columns.push(orgstartColumn);

	var startColumn = {
		header : "最新计划完成时间<br/>Date",
		field : "PlanOverDate",
		width : 110,
		renderer : function(e) {
			var date = e.value;
			if (!date)
				return "";
			return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
					+ date.getDate();
		},
		editor : {
			type : "datepicker"
		}
	};
	columns.push(startColumn);

	var finishColumn = {
		header : "实际完成时间<br/>Date",
		field : "ActualOverDate",
		width : 110,
		renderer : function(e) {
			var date = e.value;
			if (!date)
				return "";
			return date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
					+ date.getDate();
		},
		editor : {
			type : "datepicker"
		}
	};
	columns.push(finishColumn);

	var durationColumn = {
		name : "duration",
		header : "延期时间<br/>Number",
		field : "DelayTime",
		width : 60,
		editor : {
			type : "spinner",
			minValue : 0,
			maxValue : 100
		}
	};
	columns.push(durationColumn);

	var delayResonColumn = {
		name : "delayReson",
		header : "原因说明<br/>String",
		field : "DelayReson",
		width : 200,
		editor : {
			type: "textarea", minWidth: 450, height: 150
		}
	};
	columns.push(delayResonColumn);

	var responseColumn = {
		name : "response",
		header : "责任人<br/>String",
		field : "ResponsUser",
		width : 100,
		editor : {
			type : "textbox"
		}
	};
	columns.push(responseColumn);

	var remarkColumn = {
		header : "备注<br/>String",
		field : "Remark",
		width : 200,
		editor : {
			type: "textarea", minWidth: 350, height: 150
		}
	};
	columns.push(remarkColumn);

	var gantt = new CreateGantt();
	gantt.setShowGanttView(false);
	gantt.setShowTableView(true);
	gantt.setAllowDragDrop(true);
	gantt.setColumns(columns);
	gantt.setTreeColumn("name");
	gantt.render(document.body);
	gantt.setStyle("width:100%;height:100%; margin:10px;");
	/*---------------------------------------------------------------------------*/
	
	/**
	function updateTask() {
		var selectedTask = gantt.getSelected();
		alert("编辑选中节点:" + selectedTask.Name);
	}
	**/

	//加载列表数据
	function loadList() {
		gantt.loading();
		// alert($('#prjtNo', window.parent.document));
		//alert("./PrjtDef!loadProjectTasks.shtml?task.prjtNo="+$('#prjtNo', window.parent.document).val());
		$.ajax({
			// url: "data/taskList.txt",
			url : "./PrjtDef!loadProjectPoints.shtml?prjtDef.prjtNo="
					+ $('#prjtNo', window.parent.document).val(),
			cache : false,
			success : function(text) {
				//alert(text);
				var data = mini.decode(text);
				//列表转树形
				data = mini.arrayToTree(data, "children", "UID", "Parent");
				gantt.loadTasks(data);
				for ( var i = 0; i < data.length; i++) {
					if (data[i].Status == 1) {
						gantt.select(data[i]);
					} else {
						gantt.deselect(data[i]);
					}
				}
				//document.getElementById("savBtn").style.display = "none";
				//document.getElementById("startBtn").style.display = "";
				gantt.unmask();
			}
		});
	}

	loadList(); //这个方式，服务端只需要生成列表数据就可以。

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
		newTask.Name = '<新增节点>'; //初始化任务属性
		gantt.addTask(newTask);
	}
	
	function insertPoint() {
		var newTask = gantt.newTask();
		newTask.Name = '<新增节点>'; //初始化任务属性

		var selectedTask = gantt.getSelected();
		if (selectedTask) {
			gantt.addTask(newTask, "before", selectedTask); //插入到到选中任务之前
			//project.addTask(newTask, "add", selectedTask);       //加入到选中任务之内            
		} else {
			window.confirm("请选中节点");
		}
	}
	
	function removeTask() {
		var task = gantt.getSelected();
		if (task) {
			if (confirm("确定删除节点 \"" + task.Name + "\" ？")) {
				
				var url = "./PrjtDef!deletePrjtPoint.shtml?prjtPoint.pointId="+ task.UID;
				$.ajax({
					url : url,
					type : "POST",
					success : function(text) {
						if (typeof JSON == 'undefined') {
							$('head').append($("<script type='text/javascript' src='include/js/json2.js'>"));
						}
						var oj = JSON.parse(text);
						gantt.removeTask(task);
						window.confirm(oj.msg);
					}
				});
				
			}
		} else {
			window.confirm("请选中节点");
		}
	}
	
	function updateTask() {
		var selectedTask = gantt.getSelected();
		window.confirm("编辑选中节点:" + selectedTask.Name);
	}
	function upgradeTask() {
		var task = gantt.getSelected();
		if (task) {
			gantt.upgradeTask(task);
		} else {
			window.confirm("请选选中节点");
		}
	}
	function downgradeTask() {
		var task = gantt.getSelected();
		if (task) {
			gantt.downgradeTask(task);
		} else {
			window.confirm("请选选中节点");
		}
	}


	function getSelecteds() {
		var tasks = gantt.getSelecteds();
		window.confirm(tasks.length);
	}

/* 鼠标移动到上面显示详细 */
	/*  var li=document.getElementsByTagName("div");
	 //id="mini-19$1$14"
	 // var li=document.getElementsByTagName("div").getElementById("mini-19$1$14");
     var detaildiv=document.createElement("div");
     detaildiv.style.width="100px";
     detaildiv.id="detail";
     detaildiv.style.backgroundColor="#ccc"
     detaildiv.style.height="100px";
     detaildiv.style.border="1px solid #f00";
     detaildiv.style.position="absolute";
     for(var i=0;i<li.length;i++)
     {
         li[i].onmouseover=function()
         {
             detaildiv.innerHTML=this.innerHTML;
             document.body.appendChild(detaildiv);
             document.getElementById("detail").style.display="";
             detaildiv.style.left=event.clientX+"px";
             detaildiv.style.top=event.clientY+"px";
         }
         li[i].onmouseout=function()
         {
             document.getElementById("detail").style.display="none";
         }
     } */
</script>
