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
@Table(name = "region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegionEntity.findAll", query = "SELECT r FROM RegionEntity r"),
    @NamedQuery(name = "RegionEntity.findByIdRegion", query = "SELECT r FROM RegionEntity r WHERE r.idRegion = :idRegion"),
    @NamedQuery(name = "RegionEntity.findByRegionName", query = "SELECT r FROM RegionEntity r WHERE r.regionName = :regionName"),
    @NamedQuery(name = "RegionEntity.findByStatus", query = "SELECT r FROM RegionEntity r WHERE r.status = :status")})
public class RegionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_region")
    private Integer idRegion;
    @Basic(optional = false)
    @Column(name = "region_name")
    private String regionName;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region", fetch = FetchType.LAZY)
    private List<BeteUserEntity> beteUserEntityList;

    public RegionEntity() {
    }

    public RegionEntity(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public RegionEntity(Integer idRegion, String regionName, int status) {
        this.idRegion = idRegion;
        this.regionName = regionName;
        this.status = status;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<BeteUserEntity> getBeteUserEntityList() {
        return beteUserEntityList;
    }

    public void setBeteUserEntityList(List<BeteUserEntity> beteUserEntityList) {
        this.beteUserEntityList = beteUserEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegionEntity)) {
            return false;
        }
        RegionEntity other = (RegionEntity) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.RegionEntity[ idRegion=" + idRegion + " ]";
    }
    
}
