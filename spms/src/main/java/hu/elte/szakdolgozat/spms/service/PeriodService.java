package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.spms.*;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import hu.elte.szakdolgozat.spms.repository.spms.PlanRepository;
import hu.elte.szakdolgozat.spms.repository.spms.RoleRepository;
import hu.elte.szakdolgozat.spms.repository.spms.UserRepository;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import hu.elte.szakdolgozat.spms.util.PlanPerCompanyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {

    @Autowired
    private PeriodRepository periodRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Period createPeriod() {
        Optional<Period> activePeriod = periodRepository.findByActive(true);

        if (activePeriod.isPresent()) {
            activePeriod.get().setActive(false);
            periodRepository.save(activePeriod.get());
        }

        int planningYear = DateUtil.getCurrentYear() + 1;
        Period period =
                new Period(true, true, Period.MonthName.JAN, planningYear, null);
        period.setPlans(initiatePlansByPeriod(period));
        return periodRepository.save(period);
    }

    private List<Plan> initiatePlansByPeriod(Period period) {
        Role salesRole = roleRepository.findByName(Role.RoleName.SALES);

        List<User> salesUsers = userRepository.findByRole(salesRole);
        List<Plan> planList = new ArrayList<>();
        for (User su : salesUsers) {
            Plan plan = new Plan(Plan.PlanStatus.AT_SALES, period, su, null, null);
            plan.setPlanPerCompanyList(initiatePlanPerCompaniesByUser(su, plan));
            planList.add(planRepository.save(plan));
        }
        return planList;
    }

    private List<PlanPerCompany> initiatePlanPerCompaniesByUser(User user, Plan plan) {
        List<PlanPerCompany> planPerCompanyListByUser = new ArrayList<>(user.getCompanies().size());
        for (Company c : user.getCompanies()) {
            PlanPerCompany p = new PlanPerCompany();
            p.setCompany(c);
            p.setPlan(plan);
            for (Period.MonthName m : Period.MonthName.values()) {
                PlanPerCompanyUtil.setPlanPriceForMonthByMonthName(m, p, new BigDecimal(0));
            }
            planPerCompanyListByUser.add(p);
        }

        return planPerCompanyListByUser;
    }
}
