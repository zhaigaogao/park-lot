package cmcc.oa.service;

import java.util.Map;

import cmcc.oa.entity.TempCarInfo;

public interface WhetherToEnterService {
	/**
	 * @method 判断车辆是否可以进入车库
	 * @param  1:车牌号    carNumber
	 *         2:车辆进入时间 ，即系统当前时间
	 */
	public TempCarInfo wetherToEnter(String carNumber);
	
}
