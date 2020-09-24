package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class User {
    private Integer idUser;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private int countryCode;
    private int cellphoneNumber;
    private int isPlayer;
    private int isOrganizer;
    private int isGambler;
    private String password;
    private int status;
    private List<UserTeam> userTeamList;
    private List<Notification> notificationList;
    private List<Organizer> organizerList;
    private List<Review> reviewList;
    private List<Gambler> gamblerList;
    private Region region;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(int cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public int getIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(int isPlayer) {
        this.isPlayer = isPlayer;
    }

    public int getIsOrganizer() {
        return isOrganizer;
    }

    public void setIsOrganizer(int isOrganizer) {
        this.isOrganizer = isOrganizer;
    }

    public int getIsGambler() {
        return isGambler;
    }

    public void setIsGambler(int isGambler) {
        this.isGambler = isGambler;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<UserTeam> getUserTeamList() {
        return userTeamList;
    }

    public void setUserTeamList(List<UserTeam> userTeamList) {
        this.userTeamList = userTeamList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<Organizer> getOrganizerList() {
        return organizerList;
    }

    public void setOrganizerList(List<Organizer> organizerList) {
        this.organizerList = organizerList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Gambler> getGamblerList() {
        return gamblerList;
    }

    public void setGamblerList(List<Gambler> gamblerList) {
        this.gamblerList = gamblerList;
    }
}
