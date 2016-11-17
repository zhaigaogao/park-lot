package cmcc.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cmcc.oa.base.CmdResult;
import cmcc.oa.dao.TempCarInfoMapper;
import cmcc.oa.entity.TempCarInfo;
import cmcc.oa.service.AppointmentService;
import cmcc.oa.service.VehicleInAndOutService;
import cmcc.oa.service.WhetherToEnterService;

/**
 *
 * @author renlinggao
 * @Date 2016年10月28日
 */
@Service
public class VehicleInAndOutServiceImpl implements VehicleInAndOutService {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	TempCarInfoMapper tempCarInfoMapper;

	@Autowired
	WhetherToEnterService whetherToEnterService;

	@Override
	public Object executeCmd(CmdResult cmd) {
		switch (cmd.getCmdId()) {
		case 1:
			// 车辆等待通过
			if (cmd.isIsIn()) {
				return carWaitToEnter(cmd);
			} else {
				return carWaitToExit(cmd);
			}
		case 2:
			// 车辆通过
			if (cmd.isIsIn()) {
				return carEntered(cmd);
			} else {
				return carExited(cmd);
			}
		default:
			throw new IllegalArgumentException("无效的cmdId：" + cmd.getCmdId());
		}
	}

	/**
	 * 车辆离开
	 * 
	 * @param cmd
	 * @return
	 */
	private Object carExited(CmdResult cmd) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("cmdId", 5);
		result.put("return", 1);
		return result;
	}

	/**
	 * 车辆进入
	 * 
	 * @param cmd
	 * @return
	 */
	private Object carEntered(CmdResult cmd) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("cmdId", 5);
		result.put("return", 1);
		return result;
	}

	/**
	 * 
	 * 车辆等待通过
	 * 
	 * @param cmd
	 * @return
	 */
	private Object carWaitToEnter(CmdResult cmd) {
		TempCarInfo tempCarInfo = whetherToEnterService.wetherToEnter(cmd.getCarNum());
		tempCarInfo.setClientId(cmd.getClientId());

		TempCarInfo parameters = new TempCarInfo();
		parameters.setClientId(cmd.getClientId());
		List<TempCarInfo> list = tempCarInfoMapper.selectByParamters(parameters);
		if (list.isEmpty()) {
			tempCarInfoMapper.insertSelective(tempCarInfo);
		} else {
			tempCarInfo.setId(list.get(0).getId());
			tempCarInfoMapper.updateByPrimaryKey(tempCarInfo);
		}
		JSONObject result = new JSONObject();
		result.put("cmdId", 4);
		result.put("return",0);
		return result;
	}

	/**
	 * 
	 * 车辆等待通过
	 * 
	 * @param cmd
	 * @return
	 */
	private Object carWaitToExit(CmdResult cmd) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		result.put("cmdId", 4);
		result.put("return", 1);
		return result;
	}

	@Override
	public TempCarInfo findLatestCarInfo(Integer clientId) {
		TempCarInfo parameters = new TempCarInfo();
		parameters.setClientId(clientId);
		List<TempCarInfo> list = tempCarInfoMapper.selectByParamters(parameters);
		return list.isEmpty() ? null : list.get(0);
	}

}
