package bo.edu.ucb.betebackend.domain.dto;

public class TeamFormUpdateRequest {
    private String nameTeam;
    private String organizationName;

    public TeamFormUpdateRequest(String nameTeam, String organizationName) {
        this.nameTeam = nameTeam;
        this.organizationName = organizationName;
    }

    public TeamFormUpdateRequest() {
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
