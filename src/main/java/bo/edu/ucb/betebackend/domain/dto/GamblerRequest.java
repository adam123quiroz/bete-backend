package bo.edu.ucb.betebackend.domain.dto;

public class GamblerRequest {
    private Integer idUser;
    private String cardBank;
    private Integer coins;

    public GamblerRequest(Integer idUser, String cardBank, Integer coins) {
        this.idUser = idUser;
        this.cardBank = cardBank;
        this.coins = coins;
    }

    public GamblerRequest() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }
}
