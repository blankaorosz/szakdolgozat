package hu.elte.szakdolgozat.spms.controller.rest;

import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import hu.elte.szakdolgozat.spms.model.rest.SpmsRestResponse;
import hu.elte.szakdolgozat.spms.model.ui.PlanningTableCellModel;
import hu.elte.szakdolgozat.spms.service.PlanningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/plan")
@Slf4j
public class PlanningRestController {

    @Autowired
    private PlanningService planningService;

    @PostMapping("/save")
    public SpmsRestResponse save(@RequestBody List<PlanningTableCellModel> planningTableCellModelList) {
        SpmsRestResponse response = new SpmsRestResponse();

        try {
            planningService.savePlanningPage(planningTableCellModelList);
            response.setSuccess(true);
            response.setMessage("Plan successfully saved!");
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Plan could not be saved: " + ex.getMessage());
            log.error("", ex);
        }

        return response;
    }

    @PostMapping("/{id}/status/{status}")
    public void setPlanStatus(@PathVariable("id") Long planId, @PathVariable("status") Plan.PlanStatus status) {
        System.out.println("");
    }
}
