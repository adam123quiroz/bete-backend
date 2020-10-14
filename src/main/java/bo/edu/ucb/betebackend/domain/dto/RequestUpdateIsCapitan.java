package bo.edu.ucb.betebackend.domain.dto;

public class RequestUpdateIsCapitan {
    private Integer idUserTeam;
    private Integer isCapitan;

    public RequestUpdateIsCapitan(Integer idUserTeam, Integer isCapitan) {
        this.idUserTeam = idUserTeam;
        this.isCapitan = isCapitan;
    }

    public RequestUpdateIsCapitan() {
    }

    public Integer getIdUserTeam() {
        return idUserTeam;
    }

    public Integer getIsCapitan() {
        return isCapitan;
    }

    public void setIdUserTeam(Integer idUserTeam) {
        this.idUserTeam = idUserTeam;
    }

    public void setIsCapitan(Integer isCapitan) {
        this.isCapitan = isCapitan;
    }
}
