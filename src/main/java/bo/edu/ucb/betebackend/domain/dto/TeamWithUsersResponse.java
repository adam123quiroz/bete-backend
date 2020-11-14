package bo.edu.ucb.betebackend.domain.dto;

import bo.edu.ucb.betebackend.domain.Team;

import java.util.List;

public class TeamWithUsersResponse {
    private Integer idTeam;
    private String name;
    private String organization;
    private List<UserResponse> userResponseList;

    public TeamWithUsersResponse(Integer idTeam, String name, String organization, List<UserResponse> userResponseList) {
        this.idTeam = idTeam;
        this.name = name;
        this.organization = organization;
        this.userResponseList = userResponseList;
    }

    public TeamWithUsersResponse(Team team) {
        this.idTeam = team.getIdTeam();
        this.name = team.getTeamName();
        this.organization = team.getOrganization();
    }

    public TeamWithUsersResponse() {
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<UserResponse> getUserResponseList() {
        return userResponseList;
    }

    public void setUserResponseList(List<UserResponse> userResponseList) {
        this.userResponseList = userResponseList;
    }
}
