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
			          <button type="button" class="btn btn-default" id="btnAdd">新增根栏目</button>
			          <button type="button" class="btn btn-default" id="btnAddSub">新增子栏目</button>
			          <button type="button" class="btn btn-default" id="btnDel">删除栏目</button>
			          <button type="button" class="btn btn-default" id="btnReload">刷新列表</button>
			        </div>
			        <div class="panel-title">栏目列表</div>
	            </div><!-- /.panel-heading -->
	            <div class="panel-body">
					<div class="row">
					    <div class="col-sm-3">
					    	<ul id="categoryTree" class="ztree" style="min-height:200px;"></ul>
					    </div>
					    <div class="col-sm-9">
		                    <form role="form" class="form-horizontal">
			                    <div class="row">
				                    <div class="col-sm-12">
				                        <div class="form-group">
				                            <label for="subtitle" class="col-sm-2 control-label">父栏目</label>
				                            <div class="col-sm-10">
				                                <input class="form-control" id="parent" name="parent" disabled>
				                                <input type="hidden" id="parentId" name="parentId">
				                            </div>
				                        </div>
				                    </div>
				                    <div class="col-sm-12">
				                        <div class="form-group">
				                            <label for="title" class="col-sm-2 control-label">栏目名称</label>
				                            <div class="col-sm-10">
				                                <input class="form-control" id="categoryName" name="categoryName" required autofocus>
				                                <input type="hidden" id="id" name="id">
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
var ztree;
var ztree_setting = {
	data: {
		key: {
			name: 'categoryName'
		},
		simpleData: {
			enable: true,
			idKey: 'id',
			pIdKey: 'parentId',
			rootPId: null
		}
	},
	callback: {
		onClick: loadForm
	}
};
$(function() {
	initTree();
	
	$("form").submit(function(){
		$.ajax({
			url:"${ctx}/admin/saveCategory",
			method:"post",
			data:$("form").serializeObject(),
			dataType:"json",
			success:function(r){
				alert(r.message);
				if(r.status=="SUCCESS"){
					reload();
				}
			}
		});
		return false;
	});
	
	$(".panel-heading").on('click', 'button', function(e){
		switch (this.id) {
		case 'btnAdd':
			add();
			break;
		case 'btnAddSub':
			addSub();
			break;
		case 'btnDel':
			del();
			break;
		case 'btnReload':
			reload();
			break;
		default:
			break;
		}
	});
});

function loadForm(event, treeId, treeNode){
	var parent = ztree.getNodeByTId(treeNode.parentTId);
	$("#parent").val(parent?parent.categoryName:'');
	$("#parentId").val(parent?parent.id:'');
	$("#categoryName").val(treeNode.categoryName);
	$("#id").val(treeNode.id);
}

function initTree(){
	$.ajax({
		url:"${ctx}/admin/categorys",
		method:"get",
		dataType:"json",
		success:function(r){
			ztree = $.fn.zTree.init($("#categoryTree"), ztree_setting, r);
		}
	});
}

function add(){
	$("#parent").val('');
	$("#parentId").val('');
	$("#categoryName").val('');
	$("#id").val('');
}

function addSub(){
	var select = ztree.getSelectedNodes();
	if(!select.length){
		alert('请选择一个栏目');
		return;
	}
	var parent = select[0];
	$("#parent").val(parent.categoryName);
	$("#parentId").val(parent.id);
	$("#categoryName").val('');
	$("#id").val('');
}

function del(){
	$.ajax({
		url:"${ctx}/admin/delCategory",
		method:"post",
		data:$("form").serializeObject(),
		dataType:"json",
		success:function(r){
			alert(r.message);
			if(r.status=="SUCCESS"){
				reload();
			}
		}
	});
}

function reload(){
	$.fn.zTree.destroy("categoryTree");
	initTree();
}
</script>
<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>
