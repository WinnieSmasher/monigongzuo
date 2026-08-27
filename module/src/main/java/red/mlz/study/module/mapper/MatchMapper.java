package red.mlz.study.module.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import red.mlz.study.module.entity.Match;

import java.util.List;

@Mapper
public interface MatchMapper {

    List<Match> selectAll();

    Match selectById(@Param("id") Long id);

    int insert(@Param("match") Match match);

    int update(@Param("match") Match match);

    @Update("update `match` set is_deleted=1, update_time=#{time} where id=#{id} limit 1")
    int deleteById(@Param("id") Long id, @Param("time") Integer time);
}
