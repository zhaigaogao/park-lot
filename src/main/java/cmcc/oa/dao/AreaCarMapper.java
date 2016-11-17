package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.AreaCar;

public interface AreaCarMapper {

	// 查询所有区域信息
	List<AreaCar> selectAllArea();

	// 查询单条区域信息
	AreaCar selectCarInfoByPrimaryKey(AreaCar record);

	// 新增区域
	int insertArea(AreaCar record);

	// 根据id修改区域内容
	int updateArea(AreaCar record);

	// 根据id删除区域
	int deleteByPrimaryKey(Integer id);
}
