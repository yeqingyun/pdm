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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfCfg.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#comId").length > 0)
		$("#comId").ligerComboBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerComboBox();
	if ($("#cateId").length > 0)
		$("#cateId").ligerComboBox();
	if ($("#sphere").length > 0)
		$("#sphere").ligerTextBox();
	if ($("#relateId").length > 0)
		$("#relateId").ligerTextBox();
	if ($("#limit").length > 0)
		$("#limit").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#hasQuesMoudle").length > 0)
		$("#hasQuesMoudle").ligerTextBox();
	if ($("#flowCode").length > 0)
		$("#flowCode").ligerTextBox();
	if ($("#flowName").length > 0)
		$("#flowName").ligerTextBox();
	if ($("#flowUri").length > 0)
		$("#flowUri").ligerTextBox();
	if ($("#printUri").length > 0)
		$("#printUri").ligerTextBox();
	if ($("#specialClass").length > 0)
		$("#specialClass").ligerTextBox();
	if ($("#addRdExtendUri").length > 0)
		$("#addRdExtendUri").ligerTextBox();
	if ($("#flowDesc").length > 0)
		$("#flowDesc").ligerTextBox();

	$("#companyId").ligerComboBox({data:null,isMultiSelect: false,
		onSelected: function (newvalue)
        {
			$.post("Dept!selectDept.shtml",
					{'dept.comId':newvalue},
					function(data) {
			            $("#dId").ligerGetComboBoxManager().setData(data);
					},
					"json"
			);
        }
    });
	
    $("#dId").ligerComboBox({data: null, textField:"deptNm", valueField:"deptId",isMultiSelect: false,
    	onSelected: function (newvalue)
        {
			$.post("Usr!selectUser.shtml",
					{'usr.deptId':newvalue},
					function(data) {
			            $("#acceptBy").ligerGetComboBoxManager().setData(data);
					},
					"json"
			);
        }
    });
    
    $("#acceptBy").ligerComboBox({data: null, textField:"usrName",valueField:"id"});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="flowId" name="wfCfg.flowId" size="30" value="<c:out value="${wfCfg.flowId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="120" >工作流编码：</td>
			<td><input type="text" id="flowCode" name="wfCfg.flowCode" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowCode}"/>"/></td>
			<td height="24" width="120" >工作程名称：</td>
			<td><input type="text" id="flowName" name="wfCfg.flowName" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowName}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" >公司ID：</td>
			<td>
				<select id="comId" name="wfCfg.comId" onchange="">
					<c:forEach items="${companyList}" var="baCom">
						<option value="<c:out value="${baCom.comId}"/>" <c:if test="${baCom.comId==wfCfg.comId}">selected</c:if>><c:out value="${baCom.comNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" >部门ID：</td>
			<td>
				<select id="deptId" name="wfCfg.deptId">
					<c:forEach items="${deptList}" var="baDept">
						<option value="<c:out value="${baDept.deptId}"/>" <c:if test="${wfCfg.deptId==baDept.deptId}">selected</c:if>><c:out value="${baDept.deptNm}"/></option>
					</c:forEach>
				</select>
			</td>
			</tr>
		<tr>
			<td height="24" width="90" >流程分类：</td>
			<td>
				<select id="cateId" name="wfCfg.cateId">
					<c:forEach items="${cateList}" var="cate">
						<option value="<c:out value="${cate.cateId}"/>" <c:if test="${wfCfg.cateId==cate.cateId}">selected</c:if>><c:out value="${cate.cateName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" >状态：</td>
			<td>
				<select id="status" name="wfCfg.status" style="width: 200px;">
					<option value="1" <c:if test="${wfCfg.status==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${wfCfg.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" >适用范围：</td>
			<td>
				<select id="sphere" name="wfCfg.sphere">
					<option value="0" <c:if test="${wfCfg.sphere==0}">selected</c:if>>看所有人流程</option>
					<option value="1" <c:if test="${wfCfg.sphere==1}">selected</c:if>>仅看本人流程</option>
				</select>
			</td>
			<td height="24" width="90" >权限：</td>
			<td>
				<select id="limit" name="wfCfg.limit">
					<option value="3" <c:if test="${wfCfg.limit==3}">selected</c:if>>全可更新</option>
					<option value="2" <c:if test="${wfCfg.limit==2}">selected</c:if>>全可下载</option>
					<option value="1" <c:if test="${wfCfg.limit==1}">selected</c:if>>全部可看</option>
					<option value="0" <c:if test="${wfCfg.limit==0}">selected</c:if>>按岗位设置</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" >打印URI配置：</td>
			<td><input type="text" id="printUri" name="wfCfg.printUri" size="30" value="<c:out value="${wfCfg.printUri}"/>"/></td>
			<td height="24" width="90" >特殊流程Class：</td>
			<td><input type="text" id="specialClass" name="wfCfg.specialClass" size="30" value="<c:out value="${wfCfg.specialClass}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" >新增界面URI：</td>
			<td><input type="text" id="addRdExtendUri" name="wfCfg.addRdExtendUri" size="30" value="<c:out value="${wfCfg.addRdExtendUri}"/>"/></td>
			<td height="24" width="90" >工作流URI配置：</td>
			<td><input type="text" id="flowUri" name="wfCfg.flowUri" size="30" value="<c:out value="${wfCfg.flowUri}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" >是否有问题模块：</td>
			<td>
				<select id="hasQuesMoudle" name="wfCfg.hasQuesMoudle">
					<option value="0" <c:if test="${wfCfg.hasQuesMoudle==0}">selected</c:if>>无</option>
					<option value="1" <c:if test="${wfCfg.hasQuesMoudle==1}">selected</c:if>>有</option>
				</select>
			</td>
			<td height="24" width="90" >工作流描述：</td>
			<td><input type="text" id="flowDesc" name="wfCfg.flowDesc" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowDesc}"/>"/></td>
		</tr>
		
<!-- 
<td height="24" width="90" >null：</td><td><input type="text" id="relateId" name="wfCfg.relateId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.relateId}"/>"/></td>
<td height="24" width="90" >null：</td><td><input type="text" id="scheCfgId" name="wfCfg.scheCfgId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.scheCfgId}"/>"/></td>
-->
			
	<tr>
		<td>相关部门</td>
		<td colspan="3">
			<c:set var="n" value="1"/>
			<table width="100%">
				<c:forEach items="${companyList}" var="company">
				
				<c:if test="${!empty company.depts}">
				<tr><td colspan="5" bgcolor="#d3d3d3"><c:out value="${company.comNm}"/></td></tr>
				</c:if>
				
				<c:set var="j" value="0"/>
				<c:forEach items="${company.depts}" var="dept">
					<c:if test="${j%5==0}"><tr></c:if>
					<c:if test="${dept.deptId!=wfDept.deptId}">
						<td>
							<input type="Checkbox" name="wfDepts"
								value="<c:out value="${dept.deptId}"/>"  <c:out value="${dept.ischecked}"/>>
								<c:out value="${dept.deptNm}"/>
						</td>
						<c:set var="n" value="${n+1}"/>
						<c:set var="j" value="${j+1}"/>
					</c:if>
					<c:if test="${j%5==0}"></tr></c:if>
				</c:forEach>
			</c:forEach>
				</tr>
			</table>
		</td>
	</tr>
	

	<tr>
		<td>相关工作流</td>
		<td colspan="3">
			<table width="100%">
				<c:set var="ii" value="0"/>
				<c:forEach items="${workFlows}" var="workFlow">
					<c:if test="${ii%4==0}"><tr></c:if>
					<td width="10%" height="24"><input type="checkbox" name="wfRelates" <c:out value="${workFlow.checked}"/> 
						value="<c:out value="${workFlow.flowId}"/>"/><c:out value="${workFlow.flowName}"/></td>
					<c:set var="ii" value="${ii+1}"/>
				</c:forEach>
				</tr>
			</table>
		</td>
	</tr>

	<tr>
		<td>主导人</td>
		<td colspan="3">
			<table width="100%">
			<tr>
				<td>
					<select id="companyId" name="aa">
						<option value="-1">请选择</option>
						<c:forEach items="${companyList}" var="com">
							<option value="<c:out value="${com.comId}"/>"><c:out value="${com.comNm}"/></option>
						</c:forEach>
					</select>
					<select id="dId" name="dId"><option value="-1">请选择</option></select>
					<select id="acceptBy"><option value="-1">请选择</option></select>
					<input type="button" class="btn" value="添加" onclick="addleader()">
				</td>
			</tr>
			<tr>
				<td colspan="3" bgcolor="#ffffff">
					<c:set var="countI" value="0"/>
					<table id="leadertable" width="80%" cellpadding="4" cellspacing="1" bgcolor="#D3E1F1">
						<tr bgcolor="#EFF0F2">
							<td>主导人</td>
							<td width="30%">操作</td>
						</tr>
							<c:forEach items="${wfLeaders}" var="wfleader">
								<tr id="idRow<c:out value='${countI}'/>" bgcolor="#FFFFFF"  onmouseout="this.style.backgroundColor='#FFFFFF';">
									<td>
										<input type="hidden" name="wfLeaders" value="<c:out value='${wfleader.userId}'/>"> 
										<c:out value="${wfleader.userName}"/>
									</td>
									<td>
										<a href="javascript:;" onclick="delwfleader('leadertable','idRow<c:out value='${countI}'/>')"><font color="red">删除</font></a>
									</td>
								</tr>
								<c:set var="countI" value="${countI+1}"/>
							</c:forEach>
					</table>
				</td>
			</tr>
			</table>
		</td>
	</tr>
	
	</table>
</form>

</div>

</body>
</html>