package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class Team {
    private Integer idTeam;
    private String teamName;
    private String organization;
    private int status;
    private List<TournamentTeam> tournamentTeamList;
    private List<Match> matchList;
    private List<Match> matchList1;
    private List<UserTeam> userTeamList;
    private List<Bet> betList;

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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TournamentTeam> getTournamentTeamList() {
        return tournamentTeamList;
    }

    public void setTournamentTeamList(List<TournamentTeam> tournamentTeamList) {
        this.tournamentTeamList = tournamentTeamList;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public List<Match> getMatchList1() {
        return matchList1;
    }

    public void setMatchList1(List<Match> matchList1) {
        this.matchList1 = matchList1;
    }

    public List<UserTeam> getUserTeamList() {
        return userTeamList;
    }

    public void setUserTeamList(List<UserTeam> userTeamList) {
        this.userTeamList = userTeamList;
    }

    public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
    }
}
