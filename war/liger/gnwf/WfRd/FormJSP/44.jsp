<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility: hidden">页面44jsp</span>
<html>

<head>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	font-size: 13px;
}

.input_style {
	padding: 6px 0px;
	width: 600px;
	margin: 0px auto;
	/* border-bottom: #666666 1px dotted;  */
}

span.input_style {
	display: inline-block;
	width: 15px;
	height: 15px;
	text-align: left;
	vertical-align: middle;
	_overflow: hidden;
	margin-right: 4px;
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
label.input_style {
	padding: 9px 6px;
	cursor: pointer;
}

.input_style input {
	cursor: pointer;
}

.checkbox {
	padding: 0px;
	list-style: none;
	width: 600px;
	margin: 2px auto;
	height: auto;
	overflow: hidden;
}

.checkbox li {
	padding: 3px 0px;
	float: left;
}

h4 {
	width: 600px;
	margin: 0px auto;
	margin-top: 30px;
}

.button {
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
<body>
	<div id="bodyDiv">
		<p align="center" style='font-size: 13.0pt; font-family: 宋体;'>
			<b>金&nbsp;立&nbsp;通&nbsp;信&nbsp;设&nbsp;备&nbsp;有&nbsp;限&nbsp;公&nbsp;司<br />
				供应商资格评审报告
			</b>
		</p>
		<br />
		<table border=1 bordercolor=#000000 align=center width=610
			style='font-family: 宋体;'>
			<tr align="center">
				<td colspan="1" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">供应商名称</td>
				<td colspan="4"><c:choose>
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
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"> </B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="14%" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">供应商地址</td>
				<td colspan="4" align="center"><c:choose>
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
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"> </B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="14%" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">供应物料</td>
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
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
				<td width="11%" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">物料类别</td>
				<td width="40%" colspan="2" align="center"><c:choose>
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
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose></td>
			</tr>


			<tr>
				<td width="14%" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">审核类型</td>
				<td colspan="4" align="center" style="font-size: 9.0pt;"><c:choose>
						<c:when test="${E5.isEdit == '1'}">

							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
								value="1" <c:if test="${E5.fieldText == 1}">checked</c:if>
								id="E5_1" style="opacity: 0;">
							</span>
							<label for="E5_1">首次认证</label>


							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
								value="2" <c:if test="${E5.fieldText == 2}">checked</c:if>
								id="E5_2" style="opacity: 0;">
							</span>
							<label for="E5_2">重新认证</label>


							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
								value="3" <c:if test="${E5.fieldText == 3}">checked</c:if>
								id="E5_3" style="opacity: 0;">
							</span>
							<label for="E5_3">整改后认证</label>


							<span class="checkbox input_style"> <input type="radio"
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
								value="4" <c:if test="${E5.fieldText == 4}">checked</c:if>
								id="E5_4" style="opacity: 0;">
							</span>
							<label for="E5_4">其它</label>



						</c:when>
						<c:otherwise>

		  					 <span class="checkbox input_style"> <input type="radio" disabled="disabled"
								<c:if test="${fieldContents[4].fieldText == 1}">checked</c:if>
								id="E5_1" >
							</span>
							<label for="E5_1"> 首次认证</label> 


							 <span class="checkbox input_style"> <input type="radio" disabled="disabled"
								<c:if test="${fieldContents[4].fieldText == 2}">checked</c:if>
								id="E5_2" >
							</span>
							<label for="E5_2"> 重新认证 </label> 


							 <span class="checkbox input_style">  <input type="radio" disabled="disabled"
								<c:if test="${fieldContents[4].fieldText == 3}">checked</c:if>
								id="E5_3" >
							 </span>
							<label for="E5_3"> 整改后认证</label> 


							 <span class="checkbox input_style"> <input type="radio" disabled="disabled"
								<c:if test="${fieldContents[4].fieldText == 4}">checked</c:if>
								id="E5_4" >
							 </span>
							<label for="E5_4"> 其它 </label>




						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${E6.isEdit == '1'}">
							<B><input type="Text" size="25"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E6.count}"/>].fieldText"
								value="<c:out value="${E6.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="25"
								value="<c:out value="${fieldContents[5].fieldText}"/>"
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td width="14%" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">审核时间</td>
				<td colspan="4" align="center"><c:choose>
						<c:when test="${E7.isEdit == '1'}">
							<B><input type="Text" size="5"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E7.count}"/>].fieldText"
								value="<c:out value="${E7.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="5"
								value="<c:out value="${fieldContents[6].fieldText}"/>"
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 年&nbsp;&nbsp; <c:choose>
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
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>月&nbsp;&nbsp; <c:choose>
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
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 共 <c:choose>
						<c:when test="${E10.isEdit == '1'}">
							<B><input type="Text" size="3"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
								name="fieldContents[<c:out value="${E10.count}"/>].fieldText"
								value="<c:out value="${E10.fieldText}"/>"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="3"
								value="<c:out value="${fieldContents[9].fieldText}"/>"
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
							</B>
						</c:otherwise>
					</c:choose> 天</td>
			</tr>
			<tr>
				<td width="14%" align="center" height=21px
					style="font-size: 10.0pt; font-family: 宋体;">审核人员</td>
				<td align="left" style="font-size: 10.0pt; font-family: 宋体;">&nbsp;组长:<c:choose>
						<c:when test="${E11.isEdit == '1'}">
							<B><input type="Text" size="17"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E11.count}"/>].fieldText"
								value="<c:out value="${E11.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="17"
								value="<c:out value="${fieldContents[10].fieldText}"/>"
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose>
				</td>
				<td colspan="3" align="left"
					style="font-size: 10.0pt; font-family: 宋体;">&nbsp;审核员:<c:choose>
						<c:when test="${E12.isEdit == '1'}">
							<B><input type="Text" size="32"
								AUTOCOMPLETE ="off"
								id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"
								name="fieldContents[<c:out value="${E12.count}"/>].fieldText"
								value="<c:out value="${E12.fieldText}"/>"> </B>
						</c:when>
						<c:otherwise>
							<B><input type="Text" size="32"
								value="<c:out value="${fieldContents[11].fieldText}"/>"
								readonly="readonly"
								style="font-size: 9.0pt; font-family: 楷体_GB2312;"></B>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>



			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">
						设计开发<br>评审结论
					</p></td>
				<td colspan="4" valign="top">
					<table width="100%" style="border: 0;">
						<tr>
							<td valign="top">
								<p style="font-size: 10.0pt; font-family: 宋体;">
									根据《供应商评审检查表》审查结果评分为:
									<c:choose>
										<c:when test="${E13.isEdit == '1'}">
											<B><input type="Text" size="5"
												AUTOCOMPLETE ="off"
												id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
												name="fieldContents[<c:out value="${E13.count}"/>].fieldText"
												value="<c:out value="${E13.fieldText}"/>"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</B>
										</c:when>
										<c:otherwise>
											<B><input type="Text" size="5"
												value="<c:out value="${fieldContents[12].fieldText}"/>"
												readonly="readonly"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</B>
										</c:otherwise>
									</c:choose>
									分，基于以下原因:
								</p> <c:choose>
									<c:when test="${E14.isEdit == '1'}">
										<B><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E14.count}"/>].fieldText"
											value="<c:out value="${E14.fieldText}"/>"> </B>
									</c:when>
									<c:otherwise>
										<B><input type="Text" size="80"
											value="<c:out value="${fieldContents[13].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E15.isEdit == '1'}">
										<B><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E15.count}"/>].fieldText"
											value="<c:out value="${E15.fieldText}"/>"> </B>
									</c:when>
									<c:otherwise>
										<B><input type="Text" size="80"
											value="<c:out value="${fieldContents[14].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td style="font-size: 9.0pt;">&nbsp; <c:choose>
									<c:when test="${E16.isEdit == '1'}">
									
										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E16.count}"/>].fieldText"
											value="1"
											<c:if test="${E16.fieldText == 1 }">checked</c:if>
											id="E16_1" style="opacity: 0;">
										</span>
										<label for="E16_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E16.count}"/>].fieldText"
											value="2" <c:if test="${E16.fieldText == 2}">checked</c:if>
											id="E16_2" style="opacity: 0;">
										</span>
										<label for="E16_2">基本合格，可作为暂用/候备供应商<br></label>
				 
				&nbsp;
				 <span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E16.count}"/>].fieldText"
											value="3" <c:if test="${E16.fieldText == 3}">checked</c:if>
											id="E16_3" style="opacity: 0;">
										</span>
										<label for="E16_3">不合格，不选为供应商</label>

									
									</c:when>
									<c:otherwise>

										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[15].fieldText == 1}">checked</c:if>
											id="E16_1" style="opacity: 0;">
										</span>
										<label for="E16_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[15].fieldText == 2}">checked</c:if>
											id="E16_2" style="opacity: 0;">
										</span>
										<label for="E16_2">基本合格，可作为暂用/候备供应商<br></label>
				
				&nbsp;
				 <span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[15].fieldText == 3}">checked</c:if>
											id="E16_3" style="opacity: 0;">
										</span>
										<label for="E16_3">不合格，不选为供应商</label>



									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><font
								style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
									<c:when test="${E17.isEdit == '1'}">
										<B><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E17.count}"/>].fieldText"
											<c:if test="${!empty E17.fieldText}">value="<c:out value="${E17.fieldText}"/>"</c:if>
											<c:if test="${empty E17.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</B>
									</c:when>
									<c:otherwise>
										<B><input type="Text" size="12"
											value="<c:out value="${fieldContents[16].fieldText}"/>"
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:otherwise>
								</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
								<c:choose>
									<c:when test="${E18.isEdit == '1'}">
										<B><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E18.count}"/>].fieldText"
											<c:if test="${!empty E18.fieldText}">value="<c:out value="${E18.fieldText}"/>"</c:if>
											<c:if test="${empty E18.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:when>
									<c:otherwise>
										<B><input type="Text" size="12"
											value="<c:out value="${fieldContents[17].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:otherwise>
								</c:choose> &nbsp;</td>
						</tr>


					</table>
			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">
						工艺工程<br>评审结论
					</p></td>
				<td colspan="4" valign="top">

					<table width="100%" style="border: 0;">
						<tr>
							<td valign="top">
								<p style="font-size: 10.0pt; font-family: 宋体;">
									根据《供应商评审检查表》审查结果评分为:
									<c:choose>
										<c:when test="${E19.isEdit == '1'}">
											<B><input type="Text" size="5"
												AUTOCOMPLETE ="off"
												id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
												name="fieldContents[<c:out value="${E19.count}"/>].fieldText"
												value="<c:out value="${E19.fieldText}"/>"
												style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</B>
										</c:when>
										<c:otherwise>
											<B><input type="Text" size="5"
												value="<c:out value="${fieldContents[18].fieldText}"/>"
												readonly="readonly"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</B>
										</c:otherwise>
									</c:choose>
									分，基于以下原因:
								</p> <c:choose>
									<c:when test="${E20.isEdit == '1'}">
										<B><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E20.count}"/>].fieldText"
											value="<c:out value="${E20.fieldText}"/>"> </B>
									</c:when>
									<c:otherwise>
										<B><input type="Text" size="80"
											value="<c:out value="${fieldContents[19].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E21.isEdit == '1'}">
										<B><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E21.count}"/>].fieldText"
											value="<c:out value="${E21.fieldText}"/>"> </B>
									</c:when>
									<c:otherwise>
										<B><input type="Text" size="80"
											value="<c:out value="${fieldContents[20].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</B>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td style="font-size: 9.0pt;">&nbsp; <c:choose>
									<c:when test="${E22.isEdit == '1'}">

										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E22.count}"/>].fieldText"
											value="1"
											<c:if test="${E22.fieldText == 1 }">checked</c:if>
											id="E22_1" style="opacity: 0;">
										</span>
										<label for="E22_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E22.count}"/>].fieldText"
											value="2" <c:if test="${E22.fieldText == 2}">checked</c:if>
											id="E22_2" style="opacity: 0;">
										</span>
										<label for="E22_2">基本合格，可作为暂用/候备供应商<br></label>
				 
				&nbsp;
				 <span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E22.count}"/>].fieldText"
											value="3" <c:if test="${E22.fieldText == 3}">checked</c:if>
											id="E22_3" style="opacity: 0;">
										</span>
										<label for="E22_3">不合格，不选为供应商</label>


									</c:when>
									<c:otherwise>

										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[21].fieldText == 1}">checked</c:if>
											id="E22_1" style="opacity: 0;">
										</span>
										<label for="E22_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[21].fieldText == 2}">checked</c:if>
											id="E22_2" style="opacity: 0;">
										</span>
										<label for="E22_2">基本合格，可作为暂用/候备供应商<br></label>
				 
				&nbsp;
				 <span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[21].fieldText == 3}">checked</c:if>
											id="E22_3" style="opacity: 0;">
										</span>
										<label for="E22_3">不合格，不选为供应商</label>



									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><font
								style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
									<c:when test="${E23.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E23.count}"/>].fieldText"
											<c:if test="${!empty E23.fieldText}">value="<c:out value="${E23.fieldText}"/>"</c:if>
											<c:if test="${empty E23.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</b>
									</c:when>
									<c:otherwise>
										<b> <input type="Text" size="12"
											value="<c:out value="${fieldContents[22].fieldText}"/>"
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
								<c:choose>
									<c:when test="${E24.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E24.count}"/>].fieldText"
											<c:if test="${!empty E24.fieldText}">value="<c:out value="${E24.fieldText}"/>"</c:if>
											<c:if test="${empty E24.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b> <input type="Text" size="12"
											value="<c:out value="${fieldContents[23].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> &nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>



			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">
						品质认证<br>评审结论
					</p></td>
				<td colspan="4" valign="top">

					<table width="100%" style="border: 0;">
						<tr>
							<td valign="top">
								<p style="font-size: 10.0pt; font-family: 宋体;">
									根据《供应商评审检查表》审查结果评分为:
									<c:choose>
										<c:when test="${E25.isEdit == '1'}">
											<b><input type="Text" size="5"
												AUTOCOMPLETE ="off"
												id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
												name="fieldContents[<c:out value="${E25.count}"/>].fieldText"
												value="<c:out value="${E25.fieldText}"/>"
												style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:when>
										<c:otherwise>
											<b><input type="Text" size="5"
												value="<c:out value="${fieldContents[24].fieldText}"/>"
												readonly="readonly"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:otherwise>
									</c:choose>
									分，基于以下原因:
								</p> <c:choose>
									<c:when test="${E26.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="42"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E26.count}"/>].fieldText"
											value="<c:out value="${E26.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[25].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E27.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="42"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E27.count}"/>].fieldText"
											value="<c:out value="${XE22.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[26].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td style="font-size: 9.0pt;">&nbsp; <c:choose>
									<c:when test="${E28.isEdit == '1'}">

										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E28.count}"/>].fieldText"
											value="1"
											<c:if test="${E28.fieldText == 1 }">checked</c:if>
											id="E28_1" style="opacity: 0;">
										</span>
										<label for="E28_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E28.count}"/>].fieldText"
											value="2" <c:if test="${E28.fieldText == 2}">checked</c:if>
											id="E28_2" style="opacity: 0;">
										</span>
										<label for="E28_2">基本合格，可作为暂用/候备供应商<br></label>
				 &nbsp;
				
				 <span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E28.count}"/>].fieldText"
											value="3" <c:if test="${E28.fieldText == 3}">checked</c:if>
											id="E28_3" style="opacity: 0;">
										</span>
										<label for="E28_3">不合格，不选为供应商</label>


									</c:when>
									<c:otherwise>

										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[27].fieldText == 1}">checked</c:if>
											id="E28_1" style="opacity: 0;">
										</span>
										<label for="E28_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[27].fieldText == 2}">checked</c:if>
											id="E28_2" style="opacity: 0;">
										</span>
										<label for="E28_2">基本合格，可作为暂用/候备供应商<br></label>
				 &nbsp;
				
				 <span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[27].fieldText == 3}">checked</c:if>
											id="E28_3" style="opacity: 0;">
										</span>
										<label for="E28_3">不合格，不选为供应商</label>



									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><font
								style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
									<c:when test="${E29.isEdit == '1'}">
										<b> <input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E29.count}"/>].fieldText"
											<c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
											<c:if test="${empty E29.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</b>
									</c:when>
									<c:otherwise>
										<b> <input type="Text" size="12"
											value="<c:out value="${fieldContents[28].fieldText}"/>"
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> <font style="font-size: 9.0pt; font-family: 宋体;"> 日期:</font> <c:choose>
									<c:when test="${E30.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E30.count}"/>].fieldText"
											<c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
											<c:if test="${empty E30.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[29].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> &nbsp;</td>
						</tr>
					</table>

				</td>
			</tr>



			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">
						质量管理<br>体系结论
					</p></td>
				<td colspan="4" valign="top">

					<table width="100%" style="border: 0;">
						<tr>
							<td valign="top">
								<p style="font-size: 10.0pt; font-family: 宋体;">
									根据《供应商评审检查表》审查结果评分为:
									<c:choose>
										<c:when test="${E31.isEdit == '1'}">
											<b><input type="Text" size="5"
												AUTOCOMPLETE ="off"
												id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
												name="fieldContents[<c:out value="${E31.count}"/>].fieldText"
												value="<c:out value="${E31.fieldText}"/>"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:when>
										<c:otherwise>
											<b><input type="Text" size="5"
												value="<c:out value="${fieldContents[30].fieldText}"/>"
												readonly="readonly"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:otherwise>
									</c:choose>
									分，基于以下原因:
								</p> <c:choose>
									<c:when test="${E32.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="41"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E32.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E32.count}"/>].fieldText"
											value="<c:out value="${E32.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[31].fieldText}"/>"
											readonly="readonly" min="0" max="10"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E33.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="41"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E33.count}"/>].fieldText"
											value="<c:out value="${E33.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[32].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td style="font-size: 9.0pt;">&nbsp; <c:choose>
									<c:when test="${E34.isEdit == '1'}">

										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E34.count}"/>].fieldText"
											value="1"
											<c:if test="${E34.fieldText == 1 }">checked</c:if>
											id="E34_1" style="opacity: 0;">
										</span>
										<label for="E34_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E34.count}"/>].fieldText"
											value="2" <c:if test="${E34.fieldText == 2}">checked</c:if>
											id="E34_2" style="opacity: 0;">
										</span>
										<label for="E34_2">基本合格，可作为暂用/候备供应商<br></label>
				 
				&nbsp;
				 <span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E34.count}"/>].fieldText"
											value="3" <c:if test="${E34.fieldText == 3}">checked</c:if>
											id="E34_3" style="opacity: 0;">
										</span>
										<label for="E34_3">不合格，不选为供应商</label>


									</c:when>
									<c:otherwise>

										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[33].fieldText == 1}">checked</c:if>
											id="E34_1" style="opacity: 0;">
										</span>
										<label for="E34_1">合格，可选为供应商</label>


										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[33].fieldText == 2}">checked</c:if>
											id="E34_2" style="opacity: 0;">
										</span>
										<label for="E34_2">基本合格，可作为暂用/候备供应商<br></label>
				 
				&nbsp;
				 <span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[33].fieldText == 3}">checked</c:if>
											id="E34_3" style="opacity: 0;">
										</span>
										<label for="E34_3">不合格，不选为供应商</label>



									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><font
								style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
									<c:when test="${E35.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E35.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E35.count}"/>].fieldText"
											<c:if test="${!empty E35.fieldText}">value="<c:out value="${E35.fieldText}"/>"</c:if>
											<c:if test="${empty E35.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</b>
									</c:when>
									<c:otherwise>
										<b> <input type="Text" size="12"
											value="<c:out value="${fieldContents[34].fieldText}"/>"
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>&nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
								<c:choose>
									<c:when test="${E36.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E36.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E36.count}"/>].fieldText"
											<c:if test="${!empty E36.fieldText}">value="<c:out value="${E36.fieldText}"/>"</c:if>
											<c:if test="${empty E36.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[35].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> &nbsp;</td>
						</tr>
					</table>

				</td>
			</tr>



			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">综合意见：</p></td>
				<td colspan="4" valign="top">

					<table width="100%" style="border: 0;">
						<tr>
							<td valign="top">
								<p style="font-size: 10.0pt; font-family: 宋体;">
									综合评审组意见及判定等级:（评审最终得分:
									<c:choose>
										<c:when test="${E37.isEdit == '1'}">
											<b><input type="Text" size="5"
												AUTOCOMPLETE ="off"
												id="fieldContents[<c:out value="${E37.count}"/>].fieldText"
												name="fieldContents[<c:out value="${E37.count}"/>].fieldText"
												value="<c:out value="${E37.fieldText}"/>"
												style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:when>
										<c:otherwise>
											<b><input type="Text" size="5"
												value="<c:out value="${fieldContents[36].fieldText}"/>"
												readonly="readonly"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:otherwise>
									</c:choose>

									分，判定等级:
									<c:choose>
										<c:when test="${E38.isEdit == '1'}">
											<b><input type="Text" size="3"
												AUTOCOMPLETE ="off"
												id="fieldContents[<c:out value="${E38.count}"/>].fieldText"
												name="fieldContents[<c:out value="${E38.count}"/>].fieldText"
												value="<c:out value="${E38.fieldText}"/>"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:when>
										<c:otherwise>
											<b><input type="Text" size="3"
												value="<c:out value="${fieldContents[37].fieldText}"/>"
												readonly="readonly"
												style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
											</b>
										</c:otherwise>
									</c:choose>
									级）
								</p> <c:choose>
									<c:when test="${E39.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E39.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E39.count}"/>].fieldText"
											value="<c:out value="${E39.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[38].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E40.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E40.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E40.count}"/>].fieldText"
											value="<c:out value="${E40.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[39].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><font
								style="font-size: 9.0pt; font-family: 宋体;">审核组长:</font>
							<c:choose>
									<c:when test="${E41.isEdit == '1'}">
										<b> <input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E41.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E41.count}"/>].fieldText"
											<c:if test="${!empty E41.fieldText}">value="<c:out value="${E41.fieldText}"/>"</c:if>
											<c:if test="${empty E41.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[40].fieldText}"/>"
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>&nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
								<c:choose>
									<c:when test="${E42.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E42.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E42.count}"/>].fieldText"
											<c:if test="${!empty E42.fieldText}">value="<c:out value="${E42.fieldText}"/>"</c:if>
											<c:if test="${empty E42.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[41].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>&nbsp;</td>
						</tr>
					</table>

				</td>
			</tr>



  <c:set var="i" value="0" />
    <c:forEach items="${taskList}" var="wfRdTask">
       <c:if test="${wfRdTask.stepName=='公司副总裁意见'&&wfRdTask.status<5}"><c:set var="i" value="${1}" />  </c:if>
    </c:forEach>
  
   <c:if test="${i==1}">

			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">
						公司副总裁</br>意见：
					</p></td>
				<td colspan="4" valign="top">

					<table width="100%" style="border: 0;">
						<tr>
							<td valign="top">
								<p style="font-size: 10.0pt; font-family: 宋体;">公司副总裁意见:</p> <c:choose>
									<c:when test="${E43.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E43.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E43.count}"/>].fieldText"
											value="<c:out value="${E43.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[42].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E44.isEdit == '1'}">
										<b><input type="Text" size="80" maxlength="40"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E44.count}"/>].fieldText"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;"
											name="fieldContents[<c:out value="${E44.count}"/>].fieldText"
											value="<c:out value="${E44.fieldText}"/>"> </b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="80"
											value="<c:out value="${fieldContents[43].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right"><font
								style="font-size: 9.0pt; font-family: 宋体;">签名:</font>
							<c:choose>
									<c:when test="${E45.isEdit == '1'}">
										<b> <input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E45.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E45.count}"/>].fieldText"
											<c:if test="${!empty E45.fieldText}">value="<c:out value="${E45.fieldText}"/>"</c:if>
											<c:if test="${empty E45.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[44].fieldText}"/>"
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>&nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
								<c:choose>
									<c:when test="${E46.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E46.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E46.count}"/>].fieldText"
											<c:if test="${!empty E46.fieldText}">value="<c:out value="${E46.fieldText}"/>"</c:if>
											<c:if test="${empty E46.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[45].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>&nbsp;</td>
						</tr>
					</table>

				</td>
			</tr>
			
		 </c:if>	
			
			
			
			
			

			<tr>
				<td width="14%"><p align="center"
						style="font-size: 10.0pt; font-family: 宋体;">公司意见：</p></td>
				<td colspan="4" valign="top">

					<table width="100%" style="border: 0;">
						<tr>
							<td align="left" style="font-size: 9.0pt;">&nbsp; <c:choose>
									<c:when test="${E47.isEdit == '1'}">

										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E47.count}"/>].fieldText"
											value="1"
											<c:if test="${E47.fieldText == 1 }">checked</c:if>
											id="E47_1" style="opacity: 0;">
										</span>
										<label for="E47_1">可作为合格供应商<br>&nbsp;
										</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E47.count}"/>].fieldText"
											value="2" <c:if test="${E47.fieldText == 2}">checked</c:if>
											id="E47_2" style="opacity: 0;">
										</span>
										<label for="E47_2">可作为基本合格供应商（暂用/候备）<br>&nbsp;
										</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E47.count}"/>].fieldText"
											value="3" <c:if test="${E47.fieldText == 3}">checked</c:if>
											id="E47_3" style="opacity: 0;">
										</span>
										<label for="E47_3">不选为供应商<br>&nbsp;
										</label>


										<span class="checkbox input_style"> <input type="radio"
											name="fieldContents[<c:out value="${E47.count}"/>].fieldText"
											value="4" <c:if test="${E47.fieldText == 4}">checked</c:if>
											id="E47_4" style="opacity: 0;">
										</span>
										<label for="E47_4">其他</label>

									</c:when>
									<c:otherwise>

										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[46].fieldText == 1}">checked</c:if>
											id="E47_1" style="opacity: 0;">
										</span>
										<label for="E47_1">可作为合格供应商<br>&nbsp;
										</label>


										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[46].fieldText == 2}">checked</c:if>
											id="E47_2" style="opacity: 0;">
										</span>
										<label for="E47_2">可作为基本合格供应商（暂用/候备）<br>&nbsp;
										</label>


										<span class="checkbox input_style"> <input type="radio" v
											<c:if test="${fieldContents[46].fieldText == 3}">checked</c:if>
											id="E47_3" style="opacity: 0;">
										</span>
										<label for="EE47_3">不选为供应商<br>&nbsp;
										</label>


										<span class="checkbox input_style"> <input type="radio"  disabled="disabled"
											<c:if test="${fieldContents[46].fieldText == 4}">checked</c:if>
											id="E47_4" style="opacity: 0;">
										</span>
										<label for="EE47_4">其他</label>



									</c:otherwise>
								</c:choose> <c:choose>
									<c:when test="${E48.isEdit == '1'}">
										<b><input type="Text" size="20"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E48.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E48.count}"/>].fieldText"
											value="<c:out value="${E48.fieldText}"/>"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="20"
											value="<c:out value="${fieldContents[47].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>

							</td>
						</tr>
						<tr>
							<td align="right" valign="bottom"><font
								style="font-size: 9.0pt; font-family: 宋体;">签名:</font> <c:choose>
									<c:when test="${E49.isEdit == '1'}">
										<b> <input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E49.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E49.count}"/>].fieldText"
											<c:if test="${!empty E49.fieldText}">value="<c:out value="${E49.fieldText}"/>"</c:if>
											<%-- <c:if test="${empty E49.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> --%>
											<c:if test="${empty E49.fieldText}">value="刘立荣"</c:if>
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;" />
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											 value="<c:out value="${fieldContents[48].fieldText}"/>" 
											<%-- value="<c:out value="刘立荣"/>" --%>
											disabled="disabled"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose> &nbsp;<font style="font-size: 9.0pt; font-family: 宋体;">日期:</font>
								<c:choose>
									<c:when test="${E50.isEdit == '1'}">
										<b><input type="Text" size="12"
											AUTOCOMPLETE ="off"
											id="fieldContents[<c:out value="${E50.count}"/>].fieldText"
											name="fieldContents[<c:out value="${E50.count}"/>].fieldText"
											<c:if test="${!empty E50.fieldText}">value="<c:out value="${E50.fieldText}"/>"</c:if>
											<c:if test="${empty E50.fieldText}">value="<fmt:formatDate value="${currentDate}" pattern="yyyy.MM.dd"/>"</c:if>
											style="font-size: 9.0pt; border: 1px; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:when>
									<c:otherwise>
										<b><input type="Text" size="12"
											value="<c:out value="${fieldContents[49].fieldText}"/>"
											readonly="readonly"
											style="font-size: 9.0pt; font-family: 楷体_GB2312; background-color: #EFF0F2; border-bottom-style: solid; border-top-style: none; border-left-style: none; border-right-style: none;">
										</b>
									</c:otherwise>
								</c:choose>&nbsp;</td>
						</tr>

					</table>

				</td>

			</tr>

		</table>


		<table width="610" border="0" align="center" style="">
			<tr>

				<td width="20%" align="right"><font
					style='font-size: 9.0pt; font-family: 宋体;'>GN-R-025 V2.0</font></td>
			</tr>
		</table>

	</div>
</body>
</html>