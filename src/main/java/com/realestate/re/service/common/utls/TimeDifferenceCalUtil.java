package com.realestate.re.service.common.utls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDifferenceCalUtil {
	private final String startTime;
	private final String endTime;

	private final SimpleDateFormat sdf;
	private String result;
	public TimeDifferenceCalUtil(String startTime, String endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	}

	private long calculateTimeDifference() {
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = sdf.parse(startTime);
			d2 = sdf.parse(endTime);
		} catch (ParseException e) {
			System.out.println("Date parse exception : " + e.getMessage());
		}
		return (d2.getTime() - d1.getTime());
	}

	// for sec = 1000 % 60;
	// for min = (60 * 1000) % 60;

	private long hourDifference() {
		// %24 returns time in 0-23 hrs
		return calculateTimeDifference() / (60 * 60 * 1000) % 24;
	}

	private long dayDifference() {
		return calculateTimeDifference() / (60 * 60 * 1000 * 24);
	}

	/**
	 * Get time difference between two given time
	 * 
	 * @return
	 */
	public String getTimeDifference() {

		// get time in day, hr & compare for result
		long h = hourDifference();
		long d = dayDifference();

		if (d == 0 && h == 0) {
			result = "Less than hour ago";
		}
		if (d == 0 && h > 0) {
			result = getHourReport(h);
		}
		if (d > 0) {
			if (h > 0) {
				result = getDayReport(d) + getHourReport(h);
			} else {
				result = d + " day ago";
			}
		}
		return result;
	}

	private String getHourReport(long h) {
		if (h == 1) {
			return (h + " hr ago");
		} else {
			return (h + " hrs ago");
		}
	}

	private String getDayReport(long d) {
		if (d == 1) {
			return (d + " day ");
		} else {
			return (d + " days ");
		}
	}
}
