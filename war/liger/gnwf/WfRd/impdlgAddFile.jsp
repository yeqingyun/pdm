<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<style>
	.lef {
		float: left;margin-right:10; 
	}
	.rig {
		float: right;margin-right:12; 
	}
</style>
<script type="text/javascript">
<!--
function impxls() {
	if(confirm("您确定上传附件吗？")) {
		var uri = document.getElementById("actUri").value;
		var wfno = document.getElementById("wfRd.wfNo").value;
		
		var form = document.getElementById("imp-dlg");
		form.action=uri+"!addFiles.shtml?wfRd.wfNo="+wfno;
		form.submit();
		document.getElementById("impbtn").disabled = true;//this.setAttribute('disabled',true)
	}
}
function openAddFiledlg() {
	document.getElementById("dlg").style.display=""; 
}
function closeAddFiledlg() {
	document.getElementById("dlg").style.display="none"; 
}
//-->
</script>

<div id="dlg" style="position:absolute;left:0px;top:0px;width:100%;height:100%;background:#dcdcdc;align:center;zIndex:100;filter:alpha(opacity=90);opacity:0.9;display:none;">

<iframe style="width:100%; height:0;filter:alpha(opacity=0);opacity:0;" border=0 frameborder="0"></iframe>

<form id="imp-dlg" name="imp-dlg" action="" enctype="multipart/form-data" method="post">

<table align="center" width="50%" cellpadding="4" cellspacing="1" bgcolor="#D3E1F1">
	<tr>
		<td>
			<font color="#b22222" class="lef">
				<b>&nbsp;&nbsp;&nbsp;添加附件对话框</b>
			</font>
			<font color="#0000ff" class="rig">
				<a href="javascript:closeAddFiledlg();" style="cursor:hand;">
					<b>X&nbsp;&nbsp;&nbsp;</b>
				</a>
			</font>
		</td>
	</tr>
	<tr bgcolor="#f5f5f5">
		<td>
			
			<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="730" align=center>
			 	<tr class=TableHeader>
			    	<td colspan="6">公共附件</td>
			    </tr>
				<tr>
					<td colspan="6" >
						<table border="0" width="100%">
							<tr>
							<td width="100">
							<c:if test="${!empty docCates}">
								<input id="checkDoc" type="checkbox" onclick="selectDoc();">是否最终附件:
							</c:if>
							</td>
							<td align="left">
								<table border="0" width="100%" id="docTable" style="display: none;">
									<tr>
									<td>
										<c:forEach items="${docCates}" var="cate" varStatus="n">
											<input type="radio" name="docCate"  <c:if test="${n.count==1}">checked</c:if>
												value="<c:out value="${cate.docId}"/>" />
											<c:out value="${cate.docName}"/>
											<input type="hidden" id="cate<c:out value="${cate.docId}"/>" value="<c:out value="${cate.docName}"/>"/>
										</c:forEach>
									</td>
									</tr>
								</table>
							</td>
							<td align="right">
								<a href="javascript:addFile();">添加附件&nbsp;&nbsp;</a>&nbsp;
								<input type="button" name="impbtn"  id="impbtn"  value="导入" onclick="impxls();">
							</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="20%" align="center"><font color="red">附件</font></td>
					<td colspan="5" width="80%">
						<table id="attachtab" border="0" cellpadding="0" cellspacing="0">
						</table>
					</td>
				</tr>
			</table>
			
		</td>
	</tr>
</table>

</form>

</div>
