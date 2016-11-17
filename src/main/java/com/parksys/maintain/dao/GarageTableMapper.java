package com.parksys.maintain.dao;

import java.util.List;

import com.parksys.maintain.entity.GarageTable;

public interface GarageTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GarageTable params);

    int insertSelective(GarageTable params);

    GarageTable selectByPrimaryKey(Integer id);
    
    GarageTable selectSingle(GarageTable params);

    int updateByPrimaryKeySelective(GarageTable params);

    int updateByPrimaryKey(GarageTable params);
    
    List<GarageTable> selectGarageTableList(GarageTable params);
    
}