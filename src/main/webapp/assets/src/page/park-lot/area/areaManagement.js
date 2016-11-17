/**
 * 车位管理
 * Jianeng.Hu <jnhuc@isoftstone.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery');
	require('bootstrap');
    var alert = require('component/Alert');

	var urls = {
		areaCars : CONTEXT_PATH + '/area/selectCarInfoByPrimaryKey.do',
		areaCarsChange : CONTEXT_PATH + '/area/updateArea.do'
	}
	var areaFu = {
		html : "",
		allCars : "",
		carRead : "",
		carSurplus : "",
		row : function(data){
			areaFu.html = '<div class="col-sm-6 col-md-4">'
			    +'<div class="thumbnail">'
			    +'<p class="bg-primary area-titleCo">公车</p>'
					+'<div class="caption">'
						+'<p>该区域总停车位：<span class="area-carNum">'+data.model[1].leaderArea+'</span></p>'
						+'<p>该区域已停车位：<span class="area-carNum">'+data.model[2].leaderArea+'</span></p>'
						+'<p>该区域剩余可停车位：<span class="area-carNum">'+data.model[0].leaderArea+'</span></p>'
					+'</div>'
			    +'</div>'
		    +'</div>'
		    +'<div class="col-sm-6 col-md-4">'
			    +'<div class="thumbnail">'
			    +'<p class="bg-primary area-titleCo">私家车</p>'
					+'<div class="caption">'
						+'<p>该区域总停车位：<span class="area-carNum">'+data.model[1].innerArea+'</span></p>'
						+'<p>该区域已停车位：<span class="area-carNum">'+data.model[2].innerArea+'</span></p>'
						+'<p>该区域剩余可停车位：<span class="area-carNum">'+data.model[0].innerArea+'</span></p>'
					+'</div>'
			    +'</div>'
		    +'</div>'
		    +'<div class="col-sm-6 col-md-4">'
			    +'<div class="thumbnail">'
			    +'<p class="bg-primary area-titleCo">外来车</p>'
					+'<div class="caption">'
						+'<p>该区域总停车位：<span class="area-carNum">'+data.model[1].outerArea+'</span></p>'
						+'<p>该区域已停车位：<span class="area-carNum">'+data.model[2].outerArea+'</span></p>'
						+'<p>该区域预约车位：<span class="area-carNum">'+data.model[2].outerArea+'</span></p>'
						+'<p>该区域剩余可停车位：<span class="area-carNum">'+data.model[0].outerArea+'</span></p>'
					+'</div>'
			    +'</div>'
		    +'</div>';
		    $(".area-row").append(areaFu.html);
		},
		modal_body : function(data){
			areaFu.allCars = data.model[1].leaderArea + data.model[1].innerArea + data.model[1].outerArea;
			areaFu.html = '<div class="form-group">'
			    +'<label class="area-carSize" for="exampleInputPassword1">总车位数</label>'
			    +'<span class="areaAllCars">'+data.model[1].totalCar+'</span>'
			 +'</div>'
			  +'<div class="form-group">'
			    +'<label class="area-carSize" for="exampleInputPassword1">公车车位</label>'
			    +'<input type="number" value="'+data.model[1].leaderArea+'" class="area-setNum form-control area-gc area-car" id="exampleInputEmail1" placeholder="">'
			  +'</div>'
			  +'<div class="form-group">'
			    +'<label class="area-carSize" for="exampleInputPassword1">私家车车位</label>'
			    +'<input type="number" value="'+data.model[1].innerArea+'" class="area-setNum form-control area-sjc area-car" id="exampleInputEmail1" placeholder="">'
			  +'</div>'
			  +'<div class="form-group">'
			    +'<label class="area-carSize" for="exampleInputPassword1">外来车车位</label>'
			    +'<input type="number" value="'+data.model[1].outerArea+'" class="area-setNum form-control area-wlc area-car" id="exampleInputEmail1" placeholder="">'
			  +'</div>';
			  $(".area-modalBody").append(areaFu.html);
			  $(".area-car").change(function(){
				  console.log($(".areaAllCars").text())
				  areaFu.cars = parseInt($(".area-gc").val()) + parseInt($(".area-sjc").val()) + parseInt($(".area-wlc").val());
				  if(areaFu.cars > parseInt($(".areaAllCars").text())){
				  	$(this).val("");
				  	alert("不能超过该区域停车位总数");
				  }
			  })
		},
		cars : "",
		alertInfo : function(data){
			areaFu.html = '<span class="area-allCar">该区域总停车位：<span>'+data.model[1].totalCar+'</span></span><span class="area-allCar">该区域已预约车位：<span>'+data.model[1].totalCar+'</span></span>'
			+'<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">设置</button>';
		    $(".area-alertInfo").append(areaFu.html);
		},
		_ajax : function(){
			$.ajax({
				type:"POST",
				url:urls.areaCars,
				dataType:"json",
				data:{id:1},
				success:function(data){
					console.log(data);
                    if (data.success) {
                        areaFu.row(data);//展示区域内不同类型停车位
                        areaFu.modal_body(data);//设置不同类型停车位数量
                        areaFu.alertInfo(data);//展示区域内停车状态
                    } else {
                        alert(data.message);
                    }
					areaFu.changeData(data);
				}
			});
		},
		changeData : function(res){//上传车位数据给后台
			$(".area-btn").on("click",function(){
				if(areaFu.cars <= parseInt($(".areaAllCars").text())){
					if(res.model[2].innerArea < parseInt($(".area-sjc").val())||res.model[2].leaderArea < parseInt($(".area-gc").val())||res.model[2].outerArea < parseInt($(".area-wlc").val())){
						$.ajax({
							type:"POST",
							url:urls.areaCarsChange,
							dataType:"json",
							data:{"id":1,"owner":"A","inner":parseInt($(".area-sjc").val()),"leader":parseInt($(".area-gc").val()),"outer":parseInt($(".area-wlc").val())},
							success:function(data){
								console.log(data);
								alert(data.message);
								$(".modal-dialog").on("click",function () {
									location.reload([true]);
								})
							}
						});
					}else{
						alert("该车位区域还有已停车辆")
					}
				}
			})
		}
	}
	var run = function(){
		areaFu._ajax();//数据展示渲染
	}
	module.exports = {
        run: run
    };
})
