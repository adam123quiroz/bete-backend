package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class Game {
    private Integer idGame;
    private String name;
    private int status;

//    private List<Tournament> tournamentList;


    public Game(Integer idGame, String name, int status) {
        this.idGame = idGame;
        this.name = name;
        this.status = status;
    }

    public Game() {
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    /*    public List<Tournament> getTournamentList() {
        return tournamentList;    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }*/
}
