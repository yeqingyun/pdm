<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrsy/menu.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {
        	$.post("ligerToolBar1.shtml",
        			{parm:'Menu'},
        			function(data) {
        				$("#toolbar").ligerToolBar(data);
        			},
        			"json"
        	);
            check();
        }); 
    </script>

</head>

<body>
<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
    <form>
    <input type="hidden" id="id" name="menu.id" size="30" value="<c:out value="${menu.id}"/>"/>
        <table width="90%">
             <tr>
                <td  height="24" width="90" align="center">系统:</td>
                <td>
                <select id="syId" name="menu.syId" style="width:135px" validate="{required:true}" >
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"<c:if test="${syDef.syId==menu.syId}">selected</c:if>><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
                </td><td align="left"></td>
               <td  height="24" width="90" align="center">上级ID:</td>
                <td>
                    <input name="menu.parent" type="text" id="parent" value="<c:out value="${menu.parent}"/>"  ltype='spinner' ligerui="{type:'int'}" validate="{required:true}" />
                </td><td align="left"></td>
             </tr>      
             <tr> <td  height="24" width="90" align="center">菜单名:</td>
                <td><input name="menu.text" type="text" id="text" ltype="text" validate="{required:true,minlength:1,maxlength:10}" /></td>
               <td align="left"></td>
                <td  height="24" width="90" align="center">宽度:</td>
                <td><input name="menu.width" type="text" id="width"  value="130" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}" /></td>
                <td align="left"></td>
            </tr>   
            <tr>
                <td  height="24" width="90" align="center">级别:</td>
                <td><input name="menu.leve" type="text" id="leve"  value="0" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}" /></td>
                <td align="left"></td>
                <td  height="24" width="90" align="center">排序:</td>
                <td><input name="menu.sort" type="text" id="sort"  value="0" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}" /></td>
                <td align="left"></td>
            </tr>  
            <tr>
                <td  height="24" width="90" align="center">单击事件:</td>
                <td><input name="menu.click" type="text" id="click"  value="<c:out value="${menu.click}"/>" ltype="text" validate="{required:true}" /></td>
                <td align="left"></td>
                <td  height="24" width="90" align="center">图标:</td>
                <td><input name="menu.icon" type="text" id="icon"  value="<c:out value="${menu.icon}"/>"/></td>
                <td align="left"></td>
            </tr>   
             
        </table>
 <br/>
 
    </form>
    </div>
   
    
</body>
</html>