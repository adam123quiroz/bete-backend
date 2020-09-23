package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.GamblerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamblerRepository extends JpaRepository<GamblerEntity, Integer> {
}
