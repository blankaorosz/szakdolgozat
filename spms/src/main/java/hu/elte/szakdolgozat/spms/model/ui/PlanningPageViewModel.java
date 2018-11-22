package hu.elte.szakdolgozat.spms.model.ui;

import hu.elte.szakdolgozat.spms.model.entity.spms.Comment;
import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import lombok.Data;

import java.util.List;

@Data
public class PlanningPageViewModel {
    private Long planId;
    private String title;
    private List<String> headerMonths;
    private List<Integer> headerYears;
    private List<List<PlanningTableCellModel>> planningTableRowList;
    private List<User> salesUserList;
    private User selectedUser;
    private List<Comment> comments;
    private String planStatus;
}
