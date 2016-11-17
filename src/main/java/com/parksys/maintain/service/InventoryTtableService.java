package com.parksys.maintain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parksys.maintain.base.JsonResult;
import com.parksys.maintain.entity.InventoryTtable;

@Service
public interface InventoryTtableService {
	
	public void  deleteByPrimaryKey(Integer id);

	public void insert(InventoryTtable params);

	public void insertSelective(InventoryTtable params);

    public InventoryTtable selectByPrimaryKey(Integer id);

    public void updateByPrimaryKeySelective(InventoryTtable params);

    public void updateByPrimaryKey(InventoryTtable params);
    
    public InventoryTtable selectSingle(InventoryTtable params);
    
    public List<InventoryTtable> selectInventoryTtableList(InventoryTtable params);
    
    public JsonResult inventoryTtableIsContain(InventoryTtable inventoryTtable);
    
    public List<InventoryTtable> selectListByInventoryTime(InventoryTtable inventoryTtable);
}
