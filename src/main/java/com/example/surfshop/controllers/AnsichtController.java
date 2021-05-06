package com.example.surfshop.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 30.04.2021
 */
@Controller
public class AnsichtController {


    /**
     * Kontrolliert welchen Zugriff auf welche Seite gewährt wird
     * @param securityContextHolder
     * @param request
     * @return
     */
    @GetMapping("/template")
    public String ansicht(SecurityContextHolder securityContextHolder, HttpServletRequest request) {

        if (securityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("CHEF"))
                || request.isUserInRole("CHEF")) {
            return "redirect:/chef-ansicht";

        } else return "redirect:/mitarbeiter-ansicht";
    }
}
