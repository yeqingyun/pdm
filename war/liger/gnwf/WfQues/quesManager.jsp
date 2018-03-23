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

<script src="./include/js/gnwf/quesVer.js" type="text/javascript"></script>
<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>


<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<style type="text/css">
.showinfo{
  display:none;
  left:1px;
  top:1px;
  width:103px; 
  min-height:10px; 
  padding:5px;
  color:#000088;
  background:#aaffee; 
  position:fixed; 
  z-index:999999; 
  font-family: Arial; 
  border: 2px solid #379082; 
  border-radius: 20px; 
  
 }
</style>
<script type="text/javascript">
var MouseEvent = function(e){
	 this.x = e.pageX
	 this.y = e.pageY
}
var Mouse = function(e){
	var kdheight =  jQuery(document).scrollTop();
	mouse = new MouseEvent(e);
	leftpos = mouse.x-50;
	toppos = mouse.y-kdheight+8; 
}
$(function (){
   	$("#layout1").ligerLayout({
   		topHeight:25,
   		minLeftWidth:80,
   		minRightWidth:80,
   		leftWidth: 178
   	});
   	
   	$("#tree1").ligerTree({ 
   	     url:'./PrjtDef!loadPrjtTreeForQues.shtml', 
           idFieldName :'prjtNo',
           textFieldName :'prjtNm',
           iconFieldName :'iconUri',
           checkbox :false,
           onSelect: function (node, e){
        	   //alert("222");
        	   //alert(node.data.prjtNo); 
        	   //alert(node.data); 
        	    document.getElementById("projectDis").style.display=""; 
        	    
        	    document.getElementById("pNm").innerHTML = node.data.prjtNm; 
        	    
	            var prjtNo = node.data.prjtNo;
	            
	            
	            $.post("PrjtDef!findProject.shtml",{'prjtDef.prjtNo':prjtNo},
   	       			function(data) {
	            	if(data.currentPoint){
	     	        	    document.getElementById("currentPoint").innerHTML = data.currentPoint; 
	     	        	    document.getElementById("currentPointInput").value = data.currentPoint; 
	             	    }else{
	             	    	/*  document.getElementById("currentPoint").innerHTML = ""; 
	      	        	    document.getElementById("currentPointInput").value = "";  */
	             	    }
	             	    	
	             	    if(data.nextPoint){
	     	        	    document.getElementById("nextPoint").innerHTML = data.nextPoint; 
	     	        	    document.getElementById("nextPointInput").value = data.nextPoint; 
	             	    }else{
	             	    	/*  document.getElementById("nextPoint").innerHTML = ""; 
	      	        	     document.getElementById("nextPointInput").value = "";  */
	          	        }
	             	    
	             	    
	             	   
	             	    
   	       			},
   	       			"json"
   	       	      );
	           
	           $("#prjtNo").val(prjtNo);
			   QuesRespUserselect = $("#QuesRespUserId");
	           $.post("WfQues!getUserByPrjtNo.shtml",{'prjtNo':prjtNo},function(data) {
	        	   QuesRespUserselect.empty();
	        	   QuesRespUserselect.append("<option value=''>全部</option>");
	        	  // var flag = false;
	        	   $.each(data,function(i,item) {
	        		    QuesRespUserselect.append("<option value='"+item.id+"'>"+item.usrName+"</option>");
	            	});
	           },"json");
	           $.post("WfQues!findProjectRoleID.shtml",{'prjtNo':prjtNo},
	   	       			function(data) {
					//alert(data);
	        	   //判断是否“不随角色改变”角色，是就全部显示
					if (document.getElementById("gpName").value.indexOf('不随项目变更角色') > -1) {
						document.getElementById("btnspan").style.display = '';
						 document.getElementById("importBtn").style.display = '';//导入解决措施显示
						document.getElementById("importNewQuesBtn").style.display = '';//导入新问题显示
	        			document.getElementById("closeQuesBtn").style.display="";//关闭显示
	        			document.getElementById("gotoRiskBtn").style.display="";//转风险显示
	        			document.getElementById("openQuesBtn").style.display="";//开启显示 
					}
	        	  	 //判断是否该项目组成员，是项目组成员就可以使用以下按钮	        	   
		            	else if(data == 0){
		            		document.getElementById("btnspan").style.display = 'none';
		            		 document.getElementById("importNewQuesBtn").style.display = 'none';//导入新问题隐藏
		            		document.getElementById("importBtn").style.display = 'none';//导入解决措施隐藏
	        			   document.getElementById("closeQuesBtn").style.display="none";//关闭隐藏
	        			   document.getElementById("gotoRiskBtn").style.display="none";//转风险隐藏
	        			   document.getElementById("openQuesBtn").style.display="none";//开启隐藏 
		            	}else if(data ==19){//DQA
		            		document.getElementById("btnspan").style.display = '';
		            		 document.getElementById("importBtn").style.display = '';//导入解决措施显示
								document.getElementById("importNewQuesBtn").style.display = '';//导入新问题显示
			        			document.getElementById("closeQuesBtn").style.display="";//关闭显示
			        			document.getElementById("gotoRiskBtn").style.display="";//转风险显示
			        			document.getElementById("openQuesBtn").style.display="";//开启显示 
		            	}else if(data ==5||data ==63){//项目经理||PQE工程师  
		            		document.getElementById("btnspan").style.display = '';
		            		 document.getElementById("importBtn").style.display = 'none';//导入解决措施隐藏
								document.getElementById("importNewQuesBtn").style.display = '';//导入新问题显示
			        			document.getElementById("closeQuesBtn").style.display="";//关闭显示
			        			document.getElementById("gotoRiskBtn").style.display="";//转风险显示
			        			document.getElementById("openQuesBtn").style.display="";//开启显示 
		            	}else if(data ==6){//NPI工程师  6
		            		document.getElementById("btnspan").style.display = '';
		            		 document.getElementById("importNewQuesBtn").style.display = '';//导入新问题显示
			            		document.getElementById("importBtn").style.display = '';//导入解决措施显示
		        			   document.getElementById("closeQuesBtn").style.display="none";//关闭隐藏
		        			   document.getElementById("gotoRiskBtn").style.display="none";//转风险隐藏
		        			   document.getElementById("openQuesBtn").style.display="none";//开启隐藏 
		            	}else {
		            		document.getElementById("btnspan").style.display = '';
		            		 document.getElementById("importNewQuesBtn").style.display = '';//导入新问题显示
			            		document.getElementById("importBtn").style.display = 'none';//导入解决措施隐藏
		        			   document.getElementById("closeQuesBtn").style.display="none";//关闭隐藏
		        			   document.getElementById("gotoRiskBtn").style.display="none";//转风险隐藏
		        			   document.getElementById("openQuesBtn").style.display="none";//开启隐藏 
		            	}	
	   	       			},
	   	       			"json"
	   	       	      ); 
	           createByselect = $("#createBy");
	         //  $.post("WfQues!getCreatUserByPrjtNo.shtml",{'prjtNo':prjtNo},function(data) {
	        	  $.post("WfQues!getUserByPrjtNo.shtml",{'prjtNo':prjtNo},function(data) {
	        	   createByselect.empty();
	        	   createByselect.append("<option value=''>全部</option>");
	        	   $.each(data,function(i,item) {
	        		   createByselect.append("<option value='"+item.id+"'>"+item.usrName+"</option>");
	            	});
	           },"json");
	          
	           $.post("WfQues!getQuesCountByPrjtNo.shtml",{'prjtNo':prjtNo},
	       			function(data) {
	        	   if(data[0]){
		        	    document.getElementById('quesSum').innerHTML = data[0].quesOpen+data[0].quesClose+data[0].quesRisk;
		        	    document.getElementById('quesOpen').innerHTML = data[0].quesOpen;
		        	    document.getElementById('quesClose').innerHTML = data[0].quesClose;
		        	    document.getElementById('quesRisk').innerHTML = data[0].quesRisk;
	        	   }else{
	        		   document.getElementById('quesSum').innerHTML = 0;
		        	    document.getElementById('quesOpen').innerHTML = 0;
		        	    document.getElementById('quesClose').innerHTML = 0;
		        	    document.getElementById('quesRisk').innerHTML = 0;
	        	   }
	       			},
	       			"json"
	       			);
	         
	   	       sea();
           }
	      
   	});
   	
   	$("#importBtn").hover(function(e) {
		Mouse(e);
		$(".showinfo").css({top:toppos,left:leftpos}).fadeIn(100);
	},function() {
		$(".showinfo").hide();
	});
   	
	$("#typId").ligerComboBox({ url:"SchCfg!getTyps.shtml", textField:'typNm', valueField:'typId', isMultiSelect: false,
		onSelected: function (newvalue)
		{
			$.post("SchCfg!getPreSchCfgs.shtml",
			{'schCfg.typId':newvalue},
			function(data) {
				$("#scheId").ligerGetComboBoxManager().setData(data);
			},
			"json"
			);
		}
		});
	
	
	$("#scheId").ligerComboBox({textField:'schNm', valueField:'schId', isMultiSelect: false,selectBoxWidth: 100,  width : 106,
		onSelected:function(value,text) {
			/* sea();
			if(text.indexOf('小批试产') > -1 || text.indexOf('中批试产') > -1) {
				if(document.getElementById("gpName").value.indexOf('DQA') > -1 || document.getElementById("gpName").value.indexOf('项目经理') > -1){
					document.getElementById("importBtn").style.display = '';
				}
			}else {
				document.getElementById("importBtn").style.display = 'none';
			} */
		}});
	
	
	//测试项data
	var testItemData = [];
	testItemData.push({id: 0,pid:1,text: '全部'});
	testItemData.push({id: 10000,pid:0,text: '可靠性测试'});
		testItemData.push({id: 200,pid:10000,text: '电性能'});testItemData.push({id: 300,pid:10000,text: '软件功能'});
		testItemData.push({id: 400,pid:10000,text: '用户试用'});testItemData.push({id: 500,pid:10000,text: '场地测试'});
		testItemData.push({id: 600,pid:10000,text: 'EMC'});
		testItemData.push({id: 700,pid:10000,text: '环境测试'});
			testItemData.push({id: 701,pid:700,text: '低温储存'});testItemData.push({id: 702,pid:700,text: '低温工作'});
			testItemData.push({id: 703,pid:700,text: '高温储存'});testItemData.push({id: 704,pid:700,text: '高温高湿'});
			testItemData.push({id: 705,pid:700,text: '温度冲击'});testItemData.push({id: 706,pid:700,text: '湿热循环'});
			testItemData.push({id: 707,pid:700,text: '防尘'});testItemData.push({id: 708,pid:700,text: '盐雾'});
			testItemData.push({id: 709,pid:700,text: '紫外线照射'});testItemData.push({id: 710,pid:700,text: '铁屑'});
			testItemData.push({id: 711,pid:700,text: '抽真空试验'});
		testItemData.push({id: 800,pid:10000,text: '机械强度'});
			testItemData.push({id: 801,pid:800,text: '跌落'});testItemData.push({id: 802,pid:800,text: '软压'});
			testItemData.push({id: 803,pid:800,text: '静压'});testItemData.push({id: 804,pid:800,text: '随机振动'});
			testItemData.push({id: 805,pid:800,text: '冲击'});testItemData.push({id: 806,pid:800,text: '滚筒跌落_0.5米'});
			testItemData.push({id: 807,pid:800,text: '滚筒跌落_1米'});testItemData.push({id: 808,pid:800,text: '扭力'});
			testItemData.push({id: 809,pid:800,text: '微跌'});testItemData.push({id: 810,pid:800,text: '按键拉拔力'});
			testItemData.push({id: 811,pid:800,text: '胶塞拉力'});testItemData.push({id: 812,pid:800,text: '焊盘拉力'});
			testItemData.push({id: 813,pid:800,text: '数据线接口压力'});testItemData.push({id: 814,pid:800,text: '按键区域静压试验'});
			testItemData.push({id: 815,pid:800,text: '数据线吊重试验'});
		testItemData.push({id: 900,pid:10000,text: '寿命试验'});
			testItemData.push({id: 901,pid:900,text: '翻盖寿命'});testItemData.push({id: 902,pid:900,text: '滴水翻盖寿命'});
			testItemData.push({id: 903,pid:900,text: '旋盖寿命'});testItemData.push({id: 904,pid:900,text: '滑盖寿命'});
			testItemData.push({id: 905,pid:900,text: '负重滑盖寿命'});testItemData.push({id: 906,pid:900,text: '机械按键寿命'});
			testItemData.push({id: 907,pid:900,text: '波动开关寿命'});testItemData.push({id: 908,pid:900,text: 'SIM卡插拔寿命'});
			testItemData.push({id: 909,pid:900,text: '外置内存卡插拔'});testItemData.push({id: 910,pid:900,text: '耳机插孔插拔'});
			testItemData.push({id: 911,pid:900,text: '数据接口插拔'});testItemData.push({id: 912,pid:900,text: '电池插拔'});
			testItemData.push({id: 913,pid:900,text: '电池盖插拔'});testItemData.push({id: 914,pid:900,text: 'SPK寿命'});
			testItemData.push({id: 915,pid:900,text: '马达振动寿命'});testItemData.push({id: 916,pid:900,text: 'USB直插头摇摆寿命'});
			testItemData.push({id: 917,pid:900,text: '底壳滑动装饰件寿命'});testItemData.push({id: 918,pid:900,text: '出点按压寿命'});
			testItemData.push({id: 919,pid:900,text: '螺母扭力测试'});testItemData.push({id: 920,pid:900,text: '螺钉防松测试'});
		testItemData.push({id: 1000,pid:10000,text: '外观工艺'});
			testItemData.push({id: 1001,pid:1000,text: '工艺耐磨'});testItemData.push({id: 1002,pid:1000,text: '工艺硬度'});
			testItemData.push({id: 1003,pid:1000,text: '镜面/镜片硬度'});testItemData.push({id: 1004,pid:1000,text: '附着力试验'});
			testItemData.push({id: 1005,pid:1000,text: '人工汗液'});testItemData.push({id: 1006,pid:1000,text: '丝印耐酒精试验'});
			testItemData.push({id: 1007,pid:1000,text: '振动耐磨'});testItemData.push({id: 1008,pid:1000,text: '水煮试验'});
			testItemData.push({id: 1009,pid:1000,text: '钢丝绒摩擦'});testItemData.push({id: 1010,pid:1000,text: '主标贴丝印试验'});
			testItemData.push({id: 1011,pid:1000,text: '弯折试验'});testItemData.push({id: 1012,pid:1000,text: '耐化妆品染色试验'});
			testItemData.push({id: 1013,pid:1000,text: '摄像头镜片透过率'});testItemData.push({id: 1014,pid:1000,text: '摄像头镜片水滴角度'});
			testItemData.push({id: 1015,pid:1000,text: '摄像头镜片钢丝绒水滴角度'});testItemData.push({id: 1016,pid:1000,text: '摄像头镜片橡皮摩擦水滴角度'});
		testItemData.push({id: 1100,pid:10000,text: '温升'});
			testItemData.push({id: 1101,pid:1100,text: '通话状态温升'});testItemData.push({id: 1102,pid:1100,text: '摄像状态温升'});	
			testItemData.push({id: 1103,pid:1100,text: '游戏状态温升'});testItemData.push({id: 1104,pid:1100,text: '播放3D动画温升'});	
		testItemData.push({id: 1200,pid:10000,text: '工作电流'});
			testItemData.push({id: 1201,pid:1200,text: '待机电流'});testItemData.push({id: 1202,pid:1200,text: '飞航模式'});
			testItemData.push({id: 1203,pid:1200,text: '通话电流'});testItemData.push({id: 1204,pid:1200,text: 'LCD显示电流'});
			testItemData.push({id: 1205,pid:1200,text: '播放MP3电流'});testItemData.push({id: 1206,pid:1200,text: 'FM电流'});
			testItemData.push({id: 1207,pid:1200,text: '视频电流'});testItemData.push({id: 1208,pid:1200,text: '数据传输电流'});
			testItemData.push({id: 1209,pid:1200,text: '摄像电流'});testItemData.push({id: 1210,pid:1200,text: '游戏电流'});
			testItemData.push({id: 1211,pid:1200,text: '关机漏电流'});
		testItemData.push({id: 1300,pid:10000,text: '充电及电量识别精度测试'});
			testItemData.push({id: 1301,pid:1300,text: '充电电压检测精度'});testItemData.push({id: 1302,pid:1300,text: '充电波形检测'});
			testItemData.push({id: 1303,pid:1300,text: '充电功能试验'});testItemData.push({id: 1304,pid:1300,text: '低温充电电量测试'});
			testItemData.push({id: 1305,pid:1300,text: '高温充电电量测试'});testItemData.push({id: 1306,pid:1300,text: '手机连接接口电气性能测试'});
			testItemData.push({id: 1307,pid:1300,text: '高温电池安全性测试'});testItemData.push({id: 1308,pid:1300,text: '开机电量识别精准度测试'});
			testItemData.push({id: 1309,pid:1300,text: '关机充电电量测试'});
		testItemData.push({id: 1400,pid:10000,text: '整机功能性能'});testItemData.push({id: 1500,pid:10000,text: '音频'});
		testItemData.push({id: 1600,pid:10000,text: '显示拍照效果'});testItemData.push({id: 1700,pid:10000,text: '天线暗室'});
		testItemData.push({id: 1800,pid:10000,text: '防水测试'});testItemData.push({id: 1900,pid:10000,text: '附配件'});
	testItemData.push({id: 20000,pid:0,text: '白盒测试'});testItemData.push({id: 30000,pid:0,text: '硬件测试'});
	
    $("#treeTestItem").ligerComboBox({
        width : 106, 
        selectBoxWidth: 200,
        selectBoxHeight: 180, valueField: 'text',
        tree: {data: testItemData,checkbox: true, idFieldName :'id',isExpand: false, slide : false,
            parentIDFieldName :'pid',
         onClick:function(testItemData,target){
        	var testItemID = $("#treeTestItem").ligerComboBox().getValue();
        	testItemID = testItemID.replace(/;/g,"','");
        	testItemID = "'"+testItemID+"'"
        	if($("#testItemName").val() == testItemID) {
        		$("#testItemName").val("");
        	}else {
        		//alert(testItemID);
        		$("#testItemName").val(testItemID);
        	}
        } 
        }
    });
    
	
	$("#completedDate").ligerDateEditor({labelWidth: 200,format: "yyyy-MM-dd"});
	$("#idtfDate").ligerDateEditor({labelWidth: 135,format: "yyyy-MM-dd"});
	var data = [];
	data.push({id: 8,status:200,text: 'ALL'});
	/*  data.push({'children':[{id: 1,status:1,text: '待解决'},{id: 2,status:9,text: '验证未通过'},
		{id: 3,status:10,text: '待验证'},{id: 4,status:11,text: '验证通过'}],
		id:9,status:100,text:'Open'});  */    
	/* data.push({id: 5,status:21,text: '已挂起'}); */
	data.push({id: 9,status:100,text: 'Open'});
	data.push({id: 6,status:30,text: 'Close'});
	data.push({id: 7,status:40,text: 'Risk'});
    $("#treeStatus").ligerComboBox({
        width : 106, 
        selectBoxWidth: 100,
        selectBoxHeight: 180, valueField: 'text', treeLeafOnly: false,
        tree: {data: data,checkbox: false, onClick:function(data,target){
        	
        	if($("#status").val() == data.data.status) {
        		$("#status").val("");
        	}else {
        		$("#status").val(data.data.status);
        	}
        }}
    });
    
    
    var data1 = [];
	data1.push({id: 1,status:200,text: '全部'});
	data1.push({id: 2,status:30,text: '已关闭'});
	data1.push({id: 3,status:40,text: '转风险'});
	data1.push({id: 4,status:1,text: '待解决'});
	data1.push({id: 8,status:8,text: '退回'});
	data1.push({id: 5,status:9,text: '验证未通过'});
	data1.push({id: 6,status:10,text: '待验证'});
	data1.push({id: 7,status:11,text: '验证通过'});
   
	$("#treeStatus1").ligerComboBox({
			width : 106,
			selectBoxWidth : 100,
			selectBoxHeight : 180,
			valueField : 'text',
			treeLeafOnly : false,
			tree : {
				data : data1,
				checkbox : false,
				onClick : function(data, target) {
					if ($("#status").val() == data.data.status) {
						$("#status").val("");
					} else {
						$("#status").val(data.data.status);
					}
				}
			}
		});
	}
	);
	
	 var dialog33;
	 function bathUpdateQuesUsrs(){
	 	if($("#prjtNo").val() == '') {
	 		$.ligerDialog.warn("请先选择项目");
	 		return;
	 	}
	 	if(!verification('bathUpdateQuesUsrs')) {
	 		return;
	 	}
	 	$("#updateQuesIds").val(checkedCustomer.join(','));
	 	dialog33 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#prjtNo").val()+'&isFromWf=0'});
	 }
	  
	 function gotoRisk() {
			if (!verification('gotoRisk')) {
				return;
			}
			window.parent.f_open('./WfQues!managerQuesGoRiskTask.shtml?wfQues.quesId=' + gridManager.getSelectedRows()[0].quesId);
		}

	//批量关闭问题弹出填写关闭原因页面
	
	 function bathCloseQues() {
			 if(!verificationByClose('closeQues')) {
			return;
			} 
			var str = '{';
			str += '"wfQuesIDs":"' + checkedCustomer.join(',');
			str += '}';
			window.parent.f_open('./WfQues!batchManagerCloseQuesReason.shtml?wfQuesIDs='+checkedCustomer.join(','));
		} 
	
	//开启问题
	function bathOpenQues() {
		if(!verification('openQues')) {
			return;
		}
		 $.post("WfQues!bathOpenQues.shtml", {
			'choices' : checkedCustomer.join(',')
		}, function(data) {
			$.ligerDialog.success(data);
			//sea();
			relodeQuesManager()
		}, "text"); 
	}
	
	 //提交任务成功后刷新问题管理
	function relodeQuesManager() {
			if(window.parent.quesManagerWinow){
				window.parent.quesManagerWinow.frame.gridManager.loadData();
			}
	}
	
	function saveProjPoint() {
		$.ligerDialog.confirm('确定要保存项目节点？', function(type) {
			if (type) {

				var str = '{';
				if ($("#currentPointInput").length > 0) {
					str += '"prjtDef.currentPoint":"'
							+ $("#currentPointInput").val() + '",';
				}
				if ($("#nextPointInput").length > 0) {
					str += '"prjtDef.nextPoint":"' + $("#nextPointInput").val()
							+ '",';
				}
				if ($("#prjtNo").length > 0) {
					str += '"prjtDef.prjtNo":"' + $("#prjtNo").val() + '"';
				}
				str += '}';

				$.post("PrjtDef!saveProjPoint.shtml", JSON.parse(str),
						function(data) {
							alert(data.msg);
						}, "json");
			}
		});
		
	}
	function setUsrData(usrId, usrName) {
		 /*var str = '{';
		str += '"updateQuesIds":"' + $("#updateQuesIds").val() + '",';
		str += '"wfQues.usrName":"' + usrName + '",';
		str += '"responsibleUID":"' + usrId + '"';
		str += '}';
		 $.post("WfQues!bathUpdateResponers.shtml", eval('(' + str + ')'),
				function(data) {
					$.ligerDialog.success(data, "提示", function() {
					});
					if(window.parent.quesWinow){
						window.parent.quesWinow.frame.gridManager.loadData();
					}
					if(window.parent.quesManagerWinow){
						window.parent.quesManagerWinow.frame.gridManager.loadData();
					}
				}, "text");*/
		
	    var oAjax = null;
	    if(window.XMLHttpRequest){
	        oAjax = new XMLHttpRequest();
	    }else{
	        oAjax = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    oAjax.open("POST","WfQues!bathUpdateResponers.shtml",true);
	    oAjax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	    oAjax.send("updateQuesIds=" + $("#updateQuesIds").val() + "&wfQues.usrName=" + usrName + "&responsibleUID=" + usrId);
	    
	    oAjax.onreadystatechange = function(){  //OnReadyStateChange事件
	        if(oAjax.readyState == 4){  //4为完成
	            if(oAjax.status == 200){    //200为成功
	            	$.ligerDialog.success('保存记录完成。');
	    			if(window.parent.quesWinow){
	    				window.parent.quesWinow.frame.gridManager.loadData();
	    			}
	    			if(window.parent.quesManagerWinow){
	    				window.parent.quesManagerWinow.frame.gridManager.loadData();
	    			}
	            }else{
	            	$.ligerDialog.error('保存记录失败。');
	            }
	        }
	    };
	    
	}
</script>
</head>


 <style type="text/css">
   .mt {
	    border-bottom: 1px dotted #EED97C;
	    height: 25px;
	    line-height: 25px;
	    padding: 4px 8px;
	}
 </style>

<body style="padding:0px;">

    <div class="mt" id ="projectDis" style="display:none;">
	 <strong>
	    <label style= "font-weight:bold;">项目名称: </label> <label id = "pNm"></label> 
	    
	    <label style="font-weight:bold;margin-left: 30px">问题数：</label>
	    <label id = "quesSum" ></label> 
	    <label >（Open</label> 
	    <label id = "quesOpen" ></label>，
	    
	    <label>Close</label> 
	    <label id = "quesClose" ></label>，
	    
	    <label>Risk</label> 
	    <label id = "quesRisk" ></label>）
	    
	    <!-- <label style="font-weight:bold;margin-left: 30px">当前节点：</label> 
	    <label id = "currentPoint" style="display:none;"></label>
	    <input id="currentPointInput" name="currentPointInput" style="display:none;width:110px"/>
	    
	    <label style="font-weight:bold;margin-left: 30px">下一个节点：</label> 
	    <label id = "nextPoint" style="display:none;"></label>
	    <input id="nextPointInput" name="nextPointInput" style="display:none;width:110px"/>
	    
	    <input id="savePointBtn" type="button" value="保存" class="wfbigbtn2"  onclick="saveProjPoint();" style="margin-left: 10px" />
	       -->
     </strong>
	</div>

    <div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
			<div id="toolbar"></div>
			<div id="sform" style="margin:10px;height:60px;">
				<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
				<input type="hidden" id="prjtNo"  name = "prjtNo" value="<c:out value="${prjtNo}"/>"/>
				
				<input type="hidden" id="isRiskQues"  name = "isRiskQues" value="<c:out value="${wfQues.isRiskQues}"/>"/>
				<input type="hidden" id="userName"  name="userName" value="<c:out value="${sessionScope.SYUSR.usrName}"/>" />
				<input type="hidden" id="taskCount"/>
				<%-- <input type="hidden" id="gpName" name="gpName" value="<c:out value="${gp.gpName}"/>" /> --%>
				<input type="hidden" id="gpName"  name ="gpName" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>"/>
				<input type="hidden" id="fromQuesManger" name="fromQuesManger"  value="fromQuesManger" />
				<input type="hidden" id="updateQuesIds" name="updateQuesIds" size="30"/>
				
				
				<input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
				<input type="hidden" id="wfQues_usrName" name="wfQues_usrName" />
				<table>
				
				
				
				
					<tr>
					   <!-- 
						
						<td height="5%" width="8%" align="left">问题标题：</td>
						<td height="24" width="90" align="left">
							<input id="questitle" name="wfQues.title" style="width:80px"/>
						</td> -->
						
						 <td height="24" width="70" align="left">最终状态：</td>
						<td >
							<input type="text" id="treeStatus"/>
							<input type="hidden" id="status" name="wfQues.status"/>
						</td> 
						
						
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 解决状态：</label></td>
						<td >
							<input type="text" id="treeStatus1"/>
							<input type="hidden" id="status1" name="wfQues.status1"/>
						</td>
						
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 阶段：</label></td>
						<td >
						
					
							<%-- <select id="scheId" name="wfQues.scheId" style="width:100px">
								<option value="">全部</option>
								<c:forEach items="${schCfgs}" var="schCfg">
									<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
								</c:forEach>
							</select> --%>
							
							
							<select id="scheId" name="wfQues.scheId" style="width: 90px;">
							    <option value="">请选择</option>
								<option value="23">小批试产(T1)</option>
								<!-- <option value="44">小批试产(T1-1)</option>
								<option value="45">小批试产(T1-2)</option> -->	
								<option value="28">小批试产(T2)</option>
								<option value="52">小批试产(T3)</option>
								<!-- <option value="46">小批试产(T2-1)</option>
								<option value="47">小批试产(T2-2)</option> -->
								<option value="48">中批试产(PP)</option>
								<!-- <option value="49">中批试产(PP-1)</option>
								<option value="50">中批试产(PP-2)</option> -->
								<option value="51">批量(MP)</option>
							</select>
							
						</td>
						
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 责任人：</label></td>
						<td >
							 <select id="QuesRespUserId" name="QuesRespUserId" style="width: 106px;">
							</select>
						</td>
						
						
					</tr>
					
					
					
					
					
					<tr>
						<td height="24" width="70" align="left">来源：</td>
						<td >
							<select id="sourceID" name="wfQues.sourceID" style="width: 106px;">
								<option value="">请选择</option>
								 <option value="1">硬件测试</option> 
								<option value="2" >试产组装</option>
								<option value="3">试产贴片</option>
								<option value="4">白盒测试</option>
								<option value="5">整机测试</option>
								<option value="6">工厂测试</option>
							</select>
						</td>
						
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 分类：</label></td>
						<td >
							<select id="quesTypeID" name="wfQues.quesTypeID" style="width: 106px;">
								<option value="">请选择</option>
								<!--  <option value="1">基带</option>  -->
								<option value="2" >射频</option>
								<!-- <option value="3">音频</option> -->
								<option value="4">结构</option>
								<option value="5">外观工艺</option>
								<option value="6">软件</option>
								<option value="7">贴片工艺</option>
								<option value="8">组装工艺</option>
								<option value="9">物料</option>
								<option value="10">附配件测试</option>
								<option value="11">硬件</option>
							</select>
						</td>
						
						
						<!-- <td height="24" width="100" align="left">要求完成日期：</td>
						<td><input type="text" id="completedDate" name="wfQues.completedDate"/></td> -->
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 等级：</label></td>
						<td >
							<select id="cateId" name="wfQues.cateId" style="width: 106px;">
								<option value="">全部</option>
								<!--  <option value="1">S</option>  -->
								<option value="2">A</option>
								<option value="3">B</option>
								<option value="4">C</option>
							</select>
						</td>
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 提交人：</label></td>
						<td >
							<select id="createBy" name="wfQues.createBy" style="width: 106px;">
							</select>
						</td>
						
					</tr>
					
					<tr>
						<td height="24" width="70" align="left">责任部门：</td>
						<td >
							<input id="deptNm" name="wfQues.deptNm" style="width: 103px;"/>
						</td>
						
						<td height="24" width="70" align="left"> <label style="margin-left: 10px"> 问题描述：</label></td>
						<td >
							<input id="quesText" name="wfQues.quesText" style="width: 103px;"/>
						</td>
						
						<td height="24" width="70" align="left"><label style="margin-left: 10px">问题编号：</label></td>
						<td>
							<input id="quesId" name="wfQues.quesId" style="width: 103px;"/>
						</td>
							 <td height="24" width="70" align="left">测试项：</td>
						<td >
							<input type="text" id="treeTestItem"/>
							<input type="hidden" id="testItemName" name="wfQues.testItemName"/>
							<input type="hidden" id="testItemID" name="wfQues.testItemID"/>
						</td> 

							<!-- <td height="24" width="90" align="left">CQ编号：</td>
						<td height="24" width="90" align="left">
							<input id="crDefectId" name="wfQues.crDefectId" style="width:80px"/>
						</td> -->
						<!-- <td height="24" width="90" align="left">问题类别：</td>
						<td >
							<select id="category" name="wfQues.category" style="width:135px">
								<option value="0">全部</option>
								<option value="1">CQ问题</option>
								<option value="2">PDM问题</option>
							</select>
						</td> 
						
						<td height="24" width="110" align="left">是否带流程：</td>
						<td >
							<select id="selType" name="wfQues.selType" style="width:115px">
								<option value="1">全部</option>
								<option value="2">带流程问题</option>
								<option value="3">不带流程问题</option>
								
							</select>
						</td>
						
						-->
					</tr>
					
					<tr>
						<td height="24" width="120" align="left" colspan="7">
						 	<input id="queryBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
						 	 <span id="btnspan" style="display: none"> 
						 	&nbsp;&nbsp;<input id="addBtn" type="button" value=" 新增" class="wfbigbtn2"  onclick="add(0);"  />
						 	&nbsp;&nbsp;<input id="ediBtn" type="button" value=" 编辑" class="wfbigbtn2"  onclick="editQues();" />
						 	&nbsp;&nbsp;<input id="updateQuesUsersBtn" type="button" value=" 转交" class="wfbigbtn2"  onclick="bathUpdateQuesUsrs();"  />
						 	&nbsp;&nbsp;<input id="upBtn" type="button" value=" 导出"  class="wfbigbtn2" onclick="exp();"  />
						 	&nbsp;&nbsp;<input id="importNewQuesBtn" style="display: none" type="button" value=" 批量导入问题" class="wfbigbtn2"  onclick="importNewQues();" />
						 	
						 	</span>
						 	<!-- 导入解决措施.为DQA角色所有 -->
						 	&nbsp;&nbsp;<input id="importBtn" style="display: none" type="button" value=" 导入更新措施" class="wfbigbtn2"  onclick="impSovleQuesByDQA();" />
						 	<div id="prompt" class="showinfo">批量导入带有问题编号的问题解决措施</div>
						 	<!-- 关闭 ,开启,转风险.为DQA或项目经理或PQE工程师角色所有 -->
						 	&nbsp;&nbsp;&nbsp;&nbsp;
						 	&nbsp;&nbsp;<input id="closeQuesBtn" style="display: none" type="button" value=" 关闭"  class="wfbigbtn2" onclick="bathCloseQues();" />
						 	 &nbsp;&nbsp;<input id="openQuesBtn"  type="button" value=" 开启"  class="wfbigbtn2" onclick="bathOpenQues();" style="display:none;" />  
							&nbsp;&nbsp;<input id="gotoRiskBtn" type="button" value=" 转风险" class="wfbigbtn2"  onclick="gotoRisk();"  style="display:none;"/>
							<!-- 
							&nbsp;&nbsp;<font color='green'>OPEN个数：</font> <input id="quesOpen" name="wfQues.quesOpen" style="width:30px" readonly="readonly"/> 
							&nbsp;&nbsp;<font color='blue'>CLOSE个数：</font><input id="quesClose" name="wfQues.quesClose" style="width:30px" readonly="readonly"/> 
							&nbsp;&nbsp;<font color='red'>走风险个数：</font> <input id="quesRisk" name="wfQues.quesRisk" style="width:30px" readonly="readonly"/> 
							 -->
							<!-- &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="check" onclick="seltype('1');" checked="checked"/><font color="blue">全部问题</font>
							&nbsp;&nbsp;<input type="radio" name="check" onclick="seltype('2');"/><font color="blue">带流程问题</font>
							&nbsp;&nbsp;<input type="radio" name="check" onclick="seltype('3');"/><font color="blue">不带流程问题</font> -->
						 </td>
					</tr>
				</table>
			</div>
			<br/><br/>
			<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
			</form>
</div>

<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>