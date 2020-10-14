package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserById(Integer id);
    Optional<List<User>> getAllUsers();
    User save(User user);
}
