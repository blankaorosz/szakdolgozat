package hu.elte.szakdolgozat.spms.model.ui;

import lombok.Data;

import java.util.List;

@Data
public class PlanningPageViewModel {
    private Long planId;
    private String title;
    private List<String> headerMonths;
    private List<Integer> headerYears;
    private List<List<PlanningTableCellModel>> planningTableRowList;
}
