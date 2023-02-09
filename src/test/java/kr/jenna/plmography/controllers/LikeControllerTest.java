package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.like.LikeDto;
import kr.jenna.plmography.dtos.like.LikeUserIdsDto;
import kr.jenna.plmography.dtos.like.LikesDto;
import kr.jenna.plmography.services.like.CreateLikeService;
import kr.jenna.plmography.services.like.DeleteLikeService;
import kr.jenna.plmography.services.like.GetLikesService;
import kr.jenna.plmography.services.review.ToggleReviewLikeService;
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

@WebMvcTest(LikeController.class)
@ActiveProfiles("test")
class LikeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToggleReviewLikeService toggleReviewLikeService;

    @MockBean
    private GetLikesService getLikesService;

    @MockBean
    private CreateLikeService createLikeService;

    @MockBean
    private DeleteLikeService deleteLikeService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void toggleReviewLike() throws Exception {
        given(toggleReviewLikeService.toggleLike(any(), any()))
                .willReturn(LikeUserIdsDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/reviews/1/likeUserIds")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"likeUserIds\":[")
                ));
    }

    @Test
    void likes() throws Exception {
        given(getLikesService.likes())
                .willReturn(new LikesDto(List.of(LikeDto.fake())));

        mockMvc.perform(MockMvcRequestBuilders.get("/likes"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"likes\":[")
                ));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/likes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"userId\":\"1\","
                                + "\"postId\":\"1\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/likes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"likeId\": [1, 2]" +
                                "}"))
                .andExpect(status().isNoContent());
    }
}
