package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import bo.edu.ucb.betebackend.persistence.dao.GamblerRepository;
import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import bo.edu.ucb.betebackend.persistence.entity.GamblerEntity;
import bo.edu.ucb.betebackend.persistence.mapper.GamblerMapper;
import bo.edu.ucb.betebackend.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GamblerDao implements IGamblerRepository {
    final private GamblerRepository gamblerRepository;
    final private GamblerMapper gamblerMapper;
    final private UserMapper userMapper;

    public GamblerDao(GamblerRepository gamblerRepository, GamblerMapper gamblerMapper, UserMapper userMapper) {
        this.gamblerRepository = gamblerRepository;
        this.gamblerMapper = gamblerMapper;
        this.userMapper = userMapper;
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

    @Override
    public Optional<Gambler> getGamblerByUser(User user) {
        BeteUserEntity userEntity = userMapper.toUserEntity(user);
        Gambler gambler = gamblerMapper.toGambler(gamblerRepository.findByIdUser(userEntity));
        return Optional.ofNullable(gambler);
    }
}
