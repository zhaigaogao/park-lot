package cmcc.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

import cmcc.oa.base.Constants;
import cmcc.oa.dao.AppointmentDetailMapper;
import cmcc.oa.dao.ProcessInfoMapper;
import cmcc.oa.entity.AppointmentDetail;
import cmcc.oa.entity.AreaCar;
import cmcc.oa.entity.ParkingInfo;
import cmcc.oa.service.AppointmentService;
import cmcc.oa.service.AreaCarService;
import cmcc.oa.service.ParkingInfotService;
import cmcc.oa.vo.AppointmentDetailVo;
import cmcc.oa.vo.ProcessInfoVo;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentDetailMapper appointmentDetailMapper;

	@Autowired
	ProcessInfoMapper processInfoMapper;

	@Autowired
	AreaCarService areaCarService;

	@Autowired
	ParkingInfotService parkingInfotService;

	@Override
	public List<AppointmentDetailVo> query(AppointmentDetailVo parameters) {
		return appointmentDetailMapper.query(parameters);
	}

	@Override
	public void insert(ProcessInfoVo record) {
		List<ParkingInfo> infoList = new ArrayList<>();
		List<AppointmentDetail> details = JSONArray.parseObject(record.getDetails(), new TypeReference<List<AppointmentDetail>>() {
		});

		record.setLinkTableName(Constants.APPOINTMENT_TABLE_NAME);
		record.setCreateTime(new Date());
		processInfoMapper.insertSelective(record);
		for (AppointmentDetail item : details) {
			item.setCarModel(item.getCarModel() != null ? item.getCarModel() : 1);
			item.setStatus(1);
			item.setIsUsed(0);
			item.setOaProcessInfoId(record.getId());
		}
		appointmentDetailMapper.batchInsert(details);

		for (AppointmentDetail item : details) {
			ParkingInfo info = new ParkingInfo();
			info.setId(UUID.randomUUID().toString());
			info.setAppointmentDetailId(item.getId());
			info.setCarNumber(item.getCarNumber());
			info.setCarOwnerType(0);
			info.setAreaId(item.getAreaId());
			infoList.add(info);
		}
		parkingInfotService.batchInsert(infoList);
	}

	@Override
	public long getAvailableSpace(AppointmentDetailVo appointmentDetailVo) {
		AreaCar record = new AreaCar();
		record.setId(appointmentDetailVo.getAreaId());
		AreaCar area = areaCarService.selectCarInfoByPrimaryKey(record);
		Integer outerArea = area.getOuterArea();
		long count = appointmentDetailMapper.count(appointmentDetailVo);

		return outerArea - count;
	}

	@Override
	public void delete(Long id) {
		AppointmentDetail record = new AppointmentDetail();
		record.setId(id);
		record.setStatus(0);
		appointmentDetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void update(AppointmentDetail record) {
		appointmentDetailMapper.updateByPrimaryKeySelective(record);
	}
}
