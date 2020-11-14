package bo.edu.ucb.betebackend.domain.dto;

import java.util.Date;

public class TournamentRequestUpdate {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer game;
    private Integer organizer;

    public TournamentRequestUpdate(String name, String description, Date startDate, Date endDate, Integer game, Integer organizer) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.game = game;
        this.organizer = organizer;
    }

    public TournamentRequestUpdate() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getGame() {
        return game;
    }

    public void setGame(Integer game) {
        this.game = game;
    }

    public Integer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Integer organizer) {
        this.organizer = organizer;
    }
}