package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.Organization;
import cmcc.oa.entity.User;
import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.vo.VehicinfoVo;

/**
 *
 * @author renlinggao
 * @Date 2016年10月28日
 */
public interface UserService {
	/**
	 * 增加部门
	 * 
	 * @param companyId
	 * @param record
	 * @param dbName
	 * @return
	 */
	void addOrganzation(Organization record);

	/**
	 * 编辑部门
	 * 
	 * @param compangId
	 * @param orgId
	 * @param orgName
	 * @param showindex
	 */
	void updateDept(Organization org);

	/**
	 * 通过上级部门查询下级部门集合
	 * 
	 * @param orgId
	 * @return
	 */
	List<Organization> findByOrgId(String orgId);

	/**
	 * 查询父部们下有没有同名的部门
	 * 
	 * @param orgId
	 * @return
	 */
	Organization findByName(Organization org);
	
	/**
	 * 查询所有部门
	 * 
	 * @return
	 */
	List<Organization> findAll();
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 编辑用户
	 * @param user
	 */
	public void editUser(User user);
	
	/**
	 * 模糊查询用户集合
	 * @param search
	 * @return
	 */
	public List<User> findUsers(User user);
	
	/**
	 * 验证手机号唯一性
	 * @param mobile
	 * @return
	 */
	public User findUserByMobile(User user);
	
	/**
	 * 查询一个用户下的所有车辆
	 * @param vehicleInfo
	 * @return
	 */
	public List<VehicleInfo> findVehicleInfoByUserId(String userId);
}
