package hu.elte.szakdolgozat.spms.model.ui;

import hu.elte.szakdolgozat.spms.model.entity.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningTableCellModel {
    private String content;
    private boolean editable;//true if planned year
    private List<String> details; //previous year transactions
    private Long companyId; //planned year
    private Long planId;//planned year
    private Long planPerCompanyId;//planned year
    private Period.MonthName month;

}
