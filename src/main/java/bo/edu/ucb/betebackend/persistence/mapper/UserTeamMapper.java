package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.persistence.entity.UserTeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        UserMapper.class
})
public interface UserTeamMapper {
    UserTeam toUserTeam(UserTeamEntity userTeamEntity);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    UserTeamEntity toUserTeamEntity(UserTeam userTeam);
}
