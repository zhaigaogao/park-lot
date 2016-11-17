package com.parksys.maintain.controller.leaveflow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parksys.maintain.base.BaseController;

@Controller
@RequestMapping("leaveFlow")
public class LeaveFlowController extends BaseController{
	/**
	 * 
	 * @author zhaieryuan
	 * @method 请假流程控制层
	 * 
	 */
	@RequestMapping("FlowChart")
	public String leaveFlowChart(){
		
		return "parksys/leaveFlow/FlowChart";
	}
}
