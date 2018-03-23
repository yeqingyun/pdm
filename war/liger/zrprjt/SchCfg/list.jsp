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

<script src="./include/js/zrprjt/schCfg.js?v=1.0" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
var manualStartData = [{ ManualStart: 0, text: '否' }, { ManualStart: 1, text: '是'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'SchCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '进度名称', name: 'schNm', align: 'left', width: 120 },
			//{ display: '进度编号', name: 'schId', align: 'left', width: 120 },
			{ display: '工时', name: 'cfgTime', align: 'left', width: 120 },
			{ display: '工作流', name: 'flowName', align: 'left', width: 120 },
			{ display: '序号', name: 'schNo', align: 'left', width: 120 ,type:'float'},
			//{ display: '级别', name: 'leve', align: 'left', width: 120 },
			{ display: '项目分类', name: 'prjtTypNm', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }},
                
            { display: '是否手工启动', name: 'manualStart', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: manualStartData, valueColumnName: 'manualStart' },
                render: function (item)
                {
                    if (parseInt(item.manualStart) == 0) return '否';
                    return '是';
                }},
		/**	{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '流程ID', name: 'flowId', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			**/
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		
		
		
		 allowHideColumn: false, 
		// rownumbers: true, 
		 colDraggable: true, 
		 rowDraggable: true,
		// checkbox:true,
         //data: TreeDeptData, 
         alternatingRow: false, 
         tree: { columnName: 'schNm',single:true },
		
		
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./SchCfg!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
	if ($("#schId").length > 0)
		$("#schId").ligerTextBox();
	if ($("#schNo").length > 0)
		$("#schNo").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:"typNm", valueField:"typId",  isMultiSelect: false,
		onSelected: function (newvalue)
        {
			document.getElementById("schCfg.typId").value = newvalue;
			$.post("SchCfg!getPreSchCfgs.shtml",
					{'schCfg.typId':newvalue},
					function(data) {
			            $("#parent").ligerGetComboBoxManager().setData(data);
					},
					"json"
				);
        }
    });
	
    $("#parent").ligerComboBox({data: null, textField:"schNm", valueField:"schId",isMultiSelect: false,
    	onSelected: function (newvalue)
        {
    		
    		document.getElementById("schCfg.parent").value = newvalue;
    		
        }
    
    });
  //  alert(document.getElementById("schCfg.typId").value);
  //  $("#typId").selectValue(1);
  
    $.ligerui.get("typId").selectValue(document.getElementById("schCfg.typId").value);
    $.ligerui.get("parent").selectValue(document.getElementById("schCfg.parent").value);
});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">


    <input type="hidden" id="schCfg.parent" name="schCfg.parent" size="30" value="<c:out value="${schCfg.parent}"/>" />
    <input type="hidden" id="schCfg.typId" name="schCfg.typId" size="30" value="<c:out value="${schCfg.typId}"/>" />
	

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">进度名称：</td><td><input type="text" id="schNm" name="schCfg.schNm"/></td>
			<td height="24" width="90" align="center">进度编号：</td><td><input type="text" id="schNo" name="schCfg.schNo"/></td>
			<td height="24" width="90" align="center">项目分类：</td>
			<td>
			  <input type="text" id="typId" name="schCfg.typId" size="30" value="<c:out value="${schCfg.typId}"/>"/>
			</td>
			
			<td height="24" width="90" align="center">父级进度：</td>
			   <td>
			   <input type="text" id="parent" name="schCfg.parent" size="30" value="<c:out value="${schCfg.parent}"/>"/>
			</td>
		</tr><tr>
			  
			<td height="24" width="90" align="center">状态：</td>
			  <td>
			   <select id="status" name="schCfg.status" style="width: 196px">
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
