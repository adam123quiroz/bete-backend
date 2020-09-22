/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "match")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Match.findAll", query = "SELECT m FROM Match m"),
    @NamedQuery(name = "Match.findByIdMatch", query = "SELECT m FROM Match m WHERE m.idMatch = :idMatch"),
    @NamedQuery(name = "Match.findByScoreTeam1", query = "SELECT m FROM Match m WHERE m.scoreTeam1 = :scoreTeam1"),
    @NamedQuery(name = "Match.findByScoreTeam2", query = "SELECT m FROM Match m WHERE m.scoreTeam2 = :scoreTeam2"),
    @NamedQuery(name = "Match.findByDate", query = "SELECT m FROM Match m WHERE m.date = :date"),
    @NamedQuery(name = "Match.findByTime", query = "SELECT m FROM Match m WHERE m.time = :time"),
    @NamedQuery(name = "Match.findByIsFinished", query = "SELECT m FROM Match m WHERE m.isFinished = :isFinished"),
    @NamedQuery(name = "Match.findByLinkStr", query = "SELECT m FROM Match m WHERE m.linkStr = :linkStr"),
    @NamedQuery(name = "Match.findByStatus", query = "SELECT m FROM Match m WHERE m.status = :status")})
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_match")
    private Integer idMatch;
    @Column(name = "score_team_1")
    private Integer scoreTeam1;
    @Column(name = "score_team_2")
    private Integer scoreTeam2;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Basic(optional = false)
    @Column(name = "is_finished")
    private int isFinished;
    @Basic(optional = false)
    @Column(name = "link_str")
    private String linkStr;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "team2", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Team team2;
    @JoinColumn(name = "team1", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Team team1;
    @JoinColumn(name = "tournament", referencedColumnName = "id_tournament_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TournamentTeam tournament;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match", fetch = FetchType.LAZY)
    private Set<Bet> betSet;

    public Match() {
    }

    public Match(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Match(Integer idMatch, int isFinished, String linkStr, int status) {
        this.idMatch = idMatch;
        this.isFinished = isFinished;
        this.linkStr = linkStr;
        this.status = status;
    }

    public Integer getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Integer idMatch) {
        this.idMatch = idMatch;
    }

    public Integer getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(Integer scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public Integer getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(Integer scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public String getLinkStr() {
        return linkStr;
    }

    public void setLinkStr(String linkStr) {
        this.linkStr = linkStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public TournamentTeam getTournament() {
        return tournament;
    }

    public void setTournament(TournamentTeam tournament) {
        this.tournament = tournament;
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
        hash += (idMatch != null ? idMatch.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match) object;
        if ((this.idMatch == null && other.idMatch != null) || (this.idMatch != null && !this.idMatch.equals(other.idMatch))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.Match[ idMatch=" + idMatch + " ]";
    }
    
}
