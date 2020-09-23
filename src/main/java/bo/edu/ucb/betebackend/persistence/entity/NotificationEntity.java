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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificationEntity.findAll", query = "SELECT n FROM NotificationEntity n"),
    @NamedQuery(name = "NotificationEntity.findByIdNotification", query = "SELECT n FROM NotificationEntity n WHERE n.idNotification = :idNotification"),
    @NamedQuery(name = "NotificationEntity.findByMesage", query = "SELECT n FROM NotificationEntity n WHERE n.mesage = :mesage"),
    @NamedQuery(name = "NotificationEntity.findByStatus", query = "SELECT n FROM NotificationEntity n WHERE n.status = :status")})
public class NotificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notification")
    private Integer idNotification;
    @Basic(optional = false)
    @Column(name = "mesage")
    private String mesage;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserEntity user;

    public NotificationEntity() {
    }

    public NotificationEntity(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public NotificationEntity(Integer idNotification, String mesage, int status) {
        this.idNotification = idNotification;
        this.mesage = mesage;
        this.status = status;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotification != null ? idNotification.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationEntity)) {
            return false;
        }
        NotificationEntity other = (NotificationEntity) object;
        if ((this.idNotification == null && other.idNotification != null) || (this.idNotification != null && !this.idNotification.equals(other.idNotification))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.NotificationEntity[ idNotification=" + idNotification + " ]";
    }
    
}
