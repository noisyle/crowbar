<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<title>XXX管理系统</title>
	<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/static/datatables/css/dataTables.bootstrap.css" rel="stylesheet">
	<link href="${ctx}/static/metisMenu/metisMenu.min.css" rel="stylesheet">
	<link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${ctx}/static/site/css/admin.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="${ctx}/static/html5shiv.min.js}"></script>
        <script src="${ctx}/static/respond.min.js}"></script>
    <![endif]-->

</head>

<body>

<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">用户管理</h1>
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	                用户列表
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
		            <table id="table" class="table table-hover table-striped table-bordered table-condensed" cellspacing="0" width="100%">
		              <thead>
		                <tr>
		                  <th>登录名称</th>
		                  <th>用户名称</th>
		                  <th>角色</th>
		                  <th>联系电话</th>
		                  <th>电子邮箱</th>
		                  <th></th>
		                </tr>
		              </thead>
		            </table>
	   			</div><!-- /.panel-body -->
	        </div><!-- /.panel -->
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
</div><!-- /.container-fluid -->

<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/datatables/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/datatables/js/dataTables.bootstrap.js"></script>
<script src="${ctx}/static/utils.js"></script>
<script>
$(function() {
	$('#table').DataTable({
		ajax : {
			url: '${ctx}/admin/user'
		},
		serverSide: true,
		columns : [
			{data : "loginname"},
			{data : "username"},
			{data : "role"},
			{data : "phone"},
			{data : "email"},
			{data : null}
		],
        columnDefs: [{
	        targets: -1,
	        render: function (a, b, c, d) {
	            var html = " ";
	            html += '<button type="button" class="btn btn-primary btn-xs" onclick="alert(\'' + c.name + '\')">修改</button> ';
	            html += '<button type="button" class="btn btn-danger btn-xs" onclick="alert(\'' + c.name + '\')">删除</button> ';
	            return html;
	        }
	    }]
	});
});
</script>
</body>
</html>