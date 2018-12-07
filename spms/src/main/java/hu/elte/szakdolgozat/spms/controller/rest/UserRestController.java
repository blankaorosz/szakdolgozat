package hu.elte.szakdolgozat.spms.controller.rest;

import hu.elte.szakdolgozat.spms.model.entity.spms.Role;
import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.model.rest.SpmsRestResponse;
import hu.elte.szakdolgozat.spms.model.ui.CreateUserModel;
import hu.elte.szakdolgozat.spms.repository.spms.RoleRepository;
import hu.elte.szakdolgozat.spms.repository.spms.UserRepository;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public SpmsRestResponse<CreateUserModel> createUser(@RequestBody CreateUserModel userModel) {
        SpmsRestResponse<CreateUserModel> spmsRestResponse = new SpmsRestResponse<>();

        if (userRepository.findByUserName(userModel.getUserName()) != null) {
            spmsRestResponse.setSuccess(false);
            spmsRestResponse.setMessage("User already exists");
            return spmsRestResponse;
        }

        User u = new User();
        u.setUserName(userModel.getUserName());
        u.setPassword(passwordEncoder.encode(userModel.getUserPassword()));
        u.setRole(roleRepository.findByName(Role.RoleName.valueOf(userModel.getUserRole())));

        u = userRepository.save(u);

        userModel.setUserPassword("");
        userModel.setId(u.getId());

        spmsRestResponse.setSuccess(true);
        spmsRestResponse.setMessage("User added");
        spmsRestResponse.setContent(userModel);
        return spmsRestResponse;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete/{userId}")
    public SpmsRestResponse deletUser(@PathVariable Long userId) {
        SpmsRestResponse<User> spmsRestResponse = new SpmsRestResponse<>();
        if (!SecurityUtil.getLoggedInUser().getId().equals(userId)) {
            User userToDelete = userRepository.findById(userId).get();
            if (!Role.RoleName.SALES.equals(userToDelete.getRole().getName())) {
                userRepository.deleteById(userId);
                spmsRestResponse.setSuccess(true);
                spmsRestResponse.setMessage("User deleted");
                return spmsRestResponse;
            }

        }
        spmsRestResponse.setSuccess(false);
        spmsRestResponse.setMessage("User can't be deleted");
        return spmsRestResponse;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/save/{userId}")
    public SpmsRestResponse<CreateUserModel> saveUser(@PathVariable Long userId, @RequestBody CreateUserModel userModel) {
        SpmsRestResponse<CreateUserModel> response = new SpmsRestResponse();

        try {
            boolean updated = false;

            User user = userRepository.findById(userId).get();
            if (userModel.getUserName() != null && !userModel.getUserName().equals(user.getUserName())) {
                user.setUserName(userModel.getUserName());
                updated = true;
            }

            if (userModel.getUserPassword() != null) {
                String hashedPassword = passwordEncoder.encode(userModel.getUserPassword());
                if (!hashedPassword.equals(user.getPassword())) {
                    user.setPassword(hashedPassword);
                    updated = true;
                }
            }

            if (updated) {
                user = userRepository.save(user);
            }

            userModel.setId(user.getId());
            userModel.setUserName(user.getUserName());
            userModel.setUserRole(user.getRole().getName().name());

            response.setSuccess(true);
            response.setMessage("User has been updated!");
            response.setContent(userModel);

        }catch (Exception ex) {
            response.setSuccess(false);
            String msg = String
                    .format("Unexpected error while trying to update user with id: %s! Error: %s",
                            userId, ex.getMessage());
            response.setMessage(msg);
        }

        return response;
    }
}
