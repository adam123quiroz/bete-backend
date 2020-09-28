package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getGameByUsername(String username);
    Optional<User> getGameById(Integer id);
    User save(User user);
}
