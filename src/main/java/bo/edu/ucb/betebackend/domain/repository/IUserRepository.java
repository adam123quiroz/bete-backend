package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserById(Integer id);
    User save(User user);
}
