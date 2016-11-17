package com.parksys.maintain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.dao.InventoryTtableMapper;
import com.parksys.maintain.entity.InventoryTtable;
import com.parksys.maintain.service.InventoryTtableService;

@Service
public class InventoryTtableServiceImpl implements InventoryTtableService{

	@Autowired
	private InventoryTtableMapper inventoryTtableMapper;
	
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		inventoryTtableMapper.deleteByPrimaryKey(id);
	}

	public void insert(InventoryTtable params) {
		// TODO Auto-generated method stub
		inventoryTtableMapper.insert(params);
	}

	public void insertSelective(InventoryTtable params) {
		// TODO Auto-generated method stub
		inventoryTtableMapper.insertSelective(params);
	}

	public InventoryTtable selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		InventoryTtable inventoryTtable=inventoryTtableMapper.selectByPrimaryKey(id);
		return inventoryTtable;
	}

	public void updateByPrimaryKeySelective(InventoryTtable params) {
		// TODO Auto-generated method stub
		inventoryTtableMapper.updateByPrimaryKeySelective(params); 
	}

	public void updateByPrimaryKey(InventoryTtable params) {
		// TODO Auto-generated method stub
		inventoryTtableMapper.updateByPrimaryKey(params);
	}

	public InventoryTtable selectSingle(InventoryTtable params) {
		// TODO Auto-generated method stub
		InventoryTtable inventoryTtable=inventoryTtableMapper.selectSingle(params);
		return inventoryTtable;
	}

	public List<InventoryTtable> selectInventoryTtableList(InventoryTtable params) {
		// TODO Auto-generated method stub
		List<InventoryTtable> inventoryTtables=inventoryTtableMapper.selectInventoryTtableList(params);
		return inventoryTtables;
	}
	/**
	 * @author zhaieryuan
	 * @param  JsonResult
	 * @method 判断盘点表中是否有该车辆的盘点记录
	 */
	public JsonResult inventoryTtableIsContain(InventoryTtable inventoryTtable) {
		JsonResult result = new JsonResult();
		InventoryTtable queryInventoryTtable = new InventoryTtable();
		queryInventoryTtable=selectSingle(inventoryTtable);
		if(queryInventoryTtable != null){
			result.setSuccess(true);
			result.setEncoding("盘点表中存在该车牌号信息");
			result.setModel(queryInventoryTtable);
		}else {
			result.setSuccess(false);
			result.setEncoding("盘点表中不存在该车牌号信息");
		}
		return result;
	}
	//查询时间大于storage_time的所有盘点记录
	public List<InventoryTtable> selectListByInventoryTime(InventoryTtable inventoryTtable) {
		// TODO Auto-generated method stub
		List<InventoryTtable> inventoryTtables=inventoryTtableMapper.selectListByInventoryTime(inventoryTtable);
		return inventoryTtables;
	}



}
