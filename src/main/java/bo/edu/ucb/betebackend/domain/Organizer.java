package bo.edu.ucb.betebackend.domain;

import bo.edu.ucb.betebackend.persistence.entity.ReviewEntity;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;

import java.util.List;

public class Organizer {
    private Integer idOrganizer;
    private String bankCard;
    private float reputation;
    private List<Tournament> tournamentList;
    private User idUser;
    private List<Review> reviewList;


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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
