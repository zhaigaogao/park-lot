package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.Organization;

public interface OrganizationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    /**
     * 通过名字查询
     * @param record
     * @return
     */
    Organization findByName(Organization record);
    
    /**
     * 查询父部门下的子部门
     * @param orgId
     * @return
     */
    List<Organization> findByPreId(String orgId);
    
    /**
     * 查询父部门下的子部门
     * @param orgId
     * @return
     */
    List<Organization> findAll();
}