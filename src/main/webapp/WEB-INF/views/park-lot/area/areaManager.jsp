<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
	<title>车辆管理系统</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/src/css/area/areaManagement.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/assets/dep/zTree/css/zTreeStyle/zTreeStyle.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="area" />
</jsp:include>
	<div id="main">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="myModalLabel">设置</h4>
		      </div>
		      <div class="modal-body area-modalBody"></div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary area-btn">保存</button>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="car-header">车位管理</div>
		<div class="area-center">
			<div class="alert alert-info area-alertInfo" role="alert"></div>
			<div class="row area-row"></div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script type="text/javascript">
		seajs.use('page/park-lot/area/areaManagement', function (module) {
	        module.run();
	    });
	</script>
</body>
</html>
