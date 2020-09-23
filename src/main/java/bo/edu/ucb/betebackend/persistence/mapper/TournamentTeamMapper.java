package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.persistence.entity.TournamentTeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        TournamentMapper.class
})
public interface TournamentTeamMapper {
    @Mappings({
            @Mapping(source = "idTournamentTeam", target = "idTournamentTeam"),
            @Mapping(source = "phase", target = "phase"),
            @Mapping(source = "team", target = "team"),
            @Mapping(source = "tournament", target = "tournament")

    })
    TournamentTeam toTournamentTeam(TournamentTeamEntity tournamentTeamEntity);

    @InheritInverseConfiguration
    @Mapping(target = "matchEntityList", ignore = true)
    TournamentTeamEntity toTournamentEntity(TournamentTeam tournamentTeam);
}
