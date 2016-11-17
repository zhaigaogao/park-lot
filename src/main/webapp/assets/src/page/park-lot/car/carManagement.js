/**
 * 车辆管理
 * Jianeng.Hu <jnhuc@isoftstone.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery');
    var alert = require('component/Alert');
	require('bootstrap');
	require('ztree');
	var urls = {
			putCarNew : CONTEXT_PATH + '/vehicInfo/addVehicInfo.do',/* 数据上传接口 */
			qCN : CONTEXT_PATH + '/vehicInfo/queryVehicInfoVos.do',/* 查询所有车辆信息接口 */
			deleteCarNew : CONTEXT_PATH + '/vehicInfo/deleteVehicInfo.do',/* 删除车辆信息接口 */
			ztAll : CONTEXT_PATH + '/user/findAllOrgs.do',/* 人员关系信息接口 */
			zt : CONTEXT_PATH + '/user/findUsers.do',/* 人员信息接口 */
			publicCar : CONTEXT_PATH + '/vehicInfo/queryCommonVehicVos.do',/* 所有公车信息接口 */
			selfCar : CONTEXT_PATH + '/vehicInfo/queryPrivateVehicVos.do'/* 所有私家车信息接口 */
	}
	var carM = {
			userId : "",
			carNumber : "",
			carModel : "",
			carModelName : "",
			carMark : 1,
			html : "",
			li : "",
			liPrev : '<li class="prev"><a href="#">&laquo;</a></li>',
			liNext : '<li class="next"><a href="#">&raquo;</a></li>',
			color : "",
			qAC:{
				pageNum:1,
				pageSize:10,
				carNumber:"",
				userName:""
			},
			ajaxPage:{
				pageNum:1,
				pageSize:10
			},
			carType:3,
			paginationClick : function(){/*根据不同类车切换不同分页器*/
				if(carM.carType == 1){
					carM.pagination(urls.publicCar);
				}else if(carM.carType == 2){
					carM.pagination(urls.selfCar);
				}else if(carM.carType == 3){
					carM.pagination(urls.qCN);
				}
			},
			carPage:function(){/*总车量信息*/
				carM.qCN(urls.qCN);
				carM.paginationClick()
				$(".allGoCar").on("click",function(){
					carM.qCN(urls.publicCar);
					carM.carType = 1;
					$(".car_active").removeClass("car_active");
					$(this).addClass("car_active");
					carM.paginationClick()
				});
				$(".allSelftCar").on("click",function () {
					carM.qCN(urls.selfCar);
					carM.carType = 2;
					$(".car_active").removeClass("car_active");
					$(this).addClass("car_active");
					carM.paginationClick()
				});
				$(".selfCarer").on("click",function(){/*显示人员树*/
					$(".personCarer").css("display","block");
				})
				$(".selfCarer").siblings().on("click",function(){/*隐藏人员树*/
					$(".personCarer").css("display","none");
				});
			},
			pagination : function(url){/*分页器*/
				$("article").off("click").on("click",".paginationFirst",function(){
					carM.pageAjax(1,url);
				});
				$("article").on("click",".paginationClick",function(){
					var _this = $(this);
					$(this).siblings().removeClass("active");
					$(this).addClass("active");
					carM.qAC.pageNum = parseInt($(this).text());
					carM.pageAjax(carM.qAC,url);
				});
				$("article").on("click",".prev",function(){
					if($(".paginationCom .active").index()>1){
						$(".paginationCom .active").prev().addClass("active");
						$(".paginationCom .active").eq(1).removeClass("active");
						carM.qAC.pageNum = parseInt($(".paginationCom .active").text());
						carM.pageAjax(carM.qAC,url);
					}else{
						alert("已到第一页！");
					}
				})
				$("article").on("click",".next",function(){
					if($(".paginationCom .active").index()<$(".paginationClick").length){
						$(".paginationCom .active").next().addClass("active");
						$(".paginationCom .active").eq(0).removeClass("active");
						carM.qAC.pageNum = parseInt($(".paginationCom .active").text());
						carM.pageAjax(carM.qAC,url);
					}else{
						alert("已到最后一页！")
					}
				})
			},
			pageAjax:function(pageNum,url){/*点击分页器后展示对应页面信息*/
				$.ajax({
		            url: url,
		            dataType: 'json',
		            type: 'post',
		            data: pageNum,
		            success: function (res) {
	                	carM.carHtml(res);
		                $(".carsRecord").html(carM.html);
		            },
		            error: function () {
		                alert('error');
		            }
		        });
			},
			treeSetting: {
	            view: {
	                showLine: false,
	                showIcon: false,
	                showSelectStyle: true,
	                txtSelectedEnable: true
	            },
	            data: {
	                simpleData: {
	                    enable: true,
	                    rootPId: 0
	                }
	            },
	            async : {
	            	enable: true,
	            	url: urls.zt,
	            	autoParam:['id=orgId'],
	            	otherParam: ['pageSize','999999'],
	            	dataFilter: function (treeId, parentNode, resData) {
	            		if (resData.success) {
	            			$.each(resData.model.list, function (i, item) {
	            				item.name = item.userName;
	            				item.pId = item.orgId;
	            				item.isParent = false;
	            			});
	            			return resData.model.list;
	            		}
	            	}
	            },
	            callback : {
	            	beforeClick : function (treeId, treeNode) {
	            		return !treeNode.isParent;
								},
	            	onClick: function (event, treeId, treeNode, clickFlag) {
	            		carM.userId = treeNode.id;
	            	}
	            }
	        },
	        zTreeObj : "",
	        zNodes : "",
			zTree:function(){/*ztreejs*/
			    $.ajax({
		            url: urls.ztAll,
		            dataType: 'json',
		            type: 'post',
		            success: function (res) {
		                $.each(res.model,function(i,item){
		                	item.pId = item.previousId;
		                	item.name = item.orgName;
		                	item.isParent = true;
		                })
		                carM.zNodes = res.model;
		                carM.zTreeObj = $.fn.zTree.init($("#treeDemo"), carM.treeSetting, carM.zNodes);
		            },
		            error: function () {
		                alert('error');
		            }
		        });
			},
			bootS:function(){
				$('.dropdown-toggle').dropdown()
				$('#myTab a').click(function (e) {/*tab切换车种类*/
				    e.preventDefault()
				    $(this).tab('show')
				})
			},
			carNew:function(_userId,_carNumber,_carModel,_carMark){/*新增车辆*/
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
		                if(res.success){
			                location.reload([true])
		                }else{
		                	alert("该车已停入车库")
		                }
		            },
		            error: function () {
		                alert('error');
		            }
		        });
			},
			deleteCarNew:function(){/*删除车辆*/
				$(".carsRecord").on("click",".deleteCar",function(){
					var _this = $(this);
					alert("确定删除？")
					$(".alert-modal-dialog .btn").click(function(){
						$.ajax({
				            url: urls.deleteCarNew,
				            dataType: 'json',
				            type: 'post',
				            data:{id:_this.parent().attr("deleteid")},
				            success: function (res) {},
				            error: function () {
				                alert('error');
				            }
				        });
						_this.parent().remove();
					})
				})
			},
			qCN:function(url){/*查询总车辆信息*/
				carM.li = "",
				$.ajax({
		            url: url,
		            dataType: 'json',
		            type: 'post',
		            data:carM.ajaxPage,
		            success: function (res) {
		                carM.carHtml(res);
	                	for(var i = 0,j = res.model.pages,k = 0;i<j;i++){
	                		k += 1;
	                		carM.li += '<li class="paginationClick"><a href="#">' + k + '</a></li>';
	                	}
		                carM.li = carM.liPrev + carM.li + carM.liNext;
		                $(".paginationCom").html(carM.li);
		                $(".carsRecord").html(carM.html);
		                $(".paginationClick").eq(0).addClass("active");
		            },
		            error: function () {
		                alert('error');
		            }
		        });
			},
			queryCarNew:function(){/*车辆查询功能*/
				$(".searchClick").on("click",function(){
					carM.carType = 3;
					carM.html = "";
					carM.qAC.userName = "";
					carM.qAC.carNumber = "";
					carM.qAC.pageNum = 1;
					if(/[a-zA-Z0-9]$/.test($(this).siblings().val())){
						carM.qAC.carNumber = $(this).siblings().val();
					}else{
						carM.qAC.userName = $(this).siblings().val();
					}
					if($(this).siblings().val() == ""){
						carM.qCN(urls.qCN);
						carM.carType = 3;
						$(".car_active").removeClass("car_active");
						$(this).addClass("car_active");
						carM.paginationClick()
					}else{
						$.ajax({
				            url: urls.qCN,
				            dataType: 'json',
				            type: 'post',
				            data:carM.qAC,
				            success: function (res) {
				                if(res.success){
				                	carM._ajax(res);
				                }else{
				                	alert("该数据不存在");
				                	$(".carsRecord").html("");
				                }
				            },
				            error: function () {
				                alert('error');
				            }
				        });
					}
				})
			},
			carHtml : function (res) {
				carM.html = "";
				var master = res.model.list;
				carM.carAdd = '<button type="button" class="btn btn-primary btn-lg carAdd" data-toggle="modal" data-target="#myModal">+</button>';
		        carM.html = carM.carAdd;
                $.each(master,function(i,item){
                	if(item.carModel == 1){
                		item.carModel = "小型车";
                	}else if(item.carModel == 2){
                		item.carModel = "中型车";
                	}else{
                		item.carModel = "大型车";
                	}
                	if(item.carMark == 1){
                		item.carMark = "#AAA";
                		carM.html += '<div class="carNew selftCar" deleteid="'+item.id+'" style="background-color:'+item.carMark+'">'
							+'<p>'+item.carNumber+'</p>'
							+'<p>'+item.carModel+'</p>'
							+'<img class="deleteCar" src="' + CONTEXT_PATH + '/assets/src/css/image/deleteCar.jpg">'
						+'</div>';
                	}else if(item.carMark == 2){
                		item.carMark = "skyblue";
                		carM.html += '<div class="carNew selftCar" deleteid="'+item.id+'" style="background-color:'+item.carMark+'">'
							+'<p>'+item.carNumber+'</p>'
							+'<p>'+item.carModel+'</p>'
							+'<div class="carPerson">'
								+'<p>' + item.userName + '<p><p>' + item.mobile + '<p>'
							+'</div>'
							+'<img class="deleteCar" src="' + CONTEXT_PATH + '/assets/src/css/image/deleteCar.jpg">'
						+'</div>';
                	}
                })
			},
			_ajax:function(res){
				carM.carHtml(res);
                $(".carsRecord").html(carM.html);
                $('#example').popover({});
			},
			upDataCar:function(){/*上传新增车辆相关信息*/
				$(".carModel").on("click",function(){
					carM.carModel = $(this).attr("carid");
					carM.carModelName = $(this).text();
				})
				$(".navCar li").on("click",function(){
					carM.carMark = $(this).attr("carMark");
				})
				$(".upDataCar").on("click",function(){
					carM.carNumber = $(".carSelf").val();
					if(carM.carNumber == ""){
						alert("请填写车牌号");
					}else if(carM.carModel == ""){
						alert("请选择车型");
					}else if(/^[\u4E00-\u9FA5][a-zA-Z0-9]{6}$/.test(carM.carNumber) == false){
						alert("请填写正确的车牌号");
					}else{
						if(carM.carMark == 2){
							if(carM.userId == ""){
								alert("请选择人员关系");
							}else{
								carM.carNumber,carM.carModel = parseInt(carM.carModel);
								carM.carNew(carM.userId,carM.carNumber,carM.carModel,carM.carMark);
							}
						}else{
							carM.carNumber,carM.carModel = parseInt(carM.carModel);
							carM.carNew(carM.userId,carM.carNumber,carM.carModel,carM.carMark);
						}
					}
				})
			},
			yan:function () {/*新增车辆验证属于车牌号输入格式*/
				$(".carSelf").change(function(){
					 if(!/^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test($(this).val())){
					 	alert("请填写正确的车牌号");
					 }
				})
			}
	}
	var run  = function(){
		carM.carPage()/*总车辆信息*/
		carM.queryCarNew()/*查询车辆*/
		carM.zTree();/*人员关系树*/
		carM.bootS();/*bootStarp相关函数*/
		carM.upDataCar();/*上传车辆信息*/
		carM.deleteCarNew()/*车辆删除*/
		carM.yan();/*车牌号验证*/
	}
	module.exports = {
        run: run
    };
});
