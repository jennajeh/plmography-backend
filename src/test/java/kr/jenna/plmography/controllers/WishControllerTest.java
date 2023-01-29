package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.wish.WishUserIdsDto;
import kr.jenna.plmography.services.content.ToggleContentWishService;
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

@WebMvcTest(WishController.class)
@ActiveProfiles("test")
class WishControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToggleContentWishService toggleContentWishService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void toggleWishContent() throws Exception {
        given(toggleContentWishService.toggleWish(any(), any()))
                .willReturn(WishUserIdsDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/contents/1/wishUserIds")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"wishUserIds\":[")
                ));
    }
}
