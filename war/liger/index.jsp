<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title></title>
    
    <link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    
    <script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
	<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(function () {
		$("#layout1").ligerLayout({
			topHeight:25,
			minLeftWidth:80,
			minRightWidth:80,
			leftWidth: 178});
		$("#accordion1").ligerAccordion({ height: 580});

		$("#tree1").ligerTree(<c:out value="${json1}" escapeXml="false"/>);

		$("#framecenter").ligerTab(); 
		
		tab = $("#framecenter").ligerGetTabManager();
		tree = $("#tree1").ligerGetTreeManager();

		$("#topmenu").ligerMenuBar(<c:out value="${json}" escapeXml="false"/>);
	});

	function addtab(tabid,text, url) {
		if(tab.isTabItemExist(tabid)) {
			tab.reload(tabid,url);
			tab.selectTabItem(tabid);
		}
		else
			tab.addTabItem({tabid:tabid,text:text,url:url});
		
		tab.addHeight(0);
	}
	function itemclk(item){
		if (!item.id) return;
		if(item.text=='切换系统') {
			$.ligerDialog.confirm('确定要切换系统？', 
					function (type) { 
						if(type) {
							
							$.post(".shtml",
								JSON.parse(str),
								function(data) {
									$.ligerDialog.success(data);
								},
								"text"
							);
						}
					}
				);
		}
		else {
			tree.clear();
			tree.loadData(tree,"./ligerTree.shtml",{"id":item.id,"parm":item.parm});
		}
	}
	function itemck0(item) {
		if (!item.id || !item.parm) return;
		tree.clear();
		tree.loadData(tree,"./prjtTree.shtml",{"id":item.id,"parm":item.parm});
	}
	function nodeclk(node) {
		if (!node.data.url || node.data.url == "#") return;

		addtab(node.data.id,node.data.text,node.data.url);
	}
	function nullclk(){}
	</script>

</head>

<body style="padding-top:0px;padding-left:20px;padding-right:20px;">

<div id="layout1">

	<div position="top" id="top">
		<div id="topmenu">
		<span style="float:right;">
			<b><font color="#ff4500" size="2">[</font></b>&nbsp;
			<c:out value="${SYUSR.comNm}"/>
			<c:out value="${SYUSR.deptNm}"/>
			<c:out value="${SYUSR.usrName}"/>&nbsp;
			<b><font color="#ff4500" size="2">]</font></b>&nbsp;&nbsp;&nbsp;
			<c:out value="${SYUSR.syNm}"/>&nbsp;&nbsp;&nbsp;
			<a href="Login!logout.shtml">退出</a>&nbsp;&nbsp;&nbsp;
		</span>
		</div>
	</div>

	<div position="left" style="overflow:auto;overflow-x: hidden;" id="left" title="功能菜单" class="l-scroll">

			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">
	
		<div tabid="home" title="主页" lselected="true"> 
			
		</div>
		
	</div>
</div> 
<script type="text/javascript">
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
</body>

</html>

