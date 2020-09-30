package bo.edu.ucb.betebackend.domain;

import java.util.List;

public class Gambler {
    private Integer idGambler;
    private String bankCard;
    private int coins;
//    private List<Bet> betList;
    private User idUser;

    public Gambler(Integer idGambler, String bankCard, int coins, User idUser) {
        this.idGambler = idGambler;
        this.bankCard = bankCard;
        this.coins = coins;
        this.idUser = idUser;
    }

    public Gambler() {
    }

    public Integer getIdGambler() {
        return idGambler;
    }

    public void setIdGambler(Integer idGambler) {
        this.idGambler = idGambler;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    /*public List<Bet> getBetList() {
        return betList;
    }

    public void setBetList(List<Bet> betList) {
        this.betList = betList;
    }
*/
    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
