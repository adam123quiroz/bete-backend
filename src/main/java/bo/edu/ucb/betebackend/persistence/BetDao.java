package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.repository.IBetRepository;
import bo.edu.ucb.betebackend.persistence.dao.BetRepository;
import bo.edu.ucb.betebackend.persistence.entity.BetEntity;
import bo.edu.ucb.betebackend.persistence.entity.MatchEntity;
import bo.edu.ucb.betebackend.persistence.mapper.BetMapper;
import bo.edu.ucb.betebackend.persistence.mapper.MatchMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BetDao implements IBetRepository {
    private final BetRepository betRepository;
    private final BetMapper betMapper;
    private final MatchMapper matchMapper;

    public BetDao(BetRepository betRepository, BetMapper betMapper, MatchMapper matchMapper) {
        this.betRepository = betRepository;
        this.betMapper = betMapper;
        this.matchMapper = matchMapper;
    }

    public Optional<List<Bet>> getAllBets() {
        List<BetEntity> betEntities = betRepository.findAll();
        return Optional.ofNullable(betMapper.toBetList(betEntities));
    }

    public Optional<Bet> getBetById(Integer id) {
        return betRepository.findById(id).map(betMapper::toBet);
    }

    public Bet saveBet(Bet bet) {
        return betMapper.toBet(betRepository.save(betMapper.toBetEntity(bet)));
    }

    public void removeBet(Integer id) {
        betRepository.deleteById(id);
    }

    public Optional<List<Bet>> getAllBetsByMatch(Match match) {
        MatchEntity matchEntity = matchMapper.toMatchEntity(match);
        List<Bet> bets = betMapper.toBetList(betRepository.findAllByMatch(matchEntity));
        return Optional.ofNullable(bets);
    }
}
