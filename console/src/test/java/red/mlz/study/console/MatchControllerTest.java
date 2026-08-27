package red.mlz.study.console;

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

@SpringBootTest(classes = ConsoleApplication.class)
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
    void testCreate() throws Exception {
        Match match = new Match();
        match.setLeague("LA LIGA");
        match.setHomeTeam("Real Madrid");
        match.setAwayTeam("Barcelona");
        match.setHomeScore(2);
        match.setAwayScore(2);
        match.setStatus("FINISHED");
        match.setMatchTime("Aug 10, 2026 21:00");
        match.setVenue("Santiago Bernabeu");
        match.setImages("r1$r2$r3");

        MvcResult result = mockMvc.perform(post("/match/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(match)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").value("操作成功"))
            .andExpect(jsonPath("$.data").value("成功"))
            .andReturn();

        System.out.println("CREATE: " + result.getResponse().getContentAsString());

        Match created = matchService.getAll().get(0);
        testMatchId = created.getId();
    }

    @Test
    @Order(2)
    void testUpdate() throws Exception {
        if (testMatchId == null) testMatchId = 1L;

        Match match = new Match();
        match.setId(testMatchId);
        match.setLeague("LA LIGA");
        match.setHomeTeam("Real Madrid");
        match.setAwayTeam("Barcelona");
        match.setHomeScore(5);
        match.setAwayScore(0);
        match.setStatus("FINISHED");
        match.setMatchTime("Aug 10, 2026 21:00");
        match.setVenue("Santiago Bernabeu");
        match.setImages("u1");

        MvcResult result = mockMvc.perform(post("/match/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(match)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.data").value("成功"))
            .andReturn();

        System.out.println("UPDATE: " + result.getResponse().getContentAsString());

        Match updated = matchService.getById(testMatchId);
        assert updated != null;
        assert updated.getHomeScore() == 5;
        assert updated.getAwayScore() == 0;
        assert "u1".equals(updated.getImages());
    }

    @Test
    @Order(3)
    void testDelete() throws Exception {
        if (testMatchId == null) testMatchId = 1L;

        MvcResult result = mockMvc.perform(post("/match/delete")
                .param("matchId", String.valueOf(testMatchId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.data").value("成功"))
            .andReturn();

        System.out.println("DELETE: " + result.getResponse().getContentAsString());

        Match deleted = matchService.getById(testMatchId);
        assert deleted == null;
    }
}
