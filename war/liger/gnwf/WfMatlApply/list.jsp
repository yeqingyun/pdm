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
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfMatlApply'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'null', name: 'matlId', align: 'left', width: 120 },
			{ display: 'null', name: 'nORMT', align: 'left', width: 120 },
			{ display: 'null', name: 'fERTH', align: 'left', width: 120 },
			{ display: 'null', name: 'eXTWG', align: 'left', width: 120 },
			{ display: 'null', name: 'vOLUM', align: 'left', width: 120 },
			{ display: 'null', name: 'vPRSV', align: 'left', width: 120 },
			{ display: 'null', name: 'mATNR', align: 'left', width: 120 },
			{ display: 'null', name: 'bSTRF', align: 'left', width: 120 },
			{ display: 'null', name: 'wERKS', align: 'left', width: 120 },
			{ display: 'null', name: 'nTGEW', align: 'left', width: 120 },
			{ display: 'null', name: 'gEWEI', align: 'left', width: 120 },
			{ display: 'null', name: 'mATKL', align: 'left', width: 120 },
			{ display: 'null', name: 'bSTUZ', align: 'left', width: 120 },
			{ display: 'null', name: 'mEINS', align: 'left', width: 120 },
			{ display: 'null', name: 'remark', align: 'left', width: 120 },
			{ display: 'null', name: 'wRKST', align: 'left', width: 120 },
			{ display: 'null', name: 'dISLS', align: 'left', width: 120 },
			{ display: 'null', name: 'kAUSF', align: 'left', width: 120 },
			{ display: 'null', name: 'gROES', align: 'left', width: 120 },
			{ display: 'null', name: 'dISPO', align: 'left', width: 120 },
			{ display: 'null', name: 'mTART', align: 'left', width: 120 },
			{ display: 'null', name: 'bSTME', align: 'left', width: 120 },
			{ display: 'null', name: 'bKLAS', align: 'left', width: 120 },
			{ display: 'null', name: 'bSTUN', align: 'left', width: 120 },
			{ display: 'null', name: 'mAKTX', align: 'left', width: 120 },
			{ display: 'null', name: 'bSTMI', align: 'left', width: 120 },
			{ display: 'null', name: 'sTPRS', align: 'left', width: 120 },
			{ display: 'null', name: 'dISMM', align: 'left', width: 120 },
			{ display: 'null', name: 'mMSTA', align: 'left', width: 120 },
			{ display: 'null', name: 'pLIFZ', align: 'left', width: 120 },
			{ display: 'null', name: 'bSTMA', align: 'left', width: 120 },
			{ display: 'null', name: 'sOBSL', align: 'left', width: 120 },
			{ display: 'null', name: 'sTAWN', align: 'left', width: 120 },
			{ display: 'null', name: 'bRGEW', align: 'left', width: 120 },
			{ display: 'null', name: 'lGFSB', align: 'left', width: 120 },
			{ display: 'null', name: 'bESKZ', align: 'left', width: 120 },
			{ display: 'null', name: 'vOLEH', align: 'left', width: 120 },
			{ display: 'null', name: 'infoId', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfMatlApply!list.shtml',
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

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="matlId" name="wfMatlApply.matlId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="nORMT" name="wfMatlApply.nORMT"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fERTH" name="wfMatlApply.fERTH"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="eXTWG" name="wfMatlApply.eXTWG"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="vOLUM" name="wfMatlApply.vOLUM"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="vPRSV" name="wfMatlApply.vPRSV"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mATNR" name="wfMatlApply.mATNR"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTRF" name="wfMatlApply.bSTRF"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="wERKS" name="wfMatlApply.wERKS"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="nTGEW" name="wfMatlApply.nTGEW"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="gEWEI" name="wfMatlApply.gEWEI"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mATKL" name="wfMatlApply.mATKL"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTUZ" name="wfMatlApply.bSTUZ"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mEINS" name="wfMatlApply.mEINS"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="remark" name="wfMatlApply.remark"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="wRKST" name="wfMatlApply.wRKST"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="dISLS" name="wfMatlApply.dISLS"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="kAUSF" name="wfMatlApply.kAUSF"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="gROES" name="wfMatlApply.gROES"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="dISPO" name="wfMatlApply.dISPO"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mTART" name="wfMatlApply.mTART"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTME" name="wfMatlApply.bSTME"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bKLAS" name="wfMatlApply.bKLAS"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTUN" name="wfMatlApply.bSTUN"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mAKTX" name="wfMatlApply.mAKTX"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTMI" name="wfMatlApply.bSTMI"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="sTPRS" name="wfMatlApply.sTPRS"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="dISMM" name="wfMatlApply.dISMM"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="mMSTA" name="wfMatlApply.mMSTA"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="pLIFZ" name="wfMatlApply.pLIFZ"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bSTMA" name="wfMatlApply.bSTMA"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="sOBSL" name="wfMatlApply.sOBSL"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="sTAWN" name="wfMatlApply.sTAWN"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bRGEW" name="wfMatlApply.bRGEW"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="lGFSB" name="wfMatlApply.lGFSB"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="bESKZ" name="wfMatlApply.bESKZ"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="vOLEH" name="wfMatlApply.vOLEH"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="infoId" name="wfMatlApply.infoId"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>