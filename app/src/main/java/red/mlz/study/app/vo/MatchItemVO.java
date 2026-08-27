package red.mlz.study.app.vo;

public class MatchItemVO {

    private Long id;
    private String league;
    private String homeTeam;
    private String awayTeam;
    private Integer homeScore;
    private Integer awayScore;
    private String status;
    private String minute;
    private String matchTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLeague() { return league; }
    public void setLeague(String league) { this.league = league; }

    public String getHomeTeam() { return homeTeam; }
    public void setHomeTeam(String homeTeam) { this.homeTeam = homeTeam; }

    public String getAwayTeam() { return awayTeam; }
    public void setAwayTeam(String awayTeam) { this.awayTeam = awayTeam; }

    public Integer getHomeScore() { return homeScore; }
    public void setHomeScore(Integer homeScore) { this.homeScore = homeScore; }

    public Integer getAwayScore() { return awayScore; }
    public void setAwayScore(Integer awayScore) { this.awayScore = awayScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMinute() { return minute; }
    public void setMinute(String minute) { this.minute = minute; }

    public String getMatchTime() { return matchTime; }
    public void setMatchTime(String matchTime) { this.matchTime = matchTime; }
}
