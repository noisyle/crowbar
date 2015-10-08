<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/view/admin/common/header.jsp"></jsp:include>

<div class="container-fluid">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">用户信息修改</h1>
	    </div><!-- /.col-lg-12 -->
	</div><!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	            	<div class="panel-title">用户信息</div>
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
                    <form role="form" class="form-horizontal">
		                <input type="hidden" id="id" name="id" value="${user.id}">
	                    <div class="row">
		                    <div class="col-xs-6">
		                        <div class="form-group">
		                            <label for="loginname" class="col-sm-4 control-label">登录名称</label>
		                            <div class="col-sm-8">
		                                <input class="form-control" id="loginname" name="loginname" value="${user.loginname}" required autofocus>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="col-xs-6">
		                        <div class="form-group">
		                            <label for="username" class="col-sm-4 control-label">用户名称</label>
		                            <div class="col-sm-8">
		                                <input class="form-control" id="username" name="username" value="${user.username}" required>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-xs-6">
		                        <div class="form-group">
		                            <label for="role" class="col-sm-4 control-label">角色</label>
		                            <div class="col-sm-8">
		                                <input type="hidden" class="form-control" id="role" name="role" value="${user.role}" required>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-xs-6">
		                        <div class="form-group">
		                            <label for="phone" class="col-sm-4 control-label">联系电话</label>
		                            <div class="col-sm-8">
		                                <input type="tel" class="form-control" id="phone" name="phone" value="${user.phone}">
		                            </div>
		                        </div>
		                    </div>
		                    <div class="col-xs-6">
		                        <div class="form-group">
		                            <label for="phone" class="col-sm-4 control-label">电子邮箱</label>
		                            <div class="col-sm-8">
		                                <input type="email" class="form-control" id="email" name="email" value="${user.email}">
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-sm-6">
		                        <div class="form-group">
		                            <label for="role" class="col-sm-4 control-label">头像</label>
		                            <div class="col-sm-8">
		                                <div id="fileList" class="uploader-list">
		                                	<c:if test="${user.avatarId != null}">
			                                <div id="" class="file-item thumbnail">
			                                    <img src="${ctx}/admin/avatar/${user.avatarId}">
			                                </div>
		                                	</c:if>
		                                </div>
                                        <div id="filePicker">选择图片</div>
		                                <input type="hidden" id="avatarId" name="avatarId" value="${user.avatarId}">
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-xs-12">
			                    <div class="pull-right">
		                        <button class="btn btn-primary">保存用户信息</button>
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
	var json_role = ${json_role};
	$("#role").select2({data: json_role});
	
	$("form").submit(function(){
		$.ajax({
			url:"${ctx}/admin/saveUser",
			method:"post",
			data:$("form").serializeObject(),
			dataType:"json",
			success:function(r){
				alert(r.message);
				if(r.status=="SUCCESS"){
					window.location.href="${ctx}/admin/userList";
				}
			}
		});
		return false;
	});
	$("#btnBack").on("click", function(){
		window.location.href="${ctx}/admin/userList";
	});
	
	// 初始化Web Uploader
	var uploader = WebUploader.create({
	    // 选完文件后，是否自动上传。
	    auto: true,
	    // swf文件路径
	    swf: '${ctx}/static/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: '${ctx}/admin/uploadAvatar',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#filePicker',
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    },
	    compress: {
	        width: 100,
	        height: 100,
	        // 图片质量，只有type为`image/jpeg`的时候才有效。
	        quality: 90,
	        // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
	        allowMagnify: false,
	        // 是否允许裁剪。
	        crop: true,
	        // 是否保留头部meta信息。
	        preserveHeaders: true,
	        // 如果发现压缩后文件大小比原来还大，则使用原来图片
	        // 此属性可能会影响图片自动纠正功能
	        noCompressIfLarger: false,
	        // 单位字节，如果图片大小小于此值，不会采用压缩。
	        compressSize: 0
	    }
	});
	var $list = $('#fileList');
	var thumbnailWidth = 100,
		thumbnailHeight = 100;
	// 当有文件添加进来的时候
	uploader.on( 'fileQueued', function( file ) {
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +
	                '<img>' +
	            '</div>'
	            ),
	        $img = $li.find('img');
	    // $list为容器jQuery实例
// 	    $list.append( $li );
	    $list.html( $li );
	    // 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    // thumbnailWidth x thumbnailHeight 为 100 x 100
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不支持预览</span>');
	            return;
	        }

	        $img.attr( 'src', src );
	    }, thumbnailWidth, thumbnailHeight );
	});
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress span');
	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<p class="progress"><span></span></p>')
	                .appendTo( $li )
	                .find('span');
	    }

	    $percent.css( 'width', percentage * 100 + '%' );
	});
	// 根据服务器返回标识判断上传是否成功。
	uploader.on( 'uploadAccept', function( obj, res ) {
	    if ( res.status != "SUCCESS" ) {
	        // 通过return false来告诉组件，此文件上传有错。
	        return false;
	    }
		obj.file.realid = res.data;
	    return true;
	});
	// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	uploader.on( 'uploadSuccess', function( file ) {
	    $( '#'+file.id ).addClass('upload-state-done');
	    $('#avatarId').val(file.realid);
	});
	// 文件上传失败，显示上传出错。
	uploader.on( 'uploadError', function( file ) {
	    var $li = $( '#'+file.id ),
	        $error = $li.find('div.error');

	    // 避免重复创建
	    if ( !$error.length ) {
	        $error = $('<div class="error"></div>').appendTo( $li );
	    }

	    $error.text('上传失败');
	});
	// 完成上传完了，成功或者失败，先删除进度条。
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').remove();
	});
});
</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
