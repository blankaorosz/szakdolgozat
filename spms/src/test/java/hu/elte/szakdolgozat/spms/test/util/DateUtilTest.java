package hu.elte.szakdolgozat.spms.test.util;

import hu.elte.szakdolgozat.spms.util.DateUtil;
import static org.junit.Assert.*;
import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void testGetCurrentYear() {
        assertEquals(Calendar.getInstance().get(Calendar.YEAR),DateUtil.getCurrentYear());
    }

    @Test
    public void testParseDate() {
        DateUtil.parseDate("2018-12-24");
    }

    @Test(expected = RuntimeException.class)
    public void negativeTestParseDate() {
        DateUtil.parseDate("2018/12/24");
    }

    @Test
    public void testJDEDateToUnixDate() {
        Date christmasDate = DateUtil.parseDate("2018-12-24");
        Date christmasInJDE = DateUtil.JDEDateToUnixDate(118358);
        assertEquals(christmasDate,christmasInJDE);
    }

    @Test
    public void testLeapYearJDEDateToUnixDate() {
        Date birthDay = DateUtil.parseDate("1996-06-12");
        Date birthDayInJDE = DateUtil.JDEDateToUnixDate(96164);
        assertEquals(birthDay,birthDayInJDE);
    }

    @Test
    public void negativeTestLeapYearJDEDateToUnixDate() {
        Date date1 = DateUtil.parseDate("2000-03-01");
        Date date2 = DateUtil.JDEDateToUnixDate(100060);
        assertNotEquals(date1,date2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTestJDEDateToUnixDate() {
        Date date2 = DateUtil.JDEDateToUnixDate(123456789);
    }

    @Test
    public void testDateToJDEDate() {
        Date christmasDate = DateUtil.parseDate("2018-12-24");
        int christmasInJDE = DateUtil.dateToJDEDate(christmasDate);
        assertEquals(118358,christmasInJDE);
    }

    @Test
    public void testLeapYearDateToJDEDate() {
        Date birthDay = DateUtil.parseDate("1996-06-12");
        int birthDayInJDE = DateUtil.dateToJDEDate(birthDay);
        assertEquals(96164,birthDayInJDE);
    }

    @Test
    public void negativeTestLeapYearDateToJDEDate() {
        Date date1 = DateUtil.parseDate("2000-03-01");
        int date2 = DateUtil.dateToJDEDate(date1);
        assertNotEquals(100060,date2);
    }

    @Test
    public void testCreateDateFromYearAndMonth() {
       Date date = DateUtil.parseDate("2000-03-31");
       Date date2 = DateUtil.createDateFromYearAndMonth(2000,3);
       assertEquals(date,date2);
    }
}
