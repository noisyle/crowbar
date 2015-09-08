<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Admin</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

你好，${user.username }

</body>
<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
<script>
$.crowbar = {
	ctx: '${ctx}'
};
</script>
<script src="${ctx}/static/utils.js"></script>
<script>
$(function(){
});
</script>
</html>