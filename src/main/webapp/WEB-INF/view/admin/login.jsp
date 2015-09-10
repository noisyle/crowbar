<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<title>XXX管理系统</title>
	<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">登录</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" name="loginname" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" name="password" type="password" >
                                </div>
                                <div class="alert alert-danger" id="msg" style="display:none;"></div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">记住密码
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="#" role="button" class="btn btn-lg btn-primary btn-block">登录</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
	<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/utils.js"></script>
<script>
$(function(){
	$(".btn-primary").click(function(e){
		var data = $('form').serializeObject();
		data.password = $.md5(data.password).toUpperCase();
		$.ajax({
			url:"${ctx}/admin/login",
			method:"post",
			data:data,
			success:function(r){
				if(r.status=='SUCCESS'){
					window.location.href="${ctx}/admin";
				}else{
					$("#msg").text("用户名或密码错误！").show();
				}
			}
		});
	});
});
</script>
</body>

</html>
