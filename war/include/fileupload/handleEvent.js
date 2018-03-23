var handleEvent = {
    fileOnLoad: function (file) {
        $("#" + file.id).find(".file-status").html("<div class='status-warpper'><span>文件分析中</span><span class='file-load'></span></div>")
    },
    onSuccess: function (file, response) {
        $("#" + file.id).find(".file-status").text('上传成功');
        $("#" + file.source.uid).attr("data-id", response.attributes.fileNo);
    },
    onError: function (file, error, msg) {
        $("#" + file.id).find(".file-status").text('上传错误');
        alert('连接中断，请刷新页面，重试');
        if(error)
            console.error(error);
        if(msg)
            alert(msg);
    },
    updateProgress: function (file, percentage) {
        var progressBar = $("#" + file.id).find(".progress-" + file.id), pg = $("#" + file.id).find(".progress-bar")[0];
        progressBar.attr('aria-valuenow', percentage * 100);
        pg.style.width = percentage * 100 + "%";
        $("#" + file.id).find(".file-status").text('文件上传中');
    }
}