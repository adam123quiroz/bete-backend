/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.persistence.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "bet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BetEntity.findAll", query = "SELECT b FROM BetEntity b"),
    @NamedQuery(name = "BetEntity.findByIdBet", query = "SELECT b FROM BetEntity b WHERE b.idBet = :idBet"),
    @NamedQuery(name = "BetEntity.findByQuantity", query = "SELECT b FROM BetEntity b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "BetEntity.findByTeam", query = "SELECT b FROM BetEntity b WHERE b.team = :team"),
    @NamedQuery(name = "BetEntity.findByDate", query = "SELECT b FROM BetEntity b WHERE b.date = :date"),
    @NamedQuery(name = "BetEntity.findByStatus", query = "SELECT b FROM BetEntity b WHERE b.status = :status")})
public class BetEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bet")
    private Integer idBet;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "team")
    private int team;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "gambler", referencedColumnName = "id_gambler")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GamblerEntity gambler;
    @JoinColumn(name = "match", referencedColumnName = "id_match")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MatchEntity match;
    @JoinColumn(name = "team_id_team", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TeamEntity teamIdTeam;

    public BetEntity() {
    }

    public BetEntity(Integer idBet) {
        this.idBet = idBet;
    }

    public BetEntity(Integer idBet, int quantity, int team, Date date, int status) {
        this.idBet = idBet;
        this.quantity = quantity;
        this.team = team;
        this.date = date;
        this.status = status;
    }

    public Integer getIdBet() {
        return idBet;
    }

    public void setIdBet(Integer idBet) {
        this.idBet = idBet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public GamblerEntity getGambler() {
        return gambler;
    }

    public void setGambler(GamblerEntity gambler) {
        this.gambler = gambler;
    }

    public MatchEntity getMatch() {
        return match;
    }

    public void setMatch(MatchEntity match) {
        this.match = match;
    }

    public TeamEntity getTeamIdTeam() {
        return teamIdTeam;
    }

    public void setTeamIdTeam(TeamEntity teamIdTeam) {
        this.teamIdTeam = teamIdTeam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBet != null ? idBet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BetEntity)) {
            return false;
        }
        BetEntity other = (BetEntity) object;
        if ((this.idBet == null && other.idBet != null) || (this.idBet != null && !this.idBet.equals(other.idBet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.BetEntity[ idBet=" + idBet + " ]";
    }
    
}
