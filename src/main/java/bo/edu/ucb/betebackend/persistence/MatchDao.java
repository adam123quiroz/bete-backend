package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.repository.IMatchRepository;
import bo.edu.ucb.betebackend.persistence.dao.MatchRepository;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import bo.edu.ucb.betebackend.persistence.mapper.MatchMapper;
import bo.edu.ucb.betebackend.persistence.mapper.TournamentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MatchDao implements IMatchRepository {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final TournamentMapper tournamentMapper;

    public MatchDao(MatchRepository matchRepository, MatchMapper matchMapper, TournamentMapper tournamentMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    public Optional<List<Match>> getAllMatches() {
        return Optional.ofNullable(matchMapper.toListMatch(matchRepository.findAll()));
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.findById(id).map(matchMapper::toMatch);
    }

    @Override
    public Match saveMatch(Match match) {
        return matchMapper.toMatch(matchRepository.save(matchMapper.toMatchEntity(match)));
    }

    @Override
    public void remove(Integer id) {
        matchRepository.deleteById(id);
    }

    @Override
    public Optional<List<Match>> getListOfMatchesByTournament(Tournament tournament) {
        TournamentEntity tournamentEntity = tournamentMapper.toTournamentEntity(tournament);
        List<Match> matches = matchMapper.toListMatch(matchRepository.findAllByTournament(tournamentEntity));
        return Optional.ofNullable(matches);
    }
}
