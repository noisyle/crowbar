<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${ctx}/static/datatables/css/dataTables.bootstrap.css" rel="stylesheet">

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
		                  <th>id</th>
		                  <th>登录名称</th>
		                  <th>用户名称</th>
		                  <th></th>
		                </tr>
		              </thead>
		            </table>
	   			</div><!-- /.panel-body -->
	        </div><!-- /.panel -->
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
</div><!-- /.container-fluid -->

<script src="${ctx}/static/datatables/js/jquery.dataTables.js"></script>
<script src="${ctx}/static/datatables/js/dataTables.bootstrap.js"></script>
<script src="${ctx}/static/utils.js"></script>
<script>
$(function() {
	$('#table').DataTable({
		ajax : {
			url: '${ctx}/admin/users'
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
