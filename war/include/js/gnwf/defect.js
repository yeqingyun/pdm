function f_onCheckAllRow(checked) {
	for ( var rowid in this.records) {
		if (checked)
			addCheckedCustomer(this.records[rowid]['id']);
		else
			removeCheckedCustomer(this.records[rowid]['id']);
	}
}
var checkedCustomer = [];
function findCheckedCustomer(id) {
	for (var i = 0; i < checkedCustomer.length; i++) {
		if (checkedCustomer[i] == id)
			return i;
	}
	return -1;
}
function addCheckedCustomer(id) {
	if (findCheckedCustomer(id) == -1)
		checkedCustomer.push(id);
}
function removeCheckedCustomer(id) {
	var i = findCheckedCustomer(id);
	if (i == -1)
		return;
	checkedCustomer.splice(i, 1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata.id) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data) {
	if (checked) {
		addCheckedCustomer(data.id);
	} else
		removeCheckedCustomer(data.id);
}
function f_onDblClickRow(data) {
	rep(data)
}
function sea() {
	if (gridManager) {
		gridManager
				.setOptions({
					parms : [
							{
								name : 'defect.id',
								value : ($("#id").length > 0) ? $("#id").val() : ''
							},
							{
								name : 'defect.login_name',
								value : ($("#login_name").length > 0) ? $("#login_name").val() : ''
							},
							{
								name : 'defect.prjName',
								value : ($("#prjName").length > 0) ? $("#prjName").val(): ''
							},{
								name : 'defect.submit_date',
								value : ($("#submit_date").length > 0) ? $("#submit_date").val(): ''
							},{
								name : 'defect.state',
								value : ($("#state").length > 0) ? $("#state").val(): ''
							}]
				});
		gridManager.loadData();
	}
}
var gridManager;
$(function() {
	$("#maingrid").ligerGrid({
		columns : [{
			display : 'Id',
			name : 'id',
			align : 'left',
			width : 80
		},{
			display : 'Headline',
			name : 'headline',
			align : 'left',
			width : 200
		},{
			display : 'State',
			name : 'statename',
			align : 'left',
			width : 100
		}, {
			display : 'Owner',
			name : 'login_name',
			align : 'left',
			width : 100
		}, {
			display : 'Project',
			name : 'prjName',
			align : 'left',
			width : 100
		},{
			display : 'Moduleitem',
			name : 'moduleitem',
			align : 'left',
			width : 100
		},{
			display : 'Submit_date',
			name : 'submit_date',
			align : 'left',
			width : 100
		},{
			display : 'Probability',
			name : 'probability',
			align : 'left',
			width : 100
		},{
			display : 'Priority',
			name : 'priority',
			align : 'left',
			width : 100
		}],
		checkbox : true,
		rownumbers : true,
		pageSize : 20,
		url : './CQDefect!list.shtml',
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
function rep(data) {
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
			    "./CQDefect!managerDefect.shtml?defect.id="+data.id,"CQ缺陷视图："+data.id,"./include/images/Alien Folder.png"
			);
		}else if(window.parent.parent.f_open){
			window.parent.parent.f_open('./CQDefect!managerDefect.shtml?defect.id='+ data.id,'CQ缺陷视图:CQDefect');
		}else {
			DialogMgr.create('CQ缺陷视图:CQDefect', './CQDefect!managerDefect.shtml?defect.id='+ data.id);
		}
	}else{
		DialogMgr.create('CQ缺陷视图:CQDefect', './CQDefect!managerDefect.shtml?defect.id='+ data.id);
	}
}