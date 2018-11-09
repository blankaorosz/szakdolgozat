package hu.elte.szakdolgozat.spms.controller.rest;

import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import hu.elte.szakdolgozat.spms.model.ui.PlanningTableCellModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/plan")
public class PlanningRestController {

    @PostMapping("/save")
    public void test(@RequestBody List<PlanningTableCellModel> planningTableCellModelList) {
        System.out.println();
    }

    @PostMapping("/{id}/status/{status}")
    public void setPlanStatus(@PathVariable("id") Long planId, @PathVariable("status") Plan.PlanStatus status) {
        System.out.println("");
    }
}
