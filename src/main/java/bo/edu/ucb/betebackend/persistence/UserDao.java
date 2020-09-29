package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import bo.edu.ucb.betebackend.persistence.dao.UserRepository;
import bo.edu.ucb.betebackend.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao implements IUserRepository {
    final private UserRepository userRepository;
    final private UserMapper userMapper;

    public UserDao(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userMapper.toUser(userRepository.findByUsername(username)));
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id).map(userMapper::toUser);
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }
}
