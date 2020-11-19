package bo.edu.ucb.betebackend.domain.dto.model;

import bo.edu.ucb.betebackend.domain.UserTeam;

public class TeamModel {
    private Integer idTeam;
    private String nameTeam;
    private String organizationName;
    private Integer isCapitan;

    public TeamModel(String nameTeam, String organizationName) {
        this.nameTeam = nameTeam;
        this.organizationName = organizationName;
    }

    public TeamModel(Integer idTeam, String nameTeam, String organizationName, Integer isCapitan) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.organizationName = organizationName;
        this.isCapitan = isCapitan;
    }

    public static TeamModel apply(UserTeam userTeam) {
        return new TeamModel(
                userTeam.getTeam().getIdTeam(),
                userTeam.getTeam().getTeamName(),
                userTeam.getTeam().getOrganization(),
                userTeam.getIsCaptain());
    }

    public TeamModel() {
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getIsCapitan() {
        return isCapitan;
    }

    public void setIsCapitan(Integer isCapitan) {
        this.isCapitan = isCapitan;
    }
}
