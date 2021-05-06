package com.example.surfshop.controllers;

import com.example.surfshop.config.AuthenticationChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 30.04.2021
 */
@Controller
public class LogoutController {

    @GetMapping("/logout-success")
    public String logout(Model model) {
        if (AuthenticationChecker.isAuthenticated()) {
            return"redirect:/userdirect";
        }
        model.addAttribute("logout", true);
        return "index";
    }
}
