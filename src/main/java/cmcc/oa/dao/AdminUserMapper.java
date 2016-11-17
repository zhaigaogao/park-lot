package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.AdminUser;
import cmcc.oa.vo.UserInfoVo;

public interface AdminUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
    /**
     * 登录验证
     * @param record
     * @return
     */
    UserInfoVo findVoByPass(AdminUser record);
    
    /**
     * 检验用户名称是否存在
     * @param name
     * @return
     */
    Integer findByName(String name);
    
    /**
     * 检验登录名称是否已存在
     * @param username
     * @return
     */
    Integer findByUsername(String username);
    
    /**
     * 模糊查询所有登录人员
     * @param str
     * @return
     */
   List<UserInfoVo> findByLike(String str);
}