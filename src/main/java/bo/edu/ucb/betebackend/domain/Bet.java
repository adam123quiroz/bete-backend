/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.domain;

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
    @NamedQuery(name = "Bet.findAll", query = "SELECT b FROM Bet b"),
    @NamedQuery(name = "Bet.findByIdBet", query = "SELECT b FROM Bet b WHERE b.idBet = :idBet"),
    @NamedQuery(name = "Bet.findByQuantity", query = "SELECT b FROM Bet b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "Bet.findByTeam", query = "SELECT b FROM Bet b WHERE b.team = :team"),
    @NamedQuery(name = "Bet.findByDate", query = "SELECT b FROM Bet b WHERE b.date = :date")})
public class Bet implements Serializable {

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
    @JoinColumn(name = "gambler", referencedColumnName = "id_gambler")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gambler gambler;
    @JoinColumn(name = "match", referencedColumnName = "id_match")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Match match;
    @JoinColumn(name = "team_id_team", referencedColumnName = "id_team")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Team teamIdTeam;

    public Bet() {
    }

    public Bet(Integer idBet) {
        this.idBet = idBet;
    }

    public Bet(Integer idBet, int quantity, int team, Date date) {
        this.idBet = idBet;
        this.quantity = quantity;
        this.team = team;
        this.date = date;
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

    public Gambler getGambler() {
        return gambler;
    }

    public void setGambler(Gambler gambler) {
        this.gambler = gambler;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeamIdTeam() {
        return teamIdTeam;
    }

    public void setTeamIdTeam(Team teamIdTeam) {
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
        if (!(object instanceof Bet)) {
            return false;
        }
        Bet other = (Bet) object;
        if ((this.idBet == null && other.idBet != null) || (this.idBet != null && !this.idBet.equals(other.idBet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.Bet[ idBet=" + idBet + " ]";
    }
    
}
