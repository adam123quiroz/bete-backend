package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import bo.edu.ucb.betebackend.persistence.entity.TeamEntity;
import bo.edu.ucb.betebackend.persistence.entity.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeamEntity, Integer> {
    List<UserTeamEntity> findAllByUser(BeteUserEntity userEntity);
    List<UserTeamEntity> findAllByUserAndIsCaptain(BeteUserEntity userEntity, Integer isCaptain);
    List<UserTeamEntity> findAllByTeam(TeamEntity team);
    UserTeamEntity findByTeamAndUser(TeamEntity teamEntity, BeteUserEntity userEntity);
}
