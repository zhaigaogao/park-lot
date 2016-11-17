package com.parksys.maintain.service;

import java.util.List;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.GarageHistory;
import com.parksys.maintain.entity.GarageTable;


public interface GarageTableService {
	
    public void insert(GarageTable params);
	
	public void insertSelective(GarageTable params);
	
	public GarageTable SingleSelect(GarageTable params);
	
	public void updateByPrimaryKeySelective(GarageTable params);
	
	public void updateByPrimaryKey(GarageTable params);
	
	public void deleteByPrimaryKey(Integer id);
	
	public List<GarageTable> SelectGarageTableList(GarageTable params);
	
	public JsonResult GarageTableIscontain(GarageTable garageTable);
	
	public GarageTable GaragetableToGarageHistory(GarageHistory garageHistory);
}
