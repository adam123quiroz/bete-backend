package bo.edu.ucb.betebackend.domain.dto.response;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.Tournament;

public class MatchExpectResponse {
    private Integer idMatch;
    private Tournament tournament;
    private Team team1;
    private Team team2;
    private Integer scoreTeam1;
    private Integer scoreTeam2;
    private Double expectTeam1;
    private Double expectTeam2;
    private Integer isFinished;

    public MatchExpectResponse(Integer idMatch, Tournament tournament, Team team1, Team team2, Integer scoreTeam1,
                               Integer scoreTeam2, Double expectTeam1, Double expectTeam2, Integer isFinished) {
        this.idMatch = idMatch;
        this.tournament = tournament;
        this.team1 = team1;
        this.team2 = team2;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.expectTeam1 = expectTeam1;
        this.expectTeam2 = expectTeam2;
        this.isFinished = isFinished;
    }

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Integer getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(Integer scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public Integer getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(Integer scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public Double getExpectTeam1() {
        return expectTeam1;
    }

    public void setExpectTeam1(Double expectTeam1) {
        this.expectTeam1 = expectTeam1;
    }

    public Double getExpectTeam2() {
        return expectTeam2;
    }

    public void setExpectTeam2(Double expectTeam2) {
        this.expectTeam2 = expectTeam2;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    @Override
    public String toString() {
        return "MatchExpectResponse{" +
                "idMatch=" + idMatch +
                ", tournament=" + tournament +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", expectTeam1=" + expectTeam1 +
                ", expectTeam2=" + expectTeam2 +
                ", isFinished=" + isFinished +
                '}';
    }
}
