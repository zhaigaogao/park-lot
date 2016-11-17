package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.vo.VehicinfoVo;

public interface VehicleInfoService {
	/**
	 * @method 添加人员车辆信息
	 * @param  人员id:userId ,车辆信息
	 */
	public void addVehInfo(String userId,VehicleInfo vehicleInfo);
	
	/**
	 * @method 根据id删除车辆信息
	 */
	public void deleteVehInfo(String id);
	
	/**
	 * @method 根据车牌号查询车辆信息
	 */
	public VehicleInfo queryByCarNumber(String carNumber);
	/**
	 * @method 根据人员信息UserId，查询与人员相关的车辆信息
	 */
	public List<VehicinfoVo> queryByUserId(VehicinfoVo vehicinfoVo);
	
	/**
	 * 查询车辆信息列表
	 */
	public List<VehicleInfo> queryVehicInfoList(VehicleInfo vehicleInfo);
	
	/**
	 * 根据车牌号信息判断车辆插入的时候是否存在相同车牌号的车辆信息
	 */
	public boolean isExistCarNumber(String carNumber);
	/**
	 * @method 查詢公家車和私家車的并集
	 */
	public List<VehicinfoVo> queryVoOnParam(VehicinfoVo vehicinfoVo);
}
