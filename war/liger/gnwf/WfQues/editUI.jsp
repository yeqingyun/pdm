<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
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
<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>
<script language="JavaScript" src="./include/js/common.js"></script>
<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<style type="text/css">
.innerTable th,.innerTable td {
	border-right: 1px solid #9FC2E5;
	border-bottom: 1px solid #9FC2E5;
	background: #fff;
	text-align: left;
	padding: 0 7px;
	
}
.innerTable th{
font-size: 14px;
font-weight: bold;
}
</style>
<script type="text/javascript">

var i=0;
function showTheFile(cateName) {
	var sel = document.getElementById("checkDoc");
	var cateId = 0;
	var cateName = cateName;
	var quesId = "${wfQues.quesId}";
	/* alert(quesId); */
	if(sel!=null && sel.checked){
		var cateId = getRadioValue('docCate');
		var cateName = cateName;
	}
	     currRow = document.getElementById("attachtab").insertRow();
	     currRow.id = "attachtabDtl"+ currRow.rowIndex;
	     cellc = currRow.insertCell();
	     
	   	 var delobj  = '<a class="colorBlue padLR5" href=javascript:downloadOrViewFile("'+quesId+'","'+cateName+'"); >'
	   	 	+'<input type="hidden" id="fileCates'+i+'" name="fileCates['+i+']" value="'+cateId+'">'
	   	 	+'<font color="red">'+cateName+'</font></a>';
	     cellc.innerHTML=delobj;
	     i++;
	
	var submitFileBtn = document.getElementById("submitFileBtn");
	if(submitFileBtn!=null && submitFileBtn.style.display=='none'){
		submitFileBtn.style.display="block";
	}
}

$(function () {
	
	 var filelist = "${wfQues.fileName}";
	 var quesID = "${wfQues.quesId}";
	 var arrayfilelist = filelist.split("||");
	 var htm = "";
	 //alert(arrayfilelist.length);
	for(var i=0;i<arrayfilelist.length;i++){
		showTheFile(arrayfilelist[i])
		}
	
	/* check(); */
	dialog = frameElement.dialog;
	 $("#userId").ligerComboBox({url:"Usr!selectUser.shtml?usr.deptId=<c:out value="${wfQues.deptId}"/>",textField:'usrName', valueField:'id',isMultiSelect: false, selectBoxWidth:180,
		 	onSuccess: function() {
				$("#userId").ligerComboBox().selectValue("<c:out value="${wfQues.userId}"/>");
			}
	 }); 
	if ($("#prjtNm").length > 0)
		$("#prjtNm").ligerTextBox({width:235});
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox({width:235});
	if ($("#idtfDate").length > 0)
		$("#idtfDate").ligerDateEditor({showTime: false});
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:235});
	if ($("#cateId").length > 0)
		$("#cateId").ligerComboBox({width:235});
	if ($("#wfQues_usrName").length > 0)$("#wfQues_usrName").ligerTextBox({width:235});
	if ($("#wfQues_deptNm").length > 0)$("#wfQues_deptNm").ligerTextBox({width:200});
	
	//测试项data
	var testItemData = [];
	testItemData.push({id: 10000,pid:0,statu:'1',text: '可靠性测试'});
		testItemData.push({id: 200,pid:10000,statu:'1.1',text: '电性能'});testItemData.push({id: 300,pid:10000,statu:'1.2',text: '软件功能'});
		testItemData.push({id: 400,pid:10000,statu:'1.3',text: '用户试用'});testItemData.push({id: 500,pid:10000,statu:'1.4',text: '场地测试'});
		testItemData.push({id: 600,pid:10000,statu:'1.5',text: 'EMC'});
		testItemData.push({id: 700,pid:10000,statu:'1.6',text: '环境测试'});
			testItemData.push({id: 701,pid:700,statu:'1.6.1',text: '低温储存'});testItemData.push({id: 702,pid:700,statu:'1.6.2',text: '低温工作'});
			testItemData.push({id: 703,pid:700,statu:'1.6.3',text: '高温储存'});testItemData.push({id: 704,pid:700,statu:'1.6.4',text: '高温高湿'});
			testItemData.push({id: 705,pid:700,statu:'1.6.5',text: '温度冲击'});testItemData.push({id: 706,pid:700,statu:'1.6.6',text: '湿热循环'});
			testItemData.push({id: 707,pid:700,statu:'1.6.7',text: '防尘'});testItemData.push({id: 708,pid:700,statu:'1.6.8',text: '盐雾'});
			testItemData.push({id: 709,pid:700,statu:'1.6.9',text: '紫外线照射'});testItemData.push({id: 710,pid:700,statu:'1.6.10',text: '铁屑'});
			testItemData.push({id: 711,pid:700,statu:'1.6.11',text: '抽真空试验'});
		testItemData.push({id: 800,pid:10000,statu:'1.7',text: '机械强度'});
			testItemData.push({id: 801,pid:800,statu:'1.7.1',text: '跌落'});testItemData.push({id: 802,pid:800,statu:'1.7.2',text: '软压'});
			testItemData.push({id: 803,pid:800,statu:'1.7.3',text: '静压'});testItemData.push({id: 804,pid:800,statu:'1.7.4',text: '随机振动'});
			testItemData.push({id: 805,pid:800,statu:'1.7.5',text: '冲击'});testItemData.push({id: 806,pid:800,statu:'1.7.6',text: '滚筒跌落_0.5米'});
			testItemData.push({id: 807,pid:800,statu:'1.7.7',text: '滚筒跌落_1米'});testItemData.push({id: 808,pid:800,statu:'1.7.8',text: '扭力'});
			testItemData.push({id: 809,pid:800,statu:'1.7.9',text: '微跌'});testItemData.push({id: 810,pid:800,statu:'1.7.10',text: '按键拉拔力'});
			testItemData.push({id: 811,pid:800,statu:'1.7.11',text: '胶塞拉力'});testItemData.push({id: 812,pid:800,statu:'1.7.12',text: '焊盘拉力'});
			testItemData.push({id: 813,pid:800,statu:'1.7.13',text: '数据线接口压力'});testItemData.push({id: 814,pid:800,statu:'1.7.14',text: '按键区域静压试验'});
			testItemData.push({id: 815,pid:800,statu:'1.7.15',text: '数据线吊重试验'});
		testItemData.push({id: 900,pid:10000,statu:'1.8',text: '寿命试验'});
			testItemData.push({id: 901,pid:900,statu:'1.8.1',text: '翻盖寿命'});testItemData.push({id: 902,pid:900,statu:'1.8.2',text: '滴水翻盖寿命'});
			testItemData.push({id: 903,pid:900,statu:'1.8.3',text: '旋盖寿命'});testItemData.push({id: 904,pid:900,statu:'1.8.4',text: '滑盖寿命'});
			testItemData.push({id: 905,pid:900,statu:'1.8.5',text: '负重滑盖寿命'});testItemData.push({id: 906,pid:900,statu:'1.8.6',text: '机械按键寿命'});
			testItemData.push({id: 907,pid:900,statu:'1.8.7',text: '波动开关寿命'});testItemData.push({id: 908,pid:900,statu:'1.8.8',text: 'SIM卡插拔寿命'});
			testItemData.push({id: 909,pid:900,statu:'1.8.9',text: '外置内存卡插拔'});testItemData.push({id: 910,pid:900,statu:'1.8.10',text: '耳机插孔插拔'});
			testItemData.push({id: 911,pid:900,statu:'1.8.11',text: '数据接口插拔'});testItemData.push({id: 912,pid:900,statu:'1.8.12',text: '电池插拔'});
			testItemData.push({id: 913,pid:900,statu:'1.8.13',text: '电池盖插拔'});testItemData.push({id: 914,pid:900,statu:'1.8.14',text: 'SPK寿命'});
			testItemData.push({id: 915,pid:900,statu:'1.8.15',text: '马达振动寿命'});testItemData.push({id: 916,pid:900,statu:'1.8.16',text: 'USB直插头摇摆寿命'});
			testItemData.push({id: 917,pid:900,statu:'1.8.17',text: '底壳滑动装饰件寿命'});testItemData.push({id: 918,pid:900,statu:'1.8.18',text: '出点按压寿命'});
			testItemData.push({id: 919,pid:900,statu:'1.8.19',text: '螺母扭力测试'});testItemData.push({id: 920,pid:900,statu:'1.8.20',text: '螺钉防松测试'});
		testItemData.push({id: 1000,pid:10000,statu:'1.9',text: '外观工艺'});
			testItemData.push({id: 1001,pid:1000,statu:'1.9.1',text: '工艺耐磨'});testItemData.push({id: 1002,pid:1000,statu:'1.9.2',text: '工艺硬度'});
			testItemData.push({id: 1003,pid:1000,statu:'1.9.3',text: '镜面/镜片硬度'});testItemData.push({id: 1004,pid:1000,statu:'1.9.4',text: '附着力试验'});
			testItemData.push({id: 1005,pid:1000,statu:'1.9.5',text: '人工汗液'});testItemData.push({id: 1006,pid:1000,statu:'1.9.6',text: '丝印耐酒精试验'});
			testItemData.push({id: 1007,pid:1000,statu:'1.9.7',text: '振动耐磨'});testItemData.push({id: 1008,pid:1000,statu:'1.9.8',text: '水煮试验'});
			testItemData.push({id: 1009,pid:1000,statu:'1.9.9',text: '钢丝绒摩擦'});testItemData.push({id: 1010,pid:1000,statu:'1.9.10',text: '主标贴丝印试验'});
			testItemData.push({id: 1011,pid:1000,statu:'1.9.11',text: '弯折试验'});testItemData.push({id: 1012,pid:1000,statu:'1.9.12',text: '耐化妆品染色试验'});
			testItemData.push({id: 1013,pid:1000,statu:'1.9.13',text: '摄像头镜片透过率'});testItemData.push({id: 1014,pid:1000,statu:'1.9.14',text: '摄像头镜片水滴角度'});
			testItemData.push({id: 1015,pid:1000,statu:'1.9.15',text: '摄像头镜片钢丝绒水滴角度'});testItemData.push({id: 1016,pid:1000,statu:'1.9.16',text: '摄像头镜片橡皮摩擦水滴角度'});
		testItemData.push({id: 1100,pid:10000,statu:'1.10',text: '温升'});
			testItemData.push({id: 1101,pid:1100,statu:'1.10.1',text: '通话状态温升'});testItemData.push({id: 1102,pid:1100,statu:'1.10.2',text: '摄像状态温升'});	
			testItemData.push({id: 1103,pid:1100,statu:'1.10.3',text: '游戏状态温升'});testItemData.push({id: 1104,pid:1100,statu:'1.10.4',text: '播放3D动画温升'});	
		testItemData.push({id: 1200,pid:10000,statu:'1.11',text: '工作电流'});
			testItemData.push({id: 1201,pid:1200,statu:'1.11.1',text: '待机电流'});testItemData.push({id: 1202,pid:1200,statu:'1.11.2',text: '飞航模式'});
			testItemData.push({id: 1203,pid:1200,statu:'1.11.3',text: '通话电流'});testItemData.push({id: 1204,pid:1200,statu:'1.11.4',text: 'LCD显示电流'});
			testItemData.push({id: 1205,pid:1200,statu:'1.11.5',text: '播放MP3电流'});testItemData.push({id: 1206,pid:1200,statu:'1.11.6',text: 'FM电流'});
			testItemData.push({id: 1207,pid:1200,statu:'1.11.7',text: '视频电流'});testItemData.push({id: 1208,pid:1200,statu:'1.11.8',text: '数据传输电流'});
			testItemData.push({id: 1209,pid:1200,statu:'1.11.9',text: '摄像电流'});testItemData.push({id: 1210,pid:1200,statu:'1.11.10',text: '游戏电流'});
			testItemData.push({id: 1211,pid:1200,statu:'1.11.11',text: '关机漏电流'});
		testItemData.push({id: 1300,pid:10000,statu:'1.12',text: '充电及电量识别精度测试'});
			testItemData.push({id: 1301,pid:1300,statu:'1.12.1',text: '充电电压检测精度'});testItemData.push({id: 1302,pid:1300,statu:'1.12.2',text: '充电波形检测'});
			testItemData.push({id: 1303,pid:1300,statu:'1.12.3',text: '充电功能试验'});testItemData.push({id: 1304,pid:1300,statu:'1.12.4',text: '低温充电电量测试'});
			testItemData.push({id: 1305,pid:1300,statu:'1.12.5',text: '高温充电电量测试'});testItemData.push({id: 1306,pid:1300,statu:'1.12.6',text: '手机连接接口电气性能测试'});
			testItemData.push({id: 1307,pid:1300,statu:'1.12.7',text: '高温电池安全性测试'});testItemData.push({id: 1308,pid:1300,statu:'1.12.8',text: '开机电量识别精准度测试'});
			testItemData.push({id: 1309,pid:1300,statu:'1.12.9',text: '关机充电电量测试'});
		testItemData.push({id: 1400,pid:10000,statu:'1.13',text: '整机功能性能'});testItemData.push({id: 1500,pid:10000,statu:'1.14',text: '音频'});
		testItemData.push({id: 1600,pid:10000,statu:'1.15',text: '显示拍照效果'});testItemData.push({id: 1700,pid:10000,statu:'1.16',text: '天线暗室'});
		testItemData.push({id: 1800,pid:10000,statu:'1.17',text: '防水测试'});testItemData.push({id: 1900,pid:10000,statu:'1.18',text: '附配件'});
	testItemData.push({id: 20000,pid:0,statu:'2',text: '白盒测试'});testItemData.push({id: 30000,pid:0,statu:'3',text: '硬件测试'});
	//alert($("#testItemName").val() + "~~~~~"+$("#testItemID").val());
	$("#treeTestItem").val($("#testItemName").val());
	$("#treeTestItem").ligerComboBox({
        width : 200, 
        selectBoxWidth: 200,
        selectBoxHeight: 180, valueField: 'text',
        tree: {data: testItemData,checkbox: false, idFieldName :'id',isExpand: false, slide : false,
            parentIDFieldName :'pid',
         onClick:function(testItemData,target){
        	var testItemName = $("#treeTestItem").ligerComboBox().getValue();
        	testItemName = testItemName.replace(/;/g,"','");
        	var testItemID = testItemData.data.statu;
        	
        	if($("#testItemName").val() == testItemName) {
        		$("#testItemName").val("");
        	}else {
        		//alert(testItemID);
        		$("#testItemName").val(testItemName);
        		$("#testItemID").val(testItemID);
        	}
        } 
        }
    });
    

	$("#completedDate").ligerDateEditor({labelWidth: 200,format: "yyyy-MM-dd"});
	
});

var dialog33;
//修改问题责任人
function updateQuesUsrs(){
	//alert($("#wfQues_prjtNo").val());
	dialog33 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#prjtNo").val()+'&isFromWf=0'});
}
function setUsrData(usrId,usrName){
	document.getElementById("wfQues_usrName").innerHTML = usrName;
	$("#responsibleUID").val(usrId);
	var str = '{';
	str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '",';
	str += '"wfQues.usrName":"' + usrName + '",';
	str += '"responsibleUID":"' + usrId + '"';
	str += '}';
	$.post("WfQues!updateResponers.shtml",eval('(' + str + ')'),function(data) {
		$.ligerDialog.success(data,"提示",function(){
			//alert(data);
			window.location = "./WfQues!editQuesUI.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
		  });
	 },"text");
} 
//提交任务成功后刷新问题管理
function relodeQuesManager() {
		if(window.parent.quesManagerWinow){
			window.parent.quesManagerWinow.frame.gridManager.loadData();
		}
}
function updateQues() {
	$("form").submit();
} 
//判断是否为DQA或项目经理，是的话就允许修改问题等级
function cateID() {
	//alert(document.getElementById("gpNames").value);
	if (document.getElementById("gpNames").value.indexOf(',DQA') > -1 || document.getElementById("gpNames").value.indexOf(',项目经理') > -1) {
		
	}else{
		alert("非DQA或项目经理不允许修改问题等级！");
		document.getElementById("cateId").options[${wfQues.cateId}].selected=true;
	}
} 

	
</script>
</head>
<body>
<div id="toolbar"></div>
<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<form action="WfQues!editQues.shtml" method="post" enctype="multipart/form-data">
	<input type="hidden" id="responsibleUID" name="responsibleUID"/>
	<input type="hidden" id="quesId" name="wfQues.quesId" value="<c:out value="${wfQues.quesId}"/>"/>
	<input type="hidden" id="prjtNo" name="wfQues.prjtNo" value="<c:out value="${wfQues.prjtNo}"/>"/>
	<input type="hidden" id="gpNames" name="gpNames" size="30" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>" />
	
	
	<input type="hidden" id="wfQues_quesId" name="wfQues_quesId" value="<c:out value="${wfQues.quesId}"/>">
	<div class="listTable clearfix" style="margin:10px 20px;" >
     <table cellpadding=0 cellspacing=0.5 width=700px style="background:#9FC2E5;" class="innerTable"  align=center>
	    <tr>
			<th>项目名称：<font color="red">&nbsp;*</font></th>
			<td ><input type="text" id="prjtNm" name="wfQues.prjtNm" size="30" value="<c:out value="${wfQues.prjtNm}"/>"
			     disabled="disabled" validate="{required:true}" />   </td>
				    <%-- <th >项目工作流：<font color="red">&nbsp;*</font></th>
				    <td>
				    <input type="text" id="wfName" name="wfQues.wfName" size="30" value="<c:out value="${wfQues.wfName}"/>"
				    	  disabled="disabled"  validate="{required:true}" /> 
				     </td> --%>
			<th	>阶段：<font color="red">&nbsp;*</font></th>
			<td height="24" width="90" align="left">
					<select id="scheId" name="wfQues.scheId" style="width: 90px;">
							    <option value="">请选择</option>
								<option value="23" <c:if test="${23 == wfQues.scheId}">selected</c:if>>小批试产(T1)</option>
								<%-- <option value="44" <c:if test="${44 == wfQues.scheId}">selected</c:if>>小批试产(T1-1)</option>
								<option value="45" <c:if test="${45 == wfQues.scheId}">selected</c:if>>小批试产(T1-2)</option> --%>
								<option value="28" <c:if test="${28 == wfQues.scheId}">selected</c:if>>小批试产(T2)</option>
								<option value="52" <c:if test="${52 == wfQues.scheId}">selected</c:if>>小批试产(T3)</option>
								<%-- <option value="46" <c:if test="${46 == wfQues.scheId}">selected</c:if>>小批试产(T2-1)</option>
								<option value="47" <c:if test="${47 == wfQues.scheId}">selected</c:if>>小批试产(T2-2)</option> --%>
								<option value="48" <c:if test="${48 == wfQues.scheId}">selected</c:if>>中批试产(PP)</option>
								<%-- <option value="49" <c:if test="${49 == wfQues.scheId}">selected</c:if>>中批试产(PP-1)</option>
								<option value="50" <c:if test="${50 == wfQues.scheId}">selected</c:if>>中批试产(PP-2)</option> --%>
								<option value="51" <c:if test="${51 == wfQues.scheId}">selected</c:if>>批量(MP)</option>
					</select>
							
				</td>
		</tr>
		<tr>
			<th>问题来源：<font color="red">&nbsp;*</font></th>
			<td ><select id="sourceID" name="wfQues.sourceID" style="width:200px">
					<option value="">请选择</option>
					 <option value="1" <c:if test="${1 == wfQues.sourceID}">selected</c:if>>硬件测试</option> 
					<option value="2" <c:if test="${2 == wfQues.sourceID}">selected</c:if>>试产组装</option>
					<option value="3" <c:if test="${3 == wfQues.sourceID}">selected</c:if>>试产贴片</option>
					<option value="4" <c:if test="${4 == wfQues.sourceID}">selected</c:if>>白盒测试</option>
					<option value="5" <c:if test="${5 == wfQues.sourceID}">selected</c:if>>整机测试</option>
					<option value="6" <c:if test="${6 == wfQues.sourceID}">selected</c:if>>工厂测试</option>
				</select>
			</td>  
						<%-- <th>标题：<font color="red">&nbsp;*</font></th>
						<td colspan="3"><input type="text" id="title" name="wfQues.title"  value="<c:out value="${wfQues.title}"/>"  validate="{required:true}" />  </td>
					 --%>
			<th>问题类别：<font color="red">&nbsp;*</font></th>
			<td >
				<select id="quesTypeID" name="wfQues.quesTypeID" style="width:200px">
					<option value="">请选择</option>
					 <%-- <option value="1" <c:if test="${1 == wfQues.quesTypeID}">selected</c:if>>基带</option>  --%>
					<option value="2" <c:if test="${2 == wfQues.quesTypeID}">selected</c:if>>射频</option>
					<%-- <option value="3" <c:if test="${3 == wfQues.quesTypeID}">selected</c:if>>音频</option> --%>
					<option value="4" <c:if test="${4 == wfQues.quesTypeID}">selected</c:if>>结构</option>
					<option value="5" <c:if test="${5 == wfQues.quesTypeID}">selected</c:if>>外观工艺</option>
					<option value="6" <c:if test="${6 == wfQues.quesTypeID}">selected</c:if>>软件</option>
					<option value="7" <c:if test="${7 == wfQues.quesTypeID}">selected</c:if>>贴片工艺</option>
					<option value="8" <c:if test="${8 == wfQues.quesTypeID}">selected</c:if>>组装工艺</option>
					<option value="9" <c:if test="${9 == wfQues.quesTypeID}">selected</c:if>>物料</option>
					<option value="10" <c:if test="${10 == wfQues.quesTypeID}">selected</c:if>>附配件测试</option>
					<option value="11" <c:if test="${11 == wfQues.quesTypeID}">selected</c:if>>硬件</option>
				</select>  
			</td>	
		</tr>
		<tr>
			<%-- <th ><input type="button" id="responsibleBtn" name="responsibleBtn" size="30"  class="wfbigbtn2"  onclick="selcResponsible()" 
			disabled="disabled" value="责任人"/>
			</th>
			<td colspan="3">
				<input type="text" id="userName" name="wfQues.usrName"  disabled="disabled" value="<c:out value="${wfQues.usrName}"/>"/>
			</td> --%>
			<th>责任人：<font color="red">&nbsp;*</font></th>
			 <td nowrap="nowrap" id = "wfQues_usrName">
                            	<c:out value="${wfQues.usrName}"/>
	                            <input id="upBtn" type="button" value=" 转交" class="wfbigbtn2"  onclick="updateQuesUsrs();" />
            </td> 
			<%-- <td >
				<input type="text" style="width:200px" id="wfQues_usrName" name="wfQues.usrName"  onclick="selcResponsible()" value="<c:out value="${wfQues.usrName}"/>"/>
			</td> --%>
			<th>责任部门：</th>
			<td >
				<input type="text" id="wfQues_deptNm" readonly="true" value="<c:out value="${wfQues_deptNm}"/>"/>
			</td>
		</tr>
		 <tr>
		 
			<th>问题等级：<font color="red">&nbsp;*</font></th>
			<td >
				<select id="cateId" name="wfQues.cateId" onchange="cateID()" >
					<option value="">请选择</option>
					<%--  <option value="1" <c:if test="${1 == wfQues.cateId}">selected</c:if>>S</option> --%> 
					<option value="2" <c:if test="${2 == wfQues.cateId}">selected</c:if>>A</option>
					<option value="3" <c:if test="${3 == wfQues.cateId}">selected</c:if>>B</option>
					<option value="4" <c:if test="${4 == wfQues.cateId}">selected</c:if>>C</option>
				</select>  
			</td>
			<th>不良比例：<font color="red">&nbsp;*</font></th>
			<td >
				<input type="text" id="fractionDefective" name="wfQues.fractionDefective" value="<c:out value="${wfQues.fractionDefective}"/>"/>
			</td>
			<tr>
			 <td height="24" width="70" align="left">测试项：</td>
			<td >
				<input type="text" id="treeTestItem"/>
				<input type="hidden" id="testItemName" name="wfQues.testItemName" value="<c:out value="${wfQues.testItemName}"/>"/>
				<input type="hidden" id="testItemID" name="wfQues.testItemID" value="<c:out value="${wfQues.testItemID}"/>"/>
			</td> 
		</tr>
		</tr> 
		<tr>
			<th>问题描述：<font color="red">&nbsp;*</font></th>
			<td colspan="3">
				<textarea id="quesText" name="wfQues.quesText" style="width:500px;height: 200px;" validate="{required:true}"><c:out value="${wfQues.quesText}"/></textarea>  
			</td>
		</tr>
		<tr id = "upload">
	   <th><div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
				<a href="javascript:void(0);" onclick="addFile()">添加附件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
			</div></th>
	   <td colspan="3">
	   	<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="600px" align=center>
				<tr>
					<td>
						<table id="attachtab" border="0" cellpadding="0" cellspacing="0">
						</table>
					</td>
				</tr>
			</table>
	   </td>
	</tr> 
		
	
	<tr id="savBtn"  >
	   <td colspan="4">
		    <table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0  align=right>
				<tr>
					<td align="right">
						<input  type="button" value=" 提交问题  " class="wfbigbtn2"  onclick="updateQues();" />
					</td>
				</tr>
			 </table>
	   </td>
	</tr>
</table>
</div> 
</form>
</div>
</body>
</html>