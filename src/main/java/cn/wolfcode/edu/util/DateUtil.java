package cn.wolfcode.edu.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 返回一个不带时分秒的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到某一天的最后一秒钟
	 */
	public static Date getEndDate(Date now) {
		if (now != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.DATE, 1);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0,
					0);
			calendar.add(Calendar.SECOND, -1);
			now = calendar.getTime();
			return now;
		} else {
			return null;
		}
	}

	/**
	 * 两个时间的间隔秒
	 */
	public static int getBetweenTime(Date one, Date other) {
		return (int) (Math.abs(one.getTime() - other.getTime()) / 1000);
	}
}
