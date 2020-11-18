package bo.edu.ucb.betebackend.domain.dto.model;

public class TeamModel {
    private Integer idTeam;
    private String nameTeam;
    private String organizationName;

    public TeamModel(String nameTeam, String organizationName) {
        this.nameTeam = nameTeam;
        this.organizationName = organizationName;
    }

    public TeamModel(Integer idTeam, String nameTeam, String organizationName) {
        this.idTeam = idTeam;
        this.nameTeam = nameTeam;
        this.organizationName = organizationName;
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
}
