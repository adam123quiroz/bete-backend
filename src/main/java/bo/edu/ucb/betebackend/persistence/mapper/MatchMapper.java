package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.persistence.entity.MatchEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        TournamentTeamMapper.class
})
public interface MatchMapper {
    @Mappings({
            @Mapping(source = "idMatch", target = "idMatch"),
            @Mapping(source = "scoreTeam1", target = "scoreTeam1"),
            @Mapping(source = "scoreTeam2", target = "scoreTeam2"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "time", target = "time"),
            @Mapping(source = "isFinished", target = "isFinished"),
            @Mapping(source = "linkStr", target = "linkStr"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "team2", target = "team2"),
            @Mapping(source = "team1", target = "team1"),
            @Mapping(source = "tournament", target = "tournament"),
    })
    Match toMatch(MatchEntity matchEntity);

    @InheritInverseConfiguration
    @Mapping(target = "betEntityList", ignore = true)
    MatchEntity toMatchEntity(Match match);
}
