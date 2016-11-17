package com.parksys.maintain.dao;

import java.util.List;

import com.parksys.maintain.entity.ParkMeter;

public interface ParkMeterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkMeter record);

    int insertSelective(ParkMeter record);

    ParkMeter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkMeter record);

    int updateByPrimaryKey(ParkMeter record);
    
    ParkMeter selectSingle(ParkMeter record);
    
    List<ParkMeter> selectParkMeterList(ParkMeter record);
}