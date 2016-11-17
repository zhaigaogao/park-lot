package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.AdminUser;
import cmcc.oa.vo.UserInfoVo;

/**
 *
 * @author renlinggao
 * @Date 2016年10月27日
 */
public interface AdminUserService {
	
	/**
	 * 登录验证
	 * @param user
	 * @return
	 */
	public UserInfoVo checkPass(AdminUser user);
	
	/**
	 * 角色id
	 * @param user
	 * @param id
	 */
	public void addAdminUser(AdminUser user,String id);
	
	/**
	 * 验证登录名是否已存在
	 * @param username
	 * @return
	 */
	public Integer checkUsername(String username);
	
	/**
	 * 修改登录信息
	 * @param user
	 */
	public void editAdminUser(AdminUser user,String id);
	
	/**
	 * 修改密码
	 * @param oldPass
	 * @param newPass
	 */
	public void editPassword(AdminUser user,String newPass);
	
	/**
	 * 查询所有的登录用户
	 * @param str
	 * @return
	 */
	public List<UserInfoVo> findAdminUsers(String str);
	
}
