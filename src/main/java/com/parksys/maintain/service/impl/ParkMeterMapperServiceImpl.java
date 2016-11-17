package com.parksys.maintain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.dao.ParkMeterMapper;
import com.parksys.maintain.entity.ParkMeter;
import com.parksys.maintain.service.ParkMeterMapperService;

@Service
public class ParkMeterMapperServiceImpl implements ParkMeterMapperService{

	@Autowired
	private ParkMeterMapper parkMeterMapper;
	
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		parkMeterMapper.deleteByPrimaryKey(id);
	}

	public void insert(ParkMeter params) {
		// TODO Auto-generated method stub
		parkMeterMapper.insert(params);
	}

	public void insertSelective(ParkMeter params) {
		// TODO Auto-generated method stub
		parkMeterMapper.insertSelective(params);
	}

	public ParkMeter selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		ParkMeter parkMeter=parkMeterMapper.selectByPrimaryKey(id);
		return parkMeter;
	}

	public void updateByPrimaryKeySelective(ParkMeter params) {
		// TODO Auto-generated method stub
		parkMeterMapper.updateByPrimaryKeySelective(params);
	}

	public void updateByPrimaryKey(ParkMeter params) {
		// TODO Auto-generated method stub
		parkMeterMapper.updateByPrimaryKey(params);
	}

	public ParkMeter selectSingle(ParkMeter params) {
		// TODO Auto-generated method stub
		ParkMeter parkMeter=parkMeterMapper.selectSingle(params);
		return parkMeter;
	}

	public List<ParkMeter> selectParkMeterList(ParkMeter params) {
		// TODO Auto-generated method stub
		List<ParkMeter> parkMeters=parkMeterMapper.selectParkMeterList(params);
		return parkMeters;
	}

}
