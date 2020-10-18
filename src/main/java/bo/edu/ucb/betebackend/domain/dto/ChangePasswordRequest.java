package bo.edu.ucb.betebackend.domain.dto;

import javax.validation.constraints.NotNull;

public class ChangePasswordRequest {
    @NotNull(message = "oldPassword is mandatory")
    private String oldPassword;
    @NotNull(message = "newPassword is mandatory")
    private String newPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePasswordRequest() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
