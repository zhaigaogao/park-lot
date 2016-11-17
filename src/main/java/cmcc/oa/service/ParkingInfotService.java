package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.ParkingInfo;
import cmcc.oa.vo.ParkingStatusVo;

public interface ParkingInfotService {

	/***
	 * 获取停车场总体概况
	 * 
	 * @return 当前已停车辆数量和预约数量
	 */
	ParkingStatusVo getTotalStatus();

	/***
	 * 获取停车场总体概况,根据区域划分
	 * 
	 * @param 区域id,如不传入查询所有区域
	 * @return 当前已停车辆数量和预约数量
	 */
	List<ParkingStatusVo> getStatusByArea(String areaId);

	void batchInsert(List<ParkingInfo> infoList);
}
