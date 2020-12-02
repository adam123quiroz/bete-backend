package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.*;
import bo.edu.ucb.betebackend.domain.dto.response.MatchExpectResponse;
import bo.edu.ucb.betebackend.domain.repository.*;
import bo.edu.ucb.betebackend.domain.utils.AlgorithmOfDistribution;
import bo.edu.ucb.betebackend.domain.utils.BettingAlgorithmsUtils;
import bo.edu.ucb.betebackend.domain.utils.LeagueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final ITournamentTeamRepository tournamentTeamRepository;
    private final IMatchRepository matchRepository;
    private final IBetRepository betRepository;

    private final LeagueUtils leagueUtils;

    final static Logger logger = LoggerFactory.getLogger(MatchService.class);


    public MatchService(ITournamentTeamRepository tournamentTeamRepository, IMatchRepository matchRepository,
                        IBetRepository betRepository, LeagueUtils leagueUtils) {
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
        this.leagueUtils = leagueUtils;
    }

    private MatchExpectResponse apply(BettingAlgorithmsUtils bettingAlgorithmsUtils) {
        MatchExpectResponse matchExpectResponse = new MatchExpectResponse(
                bettingAlgorithmsUtils.getIdMatch().getIdMatch(),
                bettingAlgorithmsUtils.getIdMatch().getTournament(),
                bettingAlgorithmsUtils.getIdMatch().getTeam1(),
                bettingAlgorithmsUtils.getIdMatch().getTeam2(),
                bettingAlgorithmsUtils.getIdMatch().getScoreTeam1(),
                bettingAlgorithmsUtils.getIdMatch().getScoreTeam2(),
                bettingAlgorithmsUtils.getIdMatch().getIsFinished()
        );

        if (bettingAlgorithmsUtils.getTotalSumTeam1() == 0) matchExpectResponse.setExpectTeam1(1.0);
        if (bettingAlgorithmsUtils.getTotalSumTeam2() == 0) matchExpectResponse.setExpectTeam2(1.0);
        return matchExpectResponse;
    }

    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.getMatchById(id);
    }

    public Optional<List<Match>> getAllMatches() {
        return matchRepository.getAllMatches();
    }

    public Optional<List<Match>> getAllMatchesByIsFinished(Integer status) {
        List<Match> matchStream = getAllMatches()
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(match -> match.getIsFinished() == status)
                .collect(Collectors.toList());
        return Optional.of(matchStream);
    }

    public Optional<LeagueUtils.GroupMatch> raffleTeams(Tournament tournament) {
        List<Team> teamList = tournamentTeamRepository.getListTournamentTeamsByTournament(tournament)
                .orElseGet(ArrayList::new)
                .stream()
                .map(TournamentTeam::getTeam)
                .collect(Collectors.toList());

        LeagueUtils.GroupMatch groupMatch = leagueUtils.raffleTeams(teamList);
        groupMatch.setMatches(groupMatch.getMatches().stream()
                .map(match -> getMatch(match, tournament))
                .map(matchRepository::saveMatch)
                .collect(Collectors.toList()));

        return Optional.of(groupMatch);
    }

    public Optional<List<MatchExpectResponse>> getListMatchWithOutcomeForecast(List<Match> matches) {
        try {
            List<Integer> sum1 = matches.stream()
                    .map(match -> getSumBet(match, 1))
                    .collect(Collectors.toList());
            List<Integer> sum = matches.stream()
                    .map(match -> getSumBet(match, 2))
                    .collect(Collectors.toList());
            logger.info("sum " + sum1.size());
            logger.info("sum1 " + sum.size());
            return Optional.of(extractedCreateList(matches, sum1, sum).stream()
                    .map(this::apply)
                    .peek(matchExpectResponse -> logger.info(matchExpectResponse.toString()))
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }

    private List<BettingAlgorithmsUtils> extractedCreateList(List<Match> matches, List<Integer> sum1, List<Integer> sum) {
        List<BettingAlgorithmsUtils> bettingAlgorithmsUtils = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            bettingAlgorithmsUtils.add(new BettingAlgorithmsUtils(
                    matches.get(i),
                    sum1.get(i),
                    sum.get(i)));
        }
        return bettingAlgorithmsUtils;
    }

    private int getSumBet(Match match, int i) {
        return betRepository.getAllBetsByMatch(match)
                .orElseGet(Collections::emptyList)
                .stream()
                .peek(bet -> logger.info("kk " + bet))
                .filter(bet -> bet.getTeam() == i)
                .mapToInt(Bet::getQuantity)
                .sum();
    }

    public Optional<List<Match>> getListOfMatchesByTournament(Tournament tournament) {
        return matchRepository.getListOfMatchesByTournament(tournament);
    }

    public Match updateMatchResult(Match match, Integer scoreTeam1Integer, Integer scoreTeam2Integer) {
        match.setScoreTeam1(scoreTeam1Integer);
        match.setScoreTeam2(scoreTeam2Integer);
        return matchRepository.saveMatch(match);
    }

    private Match getMatch(Match match, Tournament tournament) {
        match.setTournament(tournament);
        match.setDate(new Date()); // TODO: 11/29/2020 Ask what Datetime
        match.setTime(new Date()); // TODO: 11/29/2020 Ask for the Time
        match.setIsFinished(0);
        match.setStatus(1);
        match.setScoreTeam1(0);
        match.setScoreTeam2(0);
        return match;
    }

    public Match updateIsFinished(Match match, int i) {
        match.setIsFinished(i);
        return matchRepository.saveMatch(match);
    }

    public void spreadAllTheBets(Match updateMatch) {
        // TODO: 12/2/2020 To Do the Tie Way
        int sumWinningTeam = winningTeam(updateMatch, getWinnerTeam(updateMatch).get("winningTeam"));
        int sumLoserTeam = winningTeam(updateMatch, getWinnerTeam(updateMatch).get("loserTeam"));
        AlgorithmOfDistribution algorithmOfDistribution = new AlgorithmOfDistribution(sumWinningTeam, sumLoserTeam);

    }

    private int winningTeam(Match updateMatch, Team team) {
        return betRepository.getAllBetsByMatch(updateMatch)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(bet -> bet.getTeamIdTeam().getIdTeam().equals(team.getIdTeam()))
                .mapToInt(Bet::getQuantity)
                .sum();
    }

    private HashMap<String, Team> getWinnerTeam(Match updateMatch) {
        HashMap<String, Team> teamHashMap = new HashMap<>();
        if (updateMatch.getScoreTeam1().equals(updateMatch.getScoreTeam2())) teamHashMap.put("teamTie", null);
        if (updateMatch.getScoreTeam2() > updateMatch.getScoreTeam2())
            assigningTeams(teamHashMap, updateMatch.getTeam2(), updateMatch.getTeam1());
        else assigningTeams(teamHashMap, updateMatch.getTeam1(), updateMatch.getTeam2());
        return teamHashMap;
    }

    private void assigningTeams(HashMap<String, Team> teamHashMap, Team team2, Team team1) {
        teamHashMap.put("winningTeam", team2);
        teamHashMap.put("loserTeam", team1);
        teamHashMap.put("teamTie", team1);
    }
}
