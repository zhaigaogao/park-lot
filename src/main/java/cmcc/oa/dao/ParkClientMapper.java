package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.ParkClient;

public interface ParkClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkClient record);

    int insertSelective(ParkClient record);

    ParkClient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkClient record);

    int updateByPrimaryKey(ParkClient record);
    
    /**
     * 模糊查询
     * @param search
     * @return
     */
    List<ParkClient> findByLike(String search);
    
    /**
     * 通过唯一标识查询
     * @param key
     * @return
     */
    ParkClient findByKey(String key);
}