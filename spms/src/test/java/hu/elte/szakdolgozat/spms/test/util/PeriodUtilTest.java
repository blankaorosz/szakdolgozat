package hu.elte.szakdolgozat.spms.test.util;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.util.PeriodUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PeriodUtilTest {

    @Test
    public void testGenerateMonthHeaderFromJan() {
        List<String> monthNames = Arrays.asList(
                "JAN", "FEB", "MAR", "APR", "MAY",
                "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
        );

        Period p = new Period();
        p.setBeginMonth(Period.MonthName.JAN);
        List<String> headers = PeriodUtil.generateMonthHeader(p);
        assertEquals(monthNames, headers);

    }

    @Test
    public void testGenerateMonthHeaderFromJune() {
        List<String> monthNames = Arrays.asList(
                "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC",
                "JAN", "FEB", "MAR", "APR", "MAY"
        );

        Period p = new Period();
        p.setBeginMonth(Period.MonthName.JUN);
        List<String> headers = PeriodUtil.generateMonthHeader(p);
        assertEquals(monthNames, headers);

    }

    @Test
    public void negativeTestGenerateMonthHeader() {
        List<String> monthNames = Arrays.asList(
                "JAN", "FEB", "MAR", "APR", "MAY",
                "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
        );

        Period p = new Period();
        p.setBeginMonth(Period.MonthName.FEB);
        List<String> headers = PeriodUtil.generateMonthHeader(p);
        assertNotEquals(monthNames, headers);

    }
}
