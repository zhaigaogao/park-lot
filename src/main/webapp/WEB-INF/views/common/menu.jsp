<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="./taglibs.jsp"%>
<%@ include file="./vars.jsp"%>

<c:set var="currentMenu" value="${pageContext.request.getParameter(\"currentMenu\")}" />

<div class="menu-panel">
    <ul id="menu" class="menu">
        <li>
            <a href="${contextPath}/index.do"
                class="${currentMenu == 'index' ? "current" : ""}">
                <span class="glyphicon glyphicon-home"></span>
                <span>首页</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/employee/manager.do"
                class="${currentMenu == 'employee' ? "current" : ""}">
                <span class="glyphicon glyphicon-user"></span>
                <span>人员管理</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/car/carManager.do"
                class="${currentMenu == 'car' ? "current" : ""}">
                <span class="glyphicon glyphicon-th-list"></span>
                <span>车辆管理</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/area/areaManager.do"
                class="${currentMenu == 'area' ? "current" : ""}">
                <span class="glyphicon glyphicon-road"></span>
                <span>车位管理</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/access/accessControl.do"
                class="${currentMenu == 'access' ? "current" : ""}">
                <span class="glyphicon glyphicon-sort"></span>
                <span>出入口管理</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/reservation/reManager.do"
                class="${currentMenu == 'reservation' ? "current" : ""}">
                <span class="glyphicon glyphicon-edit"></span>
                <span>预约管理</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/money/moManager.do"
                class="${currentMenu == 'money' ? "current" : ""}">
                <span class="glyphicon glyphicon-usd"></span>
                <span>收费策略</span>
            </a>
        </li>
        <li>
            <a href="${contextPath}/parking/manager.do"
                class="${currentMenu == 'parking' ? "current" : ""}">
                <span class="glyphicon glyphicon-stats"></span>
                <span>停车记录</span>
            </a>
        </li>
    </ul>
</div>
