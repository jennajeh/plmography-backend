package kr.jenna.plmography.controllers;

import kr.jenna.plmography.exceptions.EmailAlreadyExist;
import kr.jenna.plmography.models.Email;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.services.CreateUserService;
import kr.jenna.plmography.services.GetUserService;
import kr.jenna.plmography.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService createUserService;

    @MockBean
    private GetUserService getUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void userDetail() throws Exception {
        given(getUserService.detail(any())).willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"email\"")
                ));
    }

    @Test
    void signup() throws Exception {
        given(createUserService.create(any())).willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\""
                                + ", \"nickname\":\"전제나\""
                                + ", \"password\":\"Test123!\""
                                + ", \"passwordCheck\":\"Test123!\""
                                + "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }

    @Test
    void signUpWithInvalidEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"xxx\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"Test123!\""
                                + ", \"passwordCheck\":\"Test123!\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithExistedEmail() throws Exception {
        given(createUserService.create(any()))
                .willThrow(new EmailAlreadyExist(new Email("jenna@gmail.com")));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"Test123!\""
                                + ", \"passwordCheck\":\"Test123!\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithShortPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"Test1\""
                                + ", \"passwordCheck\":\"Test1\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutUppercase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"test123!\""
                                + ", \"passwordCheck\":\"test123!\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutLowercase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"TEST123!\""
                                + ", \"passwordCheck\":\"TEST123!\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"TESTasdf!\""
                                + ", \"passwordCheck\":\"TESTasdf!\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutSpecialCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"email\":\"jenna@gmail.com\","
                                + "\"nickname\":\"전제나\","
                                + ", \"password\":\"TESTasdf1\""
                                + ", \"passwordCheck\":\"TESTasdf1\""
                                + "}"))
                .andExpect(status().isBadRequest());
    }
}
