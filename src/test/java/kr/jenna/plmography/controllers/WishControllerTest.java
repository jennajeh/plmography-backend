package kr.jenna.plmography.controllers;

import kr.jenna.plmography.dtos.wish.WishContentIdsDto;
import kr.jenna.plmography.services.user.ToggleUserWishService;
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
    private ToggleUserWishService toggleUserWishService;

    @SpyBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setup() {
        token = jwtUtil.encode(1L);
    }

    @Test
    void toggleWishContent() throws Exception {
        given(toggleUserWishService.toggleWish(any(), any()))
                .willReturn(WishContentIdsDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/wishContent/2")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"wishContentIds\":[")
                ));
    }
}
