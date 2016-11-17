package cmcc.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.FeeScaleMapper;
import cmcc.oa.entity.FeeScale;
import cmcc.oa.service.FeeScaleService;

@Service
public class FeeScaleImpl implements FeeScaleService {

	@Autowired
	private FeeScaleMapper feeScaleMapper;
	
	@Override
	public FeeScale findOneByCondition(FeeScale record) {
		
		return feeScaleMapper.findOneByCondition(record);
	}

	/*@Override
	public int addScale(FeeScale record) {
		
		return feeScaleMapper.addScale(record);
	}*/

	@Override
	public void updateScale(FeeScale record) {
		
		feeScaleMapper.updateScale(record);
	}

/*	@Override
	public int deleteScale(Integer id) {
		
		return feeScaleMapper.deleteScale(id);
	}*/

	@Override
	public List<FeeScale> findAllByCondition() {
		
		return feeScaleMapper.findAllByCondition();
	}

	@Override
	public void updateBenefit(Integer carType,Integer monthPay,Integer yearPay) {
		
		feeScaleMapper.updateBenefit(carType,monthPay,yearPay);
	}

}
