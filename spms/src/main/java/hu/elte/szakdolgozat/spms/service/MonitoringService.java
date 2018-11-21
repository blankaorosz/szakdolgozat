package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.spms.*;
import hu.elte.szakdolgozat.spms.model.ui.MonitoringCellGroup;
import hu.elte.szakdolgozat.spms.model.ui.MonitoringRowModel;
import hu.elte.szakdolgozat.spms.model.ui.MonitoringTableViewModel;
import hu.elte.szakdolgozat.spms.repository.spms.OrdersRepository;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import hu.elte.szakdolgozat.spms.util.PeriodUtil;
import hu.elte.szakdolgozat.spms.util.PlanPerCompanyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    OrdersRepository ordersRepository;

    public MonitoringTableViewModel createMonitoringTableViewModel(User user, Period period) {
        MonitoringTableViewModel monitoringTableViewModel = new MonitoringTableViewModel();
        monitoringTableViewModel.setHeaderMonths(PeriodUtil.generateMonthHeader(period));
        monitoringTableViewModel.setHeaderTitles(generateMonitoringTableHeaderTitles(user)) ;

        List<MonitoringRowModel> monitoringRowModelList = new ArrayList<>();
        if(user.getRole().getName().equals(Role.RoleName.SALES)){
            monitoringTableViewModel.setRows(generateMonitoringTableRows(
                    PeriodUtil.findPlanForSalesUserFromPeriod(period,user), true));
        } else {
            for(Plan p : period.getPlans()) {
                monitoringRowModelList.addAll(generateMonitoringTableRows(
                    PeriodUtil.findPlanForSalesUserFromPeriod(period,p.getUser()),false));
            }
            monitoringTableViewModel.setRows(monitoringRowModelList);
        }


        return monitoringTableViewModel;
    }

    private List<MonitoringRowModel> generateMonitoringTableRows(Plan plan, boolean isWithoutUsername) {
        List<MonitoringRowModel> monitoringTableRows = new ArrayList<>();
        String userName = isWithoutUsername ? "" : plan.getUser().getUserName();
        for(PlanPerCompany ppc : plan.getPlanPerCompanyList()){
            MonitoringRowModel monitoringRowModel = new MonitoringRowModel();
            monitoringRowModel.setPersonName(userName);
            monitoringRowModel.setCompanyName(ppc.getCompany().getName());
            monitoringRowModel.setColumnValues(generateCellValues(ppc, plan.getPeriod().getYearPlanned()));

            monitoringTableRows.add(monitoringRowModel);
        }

        return monitoringTableRows;
    }

    private List<MonitoringCellGroup> generateCellValues(PlanPerCompany ppc, int yearPlanned) {
        List<MonitoringCellGroup> monitoringCellGroupList = new ArrayList<>();
        for(Period.MonthName m : Period.MonthName.values()) {
            BigDecimal plannedAmount = PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(m,ppc);
            MonitoringCellGroup monitoringCellGroup = new MonitoringCellGroup();
            monitoringCellGroupList.add(monitoringCellGroup);
            List<Order> orders = ordersRepository.findByCompanyAndOrderDateBetween(
                    ppc.getCompany(),
                    DateUtil.createDateFromYearAndMonth(yearPlanned, m.getIndex()),
                    DateUtil.createDateFromYearAndMonth(yearPlanned,m.getIndex()+1));
            if(orders == null || orders.isEmpty()){
                monitoringCellGroup.setActualAmount("00.0");
                monitoringCellGroup.setPercentDone("0%");
            } else {
                BigDecimal actualAmount = new BigDecimal(0);
                for(Order o : orders) {
                    for (OrderItem oi : o.getOrderItemList()){
                        BigDecimal sumprice = oi.getProductPriceAtOrderTime()
                            .multiply((new BigDecimal(oi.getQuantity())));
                        actualAmount = actualAmount.add(sumprice);
                    }
                }
                monitoringCellGroup.setActualAmount(actualAmount.toString());
                monitoringCellGroup.setPercentDone(actualAmount.divide(plannedAmount)
                        .multiply(new BigDecimal(100)).toString() + "%");
            }

            monitoringCellGroup.setPlannedAmount(plannedAmount.toString());
            Calendar cal = Calendar.getInstance();
            if(cal.get(Calendar.MONTH) == m.getIndex()) {
                int actualDay = cal.get(Calendar.DAY_OF_MONTH);
                int maxDayNumOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                int percentage = actualDay*100/maxDayNumOfMonth;
                monitoringCellGroup.setPercentOfTimeGone(percentage + "%");
            } else if (cal.get(Calendar.MONTH) < m.getIndex()) {
                monitoringCellGroup.setPercentOfTimeGone("0%");
            } else {
                monitoringCellGroup.setPercentOfTimeGone("100%");
            }

        }

        return monitoringCellGroupList;
    }

    private List<String> generateMonitoringTableHeaderTitles(User user) {
        boolean salesUser = user.getRole().getName().equals(Role.RoleName.SALES);
        int capacity = 49;
        if(salesUser) capacity++;
        List<String> headerTitles = new ArrayList<>(capacity);
        headerTitles.add("Company name");
        if(!salesUser) headerTitles.add("Sales person");
        for(int i = 0; i<12; i++){
            headerTitles.add("Actual result");
            headerTitles.add("Planned result");
            headerTitles.add("Difference (%)");
            headerTitles.add("% spent from month");
        }
        return headerTitles;
    }
}
