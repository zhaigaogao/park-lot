package cmcc.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cmcc.oa.dao.ParkClientMapper;
import cmcc.oa.entity.ParkClient;
import cmcc.oa.service.ParkClientService;

/**
 *
 * @author renlinggao
 * @Date 2016年10月28日
 */
@Service
public class ParkClientServiceImpl implements ParkClientService{
	@Autowired
	private ParkClientMapper parkClientMapper;

	@Override
	public void addClient(ParkClient parkClient) {
		parkClient.setCreateTime(new Date());
		parkClient.setStatus(1);
		parkClientMapper.insertSelective(parkClient);
	}

	@Override
	public void editClient(ParkClient parkClient) {
		parkClient.setUpdateTime(new Date());
		parkClientMapper.updateByPrimaryKeySelective(parkClient);
	}

	@Override
	public List<ParkClient> findByLike(String search) {
		return parkClientMapper.findByLike(search);
	}

	@Override
	public ParkClient getByKey(String key) {
		return parkClientMapper.findByKey(key);
	}

}
