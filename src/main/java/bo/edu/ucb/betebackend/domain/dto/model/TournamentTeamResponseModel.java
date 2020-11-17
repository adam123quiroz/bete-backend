package bo.edu.ucb.betebackend.domain.dto.model;

import bo.edu.ucb.betebackend.domain.TournamentTeam;

public class TournamentTeamResponseModel {
    private Integer idTournamentTeam;
    private Integer idTeam;
    private String teamName;

    public TournamentTeamResponseModel(Integer idTournamentTeam, Integer idTeam, String teamName) {
        this.idTournamentTeam = idTournamentTeam;
        this.idTeam = idTeam;
        this.teamName = teamName;
    }

    public TournamentTeamResponseModel(TournamentTeam tournamentTeam) {
        this.idTournamentTeam = tournamentTeam.getIdTournamentTeam();
        this.idTeam = tournamentTeam.getTeam().getIdTeam();
        this.teamName = tournamentTeam.getTeam().getTeamName();
    }

    public TournamentTeamResponseModel() {
    }

    public Integer getIdTournamentTeam() {
        return idTournamentTeam;
    }

    public void setIdTournamentTeam(Integer idTournamentTeam) {
        this.idTournamentTeam = idTournamentTeam;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
