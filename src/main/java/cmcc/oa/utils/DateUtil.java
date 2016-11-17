package cmcc.oa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 *
 * @author renlinggao
 * @Date 2016年6月28日
 */
public class DateUtil {
	private static final String FORMART_STYLE = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat format = new SimpleDateFormat(FORMART_STYLE);

	private static Logger logger = Logger.getLogger(DateUtil.class);

	public static String getDateStr(Date date) {
		return format.format(date);
	}

	/**
	 * 字符传转日期
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr) {
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("时间格式异常");
		}
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 *            日期str
	 * @param formatStr
	 *            日期格式
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr, String formatStr) {
		SimpleDateFormat fm = new SimpleDateFormat(formatStr);
		try {
			return fm.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("时间格式异常");
		}
	}
}
