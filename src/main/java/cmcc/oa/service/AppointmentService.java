package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.AppointmentDetail;
import cmcc.oa.vo.AppointmentDetailVo;
import cmcc.oa.vo.ProcessInfoVo;

public interface AppointmentService {

	public List<AppointmentDetailVo> query(AppointmentDetailVo parameters);

	public void insert(ProcessInfoVo record);

	long getAvailableSpace(AppointmentDetailVo appointmentDetailVo);

	public void delete(Long id);

	void update(AppointmentDetail appointmentDetail);

}
