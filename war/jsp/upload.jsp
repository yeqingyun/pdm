<html>
<head>
<title>Upload File Page</title>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/jquery/jquery.uploadify.js" type="text/javascript"></script>
<link rel="stylesheet" href="CSS/uploadify.css" type="text/css" media="screen" />
</head>
<body>
<input type="file" name="fileInp" id="fileInp" />
<a href="javascript:$('#fileInp').fileUploadStart();">Upload Files</a> | 
<a href="javascript:$('#fileInp').fileUploadClearQueue();">Clear Queue</a>
<script type="text/javascript">
    $(document).ready(function() {
        $('#fileInput').fileUpload({
            'uploader': './include/liger/jquery/uploader.swf',       //上传文件的进度条
            'script': './Def!imp.shtml',         //上传文件的后台处理页面
            'cancelImg': './include/liger/jquery/uploadify-cancel.png',     //取消上传的图片
            'auto': false,
            'multi': true,
            'simUploadLimit':4,                   //上传文件大小的限制
            'folder': '/upload',              //上传的文件夹
            'onComplete': function(event, queueID, fileObj, response, data) {             //上传完成后的操作
                alert(response);
            }
        });
    });
</script>
</body>
</html> 