package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Page.PagesDto;
import kr.jenna.plmography.dtos.Review.ReviewDto;
import kr.jenna.plmography.dtos.Review.ReviewsDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.services.Review.CreateReviewService;
import kr.jenna.plmography.services.Review.DeleteReviewService;
import kr.jenna.plmography.services.Review.GetReviewService;
import kr.jenna.plmography.services.Review.GetReviewsService;
import kr.jenna.plmography.services.Review.PatchReviewService;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
@ActiveProfiles("test")
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateReviewService createReviewService;

    @MockBean
    private GetReviewService getReviewService;

    @MockBean
    private GetReviewsService getReviewsService;

    @MockBean
    private PatchReviewService patchReviewService;

    @MockBean
    private DeleteReviewService deleteReviewService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void create() throws Exception {
        given(createReviewService.create(any(), any()))
                .willReturn(Review.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"reviewBody\":\"영화가 재미있어요!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void reviews() throws Exception {
        Integer page = 1;
        Integer size = 3;

        given(getReviewsService.reviews(1L, page, size))
                .willReturn(new ReviewsDto(List.of(ReviewDto.fake()), new PagesDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews?&page=1&size=3")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("\"reviews\":[")
                ));

        verify(getReviewsService).reviews(1L, page, size);
    }

    @Test
    void myReview() throws Exception {
        given(getReviewService.myReview(any())).willReturn(any());

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        given(patchReviewService.modify(any(), any(), any())).willReturn(Review.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/reviews/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"reviewBody\":\"modify body\""
                                + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/reviews/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
