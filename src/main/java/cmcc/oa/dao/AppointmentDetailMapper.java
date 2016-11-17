package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.AppointmentDetail;
import cmcc.oa.vo.AppointmentDetailVo;

public interface AppointmentDetailMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AppointmentDetail record);

	int insertSelective(AppointmentDetail record);

	AppointmentDetail selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AppointmentDetail record);

	int updateByPrimaryKey(AppointmentDetail record);

	List<AppointmentDetailVo> query(AppointmentDetailVo paramters);

	int batchInsert(List<AppointmentDetail> record);

	AppointmentDetail selectSingle(AppointmentDetail appointmentDetail);

	long count(AppointmentDetail appointmentDetail);
}