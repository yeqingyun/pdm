function f_onCheckAllRow(checked) {
	for ( var rowid in this.records) {
		if (checked)
			addCheckedCustomer(this.records[rowid]['quesId']);
		else
			removeCheckedCustomer(this.records[rowid]['quesId']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(quesId) {
	for (var i = 0; i < checkedCustomer.length; i++) {
		if (checkedCustomer[i] == quesId)
			return i;
	}
	return -1;
}
function addCheckedCustomer(quesId) {
	if (findCheckedCustomer(quesId) == -1)
		checkedCustomer.push(quesId);
}
function removeCheckedCustomer(quesId) {
	var i = findCheckedCustomer(quesId);
	if (i == -1)
		return;
	checkedCustomer.splice(i, 1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.quesId) == -1)
		return false;
	return true;
}

var checkedWfNo;

function f_onCheckRow(checked, data) {
	if (checked) {
		addCheckedCustomer(data.quesId);
		checkedWfNo = data.wfNo;
	} else
		removeCheckedCustomer(data.quesId);
}
function f_onDblClickRow(data) {
	if(data.status == 40) {
		$.ligerDialog.warn("已转风险评估的问题，不能在此操作，请转到风险管理模块处理！");
		return;
	}
	if(data.crDefectId == null) {
		rep(data);
	}else {
		repCQ(data);
	}
}


//function check(rflag) {
//	alert("check");
//	$.metadata.setType("attr", "validate");
//	var v = $("form")
//			.validate(
//					{
//						errorPlacement : function(lable, element) {
//
//							if (element.hasClass("l-textarea")) {
//								element.addClass("l-textarea-invalid");
//							} else if (element.hasClass("l-text-field")) {
//								element.parent().addClass("l-text-invalid");
//							}
//
//							var nextCell = element.parents("td:first").next(
//									"td");
//							nextCell.find("div.l-exclamation").remove();
//							$(
//									'<div class="l-exclamation" title="'
//											+ lable.html() + '"></div>')
//									.appendTo(nextCell).ligerTip();
//						},
//						success : function(lable) {
//							var element = $("#" + lable.attr("for"));
//							var nextCell = element.parents("td:first").next(
//									"td");
//							if (element.hasClass("l-textarea")) {
//								element.removeClass("l-textarea-invalid");
//							} else if (element.hasClass("l-text-field")) {
//								element.parent().removeClass("l-text-invalid");
//							}
//							nextCell.find("div.l-exclamation").remove();
//							
//							//alert("check2");
//						},
//						/*submitHandler : function() {
//							if (rflag) {
//								rsave();
//							} else {
//								saver();
//							}
//						}*/
//					});
//	// $("form").ligerForm();
//}

function res() {
	if ($("#comId").length > 0)
		$("#comId").val("");
	if ($("#deptId").length > 0)
		$("#deptId").val("");
	if ($("#userId").length > 0)
		$("#userId").val("");
	if ($("#quesLevel").length > 0)
		$("#quesLevel").val("");
	if ($("#status").length > 0)
		$("#status").val("");
	if ($("#createBy").length > 0)
		$("#createBy").val("");
	if ($("#lastUpd").length > 0)
		$("#lastUpd").val("");
	if ($("#wfNo").length > 0)
		$("#wfNo").val("");
	if ($("#isRisk").length > 0)
		$("#isRisk").val("");
	if ($("#quesText").length > 0)
		$("#quesText").val("");
	if ($("#result").length > 0)
		$("#result").val("");
	if ($("#ryUsrs").length > 0)
		$("#ryUsrs").val("");
	if ($("#ryUsrNms").length > 0)
		$("#ryUsrNms").val("");
	if ($("#title").length > 0)
		$("#title").val("");
	
	if ($("#sourceID").length > 0)
		$("#sourceID").val("");
	if ($("#quesTypeID").length > 0)
		$("#quesTypeID").val("");
	
	if ($("#quesId").length > 0)
		$("#quesId").val("");
	
	if ($("#fractionDefective").length > 0)
		$("#fractionDefective").val("");

	if ($("#testItemID").length > 0)
		$("#testItemID").val("");
	if ($("#testItemName").length > 0)
		$("#testItemName").val("");
}
var DialogMgr = (function() {
	var dialog;
	return {
		create : function(title, url) {
			dialog = $.ligerDialog.open({
				title : title,
				height : 460,
				width : 810,
				url : url,
				showMax : true,
				showToggle : true,
				showMin : true,
				isResize : true
			})
		},
		close : function() {
			if (dialog) {
				dialog.close();
			}
		}
	};
})();

/**
 * 新增问题
 * @param isFromWf
 */
function add(isFromWf) {
	if(isFromWf){
		if ($('#wfNo') && $('#wfNo').val() && $('#wfNo').val() != '') {
			window.parent.parent.f_open('./WfQues!add.shtml?wfQues.taskId='
					+ $('#taskId').val() + '&wfQues.wfNo=' + $('#wfNo').val()+ '&isFromWf=' + isFromWf,'新增对话框：WfQues');
			/*DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?wfQues.taskId='
					+ $('#taskId').val() + '&wfQues.wfNo=' + $('#wfNo').val()+ '&isFromWf=' + isFromWf);*/
		} else {
			$.ligerDialog.warn('没有对应流程,不能添加问题。');
		}
	}else{
		window.parent.f_open('./WfQues!add.shtml?isFromWf='+isFromWf+'&wfQues.prjtNm=' +document.getElementById("pNm").innerHTML+'&wfQues.prjtNo=' + $('#prjtNo').val(),'新增对话框：WfQues');
		//DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?isFromWf='+isFromWf);
	}
}

function transmgr() {
	if (window.parent.parent.parent) {
		if (window.parent.parent.parent.addtab) {
			window.parent.parent.parent.addtab($("#wfNo").val(), $("#wfNo")
					.val()
					+ "流程问题", "./WfQues!tasklistmgr.shtml?wfNo="
					+ $("#wfNo").val());
		} else {
			if (window.parent.parent.f_open) {
				window.parent.parent.f_open("./WfQues!tasklistmgr.shtml?wfNo="
						+ $("#wfNo").val(), $("#wfNo").val() + "流程问题", '');
			}
		}
	}
}

function savDeal(msg) {
	$.ligerDialog.success(msg);
	reloadGrid();
	DialogMgr.close();
}

function edi() {
	var data = gridManager.getSelected();
	if (!data) {
		$.ligerDialog.warn('请选择编辑的记录。');
		return;
	}
	var createBy = data.createBy;
	var roleDQAId = data.roleDQAId;
	if ($('#sys_usrid')) {
		var sur = $('#sys_usrid').val();
		if (sur != roleDQAId && sur != createBy) {
			$.ligerDialog.warn('您非本问题[DQA]或[创建人],不能进行此操作');
			return;
		}
	}
	DialogMgr.create('编辑对话框：WfQues', './WfQues!edit.shtml?wfQues.quesId='
			+ data.quesId);
}
function rep(data) {
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
			    "./WfQues!managerQues.shtml?wfQues.quesId="+data.quesId,"问题管理："+data.quesId,"./include/images/Alien Folder.png"
			);
		}else if(window.parent.parent.f_open){
			window.parent.parent.f_open('./WfQues!managerQues.shtml?wfQues.quesId='+ data.quesId,'管理对话框：WfQues');
		}else {
			DialogMgr.create('管理对话框：WfQues', './WfQues!managerQues.shtml?wfQues.quesId='+ data.quesId);
		}
	}else{
		DialogMgr.create('管理对话框：WfQues', './WfQues!managerQues.shtml?wfQues.quesId='+ data.quesId);
	}
}
function repCQ(data) {
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
			    "./CQDefect!managerDefect.shtml?defect.id="+data.crDefectId,"CQ缺陷视图："+data.crDefectId,"./include/images/Alien Folder.png"
			);
		}else if(window.parent.parent.f_open){
			window.parent.parent.f_open('./CQDefect!managerDefect.shtml?defect.id='+ data.crDefectId,'CQ缺陷视图:CQDefect');
		}else {
			DialogMgr.create('CQ缺陷视图:CQDefect', './CQDefect!managerDefect.shtml?defect.id='+ data.crDefectId);
		}
	}else{
		DialogMgr.create('CQ缺陷视图:CQDefect', './CQDefect!managerDefect.shtml?defect.id='+ data.crDefectId);
	}
}
function vie(data) {
	$.ligerDialog.open({
		title : '查看管理对话框：WfQues',
		height : 560,
		width : 820,
		url : './WfQues!view.shtml?wfQues.quesId=' + data.quesId,
		showMax : true,
		showToggle : true,
		showMin : true,
		isResize : true
	});
}
function sea() {
	if (gridManager) {
		
		
		//alert(document.getElementById("createBy").value);
		


		var selectIndex ;
		var selectUserText = "";
		 if($("#QuesRespUserId").val()){
			 selectIndex = document.getElementById("QuesRespUserId").selectedIndex;//获得是第几个被选中了
			 selectUserText = document.getElementById("QuesRespUserId").options[selectIndex].text //获得被选中的项目的文本
			 //alert($("#QuesRespUserId").val() +selectUserText);
		 }

		
		
		gridManager
				.setOptions({
					
					parms : [
					      
							{
								name : 'wfQues.scheId',
								value : ($("#scheId").length > 0 && $("#scheId")
										.ligerComboBox().selectedValue) ? $(
										"#scheId").ligerComboBox().selectedValue
										: '' ? $("#scheId").ligerComboBox().selectedValue
												: ''
							},
							
							/**
							{
								name : 'wfQues.typId',
								value : ($("#typId").length > 0 && $("#typId")
										.ligerComboBox().selectedValue) ? $(
										"#typId").ligerComboBox().selectedValue
										: ''
							},
							
							{
								name : 'wfQues.wfNo',
								value : ($("#wfNo").length > 0) ? $("#wfNo")
										.val() : ''
							},
							
							{
								name : 'wfQues.userId',
								value : ($("#userId").length > 0) ? $("#userId")
										.val()
										: ''
							},
							{
								name : 'wfQues.selectType',
								value : ($("#selectType").length > 0) ? $(
										"#selectType").val() : ''
							},
							{
								name : 'wfQues.usrName',
								value : ($("#usrName").length > 0) ? $(
										"#usrName").val() : ''
							},**/
							
							
							{
								name : 'wfQues.quesId',
								value : ($("#quesId").length > 0) ? $(
										"#quesId").val() : ''
							},
							/**
							{
								name : 'wfQues.title',
								value : ($("#questitle").length > 0) ? $(
										"#questitle").val() : ''
							},
							
							{
								name : 'wfQues.completedDate',
								value : ($("#completedDate").length > 0) ? $(
										"#completedDate").val() : ''
							},
							
								{
								name : 'wfQues.idtfDate',
								value : ($("#idtfDate").length > 0) ? $(
										"#idtfDate").val() : ''
							},
							
							{
								name : 'wfQues.crDefectId',
								value : ($("#crDefectId").length > 0) ? $(
										"#crDefectId").val() : ''
							},
							
							{
								name : 'wfQues.category',
								value : ($("#category").length > 0) ? $(
										"#category").val() : ''
							},
							
							{
								name : 'wfQues.selType',
								value : ($("#selType").length > 0) ? $(
										"#selType").val() : ''
							},
							
							
							
							
							{
								name : 'wfQues.createBy',
								value : ($("#createBy").length > 0) ? $(
										"#createBy").val() : ''
							},
							
							
							
							
							**/
							
							
							
							{
								name : 'wfQues.usrName',
								value : selectUserText
							},
							
							
							{
								name : 'wfQues.createBy',
								value : (document.getElementById("createBy").value) ? document.getElementById("createBy").value : ''
							},
							
							
							
							{
								name : 'wfQues.quesText',
								value : ($("#quesText").length > 0) ? $(
										"#quesText").val() : ''
							},
							
							{
								name : 'wfQues.cateId',
								value : ($("#cateId").length > 0) ? $(
										"#cateId").val() : ''
							},
							
							{
								name : 'wfQues.status',
								value : ($("#status").length > 0) ? $("#status").val(): ''
							},
							
							{
								name : 'wfQues.prjtNo',
								value : ($("#prjtNo").length > 0) ? $("#prjtNo")
										.val()
										: ''
							},
							{
								name : 'wfQues.deptNm',
								value : ($("#deptNm").length > 0) ? $(
										"#deptNm").val() : ''
							},
							{
								name : 'wfQues.sourceID',
								value : ($("#sourceID").length > 0) ? $(
										"#sourceID").val() : ''
							},{
								name : 'wfQues.quesTypeID',
								value : ($("#quesTypeID").length > 0) ? $(
										"#quesTypeID").val() : ''
							},
							
							{
								name : 'wfQues.status1',
								value : ($("#status1").length > 0) ? $("#status1").val(): ''
							},
							
							{
								name : 'wfQues.fractionDefective',
								value : ($("#fractionDefective").length > 0) ? $("#fractionDefective").val(): ''
							},
							{
								name : 'wfQues.testItemName',
								value : ($("#testItemName").length > 0) ? $("#testItemName").val(): ''
							},
							{
								name : 'wfQues.testItemID',
								value : ($("#testItemID").length > 0) ? $("#testItemID").val(): ''
							}
							
							]
				});
		gridManager.loadData();
	}
}
var gridManager;


$(function() {
	if ($("#quesText").val()) {
		// var tmp = $("#quesText").val().replace(/<br\/>/g, "\n");
		// $("#quesText").val(tmp);
	}
	if ($("#result").val()) {
		var tmp = $("#result").val().replace(/<br\/>/g, "\n");
		$("#result").val(tmp);
	}
	var flag = false;
	if ($("#startCreateDate").length > 0) {
		flag = true;
	}
	$("#maingrid").ligerGrid({

		columns : [ {
			display : '最终状态',
			name : 'status',
			align : 'Central',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.status == 21) {
					innerHtml += "green";
					v = "Open";//已挂起
				} else if (row.status == 30) {
					innerHtml += "blue";
					v = "Close";
				}else if(row.status == 40){
					innerHtml += "red";
					v = "Risk";
				}else if(row.status == 1){
					innerHtml += "green";
					v = "Open";//待解决
				}else if(row.status == 9){
					innerHtml += "green";
					v = "Open";//验证未通过
				}else if(row.status == 10){
					innerHtml += "green";
					v = "Open";//待验证
				}else if(row.status == 11){
					innerHtml += "green";
					v = "Open";//验证通过
				}else if(row.status == 8){
					innerHtml += "green";
					v = "Open";//退回
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '等级',
			name : 'cateId',
			align : 'Central',
			width : 45,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
				if (row.cateId == 1) {
					innerHtml += "#9D2602";
					v = "S";
				} else if (row.cateId == 2) {
					innerHtml += "red";
					v = "A";
				} else if (row.cateId == 3) {
					innerHtml += "blue";
					v = "B";
				} else if (row.cateId == 4) {
					innerHtml += "green";
					v = "C";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '解决状态',
			name : 'status',
			align : 'Central',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v;
				if (row.status == 21) {
					innerHtml += "green";
					v = "已挂起";
				} else if (row.status == 30) {
					innerHtml += "green";
					v = "已关闭";
				}else if(row.status == 40){
					innerHtml += "red";
					v = "转风险";
				}else if(row.status == 1){
					innerHtml += "";
					v = "待解决";
				}else if(row.status == 9){
					innerHtml += "";
					v = "验证未通过";
				}else if(row.status == 10){
					innerHtml += "";
					v = "待验证";
				}else if(row.status == 11){
					innerHtml += "";
					v = "验证通过";
				}else if(row.status == 8){
					innerHtml += "";
					v = "退回";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},{
			display : '阶段',
			name : 'schNm',
			align : 'Central',
			width : 100,
		},{
			display : '测试项',
			name : 'testItemID',
			align : 'Central',
			width : 100,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
				if (row.testItemID == "1") {innerHtml += "blue";v = "可靠性测试";
				} else if (row.testItemID == "1.1") {innerHtml += "green";v = "电性能";
				} else if (row.testItemID == "1.2") {innerHtml += "green";v = "软件功能";
				} else if (row.testItemID == "1.3") {innerHtml += "green";v = "用户试用";
				} else if (row.testItemID == "1.4") {innerHtml += "green";v = "场地测试";
				} else if (row.testItemID == "1.5") {innerHtml += "green";v = "EMC";
				} else if (row.testItemID == "1.6") {innerHtml += "green";v = "环境测试";
				} else if (row.testItemID == "1.6.1") {innerHtml += "";v = "低温储存";
				} else if (row.testItemID == "1.6.2") {innerHtml += "";v = "低温工作";
				} else if (row.testItemID == "1.6.3") {innerHtml += "";v = "高温储存";
				} else if (row.testItemID == "1.6.4") {innerHtml += "";v = "高温高湿";
				} else if (row.testItemID == "1.6.5") {innerHtml += "";v = "温度冲击";
				} else if (row.testItemID == "1.6.6") {innerHtml += "";v = "湿热循环";
				} else if (row.testItemID == "1.6.7") {innerHtml += "";v = "防尘";
				} else if (row.testItemID == "1.6.8") {innerHtml += "";v = "盐雾";
				} else if (row.testItemID == "1.6.9") {innerHtml += "";v = "紫外线照射";
				} else if (row.testItemID == "1.6.10") {innerHtml += "";v = "铁屑";
				} else if (row.testItemID == "1.6.11") {innerHtml += "";v = "抽真空试验";
				} else if (row.testItemID == "1.7") {innerHtml += "green";v = "机械强度";
				} else if (row.testItemID == "1.7.1") {innerHtml += "";v = "跌落";
				} else if (row.testItemID == "1.7.2") {innerHtml += "";v = "软压";
				} else if (row.testItemID == "1.7.3") {innerHtml += "";v = "静压";
				} else if (row.testItemID == "1.7.4") {innerHtml += "";v = "随机振动";
				} else if (row.testItemID == "1.7.5") {innerHtml += "";v = "冲击";
				} else if (row.testItemID == "1.7.6") {innerHtml += "";v = "滚筒跌落_0.5米";
				} else if (row.testItemID == "1.7.7") {innerHtml += "";v = "滚筒跌落_1米";
				} else if (row.testItemID == "1.7.8") {innerHtml += "";v = "扭力";
				} else if (row.testItemID == "1.7.9") {innerHtml += "";v = "微跌";
				} else if (row.testItemID == "1.7.10") {innerHtml += "";v = "按键拉拔力";
				} else if (row.testItemID == "1.7.11") {innerHtml += "";v = "胶塞拉力";
				} else if (row.testItemID == "1.7.12") {innerHtml += "";v = "焊盘拉力";
				} else if (row.testItemID == "1.7.13") {innerHtml += "";v = "数据线接口压力";
				} else if (row.testItemID == "1.7.14") {innerHtml += "";v = "按键区域静压试验";
				} else if (row.testItemID == "1.7.15") {innerHtml += "";v = "数据线吊重试验";
				} else if (row.testItemID == "1.8") {innerHtml += "green";v = "寿命试验";
				} else if (row.testItemID == "1.8.1") {innerHtml += "";v = "翻盖寿命";
				} else if (row.testItemID == "1.8.2") {innerHtml += "";v = "滴水翻盖寿命";
				} else if (row.testItemID == "1.8.3") {innerHtml += "";v = "旋盖寿命";
				} else if (row.testItemID == "1.8.4") {innerHtml += "";v = "滑盖寿命";
				} else if (row.testItemID == "1.8.5") {innerHtml += "";v = "负重滑盖寿命";
				} else if (row.testItemID == "1.8.6") {innerHtml += "";v = "机械按键寿命";
				} else if (row.testItemID == "1.8.7") {innerHtml += "";v = "波动开关寿命";
				} else if (row.testItemID == "1.8.8") {innerHtml += "";v = "SIM卡插拔寿命";
				} else if (row.testItemID == "1.8.9") {innerHtml += "";v = "外置内存卡插拔";
				} else if (row.testItemID == "1.8.10") {innerHtml += "";v = "耳机插孔插拔";
				} else if (row.testItemID == "1.8.11") {innerHtml += "";v = "数据接口插拔";
				} else if (row.testItemID == "1.8.12") {innerHtml += "";v = "电池插拔";
				} else if (row.testItemID == "1.8.13") {innerHtml += "";v = "电池盖插拔";
				} else if (row.testItemID == "1.8.14") {innerHtml += "";v = "SPK寿命";
				} else if (row.testItemID == "1.8.15") {innerHtml += "";v = "马达振动寿命";
				} else if (row.testItemID == "1.8.16") {innerHtml += "";v = "USB直插头摇摆寿命";
				} else if (row.testItemID == "1.8.17") {innerHtml += "";v = "底壳滑动装饰件寿命";
				} else if (row.testItemID == "1.8.18") {innerHtml += "";v = "出点按压寿命";
				} else if (row.testItemID == "1.8.19") {innerHtml += "";v = "螺母扭力测试";
				} else if (row.testItemID == "1.8.20") {innerHtml += "";v = "螺钉防松测试";
				} else if (row.testItemID == "1.9") {innerHtml += "green";v = "外观工艺";
				} else if (row.testItemID == "1.9.1") {innerHtml += "";v = "工艺耐磨";
				} else if (row.testItemID == "1.9.2") {innerHtml += "";v = "工艺硬度";
				} else if (row.testItemID == "1.9.3") {innerHtml += "";v = "镜面/镜片硬度";
				} else if (row.testItemID == "1.9.4") {innerHtml += "";v = "附着力试验";
				} else if (row.testItemID == "1.9.5") {innerHtml += "";v = "人工汗液";
				} else if (row.testItemID == "1.9.6") {innerHtml += "";v = "丝印耐酒精试验";
				} else if (row.testItemID == "1.9.7") {innerHtml += "";v = "振动耐磨";
				} else if (row.testItemID == "1.9.8") {innerHtml += "";v = "水煮试验";
				} else if (row.testItemID == "1.9.9") {innerHtml += "";v = "钢丝绒摩擦";
				} else if (row.testItemID == "1.9.10") {innerHtml += "";v = "主标贴丝印试验";
				} else if (row.testItemID == "1.9.11") {innerHtml += "";v = "弯折试验";
				} else if (row.testItemID == "1.9.12") {innerHtml += "";v = "耐化妆品染色试验";
				} else if (row.testItemID == "1.9.13") {innerHtml += "";v = "摄像头镜片透过率";
				} else if (row.testItemID == "1.9.14") {innerHtml += "";v = "摄像头镜片水滴角度";
				} else if (row.testItemID == "1.9.15") {innerHtml += "";v = "摄像头镜片钢丝绒水滴角度";
				} else if (row.testItemID == "1.9.16") {innerHtml += "";v = "摄像头镜片橡皮摩擦水滴角度";
				} else if (row.testItemID == "1.10") {innerHtml += "green";v = "温升";
				} else if (row.testItemID == "1.10.1") {innerHtml += "";v = "通话状态温升";
				} else if (row.testItemID == "1.10.2") {innerHtml += "";v = "摄像状态温升";
				} else if (row.testItemID == "1.10.3") {innerHtml += "";v = "游戏状态温升";
				} else if (row.testItemID == "1.10.4") {innerHtml += "";v = "播放3D动画温升";
				} else if (row.testItemID == "1.11") {innerHtml += "green";v = "工作电流";
				} else if (row.testItemID == "1.11.1") {innerHtml += "";v = "待机电流";
				} else if (row.testItemID == "1.11.2") {innerHtml += "";v = "飞航模式";
				} else if (row.testItemID == "1.11.3") {innerHtml += "";v = "通话电流";
				} else if (row.testItemID == "1.11.4") {innerHtml += "";v = "LCD显示电流";
				} else if (row.testItemID == "1.11.5") {innerHtml += "";v = "播放MP3电流";
				} else if (row.testItemID == "1.11.6") {innerHtml += "";v = "FM电流";
				} else if (row.testItemID == "1.11.7") {innerHtml += "";v = "视频电流";
				} else if (row.testItemID == "1.11.8") {innerHtml += "";v = "数据传输电流";
				} else if (row.testItemID == "1.11.9") {innerHtml += "";v = "摄像电流";
				} else if (row.testItemID == "1.11.10") {innerHtml += "";v = "游戏电流";
				} else if (row.testItemID == "1.11.11") {innerHtml += "";v = "关机漏电流";
				} else if (row.testItemID == "1.12") {innerHtml += "green";v = "充电及电量识别精度测试";
				} else if (row.testItemID == "1.12.1") {innerHtml += "";v = "充电电压检测精度";
				} else if (row.testItemID == "1.12.2") {innerHtml += "";v = "充电波形检测";
				} else if (row.testItemID == "1.12.3") {innerHtml += "";v = "充电功能试验";
				} else if (row.testItemID == "1.12.4") {innerHtml += "";v = "低温充电电量测试";
				} else if (row.testItemID == "1.12.5") {innerHtml += "";v = "高温充电电量测试";
				} else if (row.testItemID == "1.12.6") {innerHtml += "";v = "手机连接接口电气性能测试";
				} else if (row.testItemID == "1.12.7") {innerHtml += "";v = "高温电池安全性测试";
				} else if (row.testItemID == "1.12.8") {innerHtml += "";v = "开机电量识别精准度测试";
				} else if (row.testItemID == "1.12.9") {innerHtml += "";v = "关机充电电量测试";
				} else if (row.testItemID == "1.13") {innerHtml += "green";v = "整机功能性能";
				} else if (row.testItemID == "1.14") {innerHtml += "green";v = "音频";
				} else if (row.testItemID == "1.15") {innerHtml += "green";v = "显示拍照效果";
				} else if (row.testItemID == "1.16") {innerHtml += "green";v = "天线暗室";
				} else if (row.testItemID == "1.17") {innerHtml += "green";v = "防水测试";
				} else if (row.testItemID == "1.18") {innerHtml += "green";v = "附配件";
				} else if (row.testItemID == "2") {innerHtml += "blue";v = "白盒测试";
				} else if (row.testItemID == "3") {innerHtml += "blue";v = "硬件测试";
				}else {innerHtml += "";v = "";} 
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},
//		{
//			display : '测试项',
//			name : 'testItemName',
//			align : 'left',
//			width : 180
//			,hide : true
//		},
		{
			display : '问题描述',
			name : 'quesText',
			align : 'left',
			width : 180
		}, {
			display : '不良比例',
			name : 'fractionDefective',
			align : 'Central',
			width : 55,
			
		},  {
			display : '原因分析',
			name : 'quesAnalysis',
			align : 'left',
			width : 180
		},{
			display : '改善措施',
			name : 'quesMeasures',
			align : 'left',
			width : 200,
		},{
			display : '验证说明',
			name : 'idtfRes',
			align : 'left',
			width : 200,
		},{
			display : '责任人',
			name : 'usrName',
			align : 'left',
			width : 100
		},{
			display : '责任部门',
			name : 'deptNm',
			align : 'left',
			width : 100
		},{
			display : '分类',
			name : 'quesTypeID',
			align : 'left',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
//				if (row.quesTypeID == 1) {
//					innerHtml += "green";
//					v = "基带";
//				} else 
				if (row.quesTypeID == 2) {
					innerHtml += "green";
					v = "射频";
				} 
//				else if (row.quesTypeID == 3) {
//					innerHtml += "green";
//					v = "音频";
//				} 
				else if (row.quesTypeID == 4) {
					innerHtml += "green";
					v = "结构";
				} else if (row.quesTypeID == 5) {
					innerHtml += "green";
					v = "外观工艺";
				} else if (row.quesTypeID == 6) {
					innerHtml += "green";
					v = "软件";
				}else if (row.quesTypeID == 7) {
					innerHtml += "green";
					v = "贴片工艺";
				}else if (row.quesTypeID == 8) {
					innerHtml += "green";
					v = "组装工艺";
				}else if (row.quesTypeID == 9) {
					innerHtml += "green";
					v = "物料";
				}else if (row.quesTypeID == 10) {
					innerHtml += "green";
					v = "附配件测试";
				}else if (row.quesTypeID == 11) {
					innerHtml += "green";
					v = "硬件";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		},{
			display : '来源',
			name : 'sourceID',
			align : 'left',
			width : 70,
			render : function(row) {
				var innerHtml = "<font color='";
				var v = '';
				if (row.sourceID == 1) {
					innerHtml += "";
					v = "硬件测试";
				} else if (row.sourceID == 2) {
					innerHtml += "";
					v = "试产组装";
				} else if (row.sourceID == 3) {
					innerHtml += "";
					v = "试产贴片";
				} else if (row.sourceID == 4) {
					innerHtml += "";
					v = "白盒测试";
				}else if (row.sourceID == 5) {
					innerHtml += "";
					v = "整机测试";
				}else if (row.sourceID == 6) {
					innerHtml += "";
					v = "工厂测试";
				}
				innerHtml += "'>" + v + "</font>";
				return innerHtml;
			}
		}, {
			display : '提交时间',
			name : 'createDate',
			align : 'left',
			width : 90
		},{
			display : '验证人',
			name : 'idtfUsr',
			align : 'left',
			width : 70
		},{
			display : '验证日期',
			name : 'idtfDate',
			align : 'left',
			width : 90
		},{
			display : '解决时长(天)',
			name : 'datetimeforques',
			align : 'center',
			width : 80,
		},{
			display : '问题编号',
			name : 'quesId',
			align : 'left',
			width : 100,
		},{
			display : '录入人',
			name : 'createUsr',
			align : 'left',
			width : 120,
			hide : true
		},{
			display : '项目名称',
			name : 'prjtNm',
			align : 'left',
			width : 100,
		}
		/*, {
			display : 'CQ编号',
			name : 'crDefectId',
			align : 'left',
			width : 50,
			hide : true
		},{
			display : 'userId',
			name : 'userId',
			hide : true
		},{
			display : 'flowId',
			name : 'flowId',
			hide : true
		}, {
			display : 'roleDQAId',
			name : 'roleDQAId',
			hide : true
		}, {
			display : '项目进度ID',
			name : 'scheId',
			align : 'left',
			width : 120,
			hide : true
		}, {
			display : '工作任务ID',
			name : 'taskId',
			align : 'left',
			width : 120,
			hide : true
		}**/
		/*,{
			display : '问题标题',
			name : 'title',
			align : 'left',
			width : 120
		}
		
		, {
			display : '要求完成日期',
			name : 'completedDate',
			align : 'left',
			width : 120,
			hide : true
		}
		, {
			display : '阶段名段',
			name : 'schNm',
			align : 'left',
			width : 150,
			hide:true
		}, {
			display : '工作流名称',
			name : 'wfName',
			align : 'left',
			width : 250,
			hide : true
		}**/
		],
		checkbox : true,
		rownumbers : true,
		pageSize : 20,
		url : './WfQues!list.shtml?1=1',
		usePager : true,
		delayLoad : true, // 初始化不加载数据
		width : '99.5%',
		height : '95%',
		isChecked : f_isChecked,
		onCheckRow : f_onCheckRow,
		onCheckRow : f_onCheckRow,
		onDblClickRow : f_onDblClickRow,
		onCheckAllRow : f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	sea();
})

//查找测试项id
function checkTestItemName() {
	gridManager.loadData();
}


function reloadGrid() {
	gridManager.loadData();
}

function rsave() {
	$.ligerDialog.confirm('确定要保存记录？', function(type) {
		if (type) {
			var str = '{';
			str += '"wfRd.wfNo":"' + $("#wfNo").val() + '",';
			str += '"wfRd.quesIds":"' + $("#quesIds").val() + '",';
			str += '"wfRd.wfName":"' + $("#wfName").val() + '",';
			str += '"wfRd.wfDesc":"' + $("#wfDesc").val() + '",';
			str += '"wfRd.planEDate":"' + $("#planEDate").val() + '"';
			str += '}';
			$.post("WfQues!rsav.shtml", JSON.parse(str), function(data) {
				window.parent.savDeal(data);
			}, "text");
		}
	});
}

function saver() {
	$.ligerDialog.confirm('确定要保存记录？', function(type) {
		if (type) {
			var str = '{';
			if ($("#prjtNo").length > 0) {
				str += '"wfQues.prjtNo":"' + prjtNomanager.getValue() + '",';
			}else{
				str += '"wfQues.prjtNo":"' + $("#wfQues_prjtNo").val() + '",';
			}
			if ($("#wfQues_usrName").length > 0) {
					str += '"wfQues.usrName":"' + $("#wfQues_usrName").val() + '",';
			}
			if ($("#wfNo").length > 0) {
				str += '"wfQues.wfNo":"' + $("#wfNo").val() + '",';
			}
			if ($("#wfQues_wfRd").length > 0) {
				str += '"wfQues.wfNo":"' + wfQues_wfRdmanager.getValue() + '",';
		     }
			if ($("#quesId").length > 0) {
				str += '"wfQues.quesId":"' + $("#quesId").val() + '",';
			}
			if ($("#scheId").length > 0) {
				str += '"wfQues.scheId":"' + $("#scheId").val() + '",';
			}
			if ($("#taskId").length > 0) {
				str += '"wfQues.taskId":"' + $("#taskId").val() + '",';
			}
			if ($("#cateId").length > 0) {
				str += '"wfQues.cateId":"' + $("#cateId").val() + '",';
			}
			if ($("#userId").length > 0) {
				str += '"wfQues.userId":"' + $("#userId").val() + '",';
			}
			if ($("#quesLevel").length > 0) {
				str += '"wfQues.quesLevel":"' + $("#quesLevel").val() + '",';
			}
			if ($("#idtfBy").length > 0) {
				str += '"wfQues.idtfBy":"' + $("#idtfBy").val() + '",';
			}
			if ($("#idtfRes").length > 0) {
				str += '"wfQues.idtfRes":"' + $("#idtfRes").val() + '",';
			}
			if ($("#idtfDate").length > 0) {
				str += '"wfQues.idtfDate":"' + $("#idtfDate").val() + '",';
			}
			//```````````
			if ($("#sourceID").length > 0) {
				str += '"wfQues.sourceID":"' + $("#sourceID").val() + '",';
			}
			if ($("#quesTypeID").length > 0) {
				str += '"wfQues.quesTypeID":"' + $("#quesTypeID").val() + '",';
			}
			
			
			if ($("#title").length > 0) {
				str += '"wfQues.title":"' + $("#title").val() + '",';
			}
			if ($("#result").length > 0) {
				str += '"wfQues.result":"'
						+ ($("#result").val()).replace(/\n/g, "<br/>") + '",';
			}
			if ($("#responsibleUID").length > 0) {
				str += '"responsibleUID":"' + $("#responsibleUID").val() + '",';
			}
			if ($("#includeQuesNum").length > 0) {
				str += '"wfQues.includeQuesNum":"'+ $("#includeQuesNum").val()+ '",';
			}
			if ($("#completedDate").length > 0 && $("#completedDate").ligerDateEditor().getValue() != null) {
				str += '"wfQues.completedDate":"'+ $("#completedDate").val()+ '",';
			}
			
			if ($("#testItemName").length > 0 ) {
				str += '"wfQues.testItemName":"'+ $("#testItemName").val()+ '",';
			}
			if ($("#testItemID").length > 0 ) {
				str += '"wfQues.testItemID":"'+ $("#testItemID").val()+ '",';
			}
			if ($("#quesText").length > 0) {
				str += '"wfQues.quesText":"'
						+ ($("#quesText").val()).replace(/\n/g, "").replace(
								/"/g, "'") + '"';
			}
			if ($("#fractionDefective").length > 0) {
				str += '"wfQues.fractionDefective":"' + $("#fractionDefective").val() + '",';
		}
			str += '}';
			$.post("WfQues!sav.shtml", eval('(' + str + ')'), function(data) {
				var s  = data.split(":");
				if(s[0].indexOf("完成") > -1){
					$("#quesId").val(s[1].trim());
					document.getElementById("savBtn").style.display = "none";
					//document.getElementById("closeBtn").style.display = "";
					document.getElementById("uploadBut").disabled=false;
					document.getElementById("uploadBut").className="wfbigbtn2";
					if($("#isFromWf").val() == 0) {
						//window.parent.parent.gridManager.loadData();
						window.parent.win.frame.gridManager.loadData();
					}else if($("#isFromWf").val() == 1){
						window.parent.frames["quesIframe"].location="./WfQues!taskList.shtml?wfNo=" + $("#wfNo").val();
					}
				}
				$.ligerDialog.success(s[0]);
			}, "text");
		}
	});
}
function del() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择删除的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要删除记录？', function(type) {
		if (type) {
			$.post("WfQues!del.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
				reloadGrid();
			}, "text");
		}
	});
}

// 问题转发
function retrans() {
	// alert(checkedCustomer.join(','));
	/**
	 * var str = '{'; str += '"wfQues.quesId":"'+checkedCustomer.join(',')+'"';
	 * str += '}'; wfQues $.post("WfQues!upd.shtml", JSON.parse(str),
	 * function(data) { $.ligerDialog.success(data); }, "text" );
	 */
	var data1 = gridManager.getSelectedRows();
	if (data1.length < 1) {
		$.ligerDialog.warn('请选择问题进行转发。');
		return;
	}
	$.ligerDialog.open({
		title : '问题转发',
		height : 400,
		width : 450,
		url : './WfQues!retrans.shtml' + '?wfQues.quesId='
				+ checkedCustomer.join(',') + '&wfQues.wfNo=' + checkedWfNo
	});

}

function grisk() {
	var data1 = gridManager.getSelectedRows();
	if (data1.length < 1) {
		$.ligerDialog.warn('请选择走风险流程的问题。');
		return;
	}
	var wfNo = '';
	for (var i = 0; i < data1.length; i++) {
		var status = data1[i].status;
		if (status == 30) {
			$.ligerDialog.warn('已关闭,不能走风险流程');
			return;
		}
		if (status == 20) {
			$.ligerDialog.warn('已走风险流程');
			return;
		}
		var createBy = data1[i].createBy;
		var roleDQAId = data1[i].roleDQAId;
		if ($('#sys_usrid')) {
			var sur = $('#sys_usrid').val();
			// if(sur != roleDQAId && sur != createBy) {
			// 只能有问题提出者启动风险流程
			if (sur != createBy) {
				$.ligerDialog.warn('您非本问题[DQA]或[创建人],不能进行此操作');
				return;
			}
		}

		if (wfNo != '' && data1[i].wfNo != wfNo) {
			$.ligerDialog.warn('所选择问题,必须属于同一工作流');
			return;
		}
		wfNo = data1[i].wfNo;
	}
	DialogMgr.create('启动风险流程：WfReply', './WfQues!addrisk.shtml?wfRd.wfNo='
			+ wfNo + '&wfRd.quesIds=' + checkedCustomer.join(','));
	/*
	 * $.ligerDialog.confirm('确定要走风险流程？', function (type) { if(type) {
	 * 
	 * $.post("WfQues!grisk.shtml", {'choices': checkedCustomer.join(',')},
	 * function(data) { $.ligerDialog.success(data); reloadGrid(); }, "text" ); } } );
	 */
}
function can() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择撤消的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要撤消记录？', function(type) {
		if (type) {
			$.post("WfQues!can.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function chk() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择审核的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要审核记录？', function(type) {
		if (type) {
			$.post("WfQues!chk.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function rev() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择复核的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要复核记录？', function(type) {
		if (type) {
			$.post("WfQues!rev.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function con() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择确认的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要确认记录？', function(type) {
		if (type) {
			$.post("WfQues!con.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function sta() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择开始的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要开始记录？', function(type) {
		if (type) {
			$.post("WfQues!sta.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function stp() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择中止的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要中止记录？', function(type) {
		if (type) {
			$.post("WfQues!stp.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function ove() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择完成的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要完成记录？', function(type) {
		if (type) {
			$.post("WfQues!ove.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}
function iss() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择发布的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要发布记录？', function(type) {
		if (type) {
			$.post("WfQues!iss.shtml", {
				'choices' : checkedCustomer.join(',')
			}, function(data) {
				$.ligerDialog.success(data);
			}, "text");
		}
	});
}

function uplTemp() {
	window.location.href = './include/template/question_template.xls';
}

function prn() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择打印的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要打印记录？', function(type) {
		if (type) {
			$.ligerDialog.open({
				title : '打印对话框：WfQues',
				height : 560,
				width : 780,
				url : './WfQues!prn.shtml?choices=' + checkedCustomer.join(',')
			});
		}
	});
}
function dow() {
	var data1 = gridManager.getSelected();
	if (!data1) {
		$.ligerDialog.warn('请选择下载的记录。');
		return;
	}
	$.ligerDialog.confirm('确定要下载记录？', function(type) {
		if (type) {
			$("#form1").attr("action", "WfQues!dow.shtml");
			$("#form1").submit();
		}
	});
}

function downloadOrViewFile(wfQuesId,fileName) {
	var picfixArr = ["jpg","bmp","jpeg","png","gif"];
	var fix = fileName.substring(fileName.indexOf(".") + 1,fileName.length);
	if(picfixArr.indexOf(fix) > -1) {
		window.parent.parent.f_open('./WfQues!viewPic.shtml?wfQues.quesId=' + wfQuesId,"预览");
	}else {
		window.location.href = './WfQues!downloadFile.shtml?wfQues.quesId=' + wfQuesId;
	}
}

// ===============================下拉输入选择框=================================
var oldParam;
var sel;
var txt;
function showtips2(){
	var param = document.getElementById("userName").value;
	txt = document.getElementById("userName");
	sel = document.getElementById("sel")
	var j = 0;
	if (param != null && param!=' ') {
		if(param.length>0 && param.length<5 && oldParam!=param){
			$.ajax({
				 type:"post",  
			       url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
			       dataType:"json",
			       success: function fun1(jsonData) { 
			    	    $("#"+"sel").empty();
			   			$.each(jsonData,function(i,item){
							var dept = "";
							if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
								dept = " -- " + item.deptNm;
							}
							//15*n px;
							if(i>10){
								$("#"+"sel").css("height","200px");
							}else
								{
								$("#"+"sel").css("height", ((i+1)*20) + "px");
							}
							$("#"+"sel").append("<option value='"+item.id+"' onclick='rv()'>" + item.usrName + dept+"</option>");  
						});
			   			if(jsonData.length>0){
							sel.size = (j > 1) ? j : 2;
							sel.size = (sel.size>17) ? 17 :sel.size;
							sel.style.display = '';
						}else{
							c();
						}
			       }
			});
		}
	}else{
		c();
	}
	oldParam = param;
}
function enterTips() {
	sel = document.getElementById("sel");
	txt = document.getElementById("userName")

	//var sel = document.getElementById("sel");
	if(txt.value.length<=0){
		sel.style.display = 'none';
	}
	var code;
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){
		code = event.keyCode;
	}else{
		code = enterTips.caller.arguments[0].which;
	}
	if (sel.style.display != 'none') {
		if (code == 13)
			event.srcElement.value = sel.value, sel.style.display = 'none';
		if (code == 40)
			sel.focus();
	}
}

function rv() {
	sel = document.getElementById("sel");
	txt = document.getElementById("userName")
	sel.style.display = 'none';
    txt.value = sel.options[sel.selectedIndex].text;
    //document.getElementById("userId").value = sel.options[sel.selectedIndex].value;
    var currentid=sel.options[sel.selectedIndex].value;
    var roleid=$("#sel").attr("data-roleId");
    var rowid=$("#sel").attr("data-id");
	oldParam = txt.value;
	c();
}
function c() {
	if(sel!=null){
		sel.style.display = 'none';
	}
}
document.onclick = function() {
	c();
}
//新增问题添加多个附件
var i=0;
function addFile() {
	var sel = document.getElementById("checkDoc");
	var cateId = 0;
	var cateName = "";
	if(sel!=null && sel.checked){
		var cateId = getRadioValue('docCate');
		
		var cateName = document.getElementById("cate"+cateId).value;
	}
	     currRow = document.getElementById("attachtab").insertRow();
	     currRow.id = "attachtabDtl"+ currRow.rowIndex;
	     cellc = currRow.insertCell();
	   	 var delobj = '<input type="file" size="40" id="files'+i+'" name="files['+i+']">&nbsp;&nbsp;'
	   	 	+'<input type="hidden" id="fileCates'+i+'" name="fileCates['+i+']" value="'+cateId+'">'
	   	 	+'<font color="red">'+cateName+'</font>'
	   	 	+"&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"removeFile('attachtab'"+",'"+currRow.id+"');\">删除</a>";
	     cellc.innerHTML=delobj;
//	     alert("---"+delobj);
	     i++;
	
	//鏃犱换鍔￠〉闈㈡椂鍔犱笂浼犳寜閽�
	var submitFileBtn = document.getElementById("submitFileBtn");
	if(submitFileBtn!=null && submitFileBtn.style.display=='none'){
		submitFileBtn.style.display="block";
	}
}
function removeFile(table,id){
	if(table=="oldAttachtab"){
		document.getElementById("delAttach").value=document.getElementById("delAttach").value+","
			+document.getElementById(table).rows[document.getElementById(id).rowIndex].title;
	}
	document.getElementById(table).deleteRow(document.getElementById(id).rowIndex);
	
	//鏃犱换鍔￠〉闈㈡椂鍘绘帀涓婁紶鎸夐挳
	if(document.getElementById("attachtab").rows.length<=0){
		submitFileBtn.style.display="none";
	}
}
//下载问题描述的附件
function downloadOrViewFile(wfQuesId,fileName) {
	var picfixArr = ["jpg","bmp","jpeg","png","gif"];
	var fix = fileName.substring(fileName.indexOf(".") + 1,fileName.length);
	if(picfixArr.indexOf(fix) > -1) {
		window.parent.parent.f_open('./WfQues!viewPic.shtml?wfQues.quesId=' + wfQuesId,"预览");
	}else {
		window.location.href = './WfQues!downloadFile.shtml?wfQues.quesId=' + wfQuesId +'&fileNameStr = '+fileName;
	}
}