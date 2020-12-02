package bo.edu.ucb.betebackend.domain.dto.request;

public class BetRequest {
    private Integer matchId;
    private int quantity;
    private int team;
    private Integer gamblerId;
    private Integer teamIdTeam;

    public BetRequest(int quantity, int team, Integer gamblerId, Integer matchId, Integer teamIdTeam) {
        this.quantity = quantity;
        this.team = team;
        this.gamblerId = gamblerId;
        this.matchId = matchId;
        this.teamIdTeam = teamIdTeam;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public Integer getGamblerId() {
        return gamblerId;
    }

    public void setGamblerId(Integer gamblerId) {
        this.gamblerId = gamblerId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getTeamIdTeam() {
        return teamIdTeam;
    }

    public void setTeamIdTeam(Integer teamIdTeam) {
        this.teamIdTeam = teamIdTeam;
    }
}
