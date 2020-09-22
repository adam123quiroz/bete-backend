/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.domain;

import java.io.Serializable;
import java.util.Set;
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
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findByIdTeam", query = "SELECT t FROM Team t WHERE t.idTeam = :idTeam"),
    @NamedQuery(name = "Team.findByTeamName", query = "SELECT t FROM Team t WHERE t.teamName = :teamName"),
    @NamedQuery(name = "Team.findByOrganization", query = "SELECT t FROM Team t WHERE t.organization = :organization"),
    @NamedQuery(name = "Team.findByStatus", query = "SELECT t FROM Team t WHERE t.status = :status")})
public class Team implements Serializable {

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
    private Set<TournamentTeam> tournamentTeamSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team2", fetch = FetchType.LAZY)
    private Set<Match> matchSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team1", fetch = FetchType.LAZY)
    private Set<Match> matchSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team", fetch = FetchType.LAZY)
    private Set<UserTeam> userTeamSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamIdTeam", fetch = FetchType.LAZY)
    private Set<Bet> betSet;

    public Team() {
    }

    public Team(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public Team(Integer idTeam, String teamName, String organization, int status) {
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
    public Set<TournamentTeam> getTournamentTeamSet() {
        return tournamentTeamSet;
    }

    public void setTournamentTeamSet(Set<TournamentTeam> tournamentTeamSet) {
        this.tournamentTeamSet = tournamentTeamSet;
    }

    @XmlTransient
    public Set<Match> getMatchSet() {
        return matchSet;
    }

    public void setMatchSet(Set<Match> matchSet) {
        this.matchSet = matchSet;
    }

    @XmlTransient
    public Set<Match> getMatchSet1() {
        return matchSet1;
    }

    public void setMatchSet1(Set<Match> matchSet1) {
        this.matchSet1 = matchSet1;
    }

    @XmlTransient
    public Set<UserTeam> getUserTeamSet() {
        return userTeamSet;
    }

    public void setUserTeamSet(Set<UserTeam> userTeamSet) {
        this.userTeamSet = userTeamSet;
    }

    @XmlTransient
    public Set<Bet> getBetSet() {
        return betSet;
    }

    public void setBetSet(Set<Bet> betSet) {
        this.betSet = betSet;
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
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.idTeam == null && other.idTeam != null) || (this.idTeam != null && !this.idTeam.equals(other.idTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.Team[ idTeam=" + idTeam + " ]";
    }
    
}
