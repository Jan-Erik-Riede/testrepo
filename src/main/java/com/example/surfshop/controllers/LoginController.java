package com.example.surfshop.controllers;

import com.example.surfshop.config.AuthenticationChecker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 27.04.2021
 */
@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        if (AuthenticationChecker.isAuthenticated()) {
            return "redirect:/userdirect";
        }
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        if (AuthenticationChecker.isAuthenticated()) {
            return "redirect:/userdirect";
        }
        return "index";
    }

    @GetMapping("/login-invalid")
    public String invalidLogin(Model model) {
        model.addAttribute("sessionError", true);
        return "index";
    }

    @PostMapping("/login")
    public String login() {
        return "/template";
    }

    /**
     * Kontrolliert welchen Zugriff auf welche Seite gewährt wird
     * @param securityContextHolder
     * @param request
     * @return
     */
    @GetMapping("/login-user")
    public String loginUser(SecurityContextHolder securityContextHolder, HttpServletRequest request) {

        if (securityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("CHEF"))
                || request.isUserInRole("CHEF")) {
            return "redirect:/chef-ansicht";

        } else return "redirect:/mitarbeiter-ansicht";
    }


    @GetMapping("/login-error")
    public String showLoginError(Model model) {
        if (AuthenticationChecker.isAuthenticated()) {
            return "redirect:/userdirect";
        }
        model.addAttribute("error", true);
        return "index";
    }
}
