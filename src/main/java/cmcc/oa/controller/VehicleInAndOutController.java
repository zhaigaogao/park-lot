package cmcc.oa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cmcc.oa.base.CmdResult;

/**
 * 车辆进出逻辑判断
 * 
 * @author renlinggao
 * @Date 2016年10月28日
 */
@Controller
@RequestMapping("vehicle")
public class VehicleInAndOutController {
	

	@RequestMapping("executeCmd")
	@ResponseBody
	public Object executeCmd(Integer cmdId,
			String carNum,Boolean isIn,Integer clientId,String welcomeMess,Long sendTime
			){
		//检查数据完整性
		if(cmdId == null || StringUtils.isEmpty(carNum) || isIn == null || clientId == null || sendTime == null){
			Map<String, Object> resultMap = new HashMap<>();
			return resultMap;
		}
		CmdResult cmdResult = new CmdResult();
		cmdResult.setCmdId(cmdId);
		cmdResult.setCarNum(carNum);
		cmdResult.setIsIn(isIn);
		cmdResult.setClientId(clientId);
		cmdResult.setSendTime(new Date(sendTime));
		cmdResult.setWelcomeMess(welcomeMess);
		return cmdResult;
	}
}
