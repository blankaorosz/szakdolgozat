package hu.elte.szakdolgozat.spms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/statisticsPage")
public class StatisticsController {

    @PreAuthorize("hasRole('ROLE_SALES')")
    @RequestMapping(method = RequestMethod.GET)
    public String getPlanningPage(ModelMap model) {


        return "statisticsPage";
    }
}
