package cmcc.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cmcc.oa.entity.FeeScale;

public interface FeeScaleMapper {

	// 根据条件查询出计费标准表
	FeeScale findOneByCondition(FeeScale record);

	// 新增一条计费标准
	int addScale(FeeScale record);

	// 修改一条计费标准
	void updateScale(FeeScale record);

	// 删除一条计费标准
	int deleteScale(Integer id);
	
	// 查看所有计费标准表
	List<FeeScale> findAllByCondition();
	
	// 根据车型修改优惠金额（包月、包年）
	void updateBenefit(@Param("carType")Integer carType,@Param("monthPay")Integer monthPay,@Param("yearPay")Integer yearPay);
}
