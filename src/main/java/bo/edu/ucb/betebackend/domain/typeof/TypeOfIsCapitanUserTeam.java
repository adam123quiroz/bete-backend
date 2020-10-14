package bo.edu.ucb.betebackend.domain.typeof;

public enum TypeOfIsCapitanUserTeam {
    TheCapitan(2), AcceptTheInvitation(1), NotAnswer(0), NotAccept(-1);

    TypeOfIsCapitanUserTeam(int typeOfUsers) {
        this.typeOfCapitanFill = typeOfUsers;
    }

    private final int typeOfCapitanFill;

    public int getStatus() {
        return typeOfCapitanFill;
    }
}
