package com.example.surfshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 02.05.2021
 */
@SpringBootTest
@AutoConfigureMockMvc
class LogoutControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void logoutSuccess() throws Exception {
        mvc.perform(get("/logout-success"))
                .andExpect(status().isOk());
    }

    @Test
    void logout() throws Exception {
        mvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logout-success"));
    }

    @Test
    void tryUrl() throws Exception {
        mvc.perform(get("/benutzeruebersicht"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void tryUrl2() throws Exception {
        mvc.perform(get("/template"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void tryUrl3() throws Exception {
        mvc.perform(get("/createuser"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}