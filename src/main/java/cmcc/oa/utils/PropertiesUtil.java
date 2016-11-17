package cmcc.oa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author renlinggao
 * @Date 2016年5月9日
 */

public class PropertiesUtil {

	/**
	 * 从properties文件读取配置
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String readByKey(final String filePath, final String key) {
		String value = null;
		Properties prop = new Properties();
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
		try {
			prop.load(in);
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 读指定路径下properties文件内数据并保存到map中
	 * 
	 * @param filePath
	 *            properties文件路径
	 * @return 封装键值对的map
	 */
	public static Map<String, String> readProperties(final String filePath) {
		Map<String, String> kvDatas = null;
		Properties prop = new Properties();
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
		try {
			prop.load(in);
			kvDatas = new HashMap<String, String>();
			Set<Object> keys = prop.keySet();
			Iterator<Object> iter = keys.iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				if (null != key) {
					String keyStr = String.valueOf(key);
					kvDatas.put(keyStr, prop.getProperty(keyStr));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return kvDatas;
	}

	public static String getAppByKey(String key) {
		Map<String, String> prop = PropertiesUtil.readProperties("app.properties");
		return prop.get(key);
	}

}
