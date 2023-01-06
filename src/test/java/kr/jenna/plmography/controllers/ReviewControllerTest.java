package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.PagesDto;
import kr.jenna.plmography.dtos.ReviewDto;
import kr.jenna.plmography.dtos.ReviewsDto;
import kr.jenna.plmography.models.Review;
import kr.jenna.plmography.services.CreateReviewService;
import kr.jenna.plmography.services.DeleteReviewService;
import kr.jenna.plmography.services.GetReviewService;
import kr.jenna.plmography.services.GetReviewsService;
import kr.jenna.plmography.services.PatchReviewService;
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
import java.time.format.DateTimeFormatter;
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
    private ReviewDto reviewDto;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
        reviewDto = new ReviewDto(1L, 1L, 1L, 4L, "꿀잼",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    void create() throws Exception {
        given(createReviewService.create(any()))
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
                .willReturn(new ReviewsDto(List.of(reviewDto), new PagesDto(1)));

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
    void detail() throws Exception {
        given(getReviewService.detail(any())).willReturn(Review.fake());

        mockMvc.perform(MockMvcRequestBuilders.get("/reviews/1"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/reviews/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"reviewBody\":\"modify body\""
                                + "}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/reviews/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"reviewBody\":\"review body\""
                                + "}"))
                .andExpect(status().isNoContent());
    }
}
