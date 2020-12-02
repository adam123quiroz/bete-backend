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
