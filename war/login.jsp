<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
	.clearfix:after {visibility:hidden;display:block;font-size:0;content: " ";clear:both;height:0;}.clearfix{*zoom:1;}
	.wrap{width:900px;margin:0 auto;font-size:12px;}
	.top{padding:15px;height:57px;line-height:57px;font-size:12px;}
	.top h1{float:left;}
	.top p{float:right;color:#4a4a4a;}
	.top p a{color:#4a4a4a;text-decoration:none;}
	.top p a:hover{color:#4a4a4a;text-decoration:underline;}
	.main{height:362px;width:100%;background:url(./include/img/lgnpic.png) no-repeat 5px center;padding:0 15px 0 0;margin:20px 10px;}
	.login{float:right;display:inline;padding:30px;width:280px;border:4px solid #a3d1f7;background:#ecf7ff;border-radius:5px;color:#464646;margin:45px 20px 0 0;}
	.login label,.login input{vertical-align:middle;}
	.login td,.login th{padding:3px 0;}
	.login th{font-size:12px;text-align:right;font-weight:normal;}
	.login .infoinput{width:170px;height:22px;line-height:22px;padding:0 2px;background:#fff;border:1px solid #959595;color:#686868;}
	.login .autolgn{line-height:16px;font-family:tahoma;}
	.login .submit input{height:30px;line-height:30px;}
	.login .submit input{width:89px;height:30px;background:url(./include/img/lgnbtn.png);border:none;cursor:pointer;}
	.login .submit a{color:#f00;}
	.login .submit a:hover{color:#f00;}
	.foot{color:#6e6c6c;padding:10px;text-align:center;color:#6e6c6c;border-top:1px solid #c0c0c0;}
</style>

<script>
function check() {
	
	
	/**
	if(document.getElementById("syDef.syId").value=="") {
		alert("请选择系统。");
		document.getElementById("syDef.syId").focus();
		return false;
	}
	**/
	//alert("ffffff");
	
	/**
	
	    var Sys = {};
	    var ua = navigator.userAgent.toLowerCase();
	    var s;
	     (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
	     (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
	     (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	     (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
	     (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	
	    //以下进行测试
	   // if (Sys.ie) document.write('IE: ' + Sys.ie);
	   // if (Sys.firefox) document.write('Firefox: ' + Sys.firefox);
	    if (!Sys.chrome) {
	    	alert("对不起！本系统只支持google浏览器,请用google浏览器打开,如果您还未安装请在本页面的的'资源下载'处下载google浏览器安装");
	    	return false;
	    }
	  //  if (Sys.opera) document.write('Opera: ' + Sys.opera);
	  // if (Sys.safari) document.write('Safari: ' + Sys.safari);
	
	  
	  
	  **/
	
	
	if(document.getElementById("usr.login").value=="") {
		alert("请输入登录账号。");
		document.getElementById("usr.login").focus();
		return false;
	}
	if(document.getElementById("usr.pwd").value=="") {
		alert("请输入登录密码。");
		document.getElementById("usr.pwd").focus();
		return false;
	}
	
	
}


function checkParent(){
    if(window != top){ 
        top.window.location="Login.shtml"; 
    }
}

</script>
</head>

<body onload="checkParent();">

<div class="wrap">

	<div class="top clearfix">
		<h1><img src="./include/img/lgnlogo.png" alt="金立通信设备有限公司" title="金立通信设备有限公司" /></h1>
		<p><a href="javascript:;">金立官网</a> | <a href="javascript:;">网上商城</a> | <a href="javascript:;">金立论坛</a></p>
	</div>

	<div class="main">

				   
		<div class="login">
			<form action="Login!login.shtml" method="post" onsubmit="return check();">
			<table cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<th>用户名&nbsp;&nbsp;</th>
					<td><input type="text" class="infoinput" id="usr.login" name="usr.login"/></td>
				</tr>
				<tr>
					<th>密码&nbsp;&nbsp;</th>
					<td><input type="password" class="infoinput" id="usr.pwd" name="usr.pwd" />
					</td>
				</tr>
				<tr><td colspan=2> 
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      <FONT face=楷体_GB2312 color=blue size=2>
				       <STRONG>本系统的密码与<a href="http://hr.gionee.com"  target="_blank"><span style="color: red">HR系统</span></a>的<span style="color: red">密码</span>相同
				       </STRONG>
				      </FONT></td>
				</tr>
				<tr><th colspan=2></th></tr>
				<tr>
					<th></th>
					<td class="autolgn"><input type="checkbox" /><label>下次自动登录（公共场合慎用）</label></td>
				</tr>
				<tr>
					<th></th>
					<td class="submit"><input type="submit" value=''/>&nbsp;<label><a href="javascript:;">忘记密码？</a></label></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	<div class="foot">
		资源下载：
		<!-- 
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <img align="middle" src="./include/img/workflow/ff.png"/>
    	 <a href="./Login!downLoad.shtml?filePath=/include/firefoxBrowser.rar&fileNm=火狐浏览器.rar">火狐浏览器</a>
        -->
    	 &nbsp;&nbsp;&nbsp;&nbsp;
    	 <img align="middle" src="./include/img/workflow/google.png"/>
    	 <a href="./Login!downLoad.shtml?filePath=/include/googleBrowser.rar&fileNm=google浏览器.rar"> google览器</a>
	     &nbsp;&nbsp;&nbsp;&nbsp;
    	 <img align="middle" src="./include/img/workflow/HttpUpload.png"/>
    	 <a href="./Login!downLoad.shtml?filePath=/include/HttpUploader5.rar&fileNm=大附件上传控件.rar"> 大附件上传控件</a>
    	 &nbsp;&nbsp;&nbsp;&nbsp;
    	 <img align="middle" src="./include/img/workflow/ques_temp.png"/>
    	 <a href="./Login!downLoad.shtml?filePath=/include/template/question_template.xls&fileNm=question_template.xls"> 问题导入模板</a>
    	 
    	 
	
	</div>
	<div class="foot">
		@版权所有：深圳市金立通信设备有限公司 
		&nbsp;&nbsp;&nbsp;&nbsp;
		软件开发：金立集团信息中心开发部
	</div>
</div>
</body>
</html>
