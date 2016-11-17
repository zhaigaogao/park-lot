/**
 * 收费策略
 * Jianeng.Hu <jnhuc@isoftstone.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery');
	require('bootstrap');
	var urls = {
		moGet : CONTEXT_PATH + '/feeScale/findAllScale.do',
		moSet : CONTEXT_PATH + '/feeScale/updateScale.do',
		freeSet : CONTEXT_PATH + '/feeScale/updateBenefit.do'
	}
	var moFu = {
		html : "",
		row : function(data){
			moFu.html = '<div class="col-sm-6 col-md-4">'
			    +'<div class="thumbnail">'
			    +'<p class="bg-primary area-titleCo">小型车收费标准</p>'
					+'<div class="caption">'
						+'<label>白天</label>'
						+'<div class="caption">'
							+'<p>前'+ data.model[0].freeTime +'分钟免费</p>'
							+'<p>每'+ data.model[0].chargePeriod +'分钟'+ data.model[0].feePeriod +'元</p>'
						+'</div>'
						+'<label>晚上</label>'
						+'<div class="caption">'
							+'<p>前'+data.model[1].freeTime+'分钟免费</p>'
							+'<p>每'+data.model[1].chargePeriod+'分钟'+data.model[1].feePeriod+'元</p>'
						+'</div>'
						+'<label>优惠</label>'
						+'<div class="caption">'
							+'<p>包月'+data.model[1].monthPay+'元</p>'
							+'<p>包年'+data.model[1].yearPay+'元</p>'
						+'</div>'
					+'</div>'
			    +'</div>'
		    +'</div>'
		    +'<div class="col-sm-6 col-md-4">'
			    +'<div class="thumbnail">'
			    +'<p class="bg-primary area-titleCo">中型车收费标准</p>'
					+'<div class="caption">'
						+'<label>白天</label>'
						+'<div class="caption">'
							+'<p>前'+data.model[2].freeTime+'分钟免费</p>'
							+'<p>每'+data.model[2].chargePeriod+'分钟'+data.model[2].feePeriod+'元</p>'
						+'</div>'
						+'<label>晚上</label>'
						+'<div class="caption">'
							+'<p>前'+data.model[3].freeTime+'分钟免费</p>'
							+'<p>每'+data.model[3].chargePeriod+'分钟'+data.model[3].feePeriod+'元</p>'
						+'</div>'
						+'<label>优惠</label>'
						+'<div class="caption">'
							+'<p>包月'+data.model[3].monthPay+'元</p>'
							+'<p>包年'+data.model[3].yearPay+'元</p>'
						+'</div>'
					+'</div>'
			    +'</div>'
		    +'</div>'
		    +'<div class="col-sm-6 col-md-4">'
			    +'<div class="thumbnail">'
			    +'<p class="bg-primary area-titleCo">大型车收费标准</p>'
					+'<div class="caption">'
						+'<label>白天</label>'
						+'<div class="caption">'
							+'<p>前'+data.model[4].freeTime+'分钟免费</p>'
							+'<p>每'+data.model[4].chargePeriod+'分钟'+data.model[4].feePeriod+'元</p>'
						+'</div>'
						+'<label>晚上</label>'
						+'<div class="caption">'
							+'<p>前'+data.model[5].freeTime+'分钟免费</p>'
							+'<p>每'+data.model[5].chargePeriod+'分钟'+data.model[5].feePeriod+'元</p>'
						+'</div>'
						+'<label>优惠</label>'
						+'<div class="caption">'
							+'<p>包月'+data.model[5].monthPay+'元</p>'
							+'<p>包年'+data.model[5].yearPay+'元</p>'
						+'</div>'
					+'</div>'
			    +'</div>'
		    +'</div>';
		    $(".area-row").append(moFu.html);
		},
		_ajax : function(){
			$.ajax({
				type:"POST",
				url:urls.moGet,
				dataType:"json",
				data:{id:1},
				success:function(data){
					moFu.row(data);
				}
			});
		},
		carFreeType : 1,
		changeData : function(){
			$(".mo-btn").on("click",function(){
				moFu.moData.freeTime = parseInt($(".freeTime").val());
				moFu.moData.chargePeriod = parseInt($(".chargePeriod").val());
				moFu.moData.feePeriod = parseInt($(".feePeriod").val());
				if(moFu.carFreeType != 1){
					$.ajax({
						url: urls.freeSet,
						dataType: 'json',
						type: 'post',
						data: {carType:moFu.moData.carType,monthPay:parseInt($(".mo-monthPay").val()),yearPay:parseInt($(".mo-yearPay").val())},
						success: function (res) {
			                if(res.success){
			                  location.reload([true]);
			                }
						},
						error: function () {
							alert('error');
						}
					 });
				}else{
					$.ajax({
			            url: urls.moSet,
			            dataType: 'json',
			            type: 'post',
			            data: moFu.moData,
			            success: function (res) {
			                if(res.success){
			                	location.reload([true]);
			                }
			            },
			            error: function () {
			                alert('error');
			            }
			        });
				}
			})
		},
		moData : {
			freeTime : "",
			chargePeriod : "",
			feePeriod : "",
			dayNight : 0,
			carType : 1
		},
		dataSet:function(){
			$(".mo-day span").on("click",function(){
				$(this).siblings().removeClass("span_active");
				$(this).addClass("span_active");
				moFu.moData.dayNight = parseInt($(this).attr("dayNight"));
			});
			$(".selfCarer").on("click",function(){
				moFu.moData.carType = parseInt($(this).attr("carmark"));
				moFu.carFreeType = parseInt($(this).attr("carFreeType"));
			})
			$(".carModel").on("click",function(){
				moFu.moData.carType = $(this).attr("carid");
			})
		}
	}
	var run = function(){
		moFu._ajax();//数据展示渲染
		moFu.dataSet();
		moFu.changeData();
	}
	module.exports = {
        run: run
    };
})
