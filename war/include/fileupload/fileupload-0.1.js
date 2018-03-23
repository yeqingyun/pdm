var gnifUpload = function () {

    //判断flash版本
    var flashVersion = function () {
        var version;
        try {
            version = navigator.plugins['Shockwave Flash'];
            version = version.description;
        } catch (ex) {
            try {
                version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                    .GetVariable('$version');
            } catch (ex2) {
                version = '0.0';
            }
        }
        version = version.match(/\d+/g);
        return parseFloat(version[0] + '.' + version[1], 10);
    }

    //根据flash显示提示信息
    var hintFlashInfo = function () {
        if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {
            if (flashVersion()) {
                alert("flash版本过低，无法上传");
            } else {
                alert("没有安装flash，无法上传");
            }
            return false;
        } else if (!WebUploader.Uploader.support()) {
            alert("上传控件不支持当前浏览器，请换一个浏览器后重试");
            return false;
        }
        return true;
    }


    var createUploader = function (filePicker, code, handleEvent, tmp, urls, call, fileNumLimit, accept) {
        var uploadUrl, server = urls.server, savePath = savePath;
        WebUploader.Uploader.register({
            "before-send-file": "beforeSendFile",
            "before-send": "beforeSend",
            "after-send-file": "afterSendFile"
        }, {
            //step1：所有分块进行上传之前调用此函数
            beforeSendFile: function (file) {
                var deferred = WebUploader.Deferred(), ow = this.owner;

                if (file.size > 52428800) {//文件大于50M
                    uploadUrl = server + "/upload.html";
                    this.owner.options.server = uploadUrl;
                    handleEvent.fileOnLoad(file);
                    (new WebUploader.Uploader()).md5File(file, 0, file.size).then(function (val) {
                        file.md5 = val;
                        var param = JSON.parse(sendRequest(urls.uploadSignature, encodeURIComponent(encodeURIComponent(file.md5 + "\n" + file.name + "\n" + file.size))));
                        var jsonpData = {
                            code: code,
                            signature: encodeURIComponent(param.signature),
                            fileInfo: encodeURIComponent(param.fileInfo),
                            tmp: tmp,
                            step: 1
                        };
                        if (call) {
                            jsonpData.call = call;
                        }
                        $.jsonp({
                            async: true,
                            type: "POST",
                            url: uploadUrl,
                            callbackParameter: "callback",
                            callback: "uploadcallback",
                            data: jsonpData,
                            success: function (response) {
                                if (response.isSuccess) {
                                    if (!response.notRepeat) {
                                        //文件存在，跳过
                                        deferred.reject(response);
                                        //uploader.trigger('repeatUpload', file, response);
                                    } else {
                                        deferred.resolve();
                                    }
                                } else {
                                    deferred.reject(response);
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                uploader.stop(true);
                                deferred.reject();
                                handleEvent.onError(file, errorThrown);
                            }
                        });
                    });


                } else {
                    uploadUrl = server + "/common_upload.html";
                    this.owner.options.server = uploadUrl;
                    handleEvent.fileOnLoad(file);
                    (new WebUploader.Uploader()).md5File(file, 0, file.size).then(function (val) {
                        ow.options.formData.fileMd5 = val;
                        deferred.resolve();
                    });
                }
                return deferred.promise();
            },
            //step2：如果有分块上传，则每个分块上传之前调用此函数
            beforeSend: function (block) {
                var deferred = WebUploader.Deferred(), ow = this.owner, file = block.file;

                if (file.size > 52428800) {
                    (ow).md5File(block.file, block.chunk * ow.options.chunkSize, block.chunk * ow.options.chunkSize + parseInt(block.end - block.start)).then(function (val) {

                        $.jsonp({
                            type: "POST",
                            url: uploadUrl,
                            callbackParameter: "callback",
                            callback: "uploadcallback",
                            data: {
                                //文件名称
                                fileName: block.file.name,
                                //文件md5
                                fileMd5: block.file.md5,
                                //文件大小
                                fileSize: block.file.size,
                                //当前分块名称，即起始位置
                                chunkOrder: block.chunk != 0 ? block.chunk : block.chunks,
                                //当前分块大小
                                chunkSize: block.end - block.start,
                                step: 2
                            },
                            success: function (response) {
                                if (response.isSuccess) {
                                    if (!response.notRepeat) {
                                        //文件块存在，跳过
                                        deferred.reject();
                                    } else {
                                        //分块不存在或不完整，重新发送该分块内容
                                        deferred.resolve();
                                    }
                                } else {
                                    //如果是文件出错，则停止上传
                                    uploader.trigger('uploadError', file, response);
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                deferred.reject();
                                uploader.stop(true);
                                handleEvent.onError(file, errorThrown);
                            }
                        });

                        ow.options.formData.fileMd5 = block.file.md5;
                        ow.options.formData.chunk = block.chunk != 0 ? block.chunk : block.chunks;
                        ow.options.formData.chunkSize = block.end - block.start;
                        ow.options.formData.chunkMd5 = val;
                    });
                } else {
                    deferred.resolve();
                }
                return deferred.promise();

            },
            //step3：所有分块上传成功后调用此函数
            afterSendFile: function (file) {
                var deferred = WebUploader.Deferred();
                if (file.size > 52428800) {
                    //如果分块上传成功，则通知后台合并分块
                    var data = {
                        //文件名称
                        fileName: file.name,
                        //文件唯一标记
                        fileMd5: file.md5,
                        //文件大小
                        fileSize: file.size,
                        step: 4,
                        code: code,
                        tmp: tmp
                    };

                    if (data.callParam) {
                        data.call = encodeURIComponent(CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(JSON.stringify(data.callParam))));
                    }

                    if (savePath) {
                        data.savePath = savePath;
                    }


                    $.jsonp({
                        type: "POST",
                        url: uploadUrl,
                        callbackParameter: "callback",
                        callback: "uploadcallback",
                        data: data,
                        success: function (response) {
                            if (response.isSuccess) {
                                uploader.trigger('uploadSuccess', file, response);
                            } else {
                                handleEvent.updateProgress(file, 0);
                                uploader.trigger('uploadError', file, response);
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            uploader.stop(true);
                            deferred.reject();
                            handleEvent.onError(file, errorThrown)
                        }
                    });
                } else {
                    deferred.resolve();
                }
                return deferred.promise();
            }
        });

        var uploader = WebUploader.create({
            server: '',
            chunked: true,
            chunkSize: 50 * 1024 * 1024,
            pick: {
                id: filePicker[0],
                multiple: true,
                innerHTML: '选择文件'
            },
            resize: false,
            method: 'POST',
            prepareNextFile: true,
            compress: false,
            fileNumLimit: fileNumLimit,
            accept: accept
        });
        return uploader;
    }

    var registerWebUploadEvent = function (events, tmp) {
        var uploader = this.uploader, that = this;
        uploader.on('fileQueued', events.fileQueued);

        uploader.on('uploadProgress', events.uploadProgress);

        uploader.on('uploadError', events.uploadError);

        uploader.on('uploadAccept', events.uploadAccept);

        uploader.on('repeatUpload', events.repeatUpload);

        //uploader.on('uploadStart', events.uploadStart);

        uploader.on('uploadSuccess', events.uploadSuccess);

        uploader.on('uploadBeforeSend', function (block, data) {
            // file为分块对应的file对象。
            var chunkMd5 = data.chunkMd5, urls = that.urls, file = block.file, callParam = that.callParam,
                savePath = that.savePath;
            data.code = that.code;
            if (file.size > 52428800) {
                var param = JSON.parse(sendRequest(urls.uploadSignature, encodeURIComponent(encodeURIComponent(data.fileMd5 + "\n" + file.name + "\n" + file.size + "\n" + chunkMd5 + "\n" + data.chunkSize))));
                data.step = 3;
                data.signature = encodeURIComponent(param.signature);
                data.fileInfo = encodeURIComponent(param.fileInfo);
            } else {
                var param = JSON.parse(sendRequest(urls.uploadSignature, encodeURIComponent(encodeURIComponent(data.fileMd5 + "\n" + file.name + "\n" + file.size))));
                data.signature = encodeURIComponent(param.signature);
                data.fileInfo = encodeURIComponent(param.fileInfo);
                data.tmp = tmp;
                if (callParam) {
                    data.call = encodeURIComponent(CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(JSON.stringify(callParam))));
                }
                if (savePath) {
                    data.savePath = savePath;
                }
            }
        })
    }

    var bindBtn = function (uploader, uploadBtn, filePickerDiv, dis) {
        filePickerDiv.click(function () {
            dis.find("input").click();
        })
        uploadBtn.unbind('click').click(function () {
            if (uploader.getFiles().length == 0)return;
            uploader.upload();
            uploadBtn.unbind('click').click(function () {
                _stop(uploader, uploadBtn);
            }).text('取消上传');
        });
    }

    var _deleteFile = function (fileNo, tmp, callBack) {
        if (!tmp)
            tmp = 0;
        var jsonData = JSON.parse(sendRequest(this.urls.uploadSignature, encodeURIComponent(fileNo + "\\n" + tmp)));
        var data = {
            policy: jsonData.fileInfo,
            signature: jsonData.signature,
            code: this.code
        };

        $.jsonp({
            type: "POST",
            url: this.urls.server + '/file_delete.html',
            callbackParameter: "callback",
            callback: "filedeletecallback",
            data: data,
            success: function (response) {
                if (response.isSuccess) {
                    if (callBack)
                        callBack();
                } else {
                    alert('删除失败,' + response.message);
                }
            }
        });
    }


    function sendRequest(url, param) {
        var xmlhttp = null;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
            var serverUrl = url + param;
            xmlhttp.open("GET", serverUrl, false);
            xmlhttp.send(null);
            return xmlhttp.responseText;
        } else {
            alert('Your browser does not support XMLHTTP.');
        }
    }

    function _checkBtnContinue() {
        if (this.uploader.getStats().queueNum == 0) {
            this.btnContinue();
        }
    }


    function _stop(uploader, uploadBtn) {
        if (this.waited <= 0)return;
        uploader.stop(true);
        uploadBtn.unbind('click').click(_start.bind(this, uploader, uploadBtn)).text("继续上传");
    }

    function _start(uploader, uploadBtn) {
        if (this.waited <= 0)return;
        uploader.upload();
        uploadBtn.unbind('click').click(_stop.bind(this, uploader, uploadBtn)).text("取消上传");
    }

    function _btnContinue() {
        if (this.waited <= 0)return;
        this.uploadBtn.unbind('click').click(_start.bind(this, this.uploader, this.uploadBtn)).text("继续上传");
    }

    function _init(config) {
        var dis = config.dis, filePickerDiv = config.filePickerDiv, uploadBtn = config.uploadBtn,
            code = config.code, savePath = config.savePath, callParam = config.callParam,
            handleEvent = config.handleEvent, tmp = config.tmp, urls = config.urls, uploader,
            fileNumLimit = config.fileNumLimit,
            accept = config.accept, uploadCallBack = config.uploadCallBack;

        //检查flash信息
        if (!hintFlashInfo())
            return;
        uploader = createUploader(dis, code, handleEvent, tmp, urls, savePath, fileNumLimit, accept);
        bindBtn(uploader, uploadBtn, filePickerDiv, dis);
        this.uploadBtn = uploadBtn;
        this.uploader = uploader;
        this.code = code;
        this.urls = urls;
        this.savePath = savePath;
        this.callParam = callParam;
        this.waited = 0;
        this.uploadCallBack = uploadCallBack;
        return uploader;
    }

    function _delete(fileId) {
        this.uploader.removeFile(fileId, true);
    }


    function _bindEvent(events, tmp) {
        registerWebUploadEvent.call(this, events, tmp);
    }

    return {
        init: _init,
        delete: _delete,
        bindEvent: _bindEvent,
        deleteFile: _deleteFile,
        checkBtnContinue: _checkBtnContinue,
        btnContinue: _btnContinue
    };

};