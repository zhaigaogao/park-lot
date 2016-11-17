<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head-public.jsp"%>
<link rel="stylesheet" href="${contextPath}/assets/dep/zTree/css/zTreeStyle/zTreeStyle.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<jsp:include page="/WEB-INF/views/common/menu.jsp">
    <jsp:param name="currentMenu" value="employee" />
</jsp:include>
<div id="main">
    <jsp:include page="/WEB-INF/views/common/breadcrumbs.jsp">
        <jsp:param name="position" value="用户管理" />
    </jsp:include>
    <div id="content" class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <div class="panel">
                    <div class="panel-body">
                        <button type="button" class="btn btn-default" role="add-dept">添加一级部门</button>
                    </div>
                </div>
                <ul id="emp-tree" class="ztree"></ul>
            </div>
            <div class="col-md-9">
                <div class="panel">
                    <div id="buttons" class="panel-body">
                        <div class="btn-group pull-left">
                            <button type="button" class="btn btn-primary" role="edit">编辑部门</button>
                            <button type="button" class="btn btn-danger" role="remove">删除部门</button>
                            <button type="button" class="btn btn-default" role="add-child">添加子部门</button>
                        </div>
                        <div class="btn-group pull-right">
                            <button type="button" class="btn btn-default" role="add-user">添加人员</button>
                            <!--<button type="button" class="btn btn-default">同步人员</button>-->
                        </div>
                    </div>
                </div>
                <table id="emp-table" class="table table-bordered">
                    <thead>
                        <tr>
                            <th>姓名</th>
                            <th>职务</th>
                            <th>手机号码</th>
                            <th>工号</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
                <div id="pagination" class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<script id="tmpl-row" type="text/html">
    <td>{{userName}}</td>
    <td>{{post}}</td>
    <td>{{mobile}}</td>
    <td>{{workNumber}}</td>
    <td>
        <a href="javascript:" role="edit">编辑</a>
        <a href="javascript:" role="remove">删除</a>
        <a href="javascript:" role="car">车辆信息</a>
        <!--<a href="javascript:" role="change">更换部门</a>-->
    </td>
</script>

<script id="tmpl-deptModal" type="text/html">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">{{ model._isNew ? '添加部门' : '编辑部门' }}</h4>
            </div>
            <div class="modal-body">
                <form class="form">
                    <input type="hidden" name="id" value="{{model.id}}">
                    <input type="hidden" name="previousId" value="{{model.previousId}}">
                    <div class="form-group">
                        <label for="orgName" class="control-label">名称<i class="required">*</i></label>
                        <input type="text" class="form-control" id="orgName" name="orgName" placeholder="部门名称" value="{{model.orgName}}">
                    </div>
                    <div class="form-group">
                        <label for="showindex" class="control-label">序号<i class="required">*</i></label>
                        <input type="text" class="form-control" id="showindex" name="showindex" placeholder="部门序号" value="{{model.showindex}}">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-do="submit">保存</button>
            </div>
        </div>
    </div>
</script>

<script id="tmpl-empModal" type="text/html">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">{{ model._isNew ? '添加人员' : '编辑人员' }}</h4>
            </div>
            <div class="modal-body">
                <form class="form">
                    <input type="hidden" name="id" value="{{model.id}}">
                    <input type="hidden" name="orgId" value="{{model.orgId}}">
                    <div class="form-group">
                        <label for="userName" class="control-label">姓名<i class="required">*</i></label>
                        <input type="text" class="form-control" id="userName" name="userName" placeholder="姓名" value="{{model.userName}}">
                    </div>
                    <div class="form-group">
                        <label for="mobile" class="control-label">手机<i class="required">*</i></label>
                        <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机" value="{{model.mobile}}">
                    </div>
                    <div class="form-group">
                        <label for="post" class="control-label">职务</label>
                        <input type="text" class="form-control" id="post" name="post" placeholder="职务" value="{{model.post}}">
                    </div>
                    <div class="form-group">
                        <label for="workNumber" class="control-label">工号</label>
                        <input type="text" class="form-control" id="workNumber" name="workNumber" placeholder="工号" value="{{model.workNumber}}">
                    </div>
                    <div class="form-group">
                        <label for="showindex" class="control-label">排序序号<i class="required">*</i></label>
                        <input type="text" class="form-control" id="showindex" name="showindex" placeholder="排序序号" value="{{model.showindex}}">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-do="submit">保存</button>
            </div>
        </div>
    </div>
</script>

<script id="tmpl-carModal" type="text/html">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">人员车辆信息</h4>
            </div>
            <div class="modal-body">
                <form class="form form-horizontal">
                    <input type="hidden" name="userId" value="{{model.id}}">
                    <input type="hidden" name="carMark" value="2">
                    <div class="form-group">
                        <label for="carNumber" class="col-md-2 control-label">车牌号<i class="required">*</i></label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" id="carNumber" name="carNumber" placeholder="车牌号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="carModel" class="col-md-2 control-label">车型</label>
                        <div class="col-md-10">
                            <label class="radio-inline">
                                <input type="radio" name="carModel" value="1" checked> 小型车
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="carModel" value="2"> 中型车
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="carModel" value="3"> 大型车
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="button" class="btn btn-primary pull-right" data-do="submit">添加</button>
                        </div>
                    </div>
                </form>
                <div class="panel panel-default">
                    <div class="panel-heading">{{model.userName}}的车辆信息</div>
                    <div class="panel-body" style="max-height: 200px; overflow-y: auto;">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>车牌号</th>
                                    <th>车型</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</script>

<script id="tmpl-car-row" type="text/html">
    <td>{{carNumber}}</td>
    <td>{{carModel == 1 ? '小型车' : (carModel == 2 ? '中型车' : '大型车' )}}</td>
    <td>
        <a href="javascript:" role="remove">删除</a>
    </td>
</script>

<script>
    seajs.use('page/park-lot/employee/manager', function (module) {
        module.run();
    });
</script>

</body>
</html>
