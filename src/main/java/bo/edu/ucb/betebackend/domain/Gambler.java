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
@Table(name = "gambler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gambler.findAll", query = "SELECT g FROM Gambler g"),
    @NamedQuery(name = "Gambler.findByIdGambler", query = "SELECT g FROM Gambler g WHERE g.idGambler = :idGambler"),
    @NamedQuery(name = "Gambler.findByBankCard", query = "SELECT g FROM Gambler g WHERE g.bankCard = :bankCard"),
    @NamedQuery(name = "Gambler.findByCoins", query = "SELECT g FROM Gambler g WHERE g.coins = :coins")})
public class Gambler implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gambler")
    private Integer idGambler;
    @Basic(optional = false)
    @Column(name = "bank_card")
    private String bankCard;
    @Basic(optional = false)
    @Column(name = "coins")
    private int coins;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gambler", fetch = FetchType.LAZY)
    private Set<Bet> betSet;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idUser;

    public Gambler() {
    }

    public Gambler(Integer idGambler) {
        this.idGambler = idGambler;
    }

    public Gambler(Integer idGambler, String bankCard, int coins) {
        this.idGambler = idGambler;
        this.bankCard = bankCard;
        this.coins = coins;
    }

    public Integer getIdGambler() {
        return idGambler;
    }

    public void setIdGambler(Integer idGambler) {
        this.idGambler = idGambler;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @XmlTransient
    public Set<Bet> getBetSet() {
        return betSet;
    }

    public void setBetSet(Set<Bet> betSet) {
        this.betSet = betSet;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGambler != null ? idGambler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gambler)) {
            return false;
        }
        Gambler other = (Gambler) object;
        if ((this.idGambler == null && other.idGambler != null) || (this.idGambler != null && !this.idGambler.equals(other.idGambler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.Gambler[ idGambler=" + idGambler + " ]";
    }
    
}
