/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.domain;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "user_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTeam.findAll", query = "SELECT u FROM UserTeam u"),
    @NamedQuery(name = "UserTeam.findByIdUserTeam", query = "SELECT u FROM UserTeam u WHERE u.idUserTeam = :idUserTeam"),
    @NamedQuery(name = "UserTeam.findByIsCaptain", query = "SELECT u FROM UserTeam u WHERE u.isCaptain = :isCaptain")})
public class UserTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_team")
    private Integer idUserTeam;
    @Basic(optional = false)
    @Column(name = "is_captain")
    private int isCaptain;
    @JoinColumn(name = "team", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Team team;
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public UserTeam() {
    }

    public UserTeam(Integer idUserTeam) {
        this.idUserTeam = idUserTeam;
    }

    public UserTeam(Integer idUserTeam, int isCaptain) {
        this.idUserTeam = idUserTeam;
        this.isCaptain = isCaptain;
    }

    public Integer getIdUserTeam() {
        return idUserTeam;
    }

    public void setIdUserTeam(Integer idUserTeam) {
        this.idUserTeam = idUserTeam;
    }

    public int getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(int isCaptain) {
        this.isCaptain = isCaptain;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserTeam != null ? idUserTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTeam)) {
            return false;
        }
        UserTeam other = (UserTeam) object;
        if ((this.idUserTeam == null && other.idUserTeam != null) || (this.idUserTeam != null && !this.idUserTeam.equals(other.idUserTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.UserTeam[ idUserTeam=" + idUserTeam + " ]";
    }
    
}
