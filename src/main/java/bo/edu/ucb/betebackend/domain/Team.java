package bo.edu.ucb.betebackend.domain;

import java.util.List;
import java.util.Objects;

public class Team {
    private Integer idTeam;
    private String teamName;
    private String organization;
    private int status;
    /*private List<TournamentTeam> tournamentTeamList;
    private List<Match> matchList;
    private List<Match> matchList1;
    private List<Bet> betList;*/
//    private List<UserTeam> userTeamList;

    public Team(Integer idTeam, String teamName, String organization, int status) {
        this.idTeam = idTeam;
        this.teamName = teamName;
        this.organization = organization;
        this.status = status;
    }

    public Team() {
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

    /* public List<TournamentTeam> getTournamentTeamList() {
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


    public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
    }*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return status == team.status &&
                Objects.equals(idTeam, team.idTeam) &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(organization, team.organization);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                '}';
    }

    //    public List<UserTeam> getUserTeamList() {
//        return userTeamList;
//    }
//
//    public void setUserTeamList(List<UserTeam> userTeamList) {
//        this.userTeamList = userTeamList;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(idTeam, teamName, organization, status);
//    }
}
