package cmcc.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.vo.VehicinfoVo;

public interface VehicleInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(VehicleInfo record);

	int insertSelective(VehicleInfo record);

	VehicleInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(VehicleInfo record);

	int updateByPrimaryKey(VehicleInfo record);

	VehicleInfo selectSingle(VehicleInfo vehicleInfo);

	List<VehicleInfo> selectList(VehicleInfo vehicleInfo);
	
	List<VehicinfoVo> SelectVehicinfoVoList(VehicinfoVo vehicinfoVo);
	
	List<VehicleInfo> SelectByCarNumber(@Param(value="carNumber")String carNumber); 
	
	VehicinfoVo   SelectVehicinfoVoSingle(VehicinfoVo vehicinfoVo);
	
	/**
	 * 查询用户名下的所有車輛
	 * @param vehicinfoVo
	 * @return
	 */
	List<VehicleInfo> findVehicleInfoByUserId(String userId);
	/**
	 * 根据车牌号修改车辆信息
	 */
	void  updateByCarNumber(VehicleInfo vehicleInfo);
	
	List<VehicinfoVo> queryVoOnParam(VehicinfoVo vehicinfoVo);
}