package cmcc.oa.service;

import java.util.List;

import cmcc.oa.entity.ParkClient;

/**
 *	入口逻辑层
 * @author renlinggao
 * @Date 2016年10月28日
 */
public interface ParkClientService {
	
	/**
	 * 添加
	 * @param parkClient
	 */
	public void addClient(ParkClient parkClient);
	
	/**
	 * 更新编辑
	 * @param parkClient
	 */
	public void editClient(ParkClient parkClient);
	
	/**
	 * 模糊查询
	 * @param search
	 * @return
	 */
	public List<ParkClient> findByLike(String search);
	
	/**
	 * 通过唯一编号查询
	 * @param key
	 * @return
	 */
	public ParkClient getByKey(String key);
	

}
