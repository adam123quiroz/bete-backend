package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.persistence.entity.TeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mappings({
            @Mapping(source = "idTeam", target = "idTeam"),
            @Mapping(source = "teamName", target = "teamName"),
            @Mapping(source = "organization", target = "organization"),
            @Mapping(source = "status", target = "status"),
    })
    Team toTeam(TeamEntity teamEntity);

    @InheritInverseConfiguration
    @Mapping(target = "tournamentTeamEntityList", ignore = true)
    @Mapping(target = "matchEntityList", ignore = true)
    @Mapping(target = "matchEntityList1", ignore = true)
    @Mapping(target = "userTeamEntityList", ignore = true)
    @Mapping(target = "betEntityList", ignore = true)
    TeamEntity toTeamEntity(Team team);
}
