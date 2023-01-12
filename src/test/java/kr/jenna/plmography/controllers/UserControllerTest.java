package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.User.UserCountDto;
import kr.jenna.plmography.exceptions.EmailAlreadyExist;
import kr.jenna.plmography.models.User;
import kr.jenna.plmography.models.VO.Email;
import kr.jenna.plmography.services.User.CreateUserService;
import kr.jenna.plmography.services.User.GetUserService;
import kr.jenna.plmography.services.User.GetUsersService;
import kr.jenna.plmography.services.User.PatchUserService;
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

import java.util.List;

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

    @MockBean
    private GetUsersService getUsersService;

    @MockBean
    private PatchUserService patchUserService;

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
    void list() throws Exception {
        User user = User.fake();

        given(getUsersService.list()).willReturn(List.of(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
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

    @Test
    void changeProfile() throws Exception {
        given(patchUserService.update(any(), any())).willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/users")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"nickname\":\"전제나\","
                                + "\"profileImage\":\"new image\""
                                + "}"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"profileImage\"")
                ));

    }

    @Test
    void userCountWithExistingEmailAndNickname() throws Exception {
        given(getUserService.count(any(), any()))
                .willReturn(new UserCountDto(1, 1));

        mockMvc.perform(MockMvcRequestBuilders.get(
                        "/users/checkDuplicate?countOnly=true&email=\"exist@email.com\"&nickname=\"exist\""))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"countEmail\":1")
                ));
    }
}
