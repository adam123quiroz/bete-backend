package bo.edu.ucb.betebackend.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Match {
    private Integer idMatch;
    private Integer scoreTeam1;
    private Integer scoreTeam2;
    private Date date;
    private Date time;
    private int isFinished;
    private String linkStr;
    private int status;
    private Team team2;
    private Team team1;
    private TournamentTeam tournament;
//    private List<Bet> betList;


    public Match(Integer idMatch, Integer scoreTeam1, Integer scoreTeam2, Date date, Date time, int isFinished, String linkStr, int status, Team team2, Team team1, TournamentTeam tournament) {
        this.idMatch = idMatch;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.date = date;
        this.time = time;
        this.isFinished = isFinished;
        this.linkStr = linkStr;
        this.status = status;
        this.team2 = team2;
        this.team1 = team1;
        this.tournament = tournament;
    }

    public Match() {
    }

    public Match(Team team2, Team team1) {
        this.team2 = team2;
        this.team1 = team1;
    }

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public String getLinkStr() {
        return linkStr;
    }

    public void setLinkStr(String linkStr) {
        this.linkStr = linkStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public TournamentTeam getTournament() {
        return tournament;
    }

    public void setTournament(TournamentTeam tournament) {
        this.tournament = tournament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return isFinished == match.isFinished &&
                status == match.status &&
                Objects.equals(idMatch, match.idMatch) &&
                Objects.equals(scoreTeam1, match.scoreTeam1) &&
                Objects.equals(scoreTeam2, match.scoreTeam2) &&
                Objects.equals(date, match.date) &&
                Objects.equals(time, match.time) &&
                Objects.equals(linkStr, match.linkStr) &&
                Objects.equals(team2, match.team2) &&
                Objects.equals(team1, match.team1) &&
                Objects.equals(tournament, match.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatch, scoreTeam1, scoreTeam2, date, time, isFinished, linkStr, status, team2, team1, tournament);
    }

    @Override
    public String toString() {
        return "Match{" +
                "team2=" + team2 +
                ", team1=" + team1 +
                '}';
    }

    /*    public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
    }*/
}
