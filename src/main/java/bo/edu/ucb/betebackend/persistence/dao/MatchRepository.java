package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.MatchEntity;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchEntity, Integer> {
    List<MatchEntity> findAllByTournament(TournamentEntity tournamentEntity);
}
