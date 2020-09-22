/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.domain;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "pg_buffercache")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PgBuffercache.findAll", query = "SELECT p FROM PgBuffercache p"),
    @NamedQuery(name = "PgBuffercache.findByBufferid", query = "SELECT p FROM PgBuffercache p WHERE p.bufferid = :bufferid"),
    @NamedQuery(name = "PgBuffercache.findByRelfilenode", query = "SELECT p FROM PgBuffercache p WHERE p.relfilenode = :relfilenode"),
    @NamedQuery(name = "PgBuffercache.findByReltablespace", query = "SELECT p FROM PgBuffercache p WHERE p.reltablespace = :reltablespace"),
    @NamedQuery(name = "PgBuffercache.findByReldatabase", query = "SELECT p FROM PgBuffercache p WHERE p.reldatabase = :reldatabase"),
    @NamedQuery(name = "PgBuffercache.findByRelforknumber", query = "SELECT p FROM PgBuffercache p WHERE p.relforknumber = :relforknumber"),
    @NamedQuery(name = "PgBuffercache.findByRelblocknumber", query = "SELECT p FROM PgBuffercache p WHERE p.relblocknumber = :relblocknumber"),
    @NamedQuery(name = "PgBuffercache.findByIsdirty", query = "SELECT p FROM PgBuffercache p WHERE p.isdirty = :isdirty"),
    @NamedQuery(name = "PgBuffercache.findByUsagecount", query = "SELECT p FROM PgBuffercache p WHERE p.usagecount = :usagecount"),
    @NamedQuery(name = "PgBuffercache.findByPinningBackends", query = "SELECT p FROM PgBuffercache p WHERE p.pinningBackends = :pinningBackends")})
public class PgBuffercache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "bufferid")
    private Integer bufferid;
    @Column(name = "relfilenode")
    private BigInteger relfilenode;
    @Column(name = "reltablespace")
    private BigInteger reltablespace;
    @Column(name = "reldatabase")
    private BigInteger reldatabase;
    @Column(name = "relforknumber")
    private Short relforknumber;
    @Column(name = "relblocknumber")
    private BigInteger relblocknumber;
    @Column(name = "isdirty")
    private Boolean isdirty;
    @Column(name = "usagecount")
    private Short usagecount;
    @Column(name = "pinning_backends")
    private Integer pinningBackends;

    public PgBuffercache() {
    }

    public Integer getBufferid() {
        return bufferid;
    }

    public void setBufferid(Integer bufferid) {
        this.bufferid = bufferid;
    }

    public BigInteger getRelfilenode() {
        return relfilenode;
    }

    public void setRelfilenode(BigInteger relfilenode) {
        this.relfilenode = relfilenode;
    }

    public BigInteger getReltablespace() {
        return reltablespace;
    }

    public void setReltablespace(BigInteger reltablespace) {
        this.reltablespace = reltablespace;
    }

    public BigInteger getReldatabase() {
        return reldatabase;
    }

    public void setReldatabase(BigInteger reldatabase) {
        this.reldatabase = reldatabase;
    }

    public Short getRelforknumber() {
        return relforknumber;
    }

    public void setRelforknumber(Short relforknumber) {
        this.relforknumber = relforknumber;
    }

    public BigInteger getRelblocknumber() {
        return relblocknumber;
    }

    public void setRelblocknumber(BigInteger relblocknumber) {
        this.relblocknumber = relblocknumber;
    }

    public Boolean getIsdirty() {
        return isdirty;
    }

    public void setIsdirty(Boolean isdirty) {
        this.isdirty = isdirty;
    }

    public Short getUsagecount() {
        return usagecount;
    }

    public void setUsagecount(Short usagecount) {
        this.usagecount = usagecount;
    }

    public Integer getPinningBackends() {
        return pinningBackends;
    }

    public void setPinningBackends(Integer pinningBackends) {
        this.pinningBackends = pinningBackends;
    }
    
}
