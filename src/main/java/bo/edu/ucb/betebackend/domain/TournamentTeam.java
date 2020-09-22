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
@Table(name = "tournament_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TournamentTeam.findAll", query = "SELECT t FROM TournamentTeam t"),
    @NamedQuery(name = "TournamentTeam.findByIdTournamentTeam", query = "SELECT t FROM TournamentTeam t WHERE t.idTournamentTeam = :idTournamentTeam"),
    @NamedQuery(name = "TournamentTeam.findByPhase", query = "SELECT t FROM TournamentTeam t WHERE t.phase = :phase")})
public class TournamentTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tournament_team")
    private Integer idTournamentTeam;
    @Basic(optional = false)
    @Column(name = "phase")
    private int phase;
    @JoinColumn(name = "team", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Team team;
    @JoinColumn(name = "tournament", referencedColumnName = "id_tournament")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tournament tournament;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", fetch = FetchType.LAZY)
    private Set<Match> matchSet;

    public TournamentTeam() {
    }

    public TournamentTeam(Integer idTournamentTeam) {
        this.idTournamentTeam = idTournamentTeam;
    }

    public TournamentTeam(Integer idTournamentTeam, int phase) {
        this.idTournamentTeam = idTournamentTeam;
        this.phase = phase;
    }

    public Integer getIdTournamentTeam() {
        return idTournamentTeam;
    }

    public void setIdTournamentTeam(Integer idTournamentTeam) {
        this.idTournamentTeam = idTournamentTeam;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @XmlTransient
    public Set<Match> getMatchSet() {
        return matchSet;
    }

    public void setMatchSet(Set<Match> matchSet) {
        this.matchSet = matchSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTournamentTeam != null ? idTournamentTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TournamentTeam)) {
            return false;
        }
        TournamentTeam other = (TournamentTeam) object;
        if ((this.idTournamentTeam == null && other.idTournamentTeam != null) || (this.idTournamentTeam != null && !this.idTournamentTeam.equals(other.idTournamentTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.TournamentTeam[ idTournamentTeam=" + idTournamentTeam + " ]";
    }
    
}
