<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
	<title>预约管理系统</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/reservation/datedropper.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/reservation/timedropper.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/reservation/reManager.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="reservation" />
</jsp:include>
	<div id="main">
		<div class="takeReCars"></div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">车辆登记</h4>
		      </div>
		      <div class="modal-body">
		        <ul class="nav nav-tabs navCar" role="tablist" id="myTab">
				  <li carMark="3" role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">外来车</a></li>
				</ul>

				<div class="tab-content">
				  <div role="tabpanel" class="tab-pane active" id="home">
				  	  <div class="form-group">
					    <label class="col-sm-2 control-label re_label">预约人</label>
					      <input type="text" class="form-control carSelf" placeholder="请输入预约人姓名">
					  </div>
				  	  <div class="form-group">
					    <label class="col-sm-2 control-label re_label">手机号码</label>
					      <input type="number" class="form-control carSelf" placeholder="请输入预约人手机号码">
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label re_label">车牌号</label>
					      <input type="text" class="form-control carSelf" placeholder="请输入车牌号">
					  </div>
					  <div class="form-group">
					    <label class="col-sm-2 control-label re_label">车型</label>
						<div class="btn-group">
							<button type="button" carId="1" class="btn btn-default carModel">小型车</button>
							<button type="button" carId="2" class="btn btn-default carModel">中型车</button>
							<button type="button" carId="3" class="btn btn-default carModel">大型车</button>
						</div>
					  </div>
					   <div class="form-group">
					    <label style="width:100%;" class="col-sm-2 control-label">预约时间</label>
			        		<div class="demo">
								<span style="margin-left: 95px;">请选择起始日期：<input type="text" class="input" id="pickdate" placeholder="请点击" /></span>
								<span style="margin-left:10px;">时间：<input type="text" class="input" id="picktime" /></span>
							</div>
							<div class="demo">
								<span style="margin-left: 95px;">请选择结束日期：<input type="text" class="input" id="pickdates" placeholder="请点击" /></span>
								<span style="margin-left:10px;">时间：<input type="text" class="input" id="picktimes" /></span>
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
			<div class="car-header">预约管理
				<div class="carAdd" data-toggle="modal" data-target="#myModal">添加预约</div>
			</div>
			<section>
				<div class="car-search">
					<input type="text" placeholder="请输入车牌号或者车型查询">
					<div class="searchClick">查询</div>
				</div>
				<div class="cared">
					<div class="carsRecord">
						<table class="table">
							<tr>
								<th>姓名</th>
								<th>手机号码</th>
								<th>车型</th>
								<th>车牌号</th>
								<th>起始时间</th>
								<th>结束时间</th>
								<th>操作</th>
							</tr>
						</table>
						<div class="cars" style="padding:0;float:left;"></div>
					</div>
				</div>
				<ul class="pagination paginationCom">
				</ul>
			</section>
		</article>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script type="text/javascript">
		seajs.use('page/park-lot/reservation/reManager', function (module) {
	        module.run();
	    });
	</script>
</body>
</html>
