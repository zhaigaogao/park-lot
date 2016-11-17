/**
 * 预约管理
 * Jianeng.Hu <jnhuc@isoftstone.com.com>
 */
'use strict';
var carManagementFus = {};
define(function (require, exports, module) {
	carManagementFus = {
		changeReCar : function (userName, phoneNum, carNumber, startTime, endTime) {
				carManagementFu.changeReCarNew = '<div class="modal fade" id="takeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'
				  +'<div class="modal-dialog">'
				    +'<div class="modal-content">'
				      +'<div class="modal-header">'
				        +'<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
				        +'<h4 class="modal-title" id="myModalLabel">车辆登记</h4>'
				      +'</div>'
				      +'<div class="modal-body">'
				        +'<ul class="nav nav-tabs navCar" role="tablist" id="myTab">'
						  +'<li carMark="3" role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">外来车</a></li>'
						+'</ul>'
						+'<div class="tab-content">'
						  +'<div role="tabpanel" class="tab-pane active" id="home">'
						  	  +'<div class="form-group">'
							    +'<label class="col-sm-2 control-label re_label">预约人</label>'
							      +'<input type="text" class="form-control carSelf" value="' + userName + '">'
							  +'</div>'
							  +'<div class="form-group">'
							    +'<label class="col-sm-2 control-label re_label">手机号码</label>'
							      +'<input type="text" class="form-control carSelf" value="' + phoneNum + '">'
							  +'</div>'
							  +'<div class="form-group">'
							    +'<label class="col-sm-2 control-label re_label">车牌号</label>'
							      +'<input type="text" class="form-control carSelf" value="' + carNumber + '">'
							  +'</div>'
							  +'<div class="form-group">'
							    +'<label class="col-sm-2 control-label re_label">车型</label>'
								+'<div class="btn-group">'
									+'<button type="button" carId="1" class="btn btn-default carModel">小型车</button>'
									+'<button type="button" carId="2" class="btn btn-default carModel">中型车</button>'
									+'<button type="button" carId="3" class="btn btn-default carModel">大型车</button>'
								+'</div>'
							  +'</div>'
							   +'<div class="form-group">'
							    +'<label style="width:100%;" class="col-sm-2 control-label">预约时间</label>'
					        		+'<div class="demo">'
										+'<span style="margin-left: 95px;">请选择起始日期：<input type="text" class="input" id="startF" value="' + startTime + '" /></span>'
										+'<span style="margin-left:10px;">时间：<input type="text" class="input" id="startFs" value="' + startTime + '" /></span>'
									+'</div>'
									+'<div class="demo">'
										+'<span style="margin-left: 95px;">请选择结束日期：<input type="text" class="input" id="endF" value="' + endTime + '" /></span>'
										+'<span style="margin-left:10px;">时间：<input type="text" class="input" id="endFs" value="' + startTime + '" /></span>'
									+'</div>'
							  +'</div>'
						  +'</div>'
						+'</div>'
				      +'</div>'
				      +'<div class="modal-footer">'
				        +'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'
				        +'<button type="button" class="btn btn-primary upDataCar">保存</button>'
				      +'</div>'
				    +'</div>'
				  +'</div>'
				+'</div>';
			},
		timeData : function (start, starts, end, ends) {
			$(start).dateDropper({
				animate: false,
				format: 'Y-m-d',
				maxYear: '2020'
			});
			$(starts).timeDropper({
				meridians: false,
				format: '00:00',
			});
			$(end).dateDropper({
				animate: false,
				format: 'Y-m-d',
				maxYear: '2020'
			});
			$(ends).timeDropper({
				meridians: false,
				format: '00:00',
			});
		}
	}
})