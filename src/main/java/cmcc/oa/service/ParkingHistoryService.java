package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.ParkingHistory;

public interface ParkingHistoryService {
	
	public List<ParkingHistory> query(ParkingHistory parkingHistory);
}
