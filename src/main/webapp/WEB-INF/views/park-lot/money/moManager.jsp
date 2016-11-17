<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
	<title>预约管理系统</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/money/moManager.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="money" />
</jsp:include>
	<div id="main">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">设置</h4>
		      </div>
		      <div class="modal-body area-modalBody">
		      	<ul class="nav nav-tabs navCar" role="tablist" id="myTab">
						  <li class="selfCarer active" carFreeType="1" carMark="1" role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">小型车</a></li>
						  <li class="selfCarer" carFreeType="1" carMark="2" role="presentation"><a href="#home" role="tab" data-toggle="tab">中型车</a></li>
						  <li class="selfCarer" carFreeType="1" carMark="3" role="presentation"><a href="#home" role="tab" data-toggle="tab">大型车</a></li>
						  <li class="selfCarer" carFreeType="2" carMark="1" role="presentation"><a href="#profile" role="tab" data-toggle="tab">优惠</a></li>
						</ul>

						<div class="tab-content">
						  <div role="tabpanel" class="tab-pane active" id="home">
							  <div class="form-group mo-set">
							    <p class="col-sm-2 mo-day">
							    	<span class="span_active" dayNight="0">白天</span>
							    	<span dayNight="1">晚上</span>
							    </p>
							    <div class="mo_tim">
							    	<div class="form-group">
							    		<label class="area-carSize" for="exampleInputPassword1">免费时长</label>
							    		<input type="number" value="0" class="mo-setNum form-control freeTime" id="exampleInputEmail1" placeholder=""><span class="mo-span">分钟</span>
							    	</div>
							    	<div class="form-group">
							    		<label class="area-carSize" for="exampleInputPassword1">收费标准</label>
							    		<input type="number" value="0" class="mo-setNum form-control feePeriod" id="exampleInputEmail1" placeholder=""><span class="mo-span">元/</span><input type="number" value="0" class="mo-setNum form-control chargePeriod" id="exampleInputEmail1" placeholder=""><span  class="mo-span">分钟</span>
							    	</div>
							    </div>
							  </div>
						  </div>
						  <div role="tabpanel" class="tab-pane" id="profile">
								<div class="form-group" style="margin-top:40px;">
									<label class="area-carSize" for="exampleInputPassword1">包月</label>
									<input type="number" value="0" class="mo-setNum form-control mo-monthPay" placeholder=""><span class="mo-span">元</span>
								</div>
								<div class="form-group">
									<label class="area-carSize" for="exampleInputPassword1">包年</label>
									<input type="number" value="0" class="mo-setNum form-control mo-yearPay" placeholder=""><span class="mo-span">元</span>
								</div>
								<div class="form-group mo-pre">
							    <label class="area-carSize">车型</label>
										<div class="btn-group">
											<button type="button" carid="1" class="btn btn-default carModel">小型车</button>
											<button type="button" carid="2" class="btn btn-default carModel">中型车</button>
											<button type="button" carid="3" class="btn btn-default carModel">大型车</button>
										</div>
								</div>
							</div>
			      </div>
					</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary mo-btn">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="car-header">收费标准</div>
		<div class="area-center">
			<div class="alert alert-info area-alertInfo" role="alert">
				<span class="area-allCar">本日已收停车费：<span>3000元</span></span>
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">设置</button>
			</div>
			<div class="row area-row"></div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script type="text/javascript">
		seajs.use('page/park-lot/money/moManager', function (module) {
	        module.run();
	    });
	</script>
</body>
</html>
