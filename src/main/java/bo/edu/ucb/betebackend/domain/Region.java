package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class Region {
    private Integer idRegion;
    private String regionName;
//    List<User> userList;


    public Region(Integer idRegion, String regionName) {
        this.idRegion = idRegion;
        this.regionName = regionName;
    }

    public Region() {
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
/*
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }*/
}
