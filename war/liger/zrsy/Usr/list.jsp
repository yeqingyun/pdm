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

<script src="./include/js/zrsy/usr.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Usr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#comVal").ligerComboBox({
        //width : 180, 
        selectBoxWidth: 200,
        selectBoxHeight: 200, textField:'text', valueField:'id', treeLeafOnly: false,
        url: './Com!loadComs.shtml',
        onSelected: function (newvalue)
        {
       	 var id=newvalue;
	       	/* $.post("./Com!loadTree.shtml",
					{'com.comId':id},
					function(data) {
						//alert(data);
			            $("#deptVal").ligerComboBox().setData(data);
					},
					"json"
				); */
			$("#comId").val(id);
        }

	 });
	$("#deptVal").ligerComboBox({  
		//width : 180, 
        selectBoxWidth: 200,
        selectBoxHeight: 200, textField:'text', valueField:'id', treeLeafOnly: false,
        url: './Com!loadDept.shtml',
        onSelected: function (newtext)
        {
			$("#orgNo").val(newtext);
        }
	});
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'Id', name: 'id', align: 'left', width: 120 ,hide: 1},
			{ display: '公司', name: 'comNm', align: 'left', width: 120 },
			{ display: '部门', name: 'deptNm', align: 'left', width: 120 },
			{ display: '用户号', name: 'usrNo', align: 'left', width: 120 },
			{ display: '登录账号', name: 'login', align: 'left', width: 120 },
			{ display: '密码', name: 'pwd', align: 'left', width: 120 ,hide: 1},
			{ display: '用户名称', name: 'usrName', align: 'left', width: 120 },
			{ display: '电子邮箱', name: 'email', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }
		},
			{ display: '备注', name: 'remark', align: 'left', width: 120 ,hide: 1},
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 ,hide: 1},
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 ,hide: 1},
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 }
			

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Usr!list.shtml',
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
	if ($("#id").length > 0)
		$("#id").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#login").length > 0)
		$("#login").ligerTextBox();
	if ($("#usrNo").length > 0)
		$("#usrNo").ligerTextBox();
	if ($("#usrName").length > 0)
		$("#usrName").ligerTextBox();
	if ($("#email").length > 0)
		$("#email").ligerTextBox();
});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">公司：</td>
			<td>
			<input type="text"  id="comVal" onclick=""/>
  			<input type="hidden"  id="comId" name="usr.comId"/>
				<!--  <select id="comId" name="usr.comId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select>
				-->
			</td>
			<td height="24" width="90" align="center">部门：</td>
			<td>
				  <!-- <input type="text" id="deptVal"/>
				<input type="hidden" id="orgNo" name="usr.orgNo"/>   -->
				  <select id="orgNo" name="usr.orgNo" style="width:135px">
					<option value="">请选择</option>
					 <c:forEach items="${depts}" var="dept">
						<option value="<c:out value="${dept.deptId}"/>"><c:out value="${dept.deptNm}"/></option>
					</c:forEach> 
					<option value="50100003">金立品牌渠道部</option>
					<option value="50100021">结构部</option>
					<option value="50100022">项目部</option>
					<option value="50015235">硬件部</option>
					<option value="50017947">测试部</option>
					<option value="50020338">品质部</option>
					<option value="50026502">射频部</option>
					
					
					
					
					 <option value="10100014">关键物料采购中心</option>
					<option value="10100023"> 研发一部</option>
					<option value="10100024">项目管理部(研一) </option>
					<option value="10100025">ID部(研一) </option>
					<option value="10100026"> 硬件部(研一)</option>
					<option value="10100027">结构部(研一) </option>
					<option value="10100028"> 测试部(研一)</option>
					<option value="10100029"> 研发二部</option>
					<option value="10100030">项目管理部（研二） </option>
					<option value="10100031">ID部（研二） </option>
					<option value="10100032"> 硬件部（研二）</option>
					<option value="10100033"> 结构部（研二）</option>
					<option value="10100036">测试部（研二） </option>
					<option value="10100037">研发三部 </option>
					<option value="10100038">项目管理部（研三） </option>
					<option value="10100039"> ID部（研三）</option>
					<option value="10100040">硬件部（研三） </option>
					<option value="10100041">结构部（研三） </option>
					<option value="10100042">软件部（研三） </option>
					<option value="10100043"> 结构堆叠部（研三）</option>
					<option value="10100044"> 测试部（研三）</option>
					<option value="10100049">材料认证部</option>
					<option value="10100055"> 项目组</option>
					<option value="20100032"> 采购中心采购部</option>
					<option value="20100033">采购部后勤采购科 </option>
					<option value="20100034"> 采购部电子科</option>
					<option value="20100035">采购部业务科</option>
					<option value="20100036">采购部业务科</option>
					
					<option value="88100001">海外销售管理部</option>
					<option value="88100002">海外ID一部</option>
					<option value="88100003">海外结构部</option>
					<option value="88100004">海外项目管理部</option>
					<option value="88100005">海外硬件部</option>
					<option value="88100006">海外软件部</option>
					<option value="88100007">海外测试部</option>
					<option value="88100008">海外商务采购部</option>
					<option value="88100009">海外人事行政部</option>
					<option value="88100010">海外产品规划部</option>
					<option value="88100011">海外市场部</option>
					<option value="88100012">海外销售管理部销售一区</option>
					<option value="88100013">海外销售管理部销售二区</option>
					<option value="88100014">海外销售管理部销售三区</option>
					<option value="88100015">海外销售管理部销售四区</option>
					<option value="88100016">海外销售管理部销售五区</option>
					<option value="88100017">海外销售管理部销售六区</option>
					<option value="88100018">海外ID一部ID组</option>
					<option value="88100019">海外ID一部CMF组</option>
					<option value="88100020">海外结构部结构一组</option>
					<option value="88100021">海外结构部结构二组</option>
					<option value="88100022">海外项目管理部项目经理组</option>
					<option value="88100023">海外项目管理部客户经理组</option>
					<option value="88100024">海外项目管理部NPI组</option>
					<option value="88100025">海外硬件部PCB组</option>
					<option value="88100026">海外硬件部音频组</option>
					<option value="88100027">海外硬件部基带组</option>
					<option value="88100028">海外硬件部射频组</option>
					<option value="88100029">海外软件部软件项目一组</option>
					<option value="88100030">海外软件部软件项目二组</option>
					<option value="88100031"> 海外软件部软件项目三组</option>
					<option value="88100032">海外软件部软件项目四组 </option>
					<option value="88100033">海外软件部软件项目五组 </option>
					<option value="88100034">海外软件部智能平台组 </option>
					<option value="88100035"> 海外软件部智能应用组</option>
					<option value="88100036">海外软件部MTK平台组 </option>
					<option value="88100037">海外测试部测试组 </option>
					<option value="88100038"> 海外测试部软测一组</option>
					<option value="88100039">海外测试部软测二组 </option>
					<option value="88100040"> 海外测试部软测三组</option>
					<option value="88100041">海外测试部软测四组 </option>
					<option value="88100042"> 海外测试部自动化测试组</option>
					<option value="88100043">海外测试部硬件测试组 </option>
					<option value="88100044"> 海外测试部质量组</option>
					<option value="88100045"> 海外测试部售后组</option>
					<option value="88100046"> 海外测试部文控体系组</option>
					<option value="88100047"> 海外测试部VAS组</option>
					<option value="88100048">海外商务采购部商务组 </option>
					<option value="88100049">海外商务采购部采购组 </option>
					<option value="88100050">海外人事行政部后勤组 </option>
					<option value="88100051"> 海外人事行政部人事组</option>
					<option value="88100052">海外人事行政部行政组 </option>
					<option value="88100053">海外市场部品牌组 </option>
					<option value="88100054">海外市场部产品市场组 </option>
					<option value="88100055"> 海外市场部设计组</option>
					
				</select>  
				
			</td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="usr.status" style="width:135px">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="usr.startCreateDate"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">用户号：</td><td><input type="text" id="usrNo" name="usr.usrNo"/></td>
			<td height="24" width="90" align="center">用户名称：</td><td><input type="text" id="usrName" name="usr.usrName"/></td>
			<td height="24" width="90" align="center">登录账号：</td><td><input type="text" id="login" name="usr.login"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="usr.endCreateDate"/></td>
		</tr>
	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
