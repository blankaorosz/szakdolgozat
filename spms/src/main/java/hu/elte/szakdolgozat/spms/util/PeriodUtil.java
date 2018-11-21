package hu.elte.szakdolgozat.spms.util;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;

import java.util.ArrayList;
import java.util.List;

public class PeriodUtil {

    public static List<String> generateMonthHeader(Period period) {
        Period.MonthName beginMonthName = period.getBeginMonth();
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

    public static Plan findPlanForSalesUserFromPeriod(Period period, User user) {
        for (Plan p : period.getPlans()) {
            if (user.getAgentCode().equals(p.getUser().getAgentCode())) {
                return p;
            }
        }

        throw new IllegalStateException(
                String.format("Plan cannot be found for User[%s] in Period[%s]", user.getUserName(), period.getId())
        );
    }
}
