package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.AreaCar;

public interface AreaCarService {
	/**
	 * 查询所有区域信息
	 * @return
	 */
	List<AreaCar> selectAllArea();

	/**
	 * 根据id查询区域信息
	 * 
	 * @param 区域 id
	 * @return
	 */
	AreaCar selectCarInfoByPrimaryKey(AreaCar record);

	/**
	 * 新增区域
	 * 
	 * @param 区域  record
	 * @return
	 */
	void insertArea(AreaCar record);

	/**
	 * 根据id修改区域内容
	 * 
	 * @param 区域  record
	 * @return
	 */
	void updateArea(AreaCar record);

	/**
	 * 根据id删除区域
	 * 
	 * @param 区域id
	 * @return
	 */
	void deleteByPrimaryKey(Integer id);
	
	
}
