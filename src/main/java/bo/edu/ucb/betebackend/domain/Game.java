package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class Game {
    private Integer idGame;
    private String name;
    private List<Tournament> tournamentList;


    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }
}
