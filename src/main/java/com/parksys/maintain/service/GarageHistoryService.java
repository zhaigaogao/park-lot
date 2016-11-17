package com.parksys.maintain.service;

import java.util.List;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;

public interface GarageHistoryService {
	
	public GarageHistory selectByPrimaryKey(Integer	id);
	
	public List<GarageHistory> SelectGarageHistoryList(GarageHistory params);
	
	public GarageHistory selectSingle(GarageHistory params);
	
	public void deleteByPrimaryKey(Integer id);
	
	public void insert(GarageHistory params);
	
	public void insertSelective(GarageHistory params);
	
	public void updateByPrimaryKeySelective(GarageHistory params);
	
	public void updateByPrimaryKey(GarageHistory params);
	
	public JsonResult GarageHistoryIscontain(GarageHistory garageHistory);
	
	public GarageHistory GaragetableToGarageHistory(GarageTable garageTable);
	
	public GarageHistory selectNearlyGarageHistory(GarageHistory garageHistory);
	
}
