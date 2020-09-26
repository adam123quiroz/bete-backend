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
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GameEntity.findAll", query = "SELECT g FROM GameEntity g"),
    @NamedQuery(name = "GameEntity.findByIdGame", query = "SELECT g FROM GameEntity g WHERE g.idGame = :idGame"),
    @NamedQuery(name = "GameEntity.findByName", query = "SELECT g FROM GameEntity g WHERE g.name = :name"),
    @NamedQuery(name = "GameEntity.findByStatus", query = "SELECT g FROM GameEntity g WHERE g.status = :status")})
public class GameEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_game")
    private Integer idGame;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game", fetch = FetchType.LAZY)
    private List<TournamentEntity> tournamentEntityList;

    public GameEntity() {
    }

    public GameEntity(Integer idGame) {
        this.idGame = idGame;
    }

    public GameEntity(Integer idGame, String name, int status) {
        this.idGame = idGame;
        this.name = name;
        this.status = status;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public List<TournamentEntity> getTournamentEntityList() {
        return tournamentEntityList;
    }

    public void setTournamentEntityList(List<TournamentEntity> tournamentEntityList) {
        this.tournamentEntityList = tournamentEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGame != null ? idGame.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameEntity)) {
            return false;
        }
        GameEntity other = (GameEntity) object;
        if ((this.idGame == null && other.idGame != null) || (this.idGame != null && !this.idGame.equals(other.idGame))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.GameEntity[ idGame=" + idGame + " ]";
    }
    
}
