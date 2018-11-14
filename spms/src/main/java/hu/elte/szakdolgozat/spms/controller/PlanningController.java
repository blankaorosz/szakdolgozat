package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import hu.elte.szakdolgozat.spms.service.PlanningService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/planningPage")
public class PlanningController {
   @Autowired
   private PlanningService planningService;
   @Autowired
   private PeriodRepository periodRepository;

   @PreAuthorize("hasAnyRole('ROLE_SALES', 'ROLE_CONTROLLER')")
   @RequestMapping(method = RequestMethod.GET)
   public String getPlanningPage(ModelMap model) {
      User currentUser = SecurityUtil.getLoggedInUser();
      Optional<Period> planningPeriod = periodRepository.findByActive(true);

      if (!planningPeriod.isPresent()) {
         //TODO: handle this case
      }

      model.addAttribute("planningPageViewModel",
              planningService.createPlanningPageViewModel(currentUser, planningPeriod.get()));
      return "planningPage";
   }
}