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
		                    <div class="col-sm-6">
		                        <div class="form-group">
		                            <label for="title" class="col-sm-4 control-label">标题</label>
		                            <div class="col-sm-8">
		                                <input class="form-control" id="title" name="title" value="${article.title}" required autofocus>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="col-sm-6">
		                        <div class="form-group">
		                            <label for="subtitle" class="col-sm-4 control-label">副标题</label>
		                            <div class="col-sm-8">
		                                <input class="form-control" id="subtitle" name="subtitle" value="${article.subtitle}" required>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-sm-6">
		                        <div class="form-group">
		                            <label for="content" class="col-sm-4 control-label">栏目</label>
		                            <div class="col-sm-8">
		                                <input type="hidden" class="form-control" id="categoryId" name="categoryId" value="${article.category.id}" data-text="${article.category.categoryName}" required>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-sm-12">
		                        <div class="form-group">
		                            <label for="content" class="col-sm-2 control-label">正文</label>
		                            <div class="col-sm-10">
		                                <textarea class="form-control" rows="3" id="content" name="content" required>${article.content}</textarea>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-sm-12">
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
	$("#categoryId").select2({
	    placeholder: "选择一个栏目",
	    minimumInputLength: 0,
	    ajax: { // instead of writing the function to execute the request we use Select2's convenient helper
	        url: "${ctx}/admin/categorys",
	        dataType: 'json',
	        quietMillis: 250,
	        data: function (term, page) {
	            return {
	                q: term, // search term
	            };
	        },
	        results: function (data, page) { // parse the results into the format expected by Select2.
	            // since we are using custom formatting functions we do not need to alter the remote JSON data
	            return { results: data };
	        },
	        cache: true
	    },
	    initSelection: function(element, callback) {
	        var id = $(element).val();
	        var text = $(element).data("text");
	        if (id !== "") {
	        	callback({id: id, categoryName: text});
	        }
	    },
	    formatResult: function (row) { return row.categoryName }, // omitted for brevity, see the source of this page
	    formatSelection: function (row) { return row.categoryName },  // omitted for brevity, see the source of this page
	    escapeMarkup: function (m) { return m; } // we do not want to escape markup since we are displaying html in results
	});
	
	$('#content').summernote({
		height: 300
	});
	
	$("form").submit(function(){
		$.ajax({
			url:"${ctx}/admin/saveArticle",
			method:"post",
			data:$("form").serializeObject(),
			dataType:"json",
			success:function(r){
				alert(r.message);
				if(r.status=="SUCCESS"){
					window.location.href="${ctx}/admin/articleList";
				}
			}
		});
		return false;
	});
	$("#btnBack").on("click", function(){
		window.location.href="${ctx}/admin/articleList";
	});
});
</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
