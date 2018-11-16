package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.repository.spms.PlanRepository;
import hu.elte.szakdolgozat.spms.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/statisticsPage")
public class StatisticsController {
    @Autowired
    private StatisticService statisticService;

    @PreAuthorize("hasRole('ROLE_CEO')")
    @RequestMapping(method = RequestMethod.GET)
    public String getPlanningPage(ModelMap model) {

        model.addAttribute("statisticPageChartModel",
                statisticService.createStatisticChartModel());

        return "statisticsPage";
    }
}
