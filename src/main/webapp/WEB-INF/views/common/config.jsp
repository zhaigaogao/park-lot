<%@ page language="java" pageEncoding="utf-8"%>
<%--
配置项
1、系统配置，由后台输出至页面
2、seajs 配置
--%>
<script id="config">
var SEA_VERSION = '1';
var COMPANY_ID = '<c:out value="${sessionScope.company.id}"/>';
var COMPANY_NAME = '<c:out value="${sessionScope.company.customerName}"/>';
var USER_ID = '<c:out value="${sessionScope.user.id}"/>';
var MOBILEPHONE = '<c:out value="${sessionScope.user.mobile}"/>';
var SECRET_KEY = 0;

var CONTEXT_PATH = '<c:out value="${contextPath}"/>';
var SEA_CONFIG = {
    debug: true,
    base: CONTEXT_PATH + '/assets',
    map: [
    	[ /^(.*\.(?:css|js))(.*)$/i, '$1?' + 'v=' + SEA_VERSION ]
    ],
    paths: {
        'page': 'src/page',
        'component': 'src/component',
        'util': 'src/component/util'
    },
    alias: {
        'jquery': 'dep/jquery/1.11.2/jquery.js',
        'jqueryui': 'dep/jquery-ui/jquery-ui.min.js',
        'bootstrap': 'dep/bootstrap/3.3.6/js/bootstrap-cmd',
        'underscore': 'dep/underscore/1.8.3/cmd/underscore-min',
        'backbone': 'dep/backbone/1.3.3/backbone',

        'plupload': 'dep/plupload/2.1.3/js/plupload.full.min.js',
        'jquery-validate': 'dep/jquery-validation/1.15.0/jquery.validate.min',
        'bootstrap-datetimepicker': 'dep/bootstrap-datetimepicker/js/bootstrap-datetimepicker',
        'bootstrap-datetimepicker-zh-CN': 'dep/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN',
        'echarts': 'dep/echarts/echarts.common.min',

        'ztree': 'dep/zTree/js/jquery.ztree.core',
        'ztreeExedit': 'dep/zTree/js/jquery.ztree.exedit',
        'ztreeCheck': 'dep/zTree/js/jquery.ztree.excheck',

        'md5': 'util/md5',
        'cookie': 'util/cookie',
        'parseQueryString': 'util/parseQueryString',
        'template': 'util/template',
        'jquery-util': 'util/jquery-util',

        'common': 'component/common'
    }
};
</script>
