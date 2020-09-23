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
@Table(name = "organizer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrganizerEntity.findAll", query = "SELECT o FROM OrganizerEntity o"),
    @NamedQuery(name = "OrganizerEntity.findByIdOrganizer", query = "SELECT o FROM OrganizerEntity o WHERE o.idOrganizer = :idOrganizer"),
    @NamedQuery(name = "OrganizerEntity.findByBankCard", query = "SELECT o FROM OrganizerEntity o WHERE o.bankCard = :bankCard"),
    @NamedQuery(name = "OrganizerEntity.findByReputation", query = "SELECT o FROM OrganizerEntity o WHERE o.reputation = :reputation")})
public class OrganizerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_organizer")
    private Integer idOrganizer;
    @Basic(optional = false)
    @Column(name = "bank_card")
    private String bankCard;
    @Basic(optional = false)
    @Column(name = "reputation")
    private float reputation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer", fetch = FetchType.LAZY)
    private List<TournamentEntity> tournamentEntityList;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEntity idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList;

    public OrganizerEntity() {
    }

    public OrganizerEntity(Integer idOrganizer) {
        this.idOrganizer = idOrganizer;
    }

    public OrganizerEntity(Integer idOrganizer, String bankCard, float reputation) {
        this.idOrganizer = idOrganizer;
        this.bankCard = bankCard;
        this.reputation = reputation;
    }

    public Integer getIdOrganizer() {
        return idOrganizer;
    }

    public void setIdOrganizer(Integer idOrganizer) {
        this.idOrganizer = idOrganizer;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public float getReputation() {
        return reputation;
    }

    public void setReputation(float reputation) {
        this.reputation = reputation;
    }

    @XmlTransient
    public List<TournamentEntity> getTournamentEntityList() {
        return tournamentEntityList;
    }

    public void setTournamentEntityList(List<TournamentEntity> tournamentEntityList) {
        this.tournamentEntityList = tournamentEntityList;
    }

    public UserEntity getIdUser() {
        return idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public List<ReviewEntity> getReviewEntityList() {
        return reviewEntityList;
    }

    public void setReviewEntityList(List<ReviewEntity> reviewEntityList) {
        this.reviewEntityList = reviewEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganizer != null ? idOrganizer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizerEntity)) {
            return false;
        }
        OrganizerEntity other = (OrganizerEntity) object;
        if ((this.idOrganizer == null && other.idOrganizer != null) || (this.idOrganizer != null && !this.idOrganizer.equals(other.idOrganizer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity[ idOrganizer=" + idOrganizer + " ]";
    }
    
}
