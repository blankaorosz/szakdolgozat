package hu.elte.szakdolgozat.spms.controller.rest;

import hu.elte.szakdolgozat.spms.model.rest.SpmsRestResponse;
import hu.elte.szakdolgozat.spms.service.CompanyAndOrderDataImporterService;
import hu.elte.szakdolgozat.spms.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rest/statistics")
public class StatisticsRestController {

    @Autowired
    private CompanyAndOrderDataImporterService importerService;

    @PreAuthorize("hasRole('ROLE_CEO')")
    @PostMapping("/refresh-office-data/{from}/{to}")
    public SpmsRestResponse refreshOfficeData(@PathVariable long from, @PathVariable long to) {
        SpmsRestResponse response = new SpmsRestResponse();

        Date fromDate = new Date(from);
        Date toDate = new Date(to);

        try {
            importerService.importByRange(fromDate, toDate);

            response.setSuccess(true);
            response.setMessage("Data successfully imported!");
        }  catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Unexpected error during import: " + ex.getMessage());
        }

        return response;
    }
}
