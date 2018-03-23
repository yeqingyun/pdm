<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.red{color:#f00;}
b{font-size:12px;color:#f00;}
.secLevel b{color:blue;}
.secLevel span b{color:purple;}
.secTable{display:none;}
.lasTable{display:none;}
</style>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrsy/sco.js" type="text/javascript"></script> 
<script type="text/javascript">

$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
});
</script>
<script type="text/javascript">
$(function(){
	var lasInput = $(".firTable").find("span").find("input[checked]");  //最内层级
	var secInput = $(".firTable").find(".secLevel").find("input[checked]")  //第二层级
	var firInput = $(".firTable").find("firLevel").find("input[checked]") //第一层级
	var inP = $(".firTable").find("p");
	
	if(lasInput.length!=0){
		lasInput.parents(".lasTable").siblings(".secLevel").find("input").attr("checked","checked");
		lasInput.parents(".secTable").siblings(".firLevel").find("input").attr("checked","checked");
		lasInput.parents(".secTable").show();  //选中的一级显示
		lasInput.parents(".lasTable").show();  //选中的二级显示
	}
	
	if(secInput.length!=0){
		secInput.parents(".secTable").siblings(".firLevel").find("input").attr("checked","checked");
		secInput.parents(".secTable").show();
	}
	
	$(".firLevel").find("input").click(function(){
		$(this).parents(".firLevel").siblings(".secTable").toggle();
	})
	
	$(".secLevel").find("input").click(function(){
		var nextT = $(this).parents(".secLevel").siblings(".lasTable");
		if(nextT.is(":hidden")){
			nextT.show();
			$(this).parents(".secLevel").siblings(".lasTable").find("input").attr("checked","checked");
		}else{
			nextT.hide();
		}
	})
	check();
});
</script>

</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="scoId" name="sco.scoId" size="30"  value="<c:out value="${sco.scoId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">类型：</td><td><input type="text" id="scope" name="sco.scope" size="30" value="<c:out value="${sco.scope}"/>" validate="{required:true}"/></td><td align="left"></td>
		</tr><tr>
		<td height="24" width="90" align="center">范围名称：</td><td><input type="text" id="scoNm" name="sco.scoNm" size="30" value="<c:out value="${sco.scoNm}"/>" ltype="text" validate="{required:true}"/></td><td align="left"></td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="sco.status" style="width:135px">
					<option value="1"<c:if test="${sco.status==1}">selected</c:if>>有效</option>
					<option value="0"<c:if test="${sco.status==0}">selected</c:if>>无效</option>
				</select></td><td align="left"></td>
		</tr>
		<tr>
			<td colspan="5">
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr><td class="firTable">
						<div class="firLevel">
							<input type="checkbox" id="com0" name="_comss"  value="0"/>
						</div>
					</td></tr>
					<c:set var="i" value="0"/>
					<c:forEach items="${coms}" var="com">
						<tr>
							<td width="10%" height="24" class="firTable">
								<div class="firLevel"><p><input type="checkbox" id="com<c:out value="${i}"/>" name="_coms" <c:out value="${com.checked}"/> value="<c:out value="${com.comId}"/>"/><c:out value="${com.comNm}"/></p></div>
								<table align="center" width="100%" cellpadding="4" cellspacing="4" class="secTable">
									<c:set var="ii" value="0"/>
									<c:forEach items="${com.depts}" var="dept">
									<tr>
										<td>
										<div class="secLevel"><p>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="dept<c:out value="${ii}"/>" name="_depts<c:out value="${i}"/>" <c:out value="${dept.checked}"/> value="<c:out value="${dept.deptId}"/>"/><c:out value="${dept.deptNm}"/></p></div>
												<div class="lasTable">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:set var="iii" value="0"/>
												<c:forEach items="${dept.usrs}" var="usr">
														<span><input type="checkbox" id="usr<c:out value="${iii}"/>" name="_usrs<c:out value="${ii}"/>" <c:out value="${usr.checked}"/> value="<c:out value="${usr.id}"/>"/><c:out value="${usr.usrName}"/></span>
												<c:set var="iii" value="${iii+1}"/>
												</c:forEach>
												</div>
										</td>
									</tr>
									<c:set var="ii" value="${ii+1}"/>
									</c:forEach>
								</table>
							</td>
						</tr>
					<c:set var="i" value="${i+1}"/>
					</c:forEach>
				</table>
			</td>
		</tr>
</table>
</form>

</div>

</body>
</html>