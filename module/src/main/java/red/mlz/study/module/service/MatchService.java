package red.mlz.study.module.service;

import org.springframework.stereotype.Service;
import red.mlz.study.module.entity.Match;
import red.mlz.study.module.mapper.MatchMapper;

import jakarta.annotation.Resource;
import java.util.List;

@Service
public class MatchService {

    @Resource
    private MatchMapper matchMapper;

    public List<Match> getAll() {
        return matchMapper.selectAll();
    }

    public Match getById(Long id) {
        return matchMapper.selectById(id);
    }

    public int create(Match match) {
        int now = (int) (System.currentTimeMillis() / 1000);
        match.setCreateTime(now);
        match.setUpdateTime(now);
        match.setIsDeleted(0);
        return matchMapper.insert(match);
    }

    public int update(Match match) {
        match.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return matchMapper.update(match);
    }

    public int delete(Long id) {
        return matchMapper.deleteById(id, (int) (System.currentTimeMillis() / 1000));
    }
}
