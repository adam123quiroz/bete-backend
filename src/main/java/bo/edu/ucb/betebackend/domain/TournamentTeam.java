package bo.edu.ucb.betebackend.domain;

public class TournamentTeam {
    private Integer idTournamentTeam;
    private int phase;
    private Team team;
    private Tournament tournament;
    private Integer status;

    public TournamentTeam() {
    }

    public TournamentTeam(Integer idTournamentTeam, int phase, Team team, Tournament tournament, Integer status) {
        this.idTournamentTeam = idTournamentTeam;
        this.phase = phase;
        this.team = team;
        this.tournament = tournament;
        this.status = status;
    }

    public Integer getIdTournamentTeam() {
        return idTournamentTeam;
    }

    public void setIdTournamentTeam(Integer idTournamentTeam) {
        this.idTournamentTeam = idTournamentTeam;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
