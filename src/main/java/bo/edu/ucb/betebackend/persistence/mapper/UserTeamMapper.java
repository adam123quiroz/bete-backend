package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.persistence.entity.UserTeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        UserMapper.class
})
public interface UserTeamMapper {
    @Mappings({
            @Mapping(source = "idUserTeam", target = "idUserTeam"),
            @Mapping(source = "isCaptain", target = "isCaptain"),
            @Mapping(source = "team", target = "team"),
            @Mapping(source = "user", target = "user"),
    })
    UserTeam toUserTeam(UserTeamEntity userTeamEntity);

    @InheritInverseConfiguration
    UserTeamEntity toUserTeamEntity(UserTeam userTeam);
}
