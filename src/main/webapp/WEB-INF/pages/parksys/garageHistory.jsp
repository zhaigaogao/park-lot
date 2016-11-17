<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="/WEB-INF/common/vars.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<title>车库历史信息</title>
	</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
	
      <a href="${contextPath}/ExportGarageTable/exportGarageTableList.do"
       class="easyui-linkbutton" onclick="$('#btn-edit').menubutton('disable')">导出车库表Excel</a>
       
      <a href="${contextPath}/ExportGarageHistory/exportGarageHistoryList.do" 
      class="easyui-linkbutton" onclick="$('#btn-edit').menubutton('disable')">导出历史表Excel</a>
      
    </div>
    <script>
   //初始化加载方法
   function addTab(title, url){
		if ($('#tt').tabs('exists', title)){
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}
	}
</script>
	
	<!--左侧菜单栏start-->
	<div data-options="region:'west',split:true,title:'菜单栏'" style="width:150px;padding:10px;">
		<ul class="easyui-tree">
        <li>
            <span>车库信息</span>
            <ul>
                 <li><a href="#"  onclick="addTab('google1','http://www.baidu.com')">google1</a></li>
                 <li><a href="#"  onclick="addTab('google2','http://www.baidu.com')">google2</a></li>
                 <li><a href="#"  onclick="addTab('google3','http://www.baidu.com')">google2</a></li>
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
	</div>
	<!--左侧菜单栏end-->
	
	<!--右侧弹出框start-->
	<div data-options="region:'east',split:true,collapsed:true,title:'弹出框'" style="width:100px;padding:10px;">
		
	</div>
	<!--左侧菜单栏end-->
	
	<!--尾部显示层start-->
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">
		
	</div>
	<!--尾部显示层end-->
	
	<!--页面中心显示层start-->
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="主页" style="padding:10px;">
			   <table class="easyui-datagrid" data-options="fit:true" fitColumns="true">
	            <thead>
		        <tr>
			    <th field="itemid" width="30" align="center">编号</th>
			    <th field="productid" width="80" align="center">入库时间</th>
			    <th field="listprice" align="center" width="70">出库时间</th>
			    <th field="unitcost" align="center" width="70">停车编号</th>
			    <th field="attr1" align="center" width="70">停车状态</th>
			    <th field="status" align="center" width="50">记录状态</th>
		        </tr>
	            </thead>
	            <c:forEach var="test" items="${garageHistoryList }" >
	               <tr>
	               <td>${test.id }</td>
	               <td>${test.storageTime }</td>
	               <td>${test.deliveryTime }</td>
	               <td>${test.parkNumber }</td>
	               <td>${test.parkStatus }</td>
	               <td>${test.dataSources }</td>
	               </tr>
	            </c:forEach>
               </table>
			</div>
			<div title="出库车辆">
			<iframe src="${contextPath}/test/tabsjsp.do" width="100%" height="100%"> 
			</div>
		</div>
	</div>
	<!--页面中心显示层end-->
	
</body>

</html>
