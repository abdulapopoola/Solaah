package com.codekraft.solaah;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utilities {
	/**
	 * Gets current date or time in specified format
	 * */
	public static String getCurrentDateOrTime(String format) {
		Calendar c = Calendar.getInstance();

		SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = df.format(c.getTime());

		return formattedDate;
	}
}
