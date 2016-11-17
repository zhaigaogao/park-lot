package com.parksys.maintain.service;

import java.util.List;

import com.parksys.maintain.entity.ParkMeter;

public interface ParkMeterMapperService {
	
	public void deleteByPrimaryKey(Integer id);
	
	public void insert(ParkMeter params);
	
	public void insertSelective(ParkMeter params);
	
	public ParkMeter selectByPrimaryKey(Integer id);
	
	public void updateByPrimaryKeySelective(ParkMeter params);
	
	public void updateByPrimaryKey(ParkMeter params);
	
	public ParkMeter selectSingle(ParkMeter params);
	
	public List<ParkMeter> selectParkMeterList(ParkMeter params);
}
