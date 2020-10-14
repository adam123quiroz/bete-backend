package bo.edu.ucb.betebackend.domain.dto;

public class ChangeCaptainRequest {
    private Integer idTeam;
    private Integer idUserOldCaptainInteger;
    private Integer idUserNewCaptainInteger;

    public ChangeCaptainRequest(Integer idTeam, Integer idUserOldCaptainInteger, Integer idUserNewCaptainInteger) {
        this.idTeam = idTeam;
        this.idUserOldCaptainInteger = idUserOldCaptainInteger;
        this.idUserNewCaptainInteger = idUserNewCaptainInteger;
    }

    public ChangeCaptainRequest() {
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public Integer getIdUserOldCaptainInteger() {
        return idUserOldCaptainInteger;
    }

    public void setIdUserOldCaptainInteger(Integer idUserOldCaptainInteger) {
        this.idUserOldCaptainInteger = idUserOldCaptainInteger;
    }

    public Integer getIdUserNewCaptainInteger() {
        return idUserNewCaptainInteger;
    }

    public void setIdUserNewCaptainInteger(Integer idUserNewCaptainInteger) {
        this.idUserNewCaptainInteger = idUserNewCaptainInteger;
    }
}
