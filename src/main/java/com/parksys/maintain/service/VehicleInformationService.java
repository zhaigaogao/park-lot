package com.parksys.maintain.service;

import java.util.List;

import com.parksys.maintain.entity.VehicleInformation;

public interface VehicleInformationService {
	
	public void deleteByPrimaryKey(Integer id);
	
	public void insert(VehicleInformation params);
	
	public void insertSelective(VehicleInformation params);
	
	public VehicleInformation selectByPrimaryKey(Integer id);
	
	public void updateByPrimaryKeySelective(VehicleInformation params);
	
	public void updateByPrimaryKey(VehicleInformation parans);
	
	public VehicleInformation selectSingle(VehicleInformation params);
	
	public List<VehicleInformation> selectVehicleInformationList(VehicleInformation record);
}
