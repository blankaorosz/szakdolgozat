package hu.elte.szakdolgozat.spms.util;

import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date JDEDateToUnixDate(String jdeDate) {
        if (StringUtils.isEmpty(jdeDate) || jdeDate.length() < 5 || jdeDate.length() > 6) {
            throw new IllegalArgumentException("Not a valid JDE date: " + jdeDate);
        }

        if (jdeDate.length() == 5) {
            jdeDate = "0" + jdeDate;
        }

        int year = 1900 + (100 * Character.getNumericValue(jdeDate.charAt(0)));
        year += Integer.valueOf(jdeDate.substring(1,3)); //CYYDDD

        int daysFromYearStart = Integer.valueOf(jdeDate.substring(3));

        Calendar c = createInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.DAY_OF_MONTH, daysFromYearStart);

        return c.getTime();
    }

    private static Calendar createInstance() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MONTH, 0);

        return c;
    }

    public static String dateToJDEDate(Date da) {
        Calendar c = Calendar.getInstance();
        c.setTime(da);
        String dayofyear = "" + c.get(Calendar.DAY_OF_YEAR);
        if(dayofyear.length() == 1) dayofyear= 00 + dayofyear;
        if(dayofyear.length() == 2) dayofyear= 0 + dayofyear;
        String jdeDate = "" +(c.get(Calendar.YEAR)-1900) + dayofyear;
        return jdeDate;
    }
}
