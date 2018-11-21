package hu.elte.szakdolgozat.spms.model.ui;

import lombok.Data;

import java.util.List;

@Data
public class MonitoringTableViewModel {
    private List<String> headerMonths;
    private List<String> headerTitles;
    private List<MonitoringRowModel> rows;
}
