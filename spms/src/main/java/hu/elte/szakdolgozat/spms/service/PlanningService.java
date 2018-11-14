package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.spms.*;
import hu.elte.szakdolgozat.spms.model.ui.PlanningPageViewModel;
import hu.elte.szakdolgozat.spms.model.ui.PlanningTableCellModel;
import hu.elte.szakdolgozat.spms.repository.spms.OrdersRepository;
import hu.elte.szakdolgozat.spms.repository.spms.PlanPerCompanyRepository;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import hu.elte.szakdolgozat.spms.util.PlanPerCompanyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PlanningService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private PlanPerCompanyRepository planPerCompanyRepository;

    public void savePlanningPage(List<PlanningTableCellModel> planningTableCellModelList) {
        Map<Long, PlanPerCompany> planPerCompanyById = new HashMap<>();

        for (PlanningTableCellModel cellData : planningTableCellModelList) {
            PlanPerCompany ppc = planPerCompanyById.computeIfAbsent(cellData.getPlanPerCompanyId(),
                    aLong -> {
                        Optional<PlanPerCompany> p = planPerCompanyRepository.findById(aLong);
                        if (p.isPresent()) return p.get();
                        else return null;
                    });

            PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(
                    cellData.getMonth(),
                    ppc,
                    new BigDecimal(cellData.getContent()));
        }

        planPerCompanyRepository.saveAll(planPerCompanyById.values());
    }

    public PlanningPageViewModel createPlanningPageViewModel(User user, Period period) {
        Plan actualPlanOfUser = extractPlanForUser(period, user);

        PlanningPageViewModel planningPageViewModel = new PlanningPageViewModel();
        planningPageViewModel.setPlanId(actualPlanOfUser.getId());
        planningPageViewModel.setHeaderMonths(generateMonthHeader(period));
        planningPageViewModel.setHeaderYears(generateYearHeader(period));
        planningPageViewModel.setTitle("Planning process - " + period.getYearPlanned());
        planningPageViewModel.setPlanningTableRowList(createPlanningTableRowList(actualPlanOfUser));
        return planningPageViewModel;
    }

    private Plan extractPlanForUser(Period period, User user) {
        for (Plan p : period.getPlans()) {
            if (user.getAgentCode().equals(p.getUser().getAgentCode())) {
                return p;
            }
        }

        throw new IllegalStateException(
                String.format("Plan cannot be found for User[%s] in Period[%s]", user.getUserName(), period.getId())
        );
    }

    private List<String> generateMonthHeader(Period period) {
        Period.MonthName beginMonthName = period.getBegingMonth();
        Period.MonthName[] monthNames = Period.MonthName.values();
        int indexesDone = 0;

        List <String> months = new ArrayList<>(12);

        for(int i = beginMonthName.getIndex(); i< monthNames.length; i++) {
            months.add(monthNames[i].name());
            indexesDone++;
        }

        int indexesLeft = monthNames.length - indexesDone;
        for (int i = 0; i< indexesLeft; i++) {
            months.add(monthNames[i].name());
        }

        return months;
    }

    private List<Integer> generateYearHeader(Period period) {
        int actualPlanningYear = period.getYearPlanned();
        int previousYear = actualPlanningYear-1;
        List<Integer> yearHeaderList = new ArrayList<>(24);

        for(int i = 0; i < 12; i++){
            yearHeaderList.add(previousYear);
            yearHeaderList.add(actualPlanningYear);
        }

        return yearHeaderList;
    }

    private List<List<PlanningTableCellModel>> createPlanningTableRowList(Plan actualPlanOfUser) {
        List<List<PlanningTableCellModel>> planningTableRowList
                = new ArrayList<>(actualPlanOfUser.getPlanPerCompanyList().size());
        for (PlanPerCompany ppc : actualPlanOfUser.getPlanPerCompanyList()) {
            List<PlanningTableCellModel> planningTableCellModelList = new ArrayList<>(25); //two year + company
            planningTableRowList.add(planningTableCellModelList);

            planningTableCellModelList.add(new PlanningTableCellModel(ppc.getCompany().getName(),
                    false,null,null,null,null,null));

            for(Period.MonthName m : Period.MonthName.values()) {
                planningTableCellModelList.add(createPrevYearOrderDataByMonth(
                        actualPlanOfUser.getPeriod().getYearPlanned()-1, m, ppc.getCompany()));

                PlanningTableCellModel cell = new PlanningTableCellModel();
                cell.setEditable(true);
                cell.setContent(
                        PlanPerCompanyUtil.getPlanPriceForMonthByMonthName(m, ppc).toString()
                );
                cell.setCompanyId(ppc.getCompany().getId());
                cell.setMonth(m);
                cell.setPlanId(actualPlanOfUser.getId());
                cell.setPlanPerCompanyId(ppc.getId());

                planningTableCellModelList.add(cell);
            }
        }

        return planningTableRowList;
    }

    private PlanningTableCellModel createPrevYearOrderDataByMonth(int prevYear, Period.MonthName monthName, Company company) {
        PlanningTableCellModel planningTableCellModel = new PlanningTableCellModel();
        planningTableCellModel.setEditable(false);

        List<Order> orders = ordersRepository.findByCompanyAndOrderDateBetween(
                company,
                DateUtil.createDateFromYearAndMonth(prevYear, monthName.getIndex()),
                DateUtil.createDateFromYearAndMonth(prevYear,monthName.getIndex()+1));

        if(orders == null || orders.isEmpty()){
            planningTableCellModel.setContent("Not available");
        }else {
            BigDecimal sumAmount = new BigDecimal(0);
            List<String>  productDetails = new ArrayList<>();
            for(Order o : orders) {
                for (OrderItem oi : o.getOrderItemList()){
                    BigDecimal sumprice = oi.getProductPriceAtOrderTime()
                            .multiply((new BigDecimal(oi.getQuantity())));
                    sumAmount = sumAmount.add(sumprice);
                    productDetails.add(oi.getQuantity() + oi.getProduct().getUnit() + " "
                            + oi.getProduct().getName() + " - " + sumprice + " Ft");
                }

            }
            planningTableCellModel.setContent(sumAmount.toString() + " Ft");
            planningTableCellModel.setDetails(productDetails);
        }
        return  planningTableCellModel;
    }

    private List<List<PlanningTableCellModel>> mockPlanningTableRow() {
        List<List<PlanningTableCellModel>> planningTableRowList = new ArrayList<>(15);
        for(int i = 0; i < 15; i++){
            List<PlanningTableCellModel> planningTableCellModel = new ArrayList<>(25);
            planningTableRowList.add(planningTableCellModel);
            planningTableCellModel.add(new PlanningTableCellModel("Company name" + i,false,null,
                    null,null,null,null));
            for(int j = 0; j < 24; j++){
                PlanningTableCellModel cell = new PlanningTableCellModel();
                if(j % 2 == 0) {
                    cell.setContent("20 000 ft");
                    List<String> detailList = new ArrayList<>();
                    detailList.add("alma" + i + "" + j);
                    detailList.add("harom sarga gurulos fotel almatartoval");
                    cell.setDetails(detailList);
                    cell.setEditable(false);
                }else {
                    cell.setEditable(true);
                    cell.setContent(i*j + "0 ft");
                    cell.setCompanyId(1l);
                    cell.setMonth(Period.MonthName.OCT);
                    cell.setPlanId(1l);
//                    cell.setPlanPerCompanyId(1l);
                }
                planningTableCellModel.add(cell);
            }
        }
        return planningTableRowList;
    }
}
