package cmcc.oa.dao;

import java.util.List;
import java.util.Map;

import cmcc.oa.entity.ParkingInfo;
import cmcc.oa.vo.ParkingStatusVo;

public interface ParkingInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ParkingInfo record);

    int batchInsert(List<ParkingInfo> infoList);
    
    int insertSelective(ParkingInfo record);

    ParkingInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ParkingInfo record);

    int updateByPrimaryKey(ParkingInfo record);

	List<ParkingStatusVo> queryParkingStatus(Map<String,Object> paramters);

}