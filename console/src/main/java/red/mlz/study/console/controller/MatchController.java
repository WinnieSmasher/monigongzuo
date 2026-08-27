package red.mlz.study.console.controller;

import red.mlz.study.module.common.Result;
import red.mlz.study.module.entity.Match;
import red.mlz.study.module.service.MatchService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Resource
    private MatchService matchService;

    @PostMapping("/create")
    public Result<String> create(@RequestBody Match match) {
        int rows = matchService.create(match);
        return rows == 1 ? Result.success("成功") : Result.fail("失败");
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody Match match) {
        int rows = matchService.update(match);
        return rows == 1 ? Result.success("成功") : Result.fail("失败");
    }

    @PostMapping("/delete")
    public Result<String> delete(@RequestParam("matchId") Long matchId) {
        int rows = matchService.delete(matchId);
        return rows == 1 ? Result.success("成功") : Result.fail("失败");
    }
}
