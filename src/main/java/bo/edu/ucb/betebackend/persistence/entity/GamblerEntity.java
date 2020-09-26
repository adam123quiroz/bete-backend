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
@Table(name = "gambler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GamblerEntity.findAll", query = "SELECT g FROM GamblerEntity g"),
    @NamedQuery(name = "GamblerEntity.findByIdGambler", query = "SELECT g FROM GamblerEntity g WHERE g.idGambler = :idGambler"),
    @NamedQuery(name = "GamblerEntity.findByBankCard", query = "SELECT g FROM GamblerEntity g WHERE g.bankCard = :bankCard"),
    @NamedQuery(name = "GamblerEntity.findByCoins", query = "SELECT g FROM GamblerEntity g WHERE g.coins = :coins"),
    @NamedQuery(name = "GamblerEntity.findByStatus", query = "SELECT g FROM GamblerEntity g WHERE g.status = :status")})
public class GamblerEntity implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gambler", fetch = FetchType.LAZY)
    private List<BetEntity> betEntityList;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BeteUserEntity idUser;

    public GamblerEntity() {
    }

    public GamblerEntity(Integer idGambler) {
        this.idGambler = idGambler;
    }

    public GamblerEntity(Integer idGambler, String bankCard, int coins, int status) {
        this.idGambler = idGambler;
        this.bankCard = bankCard;
        this.coins = coins;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<BetEntity> getBetEntityList() {
        return betEntityList;
    }

    public void setBetEntityList(List<BetEntity> betEntityList) {
        this.betEntityList = betEntityList;
    }

    public BeteUserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(BeteUserEntity idUser) {
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
        if (!(object instanceof GamblerEntity)) {
            return false;
        }
        GamblerEntity other = (GamblerEntity) object;
        if ((this.idGambler == null && other.idGambler != null) || (this.idGambler != null && !this.idGambler.equals(other.idGambler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.GamblerEntity[ idGambler=" + idGambler + " ]";
    }
    
}
