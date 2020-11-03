package bo.edu.ucb.betebackend.domain.dto;

import bo.edu.ucb.betebackend.domain.Team;

import java.util.List;

public class TeamUserRequest {
    private String teamName;
    private String organization;
    private List<Integer> idsUserList;
    private Integer creator;

    public TeamUserRequest(String teamName, String organization, List<Integer> idsUserList, Integer creator) {
        this.teamName = teamName;
        this.organization = organization;
        this.idsUserList = idsUserList;
        this.creator = creator;
    }

    public TeamUserRequest() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<Integer> getIdsUserList() {
        return idsUserList;
    }

    public void setIdsUserList(List<Integer> idsUserList) {
        this.idsUserList = idsUserList;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}
