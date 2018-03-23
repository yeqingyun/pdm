<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link href="./include/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js" ></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>

<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>
<style type="text/css">
	ul,
	ol {
	  padding: 0;
	  margin: 0 0 9px 25px;
	}
	ul ul,
	ul ol,
	ol ol,
	ol ul {
	  margin-bottom: 0;
	}
	ul {
	  list-style: disc;
	}
	ol {
	  list-style: decimal;
	}
	li {
	  line-height: 18px;
	}
	ul.unstyled,
	ol.unstyled {
	  margin-left: 0;
	  list-style: none;
	}
</style>
</head>
<body style="padding:10px"> 
    <form>
    <input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
    	<div class="l-form" style="width: 700px;background-color: #FFFEF8">
	    	<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif"><span>任务接收</span></div>
	  		<ul>
				<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
					<ul>
						<li style="width:90px;text-align:left;">流程编号 ：</li>
						<li style="width:240px;text-align:left;">A2013500003</li>
					</ul>
				</li>
			</ul>
	  		<ul>
	  			<li fieldindex="2" class="l-fieldcontainer">
					<ul>
						
						<li style="width:300px;text-align:left;">
							<div class="form-actions">
								<span>有您的任务，接收任务请点击</span>
								<button id="create_button" class="btn btn-large btn-primary" onclick="acceptJob();">接收任务</button>
							</div>
						</li>
					</ul>
				</li>
	  		</ul>
		</div> 
    </form> 
</body>
</html>