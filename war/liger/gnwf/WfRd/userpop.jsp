<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
.trans-bg{width:100%;height:100%;z-index:998;position:absolute;bottom:0;left:0;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;}
.popup{background:url(./include/images/alert-bg.jpg) repeat-x #f5f5f5;position:absolute;z-index:999;border-radius:2px;top:120px;left:50%;display:none;}
.popup-t{line-height:27px;font-size:14px;font-weight:bold;color:#028ebc;padding:3px 15px 10px 15px;}
.popup-t .close{position:absolute;right:15px;top:8px;cursor:pointer;}
.popup-t img{vertical-align:middle;}
</style>

<div class="trans-bg" id="trans-bg"></div>
<div id="popup-dlg" class="popup" >
	<div class="popup-t">
		选择对话框<span class="close" id="popup-btn" onclick="closePop();"><img src="./include/images/alert-close.jpg" /></span>
	</div>
	<div class="popup-c">
		<iframe id="popup-frame" name="popup-frame" src="" border=0 frameborder="0" scrolling="no"></iframe>
	</div>
</div>


<script type="text/javascript">
function openPop(url){
	$("#popup-dlg").show();
	$("#trans-bg").show();
	$("#popup-frame").attr("src",url);
	$("#popup-frame").load(function(){
		var urlhei = $("#popup-frame").contents().find(".popup-html").height();
		var urlwid = $("#popup-frame").contents().find(".popup-html").width();
		$(this).height(urlhei);
		$(this).width(urlwid);
		$("#popup-dlg").css("margin-left","-"+urlwid/2+"px");
		$("#popup-dlg").css("margin-bottom","15px");//弹框距离dib
	})
}
function closePop(){
	$("#popup-dlg").hide();
	$("#trans-bg").hide();
}
</script>