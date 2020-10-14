package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.persistence.entity.UserTeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTeamMapper {
    UserTeam toUserTeam(UserTeamEntity userTeamEntity);
    List<UserTeam> toListUserTeam(List<UserTeamEntity> userTeamEntity);

    @InheritInverseConfiguration
    UserTeamEntity toUserTeamEntity(UserTeam userTeam);
}
