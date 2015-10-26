<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/common/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">修改密码</h1>
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
	<div class="row">
	    <div class="col-sm-6 col-sm-offset-3">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	            	<div class="panel-title">修改密码</div>
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
                    <form role="form" class="form-horizontal">
                        <div class="form-group">
                            <label for="oldpass" class="col-sm-2 control-label">旧密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="oldPass" name="oldPass" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pass1" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="pass1" name="pass1" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pass2" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="pass2" name="pass2" required>
                            </div>
                        </div>
	                    <div class="pull-right">
	                        <button class="btn btn-primary">确定</button>
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
		var data = $("form").serializeObject();
		$.extend(data, {
			oldPass:$.md5(data.oldPass).toUpperCase(),
			pass1:$.md5(data.pass1).toUpperCase(),
			pass2:$.md5(data.pass1).toUpperCase()
		});
		$.ajax({
			url:"${ctx}/admin/changePass",
			method:"post",
			data:data,
			dataType:"json",
			success:function(r){
				alert(r.message);
				if(r.status=='SUCCESS'){
					window.location.href="${ctx}/admin";
				}
			}
		});
		return false;
	});
});
</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
