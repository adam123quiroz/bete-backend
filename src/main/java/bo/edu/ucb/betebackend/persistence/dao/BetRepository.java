package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.BetEntity;
import bo.edu.ucb.betebackend.persistence.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<BetEntity, Integer> {
    List<BetEntity> findAllByMatch(MatchEntity matchEntity);
}
