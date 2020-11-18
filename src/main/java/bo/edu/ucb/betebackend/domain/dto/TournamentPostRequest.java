package bo.edu.ucb.betebackend.domain.dto;

import javax.validation.constraints.NotNull;

public class TournamentPostRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
    @NotNull
    private Integer idUser;
    @NotNull
    private Integer idGame;

    public TournamentPostRequest(@NotNull String name, @NotNull String description, @NotNull String startDate, @NotNull String endDate, @NotNull Integer idUser, @NotNull Integer idGame) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idUser = idUser;
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }
}
