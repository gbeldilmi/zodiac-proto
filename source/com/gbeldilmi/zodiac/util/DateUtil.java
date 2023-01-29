package com.gbeldilmi.zodiac.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
  public static String getDateString(String format) {
    Date date = Calendar.getInstance().getTime();  
    DateFormat dateFormat = new SimpleDateFormat(format);  
    return dateFormat.format(date);
  }
}
