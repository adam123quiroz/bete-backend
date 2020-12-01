package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.persistence.entity.MatchEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        TournamentMapper.class
})
public interface MatchMapper {

    Match toMatch(MatchEntity matchEntity);
    List<Match> toListMatch(List<MatchEntity> matchEntities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(ignore = true,  target = "betEntityList")
    })
    MatchEntity toMatchEntity(Match match);
}
