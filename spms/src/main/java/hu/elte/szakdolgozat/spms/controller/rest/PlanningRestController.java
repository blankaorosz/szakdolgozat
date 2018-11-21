package hu.elte.szakdolgozat.spms.controller.rest;

import hu.elte.szakdolgozat.spms.model.entity.spms.Comment;
import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.model.rest.SpmsRestResponse;
import hu.elte.szakdolgozat.spms.model.ui.PlanningTableCellModel;
import hu.elte.szakdolgozat.spms.service.PlanningService;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/plan")
@Slf4j
public class PlanningRestController {

    @Autowired
    private PlanningService planningService;

    @PreAuthorize("hasAnyRole('ROLE_SALES', 'ROLE_CONTROLLER')")
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


    @PreAuthorize("hasAnyRole('ROLE_SALES', 'ROLE_CONTROLLER')")
    @PostMapping("/{id}/status/{status}")
    public SpmsRestResponse setPlanStatus(@PathVariable("id") Long planId, @PathVariable("status") Plan.PlanStatus status) {
        SpmsRestResponse response = new SpmsRestResponse();
        User currentUser = SecurityUtil.getLoggedInUser();
        try {
            planningService.changePlanStatus(currentUser,planId, status);
            response.setSuccess(true);
            response.setMessage("Plan status successfully changed!");
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Plan status could not be changed: " + ex.getMessage());
            log.error("", ex);
        }

        return response;
    }

    @PreAuthorize("hasRole('ROLE_CONTROLLER')")
    @PostMapping(value = "/{id}/addComment", consumes = "text/html", produces = "application/json")
    public SpmsRestResponse<Comment> addComment(@PathVariable("id") Long planId, @RequestBody String commentText) {
        SpmsRestResponse<Comment> response = new SpmsRestResponse();
        User currentUser = SecurityUtil.getLoggedInUser();
        try {
            Comment c = planningService.addComment(planId,currentUser, commentText);
            c.setPlan(null);
            response.setContent(c);
            response.setSuccess(true);
            response.setMessage("Comment successfully added!");
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMessage("Comment could not be added: " + ex.getMessage());
            log.error("", ex);
        }

        return response;
    }
}

