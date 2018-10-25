package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.Period;
import hu.elte.szakdolgozat.spms.model.entity.User;
import hu.elte.szakdolgozat.spms.security.SpmsUserPrincipal;
import hu.elte.szakdolgozat.spms.service.PlanningService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import javax.naming.AuthenticationException;

@Controller
@RequestMapping("/planningPage")
public class PlanningController {
   @Autowired
   private PlanningService planningService;

   @PreAuthorize("hasRole('ROLE_SALES')")
   @RequestMapping(method = RequestMethod.GET)
   public String getPlanningPage(ModelMap model) {
      User currentUser = SecurityUtil.getLoggedInUser();
      Period planningPeriod = mockPeriod();

      model.addAttribute("planningPageViewModel",
              planningService.createPlanningPageViewModel(currentUser, planningPeriod));
      return "planningPage";
   }



   private Period mockPeriod(){

      Period p = new Period();
      p.setYearPlanned(2019);
      p.setPlanningEnabled(true);
      p.setBegingMounth(Period.MonthName.JAN);
      return p;
   }
}