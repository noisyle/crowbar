<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
<script>
if(window.parent!=window){
	window.parent.location.href="${ctx}/admin/login";
}
</script>

    <div class="container">
        <div class="center-block" style="max-width:300px;">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="login.boxTitle" /></h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="<spring:message code="login.username" />" name="loginname" type="text" value="admin1" autofocus />
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="<spring:message code="login.password" />" name="password" type="password" value="123456" />
                            </div>
                            <div class="alert alert-danger" id="msg" style="display:none; padding:5px 15px;"></div>
                            <div class="checkbox">
                                <label>
                                    <input name="rememberMe" type="checkbox"><spring:message code="login.remember" />
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <a href="#" role="button" class="btn btn-lg btn-primary btn-block"><spring:message code="login.btnLogin" /></a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

	<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
	<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/utils.js"></script>
<script>
$(function(){
	$('.btn-primary').on('click', login);
	$('form').on('keyup', 'input', function(e){
		if(e.which=='13'){
			login(e);
		}
	});
	
	function login(e){
		var data = $('form').serializeObject();
		data.password = $.md5(data.password).toUpperCase();
		data.rememberMe = $('[name=rememberMe]').prop("checked");
		$.ajax({
			url:"${ctx}/admin/login",
			method:"post",
			data:data,
			success:function(r){
				if(r.status=='SUCCESS'){
					window.location.href="${ctx}/admin";
				}else{
					$("#msg").text(r.message).show();
				}
			}
		});
	}
});
</script>
</body>

</html>
