package bo.edu.ucb.betebackend.domain;

import bo.edu.ucb.betebackend.domain.dto.request.TournamentPostRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tournament {
    private Integer idTournament;
    private String name;
    private String description;
    private int status;
    private Date startDate;
    private Date endDate;
    private int isFinished;
//    private List<TournamentTeam> tournamentTeamList;
    private Game game;
    private Organizer organizer;

    public Tournament(Integer idTournament, String name, String description, int status, Date startDate, Date endDate, int isFinished, Game game, Organizer organizer) {
        this.idTournament = idTournament;
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFinished = isFinished;
        this.game = game;
        this.organizer = organizer;
    }

    public Tournament() {
    }

    public Tournament(String name, String description, int status, Date startDate, Date endDate, int isFinished, Game game, Organizer organizer) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFinished = isFinished;
        this.game = game;
        this.organizer = organizer;
    }

    public Tournament(TournamentPostRequest request) throws ParseException {
        this.name = request.getName();
        this.description = request.getDescription();
        this.startDate = getDate(request.getStartDate());
        this.endDate = getDate(request.getEndDate());
        this.status = 1;
    }

    private Date getDate(String startDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return format.parse(startDate);
    }

    public Integer getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(Integer idTournament) {
        this.idTournament = idTournament;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

/*    public List<TournamentTeam> getTournamentTeamList() {
        return tournamentTeamList;
    }

    public void setTournamentTeamList(List<TournamentTeam> tournamentTeamList) {
        this.tournamentTeamList = tournamentTeamList;
    }*/

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}
