package com.demo.redis.integration;

import com.demo.redis.integration.controller.AccountController;
import com.demo.redis.integration.dto.AccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
class RedisIntegrationApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private AccountController accountController;

    private AccountRequest accountRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .build();

        accountRequest = AccountRequest.builder()
                .address("New York")
                .phone("554-5431-8844")
                .build();
    }

    @Test
    void shouldCreateAccount() throws Exception {
        String requestAsJson = objectMapper.writeValueAsString(accountRequest);
        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsJson)
                ).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void shouldListAccounts() throws Exception {
        String requestAsJson = objectMapper.writeValueAsString(accountRequest);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsJson)
                ).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
