package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import hu.elte.szakdolgozat.spms.repository.spms.PlanRepository;
import hu.elte.szakdolgozat.spms.service.StatisticService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Controller
@RequestMapping("/statisticsPage")
public class StatisticsController {
    @Autowired
    private StatisticService statisticService;
    @Autowired
    PeriodRepository periodRepository;

    @PreAuthorize("hasRole('ROLE_CEO')")
    @RequestMapping(method = RequestMethod.GET)
    public String getPlanningPage(ModelMap model) {
        User currentUser = SecurityUtil.getLoggedInUser();
        Optional<Period> period = periodRepository.findByActive(true);
        if(!period.isPresent() || !period.get().isPlanningEnabled()) {
            return "redirect:/";
        }
        model.addAttribute("statisticPageChartModel",
                statisticService.createStatisticChartModel());
        model.addAttribute("userName", currentUser.getUserName());
        return "statisticsPage";
    }
}
