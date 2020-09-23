/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.ucb.betebackend.persistence.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author artud
 */
@Entity
@Table(name = "tournament")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TournamentEntity.findAll", query = "SELECT t FROM TournamentEntity t"),
    @NamedQuery(name = "TournamentEntity.findByIdTournament", query = "SELECT t FROM TournamentEntity t WHERE t.idTournament = :idTournament"),
    @NamedQuery(name = "TournamentEntity.findByNombre", query = "SELECT t FROM TournamentEntity t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TournamentEntity.findByDescription", query = "SELECT t FROM TournamentEntity t WHERE t.description = :description"),
    @NamedQuery(name = "TournamentEntity.findByStatus", query = "SELECT t FROM TournamentEntity t WHERE t.status = :status"),
    @NamedQuery(name = "TournamentEntity.findByStartDate", query = "SELECT t FROM TournamentEntity t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TournamentEntity.findByEndDate", query = "SELECT t FROM TournamentEntity t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "TournamentEntity.findByIsFinished", query = "SELECT t FROM TournamentEntity t WHERE t.isFinished = :isFinished")})
public class TournamentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tournament")
    private Integer idTournament;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "is_finished")
    private int isFinished;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament", fetch = FetchType.LAZY)
    private List<TournamentTeamEntity> tournamentTeamEntityList;
    @JoinColumn(name = "game", referencedColumnName = "id_game")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GameEntity game;
    @JoinColumn(name = "organizer", referencedColumnName = "id_organizer")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrganizerEntity organizer;

    public TournamentEntity() {
    }

    public TournamentEntity(Integer idTournament) {
        this.idTournament = idTournament;
    }

    public TournamentEntity(Integer idTournament, String nombre, int status, Date startDate, Date endDate, int isFinished) {
        this.idTournament = idTournament;
        this.nombre = nombre;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFinished = isFinished;
    }

    public Integer getIdTournament() {
        return idTournament;
    }

    public void setIdTournament(Integer idTournament) {
        this.idTournament = idTournament;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    @XmlTransient
    public List<TournamentTeamEntity> getTournamentTeamEntityList() {
        return tournamentTeamEntityList;
    }

    public void setTournamentTeamEntityList(List<TournamentTeamEntity> tournamentTeamEntityList) {
        this.tournamentTeamEntityList = tournamentTeamEntityList;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
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
        hash += (idTournament != null ? idTournament.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TournamentEntity)) {
            return false;
        }
        TournamentEntity other = (TournamentEntity) object;
        if ((this.idTournament == null && other.idTournament != null) || (this.idTournament != null && !this.idTournament.equals(other.idTournament))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bo.edu.ucb.betebackend.persistence.entity.TournamentEntity[ idTournament=" + idTournament + " ]";
    }
    
}
