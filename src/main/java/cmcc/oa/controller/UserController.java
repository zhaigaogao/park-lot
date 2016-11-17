package cmcc.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cmcc.oa.base.BaseController;
import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.Organization;
import cmcc.oa.entity.User;
import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 添加部门
	 * 
	 * @param orgName
	 * @param previousId
	 * @param showindex
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveDept")
	@ResponseBody
	public JsonResult saveDept(@RequestParam(value = "orgName", required = true) String orgName,
			@RequestParam(value = "previousId", required = true) String previousId,
			@RequestParam(value = "showindex", defaultValue = "100") Integer showindex, HttpServletRequest request) {
		JsonResult result = new JsonResult();
		// 初始化用户填写的数据
		Organization org = new Organization();
		org.setOrgName(orgName);
		org.setPreviousId(previousId);
		org.setShowindex(showindex);
		userService.addOrganzation(org);
		
		result.setModel(org);
		return result;
	}

	/**
	 * 删除部门
	 * 
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/delDept")
	@ResponseBody
	public JsonResult deleteDept(@RequestParam(value = "orgId", required = true) String orgId) {
		JsonResult result = new JsonResult();
		Organization org = new Organization();
		org.setId(orgId);
		org.setStatus("0");
		userService.updateDept(org);
		return result;
	}

	/**
	 * 人员更改部门
	 * 
	 * @param orgId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/updateDeptByUserId")
	@ResponseBody
	public JsonResult updateDeptByUserId(@RequestParam(value = "orgId") String orgId,
			@RequestParam(value = "userId") String userId) {
		JsonResult result = new JsonResult();
		User user = new User();
		user.setId(userId);
		user.setOrgId(orgId);
		userService.editUser(user);
		return result;
	}

	/**
	 * 编辑部门
	 * 
	 * @param orgName
	 * @param showindex
	 * @return
	 */
	@RequestMapping(value = "/updateDept")
	@ResponseBody
	public JsonResult updateDept(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "orgName", required = true) String orgName,
			@RequestParam(value = "showindex", defaultValue = "100") Integer showindex) {
		JsonResult result = new JsonResult();
		Organization org = new Organization();
		org.setId(id);
		org.setOrgName(orgName);
		org.setShowindex(showindex);
		
		userService.updateDept(org);

		return result;
	}

	/**
	 * 删除人员
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public JsonResult deleteUser(@RequestParam(value = "userId") String userId) {
		JsonResult result = new JsonResult();
		User user = new User();
		user.setId(userId);
		user.setStatus("0");
		userService.editUser(user);
		return result;
	}

	/**
	 * 添加一个人员
	 * 
	 * @param userName
	 * @param post
	 * @param mobile
	 * @param workNumber
	 * @param showindex
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/saveUser")
	@ResponseBody
	public JsonResult addUser(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "post", required = false) String post,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "workNumber", required = false) String workNumber,
			@RequestParam(value = "showindex", defaultValue = "100") Integer showindex,
			@RequestParam(value = "orgId", required = true) String orgId) {
		JsonResult result = new JsonResult();
		User user = new User();
		user.setUserName(userName);
		user.setPost(post);
		user.setMobile(mobile);
		user.setWorkNumber(workNumber);
		user.setShowindex(showindex);
		user.setOrgId(orgId);
		userService.addUser(user);
		return result;
	}

	/**
	 * 人员更新
	 * 
	 * @param userId
	 * @param userName
	 * @param post
	 * @param workNumber
	 * @param showindex
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/updateUser")
	@ResponseBody
	public JsonResult updateUser(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "post", required = false) String post,
			@RequestParam(value = "workNumber", required = false) String workNumber,
			@RequestParam(value = "showindex", defaultValue = "100") Integer showindex,
			@RequestParam(value = "orgId", required = true) String orgId) {
		JsonResult result = new JsonResult();
		User user = new User();
		user.setId(userId);
		user.setUserName(userName);
		user.setPost(post);
		user.setWorkNumber(workNumber);
		user.setShowindex(showindex);
		user.setOrgId(orgId);
		userService.editUser(user);
		return result;
	}

	/**
	 * 检查改部门下是否存在这个部门的名称
	 * 
	 * @param orgName
	 * @param previousId
	 * @return
	 */
	@RequestMapping("checkOrgName")
	@ResponseBody
	public JsonResult checkOrgName(@RequestParam(value = "orgName", required = true) String orgName,
			String previousId) {
		JsonResult result = new JsonResult();
		Organization org = new Organization();
		org.setPreviousId(previousId);
		org.setOrgName(orgName);
		org = userService.findByName(org);
		if (org != null) {
			result.setSuccess(false);
			result.setMessage("该部门下已存在同名的部门");
		}
		return result;
	}

	/**
	 * 查询用户的手机号是否存在
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("checkMobile")
	@ResponseBody
	public JsonResult checkUserMobile(@RequestParam(value = "mobile", required = true) String mobile) {
		JsonResult result = new JsonResult();
		User user = new User();
		user.setMobile(mobile);
		user = userService.findUserByMobile(user);
		if (user != null) {
			result.setSuccess(false);
			result.setMessage("该手机号已经存在");
		}
		return result;

	}

	/**
	 * 查询一个部门下的所有的子部门
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("findOrgByPreId")
	@ResponseBody
	public JsonResult findOrgByPreId(String previousId) {
		JsonResult result = new JsonResult();
		Organization org = new Organization();
		org.setPreviousId(previousId);
		List<Organization> orgs = userService.findByOrgId(previousId);
		result.setModel(orgs);
		return result;
	}
	
	/**
	 * 查询一个部门下的所有的子部门
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("findAllOrgs")
	@ResponseBody
	public JsonResult findAllOrgs() {
		JsonResult result = new JsonResult();
		List<Organization> orgs = userService.findAll();
		result.setModel(orgs);
		return result;
	}

	/**
	 * 分页查询部门下的人员信息
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping("findUsers")
	@ResponseBody
	public JsonResult findUsers(@RequestParam(value = "orgId", required = true) String orgId, String search,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		JsonResult result = new JsonResult();
		User user = new User();
		user.setOrgId(orgId);
		user.setUserName(search);
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userService.findUsers(user);
		PageInfo<User> pageInfo = new PageInfo<>(users);
		result.setModel(pageInfo);
		return result;
	}
	
	/**
	 * 查询用户下的所有车辆
	 * @param userId
	 * @return
	 */
	@RequestMapping("findUserVehicleInfos")
	@ResponseBody
	public JsonResult findUserVehicleInfo(@RequestParam(value = "userId", required = true) String userId) {
		JsonResult result = new JsonResult();
		List<VehicleInfo> resultList = userService.findVehicleInfoByUserId(userId);
		result.setModel(resultList);
		return result;
	}

}
