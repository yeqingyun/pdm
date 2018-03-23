/*function dowDoc(docId){
		$.get("WfDoc!checkDocAuth.shtml?wfDoc.docId="+docId,
		function(data) {
			var oj = JSON.parse(data);
			if(oj.result==-1){
				alert("下载错误");
			}else if(oj.result==0){
				alert("没有权限下载");
			}else if(oj.result==1){
				 window.location ="./WfDoc!dow.shtml?wfDoc.docId="+docId;
			}
		}, "text");
}
*/

function dowDoc(docId){
		$.get("WfDoc!checkDocAuth.shtml?wfDoc.docId="+docId,
		function(data) {
			var oj = JSON.parse(data);
			if(oj.result==-1){
				alert("下载错误");
			}else{
				 window.location ="./WfDoc!dow.shtml?wfDoc.docId="+docId;
			}
		}, "text");
}