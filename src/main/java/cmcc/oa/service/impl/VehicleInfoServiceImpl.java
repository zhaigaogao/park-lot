package cmcc.oa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.PerVehicleMapper;
import cmcc.oa.dao.VehicleInfoMapper;
import cmcc.oa.entity.PerVehicle;
import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.service.VehicleInfoService;
import cmcc.oa.vo.VehicinfoVo;

@Service
public class VehicleInfoServiceImpl implements VehicleInfoService{

	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	@Autowired
	private PerVehicleMapper perVehicleMapper;
	
	@Override
	public void addVehInfo(String userId, VehicleInfo vehicleInfo) {
		// TODO Auto-generated method stub
		//判断车辆信息是否有人员相关联
		if(userId.isEmpty()){
			//车辆信息没有人员信息关联
			vehicleInfoMapper.insertSelective(vehicleInfo);
		}else{
			//车辆信息有人员相关联
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int carId=vehicleInfoMapper.insertSelective(vehicleInfo);
			PerVehicle perVehicle=new PerVehicle();
			perVehicle.setCarId(Long.parseLong(String.valueOf(carId)));
			perVehicle.setCarNumber(vehicleInfo.getCarNumber());
			perVehicle.setFlag(1);
			perVehicle.setUserId(userId);
			try {
				perVehicle.setCreateTime(sdf.parse(sdf.format(new Date())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			perVehicleMapper.insertSelective(perVehicle);
		}
	}

	@Override
	public void deleteVehInfo(String id) {
		// TODO Auto-generated method stub
		VehicleInfo vehicleInfo=new VehicleInfo();
		vehicleInfo.setFlag(0);
		vehicleInfo.setId(Long.parseLong(id));
		//查询出该车辆信息是否在关联表中有和人员信息的关联
		PerVehicle perVehicle=new PerVehicle();
		perVehicle.setCarId(Long.parseLong(id));
		perVehicle=perVehicleMapper.selectSingle(perVehicle);
		if(perVehicle!=null){
			perVehicle.setFlag(0);
			perVehicleMapper.updateByPrimaryKeySelective(perVehicle);
			vehicleInfoMapper.updateByPrimaryKeySelective(vehicleInfo);
		}else {
			vehicleInfoMapper.updateByPrimaryKeySelective(vehicleInfo);
		}
		
	}

	@Override
	public VehicleInfo queryByCarNumber(String carNumber) {
		// TODO Auto-generated method stub
		VehicleInfo vehicleInfo=new VehicleInfo();
		vehicleInfo.setCarNumber(carNumber);
		vehicleInfo=vehicleInfoMapper.selectSingle(vehicleInfo);
		return vehicleInfo;
	}

	/**
	 * 根据车牌号，或者用户ID查询车辆信息
	 */
	@Override
	public List<VehicinfoVo> queryByUserId(VehicinfoVo vehicinfoVo) {
		// TODO Auto-generated method stub
		vehicinfoVo.setFlag(1);
		List<VehicinfoVo> vehicinfoVos=vehicleInfoMapper.SelectVehicinfoVoList(vehicinfoVo);
		return vehicinfoVos;
	}

	@Override
	public List<VehicleInfo> queryVehicInfoList(VehicleInfo vehicleInfo) {
		// TODO Auto-generated method stub
		List<VehicleInfo> vehicleInfos=vehicleInfoMapper.selectList(vehicleInfo);
		return vehicleInfos;
	}

	@Override
	public boolean isExistCarNumber(String carNumber) {
		// TODO Auto-generated method stub
		//判断车牌号是否存在
		List<VehicleInfo> vehicinfos=vehicleInfoMapper.SelectByCarNumber(carNumber);
		if(vehicinfos.isEmpty()){
			return true;
		}	
		return false;
	}

	@Override
	public List<VehicinfoVo> queryVoOnParam(VehicinfoVo vehicinfoVo) {
		// TODO Auto-generated method stub
		List<VehicinfoVo> vehicinfoVos=vehicleInfoMapper.queryVoOnParam(vehicinfoVo);
		return vehicinfoVos;
	}


}
