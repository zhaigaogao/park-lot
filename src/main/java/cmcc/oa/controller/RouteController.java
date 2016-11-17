package cmcc.oa.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cmcc.oa.base.BaseController;

/**
 *	静态路由
 * @author renlinggao
 * @Date 2016年10月21日
 */
@Controller
public class RouteController extends BaseController{
	// login
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "/login";
	}
	// index
	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request) {
		return "/park-lot/index";
	}
	// employee
	@RequestMapping("/employee/manager")
	public String employeeManager(HttpServletRequest request) {
		return "/park-lot/employee/manager";
	}
	// car
	@RequestMapping("/car/carManager")
	public String toIndex() {
		return "/park-lot/car/carManager";
	}
	// area
	@RequestMapping("/area/areaManager")
	public String areaManager() {
		return "/park-lot/area/areaManager";
	}
	// reManager
	@RequestMapping("reservation/reManager")
	public String reManager() {
		return "/park-lot/reservation/reManager";
	}
	// money
	@RequestMapping("money/moManager")
	public String moManager() {
		return "/park-lot/money/moManager";
	}
	// parking
	@RequestMapping("parking/manager")
	public String parkingManager() {
		return "/park-lot/parking/manager";
	}
	// accessControl 
	@RequestMapping("access/accessControl")
	public String accessControl() {
		return "/park-lot/access/accessControl";
	}
}