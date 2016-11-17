package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.FeeScale;

public interface FeeScaleService {

	/**
	 * 根据条件查询出计费标准表
	 * 
	 * @param record
	 * @return
	 */
	FeeScale findOneByCondition(FeeScale record);

	/**新增一条计费标准
	 * 
	 * @param record
	 * @return
	 *//*
	int addScale(FeeScale record);*/

	/**
	 * 修改一条计费标准
	 * @param id
	 * @return
	 */
	void updateScale(FeeScale record);

	/**
	 * 删除一条计费标准
	 * @param id
	 * @return
	 */
/*	int deleteScale(Integer id);*/

	/**
	 * 查看所有计费标准表
	 * @param record
	 * @return
	 */
	List<FeeScale> findAllByCondition();
	
	/**
	 * 根据车型修改优惠金额（包月、包年）
	 * @param carType
	 */
	void updateBenefit(Integer carType,Integer monthPay,Integer yearPay);
}
