package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.domain.repository.IMatchRepository;
import bo.edu.ucb.betebackend.domain.repository.ITeamRepository;
import bo.edu.ucb.betebackend.domain.repository.ITournamentRepository;
import bo.edu.ucb.betebackend.domain.repository.ITournamentTeamRepository;
import bo.edu.ucb.betebackend.domain.utils.LeagueUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final ITournamentRepository tournamentRepository;
    private final ITeamRepository teamRepository;
    private final ITournamentTeamRepository tournamentTeamRepository;
    private final IMatchRepository matchRepository;

    private final LeagueUtils leagueUtils;

    public MatchService(ITournamentRepository tournamentRepository, ITeamRepository teamRepository, ITournamentTeamRepository tournamentTeamRepository, IMatchRepository matchRepository, LeagueUtils leagueUtils) {
        this.tournamentRepository = tournamentRepository;
        this.teamRepository = teamRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.matchRepository = matchRepository;
        this.leagueUtils = leagueUtils;
    }

    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.getMatchById(id);
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
}
