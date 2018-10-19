package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.Period;
import hu.elte.szakdolgozat.spms.repository.PeriodRepository;
import hu.elte.szakdolgozat.spms.repository.PlanPerCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/planningPage")
public class PlanningController {
   @Autowired
   private PeriodRepository periodRepository;

   @RequestMapping(method = RequestMethod.GET)
   public String getPlanningPage(ModelMap model) {

      Period planningPeriod = getPeriod();

      model.addAttribute("period", planningPeriod);
      model.addAttribute("months", monthNames(planningPeriod));
      return "planningPage";
   }

   private List<String> monthNames(Period period) {
      Period.MonthName beginMonthName = getPeriod().getBegingMounth();
      Period.MonthName[] monthNames = Period.MonthName.values();
      int indexesDone = 0;

      List <String> months = new ArrayList<>(12);

      for(int i = beginMonthName.getIndex(); i< monthNames.length; i++) {
         months.add(monthNames[i].name());
         indexesDone++;
      }

      int indexesLeft = monthNames.length - indexesDone;
      for (int i = 0; i< indexesLeft; i++) {
         months.add(monthNames[i].name());
      }

      return months;
   }

   private Period getPeriod(){
      Period p = new Period();
      p.setYearPlanned(2019);
      p.setPlanningEnabled(true);
      p.setBegingMounth(Period.MonthName.JAN);
      return p;
   }
}