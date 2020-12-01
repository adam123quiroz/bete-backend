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
@Table(name = "tournament_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TournamentTeamEntity.findAll", query = "SELECT t FROM TournamentTeamEntity t"),
    @NamedQuery(name = "TournamentTeamEntity.findByIdTournamentTeam", query = "SELECT t FROM TournamentTeamEntity t WHERE t.idTournamentTeam = :idTournamentTeam"),
    @NamedQuery(name = "TournamentTeamEntity.findByPhase", query = "SELECT t FROM TournamentTeamEntity t WHERE t.phase = :phase"),
    @NamedQuery(name = "TournamentTeamEntity.findByStatus", query = "SELECT t FROM TournamentTeamEntity t WHERE t.status = :status")})
public class TournamentTeamEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tournament_team")
    private Integer idTournamentTeam;
    @Basic(optional = false)
    @Column(name = "phase")
    private int phase;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "team", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TeamEntity team;
    @JoinColumn(name = "tournament", referencedColumnName = "id_tournament")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TournamentEntity tournament;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", fetch = FetchType.LAZY)
    private List<MatchEntity> matchEntityList;

    public TournamentTeamEntity() {
    }

    public TournamentTeamEntity(Integer idTournamentTeam) {
        this.idTournamentTeam = idTournamentTeam;
    }

    public TournamentTeamEntity(Integer idTournamentTeam, int phase, int status) {
        this.idTournamentTeam = idTournamentTeam;
        this.phase = phase;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public TournamentEntity getTournament() {
        return tournament;
    }

    public void setTournament(TournamentEntity tournament) {
        this.tournament = tournament;
    }

    @XmlTransient
    public List<MatchEntity> getMatchEntityList() {
        return matchEntityList;
    }

    public void setMatchEntityList(List<MatchEntity> matchEntityList) {
        this.matchEntityList = matchEntityList;
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
        if (!(object instanceof TournamentTeamEntity)) {
            return false;
        }
        TournamentTeamEntity other = (TournamentTeamEntity) object;
        if ((this.idTournamentTeam == null && other.idTournamentTeam != null) || (this.idTournamentTeam != null && !this.idTournamentTeam.equals(other.idTournamentTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.TournamentTeamEntity[ idTournamentTeam=" + idTournamentTeam + " ]";
    }

    @XmlTransient
    public List<MatchEntity> getMatchList() {
        return matchEntityList;
    }

    public void setMatchList(List<MatchEntity> matchEntityList) {
        this.matchEntityList = matchEntityList;
    }
    
}
