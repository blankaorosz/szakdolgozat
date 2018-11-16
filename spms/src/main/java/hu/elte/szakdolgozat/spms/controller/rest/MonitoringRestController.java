package hu.elte.szakdolgozat.spms.controller.rest;

import hu.elte.szakdolgozat.spms.model.rest.SpmsRestResponse;
import hu.elte.szakdolgozat.spms.service.CompanyAndOrderDataImporterService;
import hu.elte.szakdolgozat.spms.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/monitoring")
public class MonitoringRestController {

    @Autowired
    private PeriodService periodService;

    @PreAuthorize("hasRole('ROLE_CEO')")
    @PostMapping("/planning-period/start-new")
    public SpmsRestResponse startPlanningPeriod() {
        SpmsRestResponse response = new SpmsRestResponse();
        try{
            periodService.createPeriod();
            response.setSuccess(true);
            response.setMessage("New period succesfully created");
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Unexpected error during create: " + ex.getMessage());
        }

        return response;
    }
}
