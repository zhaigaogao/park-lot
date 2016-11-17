<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
	<title>车辆管理系统</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/car/car.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/dep/zTree/css/zTreeStyle/zTreeStyle.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="car" />
</jsp:include>
<div id="main">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">车辆登记</h4>
	      </div>
	      <div class="modal-body">
	        <ul class="nav nav-tabs navCar" role="tablist" id="myTab">
			  <li carMark="1" role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">公车</a></li>
			  <li class="selfCarer" carMark="2" role="presentation"><a href="#profile" role="tab" data-toggle="tab">私家车</a></li>
			</ul>

			<div class="tab-content">
			  <div role="tabpanel" class="tab-pane active" id="home">
				  <div class="form-group">
				    <label class="col-sm-2 control-label">车牌号</label>
				      <input type="text" class="form-control carSelf" placeholder="请输入车牌号码 例：苏A10086">
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">车型</label>
					<div class="btn-group">
						<button type="button" carId="1" class="btn btn-default carModel">小型车</button>
						<button type="button" carId="2" class="btn btn-default carModel">中型车</button>
						<button type="button" carId="3" class="btn btn-default carModel">大型车</button>
					</div>
				  </div>
				  <div class="personCarer">
				  	  <div class=personCar>
					    <label class="col-sm-2 control-label">人员关系</label>
					  </div>
					  <div class="form-group">
					      <ul id="treeDemo" class="ztree" style="margin-left:85px;height: 170px;overflow: scroll;"></ul>
								<%-- <div class="ztreeMo">更多</div> --%>
					  </div>
				  </div>

			  </div>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary upDataCar">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	<article>
		<div class="car-header">车辆管理 </div>
		<section>
			<div class="car-search">
				<input type="text" placeholder="请输入车牌号码、人员姓名">
				<div class="searchClick">查询</div>
			</div>
			<div class="cared">
				<div class="attention">
					<!-- <span style="float:left;margin-top:5px;">私家车</span> -->
					<p style="margin-left:11px;" class="carColor allCar allGoCar">公车</p>
					<p style="margin-left:11px;" class="carColor selftCar allSelftCar">私家车</p>
					<!-- <span style="float:left;margin-top:5px;">公车</span> -->
					<div class="addCars" data-toggle="modal" data-target="#myModal">添加</div>
				</div>
				<div class="carsRecord">
					<button type="button" class="btn btn-primary btn-lg carAdd" data-toggle="modal" data-target="#myModal">
					  +
					</button>
					<div class="cars" style="padding:0;float:left;"></div>
				</div>
			</div>
			<ul class="pagination paginationCom">
				<!-- <li class="paginationFirst"><a href="#">首页</a></li> -->
			</ul>
		</section>
	</article>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script type="text/javascript">
		seajs.use('page/park-lot/car/carManagement', function (module) {
	        module.run();
	    });
	</script>
</body>
</html>
