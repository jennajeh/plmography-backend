package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Comment.CommentDto;
import kr.jenna.plmography.dtos.Comment.CommentsDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.services.Comment.CreateCommentService;
import kr.jenna.plmography.services.Comment.DeleteCommentService;
import kr.jenna.plmography.services.Comment.GetCommentsService;
import kr.jenna.plmography.services.Comment.PatchCommentService;
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

@WebMvcTest(CommentController.class)
@ActiveProfiles("test")
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private GetCommentsService getCommentsService;

    @MockBean
    private PatchCommentService patchCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void create() throws Exception {
        given(createCommentService.create(any(), any()))
                .willReturn(Comment.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"commentBody\":\"동의합니다!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void list() throws Exception {
        CommentDto commentDto = CommentDto.fake();

        given(getCommentsService.comments(1L))
                .willReturn(new CommentsDto(List.of(commentDto)));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"comments\"")
                ));

        verify(getCommentsService).comments(1L);
    }

    @Test
    void patch() throws Exception {
        given(patchCommentService.modify(any(), any(), any())).willReturn(Comment.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/comments/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"commentBody\":\"modify body\""
                                + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
