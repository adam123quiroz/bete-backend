package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Tournament;

import java.util.List;
import java.util.Optional;

public interface IMatchRepository {
    Optional<List<Match>> getAllMatches();
    Optional<Match> getMatchById(Integer id);
    Match saveMatch(Match match);
    void remove(Integer id);

    Optional<List<Match>> getListOfMatchesByTournament(Tournament tournament);
}
