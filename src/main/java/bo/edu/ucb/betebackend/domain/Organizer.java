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
@Table(name = "organizer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizer.findAll", query = "SELECT o FROM Organizer o"),
    @NamedQuery(name = "Organizer.findByIdOrganizer", query = "SELECT o FROM Organizer o WHERE o.idOrganizer = :idOrganizer"),
    @NamedQuery(name = "Organizer.findByBankCard", query = "SELECT o FROM Organizer o WHERE o.bankCard = :bankCard"),
    @NamedQuery(name = "Organizer.findByReputation", query = "SELECT o FROM Organizer o WHERE o.reputation = :reputation")})
public class Organizer implements Serializable {

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
    private Set<Tournament> tournamentSet;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer", fetch = FetchType.LAZY)
    private Set<Review> reviewSet;

    public Organizer() {
    }

    public Organizer(Integer idOrganizer) {
        this.idOrganizer = idOrganizer;
    }

    public Organizer(Integer idOrganizer, String bankCard, float reputation) {
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
    public Set<Tournament> getTournamentSet() {
        return tournamentSet;
    }

    public void setTournamentSet(Set<Tournament> tournamentSet) {
        this.tournamentSet = tournamentSet;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Set<Review> getReviewSet() {
        return reviewSet;
    }

    public void setReviewSet(Set<Review> reviewSet) {
        this.reviewSet = reviewSet;
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
        if (!(object instanceof Organizer)) {
            return false;
        }
        Organizer other = (Organizer) object;
        if ((this.idOrganizer == null && other.idOrganizer != null) || (this.idOrganizer != null && !this.idOrganizer.equals(other.idOrganizer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.domain.Organizer[ idOrganizer=" + idOrganizer + " ]";
    }
    
}
