package bo.edu.ucb.betebackend.domain.dto;

import java.util.List;

public class TeamAndUserByCapitanResponse {
    private Integer idTeam;
    private String nameTeam;
    private String organization;
    private List<UserCapitanResponse> capitanResponseList;

    public TeamAndUserByCapitanResponse(Integer idTeam, String nameTeam, String organization, List<UserCapitanResponse> capitanResponseList) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.organization = organization;
        this.capitanResponseList = capitanResponseList;
    }

    public TeamAndUserByCapitanResponse() {
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<UserCapitanResponse> getCapitanResponseList() {
        return capitanResponseList;
    }

    public void setCapitanResponseList(List<UserCapitanResponse> capitanResponseList) {
        this.capitanResponseList = capitanResponseList;
    }
}
