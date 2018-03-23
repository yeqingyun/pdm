<%@page contentType="text/html;charset=utf-8"%>
<style>
	.lef {
		float: left;margin-right:10; 
	}
	.rig {
		float: right;margin-right:12; 
	}
</style>
<script type="text/javascript">
//<!--
function impxls() {
	if(confirm("您确定要导入记录？")) {
		var uri = document.getElementById("actUri").value;
		//alert(uri);
		var wfno = document.getElementById("wfRd.wfNo").value;
		var taskstepId = document.getElementById("taskStepId").value;
		var currenttaskId = document.getElementById("currentTaskId").value;
		//alert(taskstepId);
		//alert(currenttaskId);
		//alert(wfno);
		var form = document.getElementById("imp-dlg");
		form.action=uri+"!imp1.shtml?wfRd.wfNo="+wfno+"&currentTaskId="+currenttaskId+"&taskStepId="+taskstepId;
		form.submit();
		document.getElementById("impbtn").disabled = true;//this.setAttribute('disabled',true)
	}
}
function opendlg() {
	document.getElementById("dlg").style.display=""; 
}
function closedlg() {
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
				<b>&nbsp;&nbsp;&nbsp;导入对话框</b>
			</font>
			<font color="#0000ff" class="rig">
				<a href="javascript:closedlg();" style="cursor:hand;">
					<b>X&nbsp;&nbsp;&nbsp;</b>
				</a>
			</font>
		</td>
	</tr>
	<tr bgcolor="#f5f5f5">
		<td height="120" align="center">
			<input type="file" name="impfile" value="" size="50"/>
			<input type="button" name="impbtn"  id="impbtn"  value="导入" onclick="impxls();">
			<br><br>
			<font color="#ff4500">请选择需要导入的电子表格(*.xls)</font>
			<br><br>
		</td>
	</tr>
</table>

</form>

</div>
