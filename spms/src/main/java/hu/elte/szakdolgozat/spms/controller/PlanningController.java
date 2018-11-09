package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.service.PlanningService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/planningPage")
public class PlanningController {
   @Autowired
   private PlanningService planningService;

   @PreAuthorize("hasAnyRole('ROLE_SALES', 'ROLE_CONTROLLER')")
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