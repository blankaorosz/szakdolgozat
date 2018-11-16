package hu.elte.szakdolgozat.spms.controller;


import hu.elte.szakdolgozat.spms.model.entity.spms.Role;
import hu.elte.szakdolgozat.spms.repository.spms.RoleRepository;
import hu.elte.szakdolgozat.spms.repository.spms.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String getAdminPage(ModelMap model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roleNames",
                Arrays.stream(Role.RoleName.values())
                        .filter(roleName -> !Role.RoleName.SALES.equals(roleName))
                        .map(roleName -> roleName.name()).toArray());

        return "adminPage";
    }
}
