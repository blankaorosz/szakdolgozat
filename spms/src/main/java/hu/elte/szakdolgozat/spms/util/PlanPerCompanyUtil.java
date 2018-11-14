package hu.elte.szakdolgozat.spms.util;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.PlanPerCompany;

import java.math.BigDecimal;

public class PlanPerCompanyUtil {

    public static PlanPerCompany setPlanPriceForMonthByMonthName(Period.MonthName monthName, PlanPerCompany ppc, BigDecimal price) {

        Class c = PlanPerCompany.class;

        String month = monthName.name().charAt(0) + monthName.name().toLowerCase().substring(1);
        String setterName = "set" + month + "PricePlan";

        try {
            c.getMethod(setterName, BigDecimal.class)
            .invoke(ppc, price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ppc;
    }

    public static BigDecimal getPlanPriceForMonthByMonthName(Period.MonthName monthName, PlanPerCompany ppc) {
        Class c = PlanPerCompany.class;

        String month = monthName.name().charAt(0) + monthName.name().toLowerCase().substring(1);
        String getterName = "get" + month + "PricePlan";

        try {
            return (BigDecimal)
                    c.getMethod(getterName)
                    .invoke(ppc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
