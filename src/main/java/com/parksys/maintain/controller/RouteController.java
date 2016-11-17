package com.parksys.maintain.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;

@Controller
@RequestMapping("/main")
public class RouteController extends BaseController{
	
	@Autowired
	private GarageHistoryService garahistory;
	
	@Autowired
	private GarageTableService garageTableServiceImpl;
	
	@Autowired
	private GarageHistoryService garageHistoryServiceImpl;
	/**
	 * 添加网站的静态路由
	 */
	private Logger log=Logger.getLogger(this.getClass());
	
	@RequestMapping("/garageHitory")
	public ModelAndView garageHitory(GarageHistory garageHistory,HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		JsonResult result=new JsonResult();
		List<GarageHistory> garageHistoryList=garageHistoryServiceImpl.SelectGarageHistoryList(garageHistory);
		ModelAndView mv=new ModelAndView("parksys/main");
		mv.addObject("garageHistoryList", garageHistoryList);
		return mv;
	}
}
