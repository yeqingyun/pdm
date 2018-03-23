<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="upload.OssConstant" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>附件上传</title>
    <link href="./include/css/fileupload-0.1.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="./include/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="./include/fileupload/crypto-js.js"></script>
    <script type="text/javascript" src="./include/fileupload/enc-utf8.js"></script>
    <script type="text/javascript" src="./include/fileupload/enc-base64.js"></script>
    <script src="./include/fileupload/jquery.jsonp.js"></script>
    <script src="./include/fileupload/webuploader.js" type="text/javascript"></script>
    <script src="./include/fileupload/fileupload-0.1.js" type="text/javascript"></script>
    <script src="./include/fileupload/handleEvent.js" type="text/javascript"></script>
    <script src="./include/fileupload/event.js" type="text/javascript"></script>
    <script src="./include/fileupload/operate.js" type="text/javascript"></script>
</head>
<body>
<%--<h1>${windowNo}</h1>--%>
<div id="upload_list">
    <table>
        <tr>
            <th>文件</th>
            <th>文件大小</th>
            <th>上传进度</th>
            <th>上传状态</th>
            <th>操作</th>
        </tr>
    </table>
</div>
<div class="hide" id="dis-mis"></div>
<div class="btn_list">
    <span><button id="pick-file">选择文件</button></span>
    <span><button id="start-upload">开始上传</button></span>
</div>
<script type="text/javascript">
    $(function () {
        var tmp = 0, uploader = new gnifUpload();
        var urls = {
            server: '<%=OssConstant.server.getValue()%>',
            uploadSignature: './FileServiceSignature!getSignatureAndFileInfo.shtml?info='
        }
        var callParam = '${callParam}' ? ${callParam} : '', config = '${config}', uploadCallBack;
        if (config == 'image') {
            config = {
                fileNumLimit: 1,
                accept: {
                    title: 'Images',
                    extensions: 'jpg,png,jpeg,gif,bmp',
                    mimeTypes: 'image/gif,image/jpeg,image/png,image/jpg,image/bmp'
                },
            }

            aauploadCallBack = deleteFile.bind(null,'<%=OssConstant.server.getValue()%>', '<%=OssConstant.code.getValue()%>', ${recentFileNo})
        }
        if (callParam) {
            callParam.param = 'fileNo={fileNo}&size={fileSize}&name={fileName}&usrId=${usrId}&' + callParam.param;
        }
        var uploadConfig = {
            dis: $("#dis-mis"),
            filePickerDiv: $("#pick-file"),
            uploadBtn: $("#start-upload"),
            code: '<%=OssConstant.code.getValue()%>',
            handleEvent: handleEvent,
            tmp: tmp,
            urls: urls,
            callParam: callParam,
            fileNumLimit: config.fileNumLimit,
            accept: config.accept,
            uploadCallBack: uploadCallBack
        }
        uploader.init(uploadConfig);
        uploader.bindEvent(new uploadEvent(uploader), tmp);
    })
</script>
</body>
</html>