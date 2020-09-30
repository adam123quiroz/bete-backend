package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class Organizer {
    private Integer idOrganizer;
    private String bankCard;
    private float reputation;
    private User idUser;
    /*private List<Tournament> tournamentList;
    private List<Review> reviewList;*/

    public Organizer(Integer idOrganizer, String bankCard, float reputation, User idUser) {
        this.idOrganizer = idOrganizer;
        this.bankCard = bankCard;
        this.reputation = reputation;
        this.idUser = idUser;
    }

    public Organizer() {
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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

/*    public List<Tournament> getTournamentList() {
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
    }*/
}
