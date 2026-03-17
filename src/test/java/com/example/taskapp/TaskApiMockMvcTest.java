package com.example.taskapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskApiMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void post_api_tasks_requires_login() throws Exception {
        mockMvc.perform(
                post("/api/tasks")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"api-task\"}")
        )
        .andExpect(status().is3xxRedirection()); // /login に飛ぶ
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void post_api_tasks_success() throws Exception {
        mockMvc.perform(
                post("/api/tasks")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"api-task\"}")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("api-task"));
    }
}