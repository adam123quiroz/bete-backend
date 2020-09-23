/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.persistence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
    @NamedQuery(name = "UserEntity.findByIdUser", query = "SELECT u FROM UserEntity u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
    @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "UserEntity.findByName", query = "SELECT u FROM UserEntity u WHERE u.name = :name"),
    @NamedQuery(name = "UserEntity.findByLastname", query = "SELECT u FROM UserEntity u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "UserEntity.findByCountryCode", query = "SELECT u FROM UserEntity u WHERE u.countryCode = :countryCode"),
    @NamedQuery(name = "UserEntity.findByCellphoneNumber", query = "SELECT u FROM UserEntity u WHERE u.cellphoneNumber = :cellphoneNumber"),
    @NamedQuery(name = "UserEntity.findByIsPlayer", query = "SELECT u FROM UserEntity u WHERE u.isPlayer = :isPlayer"),
    @NamedQuery(name = "UserEntity.findByIsOrganizer", query = "SELECT u FROM UserEntity u WHERE u.isOrganizer = :isOrganizer"),
    @NamedQuery(name = "UserEntity.findByIsGambler", query = "SELECT u FROM UserEntity u WHERE u.isGambler = :isGambler"),
    @NamedQuery(name = "UserEntity.findByPassword", query = "SELECT u FROM UserEntity u WHERE u.password = :password"),
    @NamedQuery(name = "UserEntity.findByStatus", query = "SELECT u FROM UserEntity u WHERE u.status = :status")})
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @Column(name = "country_code")
    private int countryCode;
    @Basic(optional = false)
    @Column(name = "cellphone_number")
    private int cellphoneNumber;
    @Basic(optional = false)
    @Column(name = "is_player")
    private int isPlayer;
    @Basic(optional = false)
    @Column(name = "is_organizer")
    private int isOrganizer;
    @Basic(optional = false)
    @Column(name = "is_gambler")
    private int isGambler;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTeamEntity> userTeamEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<NotificationEntity> notificationEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.LAZY)
    private List<OrganizerEntity> organizerEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.LAZY)
    private List<GamblerEntity> gamblerEntityList;
    @JoinColumn(name = "region", referencedColumnName = "id_region")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RegionEntity region;

    public UserEntity() {
    }

    public UserEntity(Integer idUser) {
        this.idUser = idUser;
    }

    public UserEntity(Integer idUser, String username, String email, String name, String lastname, int countryCode, int cellphoneNumber, int isPlayer, int isOrganizer, int isGambler, String password, int status) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.countryCode = countryCode;
        this.cellphoneNumber = cellphoneNumber;
        this.isPlayer = isPlayer;
        this.isOrganizer = isOrganizer;
        this.isGambler = isGambler;
        this.password = password;
        this.status = status;
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

    @XmlTransient
    public List<UserTeamEntity> getUserTeamEntityList() {
        return userTeamEntityList;
    }

    public void setUserTeamEntityList(List<UserTeamEntity> userTeamEntityList) {
        this.userTeamEntityList = userTeamEntityList;
    }

    @XmlTransient
    public List<NotificationEntity> getNotificationEntityList() {
        return notificationEntityList;
    }

    public void setNotificationEntityList(List<NotificationEntity> notificationEntityList) {
        this.notificationEntityList = notificationEntityList;
    }

    @XmlTransient
    public List<OrganizerEntity> getOrganizerEntityList() {
        return organizerEntityList;
    }

    public void setOrganizerEntityList(List<OrganizerEntity> organizerEntityList) {
        this.organizerEntityList = organizerEntityList;
    }

    @XmlTransient
    public List<ReviewEntity> getReviewEntityList() {
        return reviewEntityList;
    }

    public void setReviewEntityList(List<ReviewEntity> reviewEntityList) {
        this.reviewEntityList = reviewEntityList;
    }

    @XmlTransient
    public List<GamblerEntity> getGamblerEntityList() {
        return gamblerEntityList;
    }

    public void setGamblerEntityList(List<GamblerEntity> gamblerEntityList) {
        this.gamblerEntityList = gamblerEntityList;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.UserEntity[ idUser=" + idUser + " ]";
    }
    
}
