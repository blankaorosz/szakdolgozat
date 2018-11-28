package hu.elte.szakdolgozat.spms.controller;

import hu.elte.szakdolgozat.spms.model.entity.spms.User;
import hu.elte.szakdolgozat.spms.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public String getErrorPage(HttpServletRequest request, ModelMap model) {
        User currentUser = SecurityUtil.getLoggedInUser();

        model.addAttribute("userName", currentUser.getUserName());
        model.addAllAttributes(errorAttributes.getErrorAttributes(new ServletWebRequest(request), false));

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
