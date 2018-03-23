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
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>

<script type="text/javascript">
$(function (){
	var data = [];
	data.push({'children':[{id: 1,status:1,text: '待解决'},{id: 2,status:9,text: '验证未通过'},
		{id: 3,status:10,text: '待验证'},{id: 4,status:11,text: '验证通过'}],
		id:9,status:100,text:'未关闭'});    
	data.push({id: 5,status:21,text: '已挂起'});
	data.push({id: 6,status:30,text: '已关闭'});
	data.push({id: 7,status:40,text: '转风险'});
    $("#treeStatus").ligerComboBox({
        width : 130, 
        selectBoxWidth: 160,
        selectBoxHeight: 180, valueField: 'text', treeLeafOnly: false,
        tree: {data: data,checkbox: false, onClick:function(data,target){
        	if($("#status").val() == data.data.status) {
        		$("#status").val("");
        	}else {
        		$("#status").val(data.data.status);
        	}
        }}
    });
});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>
<div id="toptoolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
<input type="hidden" id="isRisk" value="<%=request.getParameter("isRisk")%>"/>
<input type="hidden" id="prjtNo" value="<c:out value="${prjtNo}"/>"/>
  <br />
	<table>
		<tr>
			<td align="left">阶段：</td>
			<td  align="left">
				<select id="scheId" name="wfQues.scheId" style="width:135px">
					<option value="">全部</option>
					<c:forEach items="${schCfgs}" var="schCfg">
						<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td  align="left">状态：</td>
			<td >
				<input type="text" id="treeStatus"/>
				<input type="hidden" id="status" name="wfQues.status"/>
			</td>
			<td  align="left">责任人：</td>
			<td >
				 <input id="usrName" name="wfQues.usrName" style="width:80px"/>
			</td>
			<td  align="left">问题等级：</td>
			<td >
				<select id="cateId" name="wfQues.cateId" style="width:135px">
					<option value="">全部</option>
					<!-- <option value="1">S</option> -->
					<option value="2">B</option>
					<option value="3">C</option>
					<option value="4">D</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="left"><input id="queryBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" /></td>
		</tr>
	</table>
	<br />
<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>