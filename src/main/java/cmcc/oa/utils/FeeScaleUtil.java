package cmcc.oa.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import cmcc.oa.entity.FeeScale;
import cmcc.oa.service.FeeScaleService;

/**
 * 
 * @author weihang
 * @date 2016年10月31日17:30:45
 *
 */
public class FeeScaleUtil {
	@Autowired
	private FeeScaleService service;

	/**
	 * 计费策略
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param carType
	 *            车型
	 * @param isVip
	 *            是否为贵宾（贵宾为true 则不收取费用）
	 * @return
	 */
	public int calculateCount(Date startTime, Date endTime, int carType, boolean isVip) {
		int count = 0;
		// 是否为贵宾 如果是则直接返回0元
		if (isVip) {
			return count;
		}

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startTime);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endTime);

		// 将时间转换成毫秒
		long start = startCalendar.getTime().getTime();
		long end = endCalendar.getTime().getTime();

		// 获取规则
		FeeScale record = new FeeScale();
		record.setCarType(carType);
		record.setDayNight(0);
		FeeScale dayScale = service.findOneByCondition(record);// 白天
		record.setDayNight(1);
		FeeScale nightScale = service.findOneByCondition(record);// 晚上

		// 直接判断如果小于免费时长的话直接返回0元
		if (end - start <= dayScale.getFreeTime() * (1000 * 60)) {
			return count;
		}

		// 判断是否停车超过一天,如果超过则直接计算按天收费
		if (end - start >= 24 * (1000 * 60)*60) {
			return (int) (50 * ((end - start) / (1000 * 60 * 60 * 24)));
		}

		// 开始时间在8点之后
		if (startCalendar.get(Calendar.HOUR_OF_DAY) >= 8) {
			// 结束时间在晚8点之后
			if (endCalendar.get(Calendar.HOUR_OF_DAY) >= 20) {
				// 分别计算晚上收费规则和白天收费规则进行累加
				Calendar beginCalendar = Calendar.getInstance();
				beginCalendar.setTime(startTime);
				beginCalendar.set(Calendar.HOUR_OF_DAY, 20);
				beginCalendar.set(Calendar.MINUTE, 0);
				int night = (int) ((end - beginCalendar.getTime().getTime()) / (1000 * 60));
				// 晚上规则
				count += (night / nightScale.getChargePeriod()) * nightScale.getFeePeriod();
				// 白天规则
				int day = (int) ((beginCalendar.getTime().getTime() - start) / (1000 * 60));
				int minute = day - dayScale.getFreeTime();
				count += (minute / dayScale.getChargePeriod()) * dayScale.getFeePeriod();
			} else {
				// 白天规则
				int time = (int) ((end - start) / (1000 * 60));
				int minute = time - dayScale.getFreeTime();
				count += (minute / dayScale.getChargePeriod()) * dayScale.getFeePeriod();
			}
		} // 开始时间在8点之前
		else {
			// 结束时间在早8点之后
			if (endCalendar.get(Calendar.HOUR_OF_DAY) >= 8) {
				// 白天规则
				Calendar endCalender = Calendar.getInstance();
				endCalender.setTime(endTime);
				endCalender.set(Calendar.HOUR_OF_DAY, 8);
				endCalender.set(Calendar.MINUTE, 0);
				int day = (int) ((end - endCalender.getTime().getTime()) / (1000 * 60));
				count += (day / dayScale.getChargePeriod()) * dayScale.getFeePeriod();
				// 夜间规则
				endCalender.setTime(startTime);
				endCalender.set(Calendar.HOUR_OF_DAY, 8);
				endCalender.set(Calendar.MINUTE, 0);
				int night = (int) ((endCalender.getTime().getTime() - start) / (1000 * 60));
				int minute = night - nightScale.getFreeTime();
				count += (minute / nightScale.getChargePeriod()) * nightScale.getFeePeriod();
			} else {
				// 正常晚间计费规则
				int time = (int) ((end - start) / (1000 * 60));
				int minute = time - nightScale.getFreeTime();
				count += (minute / nightScale.getChargePeriod()) * nightScale.getFeePeriod();
			}
		}

		return count;
	}
}
