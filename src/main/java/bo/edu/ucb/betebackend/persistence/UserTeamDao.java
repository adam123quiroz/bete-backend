package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.domain.repository.IUserTeamRepository;
import bo.edu.ucb.betebackend.persistence.dao.UserTeamRepository;
import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import bo.edu.ucb.betebackend.persistence.mapper.TeamMapper;
import bo.edu.ucb.betebackend.persistence.mapper.UserMapper;
import bo.edu.ucb.betebackend.persistence.mapper.UserTeamMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserTeamDao implements IUserTeamRepository {
    final private UserTeamRepository userTeamRepository;
    final private UserTeamMapper userTeamMapper;
    final private UserMapper userMapper;
    final private TeamMapper teamMapper;

    public UserTeamDao(UserTeamRepository userTeamRepository, UserTeamMapper userTeamMapper, UserMapper userMapper, TeamMapper teamMapper) {
        this.userTeamRepository = userTeamRepository;
        this.userTeamMapper = userTeamMapper;
        this.userMapper = userMapper;
        this.teamMapper = teamMapper;
    }

    @Override
    public Optional<List<UserTeam>> getUserTeams() {
        return Optional.empty();
    }

    @Override
    public Optional<UserTeam> getUserTeamById(Integer id) {
        return userTeamRepository.findById(id).map(userTeamMapper::toUserTeam);
    }

    @Override
    public UserTeam saveUserTeam(UserTeam newUserTeam) {
        return userTeamMapper.toUserTeam(userTeamRepository.save(userTeamMapper.toUserTeamEntity(newUserTeam)));
    }

    @Override
    public List<UserTeam> getAllByUser(User user) {
        return userTeamMapper.toListUserTeam(userTeamRepository.findAllByUser(userMapper.toUserEntity(user)));
    }

    @Override
    public List<UserTeam> getAllByUserAndIsCapitan(User user, Integer isCapitan) {
        BeteUserEntity userEntity = userMapper.toUserEntity(user);
        return userTeamMapper.toListUserTeam(userTeamRepository.findAllByUserAndIsCaptain(userEntity, isCapitan));
    }

    @Override
    public List<UserTeam> getAllByTeam(Team team) {
        return userTeamMapper.toListUserTeam(userTeamRepository.findAllByTeam(teamMapper.toTeamEntity(team)));
    }

    @Override
    public void deleteUserTeam(Integer id) {

    }
}
