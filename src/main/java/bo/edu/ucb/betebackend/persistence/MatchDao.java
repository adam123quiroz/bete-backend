package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.repository.IMatchRepository;
import bo.edu.ucb.betebackend.persistence.dao.MatchRepository;
import bo.edu.ucb.betebackend.persistence.mapper.MatchMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MatchDao implements IMatchRepository {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchDao(MatchRepository matchRepository, MatchMapper matchMapper) {
        this.matchRepository = matchRepository;
        this.matchMapper = matchMapper;
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
}
