package com.parksys.maintain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.dao.GarageTableMapper;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.service.GarageTableService;

@Service
public class GarageTableServiceImpl implements GarageTableService{

	@Autowired
	private GarageTableMapper garageTableMapper;
	
	public void insert(GarageTable params) {
		// TODO Auto-generated method stub
		garageTableMapper.insert(params);
	}

	public void insertSelective(GarageTable params) {
		// TODO Auto-generated method stub
		garageTableMapper.insertSelective(params);
	}

	public GarageTable SingleSelect(GarageTable params) {
		// TODO Auto-generated method stub
		GarageTable garageTable = garageTableMapper.selectSingle(params);
		return garageTable;
	}

	public void updateByPrimaryKeySelective(GarageTable params) {
		// TODO Auto-generated method stub
		garageTableMapper.updateByPrimaryKeySelective(params);
	}

	public void updateByPrimaryKey(GarageTable params) {
		// TODO Auto-generated method stub
		garageTableMapper.updateByPrimaryKey(params);
	}

	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		garageTableMapper.deleteByPrimaryKey(id);
	}

	public List<GarageTable> SelectGarageTableList(GarageTable params) {
		// TODO Auto-generated method stub
		List<GarageTable>  garageTables=new ArrayList<GarageTable>();
		garageTables=garageTableMapper.selectGarageTableList(params);
		return garageTables;
	}
	/**
	 * @author zhaieryuan
	 * @method 根据车牌号查询车库表是否有该车牌号的信息
	 * @param  String carNumber
	 */
	public JsonResult GarageTableIscontain(GarageTable garageTable){
		JsonResult result=new JsonResult();
		GarageTable queryGarageTable=SingleSelect(garageTable);
		if(queryGarageTable != null){
			result.setSuccess(true);
			result.setModel(queryGarageTable);
			result.setMessage("车库内存在该车牌号车辆！");
		}else{
			result.setSuccess(false);
			result.setMessage("车库内不存在该车牌号车辆！");
		}
		
		return result;
	}
	
	/**
	 * @method 车库历史表数据转换为车库表信息
	 * @param  GarageTable
	 * @author zhaieryuan
	 */
	public GarageTable GaragetableToGarageHistory(GarageHistory garageHistory){
		GarageTable garageTable = new GarageTable(); 
		garageTable.setStorageTime(garageHistory.getStorageTime());
		garageTable.setDataSources(garageHistory.getDataSources());
		garageTable.setDeliveryTime(garageHistory.getDeliveryTime());
		garageTable.setParkNumber(garageHistory.getParkNumber());
		garageTable.setRegisTime(garageHistory.getRegisTime());
		garageTable.setParkStatus(garageHistory.getParkStatus());
		garageTable.setPlateNumber(garageHistory.getParkStatus());
		return garageTable;
	}
	
}
