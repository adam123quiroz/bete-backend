package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.persistence.entity.TeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        TournamentTeamMapper.class,
        MatchMapper.class,
        UserTeamMapper.class,
        BetMapper.class
})
public interface TeamMapper {
    @Mappings({
            @Mapping(source = "tournamentTeamEntityList", target = "tournamentTeamList"),
            @Mapping(source = "matchEntityList", target = "matchList"),
            @Mapping(source = "matchEntityList1", target = "matchList1"),
            @Mapping(source = "userTeamEntityList", target = "userTeamList"),
            @Mapping(source = "betEntityList", target = "betList"),
    })
    Team toTeam(TeamEntity teamEntity);

    @InheritInverseConfiguration
    TeamEntity toTeamEntity(Team team);
}
