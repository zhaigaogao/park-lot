package com.parksys.maintain.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;

@Controller
@RequestMapping("test")
public class TestController extends BaseController{
	private static final ResourceBundle bundle = ResourceBundle.getBundle("redis");
	private String redisHost = bundle.getString("redis.ip");
	private int redisPort = Integer.valueOf(bundle.getString("redis.port"));
	private String redisPassword = bundle.getString("redis.auth");
	private int redisMaxWait = Integer.valueOf(bundle.getString("redis.expiretime"));
	
	
	@Autowired
	private GarageHistoryService garahistory;
	
	@Autowired
	private GarageTableService garageTableServiceImpl;
	
	@Autowired
	private GarageHistoryService garageHistoryServiceImpl;
	/**
	 * @author zhaieryuan
	 * @see: 项目环境测试返回页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/test01")
	public ModelAndView testController(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("test/test01");
		request.setAttribute("param2", "page01");
		request.setAttribute("redisHost", redisHost);
		request.setAttribute("redisPort", redisPort);
		request.setAttribute("redisPassword", redisPassword);
		request.setAttribute("redisMaxWait", redisMaxWait);
		mv.addObject("param","zhai好远");
		return mv;
	}
	@RequestMapping("/Test01")
	public ModelAndView TestController(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("test/test01");
		request.setAttribute("param2", "page02");
		mv.addObject("param","zhai好远");
		return mv;
	}
	
	/**
	 * @author zhaieryuan
	 * @see    项目环境测试返回数据库list集合
	 */
	@RequestMapping("/quiryList")
	@ResponseBody
	public JsonResult quiryList(HttpServletRequest request){
		JsonResult result=new JsonResult();
		GarageHistory garageHistory=new GarageHistory();
		List<GarageHistory> garageHistoryList=garahistory.SelectGarageHistoryList(garageHistory);
		result.setSuccess(true);
		result.setModel(garageHistoryList);
		return result;
	}
	
	//http://localhost:8080/parksys/test/inserGarageHisrory.do?storageTime=2015-09-1212:00:31&deliveryTime=2015-09-1212:00:31&parkNumber=90&parkStatus=2&dataSources=3&regisTime=2015-09-1212:00:31
	@RequestMapping("/inserGarageHisrory")
	@ResponseBody
	public JsonResult inserGarageHisrory(GarageHistory param,HttpServletRequest request){
		JsonResult result=new JsonResult();
		garahistory.insertSelective(param);
		result.setSuccess(true);
		result.setModel(param);
		return result;
	}
	/**
	 * 条件查询结果集测试参数类型
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectByParam")
	@ResponseBody
	public JsonResult selectByParam(GarageHistory garageHistory,HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "50") int pageSize){
		JsonResult result=new JsonResult();
		PageHelper.startPage(pageNum, pageSize);
		List<GarageHistory> garageHistoryList=garahistory.SelectGarageHistoryList(garageHistory);
		PageInfo pageinfo = new PageInfo(garageHistoryList);
		result.setSuccess(true);
		result.setModel(pageinfo);
		return result;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
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
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/tabsjsp")
	public ModelAndView tabsjsp(HttpServletRequest request){
		JsonResult result=new JsonResult();
		ModelAndView mv=new ModelAndView("parksys/tab01");
		return mv;
	}
	@RequestMapping("/jqGridTest")
	public ModelAndView jqGridTest(HttpServletRequest request){
		JsonResult result=new JsonResult();
		ModelAndView mv=new ModelAndView("parksys/JqGridTest");
		return mv;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/menuTree")
	public ModelAndView MenuTree(HttpServletRequest request){
		JsonResult result=new JsonResult();
		ModelAndView mv=new ModelAndView("parksys/menuTree");
		return mv;
	}
	/**
	 * jqueryGrid练习
	 */
	@RequestMapping("/Jsondata")
	@ResponseBody
	public JSONObject Jsondata(GarageHistory garageHistory,HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		JsonResult result=new JsonResult();
		List<GarageHistory> garageHistoryList=garageHistoryServiceImpl.SelectGarageHistoryList(garageHistory);
		PageHelper.startPage(pageNum,pageSize);
		PageInfo pageinfo = new PageInfo(garageHistoryList);
		result.setSuccess(true);
		result.setModel(pageinfo);
		JSONObject jsonObject=JSONObject.fromObject(result);
		
		return jsonObject;
	}
	
	/**
	 * 测试车库历史表中最近的一条停车记录
	 */
	@RequestMapping("/queryHsitoryNearly")
	@ResponseBody
	public JsonResult queryHsitoryNearly(GarageHistory garageHistory){
		JsonResult result=new JsonResult();
		GarageHistory queryGarageHistory=garageHistoryServiceImpl.selectNearlyGarageHistory(garageHistory);
		if(queryGarageHistory != null){
			result.setSuccess(true);
			result.setMessage("查询最近一条记录成功！");
			result.setModel(queryGarageHistory);
		}else {
			result.setSuccess(true);
			result.setMessage("查询最近一条记录失败！");
		}
		return result;
	}
	
	@RequestMapping("")
	@ResponseBody
	public JsonResult testAjax(List<GarageHistory> garageHistories){
		JsonResult result=new JsonResult();
		for(GarageHistory garageHistory : garageHistories){
			garageHistory.toString();
		}
		return result;
	}

}
