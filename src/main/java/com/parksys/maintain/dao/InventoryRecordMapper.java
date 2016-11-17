package com.parksys.maintain.dao;

import java.util.List;

import com.parksys.maintain.entity.InventoryRecord;

public interface InventoryRecordMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryRecord record);

    int insertSelective(InventoryRecord record);

    InventoryRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryRecord record);

    int updateByPrimaryKey(InventoryRecord record);
    
    InventoryRecord selectSingle(InventoryRecord record);
    
    List<InventoryRecord> selectInventoryRecordList(InventoryRecord record);
    
    InventoryRecord  selectMaxId();
}