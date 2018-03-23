<%@page contentType="text/html;charset=utf-8" %>
<%@ page import="upload.OssConstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>帮助模块</title>


    <script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="./include/js/oa.js"></script>
    <script language="JavaScript">var actionUri = 'WfRdView';</script>
    <script language="JavaScript" src="./include/js/ifrma.js"></script>
    <script language="JavaScript" src="./include/js/Xmlhttp.js"></script>
    <script language="JavaScript" src="./include/js/gnoa/WfRd.js"></script>

    <link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
    <link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
    <link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="./include/css/gnwf/WfRd/WFDesc/css/css.css"/>

    <script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>

    <script type="text/javascript" src="./include/fileupload/jquery.jsonp.js"></script>
    <script type="text/javascript" src="./include/fileupload/operate.js"></script>

    <script type="text/javascript">
        $(function () {
            var recentFileNo = null;
            //load workflow image
            function loadImageDiv() {
                $.post("WfRdView!getAddRdExtendUri.shtml?WfRd.FlowId=<c:out value='${wfRd.flowId}'/>", {},
                    function (data, status) {
                        if ("success" == status && data.Total > 0 && data.Rows[0].addRdExtendUri.length > 0) {
                            var gngile_download_URL = $("#gngile_download_URL").val();
                            var fileNo = data.Rows[0].addRdExtendUri, url;
                            recentFileNo = fileNo;
                            if (isNaN(fileNo)) {
                                url = gngile_download_URL + '?fileNo=' + fileNo;
                            } else {
                                url = '<%=OssConstant.server.getValue()%>' + '<%=OssConstant.downloadURL.getValue()%>' + getDownLoadParam('<%=OssConstant.code.getValue()%>', fileNo);
                            }
                            $("#imagediv").html('<img alt="error" width="100%" src="' + url + '">');
                        } else {
                            $("#imagediv").html("还没上传");
                        }
                    }, "json");
            }

            $("#imageUpload").bind('click', onUpload);
            loadImageDiv();


            function onUpload() {
                var userId = "<c:out value='${user.id}'/>";
                var userNm = "<c:out value='${currentUserName}'/>";
                var flowId = "<c:out value='${wfRd.flowId}'/>";
                var server_URL = $('#server_URL').val();
//
//                var gngile_uploadURL = $('#gngile_upload_URL').val();
//                var httpUrl = gngile_uploadURL + "?";
//                httpUrl += 'syId=1&syNm=PDM&usrId=' + userId + '&usrNm=' + userNm
//                    + '&diyFolder=wfdesc&callBackUrl=' + server_URL
//                    + "/WfRdView!setRdExtendUri.shtml" + '&tempParams=' + 'flowId:' + flowId + ",userId:" + userId;
//
//                httpUrl = encodeURI(httpUrl);
//                var uploadDialog = $.ligerDialog.open({
//                    title: '上传附件',
//                    height: 450,
//                    width: 470,
//                    url: httpUrl,
//                    showToggle: true,
//                    showMin: true,
//                    isResize: true
//                });
//
//                $(".l-dialog-close").click(function () {
//                    loadImageDiv();
//                });


                var tempPar = 'tempParams=' + 'flowId:' + flowId + ",userId:" + userId;
                var data = {
                    url: server_URL + "/WfRdView!setRdExtendUri.shtml",
                    param: tempPar
                };
                openImgUpload('${usrId}', data, recentFileNo, loadImageDiv)
            }

            function makeUrl(httpUrl, str, value) {
                if (value != null && value != '' && value != -1) {
                    httpUrl = httpUrl + str + value;
                }
                return httpUrl;
            }


            function submitFile() { //上传附件
                if (confirm("您确定要上传附件吗？")) {
                    var form = document.getElementById("view-form");
                    form.action = 'WfRdView!addFiles.shtml';
                    form.target = "_self";
                    form.submit();
                }
            }


        });

    </script>
</head>
<div class="frmInsetCon">
    <form id="wfRd-form" name="wfRd-form" method="post" action="WfRdView.shtml" onsubmit="return check();">

        <input type="hidden" id="actUri" value="WfRdView">
        <input type="hidden" id="ediUri" value="WfRdModify">
        <input type="hidden" id="addUri" value="WfRdAdd">
        <input type="hidden" id="actParam" value="?wfRd.wfNo=<c:out value="${wfRd.wfNo}"/>">

        <input type="hidden" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
        <input type="hidden" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>">
        <input type="hidden" name="currentTask.taskId" value="<c:out value="${currentTask.taskId}"/>">

        <input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30"
               value="<c:out value="${initParam.gngile_upload_URL}"/>"/>
        <input type="hidden" id="gngile_download_URL" name="gngile_download_URL" size="30"
               value="<c:out value="${initParam.gngile_download_URL}"/>"/>
        <input type="hidden" id="server_URL" name="server_URL" size="30"
               value="<c:out value="${initParam.server_URL}"/>"/>

        <body>
        <div class="box" style="width:auto ">
            <div class="content01 margin22">
                <table width="100%" border="0">
                    <tr class="titie font16">
                        <td class="rtBg f">工作流名称</td>
                        <td>工作流编号</td>
                        <td>工作流分类</td>
                        <td>主导部门</td>
                        <td>公司</td>
                    </tr>
                    <tr class="contBg">
                        <td class="f"><c:out value="${cfg.flowName}"/></td>
                        <td><c:out value="${cfg.flowCode}"/></td>
                        <td><c:out value="${cfg.cateName}"/></td>
                        <td><c:out value="${cfg.deptName}"/></td>
                        <td><c:out value="${cfg.comName}"/></td>
                    </tr>
                </table>
            </div>
            <c:if test="${!empty deptList}">
                <div class="content02 margin22">
                    <div class="titie">
                        <h2 class="font16">相关部门</h2>
                    </div>
                    <div class="con"><c:forEach items="${deptList}" var="wfdept" varStatus="s">
                        <c:out value="${wfdept.deptName}"/>、&nbsp;&nbsp;
                        <c:if test="${s.count%6==0}"><br></c:if>
                    </c:forEach>
                    </div>
                </div>
            </c:if>

            <c:if test="${!empty cfg.flowDesc}">
                <div class="content03 margin22">
                    <div class="titie">
                        <h2 class="font16 h01">工作流描写</h2>
                    </div>
                    <div class="con"><c:out value="${cfg.flowDesc}"/></div>
                </div>
            </c:if>


            <div class="content04 margin22">
                <div class="titie">
                    <h2 class="font16 h02">工作流步骤</h2>
                </div>
                <div>
                    <table width="100%" border="0">
                        <tr>
                            <th width="10%" style="text-align:center;">步骤序号</th>
                            <th width="40%" style="text-align:center;">名称</th>
                            <th class="bordNone" width="50%" style="text-align:center;">流程可选方向</th>
                        </tr>
                        <c:forEach items="${allSteps}" var="step" varStatus="s">
                            <tr <c:if test="${s.count%2==0}"> class="trBg bord01" </c:if>>
                                <td>
                                    <c:out value="${step.sort}"/>
                                </td>
                                <td>
                                    <c:out value="${step.stepName}"/>
                                </td>
                                <td class="bordNone" style="text-align:left;">
                                    <c:set var="nextCount" value="0"/>
                                    <c:forEach items="${allNextSteps}" var="nextStep">
                                        <c:if test="${step.stepId==nextStep.stepId}">
                                            <span class="arrow"><img
                                                    src="./include/css/gnwf/WfRd/WFDesc/images/arrow_bl.png"/></span>
                                            <c:out value="${nextStep.sort}"/>
                                            <c:if test="${nextStep.stepType==1}"></c:if>
                                            <c:if test="${nextStep.stepType==2}">(分支)</c:if>
                                            <c:if test="${nextStep.stepType==3}">(并发)</c:if>&nbsp;&nbsp;
                                            <c:set var="nextCount" value="${nextCount+1}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${step.isLastStep==1}">
                                        <span class="arrow"><img
                                                src="./include/css/gnwf/WfRd/WFDesc/images/arrow_bl.png"/></span>结束
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>


            <div class="content05 margin22">
                <div class="titie">
                    <h2 class="font16 h03">工作流模板文件</h2>
                </div>
                <div>
                    <table width="100%" border="0">

                        <c:if test="${empty docList}">
                            <tr class="trBg bord01">
                                <td>暂无附件</td>
                            </tr>
                        </c:if>

                        <c:forEach items="${docList}" var="doc">
                            <tr <c:if test="${s.count%2!=0}"> class="trBg bord01"</c:if>>
                                <td>
                                    <img align="middle" src="./include/img/workflow/<c:out value="${doc.icon}"/>"/>
                                    <a href="WfDocDownload.shtml?wfDoc.docId=<c:out value="${doc.docId}"/>">
                                        附件(点击可下载或查看)：<c:out value="${doc.docName}"/>
                                    </a>
                                </td>
                                <td>版本：<c:out value="${doc.docVer}"/></td>
                                <td>上传人：<c:out value="${doc.createName}"/></td>
                                <td>上传时间：<c:out value="${doc.createDate}"/></td>
                            </tr>
                        </c:forEach>


                    </table>
                </div>
            </div>


            <div class="content06 margin22">
                <div class="titieT">
                    <h2 class="font16 h04">流程图</h2>
                    <ul>
                        <li><a name="#imageUploada"></a> <a id="imageUpload" href="#imageUploada">上传(点击上传)</a></li>
                    </ul>
                </div>
                <div id="imagediv"></div>
            </div>
        </div>
        </body>
</html>
