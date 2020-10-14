package bo.edu.ucb.betebackend.domain.dto;

import bo.edu.ucb.betebackend.domain.Team;

import java.util.List;

public class TeamUserRequest {
    private Team team;
    private List<Integer> idsUserList;
    private Integer isCapitan;

    public TeamUserRequest(Team team, List<Integer> idsUserList, Integer isCapitan) {
        this.team = team;
        this.idsUserList = idsUserList;
        this.isCapitan = isCapitan;
    }

    public TeamUserRequest() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Integer> getIdsUserList() {
        return idsUserList;
    }

    public void setIdsUserList(List<Integer> idsUserList) {
        this.idsUserList = idsUserList;
    }

    public Integer getIsCapitan() {
        return isCapitan;
    }

    public void setIsCapitan(Integer isCapitan) {
        this.isCapitan = isCapitan;
    }
}
