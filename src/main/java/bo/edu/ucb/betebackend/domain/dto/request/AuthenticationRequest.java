package bo.edu.ucb.betebackend.domain.dto.request;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest {
    @NotNull(message = "username is mandatory")
    private String username;
    @NotNull(message = "password is mandatory")
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
