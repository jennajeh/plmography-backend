package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.Article.ArticleDto;
import kr.jenna.plmography.dtos.Article.ArticlesDto;
import kr.jenna.plmography.dtos.Page.PagesDto;
import kr.jenna.plmography.services.Article.GetArticleService;
import kr.jenna.plmography.services.Article.GetArticlesService;
import kr.jenna.plmography.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@ActiveProfiles("test")
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetArticlesService getArticlesService;

    @MockBean
    private GetArticleService getArticleService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void list() throws Exception {
        Integer page = 1;
        Integer size = 5;

        given(getArticlesService.list(page, size))
                .willReturn(new ArticlesDto(List.of(ArticleDto.fake()), new PagesDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles?&page=1&size=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("\"articles\":[")
                ));

        verify(getArticlesService).list(page, size);
    }

    @Test
    void detail() throws Exception {
        given(getArticleService.detail(1L)).willReturn(ArticleDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":")
                ));
    }
}
