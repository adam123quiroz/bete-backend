package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, Integer> {
    OrganizerEntity findByIdUser(BeteUserEntity userEntity);
}
