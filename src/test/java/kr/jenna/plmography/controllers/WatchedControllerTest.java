package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.watched.WatchedContentIdsDto;
import kr.jenna.plmography.services.user.ToggleUserWatchedService;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WatchedController.class)
@ActiveProfiles("test")
class WatchedControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToggleUserWatchedService toggleUserWatchedService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void toggleWatchedContent() throws Exception {
        given(toggleUserWatchedService.toggleWatched(any(), any()))
                .willReturn(WatchedContentIdsDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/watchedContent/2")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"watchedContentIds\":[")
                ));
    }
}
