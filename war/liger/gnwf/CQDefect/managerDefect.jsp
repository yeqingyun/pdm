<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<head>
<title>defect</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<style type="text/css">
.allBox{width:988px;margin:10px auto;overflow:hidden;}
.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{height:32px;line-height:32px;color:#78503d;font-size:14px;}
.allTit p b{padding:0 10px;}
.allTit p span{padding-right:15px;}
.borBRed{border-bottom:#ffd4c1 1px solid;}
.area01{margin:10px 0;border:#ccc solid 1px;width:988px;}
.titBox{background:url(/zrprjt/include/img/workflow/rpx.png) repeat-x 0 0;padding:0 10px;height:32px;line-height:32px;border-bottom:#b7cee2 solid 1px;}
.titBox h3{font-size:16px;float:left;color:#515c68;font-weight:700;}
.titBox .butonList{margin-top:4px;float:right;}
.titBox .butonList li{float:left;}
.titBox .butonList li a{line-height:22px;padding:0 10px;text-decoration:none;margin:0 5px;font-size:12px;color:#fff; display:block;height:height:22px;border:#23537d solid 1px; border-radius:3px;background:#4280b7;}
.titBox .butonList li a:hover{background:#669bca;}
.processPicture{width:1000px;text-align:center;margin:10px auto;margin-top:20px;}
.processSection{width:118px;display:inline-block;margin:0 -4px;margin-bottom:10px;}
.PSTit{font-size:12px;height:66px;overflow:hidden;color:#666;line-height:22px;padding:0 17px;width:90px;}
*+ html .processSection { display:inline;zoom:1;}
* html .processSection { display:inline;zoom:1;}
.table01 {width:947px; margin:10px auto;color:#666;border-collapse:collapse;border-spacing:0;border-left:1px solid #ccc;border-top:1px solid #ccc;background:#fff;}
.table01 table{width:100%;}
.table01 a{text-decoration:none;}
.table01 a:hover{color:#ab0000;text-decoration:underline;}
.table01 th , .table01 td{ vertical-align:middle;padding:0px 10px;border-right:1px solid #ccc;text-align:center;font-size:12px;text-align:center;height:32px;line-height:32px;border-bottom:1px solid #ccc;}
.table01 th{font-weight:bold;background:#eee;}
.colorRed{color:#b35a48;}
.colorBlue{color:#3f94cf;}
.butList{float:right;}
.butList li{float:left;}
.butList button{margin:0 5px;color:#fff;background:#d07b48;border:#b76e27 solid 1px;height:24px;padding:0 10px; border-radius:3px;cursor:pointer;}
.tableBut{margin:0 5px;color:#fff;background:#4280b7;border:#23537d solid 1px;height:24px;padding:0 10px; border-radius:3px;cursor:pointer;}
.tableBut:hover{background:#669bca;}
.butList button:hover{background:#db9a72;}
.padLR5{padding:0 5px;}
.table01 .padd0{padding:inherit;}
.table01 .noBorR{border-right:none;}
.tableTextarea01{width:100%;padding:10px;height:100px;margin:10px auto;border:#ccc solid 1px;font-size:12px;color:#666;}
.table02 td{text-align:left;}
.table02 th{width:10%;}
.table02 .texAliC{text-align:center;}

</style>
</head>
<body>
<div class="allBox">
   <div class="area01" id = "main">
       <div class="titBox">
           <h3>Main</h3>
       </div>
       <div class="area01Con">
           <table class="table01 table02">
               <tbody>
                   <tr>
                       <th nowrap="nowrap">Id</th>
                       <td nowrap="nowrap"><c:out value="${defect.id}"/></td>
                       <th nowrap="nowrap">State</th>
                       <td nowrap="nowrap"><c:out value="${defect.state}"/></td>
                       <th nowrap="nowrap">Severity</th>
                       <td nowrap="nowrap"><c:out value="${defect.severity}"/></td>
                       <th nowrap="nowrap">Priority</th>
                       <td nowrap="nowrap"><c:out value="${defect.priority}"/></td>
                   </tr>
                   <tr>
                       <th nowrap="nowrap">Headline</th>
                       <td nowrap="nowrap" colspan="5"><c:out value="${defect.headline}"/></td>
                       <th nowrap="nowrap">Probability</th>
                       <td nowrap="nowrap"><c:out value="${defect.probability}"/></td>
                   </tr>
                    <tr> 
                    	<th nowrap="nowrap" >Project</th>
                    	<td nowrap="nowrap"><c:out value="${defect.prjName}"/></td>
                    	<th nowrap="nowrap" >Reason</th>
                    	<td nowrap="nowrap"><c:out value="${defect.reason}"/></td>
                    	<th nowrap="nowrap" >OS</th>
                    	<td nowrap="nowrap"><c:out value="${defect.os}"/></td>
                    	<th nowrap="nowrap" >Platform</th>
                    	<td nowrap="nowrap"><c:out value="${defect.platform}"/></td>
                    </tr>
                    <tr> 
                    	<th nowrap="nowrap" >Owner</th>
                    	<td nowrap="nowrap"><c:out value="${defect.login_name}"/></td>
                    	<th nowrap="nowrap" >Moduleitem</th>
                    	<td nowrap="nowrap" ><c:out value="${defect.moduleitem}"/></td>
                    	<th nowrap="nowrap" >Need Verified</th>
                    	<td nowrap="nowrap" ><c:out value="${defect.isverified}"/></td>
                    	<th nowrap="nowrap" >Reviewer</th>
                    	<td nowrap="nowrap" ><c:out value="${defect.reviewresult}"/></td>
                    </tr>
                   <tr>
	                   	<th nowrap="nowrap" >Baseline</th>
	                    <td nowrap="nowrap" ><c:out value="${defect.baseline}"/></td>
                        <th nowrap="nowrap" >Expectedbaseline</th>
                        <td nowrap="nowrap" ><c:out value="${defect.expectedbaseline}"/></td>
                        <th nowrap="nowrap" >Fixedbaseline</th>
                        <td nowrap="nowrap" ><c:out value="${defect.fixedbaseline}"/></td>
                        <th nowrap="nowrap" >Sw_leader</th>
                        <td nowrap="nowrap" ><c:out value="${defect.sw_leader_name}"/></td>
                   </tr>
                   <tr>
                    	<th nowrap="nowrap" >SQA</th>
                       <td nowrap="nowrap" ><c:out value="${defect.sqa_name}"/></td>
                       <th nowrap="nowrap" >Address TVN</th>
                       <td nowrap="nowrap" ><c:out value="${defect.bugfix}"/></td>
                       <th nowrap="nowrap" >Phase</th>
                       <td nowrap="nowrap" ><c:out value="${defect.phase}"/></td>
                       <th nowrap="nowrap" >Description<br/>Rom_used_prj</th>
                       <td nowrap="nowrap" ><c:out value="${defect.rom_used_prj}"/></td>
                   </tr>
                   <tr>
                    	<th nowrap="nowrap" >DRVLeader</th>
                        <td nowrap="nowrap" colspan="7"><c:out value="${defect.drvleader_name}"/></td>
                   </tr>
                   <tr>
                       <td nowrap="nowrap" colspan="10"><textarea readonly="readonly" class="tableTextarea01"><c:out value="${defect.description}"  escapeXml="false"/></textarea></td>
                   </tr>
                   <tr>
                       <td nowrap="nowrap" colspan="10">New Note:(Please see all notes in Notes)</td>
                   </tr>
                   <tr>
                       <td nowrap="nowrap" colspan="10"><textarea readonly="readonly" class="tableTextarea01"><c:out value="${defect.newnote}"  escapeXml="false"/></textarea></td>
                   </tr>
                   <tr>
                       <th nowrap="nowrap" >Submitter</th>
                       <td nowrap="nowrap" ><c:out value="${defect.submitter_name}"/></td>
                       <th nowrap="nowrap" >Submit_date</th>
                       <td nowrap="nowrap" ><fmt:formatDate type="both" value="${defect.submit_date}"/></td>
                       <th nowrap="nowrap" >Releatedcr</th>
                       <td nowrap="nowrap" ><c:out value="${defect.releatedcr}"/></td>
                       <th nowrap="nowrap" >Reviewresult</th>
                       <td nowrap="nowrap" ><c:out value="${defect.reviewresult}"/></td>
                   </tr>
                   <tr>
                   		<th nowrap="nowrap" >Bugtype</th>
                       <td nowrap="nowrap" ><c:out value="${defect.bugtype}"/></td>
                       <th nowrap="nowrap" >开发自测内容</th>
                       <td nowrap="nowrap" ><c:out value="${defect.ziceneirong}"/></td>
                       <th nowrap="nowrap" >测试建议</th>
                       <td nowrap="nowrap" ><c:out value="${defect.testnote}"/></td>
                       <th nowrap="nowrap" >Tester Phone</th>
                       <td nowrap="nowrap" ><c:out value="${defect.phone}"/></td>
                   </tr>
                   <tr>
                     	<th nowrap="nowrap" >Owner Phone</th>
                       <td nowrap="nowrap" ><c:out value="${defect.owner_phone}"/></td>
                       <th nowrap="nowrap" >开发自测结果</th>
                       <td nowrap="nowrap" ><c:out value="${defect.zicejieguo}"/></td>
                       <th nowrap="nowrap" >External_id</th>
                       <td nowrap="nowrap" ><c:out value="${defect.external_id}"/></td>
                       <th nowrap="nowrap" >Relatedcase</th>
                       <td nowrap="nowrap" ><c:out value="${defect.relatedcase}"/></td>
                   </tr>
                   <tr>
                       <th nowrap="nowrap" >Access Group</th>
                       <td nowrap="nowrap" ><c:out value="${defect.accessgroup}"/></td>
                       <th nowrap="nowrap" >Duplicate</th>
                       <td nowrap="nowrap" ><c:out value="${defect.is_duplicate}"/></td>
                       <th nowrap="nowrap" >Duplicate_original</th>
                       <td nowrap="nowrap" colspan="3"><c:out value="${defect.duplicate_original}"/></td>
                   </tr>
                    
               </tbody>
           </table>
       </div>
   </div>

<div class="area01">
  <div class="titBox">
      <h3>Notes</h3>
  </div>
  <div class="area01Con">
    <table class="table01 table02">
      <tbody>
      	<tr>
           <td nowrap="nowrap" colspan="10"><textarea readonly="readonly" class="tableTextarea01"><c:out value="${defect.notes}"  escapeXml="false"/></textarea></td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
</div>
</body>
