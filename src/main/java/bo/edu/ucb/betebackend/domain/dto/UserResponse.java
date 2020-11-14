package bo.edu.ucb.betebackend.domain.dto;

import bo.edu.ucb.betebackend.domain.User;

public class UserResponse {
    private Integer idUser;
    private String username;
    private Integer isCaptain;

    public UserResponse(Integer idUser, String username, Integer isCaptain) {
        this.idUser = idUser;
        this.username = username;
        this.isCaptain = isCaptain;
    }

    public UserResponse(User user, Integer isCaptain) {
        this.idUser = user.getIdUser();
        this.username = user.getUsername();
        this.isCaptain = isCaptain;
    }

    public UserResponse() {
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

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }
}
