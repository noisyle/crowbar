<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>后台管理</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/site/css/admin.css" rel="stylesheet">
</head>

<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">后台管理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">${today}</a></li>
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">你好，${user.username } <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">个人信息</a></li>
              <li><a href="#">修改密码</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="${ctx}/admin/logout">退出</a></li>
            </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div id="menu" class="sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="${ctx}/admin/user/list">用户</a></li>
            <li><a href="#">栏目</a></li>
          </ul>
        </div>
        <div class="frame">
			<iframe id="mainFrame"></iframe>
        </div>
      </div>
    </div>

</body>
<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script>$.crowbar={ctx:'${ctx}'};</script>
<script src="${ctx}/static/utils.js"></script>
<script src="${ctx}/static/site/js/admin.js"></script>
</html>