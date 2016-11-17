<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="/WEB-INF/common/vars.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<title>车库历史信息</title>
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/jquery-ui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/jquery-ui/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/css/parksys/garageHistory/main.css" />
		<script src="${contextPath}/js/jquery/2.1.3/jquery.js"></script>
		<script src="${contextPath}/js/jquery/easyUI/jquery.min.js"></script>
		<script src="${contextPath}/js/jquery/easyUI/jquery.easyui.min.js"></script>
	</head>
<body class="easyui-layout">
	<ul class="easyui-tree">
        <li>
            <span>车库信息</span>
            <ul>
                 <li><a href="#"  onclick="addTab('google','http://www.baidu.com')">google</a></li>
                 <li><a href="#"  onclick="addTab('google','http://www.baidu.com')">google</a></li>
                 <li><a href="#"  onclick="addTab('google','http://www.baidu.com')">google</a></li>
            </ul>
        </li>
        <li>
            <span>车辆流动信息</span>
            <ul>
                 <li><span>入库车辆</span></li>
                 <li><span>出库车辆</span></li>
                 <li><span>入库未登记车辆</span></li>
                 <li><span>出库未登记车辆</span></li>
                 <li><span>出入均未登记车辆</span></li>
            </ul>
        </li>
        <li>
            <span>盘点记录信息</span>
            <ul>
                 <li><span>盘点人员</span></li>
                 <li><span>盘点记录</span></li>
            </ul>
        </li>
        </ul>
</body>

</html>
