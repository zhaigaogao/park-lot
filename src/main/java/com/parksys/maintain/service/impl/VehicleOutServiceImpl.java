package com.parksys.maintain.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.service.GarageHistoryService;
import com.parksys.maintain.service.GarageTableService;
import com.parksys.maintain.service.VehicleOutService;

@Service
public class VehicleOutServiceImpl implements VehicleOutService{

	@Autowired
	private GarageTableService garageTableServiceImpl;
	
	@Autowired
	private GarageHistoryService garageHistoryServiceImpl;
	
	public JsonResult VehicleOut(GarageTable garageTable){
		SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JsonResult result=new JsonResult();
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
			result.setSuccess(true);
			result.setMessage("车辆入库未登记！");
		}
		return result;
	}
}
