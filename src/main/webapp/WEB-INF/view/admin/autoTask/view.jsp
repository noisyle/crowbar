<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/common/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">自动任务修改</h1>
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	            	<div class="panel-title">自动任务</div>
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
                    <form role="form" class="form-horizontal">
		                <input type="hidden" id="id" name="id" value="${autoTask.id}">
                        <div class="form-group">
                            <label for="taskName" class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="taskName" name="taskName" value="${autoTask.taskName}" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="clazz" class="col-sm-2 control-label">任务类</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="clazz" name="clazz" placeholder="com.noisyle.crowbar.task.Task1" value="${autoTask.clazz}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="parameter" class="col-sm-2 control-label">执行参数</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="parameter" name="parameter" value="${autoTask.parameter}" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cron" class="col-sm-2 control-label">执行时间</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="cron" name="cron" placeholder="*/10 * * * * ?" value="${autoTask.cron}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="enable" class="col-sm-2 control-label">是否启用</label>
                            <div class="col-sm-10">
                                <input type="hidden" class="form-control" id="enable" name="enable" value="${autoTask.enable}" required>
                            </div>
                        </div>
	                    <div class="pull-right">
	                        <button class="btn btn-primary">保存自动任务</button>
	                        <button type="button" class="btn btn-default" id="btnBack">返回列表</button>
                        </div>
                    </form>
	   			</div><!-- /.panel-body -->
	        </div><!-- /.panel -->
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
</div><!-- /.container-fluid -->

<script>
$(function() {
	var json_enable = [{id:'false',text:'否'},{id:'true',text:'是'}];
	$("#enable").select2({data: json_enable});
	
	$("form").submit(function(){
		$.ajax({
			url:"${ctx}/admin/saveAutoTask",
			method:"post",
			data:$("form").serializeObject(),
			dataType:"json",
			success:function(r){
				alert(r.message);
				if(r.status=="SUCCESS"){
					window.location.href="${ctx}/admin/autoTaskList";
				}
			}
		});
		return false;
	});
	$("#btnBack").on("click", function(){
		window.location.href="${ctx}/admin/autoTaskList";
	});
});
</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
