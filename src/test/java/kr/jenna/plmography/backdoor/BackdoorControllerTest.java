package kr.jenna.plmography.backdoor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BackdoorControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void setupDatabase() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-database"))
//                .andExpect(status().isOk());
//    }

//    @Test
//    void setupContent() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-content"))
//                .andExpect(status().isOk());
//    }

    @Test
    void setupMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-movie"))
                .andExpect(status().isOk());
    }

    @Test
    void setupTv() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-tv"))
                .andExpect(status().isOk());
    }

    @Test
    void setupPlatformAndType() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/backdoor/setup-platform-type"))
                .andExpect(status().isOk());
    }
}
