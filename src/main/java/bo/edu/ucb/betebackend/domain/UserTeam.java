package bo.edu.ucb.betebackend.domain;

public class UserTeam {
    private Integer idUserTeam;
    private int isCaptain;
    private Team team;
    private User user;
    private Integer status;

    public UserTeam(Integer idUserTeam, int isCaptain, Team team, User user, Integer status) {
        this.idUserTeam = idUserTeam;
        this.isCaptain = isCaptain;
        this.team = team;
        this.user = user;
        this.status = status;
    }

    public UserTeam() {
    }

    public Integer getIdUserTeam() {
        return idUserTeam;
    }

    public void setIdUserTeam(Integer idUserTeam) {
        this.idUserTeam = idUserTeam;
    }

    public int getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(int isCaptain) {
        this.isCaptain = isCaptain;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
