package cmcc.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.OrganizationMapper;
import cmcc.oa.dao.PerVehicleMapper;
import cmcc.oa.dao.UserMapper;
import cmcc.oa.dao.VehicleInfoMapper;
import cmcc.oa.entity.Organization;
import cmcc.oa.entity.PerVehicle;
import cmcc.oa.entity.User;
import cmcc.oa.entity.VehicleInfo;
import cmcc.oa.service.UserService;
import cmcc.oa.utils.DateUtil;
import cmcc.oa.utils.IdUtil;

/**
 *
 * @author renlinggao
 * @Date 2016年10月28日
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private OrganizationMapper organizationMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private VehicleInfoMapper vehicleInfoMapper;
	
	@Autowired
	private PerVehicleMapper perVehicleMapper;

	@Override
	public void addOrganzation(Organization org) {
		String preId = org.getPreviousId();
		String orgname = org.getOrgName();
		Organization organization = organizationMapper.findByName(org);
		if (organization != null) {
			throw new RuntimeException("部门名称已存在");
		}
		// 获取部门全称
		String allName = getALlOrgName(preId, orgname);
		// 初始化插入的数据
		org.setId(IdUtil.getOrgId());
		org.setCreatTime(DateUtil.getDateStr(new Date()));
		org.setOrgFullname(allName);
		org.setStatus("1");
		organizationMapper.insertSelective(org);
	}

	/**
	 * 递归查询部门信息
	 * 
	 * @param name
	 * @return
	 */
	private String getALlOrgName(String orgId, String name) {
		// 判断递归的数据部门id是否为空
		if (StringUtils.isNotEmpty(orgId)) {
			Organization org = organizationMapper.selectByPrimaryKey(orgId);
			if (org == null)
				throw new RuntimeException("找不到部门(" + orgId + ")");
			name = org.getOrgName() + "/" + name;
			getALlOrgName(org.getPreviousId(), name);
		}
		return name;
	}

	@Override
	public void updateDept(Organization org) {
		org.setUpdateTime(DateUtil.getDateStr(new Date()));
		organizationMapper.updateByPrimaryKeySelective(org);
	}

	@Override
	public List<Organization> findByOrgId(String orgId) {
		return organizationMapper.findByPreId(orgId);
	}
	
	@Override
	public List<Organization> findAll() {
		return organizationMapper.findAll();
	}

	@Override
	public Organization findByName(Organization org) {
		return organizationMapper.findByName(org);
	}

	@Override
	public void addUser(User user) {
		User u = userMapper.findByMobile(user);
		if (u != null) {
			throw new RuntimeException("改手机号已经存在");
		}
		// 初始化插入的信息
		user.setId(IdUtil.getUserId());
		user.setCreatTime(DateUtil.getDateStr(new Date()));
		user.setStatus("1");
		userMapper.insertSelective(user);

	}

	@Override
	public void editUser(User user) {
		user.setUpdateTime(DateUtil.getDateStr(new Date()));
		userMapper.updateByPrimaryKeySelective(user);
		/**
		 * 2016/11/2翟二远修改 ，删除人员关系的同时删除人员对应的车辆信息
		 */
		editVehicle(user.getId());
	}
	

	@Override
	public List<User> findUsers(User user) {
		return userMapper.findByOrgId(user);
	}

	@Override
	public User findUserByMobile(User user) {
		return userMapper.findByMobile(user);
	}

	@Override
	public List<VehicleInfo> findVehicleInfoByUserId(String userId) {
		return vehicleInfoMapper.findVehicleInfoByUserId(userId);
	}

	/**
	 * 根据用户的id逻辑删除用户对应的车辆信息
	 */
	public void editVehicle(String userId){
		PerVehicle perVehicle=new PerVehicle();
		perVehicle.setUserId(userId);
		List<PerVehicle> perVehicles=perVehicleMapper.selectList(perVehicle);
		if(!perVehicles.isEmpty()){
			perVehicle.setFlag(0);
			perVehicleMapper.updateByUserId(perVehicle);
			VehicleInfo vehicleInfo=new VehicleInfo();
			for(PerVehicle perVeh:perVehicles){
				vehicleInfo.setCarNumber(perVeh.getCarNumber());
				vehicleInfo.setFlag(0);
				vehicleInfoMapper.updateByCarNumber(vehicleInfo);
			}
		}
	}
}
