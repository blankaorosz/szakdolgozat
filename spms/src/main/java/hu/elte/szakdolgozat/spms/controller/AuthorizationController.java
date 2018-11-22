/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Blanka Orosz
 */
@Controller
@RequestMapping("/login")
public class AuthorizationController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model) {
        if (!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/";
        }
      return "login";
    }
}
