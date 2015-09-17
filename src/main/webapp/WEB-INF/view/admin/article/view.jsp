<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/common/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">文章修改</h1>
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	            	<div class="panel-title">文章信息</div>
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
                    <form role="form" class="form-horizontal">
		                <input type="hidden" id="id" name="id" value="${article.id}">
	                    <div class="row">
		                    <div class="col-md-6">
		                        <div class="form-group">
		                            <label for="title" class="col-sm-3 control-label">标题</label>
		                            <div class="col-sm-9">
		                                <input class="form-control" id="title" name="title" value="${article.title}" required autofocus>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="col-md-6">
		                        <div class="form-group">
		                            <label for="subtitle" class="col-sm-3 control-label">副标题</label>
		                            <div class="col-sm-9">
		                                <input class="form-control" id="subtitle" name="subtitle" value="${article.subtitle}" required>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-6">
		                        <div class="form-group">
		                            <label for="content" class="col-sm-3 control-label">正文</label>
		                            <div class="col-sm-9">
		                                <textarea class="form-control" rows="3" id="content" name="content" required>${article.content}</textarea>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-md-12">
			                    <div class="pull-right">
		                        <button class="btn btn-primary">保存文章</button>
		                        <button type="button" class="btn btn-default" id="btnBack">返回列表</button>
		                        </div>
		                    </div>
	                    </div>
                    </form>
	   			</div><!-- /.panel-body -->
	        </div><!-- /.panel -->
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
</div><!-- /.container-fluid -->

<script>
$(function() {
	$("form").submit(function(){
		$.ajax({
			url:"${ctx}/admin/savearticle",
			method:"post",
			data:$("form").serializeObject(),
			dataType:"json",
			success:function(r){
				alert(r.message);
				if(r.status=="SUCCESS"){
					window.location.href="${ctx}/admin/articlelist";
				}
			}
		});
		return false;
	});
	$("#btnBack").on("click", function(){
		window.location.href="${ctx}/admin/articlelist";
	});
});
</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
