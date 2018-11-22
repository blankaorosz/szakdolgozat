package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/*")
public class DefaultController {

    @Autowired
    private PeriodRepository periodRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String routeByRole(ModelMap model) {
        User currentUser = SecurityUtil.getLoggedInUser();
        switch (currentUser.getRole().getName()) {
            case SALES:
            case CONTROLLER:
                model.addAttribute("userName", currentUser.getUserName());
                return "redirect:planningPage";
            case CEO:
                Optional<Period> activePeriod= periodRepository.findByActive(true);
                model.addAttribute("userName", currentUser.getUserName());
                if(activePeriod.isPresent()) {
                    if(activePeriod.get().isPlanningEnabled()) {
                        return "redirect:statisticsPage";
                    } else {
                        return "redirect:monitoringPage";
                    }
                }
                return "redirect:bootstrapPage";
            case ADMIN:
                model.addAttribute("userName", currentUser.getUserName());
                return "redirect:adminPage";
        }
        throw new IllegalArgumentException("Unknown role");
    }
}
