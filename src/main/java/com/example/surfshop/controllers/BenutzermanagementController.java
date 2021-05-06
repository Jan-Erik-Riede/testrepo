package com.example.surfshop.controllers;

import com.example.surfshop.repositories.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 29.04.2021
 */
@Controller
public class BenutzermanagementController {

    private final BenutzerRepository benutzerRepository;

    @Autowired
    public BenutzermanagementController(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    @GetMapping("/benutzeruebersicht")
    public String showBenutzeruebersicht(Model model) {
        model.addAttribute("users", benutzerRepository.findAll());
        return "benutzeruebersicht";
    }

    /**
     * Wenn ein Benutzer nicht die Berechtigung hat diesen Bereich zu sehen, wird er auf die default Page geleitet
     * @return
     */
    @GetMapping("/access_denied")
    public String userNotAuthorized() {
        return "template";
    }

    /**
     * Kontrolliert welchen Zugriff auf welche Seite gewährt wird
     * @param securityContextHolder
     * @param request
     * @return
     */
    @GetMapping("/userdirect")
    public String whatPageToOpen(SecurityContextHolder securityContextHolder, HttpServletRequest request) {

        if (securityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("CHEF"))
                || request.isUserInRole("CHEF")) {
            return "redirect:/chef-ansicht";

        } else return "redirect:/mitarbeiter-ansicht";
    }

    /**
     * Ansicht wenn sich ein Mitarbeiter anmeldet
     * @return
     */
    @GetMapping("/mitarbeiter-ansicht")
    public String mitarbeiterAnsicht() {
        return "template-mitarbeiter";
    }

    /**
     * Ansicht wenn sich ein Chef anmeldet
     * @return
     */
    @GetMapping("/chef-ansicht")
    public String chefAnsicht() {
        return "template";
    }
}
