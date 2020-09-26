package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getByUsername(String username);
    User save(User user);
}
