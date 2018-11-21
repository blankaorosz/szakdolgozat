package hu.elte.szakdolgozat.spms.model.ui;

import lombok.Data;

import java.util.List;

@Data
public class MonitoringRowModel {
    private String companyName;
    private String personName;
    private List<MonitoringCellGroup> columnValues;
}
