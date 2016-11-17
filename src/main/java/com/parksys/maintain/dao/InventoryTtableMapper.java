package com.parksys.maintain.dao;

import java.util.List;

import com.parksys.maintain.entity.InventoryTtable;

public interface InventoryTtableMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryTtable record);

    int insertSelective(InventoryTtable record);

    InventoryTtable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryTtable record);

    int updateByPrimaryKey(InventoryTtable record);
    
    InventoryTtable selectSingle(InventoryTtable record);
    
    List<InventoryTtable> selectInventoryTtableList(InventoryTtable record);
    
    List<InventoryTtable> selectListByInventoryTime(InventoryTtable record);
}