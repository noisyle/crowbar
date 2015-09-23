<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/common/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">栏目管理</h1>
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
			        <div class="btn-group btn-group-sm pull-right" role="group" aria-label="...">
			          <button type="button" class="btn btn-default" id="btnAdd">新增文章</button>
			          <button type="button" class="btn btn-default" id="btnReload">刷新列表</button>
			        </div>
			        <div class="panel-title">文章列表</div>
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
					<div class="row">
					    <div class="col-md-2">
					    	
					    </div>
					    <div class="col-md-10">
		                    <form role="form" class="form-horizontal">
			                    <div class="row">
				                    <div class="col-sm-12">
				                        <div class="form-group">
				                            <label for="subtitle" class="col-sm-4 control-label">父栏目</label>
				                            <div class="col-sm-8">
				                                <input class="form-control" id="parent" name="parent">
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-sm-12">
				                        <div class="form-group">
				                            <label for="title" class="col-sm-4 control-label">栏目名称</label>
				                            <div class="col-sm-8">
				                                <input class="form-control" id="categoryname" name="categoryname" required autofocus>
				                            </div>
				                        </div>
				                    </div>
				                </div>
				                <div class="row">
				                    <div class="col-sm-12">
					                    <div class="pull-right">
				                        	<button class="btn btn-primary">保存栏目</button>
				                        </div>
				                    </div>
			                    </div>
		                    </form>
					    </div>
					</div>
	   			</div><!-- /.panel-body -->
	        </div><!-- /.panel -->
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
</div><!-- /.container-fluid -->

<script>
var table;
$(function() {
	
	
});

</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
