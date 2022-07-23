package com.yushixin.route.controller;

import com.yushixin.constant.TestConstant;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("跳转页面-主页")
    public void testHome() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/route/home")
                .param("tabPageId", "Welcome")
                .accept(MediaType.TEXT_HTML)
                .session(TestConstant.Session))
            .andDo(print())
            .andReturn()
            .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("跳转页面-用户注册")
    public void testRegister() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc
            .perform(get("/route/register")
                .accept(MediaType.TEXT_HTML))
            .andDo(print())
            .andReturn()
            .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("跳转页面-用户登录")
    public void testLogin() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc
                .perform(get("/route/login")
                    .accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andReturn()
                .getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
