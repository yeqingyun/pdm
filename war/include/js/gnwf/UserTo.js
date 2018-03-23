function check() {
	if(document.getElementById("mailTo.mailId").value=="") {
		alert("请输入mailId内容。");
		document.getElementById("mailTo.mailId").focus();
		return false;
	}
	if(document.getElementById("mailTo.toId").value=="") {
		alert("请输入toId内容。");
		document.getElementById("mailTo.toId").focus();
		return false;
	}
	if(document.getElementById("mailTo.toMail").value=="") {
		alert("请输入toMail内容。");
		document.getElementById("mailTo.toMail").focus();
		return false;
	}
	if(document.getElementById("mailTo.toType").value=="") {
		alert("请输入toType内容。");
		document.getElementById("mailTo.toType").focus();
		return false;
	}
	return true;
}

function closePop() {
	window.parent.closePop();
}

function retnPop(userIdEleId,userNameEleId) {
	window.parent.document.getElementById(userIdEleId).value = getOptv("to");
	window.parent.document.getElementById(userNameEleId).value = getOptz("to");
	
	///alert(window.parent.document.getElementById(userIdEleId).value);
	//alert(window.parent.document.getElementById(userNameEleId).value);
	//window.parent.addto(getOptv("to"));
	//window.parent.addcc(getOptz("to"));
	closePop();
}

function getPop() {
	var _to = window.parent.getto();
	var _cc = window.parent.getcc();
	
	setOptz("to",_to,_cc);
}

function setOptz(id,_to,_cc) {
	if(_to != "") {
		var _tos = _to.split(",");
		var _ccs = _cc.split(",");
		
		for(i=0; i<_tos.length; i++) {
			var _optz = new Option(_ccs[i],_tos[i]);
			document.getElementById(id).options.add(_optz);
		}
	}
}

//取已选择地址人名，电子邮件地址
function getOptz(id) {
	var zz = "";
	if(document.getElementById(id).options.length == 0) 
		return zz;
	if(document.getElementById(id).options.length == 1) {
		zz = document.getElementById(id).options[0].text;
	}
	else {
		for(i=0; i<document.getElementById(id).options.length; i++) {
			if(i == 0)
				zz = document.getElementById(id).options[i].text;
			else
				zz += "," + document.getElementById(id).options[i].text;
		}
	}
	return zz;
}
function getOptv(id) {
	var zz = "";
	if(document.getElementById(id).options.length == 0) 
		return zz;
	if(document.getElementById(id).options.length == 1) {
		zz = document.getElementById(id).options[0].value;
	}
	else {
		for(i=0; i<document.getElementById(id).options.length; i++) {
			if(i == 0)
				zz = document.getElementById(id).options[i].value;
			else
				zz += "," + document.getElementById(id).options[i].value;
		}
	}
	return zz;
}

function chgmailg() {
	if(document.getElementById("mailg").value == 1) {
		seaProj();
	}
	else if(document.getElementById("mailg").value == 2) {
		seaAddr();
	}
	else if(document.getElementById("mailg").value == 3) {
		seaMail();
	}
}

//按项目组查找
function seaProj() {
	try {
		var xmlhttp = sendXmlhttpRequest("GET","MailToXml.shtml?mailTo.type="+param);

		if(xmlhttp.readyState == 4) {
			var xmldom = getXmldomObject();
			xmldom.loadXML(xmlhttp.responseText);
			
			var _is = getXmldocNodes(xmldom,"METADATA/ITEM1");

			for(i=0;i<_is.length;i++) {
				var btnCode = getXmldocNode(xmldom,"METADATA/ITEM1["+i+"]/btnCode");
				var btnJs = getXmldocNode(xmldom,"METADATA/ITEM1["+i+"]/btnJs");
				var btnName = getXmldocNode(xmldom,"METADATA/ITEM1["+i+"]/btnName");
			}
		}
	}
	catch(e) {
		alert("toolbars exception!");
		return null;
	}
}

//按通讯录查找
function chgbook(param0,to) {
	try {
		var param = document.getElementById(param0).value;
		
		var xmlhttp = sendXmlhttpRequest("GET","AddrBookXml.shtml?addrBook.deptId="+param);

		if(xmlhttp.readyState == 4) {
			var xmldom = getXmldomObject();
			xmldom.loadXML(xmlhttp.responseText);
			var _is = getXmldocNodes(xmldom,"METADATA/ITEM");
			
			document.getElementById(to).length = 0;

			if (isIe() > 0) {     //非ie浏览器
				for(i=1;i<=_is.length;i++) {
					document.getElementById(to).add(document.createElement("option"));
					document.getElementById(to).options[i-1].value = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/userId");
					document.getElementById(to).options[i-1].text = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/empName");
				}
			}
			else {
				for(i=0;i<_is.length;i++) {
					document.getElementById(to).add(document.createElement("option"));
					document.getElementById(to).options[i].value = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/userId");
					document.getElementById(to).options[i].text = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/empName");
				}
			}
		}
	}
	catch(e) {
		alert("UserTo.js.chgbook exception!");
		return null;
	}
}

function ckaddto(p0,p1) {
	
	var obj0 = document.getElementById(p0);
	var obj1 = document.getElementById(p1);

	for(i=0;i<obj0.length;i++){
        if(obj0.options[i].selected){
        	if(!findoptz(p1,obj0.options[i].text)) {
        		var optz = document.createElement("option");
        		optz.value = obj0.options[i].value;
        		optz.text = obj0.options[i].text;

        		obj1.add(optz);
        	}
        }
    }
}

function findoptz(p2,value) {
	var obj2 = document.getElementById(p2);
	for(n=0; n<obj2.length; n++) {
		if(obj2.options[n].text == value)
			return true;
	}
	return false;
}

function ckdelopt(p3) {
	var obj3 = document.getElementById(p3);
	
	for(m=obj3.length-1;m>=0;m--){   
        if(obj3.options[m].selected){
        	obj3.remove(m);
        }
    }
}

//按邮件分组，查找
function seaMail() {
	
}

//function setChgDept(from,to) {
//	try {
//		var param = document.getElementById(from).value;
//		if(param != "") {
//			var xmlhttp = sendXmlhttpRequest("GET","BaDeptXml.shtml?baDept.comId="+param);
//			
//			if(xmlhttp.readyState == 4) {
//				var xmldom = getXmldomObject();
//				xmldom.loadXML(xmlhttp.responseText);
//				
//				var nodes = getXmldocNodes(xmldom,"METADATA/ITEM");
//				document.getElementById(to).length = 0;
//
//				document.getElementById(to).add(document.createElement("option"));
//				document.getElementById(to).options[0].value = "";
//				document.getElementById(to).options[0].text = "请选择";
//				
//				if (isIe() > 0) {     //非ie浏览器
//					for(i=1;i<=nodes.length;i++) {
//						document.getElementById(to).add(document.createElement("option"));
//						document.getElementById(to).options[i].value = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/deptId");
//						document.getElementById(to).options[i].text = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/deptName");
//					}
//				}
//				else {         //ie浏览器
//					for(i=0;i<nodes.length;i++) {
//						document.getElementById(to).add(document.createElement("option"));
//						document.getElementById(to).options[i+1].value = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/deptId");
//						document.getElementById(to).options[i+1].text = getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/deptName");
//					}
//				}
//			}
//		}
//		else {
//			document.getElementById(to).length = 0;
//
//			document.getElementById(to).add(document.createElement("option"));
//			document.getElementById(to).options[0].value = "";
//			document.getElementById(to).options[0].text = "请选择";
//		}
//	}
//	catch(e) {
//		alert("nodechange exception!");
//		return null;
//	}
//}
