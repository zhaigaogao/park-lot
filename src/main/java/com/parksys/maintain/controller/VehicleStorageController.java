package com.parksys.maintain.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.parksys.maintain.base.BaseController;
import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.base.TimeUtils;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;

@Controller
@RequestMapping("/VehicleStorage")
public class VehicleStorageController extends BaseController{
	/**
	 * @see 车辆入库模块
	 * @author zhaieryuan
	 */
	
	@Autowired
	private GarageTableService garageTableServiceImpl;
	
	@Autowired
	private GarageHistoryService garageHistoryServiceImpl;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * @author zhaieryuan
	 * @param  GarageTable
	 * @method 判断车库信息中入库时间和出库时间是否登记
	 */
	public JsonResult garageTime(GarageTable garageTable){
		JsonResult result = new JsonResult();
		if(garageTable.getStorageTime().equals("")){
            if(garageTable.getDeliveryTime().equals("")){
				result.setSuccess(false);
				result.setMessage("数据异常！");
			}
		}else{
			if(garageTable.getDeliveryTime().equals("")){
				
			}
		}
		return null;
	}
	
	
	/**
	 * @author zhaieryuan
	 * @method 车辆入库记录车辆信息
	 * @param  carNumber 车牌号   , storageTime  车辆入库时间
	 */
	@RequestMapping("/insertVehicle")
	@ResponseBody
	public JsonResult VehicleStorage(String carNumber,String storageTime){
		SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonResult result=new JsonResult();
		if(carNumber!=null && storageTime!=null){
			GarageTable garageTable =new GarageTable();
			garageTable.setPlateNumber(carNumber);
			//依据车牌号码查询车库表判断是否存在该车牌号数据
			if(garageTableServiceImpl.GarageTableIscontain(garageTable).getSuccess()){
				//依据车牌号码查询车库表判断存在该车牌号数据
				GarageHistory queryGarageHistory=new GarageHistory();
				queryGarageHistory.setPlateNumber(carNumber);
				queryGarageHistory=garageHistoryServiceImpl.selectNearlyGarageHistory(queryGarageHistory);
				if(queryGarageHistory != null){
					queryGarageHistory=garageHistoryServiceImpl.selectSingle(queryGarageHistory);
					garageTable=garageTableServiceImpl.SingleSelect(garageTable);
					result.setSuccess(false);
					result.setMessage("车辆入库失败！");
					garageTable=garageTableServiceImpl.SingleSelect(garageTable);
					//删除车库表中该车辆信息并标识出数据异常
					garageTableServiceImpl.deleteByPrimaryKey(garageTable.getId());
					//把车库表中的数据移入车库历史表中
					garageTable.setDataSources("1:系统出库");
					garageTable.setParkStatus("1:再次出库未登记");
					garageTable.setDeliveryTime(sfmt.format(new Date()));
					queryGarageHistory=garageHistoryServiceImpl.GaragetableToGarageHistory(garageTable);
					garageHistoryServiceImpl.insert(queryGarageHistory);
				}else{
					//如果车库历史表中没有改车牌号记录
					result.setSuccess(false);
					result.setMessage("车辆入库失败！");
					garageTable=garageTableServiceImpl.SingleSelect(garageTable);
					//删除车库表中该车辆信息并标识出数据异常
					garageTableServiceImpl.deleteByPrimaryKey(garageTable.getId());
					//把车库表中的数据移入车库历史表中
					garageTable.setDataSources("1:系统出库");
					garageTable.setParkStatus("1:车辆出库未登记");
					garageTable.setDeliveryTime(sfmt.format(new Date()));
					queryGarageHistory=garageHistoryServiceImpl.GaragetableToGarageHistory(garageTable);
					garageHistoryServiceImpl.insert(queryGarageHistory);
				}
			}else{
				//如果车库表中没有该车牌号记录
				GarageHistory queryGarageHistory=new GarageHistory();
				queryGarageHistory.setPlateNumber(carNumber);
				queryGarageHistory.setStorageTime(storageTime);
				if(garageHistoryServiceImpl.GarageHistoryIscontain(queryGarageHistory).getSuccess()){
					//查询到车库历史表中有该车辆的信息
					queryGarageHistory=garageHistoryServiceImpl.selectSingle(queryGarageHistory);
					garageTable=garageTableServiceImpl.SingleSelect(garageTable);
					//比较车库历史表中出库时间是否大于车库表的入库时间,保证历史表中的车辆出库时间必须小于再入库时间
					if(TimeUtils.compare_date(queryGarageHistory.getDeliveryTime(), sfmt.format(new Date()))){
						//车辆信息正常正常录入系统,车辆的入库时间采用系统时间
						GarageTable insertGarageTable = new GarageTable();
						insertGarageTable.setPlateNumber(carNumber);
						insertGarageTable.setDataSources("0");
						insertGarageTable.setParkStatus("0");
						insertGarageTable.setRegisTime(sfmt.format(new Date()));
						insertGarageTable.setStorageTime(storageTime);
						result.setSuccess(true);
						result.setMessage("车辆入库成功！");
						result.setModel(garageTable);
						garageTableServiceImpl.insert(insertGarageTable);
					}else {
						//车库历史表中车辆的出库时间小于当前车辆的入库时间,数据异常
						queryGarageHistory=garageHistoryServiceImpl.selectSingle(queryGarageHistory);
						queryGarageHistory.setDeliveryTime(sfmt.format(new Date()));
						//更新车辆的出库时间为当前时间
						garageHistoryServiceImpl.updateByPrimaryKey(queryGarageHistory);
					}
				}else{
					//车辆第一次进入车库数据正常录入系统,入库时间采用系统时间
					garageTable.setStorageTime(storageTime);
					garageTable.setPlateNumber(carNumber);
					garageTable.setDataSources("0");
					garageTable.setRegisTime(sfmt.format(new Date()));
					garageTable.setParkStatus("0");
					garageTableServiceImpl.insert(garageTable);
					result.setSuccess(true);
					result.setMessage("车辆入库成功！");
					result.setModel(garageTable);
				}
			}
		}else {
			result.setSuccess(false);
			result.setMessage("车辆入库失败！");
		}
		return result;
	}
	
	/**
	 * @author zhaieryuan
	 * @method 条件查询车库信息
	 * @param 
	 */
	@RequestMapping("/VehicleStorageList")
	@ResponseBody
	public JsonResult VehicleStorageList(GarageTable garageTable,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
		JsonResult result=new JsonResult();
		PageHelper.startPage(pageNum, pageSize);
		List<GarageTable> garageTables=garageTableServiceImpl.SelectGarageTableList(garageTable);
		result.setSuccess(true);
		PageInfo pageinfo = new PageInfo(garageTables);
		result.setModel(pageinfo);
		return result;
	}
	
	
	/**
	 * @author zhaieryuan
	 * @method 查询车辆信息
	 * @param
	 */
	@RequestMapping("/carMsg")
	@ResponseBody
	public JsonResult queryVehicleMsg(){
		JsonResult result=new JsonResult();
		return result;
	}
}
