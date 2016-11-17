package cmcc.oa.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cmcc.oa.base.BaseController;
import cmcc.oa.base.JsonResult;
import cmcc.oa.entity.AdminUser;
import cmcc.oa.service.AdminUserService;
import cmcc.oa.utils.ImagesUtil;
import cmcc.oa.utils.PropertiesUtil;
import cmcc.oa.vo.UserInfoVo;

/**
 *
 * @author renlinggao
 * @Date 2016年10月27日
 */
@Controller
public class AdminUserController extends BaseController {
	@Autowired
	private AdminUserService adminUserService;

	/**
	 * 用户登录验证
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("loginCheck")
	@ResponseBody
	public JsonResult checkLogin(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "code", required = true) String code) {
		JsonResult result = new JsonResult();
		// 判断验证码是否正确
		if (!code.toLowerCase().equals(getLoginImageCode().toLowerCase())) {
			result.setSuccess(false);
			result.setMessage("验证码错误");
			return result;
		}
		// 验证密码
		AdminUser user = new AdminUser();
		user.setUsername(username);
		user.setPassword(password);
		UserInfoVo userInfo = adminUserService.checkPass(user);
		if (userInfo == null) {
			result.setSuccess(false);
			result.setMessage("用户名密码错误");
			return result;
		}
		setUserInfo(userInfo);
		return result;
	}

	/**
	 * 添加一个登录用户
	 * 
	 * @param name
	 * @param username
	 * @param password
	 * @param mobile
	 * @param roleId
	 * @return
	 */
	@RequestMapping("admin/addAdminUser")
	@ResponseBody
	public JsonResult addAdminUser(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password") String password, String mobile, String roleId) {
		JsonResult result = new JsonResult();
		AdminUser user = new AdminUser();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		adminUserService.addAdminUser(user, roleId);
		return result;

	}

	/**
	 * 查询账号是否存在
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("admin/checkUsername")
	@ResponseBody
	public JsonResult checkUserName(String username) {
		JsonResult result = new JsonResult();
		int num = adminUserService.checkUsername(username);
		if (num > 0) {
			result.setSuccess(false);
			result.setMessage("账号已经存在");
		}
		return result;
	}

	/**
	 * 编辑用户
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 * @param mobile
	 * @param roleId
	 * @return
	 */
	@RequestMapping("admin/editUser")
	@ResponseBody
	public JsonResult editAdminUser(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name, String mobile, String roleId) {
		JsonResult result = new JsonResult();
		AdminUser user = new AdminUser();
		user.setName(name);
		user.setMobile(mobile);
		adminUserService.editAdminUser(user, roleId);
		return result;
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 * @param mobile
	 * @param roleId
	 * @return
	 */
	@RequestMapping("admin/editPass")
	@ResponseBody
	public JsonResult editPassword(@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "password", required = true) String password) {
		JsonResult result = new JsonResult();
		String id = getUserInfo().getId();
		AdminUser user = new AdminUser();
		user.setId(id);
		user.setPassword(oldPassword);
		adminUserService.editPassword(user, password);
		return result;
	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 * @param mobile
	 * @param roleId
	 * @return
	 */
	@RequestMapping("admin/resetPass")
	@ResponseBody
	public JsonResult resetPassword(@RequestParam(value = "id", required = true) String id) {
		JsonResult result = new JsonResult();
		// 初始密码
		String password = PropertiesUtil.getAppByKey("RESET_PASSWORD");
		AdminUser user = new AdminUser();
		user.setId(id);
		user.setPassword(password);
		adminUserService.editAdminUser(user, null);
		return result;
	}

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
	@RequestMapping("admin/delUser")
	@ResponseBody
	public JsonResult delUser(@RequestParam(value = "id", required = true) String id) {
		JsonResult result = new JsonResult();
		AdminUser user = new AdminUser();
		user.setId(id);
		user.setStatus(0);
		adminUserService.editAdminUser(user, null);
		return result;
	}

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
	@RequestMapping("admin/findAllUser")
	@ResponseBody
	public JsonResult findAllUser(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String search) {
		JsonResult result = new JsonResult();
		// 开始分页
		PageHelper.startPage(pageNum, pageSize);
		List<UserInfoVo> userInfos = adminUserService.findAdminUsers(search);
		PageInfo<UserInfoVo> pageInfo = new PageInfo<>(userInfos);
		result.setModel(pageInfo);
		return result;
	}

	/**
	 * oa pc登录生成一组验证码
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("imageCheck")
	@ResponseBody
	public void imageCheck(HttpServletResponse response, HttpServletRequest request) {

		// 调用图片工具，生成一个图片以及验证码
		Map<String, BufferedImage> map = ImagesUtil.createImage();
		// 将map里面的验证码取出
		String code = null;
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			code = iter.next();
		}
		// 将验证码放入session，以便后面验证
		setLoginImageCode(code);
		// 将map里的图片取出
		BufferedImage image = map.get(code);
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
