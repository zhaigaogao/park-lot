package com.parksys.maintain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parksys.maintain.dao.VehicleInformationMapper;
import com.parksys.maintain.entity.VehicleInformation;
import com.parksys.maintain.service.VehicleInformationService;

@Service
public class VehicleInformationServiceImpl implements VehicleInformationService{

	@Autowired
	private VehicleInformationMapper vehicleInformationMapper;
	
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		vehicleInformationMapper.deleteByPrimaryKey(id);
	}

	public void insert(VehicleInformation params) {
		// TODO Auto-generated method stub
		vehicleInformationMapper.insert(params);
	}

	public void insertSelective(VehicleInformation params) {
		// TODO Auto-generated method stub
		vehicleInformationMapper.insertSelective(params);
	}

	public VehicleInformation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		VehicleInformation vehicleInformation=vehicleInformationMapper.selectByPrimaryKey(id);
		return vehicleInformation;
	}

	public void updateByPrimaryKeySelective(VehicleInformation params) {
		// TODO Auto-generated method stub
		vehicleInformationMapper.updateByPrimaryKeySelective(params);
	}

	public void updateByPrimaryKey(VehicleInformation parans) {
		// TODO Auto-generated method stub
		vehicleInformationMapper.updateByPrimaryKey(parans);
	}

	public VehicleInformation selectSingle(VehicleInformation params) {
		// TODO Auto-generated method stub
		VehicleInformation vehicleInformation=vehicleInformationMapper.selectSingle(params);
		return vehicleInformation;
	}

	public List<VehicleInformation> selectVehicleInformationList(VehicleInformation record) {
		// TODO Auto-generated method stub
		List<VehicleInformation> vehicleInformations=vehicleInformationMapper.selectVehicleInformationList(record);
		return vehicleInformations;
	}

}
