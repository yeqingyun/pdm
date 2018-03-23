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
	/**
	$.post("ligerToolBar0.shtml",
			{parm:'WfQues'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#toptoolbar").ligerToolBar({ items: [
   		    { text: '回复', click: rep },
   		    { line:true },
   		    { text: '关闭', click: cls ,disable: true},
   		    { line:true },
   		    { text: '走风险', click: grisk }
   		]
   	});
	*/
});

$(function(){
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false,width:110});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false,width:110});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#quesId").length > 0)
		$("#quesId").ligerTextBox();
	if ($("#usrName").length > 0)
		$("#usrName").ligerTextBox({width:110});
	if ($("#scheId").length > 0)
		$("#scheId").ligerComboBox();
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#cateId").length > 0)
		$("#cateId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerTextBox();
	if ($("#quesLevel").length > 0)
		$("#quesLevel").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox({width:110});
	if ($("#prjtNo").length > 0)
		//$("#prjtNo").ligerComboBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerComboBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#isRisk").length > 0)
		$("#isRisk").ligerTextBox();
	if ($("#result").length > 0)
		$("#result").ligerTextBox();
	/*
	
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:'typNm', valueField:'typId', isMultiSelect: false,
		onSelected: function (newvalue)
		{
			$.post("SchCfg!getPreSchCfgs.shtml",
			{'schCfg.typId':newvalue},
			function(data) {
				$("#scheId").ligerGetComboBoxManager().setData(data);

			},
			"json"
			);
		}
		});*/

		//$("#scheId").ligerComboBox({textField:'schNm', valueField:'schId', isMultiSelect: false});
});
</script>
</head>

<body style="padding:0px;">



<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
<!-- 	<div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll"> -->
<!-- 			<ul id="tree1"></ul> -->
<!-- 	</div> -->

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
						<!--td height="24" width="90" align="center">项目分类：</td><td>
							<input type="text" id="typId" name="wfQues.typId" size="30"/>
							<input type="text" id="scheId" name="wfQues.scheId" size="32"/>
						</td  -->
						
						<td height="24" width="90" align="center">项目进度：</td><td>
							<select id="scheId" name="wfQues.scheId" style="width:135px">
								<option value="">全部</option>
								<c:forEach items="${schCfgs}" var="schCfg">
									<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
								</c:forEach>
							</select>
						</td>
						
						
						
						<td height="24" width="90" align="center">状态：</td>
						<td>
							<select id="status" name="wfQues.status" style="width:135px">
								<option value="">全部</option>
								<option value="19">未关闭</option>
								<option value="21">已挂起</option>
								<option value="30">已关闭</option>
							
							</select>
						</td>
						
						<td height="24" width="90" align="center">责任人：</td><td>
							<input type="hidden" id="userId" name="wfQues.userId" size="30"/>
							<input id="txt" name="wfQues.idtfUsr" value="<c:out value="${wfQues.idtfUsr}"/>" size="19" onkeydown="enterTips('sel')" onblur="blurDataClear(this,'userId')" onkeyup="showtips('txt','sel');if(event.keyCode==27)c('sel');"/>
							<BR>
							<select id="sel" style="width:220px; position:absolute; z-index:1000;DISPLAY: none" onkeydown=if(event.keyCode==13)rv('sel','txt') onclick=rv('sel','txt') onchange="getSelRes('sel','userId')"></select>
						</td>
						<!--td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="wfQues.startCreateDate"/></td  -->
					
					 <td height="24" width="90" align="center">
					 <input id="upBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
					 </td>
					 
					  <td height="24" width="90" align="center">
					 <input id="upBtn" type="button" value=" 新增" class="wfbigbtn2"  onclick="add(0);" />
					 </td>
					
					  <td height="24" width="90" align="center">
					 <input id="upBtn" type="button" value=" 导出"  class="wfbigbtn2" onclick="exp();" />
					 </td>
					
					</tr>
					
					
					
					
					
					
					
					
						<!--td height="24" width="90" align="center">项目名称：</td>
						<tr>
						<td>
						<select id="prjtNo" name="wfQues.prjtNo">
							<option value="">请选择</option>
							<c:forEach items="${prjtDefs}" var="def">
								<option value="<c:out value="${def.prjtNo}"/>"><c:out value="${def.prjtNm}"/></option>
							</c:forEach>
						</select>
						</td  -->
						<!--td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfQues.wfNo"/></td  -->
						
						<!--td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="wfQues.endCreateDate"/></td  -->
					
				<%--
				</tr>
					<input type="text" id="usrName" name="wfQues.usrName" size="30"
								onclick="openPop('WFSelectUser.shtml?taskType=1&count=1');"/>
				
					<select id="scheId" name="wfQues.scheId" style="width:135px">
						<option value="">请选择</option>
						<c:forEach items="${schCfgs}" var="schCfg">
							<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
						</c:forEach>
					</select>
					<td height="24" width="90" align="center">创建人：</td><td>
							<select id="createBy" name="wfQues.createBy" style="width:135px">
								<option value="">请选择</option>
								<c:forEach items="${usrs}" var="usr">
									<option value="<c:out value="${usr.id}"/>"><c:out value="${usr.usrName}"/></option>
								</c:forEach>
							</select>
						</td>
					<tr>
						<td height="24" width="90" align="center">问题等级：</td><td><input type="text" id="quesLevel" name="wfQues.quesLevel"/></td>
						<td height="24" width="90" align="center">分类ID：</td><td><input type="text" id="cateId" name="wfQues.cateId"/></td>
					</tr>
				 --%>
				</table>
			</div>
			
			<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
			
			</form>
</div>

<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
<script type="text/javascript">
        $(function ()
        {
        	
        	$("#layout1").ligerLayout({
        		topHeight:25,
        		minLeftWidth:80,
        		minRightWidth:80,
        		leftWidth: 178
        	});
        	
        	
//         	$("#tree1").ligerTree({ 
//         	     url:'./PrjtDef!loadPrjtTree.shtml', 
//                 idFieldName :'prjtNo',
//                 textFieldName :'prjtNm',
//                 iconFieldName :'iconUri',
//                 checkbox :false,
//                 onSelect: function (node, e){
//                 	var prjtNo = node.data.prjtNo;
//                 	//$("#flowId").val(21);
//                 	$("#prjtNo").val(prjtNo);
//                 	//alert($("#prjtNo").val());
//         	        sea();
//                 }
//         	});
   		});
   	
       
    </script>
</html>