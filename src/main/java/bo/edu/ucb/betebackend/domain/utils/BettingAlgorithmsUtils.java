package bo.edu.ucb.betebackend.domain.utils;

import bo.edu.ucb.betebackend.domain.Match;

public class BettingAlgorithmsUtils {
    private Match idMatch;
    private int totalSumTeam1;
    private int totalSumTeam2;

    public BettingAlgorithmsUtils(Match idMatch, int totalSumTeam1, int totalSumTeam2) {
        this.idMatch = idMatch;
        this.totalSumTeam1 = totalSumTeam1;
        this.totalSumTeam2 = totalSumTeam2;
    }

    public int getTotalSumTeam1() {
        return totalSumTeam1;
    }

    public void setTotalSumTeam1(int totalSumTeam1) {
        this.totalSumTeam1 = totalSumTeam1;
    }

    public int getTotalSumTeam2() {
        return totalSumTeam2;
    }

    public void setTotalSumTeam2(Integer totalSumTeam2) {
        this.totalSumTeam2 = totalSumTeam2;
    }

    public double getExpectedTeam1() {
        return ((double) totalSumTeam2 / (double) totalSumTeam1) + 1.0;
    }

    public double getExpectedTeam2() {
        return ((double) totalSumTeam1 / (double) totalSumTeam2) + 1.0;

    }

    public Match getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Match idMatch) {
        this.idMatch = idMatch;
    }
}
