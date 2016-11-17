package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 通过手机号查询
     * @param record
     * @return
     */
    User findByMobile(User record);
    
    /**
     * 查询部门下的人员信息
     * @param record
     * @return
     */
    List<User> findByOrgId(User record);
    
}