package cmcc.oa.vo;

import cmcc.oa.entity.AdminUser;

/**
 *
 * @author renlinggao
 * @Date 2016年10月27日
 */
public class UserInfoVo extends AdminUser {
	private String key;
	private String roleName;
	private Integer roleId;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
