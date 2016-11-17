package com.parksys.maintain.service;

import java.util.List;

import com.parksys.maintain.entity.InventoryRecord;

public interface InventoryRecordService {
	
	public InventoryRecord selectByPrimaryKey(Integer id);
	
	public InventoryRecord selectSingle(InventoryRecord params);
	
	public List<InventoryRecord> selectInventoryRecordList(InventoryRecord params);
	
	public void insert(InventoryRecord params);
	
	public void insertSelective(InventoryRecord params);
	
	public void deleteByPrimaryKey(Integer id);
	
	public void updateByPrimaryKeySelective(InventoryRecord params);
	
	public void updateByPrimaryKey(InventoryRecord params);
	
	public InventoryRecord selectMaxId();
}
