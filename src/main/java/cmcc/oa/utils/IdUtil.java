package cmcc.oa.utils;

import java.util.UUID;

/**
 *
 * @author renlinggao
 * @Date 2016年10月27日
 */
public class IdUtil {
	/**
	 * 生成adminUser的id
	 * 
	 * @return
	 */
	public static String getAdminUserId() {
		return "AU_" + UUID.randomUUID().toString();
	}
	
	/**
	 *生成部门id
	 * @return
	 */
	public static String getOrgId(){
		return "O_" + UUID.randomUUID().toString();
	}
	
	/**
	 * 生成用户id
	 * @return
	 */
	public static String getUserId(){
		return "U_"+ UUID.randomUUID().toString();
	}
}
