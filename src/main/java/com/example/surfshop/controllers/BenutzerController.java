package com.example.surfshop.controllers;

import com.example.surfshop.entities.Benutzer;
import com.example.surfshop.repositories.BenutzerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BenutzerController {

    private final BenutzerRepository benutzerRepository;

    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @Autowired
    public BenutzerController(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    @GetMapping("/createuser")
    public String showCreateUser(@ModelAttribute Benutzer benutzer, Model model) {
        model.addAttribute("benutzer", benutzer);
        return "createuser";
    }

    @PostMapping("/createuser")
    public String addUser(@Valid Benutzer benutzer, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "createuser";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(benutzer.getPasswort());
        benutzer.setPasswort(encodedPassword);


        try {
            benutzerRepository.save(benutzer);
        } catch (DataIntegrityViolationException e) {
            bindingResult.addError(new FieldError("benutzer", "benutzername", "Der Benutzername ist bereits vorhanden!"));
            return "createuser";
        }

        logger.info(">>benutzer : {}", benutzer.toString());
        return "redirect:/benutzeruebersicht-success";
    }

    /**
     * bei success (anlegen von User)
     * wird die uebersicht aller benutzer angezeigt, mit der erfolgreich Meldung
     * */
    @GetMapping("/benutzeruebersicht-success")
    public String showBenutzeruebersicht(Model model) {
        model.addAttribute("benutzerangelegt", true);
        model.addAttribute("users", benutzerRepository.findAll());
        return "benutzeruebersicht";
    }
}
