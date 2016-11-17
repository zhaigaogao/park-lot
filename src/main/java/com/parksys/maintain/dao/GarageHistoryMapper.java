package com.parksys.maintain.dao;

import java.util.List;

import com.parksys.maintain.entity.GarageHistory;

public interface GarageHistoryMapper {
	
	GarageHistory selectByPrimaryKey(Integer id);
	
	List<GarageHistory> selectGarageHistoryList(GarageHistory record);

    GarageHistory selectSingle(GarageHistory record);
	
    int deleteByPrimaryKey(Integer id);

    int insert(GarageHistory record);

    int insertSelective(GarageHistory record);

    int updateByPrimaryKeySelective(GarageHistory record);

    int updateByPrimaryKey(GarageHistory record);
    
    GarageHistory selectNearlyGarageHistory(GarageHistory record);
    
}