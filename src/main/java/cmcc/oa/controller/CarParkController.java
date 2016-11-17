package cmcc.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cmcc.oa.base.BaseController;
import cmcc.oa.base.CmdResult;
import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.TempCarInfo;
import cmcc.oa.service.VehicleInAndOutService;

/**
 * 出入口管理
 * 
 * @author renlinggao
 * @Date 2016年10月28日
 */
@Controller
@RequestMapping("carPark")
public class CarParkController extends BaseController {

	@Autowired
	private VehicleInAndOutService vehicleInAndOutService;

	/**
	 * 根据clientId返回最新数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param search
	 * @return
	 */
	@RequestMapping("findLatestCarInfo")
	@ResponseBody
	public JsonResult findLatestCarInfo(@RequestParam(required = true) Integer clientId) {
		JsonResult result = new JsonResult();
		TempCarInfo tempCarInfo = vehicleInAndOutService.findLatestCarInfo(clientId);
		result.setModel(tempCarInfo);
		return result;
	}

	/**
	 * 检查唯一标识是否存在
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("executeCmd")
	@ResponseBody
	public Object executeCmd(@RequestParam(required = true) String para) {
		CmdResult cmd = JSONObject.parseObject(para, new TypeReference<CmdResult>() {
		});
		JsonResult result = new JsonResult();
		Object model = vehicleInAndOutService.executeCmd(cmd);
		result.setModel(model);
		return result;
	}
}
