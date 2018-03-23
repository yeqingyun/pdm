<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script type="text/javascript">

	function Map() {
		this.container = new Object();
	}

	Map.prototype.put = function(key, value) {
		this.container[key] = value;
	}

	Map.prototype.get = function(key) {
		return this.container[key];
	}

	Map.prototype.keySet = function() {
		var keyset = new Array();
		var count = 0;
		for ( var key in this.container) {
			// 跳过object的extend函数
			if (key == 'extend') {
				continue;
			}
			keyset[count] = key;
			count++;
		}
		return keyset;
	}

	Map.prototype.size = function() {
		var count = 0;
		for ( var key in this.container) {
			// 跳过object的extend函数
			if (key == 'extend') {
				continue;
			}
			count++;
		}
		return count;
	}

	Map.prototype.remove = function(key) {
		delete this.container[key];
	}

	Map.prototype.toString = function() {
		var str = "";
		for ( var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
			str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
		}
		return str;
	}
	
	$(function() {
		$.post("ligerToolBar0.shtml", {
			parm : 'ProcFile'
		}, function(data) {
			$("#toolbar").ligerToolBar(data);
		}, "json");

		$("#layout1").ligerLayout({
			topHeight : 25,
			minLeftWidth : 80,
			minRightWidth : 80,
			leftWidth : 178
		});

		$("#tree1").ligerTree({
			url : './PrjtDef!loadPrjtTree.shtml?loadPrjtTreeType=doc',
			idFieldName : 'prjtNo',
			textFieldName : 'prjtNm',
			iconFieldName : 'iconUri',
			checkbox : false,
			onSelect : function(node, e) {
				var prjtNo = node.data.prjtNo;
				$("#projectNo").val(prjtNo);
				flush();
			}
		});
		tree = $("#tree1").ligerGetTreeManager();
		tree.selectNode(document.getElementById("projectNo").value);
		$("#navProjectTab").ligerTab({
			contextmenu : false
		});
		$("#navProjectTab").ligerGetTabManager();

		flushStatic();
	});

	// 存放 子窗口的信息
	var winMap = new Map();
	var openWin = function(ops, key) {
		if(key){
			/*if(winMap.get(key)){
				return winMap(get(key));
			}*/
			var win = $.ligerDialog.open(ops);
			winMap.put(key,win);
			return win;
		}else{
			return $.ligerDialog.open(ops);
		}
	}
	var closeWin=function(key){
		if(key){
			if(winMap.get(key)){
				var win = winMap.get(key);
				win.close();
				winMap.remove(key);
			}
		}
	}
	var getWin=function(key){
		if(key){
			return winMap.get(key);
		}
		return null;
	}
	var putWin=function(key,win){
		if(key)
			winMap.put(key,win);
	}

	function flush() {
		var para = "?wfDoc.projectNo=" + $("#projectNo").val();
		<c:if test="${!reShareBl}">
			$("#pdoc").attr("src", "./WfDoc!pDoc.shtml" + para);
			$("#bdoc").attr("src", "./WfDoc!bDoc.shtml" + para);
		</c:if>
		<c:if test="${reShareBl}">
			$("#slib").attr("src", "./WfDoc!sLib.shtml" + para);
		</c:if>
	}
	function flushStatic(){
		if($("#usrId").val()=='<%=gnwf.ww.MSG.SHARE_LIB_USR_ID%>'){
			$("#pdoc").hide();
			$("#bdoc").hide();
			$("#slib").show();
			alert(1);
		}else{
			$("#pdoc").show();
			$("#bdoc").show();
			$("#slib").hide();
		}
		
	}
	function selectTable(control) {

	}

	function f_click() {
		$("#navProjectTab").ligerTab();
		var navtab = $("#navProjectTab").ligerGetTabManager();
		navtab.selectTabItem("startFlowTab");
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
</script>
</head>

<body style="padding: 10px;">
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
	<div id="layout1" style="overflow-x: hidden; overflow-y: hidden;">
		<div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
		</div>
		<div position="center" id="framecenter">
			<div position="center" id="navProjectTab" name="navProjectTab" style="width: 100%; overflow: hidden; border: 1px solid #A3C0E8">
			
		   <c:if test="${!reShareBl}">
				<div title="过程文档" id="pdocdiv" lselected="true" style="width: 100%; height: 100%;">
					<iframe id=pdoc frameborder="0" name="pdoc" style="width: 100%; height: 450px" src=""></iframe>
				</div>
				<div title="基线文档" id="bdocdiv" style="width: 100%; height: 100%">
					<iframe id="bdoc" frameborder="0" name="bdoc" style="width: 100%; height: 450px"></iframe>
				</div>
			</c:if>
		   <c:if test="${reShareBl}">
				<div title="发布库" id="slibdiv" style="width: 100%; height: 100%">
					<iframe id="slib" frameborder="0" name="slib" style="width: 100%; height: 450px"></iframe>
				</div>
			</c:if>
			</div>
		</div>
	</div>
	<input type="hidden" id="projectNo" name="wfDoc.projectNo" />
</body>

</html>