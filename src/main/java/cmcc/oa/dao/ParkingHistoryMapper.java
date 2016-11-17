package cmcc.oa.dao;

import cmcc.oa.entity.ParkingHistory;

public interface ParkingHistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ParkingHistory record);

    int insertSelective(ParkingHistory record);

    ParkingHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ParkingHistory record);

    int updateByPrimaryKey(ParkingHistory record);
}