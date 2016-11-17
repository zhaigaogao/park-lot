package com.parksys.maintain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.dao.InventoryRecordMapper;
import com.parksys.maintain.entity.InventoryRecord;
import com.parksys.maintain.service.InventoryRecordService;

@Service
public class InventoryRecordServiceImpl implements InventoryRecordService {

	@Autowired
	private InventoryRecordMapper inventoryRecordMapper;
	
	public InventoryRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		InventoryRecord inventoryRecord=inventoryRecordMapper.selectByPrimaryKey(id);
		return inventoryRecord;
	}

	public InventoryRecord selectSingle(InventoryRecord params) {
		// TODO Auto-generated method stub
		InventoryRecord inventoryRecord=inventoryRecordMapper.selectSingle(params);
		return inventoryRecord;
	}

	public List<InventoryRecord> selectInventoryRecordList(InventoryRecord params) {
		// TODO Auto-generated method stub
		List<InventoryRecord> inventoryRecords=inventoryRecordMapper.selectInventoryRecordList(params);
		return inventoryRecords;
	}

	public void insert(InventoryRecord params) {
		// TODO Auto-generated method stub
		
	}

	public void insertSelective(InventoryRecord params) {
		// TODO Auto-generated method stub
		inventoryRecordMapper.insertSelective(params);
	}

	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		inventoryRecordMapper.deleteByPrimaryKey(id);
	}

	public void updateByPrimaryKeySelective(InventoryRecord params) {
		// TODO Auto-generated method stub
		inventoryRecordMapper.updateByPrimaryKeySelective(params);
	}

	public void updateByPrimaryKey(InventoryRecord params) {
		// TODO Auto-generated method stub
		inventoryRecordMapper.updateByPrimaryKey(params);
	}

	public InventoryRecord selectMaxId() {
		// TODO Auto-generated method stub
		InventoryRecord inventoryRecord = inventoryRecordMapper.selectMaxId();
		return inventoryRecord;
	}
	
	
}
