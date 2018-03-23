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

<script src="./include/js/zrprjt/prjtUsr.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];

var upTypData = [{ UpTyp: 1, text: '加入项目组' }, { UpTyp: 0, text: '离开项目组'}];

$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'PrjtUsr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [

            { display: '项目类型', name: 'prjtTypNm', align: 'left', width: 120 },
			{ display: '角色名', name: 'roleNm', align: 'left', width: 120 },
			{ display: '用户名', name: 'usrName', align: 'left', width: 120 },
			{ display: '优先级别', name: 'priority', align: 'left', width: 120 },
			{ display: '角色类型', name: 'roleTyp', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: roleTypData, valueColumnName: 'roleTyp' },
                render: function (item)
                {
                    if (parseInt(item.roleTyp) == 0) return '人员不随项目变更';
                    return '人员随项目变更';
                }},
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }}
			//{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			//{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			//{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			//{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./PrjtUsr!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		//onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow,
	    frozen:false,
		pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
        showTitle: false,width:'90%',columnWidth:120,
        detail: { onShowDetail: f_showOrder,height:'auto' },
        onError: function (a, b)
        { 
        }
		
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

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

    //显示顾客订单    upTypData
    function f_showOrder(row, detailPanel,callback)
    {
        var grid = document.createElement('div'); 
        $(detailPanel).append(grid);
        $(grid).css('margin',10).ligerGrid({
            columns:
                        [
                         { display: '名字', name: 'usrName', width: 240 },
	                    { display: '记录类型', name: 'upTyp',  width: 240 ,type:'int',
	        				editor: { type: 'select', data: roleTypData, valueColumnName: 'upTyp' },
	                        render: function (item)
	                        {
	                            if (parseInt(item.upTyp) == 1) return '加入项目组';
	                            if (parseInt(item.upTyp) == 0) return '离开项目组';
	                        }},
	                    { display: '时间', name: 'createDate' , width: 230 }
                        ], isScroll: false, showToggleColBtn: false, width: '90%',
           // data: f_getOrdersData(row.id) ,
           
           url:'./PrjtUsr!getPUsRecs.shtml?prjtUsrId='+row.id,
            
            showTitle: false, columnWidth: 100 ,usePager:false
             , onAfterShowData: callback,frozen:false
        });  
    }



</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
		
		   <!--td height="24" width="90" align="center">项目编号：</td>
			<td>
				<select id="prjtNo" name="prjtUsr.prjtNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtDefs}" var="prjtDef">
						<option value="<c:out value="${prjtDef.prjtNo}"/>"><c:out value="${prjtDef.prjtNo}"/></option>
					</c:forEach>
				</select>
			</td  -->
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
			<td height="24" width="90" align="center">状态：</td>
			<td>
			   <select id="status" name="prjtUsr.status" style="width: 196px">
			        <option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			 </td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
