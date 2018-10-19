package hu.elte.szakdolgozat.spms.util;


import java.util.Calendar;

public class DateUtil {

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
