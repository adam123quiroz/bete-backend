package bo.edu.ucb.betebackend.domain.typeof;

public enum TypeOfIsFinishTournament {

    TournamentOver(0),

    TournamentNotFinished(1),
    ;

    private final int typeOfIsFinishTournament;

    TypeOfIsFinishTournament(int typeOfIsFinishTournament) {
        this.typeOfIsFinishTournament = typeOfIsFinishTournament;
    }

    public int getTypeOfIsFinishTournament() {
        return typeOfIsFinishTournament;
    }
}
