package hu.elte.szakdolgozat.spms.util;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Date parseDate(String pattern) {
        try {
            return df.parse(pattern);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date JDEDateToUnixDate(Integer jdeDateInteger) {
        if (jdeDateInteger == null || jdeDateInteger < 10000 || jdeDateInteger > 999999) {
            throw new IllegalArgumentException("Not a valid JDE date: " + jdeDateInteger);
        }

        String jdeDate = String.valueOf(jdeDateInteger);

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
        c.set(Calendar.DAY_OF_MONTH, 0);

        return c;
    }

    public static Integer dateToJDEDate(Date da) {
        Calendar c = Calendar.getInstance();
        c.setTime(da);
        String dayofyear = "" + c.get(Calendar.DAY_OF_YEAR);
        if(dayofyear.length() == 1) dayofyear = "00" + dayofyear;
        if(dayofyear.length() == 2) dayofyear = "0" + dayofyear;
        String jdeDate = "" +(c.get(Calendar.YEAR)-1900) + dayofyear;
        return Integer.valueOf(jdeDate);
    }

    public static Date createDateFromYearAndMonth(int year, int month) {
        Calendar c = createInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        return c.getTime();
    }
}
