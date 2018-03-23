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

function getDownLoadParam(code, fileNo) {
    var param = '?download=1&code=' + code;
    var jsonData = JSON.parse(sendRequest('./FileServiceSignature!getSignatureAndPolicy.shtml?', 'info=' + encodeURIComponent('0\n0\n' + fileNo)));
    param += '&signature=' + jsonData.signature + '&policy=' + jsonData.policy;
    return param;
}

var openUpload = function (usrId, callParam, sourceUrl, sourceWindow) {
    var originWindow = window.parent;
    while (originWindow) {
        if (originWindow.parent && originWindow.parent != originWindow)
            originWindow = originWindow.parent;
        else
            break;
    }
    if (originWindow.f_open_ossUpload) {

        originWindow.f_open_ossUpload(
            "./Upload!index.shtml?usrId=" + usrId + "&callParam=" + JSON.stringify(callParam),
            "上传附件",
            "./include/images/procFile.png",
            sourceUrl,
            sourceWindow
        )
    }
}

var openImgUpload = function (usrId, callParam, recentFileNo, callback) {
    var originWindow = window.parent;
    while (originWindow) {
        if (originWindow.parent && originWindow.parent != originWindow)
            originWindow = originWindow.parent;
        else
            break;
    }
    if (originWindow.f_open_img_ossUpload) {
        originWindow.f_open_img_ossUpload(
            "./Upload!index.shtml?usrId=" + usrId + "&callParam=" + JSON.stringify(callParam) + "&config=image&recentFileNo=" + recentFileNo,
            "上传附件",
            "./include/images/procFile.png",
            callback
        )
    }
}


var deleteFile = function (server, code, fileNo, callBack) {
    if (!fileNo || isNaN(fileNo))return;
    if (typeof fileNo == "string")
        fileNo = fileNo.trim();
    var uploadSignature = './FileServiceSignature!getSignatureAndFileInfo.shtml?info=';
    var jsonData = JSON.parse(sendRequest(uploadSignature, encodeURIComponent(fileNo + "\\n0")));
    var data = {
        policy: jsonData.fileInfo,
        signature: jsonData.signature,
        code: code
    };
    $.jsonp({
        type: "POST",
        url: server + '/file_delete.html',
        callbackParameter: "callback",
        callback: "filedeletecallback",
        data: data,
        success: function (response) {
            if (response.isSuccess) {
                if (callBack)
                    callBack(response);
            } else {
                alert('删除失败,' + response.message);
            }
        }
    });
}