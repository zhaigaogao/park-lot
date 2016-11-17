package com.parksys.maintain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.dao.GarageHistoryMapper;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;
import com.parksys.maintain.service.GarageHistoryService;

import sun.util.logging.resources.logging;

@Service
public class GarageHistoryServiceImpl implements GarageHistoryService {
	
	@Autowired
	private GarageHistoryMapper garageHistoryMapper;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	public List<GarageHistory> SelectGarageHistoryList(GarageHistory params) {
		// TODO Auto-generated method stub
		List<GarageHistory> GarageHistoryList=new ArrayList<GarageHistory>();
		GarageHistory garageHistory=new GarageHistory();
		GarageHistoryList=garageHistoryMapper.selectGarageHistoryList(garageHistory);
		return GarageHistoryList;
	}

	public GarageHistory selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		GarageHistory garageHistory=garageHistoryMapper.selectByPrimaryKey(id);
		return garageHistory;
	}

	public GarageHistory selectSingle(GarageHistory params) {
		// TODO Auto-generated method stub
		GarageHistory garageHistory=garageHistoryMapper.selectSingle(params);
		return garageHistory;
	}

	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		garageHistoryMapper.deleteByPrimaryKey(id);
	}

	public void insert(GarageHistory params) {
		// TODO Auto-generated method stub
		garageHistoryMapper.insert(params);
	}

	public void updateByPrimaryKeySelective(GarageHistory params) {
		// TODO Auto-generated method stub
		garageHistoryMapper.updateByPrimaryKeySelective(params);
	}

	public void updateByPrimaryKey(GarageHistory params) {
		// TODO Auto-generated method stub
		garageHistoryMapper.updateByPrimaryKey(params);
	}

	public void insertSelective(GarageHistory params) {
		// TODO Auto-generated method stub
		garageHistoryMapper.insertSelective(params);
	}
	/**
	 * @author zhaieryuan
	 * @Method 根据车牌号查询车库历史表中是否有该车辆信息记录
	 * @param  String carNumber
	 */
	public JsonResult GarageHistoryIscontain(GarageHistory garageHistory){
		JsonResult result=new JsonResult();
		GarageHistory queryGarageHistory =new GarageHistory();
		try {
			 queryGarageHistory=selectSingle(garageHistory);
			 if(queryGarageHistory != null){
					result.setSuccess(true);
					result.setModel(queryGarageHistory);
					result.setMessage("车库历史记录内存在该车牌号车辆！");
				}else{
					result.setSuccess(false);
					result.setMessage("车库历史记录内不存在该车牌号车辆！");
				}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询{GarageHistory}失败，数据异常");
		}finally {
			result.setSuccess(false);
			result.setMessage("入库失败,请核对入库信息");
		}
		return result;
	}
	/**
	 * @method 车库表数据转换为车库历史表信息
	 * @param  GarageTable
	 * @author zhaieryuan
	 */
	public GarageHistory GaragetableToGarageHistory(GarageTable garageTable){
		GarageHistory garageHistory=new GarageHistory();
		garageHistory.setStorageTime(garageTable.getStorageTime());
		garageHistory.setDataSources(garageTable.getDataSources());
		garageHistory.setDeliveryTime(garageTable.getDeliveryTime());
		garageHistory.setParkNumber(garageTable.getParkNumber());
		garageHistory.setRegisTime(garageTable.getRegisTime());
		garageHistory.setParkStatus(garageTable.getParkStatus());
		garageHistory.setPlateNumber(garageTable.getPlateNumber());
		return garageHistory;
	}

	public GarageHistory selectNearlyGarageHistory(GarageHistory garageHistory) {
		// TODO Auto-generated method stub
		GarageHistory queryGarageHistory=garageHistoryMapper.selectNearlyGarageHistory(garageHistory);
		return queryGarageHistory;
	}

}
