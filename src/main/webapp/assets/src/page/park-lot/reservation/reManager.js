/**
 * 预约管理
 * Jianeng.Hu <jnhuc@isoftstone.com>
 */
'use strict';
var carManagementFu = {};
define(function (require, exports, module) {
	var $ = require('jquery');
    var alert = require('component/Alert');
	require('bootstrap');
	require('./reHtml');
	require('component/datedropper.min');
	require('component/timedropper.min');
	var urls = {
			putCarNew : CONTEXT_PATH + '/vehicInfo/addVehicInfo.do',
			queryCarNews : CONTEXT_PATH + '/vehicInfo/queryPrivateVehicVos.do',
			deleteCarNew : CONTEXT_PATH + '/vehicInfo/deleteVehicInfo.do'
	}
	carManagementFu = {
			userId : "",
			carNumber : "",
			carModel : "",
			carModelName : "",
			carMark : 3,
			html : "",
			li : "",
			liPrev : '<li class="prev"><a href="#">&laquo;</a></li>',
			liNext : '<li class="next"><a href="#">&raquo;</a></li>',
			color : "",
			queryCarNew : {
				carModel : "",
				carMark : 3,
				carNumber : "",
				userName : "",
				mobile : "",
				startTime : "",
				endTime : "",
				pageNum : 1,
				pageSize : 7
			},
			paginationClick : function(){
				$("article").on("click",".paginationClick",function(){
					var _this = $(this);
					$(this).siblings().removeClass("active");
					$(this).addClass("active");
					carManagementFu.queryCarNew.pageNum = parseInt($(this).text());
					carManagementFu.queryCarNews(carManagementFu.queryCarNew.pageNum);
				});
				$("article").on("click",".prev",function(){
					if($(".paginationCom .active").index()>1){
						$(".paginationCom .active").prev().addClass("active");
						$(".paginationCom .active").eq(1).removeClass("active");
						carManagementFu.queryCarNew.pageNum = parseInt($(".paginationCom .active").text());
						carManagementFu.queryCarNews(carManagementFu.queryCarNew.pageNum);
					}else{
						alert("已到第一页！")
					}
				})
				$("article").on("click",".next",function(){
					if($(".paginationCom .active").index()<$(".paginationClick").length){
						$(".paginationCom .active").next().addClass("active");
						$(".paginationCom .active").eq(0).removeClass("active");
						carManagementFu.queryCarNew.pageNum = parseInt($(".paginationCom .active").text());
						carManagementFu.queryCarNews(carManagementFu.queryCarNew.pageNum);
					}else{
						alert("已到最后一页！")
					}
				})
			},
			changeReCarNew : "",
			takeReCar : function () {
				$(".takeReCar").on("click",function () {
					carManagementFus.changeReCar($(this).parent().parent().children().eq(0).text(), $(this).parent().parent().children().eq(1).text(), $(this).parent().parent().children().eq(3).text(), $(this).parent().parent().children().eq(4).text(), $(this).parent().parent().children().eq(5).text())
					$(".takeReCars").html(carManagementFu.changeReCarNew);
					$('#takeModal').modal("show");
					carManagementFus.timeData("#startF","#startFs","#endF","#endFs");//时间插件
				})
			},
			deleteCarNew:function(){
				$(".carsRecord").on("click",".deleteRe",function(){
					var _this = $(this);
					alert("确定删除？")
					$(".alert-modal-dialog .btn").click(function(){
						$.ajax({
				            url: urls.deleteCarNew,
				            dataType: 'json',
				            type: 'post',
				            data:{id:_this.attr("deleteid")},
				            success: function (res) {
				            	if(res.success){
				            		location.reload([true]);
				            	}
				            },
				            error: function () {
				                alert('error');
				            }
				        });
						_this.parent().remove();
					})
				})
			},
			pageNumPa:0,
			queryCarNews:function(pageNum){
				$.ajax({
		            url: urls.queryCarNews,
		            dataType: 'json',
		            type: 'post',
		            data:carManagementFu.queryCarNew,
		            success: function (res) {
		            	carManagementFu.carAdd = '<tr><th>姓名</th><th>手机号码</th><th>车型</th><th>车牌号</th><th>起始时间</th><th>结束时间</th><th>操作</th></tr>';
		                carManagementFu.html = carManagementFu.carAdd;
		            	carManagementFu.li = "";
		                var master = res.model.list;
		                $.each(master,function(i,item){
		                	if(item.carModel == 1){
		                		item.carModel = "小型车";
		                	}else if(item.carModel == 2){
		                		item.carModel = "中型车";
		                	}else{
		                		item.carModel = "大型车";
		                	}
		                	item.carMark = "#eee";
		                	carManagementFu.html += '<tr>'
											+'<td>'+item.userName+'</td>'
											+'<td>'+item.mobile+'</td>'
											+'<td>'+item.carModel+'</td>'
											+'<td>'+item.carNumber+'</td>'
											+'<td><span>2016/10/21</span> <span>7:00</span></td>'
											+'<td><span>2016/10/21</span> <span>9:00</span></td>'
											+'<td><div class="takeReCar">编辑</div><div deleteid="' + item.id + '" class="carAdd deleteRe">删除</div></td>'
										+'</tr>';
		                })
	                	for(var i = 0,j = res.model.pages,k = 0;i<j;i++){
	                		k += 1;
	                		carManagementFu.li += '<li class="paginationClick"><a href="#">' + k + '</a></li>';
	                	}
		                carManagementFu.li = carManagementFu.liPrev + carManagementFu.li + carManagementFu.liNext;
		                $(".paginationCom").html(carManagementFu.li);
		                $(".table").html(carManagementFu.html);
						carManagementFu.takeReCar()
						console.log(pageNum)
						if(pageNum != undefined){
							carManagementFu.pageNumPa = --pageNum;
						}else{
							carManagementFu.pageNumPa = 0;
						}
		                $(".paginationClick").eq(carManagementFu.pageNumPa).addClass("active");
		            },
		            error: function () {
		                alert('error');
		            }
		        });
			},
			upDataCar:function(){
				$(".carModel").on("click",function(){
					carManagementFu.carModel = $(this).attr("carid");
					carManagementFu.carModelName = $(this).text();
				})
				$(".navCar li").on("click",function(){
					carManagementFu.carMark = $(this).attr("carMark");
				})
				$(".upDataCar").on("click",function(){
					carManagementFu.carNumber = $(".carSelf").val();
					if(carManagementFu.carNumber == ""){
						alert("请填写车牌号");
					}else if(carManagementFu.carModel == ""){
						alert("请选择车型");
					}else{
						carManagementFu.carNew(carManagementFu.userId,carManagementFu.carNumber,carManagementFu.carModel,carManagementFu.carMark);
					}
				})
			},
			carNew:function(_userId,_carNumber,_carModel,_carMark){
				$.ajax({
		            url: urls.putCarNew,
		            dataType: 'json',
		            type: 'post',
		            data: {
		            	userId:_userId,
		            	carNumber:_carNumber,
		            	carModel:_carModel,
		            	carMark:_carMark
		            },
		            success: function (res) {
		                console.log(res);
		                if(res.success){
		                	alert("添加成功").delay(1);
		                	$(".carSelf").val("");
			                carManagementFu.carModel = "";
			                carManagementFu.carMark = "";
			                setTimeout(function() {
			                	location.reload([true]);
			                },1000)
		                }
		            },
		            error: function () {
		                alert('error');
		            }
		        });
			}
	}
	var run  = function(){
		carManagementFu.queryCarNews()//总车辆信息
		// carManagementFu.queryCarNew()//查询车辆
		carManagementFu.upDataCar();//上传车辆信息
		carManagementFu.deleteCarNew()//车辆删除
		carManagementFu.paginationClick();//分页器
		carManagementFus.timeData("#pickdate","#picktime","#pickdates","#picktimes");//时间插件
	}
	module.exports = {
        run: run
    };
});
