<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
<link rel="stylesheet" href="${contextPath}/assets/src/css/index.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="index" />
</jsp:include>
<div id="main">
    <jsp:include page="/WEB-INF/views/common/breadcrumbs.jsp">
        <jsp:param name="position" value="首页" />
    </jsp:include>
    <div id="content">
        <div class="container-fluid">
            <div class="row parking-info">
            </div>
            <div class="row parking-charts">
                <div class="chart-item col-md-6">
                    <div class="chart chart-inner"></div>
                </div>
                <div class="chart-item col-md-6">
                    <div class="chart chart-outer"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<script id="tmpl-info" type="text/html">
    <div class="info-item col-md-2">
        <strong style="color:{{colors.unused}}">{{formatNumber(model.innerUsed)}}</strong>
        <span>员工可用车位</span>
    </div>
    <div class="info-item col-md-2">
        <strong style="color:{{colors.used}}">{{formatNumber(model.innerUnused)}}</strong>
        <span>员工已用车位</span>
    </div>
    <div class="info-item col-md-2">
        <strong style="color:{{colors.order}}">{{formatNumber(model.outerOrder)}}</strong>
        <span>外部车辆预约车位</span>
    </div>
    <div class="info-item col-md-2">
        <strong style="color:{{colors.used}}">{{formatNumber(model.outerOrderUsed)}}</strong>
        <span>外部车辆预约已用车位</span>
    </div>
    <div class="info-item col-md-2">
        <strong style="color:{{colors.used}}">{{formatNumber(model.outerUsed)}}</strong>
        <span>外部车辆已用车位</span>
    </div>
    <div class="info-item col-md-2">
        <strong style="color:{{colors.unused}}">{{formatNumber(model.outerUnused)}}</strong>
        <span>外部车辆可用车位</span>
    </div>
</script>
<script id="tmpl-row" type="text/html">
</script>
<script>
    seajs.use(['common', 'page/park-lot/index'], function (common, module) {
        module.run();
    });
</script>

</body>
</html>
