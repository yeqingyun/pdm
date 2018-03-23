<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	.l-form li {
		float: left;
	    overflow: hidden;
	    padding: 2px 0;
	    line-height: 15px;
	}
</style>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js" ></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>

<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>
<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfReply'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '附件(点击可下载)', name: 'createDate', align: 'left', width: 120 },
			{ display: '步骤', name: 'lastUpdDate', align: 'left', width: 120 },
			{ display: '版本', name: 'replyId', align: 'left', width: 60 },
			{ display: '类别', name: 'quesId', align: 'left', width: 60 },
			{ display: '上传人', name: 'userId', align: 'left', width: 100 },
			{ display: '上传时间', name: 'date', align: 'left', width: 120 }
		],
		rownumbers:true,
		pageSize:20,
		url:'./WfReply!list.shtml',
		usePager:false,
		width: 650,
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});
</script>
</head>
<body style="padding:10px"> 
    <form>
            <input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
           <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
    		<div class="l-form" style="width: 700px;margin:0 auto;background-color: #FFFEF8">
		    	<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif"><span>基础信息</span></div>
		  		<ul>
					<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first" style="line-height: 10px">
						<ul>
							<li style="width:90px;text-align:left;">流程编号 ：</li>
							<li style="width:240px;text-align:left;">A2013500003</li>
						</ul>
					</li>
					<li fieldindex="2" class="l-fieldcontainer">
						<ul>
							<li style="width:90px;text-align:left;">步骤名称 ：</li>
							<li style="width:240px;text-align:left;">选择结构供应商( <font color="red">主办</font>) </li>
						</ul>
					</li>
				</ul>
		  		<ul>
					<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
						<ul>
							<li style="width:90px;text-align:left;">完成时间 ：</li>
							<li style="width:240px;text-align:left;">2013-5-22</li>
						</ul>
					</li>
					<li fieldindex="2" class="l-fieldcontainer">
						<ul>
							<li style="width:90px;text-align:left;">内容描述 ：</li>
							<li style="width:240px;text-align:left;">无 </li>
						</ul>
					</li>
				</ul>
				<div>
					<ul>
					<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
						<ul>
							<li style="width:90px;text-align:left;">完成时间 ：</li>
							<li style="width:240px;text-align:left;">2013-5-22</li>
						</ul>
					</li>
					<li fieldindex="2" class="l-fieldcontainer">
						<ul>
							<li style="width:90px;text-align:left;">内容描述 ：</li>
							<li style="width:240px;text-align:left;">无 </li>
						</ul>
					</li>
				</ul>
				<ul>
					<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
						<ul>
							<li style="width:90px;text-align:left;">完成时间 ：</li>
							<li style="width:240px;text-align:left;">2013-5-22</li>
						</ul>
					</li>
					<li fieldindex="2" class="l-fieldcontainer">
						<ul>
							<li style="width:90px;text-align:left;">内容描述 ：</li>
							<li style="width:240px;text-align:left;">无 </li>
						</ul>
					</li>
				</ul>
				<ul>
					<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
						<ul>
							<li style="width:90px;text-align:left;">完成时间 ：</li>
							<li style="width:240px;text-align:left;">2013-5-22</li>
						</ul>
					</li>
					<li fieldindex="2" class="l-fieldcontainer">
						<ul>
							<li style="width:90px;text-align:left;">内容描述 ：</li>
							<li style="width:240px;text-align:left;">无 </li>
						</ul>
					</li>
				</ul>
				</div>
				<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif"><span>说明</span></div>
				<ul>
					<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
						<ul>
							<li style="width:660px;text-align:left;"><textarea rows="5" cols="20" style="width:650px;"></textarea></li>
						</ul>
					</li>
				</ul>
				<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif"><span>公共附件</span></div>
				<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
				<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif"><a href="#" onclick="addFile()">添加附件</a></div>
				<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="660px" align=center>
					<tr>
						<td>
							<table id="attachtab" border="0" cellpadding="0" cellspacing="0">
							</table>
						</td>
					</tr>
				</table>
			</div> 
    </form> 
</body>
</html>