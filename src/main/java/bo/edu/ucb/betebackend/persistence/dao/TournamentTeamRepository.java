package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import bo.edu.ucb.betebackend.persistence.entity.TournamentTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TournamentTeamRepository extends JpaRepository<TournamentTeamEntity, Integer> {
    List<TournamentTeamEntity> findAllByTournamentAndPhase(TournamentEntity tournamentEntity, Integer phase);
    List<TournamentTeamEntity> findAllByTournament(TournamentEntity tournamentEntity);
}
