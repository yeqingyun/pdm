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

<script type="text/javascript">

function retrans(){
	if(usrId){
		$.ligerDialog.confirm('确定要转发问题？', 
				function (type) { 
					if(type) {
						var str = '{';
						str += '"wfQues.quesId":"'+$("#quesId").val()+'",';
						str += '"wfQues.userId":"'+usrId+'"';
						str += '}';
						//alert(str);
						$.post("WfQues!upd.shtml",
							JSON.parse(str),
							function(data) {
								window.parent.savDeal(data);
							},
							"text"
						);
					}
				}
			);
	}
}
function seaPrjtUsr(){
	
}



 var gridManager;
$(function () {
	/**
	$.post("ligerToolBar0.shtml",
			{parm:'PrjtUsr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	**/
	
	$("#toolbar").ligerToolBar({ items: [
	                               		    { text: '转发', click: retrans },
	                               		   // { line:true },
	                               		   // { text: '搜索', click: seaPrjtUsr ,disable: true}
	                               		]
	 });
	
	$("#maingrid").ligerGrid({
		columns: [

			{ display: '角色名', name: 'roleNm', align: 'left', width: 150 },
			{ display: '用户名', name: 'usrName', align: 'left', width: 150 }
		],
		checkbox: true,
		rownumbers:true,
		pageSize:10,
		url:'./PrjtUsr!prjtUsrlist.shtml?prjtNo='+$("#prjtNo").val(),
		usePager:true,
		width: '99.5%',
		height:'99%',
		//isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		//onCheckRow: f_onCheckRow,
		//onDblClickRow: f_onDblClickRow,
		//onCheckAllRow: f_onCheckAllRow,
	    frozen:false,
		pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
       // showTitle: false,width:'90%',columnWidth:120,
       // detail: { onShowDetail: f_showOrder,height:'auto' },
        onError: function (a, b)
        { 
        }
		
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	/**
	alert($("#prjtNo").val());
	gridManager.setOptions({
		parms: [
	    	{name: 'prjtNo', value: ($("#prjtNo").length > 0)?$("#prjtNo").val():''},
		]
	});
	**/
});




var checkedCustomer = [];
var usrId;
function f_onCheckRow(checked, data){
	if (checked){
		//alert(data.id);
		usrId = data.usrId
	}
	else{
		usrId = null;
	}
}



/**
$(function(){
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerComboBox();
	if ($("#roleId").length > 0)
		$("#roleId").ligerComboBox();
	if ($("#usrId").length > 0)
		$("#usrId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();

});
**/


</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">
<input type="hidden" id="prjtNo" name ="prjtNo" value="<c:out value="${wfRd.projectNo}"/>"/>
<input type="hidden" id="quesId" name ="quesId" value="<c:out value="${wfQues.quesId}"/>"/>
<!--div id="sform" style="margin:10px;height:30px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">角色名称：</td>
			<td>
			 <select id="roleId" name="prjtUsr.roleId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtRoles}" var="prjtRole">
						<option value="<c:out value="${prjtRole.roleId}"/>"><c:out value="${prjtRole.roleNm}"/></option>
					</c:forEach>
				</select>
			 </td>
			<td height="24" width="90" align="center">用户名：</td><td><input type="text" id="usrName" name="prjtUsr.usrName"/></td>
		</tr>

	</table>
</div  -->
<br></br>
<div id="maingrid" ></div>

</form>

</body>

</html>
