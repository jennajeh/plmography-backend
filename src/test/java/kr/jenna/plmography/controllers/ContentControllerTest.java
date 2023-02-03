package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.content.ContentsDto;
import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Content;
import kr.jenna.plmography.services.content.GetContentService;
import kr.jenna.plmography.services.content.GetContentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
@ActiveProfiles("test")
class ContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetContentService getContentService;

    @MockBean
    private GetContentsService getContentsService;

    @Test
    void topRated() throws Exception {
        given(getContentsService.topRated())
                .willReturn(new ContentsDto(List.of(Content.fake().toContentDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/contents/topRated"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"contents\":[")
                ));
    }

    @Test
    void contentDetail() throws Exception {
        given(getContentService.detail(any()))
                .willReturn(Content.fake().toContentDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/contents/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ));
    }

    @Test
    void contentDetailNotFound() throws Exception {
        given(getContentService.detail(any()))
                .willThrow(ContentNotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/contents/1"))
                .andExpect(status().isNotFound());
    }
}
