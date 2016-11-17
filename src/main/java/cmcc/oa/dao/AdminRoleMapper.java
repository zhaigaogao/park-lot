package cmcc.oa.dao;

import cmcc.oa.entity.AdminRole;

public interface AdminRoleMapper {
    int insert(AdminRole record);

    int insertSelective(AdminRole record);
    
   /**
    * 根据登录用户id更新数据
    * @param record
    * @return
    */
    int updateByAdminUserId(AdminRole record);
}