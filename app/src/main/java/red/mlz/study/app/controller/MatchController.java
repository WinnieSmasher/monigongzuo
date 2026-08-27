package red.mlz.study.app.controller;

import red.mlz.study.app.vo.MatchInfoVO;
import red.mlz.study.app.vo.MatchItemVO;
import red.mlz.study.app.vo.MatchListVO;
import red.mlz.study.module.entity.Match;
import red.mlz.study.module.service.MatchService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Resource
    private MatchService matchService;

    @GetMapping("/list")
    public MatchListVO list() {
        List<Match> matches = matchService.getAll();
        List<MatchItemVO> items = new ArrayList<>();
        for (Match m : matches) {
            MatchItemVO vo = new MatchItemVO();
            vo.setId(m.getId());
            vo.setLeague(m.getLeague());
            vo.setHomeTeam(m.getHomeTeam());
            vo.setAwayTeam(m.getAwayTeam());
            vo.setHomeScore(m.getHomeScore());
            vo.setAwayScore(m.getAwayScore());
            vo.setStatus(m.getStatus());
            vo.setMinute(m.getMinute());
            vo.setMatchTime(m.getMatchTime());
            items.add(vo);
        }
        return new MatchListVO(items);
    }

    @GetMapping("/info")
    public MatchInfoVO info(@RequestParam("matchId") Long matchId) {
        Match m = matchService.getById(matchId);
        if (m == null) {
            throw new IllegalArgumentException("比赛不存在");
        }
        MatchInfoVO vo = new MatchInfoVO();
        vo.setId(m.getId());
        vo.setLeague(m.getLeague());
        vo.setHomeTeam(m.getHomeTeam());
        vo.setAwayTeam(m.getAwayTeam());
        vo.setHomeScore(m.getHomeScore());
        vo.setAwayScore(m.getAwayScore());
        vo.setStatus(m.getStatus());
        vo.setMinute(m.getMinute());
        vo.setMatchTime(m.getMatchTime());
        vo.setVenue(m.getVenue());
        vo.setImages(splitImages(m.getImages()));
        return vo;
    }

    private List<String> splitImages(String images) {
        if (images == null || images.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(images.split("\\$"));
    }
}
