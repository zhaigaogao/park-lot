package cmcc.oa.dao;

import java.util.List;

import cmcc.oa.entity.PerVehicle;

public interface PerVehicleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PerVehicle record);

    int insertSelective(PerVehicle record);

    PerVehicle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PerVehicle record);

    int updateByPrimaryKey(PerVehicle record);
    
    PerVehicle selectSingle(PerVehicle record);
    
    List<PerVehicle> selectList(PerVehicle record);
    
    void updateByUserId(PerVehicle perVehicle);
}