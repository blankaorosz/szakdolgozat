package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/bootstrapPage")
public class BootstrapController {

    @Autowired
    PeriodRepository periodRepository;

    @PreAuthorize("hasRole('ROLE_CEO')")
    @RequestMapping(method = RequestMethod.GET)
    public String getBootstrapPage(ModelMap model) {
        Optional<Period> period = periodRepository.findByActive(true);
        User currentUser = SecurityUtil.getLoggedInUser();

        if(period.isPresent()){
            return "redirect:/";
        }
        model.addAttribute("userName", currentUser.getUserName());
        return "bootstrapPage";
    }
}
