<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
	<head>
		<title>Tabbar with scrollbars</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />

	</head>
	<link rel="STYLESHEET" type="text/css" href="dhtmlxtabbar.css">
	<script  src="dhtmlxcommon.js"></script>
	<script  src="dhtmlxtabbar.js"></script>

	<body>
	<h1 id="init_tabbar_from_script">Tabbar with scrollbars</h1>
	<div id="a_tabbar" style="width:315px; height:100px;"></div><br/>
			<button onclick="getHTML()">getHTML</botton>

<script>

function do_tabbar(cont, skin){
	
	          var tabbar=new dhtmlXTabBar(cont,"top");
	          tabbar.setSkin(skin);
            tabbar.setImagePath("./imgs/");
            //tabbar.addTabs([{"id":"c11","text":"Tab 3-1 sub","child":[{"id":"c112","text":"Tab 3-1 sub sub"}]},{"id":"c12","text":"Tab 3-1 sub2","href":"common/test_page_1.html"}]);
            tabbar.addTab("b1","Tab 1-1","100px");
            tabbar.addTab("c1","Tab 1-2","100px",null,0,{"id":"c1","text":"Tab 1-2","href":"http://www.baidu.com","child":[{"id":"c11","text":"Tab 3-1 sub","child":[{"id":"c112","text":"Tab 3-1 sub sub"}]},{"id":"c12","text":"Tab 3-1 sub2","href":"common/test_page_1.html"}]});
            tabbar.setHrefMode("iframes");
            
            //tabbar.setTabActive("a1");
            tabbar.setTabChild("b1",[{"id":"b11","text":"Tab 1-1 sub "}]);
           // window[cont]=tabbar;
}    
do_tabbar("a_tabbar","dhx_blue");



function getHTML(){
	prompt("",document.getElementById("a_tabbar").outerHTML);
}
</script>
			

	</body>
</html>