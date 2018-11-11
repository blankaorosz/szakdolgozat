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
}
