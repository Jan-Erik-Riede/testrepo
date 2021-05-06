package com.example.surfshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 04.05.2021
 */
@SpringBootTest
@AutoConfigureMockMvc
class BenutzermanagementControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void showBenutzeruebersicht() throws Exception {
        mvc.perform(get("/benutzeruebersicht"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void userNotAuthorized() throws Exception {
        mvc.perform(get("/access_denied"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void whatPageToOpen() throws Exception {
        mvc.perform(get("/userdirect"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void mitarbeiterAnsicht() throws Exception {
        mvc.perform(get("/mitarbeiter-ansicht"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void chefAnsicht() throws Exception {
        mvc.perform(get("/chef-ansicht"))
                .andExpect(status().is3xxRedirection());
    }
}