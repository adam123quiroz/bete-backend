package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TournamentRepository extends JpaRepository<TournamentEntity, Integer> {
    List<TournamentEntity> findAllByOrganizer(OrganizerEntity organizerEntity);
    List<TournamentEntity> findAllByStartDateAfter(Date date);
}
