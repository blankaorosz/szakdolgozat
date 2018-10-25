package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.Period;
import hu.elte.szakdolgozat.spms.model.entity.User;
import hu.elte.szakdolgozat.spms.model.ui.PlanningPageViewModel;
import hu.elte.szakdolgozat.spms.model.ui.PlanningTableCellModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanningService {

    public PlanningPageViewModel createPlanningPageViewModel(User user, Period period) {
        PlanningPageViewModel planningPageViewModel = new PlanningPageViewModel();
        planningPageViewModel.setHeaderMonths(generateMonthHeader(period));
        planningPageViewModel.setHeaderYears(generateYearHeader(period));
        planningPageViewModel.setTitle("Planning process - " + period.getYearPlanned());
        planningPageViewModel.setPlanningTableRow(mockPlanningTableRow());
        return planningPageViewModel;
    }

    private List<String> generateMonthHeader(Period period) {
        Period.MonthName beginMonthName = period.getBegingMounth();
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

    private List<Integer> generateYearHeader(Period period) {
        int actualPlanningYear = period.getYearPlanned();
        int previousYear = actualPlanningYear-1;
        List<Integer> yearHeaderList = new ArrayList<>(24);

        for(int i = 0; i < 12; i++){
            yearHeaderList.add(previousYear);
            yearHeaderList.add(actualPlanningYear);
        }

        return yearHeaderList;
    }

    private List<List<PlanningTableCellModel>> mockPlanningTableRow() {
        List<List<PlanningTableCellModel>> planningTableRowList = new ArrayList<>(15);
        for(int i = 0; i < 15; i++){
            List<PlanningTableCellModel> planningTableCellModel = new ArrayList<>(25);
            planningTableRowList.add(planningTableCellModel);
            planningTableCellModel.add(new PlanningTableCellModel("Company name" + i,false,null,
                    null,null,null,null));
            for(int j = 0; j < 24; j++){
                PlanningTableCellModel cell = new PlanningTableCellModel();
                if(j % 2 == 0) {
                    cell.setContent("20 000 ft");
                    List<String> detailList = new ArrayList<>();
                    detailList.add("alma" + i + "" + j);
                    detailList.add("harom sarga gurulos fotel almatartoval");
                    cell.setDetails(detailList);
                    cell.setEditable(false);
                }else {
                    cell.setEditable(true);
                    cell.setContent(i*j + "0 ft");
                    cell.setCompanyId(1l);
                    cell.setMonth(Period.MonthName.OCT);
                    cell.setPlanId(1l);
//                    cell.setPlanPerCompanyId(1l);
                }
                planningTableCellModel.add(cell);
            }
        }
        return planningTableRowList;
    }
}
