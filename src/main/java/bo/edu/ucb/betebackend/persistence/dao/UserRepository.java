package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<BeteUserEntity, Integer> {
    BeteUserEntity findByUsername(String username);
}
