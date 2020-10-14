package bo.edu.ucb.betebackend.domain.dto;

public class ResponseUserNotAnswer {
    private Integer idInvitation;
    private String teamName;

    public ResponseUserNotAnswer(Integer idInvitation, String teamName) {
        this.idInvitation = idInvitation;
        this.teamName = teamName;
    }

    public ResponseUserNotAnswer() {
    }

    public Integer getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(Integer idInvitation) {
        this.idInvitation = idInvitation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
