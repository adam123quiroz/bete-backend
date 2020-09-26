/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReviewEntity.findAll", query = "SELECT r FROM ReviewEntity r"),
    @NamedQuery(name = "ReviewEntity.findByIdReview", query = "SELECT r FROM ReviewEntity r WHERE r.idReview = :idReview"),
    @NamedQuery(name = "ReviewEntity.findByStars", query = "SELECT r FROM ReviewEntity r WHERE r.stars = :stars"),
    @NamedQuery(name = "ReviewEntity.findByStatus", query = "SELECT r FROM ReviewEntity r WHERE r.status = :status")})
public class ReviewEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_review")
    private Integer idReview;
    @Basic(optional = false)
    @Column(name = "stars")
    private float stars;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BeteUserEntity user;
    @JoinColumn(name = "organizer", referencedColumnName = "id_organizer")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganizerEntity organizer;

    public ReviewEntity() {
    }

    public ReviewEntity(Integer idReview) {
        this.idReview = idReview;
    }

    public ReviewEntity(Integer idReview, float stars, int status) {
        this.idReview = idReview;
        this.stars = stars;
        this.status = status;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BeteUserEntity getUser() {
        return user;
    }

    public void setUser(BeteUserEntity user) {
        this.user = user;
    }

    public OrganizerEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(OrganizerEntity organizer) {
        this.organizer = organizer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReview != null ? idReview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewEntity)) {
            return false;
        }
        ReviewEntity other = (ReviewEntity) object;
        if ((this.idReview == null && other.idReview != null) || (this.idReview != null && !this.idReview.equals(other.idReview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.ReviewEntity[ idReview=" + idReview + " ]";
    }
    
}
