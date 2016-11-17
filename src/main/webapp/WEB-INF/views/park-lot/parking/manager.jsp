<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
<link rel="stylesheet" href="${contextPath}/assets/dep/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="parking" />
</jsp:include>
<div id="main">
    <jsp:include page="/WEB-INF/views/common/breadcrumbs.jsp">
        <jsp:param name="position" value="用户管理" />
    </jsp:include>
    <div id="content" class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-body">
                        <form class="form-inline" role="form">
                            <div class="form-group">
                                <label for="carNo">车牌号</label>
                                <input type="email" class="form-control" id="carNo" placeholder="车牌号">
                            </div>
                            <div class="form-group">
                                <label for="carNo">开始时间</label>
                                <input type="text" class="form-control" name="startDate" id="startDate">
                            </div>
                            <div class="form-group">
                                <label for="carNo">结束时间</label>
                                <input type="text" class="form-control" name="endDate" id="endDate">
                            </div>

                            <button class="btn btn-primary pull-right">搜索</button>
                        </form>
                    </div>
                </div>
                <table id="parking-table" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>车牌号</th>
                            <th>停车区域</th>
                            <th>所属人员</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>预约信息</th>
                            <th>费用</th>
                            <th>入口</th>
                            <th>出口</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<script id="tmpl-row" type="text/html">
    <td>{{carNo}}</td>
    <td>{{area}}</td>
    <td>{{user}}</td>
    <td>{{startDate}}</td>
    <td>{{endDate}}</td>
    <td>{{order}}</td>
    <td>{{charge}}</td>
    <td>{{ingate}}</td>
    <td>{{outgate}}</td>
</script>

<script>
    seajs.use('page/park-lot/parking/manager', function (module) {
        module.run();
    });
</script>

</body>
</html>
