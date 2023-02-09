package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.post.PostDto;
import kr.jenna.plmography.dtos.post.PostModificationResponseDto;
import kr.jenna.plmography.dtos.post.PostsDto;
import kr.jenna.plmography.models.Post;
import kr.jenna.plmography.services.post.CreatePostService;
import kr.jenna.plmography.services.post.DeletePostService;
import kr.jenna.plmography.services.post.GetPostService;
import kr.jenna.plmography.services.post.GetPostsService;
import kr.jenna.plmography.services.post.PatchPostService;
import kr.jenna.plmography.utils.JwtUtil;
import kr.jenna.plmography.utils.S3Uploader;
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

@WebMvcTest(PostController.class)
@ActiveProfiles("test")
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private GetPostsService getPostsService;

    @MockBean
    private PatchPostService patchPostService;

    @MockBean
    private DeletePostService deletePostService;

    @MockBean
    private S3Uploader s3Uploader;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void create() throws Exception {
        given(createPostService.create(any(), any()))
                .willReturn(Post.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"title\":\"제목 작성!\""
                                + "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void list() throws Exception {
        Integer page = 1;
        Integer size = 10;

        given(getPostsService.list(any(), any(), any()))
                .willReturn(new PostsDto(List.of(PostDto.fake()), new PagesDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/filter?page=1&size=10"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"posts\":[")
                ))
                .andExpect(content().string(
                        containsString("\"pages\":")
                ));
    }

    @Test
    void detail() throws Exception {
        given(getPostService.detail(any())).willReturn(PostDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ));
    }

    @Test
    void myPost() throws Exception {
        given(getPostService.myPost(any())).willReturn(any());

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/me")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void modify() throws Exception {
        given(patchPostService.modify(any(), any(), any()))
                .willReturn(new PostModificationResponseDto(1L));

        mockMvc.perform(MockMvcRequestBuilders.patch("/posts/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{"
                                + "\"title\":\"modify title\","
                                + "\"postBody\":\"modify body\","
                                + "\"image\":\"modify image\""
                                + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/posts/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }
}
