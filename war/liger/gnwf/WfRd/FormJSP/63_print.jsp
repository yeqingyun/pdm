<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<span style="visibility:hidden">页面44jsp</span>
<html>

    <head> 
<style type="text/css"> 
body 
{ 
margin: 0px; 
padding: 0px; 
font-size: 13px; 
} 
.input_style 
{ 
padding: 6px 0px; 
width: 600px; 
 margin: 0px auto;  
/* border-bottom: #666666 1px dotted;  */
} 
span.input_style 
{ 
display: inline-block; 
width: 15px; 
height: 15px; 
text-align: left; 
vertical-align: middle; 
_overflow: hidden; 
margin-right:4px;
}  
/* .input_style span
{ 
display: inline-block; 
width: 15px; 
height: 15px; 
text-align: left; 
vertical-align: middle; 
_overflow: hidden; 
margin-right:4px;
}   */
label.input_style 
{ 
padding: 9px 6px; 
cursor: pointer; 
} 
.input_style input 
{ 
cursor: pointer; 
} 
.checkbox 
{ 
padding: 0px; 
list-style: none; 
width: 600px; 
margin: 2px auto; 
height: auto; 
overflow: hidden; 
} 
.checkbox li 
{ 
padding: 3px 0px; 
float: left; 
} 
h4 
{ 
width: 600px; 
margin: 0px auto; 
margin-top: 30px; 
} 
.button 
{ 
width: 600px; 
text-align: center; 
margin: 0px auto; 
} 
</style> 

<script type="text/javascript">
	function getid(id) {
		return document.getElementById(id);
	}
	function gettag(tag, obj) {
		return document.getElementById('bodyDiv').getElementsByTagName(tag);
		/**
		if (obj) {
			return obj.getElementsByTagName(tag)
		} else {
			return document.getElementsByTagName(tag)
		}
		**/
	}
	function addLoadEvent(func) {
		var oldonload = window.onload;
		if (typeof window.onload != "function") {
			window.onload = func;
		} else {
			window.onload = function() {
				oldonload();
				func();
			}
		}
		;
	}

	function radio_style() {
		//if (getid("bodyDiv")) {
			if (gettag("input")) {
				var r = gettag("input");
				
				function select_element(obj, type) {
					obj.parentNode.style.background = "url(liger/gnwf/WfRd/FormJSP/radio.gif) no-repeat -15px 0px";
					if (obj.type == "radio") {
						obj.parentNode.style.background = "url(liger/gnwf/WfRd/FormJSP/radio.gif) no-repeat -15px -16px";
					}
					if (type) {
						obj.parentNode.style.background = "url(liger/gnwf/WfRd/FormJSP/radio.gif) no-repeat 0px 0px";
						if (obj.type == "radio") {
							obj.parentNode.style.background = "url(liger/gnwf/WfRd/FormJSP/radio.gif) no-repeat 0px -16px";
						}
					}
				}
				
				for (var i = 0; i < r.length; i++) {
					if (r[i].type == "radio") {
						r[i].style.opacity = 0;
						r[i].style.filter = "alpha(opacity=0)";
						r[i].onclick = function() {
							select_element(this);
							unfocus();
						}
						if (r[i].checked == true) {
							select_element(r[i]);
						} else {
							select_element(r[i], 1);
						}
					}
				}
				
				function unfocus() {
					for (var i = 0; i < r.length; i++) {
						if (r[i].type == "radio") {
							if (r[i].checked == false) {
								select_element(r[i], 1)
							}
						}
					}
				}
				
				
			}
		//}
	}
	addLoadEvent(radio_style);
</script>
</head>
 <body > 
	<div id="bodyDiv">
		<p align="center" style='font-size: 13.0pt; font-family: 宋体;margin:0px;padding:0px;margin-top:-16px;'>
			<b>金&nbsp;立&nbsp;通&nbsp;信&nbsp;设&nbsp;备&nbsp;有&nbsp;限&nbsp;公&nbsp;司<br />
				二级供应商资格评审报告
			</b>
		</p>
		<table border=1 bordercolor=#000000 align=center width=686
			style='font-family: 宋体;border-bottom:0px;margin-top:10px;'>
			<tr align="center">
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">供应商名称</td>
				<td colspan="3"><c:choose>
						<c:when test="${E1.isEdit == '1'}">
							<B><input type="Text" size="40"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E1.count}"/>].fieldText"
								value="<c:out value="${E1.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="40"
								value="<c:out value="${fieldContents[0].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"> </B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">供应商地址</td>
				<td colspan="3" align="center"><c:choose>
						<c:when test="${E2.isEdit == '1'}">
							<B><input type="Text" size="40"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E2.count}"/>].fieldText"
								value="<c:out value="${E2.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="40"
								value="<c:out value="${fieldContents[1].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"> </B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">物料名称</td>
				<td width="35%" align="center"><c:choose>
						<c:when test="${E3.isEdit == '1'}">
							<B><input type="Text" size="25"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E3.count}"/>].fieldText"
								value="<c:out value="${E3.fieldText}"/>"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="25"
								value="<c:out value="${fieldContents[2].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">一级物料名称</td>
				<td width="41%" align="center"><c:choose>
						<c:when test="${E4.isEdit == '1'}">
							<B><input type="Text" size="26"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E4.count}"/>].fieldText"
								value="<c:out value="${E4.fieldText}"/>"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="26"
								value="<c:out value="${fieldContents[3].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr align="center">
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">一级供应商</td>
				<td colspan="3"><c:choose>
						<c:when test="${E5.isEdit == '1'}">
							<B><input type="Text" size="40"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
								value="<c:out value="${E5.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="40"
								value="<c:out value="${fieldContents[4].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"> </B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">审核时间</td>
				<td colspan="3" align="center"><c:choose>
						<c:when test="${E6.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E6.count}"/>].fieldText"
								value="<c:out value="${E6.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[5].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 年&nbsp;&nbsp; <c:choose>
						<c:when test="${E7.isEdit == '1'}">
							<B><input type="Text" size="3"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E7.count}"/>].fieldText"
								value="<c:out value="${E7.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="3"
								value="<c:out value="${fieldContents[6].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>月&nbsp;&nbsp; <c:choose>
						<c:when test="${E8.isEdit == '1'}">
							<B><input type="Text" size="3"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E8.count}"/>].fieldText"
								value="<c:out value="${E8.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="3"
								value="<c:out value="${fieldContents[7].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 共 <c:choose>
						<c:when test="${E9.isEdit == '1'}">
							<B><input type="Text" size="3"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E9.count}"/>].fieldText"
								value="<c:out value="${E9.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="3"
								value="<c:out value="${fieldContents[8].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 天</td>
			</tr>
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体; border-bottom:0px;">审核组长</td>
				<td width="35%" align="center" style="border-bottom:0px;"><c:choose>
						<c:when test="${E10.isEdit == '1'}">
							<B><input type="Text" size="17"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E10.count}"/>].fieldText"
								value="<c:out value="${E10.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="17"
								value="<c:out value="${fieldContents[9].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose>
				</td>		
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体; border-bottom:0px;">审核人员</td>
				<td width="41%" align="center" style="border-bottom:0px;"><c:choose>
						<c:when test="${E11.isEdit == '1'}">
							<B><input type="Text" size="38"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E11.count}"/>].fieldText"
								value="<c:out value="${E11.fieldText}"/>"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="38"
								value="<c:out value="${fieldContents[10].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>					
			</tr>
		</table>
		<table border=1 bordercolor=#000000 align=center width=686
			style='font-family: 宋体;'>
			<tr>
				<td width="12%" align="center" valign="middle" height=17px
					style="font-size: 10.0pt; font-family: 宋体;">审查项目</td>
				<td width="77%" align="center" valign="middle" height=17px
					style="font-size: 10.0pt; font-family: 宋体;">主要内容</td>
				<td width="5%" align="center" valign="middle" height=17px
					style="font-size: 10.0pt; font-family: 宋体;">分值</td>
				<td width="6%" align="center" valign="middle" height=17px
					style="font-size: 10.0pt; font-family: 宋体;">得分</td>				
			</tr>
			<tr>
				<td width="12%" rowspan="5" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">管理体系<br>(20分)</td>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">1、是否通过ISO9001质量体系或产品认证、每年是否有例行监督审核、证书是否有效？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">15</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E12.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E12.count}"/>].fieldText" class="63jspE12"
								value="<c:out value="${E12.fieldText}"/>" onblur="formatStr1(this,15)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[11].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">2、公司各职能是否健全、各部门职责是否明确？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">10</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E13.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E13.count}"/>].fieldText" class="63jspE13"
								value="<c:out value="${E13.fieldText}"/>" onblur="formatStr1(this,10)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[12].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">3、质量管理体系所需过程策划是否完整、每个过程是否有相应的文件支持？技术类文件是否健全？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">25</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E14.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E14.count}"/>].fieldText" class="63jspE14"
								value="<c:out value="${E14.fieldText}"/>" onblur="formatStr1(this,25)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[13].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">4、质量目标设定是否合理、质量目标是否有监控？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">30</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E15.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E15.count}"/>].fieldText" class="63jspE15"
								value="<c:out value="${E15.fieldText}"/>" onblur="formatStr1(this,30)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[14].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">5、是否按规定的时间进行内部审核和管理评审、是否建立/保持持续改进的措施？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">20</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E16.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E16.count}"/>].fieldText" class="63jspE16"
								value="<c:out value="${E16.fieldText}"/>" onblur="formatStr1(this,20)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[15].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="88%" colspan="2" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">得分</td>
				<td width="12%" colspan="2" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E17.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E17.count}"/>].fieldText" class="63jspE17" disabled="disabled"
								value="<c:out value="${E17.fieldText}"/>"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[16].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="100%" colspan="4" valign="top" style="font-size: 10.0pt; font-family: 宋体;">  
				基于以下:
					<c:choose>
						<c:when test="${E18.isEdit == '1'}">								
							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E18.count}"/>].fieldText"
								value="1"
								<c:if test="${E18.fieldText == 1 }">checked</c:if>
								id="E18_1" style="opacity: 0;">
							</span>
							<label for="E18_1"><font style="font-size:12px;">合格，可作为二级供应商</font></label>

							&nbsp;<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E18.count}"/>].fieldText"
								value="2" <c:if test="${E18.fieldText == 2}">checked</c:if>
								id="E18_2" style="opacity: 0;">
							</span>
							<label for="E18_2"><font style="font-size:12px;">基本合格，可作为暂用/候备二级供应商</font></label>
										 							
	 						&nbsp;<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E18.count}"/>].fieldText"
								value="3" <c:if test="${E18.fieldText == 3}">checked</c:if>
								id="E18_3" style="opacity: 0;">
							</span>
							<label for="E18_3"><font style="font-size:12px;">不合格，不选为二级供应商</font><br></label>
						
						</c:when>
						<c:otherwise>
							<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[17].fieldText == 1}">checked</c:if>
								id="E18_1" style="opacity: 0;">
							</span>
							<label for="E18_1"><font style="font-size:12px;">合格，可作为二级供应商</font></label>

							&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[17].fieldText == 2}">checked</c:if>
								id="E18_2" style="opacity: 0;">
							</span>
							<label for="E18_2"><font style="font-size:12px;">基本合格，可作为暂用/候备二级供应商</font></label>
	
	 						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[17].fieldText == 3}">checked</c:if>
								id="E18_3" style="opacity: 0;">
							</span>
							<label for="E18_3"><font style="font-size:12px;">不合格，不选为二级供应商</font><br></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${E19.isEdit == '1'}">
							<B><input type="Text" size="110" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E19.count}"/>].fieldText"
								value="<c:out value="${E19.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="110"
								value="<c:out value="${fieldContents[18].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose><br> 
					<c:choose>
						<c:when test="${E20.isEdit == '1'}">
							<B><input type="Text" size="63" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E20.count}"/>].fieldText"
								value="<c:out value="${E20.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="63"
								value="<c:out value="${fieldContents[19].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
		
					<span style="width:16px;display:inline-block;"></span>
					<font style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
						<c:when test="${E21.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E21.count}"/>].fieldText"
								<c:if test="${!empty E21.fieldText}">value="<c:out value="${E21.fieldText}"/>"</c:if>
								<c:if test="${empty E21.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[20].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E22.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E22.count}"/>].fieldText"
								<c:if test="${!empty E22.fieldText}">value="<c:out value="${E22.fieldText}"/>"</c:if>
								<c:if test="${empty E22.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[21].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 
				</td>
			</tr>
			<tr>
				<td width="12%" rowspan="5" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">品质控制<br>(40分)</td>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体; ">1、品质人员配置是否合理、检验与试验仪器设备是否齐全？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">20</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E23.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E23.count}"/>].fieldText" class="63jspE23"
								value="<c:out value="${E23.fieldText}"/>" onblur="formatStr2(this,20)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[22].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体; ">2、关键器件检验与试验项目/方法/设备是否满足要求、是否按照既定的抽样方案进行检验与试验？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">20</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E24.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E24.count}"/>].fieldText" class="63jspE24"
								value="<c:out value="${E24.fieldText}"/>" onblur="formatStr2(this,20)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[23].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体; ">3、生产制程是否有关键品质控制点（检测）并进行监控、是否实施首件确认？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">10</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E25.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E25.count}"/>].fieldText" class="63jspE25"
								value="<c:out value="${E25.fieldText}"/>" onblur="formatStr2(this,10)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[24].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体; ">4、成品检验与试验项目/方法/设备是否满足要求、是否按照既定的抽样方案进行检验与试验？ </td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">20</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E26.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E26.count}"/>].fieldText" class="63jspE26"
								value="<c:out value="${E26.fieldText}"/>" onblur="formatStr2(this,20)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[25].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体; ">5、生产关键过程/工序是否有工艺/作业标准、是否按要求执行、关键过程/工序参数是否监控？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">30</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E27.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E27.count}"/>].fieldText" class="63jspE27"
								value="<c:out value="${E27.fieldText}"/>" onblur="formatStr2(this,30)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[26].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="88%" colspan="2" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">得分</td>
				<td width="12%" colspan="2" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E28.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E28.count}"/>].fieldText" class="63jspE28" disabled="disabled"
								value="<c:out value="${E28.fieldText}"/>"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[27].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="100%" colspan="4" valign="top" style="font-size: 10.0pt; font-family: 宋体;">
				基于以下:
					<c:choose>
						<c:when test="${E29.isEdit == '1'}">								
							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E29.count}"/>].fieldText"
								value="1"
								<c:if test="${E29.fieldText == 1 }">checked</c:if>
								id="E29_1" style="opacity: 0;">
							</span>
							<label for="E29_1"><font style="font-size:12px;">合格，可作为二级供应商</font></label>

							&nbsp;<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E29.count}"/>].fieldText"
								value="2" <c:if test="${E29.fieldText == 2}">checked</c:if>
								id="E29_2" style="opacity: 0;">
							</span>
							<label for="E29_2"><font style="font-size:12px;">基本合格，可作为暂用/候备二级供应商</font></label>
										 							
	 						&nbsp;<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E29.count}"/>].fieldText"
								value="3" <c:if test="${E29.fieldText == 3}">checked</c:if>
								id="E29_3" style="opacity: 0;">
							</span>
							<label for="E29_3"><font style="font-size:12px;">不合格，不选为二级供应商</font><br></label>
						
						</c:when>
						<c:otherwise>
							<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[28].fieldText == 1}">checked</c:if>
								id="E29_1" style="opacity: 0;">
							</span>
							<label for="E29_1"><font style="font-size:12px;">合格，可作为二级供应商</font></label>

							&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[28].fieldText == 2}">checked</c:if>
								id="E29_2" style="opacity: 0;">
							</span>
							<label for="E29_2"><font style="font-size:12px;">基本合格，可作为暂用/候备二级供应商</font></label>
	
	 						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[28].fieldText == 3}">checked</c:if>
								id="E29_3" style="opacity: 0;">
							</span>
							<label for="E29_3"><font style="font-size:12px;">不合格，不选为二级供应商</font><br></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${E30.isEdit == '1'}">
							<B><input type="Text" size="110" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E30.count}"/>].fieldText"
								value="<c:out value="${E30.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="110"
								value="<c:out value="${fieldContents[29].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose><br> 
					<c:choose>
						<c:when test="${E31.isEdit == '1'}">
							<B><input type="Text" size="63" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E31.count}"/>].fieldText"
								value="<c:out value="${E31.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="63"
								value="<c:out value="${fieldContents[30].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
					<span style="width:16px;display:inline-block;"></span>
					<font style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
						<c:when test="${E32.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E32.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E32.count}"/>].fieldText"
								<c:if test="${!empty E32.fieldText}">value="<c:out value="${E32.fieldText}"/>"</c:if>
								<c:if test="${empty E32.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[31].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E33.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E33.count}"/>].fieldText"
								<c:if test="${!empty E33.fieldText}">value="<c:out value="${E33.fieldText}"/>"</c:if>
								<c:if test="${empty E33.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[32].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 
				</td>
			</tr>
			<tr>
				<td width="12%" rowspan="5" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">工艺技术<br>(40分)</td>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">1、公司产品设计开发职能/部门人员配置是否充分、测试仪器设备是否齐全？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">25</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E34.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E34.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E34.count}"/>].fieldText" class="63jspE34"
								value="<c:out value="${E34.fieldText}"/>" onblur="formatStr3(this,25)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[33].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">2、公司生产环境是否能满足要求、工艺规划/布局是否合理、生产设备是否满足？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">25</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E35.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E35.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E35.count}"/>].fieldText" class="63jspE35"
								value="<c:out value="${E35.fieldText}"/>" onblur="formatStr3(this,25)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[34].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">3、是否有产品/零部件技术规格书、是否对产品进行测试/认证、测试/认证是否充分？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">20</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E36.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E36.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E36.count}"/>].fieldText" class="63jspE36"
								value="<c:out value="${E36.fieldText}"/>" onblur="formatStr3(this,20)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[35].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">4、生产关键过程/工序是否有工艺/作业标准、是否有效执行、关键过程/工序参数是否监控？ </td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">30</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E37.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E37.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E37.count}"/>].fieldText" class="63jspE37"
								value="<c:out value="${E37.fieldText}"/>" onblur="formatStr3(this,30)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[36].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>
			<!--  <tr>
				<td width="77%" align="left" height=16px
					style="font-size: 12px; font-family: 宋体;">5、生产关键过程/工序是否有工艺/作业标准、是否按要求执行、关键过程/工序参数是否监控？</td>
				<td width="5%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">40</td>
				<td width="6%" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E38.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E38.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E38.count}"/>].fieldText" class="63jspE38"
								value="<c:out value="${E38.fieldText}"/>" onblur="formatStr3(this,40)"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[37].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>	
			</tr>  -->
			<tr>
				<td width="88%" colspan="2" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;">得分</td>
				<td width="12%" colspan="2" align="center" height=16px
					style="font-size: 10.0pt; font-family: 宋体;"><c:choose>
						<c:when test="${E39.isEdit == '1'}">
							<B><input type="Text" size="4"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E39.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E39.count}"/>].fieldText" class="63jspE39" disabled="disabled"
								value="<c:out value="${E39.fieldText}"/>"></B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="4"
								value="<c:out value="${fieldContents[38].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="100%" colspan="4" valign="top" style="font-size: 10.0pt; font-family: 宋体;">
				基于以下:
					<c:choose>
						<c:when test="${E40.isEdit == '1'}">								
							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E40.count}"/>].fieldText"
								value="1"
								<c:if test="${E40.fieldText == 1 }">checked</c:if>
								id="E40_1" style="opacity: 0;">
							</span>
							<label for="E40_1"><font style="font-size:12px;">合格，可作为二级供应商</font></label>

							&nbsp;<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E40.count}"/>].fieldText"
								value="2" <c:if test="${E40.fieldText == 2}">checked</c:if>
								id="E40_2" style="opacity: 0;">
							</span>
							<label for="E40_2"><font style="font-size:12px;">基本合格，可作为暂用/候备二级供应商</font></label>
										 							
	 						&nbsp;<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E40.count}"/>].fieldText"
								value="3" <c:if test="${E40.fieldText == 3}">checked</c:if>
								id="E40_3" style="opacity: 0;">
							</span>
							<label for="E40_3"><font style="font-size:12px;">不合格，不选为二级供应商</font><br></label>
						
						</c:when>
						<c:otherwise>
							<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[39].fieldText == 1}">checked</c:if>
								id="E40_1" style="opacity: 0;">
							</span>
							<label for="E40_1"><font style="font-size:12px;">合格，可作为二级供应商</font></label>

							&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[39].fieldText == 2}">checked</c:if>
								id="E40_2" style="opacity: 0;">
							</span>
							<label for="E40_2"><font style="font-size:12px;">基本合格，可作为暂用/候备二级供应商</font></label>
	
	 						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
								<c:if test="${fieldContents[39].fieldText == 3}">checked</c:if>
								id="E40_3" style="opacity: 0;">
							</span>
							<label for="E40_3"><font style="font-size:12px;">不合格，不选为二级供应商</font><br></label>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${E41.isEdit == '1'}">
							<B><input type="Text" size="110" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E41.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E41.count}"/>].fieldText"
								value="<c:out value="${E41.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="110"
								value="<c:out value="${fieldContents[40].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose><br> 
					<c:choose>
						<c:when test="${E42.isEdit == '1'}">
							<B><input type="Text" size="63" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E42.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E42.count}"/>].fieldText"
								value="<c:out value="${E42.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="63"
								value="<c:out value="${fieldContents[41].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
					<span style="width:16px;display:inline-block;"></span>
					<font style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
						<c:when test="${E43.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E43.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E43.count}"/>].fieldText"
								<c:if test="${!empty E43.fieldText}">value="<c:out value="${E43.fieldText}"/>"</c:if>
								<c:if test="${empty E43.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[42].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E44.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E44.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E44.count}"/>].fieldText"
								<c:if test="${!empty E44.fieldText}">value="<c:out value="${E44.fieldText}"/>"</c:if>
								<c:if test="${empty E44.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[43].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="12%" rowspan="5" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">商务</td>
				<td width="88%" colspan="3" align="left" height=18px
					style="font-size: 10.0pt; font-family: 宋体; padding:1px;">
					<font style="font-size: 9.0pt; font-family: 宋体;">1、公司主要客户：</font> <c:choose>
						<c:when test="${E45.isEdit == '1'}">
							<B><input type="Text" size="69"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E45.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E45.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="69"
								value="<c:out value="${fieldContents[44].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td width="88%" colspan="3" align="left" height=18px
					style="font-size: 10.0pt; font-family: 宋体; padding:1px;">
					<font style="font-size: 9.0pt; font-family: 宋体;">2、公司上年度营业额（万元）：</font> <c:choose>
						<c:when test="${E46.isEdit == '1'}">
							<B><input type="Text" size="59"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E46.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E46.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="59"
								value="<c:out value="${fieldContents[45].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<!--  <tr>
				<td width="88%" colspan="3" align="left" height=18px
					style="font-size: 10.0pt; font-family: 宋体; padding:1px;">
					<font style="font-size: 9.0pt; font-family: 宋体;">3、行业地位：</font> <c:choose>
						<c:when test="${E47.isEdit == '1'}">
							<B><input type="Text" size="46"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E47.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E47.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="46"
								value="<c:out value="${fieldContents[46].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>	
			</tr>  -->
			<tr>
				<td width="88%" colspan="3" align="left" height=18px
					style="font-size: 10.0pt; font-family: 宋体; padding:1px;">
					<font style="font-size: 9.0pt; font-family: 宋体;">3、与一级供应商合作情况：</font> <c:choose>
						<c:when test="${E48.isEdit == '1'}">
							<B><input type="Text" size="62"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E48.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E48.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="62"
								value="<c:out value="${fieldContents[47].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>	
			</tr>
			<tr>
				<td width="88%" colspan="3" align="left" height=18px
					style="font-size: 10.0pt; font-family: 宋体; padding:1px;">
					<font style="font-size: 9.0pt; font-family: 宋体;">4、其上游供应商的支持：</font> <c:choose>
						<c:when test="${E49.isEdit == '1'}">
							<B><input type="Text" size="64"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E49.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E49.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="64"
								value="<c:out value="${fieldContents[48].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>	
			</tr>
			<tr>
				<td width="88%" colspan="3" align="left" height=18px
					style="font-size: 10.0pt; font-family: 宋体; padding:1px;">
					<font style="font-size: 9.0pt; font-family: 宋体;">5、产能情况：</font> <c:choose>
						<c:when test="${E50.isEdit == '1'}">
							<B><input type="Text" size="72"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E50.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E50.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="72"
								value="<c:out value="${fieldContents[49].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				</td>	
			</tr>
			<tr>
				<td width="100%" colspan="4" valign="top" style="font-size: 10.0pt; font-family: 宋体;">
				商务意见:
					<c:choose>
						<c:when test="${E51.isEdit == '1'}">
							<B><input type="Text" size="99" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E51.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E51.count}"/>].fieldText"
								value="<c:out value="${E51.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="99"
								value="<c:out value="${fieldContents[50].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose><br> 
					<c:choose>
						<c:when test="${E52.isEdit == '1'}">
							<B><input type="Text" size="63" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E52.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E52.count}"/>].fieldText"
								value="<c:out value="${E52.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="63"
								value="<c:out value="${fieldContents[51].fieldText}"/>"
								disabled="disabled"
								style="margin-top:10px;font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
				
					<span style="width:16px;display:inline-block;"></span>
					<font style="margin-top:10px;font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
						<c:when test="${E53.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E53.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E53.count}"/>].fieldText"
								<c:if test="${!empty E53.fieldText}">value="<c:out value="${E53.fieldText}"/>"</c:if>
								<c:if test="${empty E53.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[52].fieldText}"/>"
								disabled="disabled"
								style="margin-top:10px;font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E54.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E54.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E54.count}"/>].fieldText"
								<c:if test="${!empty E54.fieldText}">value="<c:out value="${E54.fieldText}"/>"</c:if>
								<c:if test="${empty E54.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[53].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr>
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">审核组<br>综合意见</td>
				<td width="88%" colspan="3" valign="top" style="font-size: 10.0pt; font-family: 宋体;">
					<p style="font-size: 10.0pt; font-family: 宋体;">
						综合评审组意见及判定等级:（评审最终得分:
						<c:choose>
							<c:when test="${E55.isEdit == '1'}">
								<b><input type="Text" size="4"
									AUTOCOMPLETE ="off"
									id="fieldContents[<c:out value="${E55.count}"/>].fieldText"
									name="fieldContents[<c:out value="${E55.count}"/>].fieldText"
									value="<c:out value="${E55.fieldText}"/>"
									style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
								</b>
							</c:when>
							<c:otherwise>
								<b><input type="Text" size="4"
									value="<c:out value="${fieldContents[54].fieldText}"/>"
									disabled="disabled"
									style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
								</b>
							</c:otherwise>
						</c:choose>

						分&nbsp;&nbsp;等级:
						<c:choose>
							<c:when test="${E56.isEdit == '1'}">
								<b><input type="Text" size="3"
									AUTOCOMPLETE ="off"
									id="fieldContents[<c:out value="${E56.count}"/>].fieldText"
									name="fieldContents[<c:out value="${E56.count}"/>].fieldText"
									value="<c:out value="${E56.fieldText}"/>"
									style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
								</b>
							</c:when>
							<c:otherwise>
								<b><input type="Text" size="3"
									value="<c:out value="${fieldContents[55].fieldText}"/>"
									disabled="disabled"
									style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
								</b>
							</c:otherwise>
						</c:choose>
						级）
					</p> <c:choose>
						<c:when test="${E57.isEdit == '1'}">
							<b><input type="Text" size="95" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E57.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E57.count}"/>].fieldText"
								value="<c:out value="${E57.fieldText}"/>"> </b>
						</c:when>
						<c:otherwise>
							<b><input type="Text" size="95"
								value="<c:out value="${fieldContents[56].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</b>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${E58.isEdit == '1'}">
							<b><input type="Text" size="95" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E58.count}"/>].fieldText"
								style="margin-top:5px;font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E58.count}"/>].fieldText"
								value="<c:out value="${E58.fieldText}"/>"> </b>
						</c:when>
						<c:otherwise>
							<b><input type="Text" size="41" maxlength="33"
								value="<c:out value="${fieldContents[57].fieldText}"/>"
								disabled="disabled"
								style="margin-top:10px;font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</b>
						</c:otherwise>
					</c:choose>
					<span style="width:10px;display:inline-block;"></span>
					<font style="margin-top:10px;font-size: 9.0pt; font-family: 宋体;">审核组长:</font> <c:choose>
						<c:when test="${E59.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E59.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E59.count}"/>].fieldText"
								<c:if test="${!empty E59.fieldText}">value="<c:out value="${E59.fieldText}"/>"</c:if>
								<c:if test="${empty E59.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
								style="margin-top:10px;font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[58].fieldText}"/>"
								disabled="disabled"
								style="margin-top:10px;font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E60.isEdit == '1'}">
							<B><input type="Text" size="12"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E60.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E60.count}"/>].fieldText"
								<c:if test="${!empty E60.fieldText}">value="<c:out value="${E60.fieldText}"/>"</c:if>
								<c:if test="${empty E60.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="12"
								value="<c:out value="${fieldContents[59].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 
				</td>
			</tr>
			
			<c:set var="i" value="0" />
			    <c:forEach items="${taskList}" var="wfRdTask">
			       <c:if test="${wfRdTask.stepName=='评审委员会意见'&&wfRdTask.status<5}"><c:set var="i" value="${1}" />  </c:if>
			    </c:forEach>
			    
			<c:if test="${i==1}">
			
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">评审委员会<br>意见</td>
				<td width="88%" colspan="3" valign="top" style="font-size: 10.0pt; font-family: 宋体;">
					评审委员会意见:
					<c:choose>
						<c:when test="${E61.isEdit == '1'}">
							<b><input type="Text" size="78.5" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E61.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E61.count}"/>].fieldText"
								value="<c:out value="${E61.fieldText}"/>"> </b>
						</c:when>
						<c:otherwise>
							<b><input type="Text" size="78.5"
								value="<c:out value="${fieldContents[60].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</b>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${E62.isEdit == '1'}">
							<b><input type="Text" size="96.5" maxlength="33"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E62.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
								name="fieldContents[<c:out value="${E62.count}"/>].fieldText"
								value="<c:out value="${E62.fieldText}"/>"> </b>
						</c:when>
						<c:otherwise>
							<b><input type="Text" size="96.5"
								value="<c:out value="${fieldContents[61].fieldText}"/>"
								disabled="disabled"
								style="margin-top:5px;font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</b>
						</c:otherwise>
					</c:choose><br>
					<span style="width:320px;display:inline-block;"></span>
					<font style="margin-top:5px;font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
						<c:when test="${E63.isEdit == '1'}">
							<B><input type="Text" size="14"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E63.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E63.count}"/>].fieldText"
								<c:if test="${!empty E63.fieldText}">value="<c:out value="${E63.fieldText}"/>"</c:if>
								<c:if test="${empty E63.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="14"
								value="<c:out value="${fieldContents[62].fieldText}"/>"
								disabled="disabled"
								style="margin-top:5px;font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E64.isEdit == '1'}">
							<B><input type="Text" size="10"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E64.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E64.count}"/>].fieldText"
								<c:if test="${!empty E64.fieldText}">value="<c:out value="${E64.fieldText}"/>"</c:if>
								<c:if test="${empty E64.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="10"
								value="<c:out value="${fieldContents[63].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 
				</td>
			</tr>
			
			</c:if>
			
			<tr>
				<td width="12%" align="center" height=18px
					style="font-size: 10.0pt; font-family: 宋体;">评审委员会<br>主任意见</td>
				<td width="88%" colspan="3" valign="top" style="font-size: 10.0pt; font-family: 宋体; padding:1px;">					
					<c:choose>
					<c:when test="${E65.isEdit == '1'}">
						&nbsp;<span class="checkbox input_style"> <input type="radio"
							name="fieldContents[<c:out value="${E65.count}"/>].fieldText"
							value="1"
							<c:if test="${E65.fieldText == 1 }">checked</c:if>
							id="E65_1" style="opacity: 0;">
						</span>
						<label for="E65_1">合格，可作为二级供应商</label>

						&nbsp;<span class="checkbox input_style"> <input type="radio"
							name="fieldContents[<c:out value="${E65.count}"/>].fieldText"
							value="2" <c:if test="${E65.fieldText == 2}">checked</c:if>
							id="E65_2" style="opacity: 0;">
						</span>
						<label for="E65_2">基本合格，可作为基本合格二级供应商（暂用/候备供应商）<br></label>
									 							
 						&nbsp;<span class="checkbox input_style"> <input type="radio"
							name="fieldContents[<c:out value="${E65.count}"/>].fieldText"
							value="3" <c:if test="${E65.fieldText == 3}">checked</c:if>
							id="E65_3" style="opacity: 0;">
						</span>
						<label for="E65_3">不合格，不选为二级供应商</label>
						
						&nbsp;<span class="checkbox input_style"> <input type="radio"
							name="fieldContents[<c:out value="${E65.count}"/>].fieldText"
							value="4" <c:if test="${E65.fieldText == 4}">checked</c:if>
							id="E65_4" style="opacity: 0;">
						</span>
						<label for="E65_4">其它</label>
					</c:when>
					<c:otherwise>					
						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
							<c:if test="${fieldContents[64].fieldText == 1}">checked</c:if>
							id="E65_1" style="opacity: 0;">
						</span>
						<label for="E65_1">合格，可作为二级供应商</label>

						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
							<c:if test="${fieldContents[64].fieldText == 2}">checked</c:if>
							id="E65_2" style="opacity: 0;">
						</span>
						<label for="E65_2">基本合格，可作为基本合格二级供应商（暂用/候备供应商）<br></label>

 						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
							<c:if test="${fieldContents[64].fieldText == 3}">checked</c:if>
							id="E65_3" style="opacity: 0;">
						</span>
						<label for="E65_3">不合格，不选为二级供应商</label><br>
						
						&nbsp;<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
							<c:if test="${fieldContents[64].fieldText == 4}">checked</c:if>
							id="E65_4" style="opacity: 0;">
						</span>
						<label for="E65_4">其它</label>
					</c:otherwise>
				</c:choose> <c:choose>
					<c:when test="${E66.isEdit == '1'}">
						<b><input type="Text" size="38"
							AUTOCOMPLETE ="off"
							id="fieldContents[<c:out value="${E66.count}"/>].fieldText"
							name="fieldContents[<c:out value="${E66.count}"/>].fieldText"
							value="<c:out value="${E66.fieldText}"/>"
							style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
						</b>
					</c:when>
					<c:otherwise>
						<b><input type="Text" size="38"
							value="<c:out value="${fieldContents[65].fieldText}"/>"
							disabled="disabled"
							style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
						</b>
					</c:otherwise>
				</c:choose>
					<span style="width:320px;display:inline-block;"></span>
					<font style="margin-top:5px;font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
						<c:when test="${E67.isEdit == '1'}">
							<B><input type="Text" size="14"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E67.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E67.count}"/>].fieldText"
								<c:if test="${!empty E67.fieldText}">value="<c:out value="${E67.fieldText}"/>"</c:if>
								<%-- <c:if test="${empty E67.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> --%>
								<%-- <c:if test="${empty E67.fieldText}">value="刘立荣"</c:if> --%>
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="14"
								<%-- value="<c:out value="${fieldContents[66].fieldText}"/>" --%>
								disabled="disabled"
								style="margin-top:5px;font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
					<c:choose>
						<c:when test="${E68.isEdit == '1'}">
							<B><input type="Text" size="10"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E68.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E68.count}"/>].fieldText"
								<c:if test="${!empty E68.fieldText}">value="<c:out value="${E68.fieldText}"/>"</c:if>
								<c:if test="${empty E68.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
								style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="10"
								value="<c:out value="${fieldContents[67].fieldText}"/>"
								disabled="disabled"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 
					
				</td>
			</tr>
		</table>

		<table width="686" border="0" align="center" style="">
			<tr>

				<td width="20%" align="right"><font
					style='font-size: 9.0pt; font-family: 宋体;'>GN-R-025 V2.0</font></td>
			</tr>
		</table>

	</div>
</body>
</html>