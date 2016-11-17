package cmcc.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.AppointmentDetailMapper;
import cmcc.oa.dao.VehicleInfoMapper;
import cmcc.oa.entity.TempCarInfo;
import cmcc.oa.service.WhetherToEnterService;
import cmcc.oa.vo.AppointmentDetailVo;
import cmcc.oa.vo.VehicinfoVo;

@Service
public class WetherToEnterServiceImpl implements WhetherToEnterService {

	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	@Autowired
	private AppointmentDetailMapper appointmentDetailMapper;

	/**
	 * @author zhaieryuan
	 * @param carNumber
	 * @return resultMap
	 */
	@Override
	public TempCarInfo wetherToEnter(String carNumber) {
		TempCarInfo carInfo = new TempCarInfo();
		carInfo.setCarNumber(carNumber);
		
		// 查询车辆信息是否是外部车辆
		VehicinfoVo vehicinfoVo = new VehicinfoVo();
		vehicinfoVo.setCarNumber(carNumber);
		vehicinfoVo.setFlag(1);
		vehicinfoVo = vehicleInfoMapper.SelectVehicinfoVoSingle(vehicinfoVo);
		if (vehicinfoVo != null) {
			// 内部车辆
			carInfo.setRetMsg("内部车辆，允许进入");
			carInfo.setCarType(1);
			carInfo.setCarUserName(vehicinfoVo.getUserName());
			carInfo.setCarUserPhone(vehicinfoVo.getMobile());
			//2016/11/2翟二远添加
			carInfo.setCarUserDept(vehicinfoVo.getOrgFullName());
			
			// TODO 显示内部车辆员工所属部门名称
			// carInfo.setCarUserDept(carUserDept);
			return carInfo;
		}

		Date current = new Date();
		// 查询车辆预约是否预约
		AppointmentDetailVo paramters = new AppointmentDetailVo();
		paramters.setIsUsed(0);
		paramters.setCarNumber(carNumber);
		List<AppointmentDetailVo> list = appointmentDetailMapper.query(paramters);
		if (list.isEmpty()) {
			// 未预约外部车辆
			carInfo.setCarType(3);
			carInfo.setRetMsg("查无此车信息");
			return carInfo;
		}

		AppointmentDetailVo record = list.get(0);
		carInfo.setCarType(2);
		carInfo.setVisitUserName(record.getUserName());
		carInfo.setVisitUserPhone(record.getMobile());
		carInfo.setVisitUserComp(record.getUnit());
		carInfo.setRegUserName(record.getCreateUserName());
		carInfo.setRegUserPhone(record.getCreateUserMobile());
		carInfo.setRegUserDept(record.getCreateUserAllOrgName());
		carInfo.setRegInTime(record.getStartTime());
		carInfo.setRegOutTime(record.getEndTime());

		//不在预约时间内
		if (current.getTime() < record.getStartTime().getTime()){
			carInfo.setRetMsg("预约车辆，提前到达");
		}else if(current.getTime() >= record.getEndTime().getTime()){
			carInfo.setRetMsg("预约车辆，晚点到达");
		} else {
			carInfo.setRetMsg("预约车辆，允许进入");
		}

		return carInfo;
	}
}
