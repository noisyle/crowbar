<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>登录</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
  font-family: "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "WenQuanYi Micro Hei", "Microsoft YaHei", sans-serif;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
#msg{
  display: none;
  padding: 0 5px;
}
</style>
</head>

<body>
  <div class="container">
    <form class="form-signin">
      <h2 class="form-signin-heading">登录</h2>
      <label for="loginname" class="sr-only">用户名</label>
      <input type="text" id="loginname" class="form-control" placeholder="用户名" required autofocus>
      <label for="password" class="sr-only">Password</label>
      <input type="password" id="password" class="form-control" placeholder="密码" required>
      <div class="alert alert-danger" id="msg"></div>
      <div class="checkbox">
        <label>
          <input type="checkbox" value="remember-me"> 自动登录
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    </form>
  </div>
  
</body>
<script src="${ctx}/static/jquery/jquery-1.11.2.min.js"></script>
<script>
$.crowbar = {
	ctx: '${ctx}'
};
</script>
<script src="${ctx}/static/utils.js"></script>
<script>
$(function(){
	$(".form-signin").submit(function(e){
		e.preventDefault();
		$.ajax({
			url:"${ctx}/admin/login",
			method:"post",
			data:{loginname:$("#loginname").val(),password:$.md5($("#password").val()).toUpperCase()}, // TODO 密码加密
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
</html>