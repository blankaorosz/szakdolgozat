package hu.elte.szakdolgozat.spms.model.ui;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonitoringCellGroup {
    private String actualAmount;
    private String plannedAmount;
    private String percentDone;
    private String percentOfTimeGone;
}
