package bo.edu.ucb.betebackend.domain.utils;

public class AlgorithmOfDistribution {
    private int sumWinningTeam;
    private int sumLoserTeam;

    public AlgorithmOfDistribution(int sumWinningTeam, int sumLoserTeam) {
        this.sumWinningTeam = sumWinningTeam;
        this.sumLoserTeam = sumLoserTeam;
    }

    public AlgorithmOfDistribution() {
    }

    public int getSumWinningTeam() {
        return sumWinningTeam;
    }

    public void setSumWinningTeam(int sumWinningTeam) {
        this.sumWinningTeam = sumWinningTeam;
    }

    public int getSumLoserTeam() {
        return sumLoserTeam;
    }

    public void setSumLoserTeam(int sumLoserTeam) {
        this.sumLoserTeam = sumLoserTeam;
    }

    public double getGainFactor() {
        return ((double) this.sumLoserTeam / (double) this.sumWinningTeam);
    }
}
