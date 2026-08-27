package red.mlz.study.app.vo;

import java.util.List;

public class MatchListVO {

    private List<MatchItemVO> list;

    public List<MatchItemVO> getList() { return list; }
    public void setList(List<MatchItemVO> list) { this.list = list; }

    public MatchListVO() {}

    public MatchListVO(List<MatchItemVO> list) {
        this.list = list;
    }
}
