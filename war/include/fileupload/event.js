Number.prototype.formatSize = function () {
    var sizeText;
    if (this.toString() / 1024 / 1024 / 1024 > 1) {
        sizeText = parseFloat(this.toString() / 1024 / 1024 / 1024).toFixed(2) + "Gb";
    } else if (this.toString() / 1024 / 1024 > 1) {
        sizeText = parseFloat(this.toString() / 1024 / 1024).toFixed(2) + "Mb";
    } else if (this.toString() / 1024 > 1) {
        sizeText = parseFloat(this.toString() / 1024).toFixed(2) + "Kb";
    } else {
        sizeText = parseFloat(this.toString()).toFixed(2) + "b";
    }
    return sizeText;
}


var uploadEvent = function (fileUploader) {
    return {
        fileQueued: function (file) {
            var fileName = file.name.length > 12 ? file.name.substring(0, 12) + '...' : file.name;
            $("#upload_list table").append("<tr id=" + file.id + "><td>" + fileName + "</td><td>" + file.size.formatSize() + "</td><td style='width: 230px'><div class='progress '" + file.id + "><div class='progress-bar' style='width: 0%'></div></div></td><td class='file-status'>等待上传</td><td><a id=" + file.source.uid + ">删除</a></td></tr>")
            $("#" + file.source.uid).unbind().click(function () {
                var fileId = $("#" + file.source.uid).attr("data-id");
                if (fileId) {
                    //删除请求
                    fileUploader.deleteFile(fileId, null, function () {
                        $("#" + file.id).remove();
                    })
                } else {
                    fileUploader.delete(file.id);
                    $("#" + file.id).remove();
                }
                fileUploader.waited--;
            })
            fileUploader.waited++;
        },
        uploadProgress: handleEvent.updateProgress,
        uploadError: function (file, response) {
            if (response && response.isSuccess) {
                handleEvent.updateProgress(file, 1);
                handleEvent.onSuccess(file, response);
                fileUploader.uploadCallBack();
                fileUploader.checkBtnContinue();
            } else {
                if (response && response.message) {
                    handleEvent.onError(file, response)
                } else {
                    if (response && response == "abort") {
                        alert('与服务器连接出错，请重试');
                    }
                }
            }
            fileUploader.waited--;
        },
        uploadAccept: function (file, response) {//上传中
            var deferred = WebUploader.Deferred();
            if (!response.isSuccess) {
                //显示错误信息
                handleEvent.onError(file, null, response.message)
                return false;
            } else {
                deferred.resolve();
            }
        },
        uploadSuccess: function (file, response) {//上传成功
            if (!response.isSuccess) {
                return uploadError(file, response);
            }
            handleEvent.onSuccess(file, response);
            fileUploader.uploadCallBack();
            fileUploader.checkBtnContinue();
            fileUploader.waited--;
        },
        repeatUpload: function (file, response) {
            handleEvent.onSuccess(file, response);
            fileUploader.uploadCallBack();
            fileUploader.checkBtnContinue();
            fileUploader.waited--;
        }
    }
}
