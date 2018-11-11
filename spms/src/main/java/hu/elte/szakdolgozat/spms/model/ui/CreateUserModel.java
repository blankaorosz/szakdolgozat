package hu.elte.szakdolgozat.spms.model.ui;

import lombok.Data;

@Data
public class CreateUserModel {
    private Long id;
    private String userName;
    private String userRole;
    private String userPassword;
}
