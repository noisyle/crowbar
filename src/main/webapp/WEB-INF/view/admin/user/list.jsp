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
<link href="${ctx}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="${ctx}/static/datatables/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="${ctx}/static/site/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container-fluid">
    <section class="content-header">
      <h1>
        用户管理
        <small>列表</small>
      </h1>
    </section>

    <section class="content">

      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">用户列表</h3>
        </div>

        <div class="box-body">
            <table id="example1" class="table table-hover table-striped table-bordered table-condensed" cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th>id</th>
                  <th>登录名称</th>
                  <th>用户名称</th>
                  <th></th>
                </tr>
              </thead>
            </table>
        </div>

      </div>

    </section>
</div>

</body>
<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${ctx}/static/datatables/js/jquery.dataTables.js"></script>
<script src="${ctx}/static/datatables/js/dataTables.bootstrap.js"></script>
<script>
$.crowbar = {
	ctx: '${ctx}'
};
</script>
<script src="${ctx}/static/utils.js"></script>
<script>
$(function() {
	$('#example1').DataTable({
		ajax : {
			url: '${ctx}/admin/users',
			data: {}
		},
		serverSide: true,
		columns : [
			{data : "id"},
			{data : "loginname"},
			{data : "username"},
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
</html>