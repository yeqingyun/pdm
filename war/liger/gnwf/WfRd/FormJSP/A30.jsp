<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>


<%-- <%@ page import="java.util.List"%>
<%@ page import="gnwf.vo.WfRdField"%> --%>
<%--  <script language="JavaScript" src="./include/js/gnwf/WfRd_special.js"></script> 
--%>
     <%@ include file="../IncudeExtendField.jsp"%>  
<%--  <%@ include file="/ZrPrjt/war/liger/gnwf/WfRd/impdlgForWorkFlow.jsp"%> --%>
 
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title> 

<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
 <script src="./include/js/Xmlhttp.js" type="text/javascript"></script> 

<script src="./include/js/gnwf/WfRd_special.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script type="text/javascript">
//针对IE8不支持maxlength属性所做的修补
$(function(){
	
    //IE也能用textarea
    $("textarea[maxlength]").keyup(function(){

    var area=$(this);

    var max=parseInt(area.attr("maxlength"),10); //获取maxlength的值

    if(max>0){

    if(area.val().length>max){ //textarea的文本长度大于maxlength

    area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值

    }

    }

    });

    //复制的字符处理问题

    $("textarea[maxlength]").blur(function(){

    var area=$(this);

    var max=parseInt(area.attr("maxlength"),10); //获取maxlength的值

    if(max>0){

    if(area.val().length>max){ //textarea的文本长度大于maxlength

    area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值

    }

    }

    });

});
    
function A30fun1(from){
	try {
		if(from.value!=null && from.value!=null){
			var tr = from.parentNode.parentNode;
			var xmlhttp = sendXmlhttpRequest("GET","WfRdView!showAjaxInfo.shtml?flowId=53&ajaxList[0]=function2&ajaxList[1]="+from.value);
			if(xmlhttp.readyState == 4){
				var text = xmlhttp.responseText;
				 var tr1 = $(from).parentsUntil("table").filter("tr");
				if(text!=null && text!='' && text!='null'){	
						      //tr.childNodes[4].childNodes[0].innerText = text;
						     // alert($(from).parentsUntil("table").filter("tr").find("option:eq(1)").text());
						       tr1.find("textarea:eq(2)").html(text);
						       

				}else{
					tr1.find("textarea:eq(2)").html(' ');
				}
			}
		}
	}catch(e) {
		alert("checkDocNo exception!  "+e);
		return null;
	}
}



 function A30fun2(self){
	try {
		  var tr1 = $(self).parentsUntil("table").filter("tr");
		 //tr1.find("td:eq(4)").html(text); 
		var tr = self.parentElement.parentElement;
		var wfNo = document.getElementById("wfRd.wfNo").value;
		//alert(wfNo);
		
	/* 	var mtart = tr.childNodes[1].childNodes[0].innerText;
		var matkl = tr.childNodes[2].childNodes[0].value;
		var value1 = tr.childNodes[4].childNodes[0].innerText;
		var value2 = tr.childNodes[5].childNodes[0].innerText;
		var maktx = encodeURI(encodeURI(value1));
		var groes = encodeURI(encodeURI(value2)); */
		
		
		
		var mtart = tr1.find("textarea:eq(0)").val();
		
		var matkl = tr1.find("option:selected").val();
		
		//alert(matkl);
		var value1 = tr1.find("textarea:eq(2)").val();
		var value2 = tr1.find("textarea:eq(4)").val();
		var maktx = encodeURI(encodeURI(value1));
		var groes = encodeURI(encodeURI(value2));
		
		//alert(mtart+"--"+matkl+"--"+value1+"--"+value2);
		if((value1!=null && value1.trim()!='') || (value2!=null && value2.trim()!='')){
			var xmlhttp = sendXmlhttpRequest("GET","WfRdView!showAjaxInfo.shtml?flowId=53&ajaxList[0]=function1" + 
			   "&ajaxList[1]="+mtart+"&ajaxList[2]="+matkl+"&ajaxList[3]="+maktx+"&ajaxList[4]="+groes+"&ajaxList[5]="+wfNo);
			if(xmlhttp.readyState == 4){
				//alert("进入判断xmlhttp.readyState == 4");
				var text = xmlhttp.responseText;
				//alert(text);
				if(text!=null && text!='' && text!='null'){
					alert("物料描述或大小量纲与别的流程重复! \n(流程为："+text+")\n\n规则如下：\n1.1和2开头的电子料，大小量纲必填且不能重复，物料描述可以重复；其他物料大小量纲和物料描述不能同时重复。;\n2.181开头大小量纲必填，描述重复时，量纲不可以重复；量纲重复时，描述不可以重复;\n3.1899物料组要排除在外;");
				}
			}
		}
	}catch(e) {
		alert("checkDocNo exception!  "+e);
		return null;
	}
} 
 
</script>
</head>
<body>
	
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>料号申请单</strong></p>

<!-- fieldText 没有ID 则可以为空  -->
<table id="OwnershipStructure" width="980" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;background-color:#EFF0F2;font-size:8.0pt;word-break:break-all; word-wrap:break-all;'>
  <tr>
    <td colspan="100" width="100%" height="30">
		<table border="0" width="100%">
			<tr>
				<td colspan="20" width="20%">&nbsp;</td>
			    <td colspan="60" width="60%"  align="center">申请表</td>
			    <td colspan="20" width="20%"  align="right">
			    	<input type="button"  value="导入" class="btn" onclick="window.opendlg();" <c:if test="${currentTask.sort!=1 && currentTask.sort!=3&& currentTask.sort!=4 && currentTask.sort!=5 && currentTask.sort!=10}">disabled</c:if>>
					<input type="button" value="导出" class="btn" onclick="exportXls();">&nbsp;&nbsp;
					<input type="button" value=" + " class="btn" onclick="AddRow53();" <c:if test="${currentTask.sort!=1}">disabled</c:if>>
					<input type="button" value=" - " class="btn" onclick="DelRow('numI1','StrRight');" <c:if test="${currentTask.sort!=1}">disabled</c:if>>
				</td>
			</tr>
		</table>
	</td>
  </tr>

    <tr id="StrRight">
		<td colspan="3" width="3%" align="center" height="50">序号</td>
		<td colspan="5" width="5%" align="center"><font color="red">物料<br>类型</font></td>
		<td colspan="4" width="4%" align="center"><font color="red">物料组</font></td>
		<td colspan="8" width="8%" align="center">物料<br>编码</td>
		<td colspan="14" width="14%" align="center"><font color="red">物料描述</font></td>
		<td colspan="13" width="13%" align="center">物料描述(英文)</td>
		<td colspan="10" width="10%" align="center">大小量纲</td>
		<td colspan="5" width="5%" align="center">特定工厂物料状态</td>
		<td colspan="9" width="9%" align="center">内部<br>型号</td>
		<td colspan="9" width="9%" align="center">外部<br>型号</td>
		<td colspan="4" width="4%" align="center">颜色</td>
		<td colspan="4" width="4%" align="center">基本<br/>单位</td>
		<td colspan="4" width="4%" align="center">工厂</td>
		<td colspan="4" width="4%" align="center">采购<br/>类型</td>
		<td colspan="4" width="4%" align="center">特殊<br/>采购类</td>
			<!-- 新增列收货处理时间(天)，生产周期,净重,毛重,重量单位 -->
		<!-- <td colspan="5" width="5%" align="center">净重</td>
	    <td colspan="5" width="5%" align="center">毛重</td>
	    <td colspan="5" width="5%" align="center">重量<br>单位</td>
		
		 <td colspan="5" width="5%" align="center">收货处理<br/>时间(天)</td>
		<td colspan="5" width="5%" align="center">生产<br/>周期</td>  -->
		
	</tr>

	 <c:set var="rownum" value="0"/>
	<c:if test="${empty rows && currentTask.sort==1}">
		<c:set var="rownum" value="${rownum + 1}"/>
		<tr>
			<td colspan="3" width="3%" height="50" align="center">1</td>
			<td colspan="5" width="5%">
				<textarea  rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
				 <input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].rowId" value="1">
			</td>
			<td colspan="4" width="4%" bgcolor="#ffffff">
				<select name="fieldContents[<c:out value="${E2.count}"/>].fieldText" onchange="A30fun1(this);"  >
						<option value="-1">请选择</option>
						<c:forEach items="${cateList}" var="cate">
							<option value="<c:out value="${cate.categoryNo}"/>" <c:if test="${E2.fieldText == cate.categoryNo}">selected</c:if>>
								<c:out value="${cate.categoryNo}"/>
							</option>
						</c:forEach>
				</select>
				 <input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].rowId" value="1"> 
			</td>
			<td colspan="8" width="8%">
				 <input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
			</td>
			<td colspan="14" width="14%">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText" onblur="A30fun2(this);"><c:out value="${E4.fieldText}"/></textarea>
				 <input type="hidden" class="ic0o0111" name="fieldContents[<c:out value="${E4.count}"/>].rowId" value="1"> 
			</td>
			<td colspan="13" width="13%">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E58.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E58.count}"/>].fieldText"><c:out value="${E58.fieldText}"/></textarea>
				 <input type="hidden" class="ic0o0111" name="fieldContents[<c:out value="${E58.count}"/>].rowId" value="1"> 
			</td>
			<td colspan="10" width="10%">
				<textarea  rows="4" maxlength="30" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E5.count}"/>].fieldText" onblur="A30fun2(this);"><c:out value="${E5.fieldText}"/></textarea>
				 <input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].rowId" value="1"> 
			</td>
			<td colspan="5" width="5%">
				 <input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
			</td>
			<td colspan="9" width="9%">
				 <input type="hidden"  name="fieldContents[<c:out value="${E7.count}"/>].rowId" value="1"> 
				<textarea  rows="4" maxlength="18" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			</td>
			<td colspan="9" width="9%">
				 <input type="hidden"  name="fieldContents[<c:out value="${E8.count}"/>].rowId" value="1"> 
				<textarea  rows="4" maxlength="18" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
			</td>
			<td colspan="4" width="4%">
				 <input type="hidden" name="fieldContents[<c:out value="${E9.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
			</td>
			<td colspan="4" width="4%">
				 <input type="hidden" name="fieldContents[<c:out value="${E10.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</td>
			<td colspan="4" width="4%">
				 <input type="hidden" name="fieldContents[<c:out value="${E11.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E11.count}"/>].fieldText"><c:out value="${E11.fieldText}"/></textarea>
			</td>
			<td colspan="4" width="4%">
				 <input type="hidden" name="fieldContents[<c:out value="${E12.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E12.count}"/>].fieldText"><c:out value="${E12.fieldText}"/></textarea>
			</td>
			<td colspan="4" width="4%">
				 <input type="hidden" name="fieldContents[<c:out value="${E13.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
			</td>
			
				<!-- 新增列收货处理时间(天)，生产周期,净重,毛重,重量单位 -->
			<%-- <td colspan="5" width="5%">
				 <input type="hidden" name="fieldContents[<c:out value="${E14.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
			</td>
			
			<td colspan="5" width="5%">
				 <input type="hidden" name="fieldContents[<c:out value="${E15.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E15.count}"/>].fieldText"><c:out value="${E15.fieldText}"/></textarea>
			</td>
			<td colspan="5" width="5%">
				 <input type="hidden" name="fieldContents[<c:out value="${E16.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E16.count}"/>].fieldText"><c:out value="${E16.fieldText}"/></textarea>
			</td>
			<td colspan="5" width="5%">
				 <input type="hidden" name="fieldContents[<c:out value="${E17.count}"/>].rowId" value="1"> 
				<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
			</td>
			<td colspan="5" width="5%">
				  <input type="hidden" name="fieldContents[<c:out value="${E18.count}"/>].rowId" value="1">  
				<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E18.count}"/>].fieldText"><c:out value="${E18.fieldText}"/></textarea>
			</td> --%>
		</tr>
	</c:if>
	
	<c:if test="${!empty rows}">
  	<c:forEach items="${rows}" var="row"> 
  		<c:set var="rownum" value="${rownum + 1}"/>
  				<c:forEach items="${row.fileds}" var="field">
					<c:if test="${field.fieldCode=='E1'}">
						<c:set var="E1" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E2'}">
						<c:set var="E2" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E3'}">
						<c:set var="E3" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E4'}">
						<c:set var="E4" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E58'}">
						<c:set var="E58" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E5'}">
						<c:set var="E5" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E6'}">
						<c:set var="E6" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E7'}">
						<c:set var="E7" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E8'}">
						<c:set var="E8" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E9'}">
						<c:set var="E9" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E10'}">
						<c:set var="E10" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E11'}">
						<c:set var="E11" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E12'}">
						<c:set var="E12" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E13'}">
						<c:set var="E13" value="${field}"/>
					</c:if>
					<!-- 新增列收货处理时间(天)，生产周期,净重,毛重,重量单位 -->
					<%-- <c:if test="${field.fieldCode=='E14'}">
						<c:set var="E14" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E15'}">
						<c:set var="E15" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E16'}">
						<c:set var="E16" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E17'}">
						<c:set var="E17" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E18'}">
						<c:set var="E18" value="${field}"/>
					</c:if> --%>
				</c:forEach>
		 <tr>
		 	<td colspan="3" align="center" height="50"><c:out value="${rownum}"/></td>
			<td colspan="5">
				<c:if test="${E1.isEdit == '1'}">
					<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
					 <input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].fieldId" value="<c:out value="${E1.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				</c:if>
				<c:if test="${E1.isEdit != '1'}">
					<c:out value="${E1.fieldText}"/>
				</c:if>
			</td>
			<td colspan="4" <c:if test="${E2.isEdit == '1'}">bgcolor="#ffffff"</c:if>>
				<c:if test="${E2.isEdit == '1'}">
					<select name="fieldContents[<c:out value="${E2.count}"/>].fieldText" onchange="A30fun1(this);">
						<option value="-1">请选择</option>
						<c:forEach items="${cateList}" var="cate">
							<option value="<c:out value="${cate.categoryNo}"/>" <c:if test="${E2.fieldText == cate.categoryNo}">selected</c:if>>
								<c:out value="${cate.categoryNo}"/>
							</option>
						</c:forEach>
					</select>
					<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].fieldId" value="<c:out value="${E2.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<!--
					<textarea  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E2.count}"/>].fieldText" onblur="aa();"><c:out value="${E2.fieldText}"/></textarea>
						-->
				</c:if>
				<c:if test="${E2.isEdit != '1'}">
					<c:out value="${E2.fieldText}"/>
				</c:if>
				
				
				
			</td>
			<td colspan="8" >
				<c:if test="${E3.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].fieldId" value="<c:out value="${E3.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E3.isEdit != '1'}">
					<c:out value="${E3.fieldText}"/>
				</c:if>
			</td>
			<td colspan="14">
				<c:if test="${E4.isEdit == '1'}">
					<textarea rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E4.count}"/>].fieldText" onblur="A30fun2(this);"><c:out value="${E4.fieldText}"/></textarea>
					<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].fieldId" value="<c:out value="${E4.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				</c:if>
				<c:if test="${E4.isEdit != '1'}">
					<c:out value="${E4.fieldText}"/>
				</c:if>
			</td>
			<td colspan="13">
				<c:if test="${E58.isEdit == '1'}">
					<textarea rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E58.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E58.count}"/>].fieldText"><c:out value="${E58.fieldText}"/></textarea>
					<input type="hidden" name="fieldContents[<c:out value="${E58.count}"/>].fieldId" value="<c:out value="${E58.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E58.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				</c:if>
				<c:if test="${E58.isEdit != '1'}">
					<c:out value="${E58.fieldText}"/>
				</c:if>
			</td>
			<td colspan="10">
				<c:if test="${E5.isEdit == '1'}">
					<textarea rows="4" maxlength="30" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E5.count}"/>].fieldText"  onblur="A30fun2(this);"><c:out value="${E5.fieldText}"/></textarea>
					<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].fieldId" value="<c:out value="${E5.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				</c:if>
				<c:if test="${E5.isEdit != '1'}">
					<c:out value="${E5.fieldText}"/>
				</c:if>
			</td>
			<td colspan="5">
				<c:if test="${E6.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].fieldId" value="<c:out value="${E6.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E6.isEdit != '1'}">
					<c:out value="${E6.fieldText}"/>
				</c:if>
			</td>
			<td colspan="9">
				<c:if test="${E7.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].fieldId" value="<c:out value="${E7.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" maxlength="18" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E7.isEdit != '1'}">
					<c:out value="${E7.fieldText}"/>
				</c:if>
			</td>
			<td colspan="9">
				<c:if test="${E8.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E8.count}"/>].fieldId" value="<c:out value="${E8.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E8.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" maxlength="18" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E8.isEdit != '1'}">
					<c:out value="${E8.fieldText}"/>
				</c:if>
			</td>
			<td colspan="4">
				<c:if test="${E9.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E9.count}"/>].fieldId" value="<c:out value="${E9.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E9.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E9.isEdit != '1'}">
					<c:out value="${E9.fieldText}"/>
				</c:if>
			</td>
			<td colspan="4">
				<c:if test="${E10.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E10.count}"/>].fieldId" value="<c:out value="${E10.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E10.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E10.isEdit != '1'}">
					<c:out value="${E10.fieldText}"/>
				</c:if>
			</td>
			<td colspan="4">
				<c:if test="${E11.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E11.count}"/>].fieldId" value="<c:out value="${E11.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E11.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E11.count}"/>].fieldText"><c:out value="${E11.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E11.isEdit != '1'}">
					<c:out value="${E11.fieldText}"/>
				</c:if>
			</td>
			<td colspan="4">
				<c:if test="${E12.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E12.count}"/>].fieldId" value="<c:out value="${E12.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E12.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E12.count}"/>].fieldText"><c:out value="${E12.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E12.isEdit != '1'}">
					<c:out value="${E12.fieldText}"/>
				</c:if>
			</td>
			<td colspan="4">
				<c:if test="${E13.isEdit == '1'}">
					 <input type="hidden" name="fieldContents[<c:out value="${E13.count}"/>].fieldId" value="<c:out value="${E13.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E13.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E13.isEdit != '1'}">
					<c:out value="${E13.fieldText}"/>
				</c:if>
			</td>
			<!-- 新增列收货处理时间(天)，生产周期,净重,毛重,重量单位 -->
			<%-- <td colspan="5">
				<c:if test="${E14.isEdit == '1'}">
					 <input type="hidden" name="fieldContents[<c:out value="${E14.count}"/>].fieldId" value="<c:out value="${E14.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E14.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E14.isEdit != '1'}">
					<c:out value="${E14.fieldText}"/>
				</c:if>
			</td>
			<td colspan="5">
				<c:if test="${E15.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E15.count}"/>].fieldId" value="<c:out value="${E15.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E15.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E15.count}"/>].fieldText"><c:out value="${E15.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E15.isEdit != '1'}">
					<c:out value="${E15.fieldText}"/>
				</c:if>
			</td>
			<td colspan="5">
				<c:if test="${E16.isEdit == '1'}">
					 <input type="hidden" name="fieldContents[<c:out value="${E16.count}"/>].fieldId" value="<c:out value="${E16.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E16.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E16.count}"/>].fieldText"><c:out value="${E16.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E16.isEdit != '1'}">
					<c:out value="${E16.fieldText}"/>
				</c:if>
			</td>
			<td colspan="5">
				<c:if test="${E17.isEdit == '1'}">
					 <input type="hidden" name="fieldContents[<c:out value="${E17.count}"/>].fieldId" value="<c:out value="${E17.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E17.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E17.isEdit != '1'}">
					<c:out value="${E17.fieldText}"/>
				</c:if>
			</td>
			<td colspan="5">
				<c:if test="${E18.isEdit == '1'}">
					 <input type="hidden" name="fieldContents[<c:out value="${E18.count}"/>].fieldId" value="<c:out value="${E18.fieldId}"/>"> 
					<input type="hidden" name="fieldContents[<c:out value="${E18.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E18.count}"/>].fieldText"><c:out value="${E18.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E18.isEdit != '1'}">
					<c:out value="${E18.fieldText}"/>
				</c:if>
			</td> --%>
		</tr>
		
		<c:remove var="E1"/>
		<c:remove var="E2"/>
		<c:remove var="E3"/>
		<c:remove var="E4"/>
		<c:remove var="E58"/>
		<c:remove var="E5"/>
		<c:remove var="E6"/>
		<c:remove var="E7"/>
		<c:remove var="E8"/>
		<c:remove var="E9"/>
		<c:remove var="E10"/>
		<c:remove var="E11"/>
		<c:remove var="E12"/>
		<c:remove var="E13"/>
		<!-- 新增列收货处理时间(天)，生产周期,净重,毛重,重量单位 -->
		<%-- <c:remove var="E14"/>
		<c:remove var="E15"/>
		<c:remove var="E16"/>
		<c:remove var="E17"/>
		<c:remove var="E18"/> --%>
  	</c:forEach>
 	</c:if>
 <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
 <input type="hidden" id="matlCate" value="<c:out value="${hiddenValue}"/>"/>
 
</table>


</br>


</body>
</html>
