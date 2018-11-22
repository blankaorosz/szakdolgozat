package hu.elte.szakdolgozat.spms.scheduler;

import hu.elte.szakdolgozat.spms.service.CompanyAndOrderDataImporterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class OfficeDataImportJob {
    private static final long ONE_DAY_IN_MILLISECONDS = 1000*60*60*24;

    @Autowired
    CompanyAndOrderDataImporterService companyAndOrderDataImporterService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void importOfficeData() {
        Date now = new Date();
        Date yesterday = new Date(now.getTime()-ONE_DAY_IN_MILLISECONDS);
        log.info("Import started in range: " + now.toString() + " and " + yesterday.toString());
        companyAndOrderDataImporterService.importByRange(yesterday,now);
        log.info("Import finished in range: " + now.toString() + " and " + yesterday.toString());
    }
}
