package red.mlz.study.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import red.mlz.study.module.entity.Match;
import red.mlz.study.module.service.MatchService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = AppApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MatchService matchService;

    private static Long testMatchId;

    @Test
    @Order(1)
    void testList() throws Exception {
        Match match = new Match();
        match.setLeague("PREMIER LEAGUE");
        match.setHomeTeam("Arsenal");
        match.setAwayTeam("Chelsea");
        match.setHomeScore(3);
        match.setAwayScore(1);
        match.setStatus("FINISHED");
        match.setMatchTime("Jul 5, 2026 20:00");
        match.setVenue("Emirates Stadium");
        match.setImages("img1$img2");
        matchService.create(match);

        testMatchId = match.getId();

        MvcResult result = mockMvc.perform(get("/match/list"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list").isArray())
            .andExpect(jsonPath("$.list", hasSize(greaterThanOrEqualTo(1))))
            .andExpect(jsonPath("$.list[0].id").exists())
            .andExpect(jsonPath("$.list[0].league").value("PREMIER LEAGUE"))
            .andExpect(jsonPath("$.list[0].homeTeam").value("Arsenal"))
            .andExpect(jsonPath("$.list[0].awayTeam").value("Chelsea"))
            .andExpect(jsonPath("$.list[0].homeScore").value(3))
            .andExpect(jsonPath("$.list[0].awayScore").value(1))
            .andExpect(jsonPath("$.list[0].status").value("FINISHED"))
            .andExpect(jsonPath("$.list[0].matchTime").value("Jul 5, 2026 20:00"))
            .andExpect(jsonPath("$.list[0].venue").doesNotExist())
            .andExpect(jsonPath("$.list[0].images").doesNotExist())
            .andReturn();

        System.out.println("LIST: " + result.getResponse().getContentAsString());
    }

    @Test
    @Order(2)
    void testInfo() throws Exception {
        if (testMatchId == null) testMatchId = 1L;

        MvcResult result = mockMvc.perform(get("/match/info")
                .param("matchId", String.valueOf(testMatchId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(testMatchId))
            .andExpect(jsonPath("$.league").value("PREMIER LEAGUE"))
            .andExpect(jsonPath("$.homeTeam").value("Arsenal"))
            .andExpect(jsonPath("$.awayTeam").value("Chelsea"))
            .andExpect(jsonPath("$.homeScore").value(3))
            .andExpect(jsonPath("$.awayScore").value(1))
            .andExpect(jsonPath("$.status").value("FINISHED"))
            .andExpect(jsonPath("$.matchTime").value("Jul 5, 2026 20:00"))
            .andExpect(jsonPath("$.venue").value("Emirates Stadium"))
            .andExpect(jsonPath("$.images").isArray())
            .andExpect(jsonPath("$.images", hasSize(2)))
            .andExpect(jsonPath("$.images[0]").value("img1"))
            .andExpect(jsonPath("$.images[1]").value("img2"))
            .andReturn();

        System.out.println("INFO: " + result.getResponse().getContentAsString());
    }

    @Test
    @Order(3)
    void testInfoNotFound() throws Exception {
        mockMvc.perform(get("/match/info")
                .param("matchId", "99999"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(400))
            .andExpect(jsonPath("$.message").value("比赛不存在"));
    }
}
