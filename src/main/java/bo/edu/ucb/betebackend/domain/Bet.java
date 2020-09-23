package bo.edu.ucb.betebackend.domain;

import java.util.Date;

public class Bet {
    private Integer idBet;
    private int quantity;
    private int team;
    private Date date;
    private Gambler gambler;
    private Match match;
    private Team teamIdTeam;

    public Integer getIdBet() {
        return idBet;
    }

    public void setIdBet(Integer idBet) {
        this.idBet = idBet;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Gambler getGambler() {
        return gambler;
    }

    public void setGambler(Gambler gambler) {
        this.gambler = gambler;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeamIdTeam() {
        return teamIdTeam;
    }

    public void setTeamIdTeam(Team teamIdTeam) {
        this.teamIdTeam = teamIdTeam;
    }
}
