package kr.jenna.plmography.controllers;

import kr.jenna.plmography.exceptions.ContentNotFound;
import kr.jenna.plmography.models.Movie;
import kr.jenna.plmography.services.GetMovieService;
import kr.jenna.plmography.services.GetMoviesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@ActiveProfiles("test")
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMovieService getMovieService;

    @MockBean
    private GetMoviesService getMoviesService;

    @Test
    void list() throws Exception {
        Integer page = 1;
        Integer size = 8;

        Movie movie = mock(Movie.class);

        given(getMoviesService.list(page, size))
                .willReturn(new PageImpl<>(List.of(movie)));

        mockMvc.perform(MockMvcRequestBuilders.get("/contents?page=1&size=8"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"totalPages\"")
                ))
                .andExpect(content().string(
                        containsString("\"contents\":[")
                ));

        verify(getMoviesService).list(page, size);
    }

    @Test
    void contentDetail() throws Exception {
        given(getMovieService.detail(any()))
                .willReturn(Movie.fake().toMovieDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/contents/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ));
    }

    @Test
    void contentDetailNotFound() throws Exception {
        given(getMovieService.detail(any()))
                .willThrow(ContentNotFound.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/contents/1"))
                .andExpect(status().isNotFound());
    }

}
