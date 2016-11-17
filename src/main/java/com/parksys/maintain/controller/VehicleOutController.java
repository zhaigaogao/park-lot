package com.parksys.maintain.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;
@Controller
@RequestMapping("VehicleHistory")
public class VehicleOutController extends BaseController{
	
	/**
	 * @see 车辆出库模块
	 * @author zhaieryuan
	 */
	@Autowired
	private GarageTableService garageTableServiceImpl;
	
	@Autowired
	private GarageHistoryService garageHistoryServiceImpl;
	
	/**
	 * @author zhaieryuan
	 * @method 车辆出库车库表信息转移到车库历史表中
	 * @param 
	 */
	@RequestMapping("/VehicleOut")
	@ResponseBody
	public JsonResult VehicleOut(String carNumber){
		SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonResult result=new JsonResult();
		if(carNumber != null && carNumber != ""){
			GarageTable garageTable=new GarageTable();
			garageTable.setPlateNumber(carNumber);
			GarageTable queryGarageTable=garageTableServiceImpl.SingleSelect(garageTable);
			if(queryGarageTable != null){
				GarageHistory garageHistory=new GarageHistory();
				garageHistory.setDataSources(queryGarageTable.getDataSources());
				garageHistory.setDeliveryTime(sfmt.format(new Date()));
				garageHistory.setParkNumber(queryGarageTable.getParkNumber());
				garageHistory.setPlateNumber(queryGarageTable.getPlateNumber());
				garageHistory.setParkStatus(queryGarageTable.getParkStatus());
				garageHistory.setRegisTime(queryGarageTable.getRegisTime());
				garageHistory.setStorageTime(queryGarageTable.getStorageTime());
				garageTableServiceImpl.deleteByPrimaryKey(queryGarageTable.getId());
				garageHistoryServiceImpl.insertSelective(garageHistory);
				result.setSuccess(true);
				result.setMessage("车辆出库成功！");
			}else {
				result.setSuccess(false);
				result.setMessage("车辆入库未登记！");
			}
		}else {
			result.setSuccess(false);
			result.setMessage("车辆出库失败！");
		}
		return result;
	}
	
	/**
	 * @author zhaieryuan
	 * @method 条件查询车库历史表
	 * @param 
	 */
	@RequestMapping("/GarageHistoryList")
	@ResponseBody
	public JsonResult GarageHistoryList(GarageHistory garageHistory,HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize){
		SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonResult result=new JsonResult();
		PageHelper.startPage(pageNum, pageSize);
		List<GarageHistory> garageHistoryList=garageHistoryServiceImpl.SelectGarageHistoryList(garageHistory);
		PageInfo pageinfo = new PageInfo(garageHistoryList);
		result.setSuccess(true);
		result.setModel(pageinfo);
		request.setAttribute("result", result);
		return result;
	}
}
