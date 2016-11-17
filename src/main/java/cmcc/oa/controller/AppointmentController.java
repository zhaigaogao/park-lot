package cmcc.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cmcc.oa.base.BaseController;
import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.AppointmentDetail;
import cmcc.oa.service.AppointmentService;
import cmcc.oa.vo.AppointmentDetailVo;
import cmcc.oa.vo.ProcessInfoVo;

/**
 * 
 * 预约管理
 */

@RequestMapping("appointment")
@Controller
public class AppointmentController extends BaseController {

	@Autowired
	AppointmentService appointmentService;

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 * @param mobile
	 * @param roleId
	 * @return
	 */
	@RequestMapping("query")
	@ResponseBody
	public JsonResult query(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String carNumber,Integer carModel,String createUserName,String userName) {
		JsonResult result = new JsonResult();
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		AppointmentDetailVo parameters = new AppointmentDetailVo();
		parameters.setCarNumber(carNumber);
		parameters.setCarModel(carModel);
		parameters.setUserName(userName);
		parameters.setCreateUserName(createUserName);
		List<AppointmentDetailVo> list = appointmentService.query(parameters);
		PageInfo<AppointmentDetailVo> pageInfo = new PageInfo<>(list);
		result.setModel(pageInfo);
		return result;
	}

	/**
	 * @method 流程添加
	 * @param ProcessInfo
	 * @return id
	 */
	@RequestMapping("insertFromProcess")
	@ResponseBody
	public JsonResult insertFromProcess(ProcessInfoVo processInfo) {
		JsonResult result = new JsonResult();
		appointmentService.insert(processInfo);
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public JsonResult update(AppointmentDetail params) {
		JsonResult result = new JsonResult();
		AppointmentDetail record = new AppointmentDetail();
		record.setId(params.getId());
		record.setCarModel(params.getCarModel());
		record.setCarNumber(params.getCarNumber());
		record.setUserName(params.getUserName());
		record.setMobile(params.getMobile());
		record.setStartTime(params.getStartTime());
		record.setEndTime(params.getEndTime());
		
		appointmentService.update(record);
		return result;
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public JsonResult delete(Long id) {
		JsonResult result = new JsonResult();
		appointmentService.delete(id);
		return result;
	}
}
