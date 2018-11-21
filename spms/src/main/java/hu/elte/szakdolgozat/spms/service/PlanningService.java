package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.spms.*;
import hu.elte.szakdolgozat.spms.model.ui.PlanningPageViewModel;
import hu.elte.szakdolgozat.spms.model.ui.PlanningTableCellModel;
import hu.elte.szakdolgozat.spms.repository.spms.*;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import hu.elte.szakdolgozat.spms.util.PeriodUtil;
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
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    public void changePlanStatus(User user, Long planId, Plan.PlanStatus incomingPlanStatus) {
        Optional<Plan> actualPlan = planRepository.findById(planId);
        if(!actualPlan.isPresent()) {
            throw new IllegalArgumentException("No plan found!");
        }

        switch (actualPlan.get().getStatus()) {
            case AT_SALES:
            case AT_SALES_FOR_EDIT:
                if(!(actualPlan.get().getUser().getId().equals(user.getId())
                    && incomingPlanStatus.equals(Plan.PlanStatus.AT_CONTROLLING))){
                    throw new IllegalArgumentException("Wrong user or incoming status-set request at sales!");
                }
                actualPlan.get().setStatus(incomingPlanStatus);
                break;
            case AT_CONTROLLING:
                if(!(incomingPlanStatus.equals(Plan.PlanStatus.AT_SALES_FOR_EDIT)
                    || incomingPlanStatus.equals(Plan.PlanStatus.ACCEPTED) )){
                    throw new IllegalArgumentException("Wrong incoming status-set request at controlling!");
                }
                actualPlan.get().setStatus(incomingPlanStatus);
                break;
            case ACCEPTED:
                throw new IllegalArgumentException("Accepted plan status can not be changed!");
        }

        planRepository.save(actualPlan.get());
    }

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

    public PlanningPageViewModel createPlanningPageViewModel(User user, Long userIdForPlan, Period period) {
        PlanningPageViewModel planningPageViewModel = new PlanningPageViewModel();
        planningPageViewModel.setHeaderMonths(PeriodUtil.generateMonthHeader(period));
        planningPageViewModel.setHeaderYears(generateYearHeader(period));
        planningPageViewModel.setTitle("Planning process - " + period.getYearPlanned());

        Plan actualPlanOfUser;
        List<User> salesUsers = null;

        if (Role.RoleName.CONTROLLER.equals(user.getRole().getName())) {
            salesUsers = collectSalesUsers(period);

            if (userIdForPlan == null || !containsByUserId(salesUsers, userIdForPlan)) {
                if (salesUsers.isEmpty()) {
                    planningPageViewModel.setPlanningTableRowList(new ArrayList<>());
                    return planningPageViewModel;
                }
                userIdForPlan = salesUsers.get(0).getId();
            }
            User selectedUser = userRepository.findById(userIdForPlan).get();

            planningPageViewModel.setSelectedUser(selectedUser);
            actualPlanOfUser = PeriodUtil.findPlanForSalesUserFromPeriod(period, selectedUser);
        } else {
            actualPlanOfUser = PeriodUtil.findPlanForSalesUserFromPeriod(period, user);
        }

        planningPageViewModel.setPlanId(actualPlanOfUser.getId());
        planningPageViewModel.setComments(commentRepository.findByPlan(actualPlanOfUser));
        planningPageViewModel.setPlanningTableRowList(createPlanningTableRowList(actualPlanOfUser));
        if (salesUsers != null) {
            planningPageViewModel.setSalesUserList(collectSalesUsers(period));
        }
        return planningPageViewModel;
    }

    public Comment addComment(Long planId, User user, String commentText) {
        Comment comment = new Comment();
        comment.setChecked(false);
        comment.setNameFrom(user.getUserName());
        comment.setPlan(planRepository.findById(planId).get());
        comment.setText(commentText);

        return commentRepository.save(comment);
    }

    private boolean containsByUserId(List<User> users, Long userId) {
        for (User u : users) {
            if (u.getId().equals(userId)) {
                return true;
            }
        }

        return false;
    }

    private List<User> collectSalesUsers(Period period) {
        List<User> salesUsers = new ArrayList<>();
        for (Plan p : period.getPlans()) {
           if(p.getStatus().equals(Plan.PlanStatus.AT_CONTROLLING)) {
               salesUsers.add(p.getUser());
           }
        }
        return salesUsers;
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



}
