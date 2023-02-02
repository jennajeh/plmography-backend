package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.page.PagesDto;
import kr.jenna.plmography.dtos.theme.ThemeDto;
import kr.jenna.plmography.dtos.theme.ThemesDto;
import kr.jenna.plmography.services.theme.GetThemesService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ThemeController.class)
@ActiveProfiles("test")
class ThemeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetThemesService getThemesService;

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

        given(getThemesService.list(page, size))
                .willReturn(new ThemesDto(List.of(ThemeDto.fake()), new PagesDto(1)));

        mockMvc.perform(MockMvcRequestBuilders.get("/themes?page=1&size=5"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"themes\":[")
                ));
    }

}
