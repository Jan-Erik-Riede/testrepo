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
 * Date: 02.05.2021
 */
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void index() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void showLogin() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void login() throws Exception {
        mvc.perform(post("/login"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void showLoginError() throws Exception {
        mvc.perform(get("/login-error"))
                .andExpect(status().isOk());
    }

    @Test
    void login_User() throws Exception {
        mvc.perform(get("/login-user"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void login_Invalid() throws Exception {
        mvc.perform(get("/login-invalid"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void loginUser() throws Exception {
        mvc.perform(formLogin().user("admin").password("admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login-user"));
        mvc.perform(formLogin().user("admin").password("admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login-error"));
    }


    @Test
    void loginUserError() throws Exception {
        mvc.perform(formLogin().user("admin").password("123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login-error"));
    }
}