package bo.edu.ucb.betebackend.domain.typeof;

public enum TypeOfUsers {
    Player(1),
    Gambler(2),
    Organizer(3);

    TypeOfUsers(int typeOfUsers) {
        this.typeOfUsers = typeOfUsers;
    }

    private final int typeOfUsers;

    public int getStatus() {
        return typeOfUsers;
    }
}
