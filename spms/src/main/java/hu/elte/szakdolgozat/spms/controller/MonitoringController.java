package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.Period;
import hu.elte.szakdolgozat.spms.model.entity.User;
import hu.elte.szakdolgozat.spms.repository.PeriodRepository;
import hu.elte.szakdolgozat.spms.service.PlanningService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/monitoringPage")
public class MonitoringController {

    @PreAuthorize("hasRole('ROLE_SALES')")
    @RequestMapping(method = RequestMethod.GET)
    public String getPlanningPage(ModelMap model) {


        return "monitoringPage";
    }
}
