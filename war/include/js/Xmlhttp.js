/**
 * desciption : 取得 Xmlhtml 控件对象实例
 * @return : Xmlhttp Object
 */
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
if (window.ActiveXObject)
	Sys.ie = ua.match(/msie ([\d.]+)/)[1]
else if (document.getBoxObjectFor)
	Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1]
else if (window.opera)
	Sys.opera = ua.match(/opera.([\d.]+)/)[1]

function isIe() {
	if(Sys.ie) 
		return 0;
	else if(Sys.firefox) 
		return 1;
	else if(Sys.opera)
		return 2;
	else 
		return 3;
}

function getXmlHttpObject(){
	var xmlhttps = ["MSXML3.XMLHTTP", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP", "MSXML.XMLHTTP", "MSXML2.ServerXMLHTTP"];
	var xmlhttp;

	if (window.XMLHttpRequest) { //针对FireFox，Mozillar，Opera，Safari，IE7，IE8
		try{
			xmlhttp = new XMLHttpRequest();
		}
		catch(e){
			alert("XmlHttp.getXmlHttpObject exception!");
			return null;
		}
		return xmlhttp;
	}
	if (window.ActiveXObject) {
	  	for(var i = 0;i<xmlhttps.length;i++){
			try{
				xmlhttp = new ActiveXObject(xmlhttps[i]);
			}
			catch(e){
				alert("XmlHttp.getXmlHttpObject exception!");
				return null;
			}
			if (xmlhttp != null) return xmlhttp;
		}
	}
	return null;
}
/**
 * desciption : 发送 XMLhttp 请求
 * @param : method GET/POST 
 * @param : url
 * @return : xmlHttp Object
 */
function sendXmlhttpRequest(method,url){
	try{
		xmlhttp = getXmlHttpObject();
		xmlhttp.open(method,url,false);
		//xmlhttp.SetRequestHeader("Content-Type","text/xml; charset=GBK");
		xmlhttp.send(null);
	}
	catch (e){
		alert("XmlHttp.sendXmlhttpRequest exception!");
		return null;
    }
	return xmlhttp;
}

function syncXmlhttpRequest(method,url){
	try{
		xmlhttp = getXmlHttpObject();
		xmlhttp.open(method,url,false);
		xmlhttp.SetRequestHeader("Content-Type","text/xml; charset=GBK"); 
		xmlhttp.send();
	}
	catch (e){
		alert("XmlHttp.sendXmlhttpRequest exception!");
		return null;
    }
	return xmlhttp;
}

/**
 * desciption : 取得 Xmldom Document etc 控件对象实例
 * @return : Microsoft.XMLDOM Object
 */
function getXmldomObject(){
	var xmldom;
	if(document.implementation && document.implementation.createDocument) {
		xmldom=document.implementation.createDocument("","",null);
		return xmldom;
	}
	
	if (window.ActiveXObject) {
		var xmldoms = ["MSXML3.DOMDocument", "MSXML2.DOMDocument", "Microsoft.DOMDocument", "MSXML.DOMDocument", "Microsoft.XMLDOM"];

		for(var i = 0;i<xmldoms.length;i++){
			try{
				xmldom = new ActiveXObject(xmldoms[i]);
			}
			catch(e){}
			if (xmldom != null) return xmldom;
		}	
	}
	alert("XmlHttp.getXmldomObject exception!");
	return null;
}

/**
 * desciption : 跟据path取得 xmldom 节点对象。
 * @param : xmldom
 * @param : path 
 * @return xmldom nodes 
 */
function getXmldocNodes(xmldom,path){
	var nodes;
	try{
		nodes = xmldom.selectNodes(path);
	}
	catch(e){
		alert("XmlHttp.getXmldocNodes exception!");
		return null;
	}
	return nodes;
}
/**
 * desciption : 根据path取得xmldom节点内容
 * @param : xmldom
 * @param : path
 * @return : xmldom node
 */
function getXmldocNode(xmldom,path){
	var nodeValue;
	try{
		var node = xmldom.selectSingleNode(path);
		nodeValue = node.text;
	}
	catch (e){
		alert("XmlHttp.getXmldocNode exception!");
		return null;
    }
	return nodeValue;
}

/**
 * desciption : 
 * test handle of the sample
 * 
function testHandleHttpResponse(){
	var xmlhttp = sendXmlhttpRequest('GET','http://localhost/crmh3x/root.jsp');
	
	try{
	  	if (xmlhttp.readyState == 4){
			var xmldom = getXmldomObject();
			xmldom.loadXML(xmlhttp.responseText);
			
			var nodes = getXmldocNodes(xmldom,"METADATA/ITEM");

			for(var i=0;i<nodes.length;i++){
				alert(getXmldocNode(xmldom,"METADATA/ITEM["+i+"]/ABSTRACT"));
			}
	  	}
	}
	catch(e){
		alert("XmlHttp.handleHttpResponse exception!");
		return null;
	}
}
*/

if(isIe() > 0) {
	
	XMLDocument.prototype.loadXML = function(sXml) {
		var oParser= new DOMParser();
		var _xmlDom = oParser.parseFromString(sXml, "text/xml");

//		while(this.firstChild){
//			this.removeChild(this.firstChild);
//		}
		
		for(var i=0;i<_xmlDom.childNodes.length;i++){
			var oNewNode = this.importNode(_xmlDom.childNodes[i],true);
			this.appendChild(oNewNode);
		}
	}
	
	Element.prototype.__defineGetter__("text",function(){ return this.textContent; });
}

//check for XPath implementation 
if(document.implementation.hasFeature("XPath", "3.0")) {
	Element.prototype.__defineGetter__("text",function(){ return this.textContent; });
	
	// prototying the XMLDocument 
	XMLDocument.prototype.selectNodes = function(cXPathString, xNode) {
		if( !xNode ) { 
			xNode = this; 
		} 
		var oNSResolver = this.createNSResolver(this.documentElement); 
		var aItems = this.evaluate(cXPathString, xNode, oNSResolver,XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
		var aResult = []; 
		for( var i = 0; i < aItems.snapshotLength; i++) { 
		     aResult[i] = aItems.snapshotItem(i); 
		} 
		return aResult; 
	}

	// prototying the Element 
	Element.prototype.selectNodes = function(cXPathString) { 
	    if(this.ownerDocument.selectNodes) { 
	         return this.ownerDocument.selectNodes(cXPathString, this); 
	    } 
	    else {
	         throw "For XML Elements Only";
	    }
	}
}

//check for XPath implementation 
if(document.implementation.hasFeature("XPath", "3.0")) { 
	Element.prototype.__defineGetter__("text",function(){ return this.textContent; });
	
	// prototying the XMLDocument 
	XMLDocument.prototype.selectSingleNode = function(cXPathString, xNode) {
	if( !xNode ) { 
	   xNode = this; 
	} 
	var xItems = this.selectNodes(cXPathString, xNode); 
	if( xItems.length > 0 ) { 
		return xItems[0]; 
	} 
	else { 
		return null; 
	} 
	
	// prototying the Element 
	Element.prototype.selectSingleNode = function(cXPathString) {
	   if(this.ownerDocument.selectSingleNode) { 
	      return this.ownerDocument.selectSingleNode(cXPathString, this); 
	   }
	   else{
	      throw "For XML Elements Only";} 
	   } 
	}
}

