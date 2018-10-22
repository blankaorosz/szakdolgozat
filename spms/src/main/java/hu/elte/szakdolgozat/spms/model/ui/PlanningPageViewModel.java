package hu.elte.szakdolgozat.spms.model.ui;

import hu.elte.szakdolgozat.spms.model.entity.PlanPerCompany;
import lombok.Data;

import java.util.List;

@Data
public class PlanningPageViewModel {
    private String title;
    private List<String> headerMonths;
    private List<Integer> headerYears;
    private List<List<PlanningTableCellModel>> planningTableRow;
}
