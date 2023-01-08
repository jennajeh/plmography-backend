package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.CommentDto;
import kr.jenna.plmography.dtos.CommentsDto;
import kr.jenna.plmography.dtos.PagesDto;
import kr.jenna.plmography.models.Comment;
import kr.jenna.plmography.services.CreateCommentService;
import kr.jenna.plmography.services.DeleteCommentService;
import kr.jenna.plmography.services.GetCommentService;
import kr.jenna.plmography.services.GetCommentsService;
import kr.jenna.plmography.services.PatchCommentService;
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
    private GetCommentService getCommentService;

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
    void comments() throws Exception {
        Integer page = 1;
        Integer size = 5;

        CommentDto commentDto = new CommentDto(
                1L, 1L, 1L, "reply", false, LocalDateTime.now());

        given(getCommentsService.comments(1L, page, size))
                .willReturn(new CommentsDto(List.of(commentDto), new PagesDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments?/page=1&size=5")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("\"comments\"")
                ));

        verify(getCommentsService).comments(1L, page, size);
    }

    @Test
    void detail() throws Exception {
        CommentDto commentDto = new CommentDto(
                1L, 1L, 1L, "강추~", false, LocalDateTime.now());

        given(getCommentService.detail(any())).willReturn(commentDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/comments/1"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"commentBody\":\"modify body\""
                                + "}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/1"))
                .andExpect(status().isNoContent());

        verify(deleteCommentService).delete(1L);
    }
}
