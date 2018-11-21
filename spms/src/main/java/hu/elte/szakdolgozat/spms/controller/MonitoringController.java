package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import hu.elte.szakdolgozat.spms.service.MonitoringService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Controller
@RequestMapping("/monitoringPage")
public class MonitoringController {

    @Autowired
    private PeriodRepository periodRepository;
    @Autowired
    private MonitoringService monitoringService;

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_SALES', 'ROLE_CONTROLLER')")
    @RequestMapping(method = RequestMethod.GET)
    public String getMonitoringPage(ModelMap model) {
        User currentUser = SecurityUtil.getLoggedInUser();
        Optional<Period> period = periodRepository.findByActive(true);

        if (!period.isPresent()) {
            //TODO: handle this case
        }

        model.addAttribute("monitoringPageViewModel",
                monitoringService.createMonitoringTableViewModel(currentUser,period.get()));
        return "monitoringPage";
    }
}
