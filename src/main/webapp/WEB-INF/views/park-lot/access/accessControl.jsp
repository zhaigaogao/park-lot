<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
	<title>预约管理系统</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/access/accessControl.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="access" />
</jsp:include>
	<div id="main">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">出入口信息编辑</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="modal-body area-modalBody">
		      		<div class="form-group">
		      			<label class="area-carSize" for="exampleInputPassword1">出入口名称</label>
		      			<input type="text" value="32" class="area-setNum form-control area-gc area-car" id="exampleInputEmail1" placeholder="">
		      		</div>
		      		<div class="form-group">
		      			<label class="area-carSize" for="exampleInputPassword1">出入口属性</label>
		      			<select class="area-setNum form-control area-gc area-car">
		      				<option>只可进</option>
		      				<option>只可出</option>
		      				<option>可进出</option>
		      			</select>
		      		</div>
		      		<div class="form-group">
		      			<label class="area-carSize" for="exampleInputPassword1">出入口ip</label>
		      			<input type="text" value="12" class="area-setNum form-control area-sjc area-car" id="exampleInputEmail1" placeholder="">
		      		</div>
		      		<div class="form-group">
		      			<label class="area-carSize" for="exampleInputPassword1">终端的标识符</label>
		      			<input type="text" value="32" class="area-setNum form-control area-wlc area-car" id="exampleInputEmail1" placeholder="">
		      		</div>
		      		<div class="form-group">
		       		  <p  class="btn btn-default ac_delete" data-dismiss="modal">关闭该出入口</p>
		      		</div>
		      	</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary ac_keep">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="car-header">出入口管理</div>
		<div class="alert alert-info area-alertInfo" role="alert">
			<span class="area-allCar">现有出入口：<span>1个</span></span>
			<button type="button" class="btn btn-primary ac_add">新增</button>
		</div>
		<div class="ac-center">
			<form>
				<table class="table">
				  	<tr>
				  		<th>出入口</th>
				  		<th>出入口属性</th>
				  		<th>出入口ip</th>
				  		<th>终端的标识符</th>
				  		<th>操作</th>
				  	</tr>
				  	<tr>
				  		<td class="ac_door">1号出入口</td>
				  		<td>只可进</td>
				  		<td>192.123.1.4</td>
				  		<td>open</td>
				  		<td>
				  			<p class="btn btn-primary ac_bj" data-toggle="modal" data-target="#myModal">编辑</p>
				  		</td>
				  	</tr>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script type="text/javascript">
		seajs.use('page/park-lot/access/accessControl', function (module) {
	        module.run();
	    });
	</script>
</body>
</html>