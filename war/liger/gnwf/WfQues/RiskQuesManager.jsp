<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>


<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 178
	});
	
	$("#tree1").ligerTree({ 
	     url:'./PrjtDef!loadPrjtTree.shtml', 
        idFieldName :'prjtNo',
        textFieldName :'prjtNm',
        iconFieldName :'iconUri',
        checkbox :false,
        onSelect: function (node, e){
        	var prjtNo = node.data.prjtNo;
        	$("#prjtNo").val(prjtNo);
	        sea();
        }
	});
});
</script>
</head>
<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
			<div id="toolbar"></div>
			<div id="sform" style="margin:10px;height:30px;">
				<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
				<input type="hidden" id="prjtNo"  name = "prjtNo" value="<c:out value="${prjtNo}"/>"/>
				<input type="hidden" id="isRiskQues"  name = "isRiskQues" value="<c:out value="${wfQues.isRiskQues}"/>"/>
				
				<input type="hidden" id="taskCount"/>
				<table>
					<tr>
						<td height="24" width="90" align="center">项目进度：</td><td>
							<select id="scheId" name="wfQues.scheId" style="width:135px">
								<option value="">全部</option>
								<c:forEach items="${schCfgs}" var="schCfg">
									<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
								</c:forEach>
							</select>
						</td>
						<td height="24" width="90" align="center">责任人：</td><td>
							<input type="hidden" id="userId" name="wfQues.userId" size="30"/>
							<input id="txt" name="wfQues.idtfUsr" value="<c:out value="${wfQues.idtfUsr}"/>" size="19" onkeydown="enterTips('sel')" onblur="blurDataClear(this,'userId')" onkeyup="showtips('txt','sel');if(event.keyCode==27)c('sel');"/>
							<BR>
							<select id="sel" style="width:220px; position:absolute; z-index:1000;DISPLAY: none" onkeydown=if(event.keyCode==13)rv('sel','txt') onclick=rv('sel','txt') onchange="getSelRes('sel','userId')"></select>
						</td>
					
					 <td height="24" width="90" align="center">
					 <input id="upBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
					 </td>
					</tr>
				</table>
			</div>
			
			<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
			
			</form>
</div>
</div>
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>