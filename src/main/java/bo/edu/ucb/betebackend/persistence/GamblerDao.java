package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import bo.edu.ucb.betebackend.persistence.dao.GamblerRepository;
import bo.edu.ucb.betebackend.persistence.entity.GamblerEntity;
import bo.edu.ucb.betebackend.persistence.mapper.GamblerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GamblerDao implements IGamblerRepository {
    final private GamblerRepository gamblerRepository;
    final private GamblerMapper gamblerMapper;

    public GamblerDao(GamblerRepository gamblerRepository, GamblerMapper gamblerMapper) {
        this.gamblerRepository = gamblerRepository;
        this.gamblerMapper = gamblerMapper;
    }

    @Override
    public Optional<List<Gambler>> getAllGamblers() {
        List<GamblerEntity> gamblerEntities = gamblerRepository.findAll();
        return Optional.ofNullable(gamblerMapper.toGamblerList(gamblerEntities));
    }

    @Override
    public Optional<Gambler> getGamblerById(Integer id) {
        return gamblerRepository.findById(id).map(gamblerMapper::toGambler);
    }

    @Override
    public Gambler saveGambler(Gambler game) {
        return gamblerMapper.toGambler(gamblerRepository.save(gamblerMapper.toGamblerEntity(game)));
    }

    @Override
    public void removeGambler(Integer id) {
        gamblerRepository.deleteById(id);
    }
}
