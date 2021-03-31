package com.github.thanhvoquang.mvccore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    //block init instance
    private DateTimeUtil() {
        throw new AssertionError("Static!");
    }

    /**
     * @param date date input
     * @param format type to convert
     * @return string output
     */
    public static String toDateString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
