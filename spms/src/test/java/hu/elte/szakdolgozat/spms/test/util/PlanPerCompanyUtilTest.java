package hu.elte.szakdolgozat.spms.test.util;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.PlanPerCompany;
import hu.elte.szakdolgozat.spms.util.PlanPerCompanyUtil;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PlanPerCompanyUtilTest {

    @Test
    public void testSetPlanPriceForMonthByMonthNameJan() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.JAN,ppc,price);
        assertEquals(price,ppc.getJanPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameFeb() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.FEB,ppc,price);
        assertEquals(price,ppc.getFebPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameMar() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.MAR,ppc,price);
        assertEquals(price,ppc.getMarPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameApr() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.APR,ppc,price);
        assertEquals(price,ppc.getAprPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameMaj() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.MAY,ppc,price);
        assertEquals(price,ppc.getMayPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameJun() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.JUN,ppc,price);
        assertEquals(price,ppc.getJunPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameJul() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.JUL,ppc,price);
        assertEquals(price,ppc.getJulPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameAug() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.AUG,ppc,price);
        assertEquals(price,ppc.getAugPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameSep() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.SEP,ppc,price);
        assertEquals(price,ppc.getSepPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameOct() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.OCT,ppc,price);
        assertEquals(price,ppc.getOctPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameNov() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.NOV,ppc,price);
        assertEquals(price,ppc.getNovPricePlan());
    }

    @Test
    public void testSetPlanPriceForMonthByMonthNameDec() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(Period.MonthName.DEC,ppc,price);
        assertEquals(price,ppc.getDecPricePlan());
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameJan() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setJanPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.JAN,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameFeb() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setFebPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.FEB,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameMar() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setMarPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.MAR,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameApr() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setAprPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.APR,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameMay() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setMayPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.MAY,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameJun() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setJunPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.JUN,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameJul() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setJulPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.JUL,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameAug() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setAugPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.AUG,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameSep() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setSepPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.SEP,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameOct() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setOctPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.OCT,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameNov() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setNovPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.NOV,ppc));
    }

    @Test
    public void testGetPlanPriceForMonthByMonthNameDec() {
        PlanPerCompany ppc = new PlanPerCompany();
        BigDecimal price = new BigDecimal(22000);
        ppc.setDecPricePlan(price);
        assertEquals(price,PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(Period.MonthName.DEC,ppc));
    }

}
