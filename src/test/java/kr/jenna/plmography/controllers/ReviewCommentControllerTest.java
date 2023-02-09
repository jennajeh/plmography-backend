package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.reviewComment.ReviewCommentDto;
import kr.jenna.plmography.dtos.reviewComment.ReviewCommentsDto;
import kr.jenna.plmography.models.ReviewComment;
import kr.jenna.plmography.services.reviewComment.CreateReviewCommentService;
import kr.jenna.plmography.services.reviewComment.DeleteReviewCommentService;
import kr.jenna.plmography.services.reviewComment.GetReviewCommentsService;
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

@WebMvcTest(ReviewCommentController.class)
@ActiveProfiles("test")
class ReviewCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateReviewCommentService createReviewCommentService;

    @MockBean
    private GetReviewCommentsService getReviewCommentsService;

    @MockBean
    private DeleteReviewCommentService deleteReviewCommentService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void create() throws Exception {
        given(createReviewCommentService.create(any(), any()))
                .willReturn(ReviewComment.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/reviewComments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"reviewCommentBody\":\"동의합니다!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void list() throws Exception {
        ReviewCommentDto reviewCommentDto = ReviewCommentDto.fake();

        given(getReviewCommentsService.list())
                .willReturn(new ReviewCommentsDto(List.of(reviewCommentDto)));

        mockMvc.perform(MockMvcRequestBuilders.get("/reviewComments"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"reviewComments\"")
                ));

        verify(getReviewCommentsService).list();
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/reviewComments/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
