package hu.elte.szakdolgozat.spms.service;

import java.util.Date;

public interface CompanyAndOrderDataImporter {

    void importByRange(Date fromDate, Date toDate);

}
