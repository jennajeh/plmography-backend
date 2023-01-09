package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Recomment.RecommentDto;
import kr.jenna.plmography.dtos.Recomment.RecommentsDto;
import kr.jenna.plmography.models.Recomment;
import kr.jenna.plmography.services.Recomment.CreateRecommentService;
import kr.jenna.plmography.services.Recomment.DeleteRecommentService;
import kr.jenna.plmography.services.Recomment.GetRecommentService;
import kr.jenna.plmography.services.Recomment.GetRecommentsService;
import kr.jenna.plmography.services.Recomment.PatchRecommentService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecommentController.class)
@ActiveProfiles("test")
class RecommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateRecommentService createRecommentService;

    @MockBean
    private GetRecommentService getRecommentService;

    @MockBean
    private GetRecommentsService getRecommentsService;

    @MockBean
    private PatchRecommentService patchRecommentService;

    @MockBean
    private DeleteRecommentService deleteRecommentService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void create() throws Exception {
        given(createRecommentService.create(any(), any()))
                .willReturn(Recomment.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/recomments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"recommentBody\":\"reply\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void list() throws Exception {
        RecommentDto recommentDto = new RecommentDto(
                1L, 1L, "reply again", 1L, 1L, LocalDateTime.now());

        given(getRecommentsService.recomments())
                .willReturn(new RecommentsDto(List.of(recommentDto)));

        mockMvc.perform(MockMvcRequestBuilders.get("/recomments"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"recomments\":[")
                ));
    }

    @Test
    void detail() throws Exception {
        RecommentDto recommentDto = new RecommentDto(
                1L, 1L, "re-reply", 1L, 1L, LocalDateTime.now());

        given(getRecommentService.detail(1L))
                .willReturn(new RecommentsDto(List.of(recommentDto)));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/recomments"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"recommentBody\":\"re-reply\"")
                ));
    }

    @Test
    void patch() throws Exception {
        given(patchRecommentService.update(any(), any(), any())).willReturn(Recomment.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/recomments/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"recommentBody\":\"modify body\""
                                + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/recomments/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
