package cmcc.oa.service;

import cmcc.oa.base.CmdResult;
import cmcc.oa.entity.TempCarInfo;

/**
 *
 * @author renlinggao
 * @Date 2016年10月28日
 */
public interface VehicleInAndOutService {
	Object executeCmd(CmdResult cmd);

	TempCarInfo findLatestCarInfo(Integer clientId);
}
