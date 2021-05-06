package com.example.surfshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
class AnsichtControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void ansicht() throws Exception {
        mvc.perform(get("/template"))
                .andExpect(status().is3xxRedirection());
    }
}