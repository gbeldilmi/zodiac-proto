package com.gbeldilmi.zodiac.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class contains utility methods to manipulate dates.
 * @author gbeldilmi
 */
public class DateUtil {
  /**
   * Get the current date as a string.
   * Note: The format of the date must be a valid format for the java.text.SimpleDateFormat class.
   * @see java.text.SimpleDateFormat
   * @param format The format of the date
   * @return The current date as a string
   */
  public static String getDateString(String format) {
    Date date = Calendar.getInstance().getTime();  
    DateFormat dateFormat = new SimpleDateFormat(format);  
    return dateFormat.format(date);
  }
}
