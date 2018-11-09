package hu.elte.szakdolgozat.spms.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    @PreAuthorize("hasRole('ROLE_SALES')")
    @RequestMapping(method = RequestMethod.GET)
    public String getAdminPage(ModelMap model) {


        return "adminPage";
    }
}
