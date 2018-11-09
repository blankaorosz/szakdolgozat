package hu.elte.szakdolgozat.spms.init;

import hu.elte.szakdolgozat.spms.service.CompanyAndOrderDataImporterService;
import hu.elte.szakdolgozat.spms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class BusinessDataInitializer {

    @Autowired
    private CompanyAndOrderDataImporterService companyAndOrderDataImporterService;

    @PostConstruct
    private void initBusinessData() {
        companyAndOrderDataImporterService.importByRange(
                DateUtil.parseDate("2018-01-01"),
                new Date()
        );
    }
}
