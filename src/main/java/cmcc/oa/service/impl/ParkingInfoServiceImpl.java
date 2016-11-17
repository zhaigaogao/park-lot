package cmcc.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.ParkingInfoMapper;
import cmcc.oa.entity.ParkingInfo;
import cmcc.oa.service.AppointmentService;
import cmcc.oa.service.ParkingInfotService;
import cmcc.oa.vo.ParkingStatusVo;

@Service
public class ParkingInfoServiceImpl implements ParkingInfotService {

	@Autowired
	ParkingInfoMapper parkingInfoMapper;
	
	
	@Override
	public ParkingStatusVo getTotalStatus() {
		Map<String, Object> paramters = new HashMap<String, Object>();
		List<ParkingStatusVo> statusList = parkingInfoMapper.queryParkingStatus(paramters);
		return statusList.get(0);
	}

	@Override
	public List<ParkingStatusVo> getStatusByArea(String areaId) {
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("groupByAreaId", "1");
		paramters.put("areaId", areaId);
		return parkingInfoMapper.queryParkingStatus(paramters);
	}

	@Override
	public void batchInsert(List<ParkingInfo> infoList) {
		parkingInfoMapper.batchInsert(infoList);
	}
}
