package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.subscribe.MySubscribeDto;
import kr.jenna.plmography.dtos.subscribe.OtherSubscribeDto;
import kr.jenna.plmography.dtos.subscribe.SubscribeUserInfoDto;
import kr.jenna.plmography.services.subscribe.CreateSubscribeService;
import kr.jenna.plmography.services.subscribe.DeleteSubscribeService;
import kr.jenna.plmography.services.subscribe.GetSubscribesService;
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

@WebMvcTest(SubscribeController.class)
@ActiveProfiles("test")
class SubscribeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateSubscribeService createSubscribeService;

    @MockBean
    private GetSubscribesService getSubscribesService;

    @MockBean
    private DeleteSubscribeService deleteSubscribeService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void follow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/subscribe/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isCreated());
    }

    @Test
    void mySubscribeList() throws Exception {
        given(getSubscribesService.mySubscribeCount(any()))
                .willReturn(new MySubscribeDto(1L, 1, 1));

        mockMvc.perform(MockMvcRequestBuilders.get("/subscribe/me")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"userId\"")
                ));
    }

    @Test
    void otherSubscribeList() throws Exception {
        given(getSubscribesService.otherSubscribeCount(any(Long.class), any(Long.class)))
                .willReturn(new OtherSubscribeDto(
                        false, new SubscribeUserInfoDto(1L, "jenna", "image"), 1, 1));

        mockMvc.perform(MockMvcRequestBuilders.get("/subscribe/2")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"subscribeStatus\"")
                ));
    }

    @Test
    void unFollow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/subscribe/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
