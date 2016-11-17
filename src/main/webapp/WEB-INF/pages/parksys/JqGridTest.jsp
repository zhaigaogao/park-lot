 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="/WEB-INF/common/vars.jsp"%>
 <%@ include file="/WEB-INF/pages/parksys/include/mainInclude.jsp"%>
 <%@ include file="/WEB-INF/pages/parksys/include/jqGridInclude.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  
<title>My First Grid</title>
<style>
html, body {  
   margin: 0;
   padding: 0;
   font-size: 75%;
}
  
</style>
  
</head>
  
<body>
  
    <table id="list1" class="scroll" cellpadding='0' cellspacing='0' >
    </table>
    <div id="pager1" class="scroll" style="text-align:center;">
    </div>
    <script type="text/javascript">
    $(function(){
    	  pageInit();
    	});
    	function pageInit(){
    	  $("#list1").jqGrid(
    	      {
    	        url : '${contextPath}/test/selectByParam.do',
    	        jsonReader : {
    	        	      root: "model.list",    // json中代表实际模型数据的入口
    	        	      page: "pageNum",    // json中代表当前页码的数据
    	        	      total: "total",    // json中代表页码总数的数据
    	        	      records: "50", // json中代表数据行总数的数据
    	        	      repeatitems: true, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
    	        	      cell: "model.list",
    	        	      subgrid: {
    	        	        root:"model.list", 
    	        	        repeatitems: true, 
    	        	        cell:"model.list"
    	        	    }
    	        	},
    	        datatype : "json",
    	        colNames : [ 'id', '入库时间', '出库时间', '车位编号', '停车状态','数据来源','登记时间', '车牌号' ],
    	        colModel : [ 
    	                     {name : 'id',index : 'id',width : 75}, 
    	                     {name : 'storageTime',index : 'storageTime',width : 90}, 
    	                     {name : 'deliveryTime',index : 'deliveryTime',width : 90},
    	                     {name : 'parkNumber',index : 'parkNumber',width : 90},
    	                     {name : 'parkStatus',index : 'parkStatus',width : 90}, 
    	                     {name : 'dataSources',index : 'dataSources',width : 90}, 
    	                     {name : 'regisTime',index : 'regisTime',width : 90}, 
    	                     {name : 'plateNumber',index : 'plateNumber',width : 90}
    	                   ],
    	        rowNum : 30,
    	        height: 468,
    	        autowidth : true,
    	        rowList : [ 10, 20, 30 ],
    	        pager : jQuery('#pager1'),
    	        mtype : "post",
    	        sortname : 'id',
    	        viewrecords : true
    	      }).navGrid('#pager1', {
    	    edit : true,
    	    add : true,
    	    del : true
    	  }); 
    	}
    </script>
  
</body>
  
</html>