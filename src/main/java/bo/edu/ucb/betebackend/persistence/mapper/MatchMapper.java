package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.persistence.entity.MatchEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        TournamentTeamMapper.class,
        BetMapper.class
})
public interface MatchMapper {
    @Mappings({
            @Mapping(source = "betEntityList", target = "betList")
    })
    Match toMatch(MatchEntity matchEntity);

    @InheritInverseConfiguration
    MatchEntity toMatchEntity(Match match);
}
