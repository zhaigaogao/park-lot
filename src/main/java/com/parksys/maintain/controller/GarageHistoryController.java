package com.parksys.maintain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.service.GarageHistoryService;

@Controller
@RequestMapping("GarageHistory")
public class GarageHistoryController extends BaseController{
	
	@Autowired
	private GarageHistoryService garahistory;
	
	/**
	 * @author zhaieryuan
	 * @method 查询历史表信息
	 * @param  GarageHistory 历史表     Page 分页参数
	 * @return GarageHistoryList
	 * @date   2016/10/13
	 */
	@RequestMapping("/selectByParam")
	@ResponseBody
	public JsonResult selectByParam(GarageHistory garageHistory,HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
		JsonResult result=new JsonResult();
		PageHelper.startPage(pageNum, pageSize);
		List<GarageHistory> garageHistoryList=garahistory.SelectGarageHistoryList(garageHistory);
		PageInfo pageinfo = new PageInfo(garageHistoryList);
		result.setSuccess(true);
		result.setModel(pageinfo);
		return result;
	}
}
