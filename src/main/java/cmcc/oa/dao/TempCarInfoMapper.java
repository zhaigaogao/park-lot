package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.TempCarInfo;

public interface TempCarInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempCarInfo record);

    int insertSelective(TempCarInfo record);
    
    TempCarInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempCarInfo record);

    int updateByPrimaryKey(TempCarInfo record);

    List<TempCarInfo> selectByParamters(TempCarInfo paramters);
}