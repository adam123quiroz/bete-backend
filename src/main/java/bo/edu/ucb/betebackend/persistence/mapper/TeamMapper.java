package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.persistence.entity.TeamEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserTeamMapper.class
})
public interface TeamMapper {
    @Mappings({
//            @Mapping(source = "tournamentTeamEntityList", target = "tournamentTeamList"),
//            @Mapping(source = "matchEntityList", target = "matchList"),
//            @Mapping(source = "matchEntityList1", target = "matchList1"),
//            @Mapping(source = "userTeamEntityList", target = "userTeamList"),
//            @Mapping(source = "betEntityList", target = "betList"),
    })
    Team toTeam(TeamEntity teamEntity);

    List<Team> toListTeam(List<TeamEntity> teamEntities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "tournamentTeamEntityList", ignore = true),
            @Mapping(target = "matchEntityList", ignore = true),
            @Mapping(target = "matchEntityList1", ignore = true),
            @Mapping(target = "userTeamEntityList", ignore = true),
            @Mapping(target = "betEntityList", ignore = true)
    })
    TeamEntity toTeamEntity(Team team);
}
