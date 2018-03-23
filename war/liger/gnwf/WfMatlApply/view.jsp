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

<script src="./include/js/gnwf/wfMatlApply.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#matlId").length > 0)
		$("#matlId").ligerTextBox();
	if ($("#nORMT").length > 0)
		$("#nORMT").ligerTextBox();
	if ($("#fERTH").length > 0)
		$("#fERTH").ligerTextBox();
	if ($("#eXTWG").length > 0)
		$("#eXTWG").ligerTextBox();
	if ($("#vOLUM").length > 0)
		$("#vOLUM").ligerTextBox();
	if ($("#vPRSV").length > 0)
		$("#vPRSV").ligerTextBox();
	if ($("#mATNR").length > 0)
		$("#mATNR").ligerTextBox();
	if ($("#bSTRF").length > 0)
		$("#bSTRF").ligerTextBox();
	if ($("#wERKS").length > 0)
		$("#wERKS").ligerTextBox();
	if ($("#nTGEW").length > 0)
		$("#nTGEW").ligerTextBox();
	if ($("#gEWEI").length > 0)
		$("#gEWEI").ligerTextBox();
	if ($("#mATKL").length > 0)
		$("#mATKL").ligerTextBox();
	if ($("#bSTUZ").length > 0)
		$("#bSTUZ").ligerTextBox();
	if ($("#mEINS").length > 0)
		$("#mEINS").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	if ($("#wRKST").length > 0)
		$("#wRKST").ligerTextBox();
	if ($("#dISLS").length > 0)
		$("#dISLS").ligerTextBox();
	if ($("#kAUSF").length > 0)
		$("#kAUSF").ligerTextBox();
	if ($("#gROES").length > 0)
		$("#gROES").ligerTextBox();
	if ($("#dISPO").length > 0)
		$("#dISPO").ligerTextBox();
	if ($("#mTART").length > 0)
		$("#mTART").ligerTextBox();
	if ($("#bSTME").length > 0)
		$("#bSTME").ligerTextBox();
	if ($("#bKLAS").length > 0)
		$("#bKLAS").ligerTextBox();
	if ($("#bSTUN").length > 0)
		$("#bSTUN").ligerTextBox();
	if ($("#mAKTX").length > 0)
		$("#mAKTX").ligerTextBox();
	if ($("#bSTMI").length > 0)
		$("#bSTMI").ligerTextBox();
	if ($("#sTPRS").length > 0)
		$("#sTPRS").ligerTextBox();
	if ($("#dISMM").length > 0)
		$("#dISMM").ligerTextBox();
	if ($("#mMSTA").length > 0)
		$("#mMSTA").ligerTextBox();
	if ($("#pLIFZ").length > 0)
		$("#pLIFZ").ligerTextBox();
	if ($("#bSTMA").length > 0)
		$("#bSTMA").ligerTextBox();
	if ($("#sOBSL").length > 0)
		$("#sOBSL").ligerTextBox();
	if ($("#sTAWN").length > 0)
		$("#sTAWN").ligerTextBox();
	if ($("#bRGEW").length > 0)
		$("#bRGEW").ligerTextBox();
	if ($("#lGFSB").length > 0)
		$("#lGFSB").ligerTextBox();
	if ($("#bESKZ").length > 0)
		$("#bESKZ").ligerTextBox();
	if ($("#vOLEH").length > 0)
		$("#vOLEH").ligerTextBox();
	if ($("#infoId").length > 0)
		$("#infoId").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="matlId" name="wfMatlApply.matlId" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.matlId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="nORMT" name="wfMatlApply.nORMT" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.nORMT}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fERTH" name="wfMatlApply.fERTH" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.fERTH}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="eXTWG" name="wfMatlApply.eXTWG" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.eXTWG}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="vOLUM" name="wfMatlApply.vOLUM" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.vOLUM}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="vPRSV" name="wfMatlApply.vPRSV" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.vPRSV}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mATNR" name="wfMatlApply.mATNR" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.mATNR}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTRF" name="wfMatlApply.bSTRF" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bSTRF}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="wERKS" name="wfMatlApply.wERKS" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.wERKS}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="nTGEW" name="wfMatlApply.nTGEW" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.nTGEW}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="gEWEI" name="wfMatlApply.gEWEI" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.gEWEI}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mATKL" name="wfMatlApply.mATKL" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.mATKL}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTUZ" name="wfMatlApply.bSTUZ" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bSTUZ}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mEINS" name="wfMatlApply.mEINS" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.mEINS}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="remark" name="wfMatlApply.remark" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.remark}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="wRKST" name="wfMatlApply.wRKST" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.wRKST}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="dISLS" name="wfMatlApply.dISLS" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.dISLS}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="kAUSF" name="wfMatlApply.kAUSF" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.kAUSF}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="gROES" name="wfMatlApply.gROES" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.gROES}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="dISPO" name="wfMatlApply.dISPO" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.dISPO}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mTART" name="wfMatlApply.mTART" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.mTART}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTME" name="wfMatlApply.bSTME" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bSTME}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bKLAS" name="wfMatlApply.bKLAS" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bKLAS}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTUN" name="wfMatlApply.bSTUN" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bSTUN}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mAKTX" name="wfMatlApply.mAKTX" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.mAKTX}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTMI" name="wfMatlApply.bSTMI" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bSTMI}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="sTPRS" name="wfMatlApply.sTPRS" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.sTPRS}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="dISMM" name="wfMatlApply.dISMM" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.dISMM}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mMSTA" name="wfMatlApply.mMSTA" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.mMSTA}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="pLIFZ" name="wfMatlApply.pLIFZ" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.pLIFZ}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTMA" name="wfMatlApply.bSTMA" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bSTMA}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="sOBSL" name="wfMatlApply.sOBSL" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.sOBSL}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="sTAWN" name="wfMatlApply.sTAWN" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.sTAWN}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bRGEW" name="wfMatlApply.bRGEW" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bRGEW}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="lGFSB" name="wfMatlApply.lGFSB" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.lGFSB}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bESKZ" name="wfMatlApply.bESKZ" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.bESKZ}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="vOLEH" name="wfMatlApply.vOLEH" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.vOLEH}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="infoId" name="wfMatlApply.infoId" size="30" validate="{required:true}" value="<c:out value="${wfMatlApply.infoId}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>