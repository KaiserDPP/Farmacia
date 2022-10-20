package com.Pharmacia.crud.other;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Fecha {
	public static final int ACTUAL_YEAR = Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date()));
	public static final int ACTUAL_MONTH = Integer.valueOf(new SimpleDateFormat("MM").format(new Date()));
	public static final int ACTUAL_DAY = Integer.valueOf(new SimpleDateFormat("dd").format(new Date()));
	public static final SimpleDateFormat ACTUAL_DATE = new SimpleDateFormat("dd/MM/yyyy");

	public static Date getTimeStamp(String zone) {
		// System.setProperty("user.timezone", "Asia/Kolkata");
		// TimeZone.setDefault(TimeZone.getTimeZone("Spain"));
		//Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo")));
		Calendar cal = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo")));
		//System.out.println("**************" + );
		Date dateRepresentation = cal.getTime();
		return dateRepresentation;
	}

}
