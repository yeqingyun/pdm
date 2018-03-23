<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

</head>
<body>

<form acton="MailCfg!imp.shtml" method="post" enctype="multipart/form-data">
	<input type="file" id="fileInp" name="fileInp"/>
	<input type="submit" value="导入"><font size="-1" color="#ff4500"><c:out value="${msg}"/></font>
</form>

</body>
</html>
