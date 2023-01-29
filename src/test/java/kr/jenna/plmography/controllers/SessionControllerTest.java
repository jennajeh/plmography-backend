package kr.jenna.plmography.controllers;

import kr.jenna.plmography.exceptions.LoginFailed;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.services.user.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void loginSuccess() throws Exception {
        given(loginService.login(any(), any())).willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"password\":\"Test123!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void loginFail() throws Exception {
        given(loginService.login(any(), any())).willThrow(new LoginFailed());

        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"password\":\"tEsT123!\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

}
