<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title> 

<script type="text/javascript">

/*$(function checkTheDifferentInput1 () {
	var max = 209;
	var printNumber = 0;
	for(var i=0;;i++)
	{
		for(var j=0;j<10;j++)
		{
			printNumber = i*20+j;
			var printNumber2 =printNumber+10;
				if( document.getElementById("fieldContents["+printNumber+"].fieldText") ==null){
					return;
				}else{
					if(document.getElementById("fieldContents["+printNumber+"].fieldText").value !=
						document.getElementById("fieldContents["+printNumber2+"].fieldText").value
								){
							document.getElementById("fieldContents["+printNumber+"].fieldText").style.background="#FCF8DC";
							document.getElementById("fieldContents["+printNumber2+"].fieldText").style.background="#FCF8DC";
						}
				}
		}
	}	 
});*/

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

$(function checkTheDifferentInput1 () {
	var table = document.getElementById("OwnershipStructure");	
	var total = table.rows.length;
	for(var i=2;i<total;i+=2)
	{
		for(var j=2;j<=12;j++)
		{
			var lastCell = table.rows[i].cells[j].getElementsByTagName('textarea')[0];
			var thisCell = table.rows[i+1].cells[j-1].getElementsByTagName('textarea')[0];
			if(lastCell == null && thisCell == null) {
				lastCell.style.background="#FFFFFF";
				thisCell.style.background="#FFFFFF";
			} else if(lastCell == null && thisCell != null) {
				if(thisCell.value.replace(/^\s+|\s+$/g,"") == '') {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FFFFFF";
				} else {
					lastCell.style.background="#FCF8DC";
					thisCell.style.background="#FCF8DC";
				}
			} else if(lastCell != null && thisCell == null) {
				if(lastCell.value.replace(/^\s+|\s+$/g,"") == '') {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FFFFFF";
				} else {
					lastCell.style.background="#FCF8DC";
					thisCell.style.background="#FCF8DC";
				}
			} else {
				if(thisCell.value.replace(/^\s+|\s+$/g,"") == lastCell.value.replace(/^\s+|\s+$/g,"")) {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FFFFFF";
				} else {
					lastCell.style.background="#FCF8DC";
					thisCell.style.background="#FCF8DC";
				}
			}
		}
	}	
});

/*function checkTheDifferentInput () {	
	var printNumber = 0;
	for(var i=0;;i++)
	{
		for(var j=0;j<10;j++)
		{
			printNumber = i*20+j;
			var printNumber2 =printNumber+10;
				if( document.getElementById("fieldContents["+printNumber+"].fieldText") ==null){
					return;
				}else{
					if(document.getElementById("fieldContents["+printNumber+"].fieldText").value !=
						document.getElementById("fieldContents["+printNumber2+"].fieldText").value
								){
							document.getElementById("fieldContents["+printNumber2+"].fieldText").style.background="#FCF8DC";
						}
				}
		}
	}	 
}*/

function checkTheDifferentInput () {
	var table = document.getElementById("OwnershipStructure");	
	var total = table.rows.length;
	for(var i=2;i<total;i+=2)
	{
		for(var j=2;j<=12;j++)
		{
			var lastCell = table.rows[i].cells[j].getElementsByTagName('textarea')[0];
			var thisCell = table.rows[i+1].cells[j-1].getElementsByTagName('textarea')[0];
			if(lastCell == null && thisCell == null) {
				lastCell.style.background="#FFFFFF";
				thisCell.style.background="#FFFFFF";
			} else if(lastCell == null && thisCell != null) {
				if(thisCell.value.replace(/^\s+|\s+$/g,"") == '') {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FFFFFF";
				} else {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FCF8DC";
				}
			} else if(lastCell != null && thisCell == null) {
				if(lastCell.value.replace(/^\s+|\s+$/g,"") == '') {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FFFFFF";
				} else {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FCF8DC";
				}
			} else {
				if(thisCell.value.replace(/^\s+|\s+$/g,"") == lastCell.value.replace(/^\s+|\s+$/g,"")) {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FFFFFF";
				} else {
					lastCell.style.background="#FFFFFF";
					thisCell.style.background="#FCF8DC";
				}
			}
		}
	}	 
}
 
 
 </script>
<!-- <script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
 <script src="./include/js/Xmlhttp.js" type="text/javascript"></script> 

<script src="./include/js/gnwf/WfRd_special.js" type="text/javascript"></script> -->



</head>
<body>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>DCC其它变更申请</strong></p>

<table id="OwnershipStructure" width="980" style="BORDER-COLLAPSE: collapse;word-break:break-all; word-wrap:break-all;" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>

  <tr>
	<td colspan="100" width="100%" height="25"  bgcolor="#FFFFB0">
		<table width="100%" border="0">
			<tr>
			<td width="15%" bgcolor="#FFFFB0"><input id="numI5" type="hidden" value="1"/>&nbsp;</td>
		    <td width="70%" align="center" bgcolor="#FFFFB0">材料认证部变更所填表单</td>
			<td width="15%" align="right"  bgcolor="#FFFFB0">
				<input type="button" id=importxls class="btn" value="导入" onclick="window.opendlg();"
					<c:if test="${currentTask.sort!=1 && currentTask.sort!=2}">disabled</c:if>>&nbsp;
				<input type="button" id=exportxls class="btn" value="导出" onclick="exportXls();">
			</td>
			</tr>
		</table>
	</td>
  </tr>
  <tr id="StrRight">
    <td colspan="3" width="3%" height="50" align="center">序号</td>
    <td colspan="4" width="4%" align="center">状态</td>
    <td colspan="6" width="6%" align="center">物料组<br>(MATKL)</td>
	<td colspan="7" width="7%" align="center">物料编码<br>(MATNR)</td>
	<td colspan="14" width="14%" align="center">物料描述<br>(MAKTX)</td>
	<td colspan="14" width="14%" align="center">物料描述(英文)<br>(MAKTX_EN)</td>
	<td colspan="9" width="9%" align="center">大小量纲<br>(GROES)</td>
	<td colspan="8" width="8%" align="center">特定工厂物料<br>状态(MMSTA)</td>
	<td colspan="6" width="6%" align="center">工厂<br>(WERKS)</td>
	<td colspan="6" width="6%" align="center">内部型号<br>(FERTH)</td>
	<td colspan="6" width="6%" align="center">外部型号<br>(EXTWG)</td>
	<td colspan="6" width="6%" align="center">颜色<br>(NORMT)</td>
	
	<td colspan="10" width="10%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">备注</td>
			<td align="right">
				<input type="button" id=addrow  class="btn" value="+" onclick='javascript:AddRow5();'
					<c:if test="${currentTask.sort!=1}">disabled</c:if>><br>
				<input type="button" id=delrow class="btn" value="-" onclick='javascript:DelRow5("numI1","StrRight");' 
					<c:if test="${currentTask.sort!=1}">disabled</c:if>>
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
 
<c:set var="rownum" value="0"/>
<c:if test="${empty rows}">
		<c:set var="rownum" value="${rownum + 1}"/>
	<tr>
	    <td colspan=3 width=3% align="center" rowSpan="2">1</td>
	    <td colspan=4 width=4% height=40 align="center">更改前</td>
	    <td colspan=6 width=6%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
		</td>
	    <td colspan=7 width=7%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E2.count}"/>].fieldText"><c:out value="${E2.fieldText}"/></textarea>
		</td>
	    <td colspan=14 width=14%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
		</td>
		<td colspan=14 width=14%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E21.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E21.count}"/>].fieldText"><c:out value="${E21.fieldText}"/></textarea>
		</td>
		<td colspan=9 width=9%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="30" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
		</td>
		<td colspan=8 width=8%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
		</td>
		<td colspan=6  width=6%>
	    	<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
		</td>
		<td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
		</td>
		<td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E8.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
		</td>
		<td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E9.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
		</td>
		<td colspan=10 width=10%>
		    <input type="hidden" name="fieldContents[<c:out value="${E10.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
		</td>
	 </tr>
	 <tr>
	    <td colspan=4 width=4% height=40 align="center">更改后</td>
	    <td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E11.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E11.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E11.fieldText}"/></textarea>
		</td>
	    <td colspan=7 width=7%>
		    <input type="hidden" name="fieldContents[<c:out value="${E12.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E12.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E12.fieldText}"/></textarea>
		</td>
	    <td colspan=14 width=14%>
		    <input type="hidden" name="fieldContents[<c:out value="${E13.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E13.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E13.fieldText}"/></textarea>
		</td>
		<td colspan=14 width=14%>
		    <input type="hidden" name="fieldContents[<c:out value="${E22.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E22.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E22.fieldText}"/></textarea>
		</td>
		<td colspan=9 width=9%>
		    <input type="hidden" name="fieldContents[<c:out value="${E14.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="30" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E14.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E14.fieldText}"/></textarea>
		</td>
		<td colspan=8 width=8%>
		    <input type="hidden" name="fieldContents[<c:out value="${E15.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E15.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E15.fieldText}"/></textarea>
		</td>
		<td colspan=6  width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E16.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E16.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E16.fieldText}"/></textarea>
		</td>
		<td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E17.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E17.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E17.fieldText}"/></textarea>
		</td>
		<td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E18.count}"/>].rowId" value="1">
			<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E18.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E18.fieldText}"/></textarea>
		</td>
		<td colspan=6 width=6%>
		    <input type="hidden" name="fieldContents[<c:out value="${E19.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E19.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E19.fieldText}"/></textarea>
		</td>
		<td colspan=10 width=10%>
		    <input type="hidden" name="fieldContents[<c:out value="${E20.count}"/>].rowId" value="1">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
				name="fieldContents[<c:out value="${E20.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E20.fieldText}"/></textarea>
		</td>
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
					<c:if test="${field.fieldCode=='E21'}">
						<c:set var="E21" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E4'}">
						<c:set var="E4" value="${field}"/>
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
					<c:if test="${field.fieldCode=='E22'}">
						<c:set var="E22" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E14'}">
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
					</c:if>
					<c:if test="${field.fieldCode=='E19'}">
						<c:set var="E19" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E20'}">
						<c:set var="E20" value="${field}"/>
					</c:if>
			</c:forEach>
			
	<tr>
	    <td colspan=3 width=3% align="center" rowSpan="2"><c:out value="${rownum}"/></td>
	    <td colspan=4 width=4% height=40 align="center">更改前</td>
	    <td colspan=6 width=6%>
	    	<c:if test="${E1.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].fieldId" value="<c:out value="${E1.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E1.isEdit != '1'}">
				<textarea  rows="4" style="width:93%;overflow:hidden;" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count}"/>].fieldText" disabled="disabled"><c:out value="${E1.fieldText}"/></textarea>
			</c:if>
		</td>
	    <td colspan=7 width=7%>
	    	<c:if test="${E2.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].fieldId" value="<c:out value="${E2.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E2.count}"/>].fieldText"><c:out value="${E2.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E2.isEdit != '1'}">
				<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E2.count}"/>].fieldText" disabled="disabled"><c:out value="${E2.fieldText}"/></textarea>
			</c:if>
		</td>
	    <td colspan=14 width=14%>
	    	<c:if test="${E3.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].fieldId" value="<c:out value="${E3.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E3.isEdit != '1'}">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E3.count}"/>].fieldText" disabled="disabled"><c:out value="${E3.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=14 width=14%>
	    	<c:if test="${E21.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E21.count}"/>].fieldId" value="<c:out value="${E21.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E21.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E21.count}"/>].fieldText"><c:out value="${E21.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E21.isEdit != '1'}">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E21.count}"/>].fieldText" disabled="disabled"><c:out value="${E21.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=9 width=9%>
		 	<c:if test="${E4.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].fieldId" value="<c:out value="${E4.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="30" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E4.isEdit != '1'}">
			<textarea  rows="4" maxlength="30" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText" disabled="disabled"><c:out value="${E4.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=8 width=8%>
		 	<c:if test="${E5.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].fieldId" value="<c:out value="${E5.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E5.isEdit != '1'}">
				<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E5.count}"/>].fieldText" disabled="disabled"><c:out value="${E5.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6  width=6%>
		 	<c:if test="${E6.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].fieldId" value="<c:out value="${E6.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E6.isEdit != '1'}">
				<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E6.count}"/>].fieldText" disabled="disabled"><c:out value="${E6.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6 width=6%>
		 	<c:if test="${E7.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].fieldId" value="<c:out value="${E7.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E7.isEdit != '1'}">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText" disabled="disabled"><c:out value="${E7.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6 width=6%>
		 	<c:if test="${E8.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E8.count}"/>].fieldId" value="<c:out value="${E8.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E8.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E8.isEdit != '1'}">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText" disabled="disabled"><c:out value="${E8.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6 width=6%>
		 	<c:if test="${E9.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E9.count}"/>].fieldId" value="<c:out value="${E9.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E9.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E9.isEdit != '1'}">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E9.count}"/>].fieldText" disabled="disabled"><c:out value="${E9.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=10 width=10%>
		 	<c:if test="${E10.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E10.count}"/>].fieldId" value="<c:out value="${E10.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E10.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E10.isEdit != '1'}">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText" disabled="disabled"><c:out value="${E10.fieldText}"/></textarea>
			</c:if>
		</td>
	 </tr>
	 <tr>
	    <td colspan=4 width=4% height=40 align="center">更改后</td>
	    <td colspan=6 width=6%>
	     	<c:if test="${E11.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E11.count}"/>].fieldId" value="<c:out value="${E11.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E11.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E11.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E11.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E11.isEdit != '1'}">
			<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E11.count}"/>].fieldText" disabled="disabled"><c:out value="${E11.fieldText}"/></textarea>

			</c:if>
		</td>
	    <td colspan=7 width=7%>
	     	<c:if test="${E12.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E12.count}"/>].fieldId" value="<c:out value="${E12.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E12.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E12.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E12.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E12.isEdit != '1'}">
				<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E12.count}"/>].fieldText" disabled="disabled"><c:out value="${E12.fieldText}"/></textarea>
			</c:if>
		</td>
	    <td colspan=14 width=14%>
	     	<c:if test="${E13.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E13.count}"/>].fieldId" value="<c:out value="${E13.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E13.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E13.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E13.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E13.isEdit != '1'}">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E13.count}"/>].fieldText" disabled="disabled" ><c:out value="${E13.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=14 width=14%>
	     	<c:if test="${E22.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E22.count}"/>].fieldId" value="<c:out value="${E22.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E22.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E22.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E22.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E22.isEdit != '1'}">
				<textarea  rows="4" maxlength="40" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E22.count}"/>].fieldText" disabled="disabled" ><c:out value="${E22.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=9 width=9%>
		 	<c:if test="${E14.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E14.count}"/>].fieldId" value="<c:out value="${E14.fieldId}"/>">
				<input type="hidden" name="fieldContents[<c:out value="${E14.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="30" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E14.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E14.isEdit != '1'}">
				<textarea  rows="4" maxlength="30" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText" disabled="disabled"><c:out value="${E14.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=8 width=8%>
		 	<c:if test="${E15.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${E15.count}"/>].fieldId" value="<c:out value="${E15.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E15.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E15.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E15.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E15.isEdit != '1'}">
				<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E15.count}"/>].fieldText" disabled="disabled"><c:out value="${E15.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6  width=6%>
		 	<c:if test="${E16.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E16.count}"/>].fieldId" value="<c:out value="${E16.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E16.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E16.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E16.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E16.isEdit != '1'}">
				<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E16.count}"/>].fieldText" disabled="disabled"><c:out value="${E16.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6 width=6%>
		 	<c:if test="${E17.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E17.count}"/>].fieldId" value="<c:out value="${E17.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E17.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E17.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E17.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E17.isEdit != '1'}">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E17.count}"/>].fieldText" disabled="disabled"><c:out value="${E17.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6 width=6%>
		 	<c:if test="${E18.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E18.count}"/>].fieldId" value="<c:out value="${E18.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E18.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E18.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E18.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E18.isEdit != '1'}">
				<textarea  rows="4" maxlength="18" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E18.count}"/>].fieldText" disabled="disabled"><c:out value="${E18.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=6 width=6%>
		 	<c:if test="${E19.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E19.count}"/>].fieldId" value="<c:out value="${E19.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E19.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E19.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E19.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E19.isEdit != '1'}">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E19.count}"/>].fieldText" disabled="disabled"><c:out value="${E19.fieldText}"/></textarea>
			</c:if>
		</td>
		<td colspan=10 width=10%>
		 	<c:if test="${E20.isEdit == '1'}">
				 <input type="hidden" name="fieldContents[<c:out value="${E20.count}"/>].fieldId" value="<c:out value="${E20.fieldId}"/>"> 
				<input type="hidden" name="fieldContents[<c:out value="${E20.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E20.count}"/>].fieldText" onblur="checkTheDifferentInput();"><c:out value="${E20.fieldText}"/></textarea>
			</c:if>
			<c:if test="${E20.isEdit != '1'}">
				<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E20.count}"/>].fieldText" disabled="disabled"><c:out value="${E20.fieldText}"/></textarea>
			</c:if>
		</td>
	 </tr>
		
		<c:remove var="E1"/>
		<c:remove var="E2"/>
		<c:remove var="E3"/>
		<c:remove var="E21"/>
		<c:remove var="E4"/>
		<c:remove var="E5"/>
		<c:remove var="E6"/>
		<c:remove var="E7"/>
		<c:remove var="E8"/>
		<c:remove var="E9"/>
		<c:remove var="E10"/>
		<c:remove var="E11"/>
		<c:remove var="E12"/>
		<c:remove var="E13"/>
		<c:remove var="E22"/>
		<c:remove var="E14"/>
		<c:remove var="E15"/>
		<c:remove var="E16"/>
		<c:remove var="E17"/>
		<c:remove var="E18"/>
		<c:remove var="E19"/>
		<c:remove var="E20"/>
  	</c:forEach>
 	</c:if>
 <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
  
  
</table>
</body>
</html>
