package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.postComment.MyPostCommentsDto;
import kr.jenna.plmography.dtos.postComment.PostCommentDto;
import kr.jenna.plmography.dtos.postComment.PostCommentsDto;
import kr.jenna.plmography.models.PostComment;
import kr.jenna.plmography.services.postComment.CreatePostCommentService;
import kr.jenna.plmography.services.postComment.DeletePostCommentService;
import kr.jenna.plmography.services.postComment.GetPostCommentsService;
import kr.jenna.plmography.services.postComment.PatchPostCommentService;
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

@WebMvcTest(PostCommentController.class)
@ActiveProfiles("test")
class PostCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePostCommentService createPostCommentService;

    @MockBean
    private GetPostCommentsService getPostCommentsService;

    @MockBean
    private PatchPostCommentService patchPostCommentService;

    @MockBean
    private DeletePostCommentService deletePostCommentService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void create() throws Exception {
        given(createPostCommentService.create(any(), any()))
                .willReturn(PostComment.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/postComments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"postCommentBody\":\"동의합니다!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void reviews() throws Exception {
        Long postId = 1L;
        Integer page = 1;
        Integer size = 5;

        given(getPostCommentsService.list(postId, page, size))
                .willReturn(new PostCommentsDto(List.of(PostCommentDto.fake()), new PagesDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/postComments?&page=1&size=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("\"postComments\":[")
                ));
    }

    @Test
    void myComments() throws Exception {
        given(getPostCommentsService.myComments(any()))
                .willReturn(new MyPostCommentsDto(List.of(PostCommentDto.fake())));

        mockMvc.perform(MockMvcRequestBuilders.get("/postComments/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"myPostComments\":[")
                ));
    }

    @Test
    void modify() throws Exception {
        given(patchPostCommentService.modify(any(), any(), any()))
                .willReturn(PostComment.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/postComments?&commentId=1&postCommentBody=dd")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"postCommentBody\":\"modify body\""
                                + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/postComments/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
