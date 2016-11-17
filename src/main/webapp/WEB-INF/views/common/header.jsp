<%@ page language="java" pageEncoding="utf-8"%>
<nav class="navbar navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <div class="navbar-brand">
                <span class="navbar-logo"></span>
                <span class="navbar-head">
                    停车场管理系统
                </span>
            </div>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <!--${sessionScope.user.userName}-->
            <li class="nav-info">欢迎您&nbsp;</li>
            <li id="realtime" class="nav-button" title="实时信息"><span class="glyphicon glyphicon-dashboard"></span></li>
            <li id="exit" class="nav-button" title="退出"><span class="glyphicon glyphicon-log-out"></span></li>
        </ul>
    </div>
</nav>
<script>
    seajs.use('common');
</script>
