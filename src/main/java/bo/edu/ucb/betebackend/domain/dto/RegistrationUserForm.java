package bo.edu.ucb.betebackend.domain.dto;

import bo.edu.ucb.betebackend.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;

public class RegistrationUserForm {
    @NotNull(message = "username is mandatory")
    private String username;

    @NotNull(message = "password is mandatory")
    private String password;

    @NotNull(message = "email is mandatory")
    private String email;

    @NotNull(message = "name is mandatory")
    private String name;

    @NotNull(message = "lastname is mandatory")
    private String lastname;

    @NotNull(message = "region is mandatory")
    private Integer region;

    @NotNull(message = "countryCode is mandatory")
    private Integer countryCode;

    @NotNull(message = "cellphoneNumber is mandatory")
    private Integer cellphoneNumber;

    public RegistrationUserForm(String username, String password, String email, String name, String lastname, Integer region, Integer countryCode, Integer cellphoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.region = region;
        this.countryCode = countryCode;
        this.cellphoneNumber = cellphoneNumber;
    }

    public RegistrationUserForm() {
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setEmail(email);
        user.setLastname(lastname);
        user.setCountryCode(countryCode);
        user.setCellphoneNumber(cellphoneNumber);
        return user;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(Integer cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegistrationUserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", region=" + region +
                ", countryCode=" + countryCode +
                ", cellphoneNumber=" + cellphoneNumber +
                '}';
    }
}
