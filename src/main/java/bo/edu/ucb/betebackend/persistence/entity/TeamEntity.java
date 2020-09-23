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
@Table(name = "team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeamEntity.findAll", query = "SELECT t FROM TeamEntity t"),
    @NamedQuery(name = "TeamEntity.findByIdTeam", query = "SELECT t FROM TeamEntity t WHERE t.idTeam = :idTeam"),
    @NamedQuery(name = "TeamEntity.findByTeamName", query = "SELECT t FROM TeamEntity t WHERE t.teamName = :teamName"),
    @NamedQuery(name = "TeamEntity.findByOrganization", query = "SELECT t FROM TeamEntity t WHERE t.organization = :organization"),
    @NamedQuery(name = "TeamEntity.findByStatus", query = "SELECT t FROM TeamEntity t WHERE t.status = :status")})
public class TeamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_team")
    private Integer idTeam;
    @Basic(optional = false)
    @Column(name = "team_name")
    private String teamName;
    @Basic(optional = false)
    @Column(name = "organization")
    private String organization;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private List<TournamentTeamEntity> tournamentTeamEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team2", fetch = FetchType.LAZY)
    private List<MatchEntity> matchEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team1", fetch = FetchType.LAZY)
    private List<MatchEntity> matchEntityList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private List<UserTeamEntity> userTeamEntityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamIdTeam", fetch = FetchType.LAZY)
    private List<BetEntity> betEntityList;

    public TeamEntity() {
    }

    public TeamEntity(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public TeamEntity(Integer idTeam, String teamName, String organization, int status) {
        this.idTeam = idTeam;
        this.teamName = teamName;
        this.organization = organization;
        this.status = status;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<TournamentTeamEntity> getTournamentTeamEntityList() {
        return tournamentTeamEntityList;
    }

    public void setTournamentTeamEntityList(List<TournamentTeamEntity> tournamentTeamEntityList) {
        this.tournamentTeamEntityList = tournamentTeamEntityList;
    }

    @XmlTransient
    public List<MatchEntity> getMatchEntityList() {
        return matchEntityList;
    }

    public void setMatchEntityList(List<MatchEntity> matchEntityList) {
        this.matchEntityList = matchEntityList;
    }

    @XmlTransient
    public List<MatchEntity> getMatchEntityList1() {
        return matchEntityList1;
    }

    public void setMatchEntityList1(List<MatchEntity> matchEntityList1) {
        this.matchEntityList1 = matchEntityList1;
    }

    @XmlTransient
    public List<UserTeamEntity> getUserTeamEntityList() {
        return userTeamEntityList;
    }

    public void setUserTeamEntityList(List<UserTeamEntity> userTeamEntityList) {
        this.userTeamEntityList = userTeamEntityList;
    }

    @XmlTransient
    public List<BetEntity> getBetEntityList() {
        return betEntityList;
    }

    public void setBetEntityList(List<BetEntity> betEntityList) {
        this.betEntityList = betEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeam != null ? idTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamEntity)) {
            return false;
        }
        TeamEntity other = (TeamEntity) object;
        if ((this.idTeam == null && other.idTeam != null) || (this.idTeam != null && !this.idTeam.equals(other.idTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.TeamEntity[ idTeam=" + idTeam + " ]";
    }
    
}
