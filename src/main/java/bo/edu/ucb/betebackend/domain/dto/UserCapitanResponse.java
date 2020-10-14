package bo.edu.ucb.betebackend.domain.dto;

public class UserCapitanResponse {
    private Integer idUser;
    private String username;
    private Integer isCapitan;

    public UserCapitanResponse(Integer idUser, String username, Integer isCapitan) {
        this.idUser = idUser;
        this.username = username;
        this.isCapitan = isCapitan;
    }

    public UserCapitanResponse() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIsCapitan() {
        return isCapitan;
    }

    public void setIsCapitan(Integer isCapitan) {
        this.isCapitan = isCapitan;
    }
}
