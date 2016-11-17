package com.parksys.maintain.dao;

import java.util.List;

import com.parksys.maintain.entity.VehicleInformation;

public interface VehicleInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleInformation record);

    int insertSelective(VehicleInformation record);

    VehicleInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleInformation record);

    int updateByPrimaryKey(VehicleInformation record);
    
    VehicleInformation selectSingle(VehicleInformation record);
    
    List<VehicleInformation> selectVehicleInformationList(VehicleInformation record);
}