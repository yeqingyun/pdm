<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfDoc.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/oa.js"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js" ></script>
<script type="text/javascript" src="./include/js/gnwf/NextStepPage.js"></script>
<script type="text/javascript" src="./include/cal/WdatePicker.js"></script>

<style type="text/css" rel="stylesheet" >
.titBox1 {background: solid 1px;}
.titBox1 h3{font-size:16px;float:left;color:#515c68;font-weight:700;}
.titBox1 .butonList1 {margin-top:4px;float:right;}
.titBox1 .butonList1 li{float:left;}
.titBox1 .butonList1 li a{line-height:19px;padding:0 10px;text-decoration:none;margin:0 5px;font-size:12px;color:#fff; display:block;height:height:19px;border:#23537d solid 1px; border-radius:3px;background:#3670F2;}
.titBox1 .butonList1 li a:hover{background:#669bca;}
</style>
<script src="./include/js/dowWfDoc.js" type="text/javascript"></script>
<script type="text/javascript">
var gridManager;
$(function () {
	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 178
	});
	
	
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '文档类别', name: 'cateName', align: 'left', width: 160},
			{ display: '文档名称', name: 'docName', align: 'left', width: 260,
				render: function (row){
					if((typeof row.docName)=='undefined')
						return "";
					var innerHtml = "<img align='middle' src='./include/img/workflow/"+row.icon+"'/>"
					+row.docName;
					//	+"<a href='WfDoc!dow.shtml?wfDoc.docId="+row.docId+"'>"+row.docName+"</a>";
					+"<a href='javascript:dowDoc("+row.docId+")'>"+row.docName+"</a>";
					return innerHtml;
				}	
			},
			{ display: '工作流', name: 'wfName', align: 'left', width: 160,
				render: function (row){
					if((typeof row.wfName)=='undefined')
						return "";
					return row.wfName;
				}	},
			{ display: '版本号', name: 'docVer', align: 'center', width: 50 },
			{ display: '状态',  align: 'center', width: 100,
				render: function (row){
					if((typeof row.status)=='undefined')
						return "";
					var v = "未定义";
					if(row.status==-1){
						v ="已删除";
					}
					if(row.status==0){
						v ="无效、作废";
					}
					if(row.status==1){
						v ="有效文档";
					}
					if(row.status==2){
						v ="正在跑归档流程";
					}
					if(row.status==3){
						v ="已归档";
					}
					if(row.status==4){
						v ="正在跑更新流程";
					}
					if(row.status==5){
						v ="已更新";
					}
					if(row.status==6){
						v ="正在跑发布流程";
					}
					if(row.status==7){
						v ="已发布";
					}
					return v;
				}
			},
			{ display: '创建人', name: 'createName', align: 'center', width: 80 },
			{ display: '创建日期', name: 'createDate', align: 'center', width: 100 },
			{ display: '操作',  align: 'center', width: 50,
				render: function (row){
					if((typeof row.status)=='undefined')
						return "";
					var v="";
					if(row.status>0){
						if($("#usrId").val()==row.createBy){//文档上传人才可以更新
							v = v+"<a href='#' onclick='uploadFile(\""+row.projectNo+"\",\""+row.cateId+"\",\""+row.taskId+"\",\""+row.wfNo+"\",\""+row.flowId+"\")'>更新</a>";
						}
					}
					//if(row.status==3){
					//  v =  v+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='openShareWin2(\""+row.docId+"\",\""+row.cateId+"\",\""+row.docVer+"\",\""+row.docName+"\",\""+row.cateName+"\")'>发布</a>";
					//}
					//if(row.status==7){//查看发布, 取消功能
					//	v =  v+"&nbsp;&nbsp;&nbsp;&nbsp;<a href='#' onclick='openShareWin3(\""+row.docId+"\",\""+row.cateId+"\",\""+row.docVer+"\",\""+row.docName+"\",\""+row.cateName+"\")'>发布明细</a>";
					//}
					
					return v;
				}
			}
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfDoc!prjtBaseLibList.shtml?wfDoc.projectNo='+$("#projectNo").val(),
		usePager:true,
		width: '99.5%',
		height:'99%',
		frozen:false,
		isChecked: f_isChecked,
		onCheckAllRow: f_onCheckAllRow,
		detail: { onShowDetail: f_showOrder,height:'auto',align:'middle'}
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	
	$("#flowId,#docCateId").change(function(){
	 	sea();
	});
	$("#docName").keyup(function(){
		sea();
	});
});

$(function(){
	if ($("#docName").length > 0)
		$("#docName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	 $("#flowId").ligerComboBox({url:"SchCfg!getWfs.shtml",  width:186, textField:"flowName", valueField:"flowId", isMultiSelect: false});
	 flowIdmanager = $("#flowId").ligerGetComboBoxManager();
	 
	 $("#docCateId").ligerComboBox({url:"WfDoc!loadDocCates.shtml",  width:186, textField:"docName", valueField:"docId", isMultiSelect: false});
	 docCateIdmanager = $("#docCateId").ligerGetComboBoxManager();
	 
	 //加载完成后,将本页面放到主页面中
	 window.parent.putWin("bdoc",$(window));
});

//定义刷新函数  刷新grid数据
var flush=sea;

//刷新grid数据
function sea() {
	//alert($("#projectNo").val());
	gridManager.setOptions({
		parms: [
			{name: 'wfDoc.docName', value: $("#docName").val()},
				{name: 'wfDoc.cateId', value:docCateIdmanager.getValue()},
				{name: 'wfDoc.flowId', value:flowIdmanager.getValue()}
		]
	});
	gridManager.loadData();
}

//打开 归档窗口 前检查
function piclick(){
	var rows=$("#maingrid").ligerGetGridManager().getSelectedRows();
	if(rows==null||rows.length==0){
		alert("请选择要归档的文档类别");
		return;
	}
	var selectflag = false;
	var str="";
	var arrs = new Array();
	for(var i=0;i<rows.length;i++){
		if((typeof rows[i].fileNo)=='undefined'){
			selectflag=true;
			arrs[arrs.length]=rows[i];
		}else{
			if(str.length>0)
				str+=",";
			str+=rows[i].cateName+"("+rows[i].docVer+")";
		}
	}
	if(str.length>0){
		alert(str+" 已经归档了，不能再归档");
	}
	if(!selectflag){
		alert("请选择要归档的文档类别");
		return ;
	}
	openPigeonholeWin(arrs);
}
//打开归档窗口
function openPigeonholeWin(rows) {
	if(window.parent)
		if(window.parent.openWin) {
			window.parent.openWin({
					url:"./WfDoc!openPigeonholeWin.shtml?tjson="+encodeURIComponent(JSON.stringify(rows))+'&wfDoc.projectNo='+$("#projectNo").val(),
					title:"归档",
					width:600,
					height:400},
					"pigeon"
					);
		}
}
//打开发布窗口
function openShareWin2(docId,cateId,docVer,docName,cateName) {
	if(window.parent)
			if(window.parent.openWin) {
				window.parent.openWin({
				url:"./WfDoc!openShareWin.shtml?wfDoc.docId="+docId+"&wfDoc.cateId="+cateId+"&wfDoc.docVer="+docVer+"&wfDoc.docName="+docName+"&wfDoc.cateName="+cateName+"&wfDoc.projectNo="+$("#projectNo").val(),
				title:"发布",
				width:600,
				height:400
				},"bshara"
			);
		}
}
//打开发布明细窗口
function openShareWin3(docId,cateId,docVer,docName,cateName) {
    var win = window.parent.openWin({
    	url: './WfDoc!openBSharaDetlWin.shtml?wfDoc.docId='+docId+"&wfDoc.cateId="+cateId+"&wfDoc.docVer="+docVer+"&wfDoc.docName="+docName+"&wfDoc.cateName="+cateName+"&wfDoc.projectNo="+$("#projectNo").val(), 
    	title: "发布明细",
    	width: 600, 
    	height: 400,  
    	modal: true
    });
}

//grid 明细
function f_showOrder(row, detailPanel,callback)
{
    var grid = document.createElement('div');
    var div = $("<div align=center width=100%>");
    var cen = $("<center>");
    var table =$("<table   width=100%>");
    var tr=$("<tr>");
    var td=$("<td align=center>");
    div.append(cen);
    cen.append(table);
    table.append(tr);
    tr.append(td);
    td.append(grid);
    $(detailPanel).append(div);
    $(grid).css('margin',10).ligerGrid({
		columns: [
					//{ display: '文档类别', name: 'cateName', align: 'left', width: 160},
					{ display: '文档名称', name: 'docName', align: 'left', width: 290,
						render: function (row){
							if((typeof row.docName)=='undefined')
								return "";
							var innerHtml = "<img align='middle' src='./include/img/workflow/"+row.icon+"'/>"
							+row.docName;
							//	+"<a href='WfDoc!dow.shtml?wfDoc.docId="+row.docId+"'>"+row.docName+"</a>";
							return innerHtml;
						}	
					},
					{ display: '版本号', name: 'docVer', align: 'center', width: 50 },
					{ display: '状态',  align: 'center', width: 100,
						render: function (row){
							if((typeof row.status)=='undefined')
								return "";	
							var v = "未定义";
							if(row.status==-1){
								v ="已删除";
							}
							if(row.status==0){
								v ="无效、作废";
							}
							if(row.status==1){
								v ="有效文档";
							}
							if(row.status==2){
								v ="正在跑归档流程";
							}
							if(row.status==3){
								v ="已归档";
							}
							if(row.status==4){
								v ="正在跑更新流程";
							}
							if(row.status==5){
								v ="已更新";
							}
							if(row.status==6){
								v ="正在跑发布流程";
							}
							if(row.status==7){
								v ="已发布";
							}
							return v;
						}
					},
					{ display: '创建人', name: 'createName', align: 'center', width: 80 },
					{ display: '创建日期', name: 'createDate', align: 'center', width: 100 }
				], isScroll: false, showToggleColBtn: false, width: '625px',
       
       url:'./WfDoc!prjtBaseLib1Dtl.shtml?wfDoc.projectNo='+row.projectNo+"&wfDoc.cateId="+row.cateId,

       showTitle: false, columnWidth: 50 ,usePager:false
        , onAfterShowData: callback,frozen:false
    });  
}
</script>
<script type="text/javascript">

function uploadFile(projectNo,cateId,taskId,wfNo,flowId) {
    var gngile_uploadURL = $('#gngile_upload_URL').val();
	var server_URL = $('#server_URL').val();
	var url = gngile_uploadURL
      /**RUL后面跟的参数 **/
	         +'?syId='+$("#syId").val()    //系统ID
	         +'&syNm='+$("#syNm").val()  //系统名字
	         +'&usrId='+$("#usrId").val() //用户ID
	         +'&usrNm='+$("#usrNm").val() //用户名字
	         +'&diyFolder='+"QuesFile" //文件上传的目录中自定义部分
	         +'&uploadType='+"BaseLib" 
         //上传的是ProcFile，取值只能是ProcFile或者BaseLib
         //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
         +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
	         +'&callBackUrl='+server_URL+"/afterUploadAction!wfAfterUpload.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
         +'&tempParams='+"uploadType:BaseLib,flowid:42,wfDoc.projectNo:"+projectNo+",wfDoc.cateId:"+cateId+',syId:'+$("#syId").val();  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
   
    if(taskId!=null){
   		url+=",wfDoc.taskId:"+taskId;
    }
    if(wfNo!=null){
   		url+=",wfDoc.wfNo:"+wfNo;
    }
    if(flowId!=null){
   		url+=",wfDoc.flowId:"+flowId;
    }
    //alert(url);
    /**RUL后面跟的参数 **/
	url=encodeURI(url);  //encodeURI
	var dialog = $.ligerDialog.open({title:'上传附件', height: 200, width: 470,url:url,isResize: true, name :'dlgUploader',buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ]});

	//监听Dialog关闭事件
	$(".l-dialog-winbtns").click(function(){
		sea();
	});

}
	//发布
	function bsclick() {
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		var rows=$("#maingrid").ligerGetGridManager().getSelectedRows();
		var sdocs = new Array();
		//检查是否选择了文档
		if(rows!=null){
			for(var i=0;i<rows.length;i+=1){
				if(rows[i].docId!=null){
					if(rows[i].status==3){
						sdocs[sdocs.length]=rows[i];
					}
				}
			}
		}
		if(sdocs.length==0){
			alert("请选择已归档文档");
			return;
		}
		var count=0;
		var sustr="";
		var erstr="";
		for(var i=0;i<rows.length;i=i+1){
			$.ajax({
				url : "WfDoc!releaseBaseLib.shtml?selectUsrs="
						+ <%=gnwf.ww.MSG.SHARE_LIB_USR_ID%> + "&wfDoc.docId="
						+ rows[i].docId,
				type : "POST",
				success : function(text) {
					count=count+1;
					if(sustr.length>0){
						sustr+=",";
					}
					sustr+='"'+rows[i-1].cateName+'"';
					sfmsg(count,sustr,erstr);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					count=count+1;
					if(sustr.length>0){
						sustr+=",";
					}
					sustr+='"'+rows[i-1].cateName+'"';
					sfmsg(count,sustr,erstr);
				}
			});
		}
	}
	function sfmsg(count,sustr,erstr){
		var rows=$("#maingrid").ligerGetGridManager().getSelectedRows();
		if(count<rows.length){
			return;
		}
		var st="";
		if(sustr.length>0){
			st+=sustr+" 发布成功<br>"; 
		}
		//if(sustr.length>0){
		//	st+=sfmsg+" 发布成功<br>"; 
		//}
		$.ligerDialog.success(st);//弹出信息
		window.parent.getWin("bdoc")[0].flush();//刷新数据
		window.parent.closeWin("bshara");//关闭本页面
	}
</script>
</head>

<body style="padding:0px;">
	<input type="hidden" id="projectNo" name="wfDoc.projectNo" value='<c:out value="${wfDoc.projectNo}"/>' />
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="center" id="framecenter">
	
	
<div class="l-loading" style="display: block" id="pageloading"></div>

<div id="sform" style="margin:2px;height:30px;">
    
	<table>
		<tr>
		 <td height="24" width="50" >工作流：</td>
			  <td>
			    <input type="text" id="flowId"  size="30"   />
			  </td>
			  <!--  
			  <td height="24" width="60"  >文档类别：</td>
			  <td>
			    <input type="text" id="docCateId"  size="30"  />
			  </td>
			  -->
			<td height="24" width="60"  align="center">文档名称：</td>
			<td><input type="text" id="docName" name="wfDoc.docName"/></td>
			<td>
	            <div class="titBox1">
	                <h3>&nbsp;&nbsp;&nbsp;&nbsp;</h3>
	                <ul class="butonList1">
	                   <li><c:if test="${pigeonBl}"><a name="showWfFormsBtn" id="showWfFormsBtn" href="javascript:piclick()">归档</a></c:if></li>
	                   <li><c:if test="${shareBt}"><a name="showWfFormsBtn" id="showWfFormsBtn" href="javascript:bsclick()">发布</a></c:if></li>
	                </ul>
	            </div>
			</td>
		</tr>
	</table>
</div>
<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

	</div>
</div> 
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>