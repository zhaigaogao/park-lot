/**
 * 出入口管理
 * Jianeng.Hu <jnhuc@isoftstone.com>
 */
'use strict';
define(function (require, exports, module) {
	var $ = require('jquery');
	require('bootstrap');
    var alert = require('component/Alert');
	var acFu = {
		newAc : "",
		doorData : {
			doorName : "",
			doorAttr : "",
			doorIp : "",
			doorIdent : ""
		},
		doorNum : parseInt($(".ac_door:last").text()),
		ac_add : function(){
			$(".ac_add").on("click",function(){
				if(acFu.doorNum > 7){
					alert("该区域不支持继续添加出入口");
				}else{
					acFu.doorNum += 1;
					acFu.ac_add = '<tr>'
				  		+'<td class="ac_door">'+ acFu.doorNum +'号出入口</td>'
				  		+'<td>只可进</td>'
				  		+'<td>224.1.2.4</td>'
				  		+'<td></td>'
				  		+'<td>'
				  			+'<p class="btn btn-primary ac_bj" data-toggle="modal" data-target="#myModal">编辑</p>'
				  		+'</td>'
				  	+'</tr>';
					$(".table").append(acFu.ac_add);
				}
			})
		},
		upData:function(){
			$(".ac_keep").on("click",function(){
				
			})
		},
		ac_delete:function(){
			
		}
	}
	var run = function(){
		acFu.ac_add();
	}
	module.exports = {
		run : run
	}
});