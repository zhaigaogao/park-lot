package cmcc.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.AdminRoleMapper;
import cmcc.oa.dao.AdminUserMapper;
import cmcc.oa.entity.AdminRole;
import cmcc.oa.entity.AdminUser;
import cmcc.oa.service.AdminUserService;
import cmcc.oa.utils.IdUtil;
import cmcc.oa.vo.UserInfoVo;

/**
 * 
 * @author renlinggao
 * @Date 2016年10月27日
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Override
	public UserInfoVo checkPass(AdminUser user) {
		return adminUserMapper.findVoByPass(user);
	}

	@Override
	public void addAdminUser(AdminUser user, String id) {
		// 初始化登录用户
		String adminUserId = IdUtil.getAdminUserId();
		user.setId(adminUserId);
		user.setCreateTime(new Date());
		user.setStatus(1);
		adminUserMapper.insertSelective(user);
		// 初始化角色
		AdminRole role = new AdminRole();
		role.setAdminUserId(adminUserId);
		role.setRoleId(id);
		adminRoleMapper.insert(role);
	}

	@Override
	public Integer checkUsername(String username) {
		return adminUserMapper.findByUsername(username);
	}

	@Override
	public void editAdminUser(AdminUser user,String id) {
		user.setUpdateTime(new Date());
		adminUserMapper.updateByPrimaryKeySelective(user);
		//如果要修改角色
		if(StringUtils.isNotEmpty(id)){
			AdminRole role = new AdminRole();
			role.setAdminUserId(user.getId());
			role.setRoleId(id);
			adminRoleMapper.updateByAdminUserId(role);
		}
	}

	@Override
	public void editPassword(AdminUser user, String newPass) {
		//验证旧密码
		UserInfoVo userInfo = adminUserMapper.findVoByPass(user);
		if(userInfo == null) throw new RuntimeException("旧密码错误");
		user.setPassword(newPass);
		//修改新密码
		user.setUpdateTime(new Date());
		adminUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<UserInfoVo> findAdminUsers(String str) {
		return adminUserMapper.findByLike(str);
	}
}
