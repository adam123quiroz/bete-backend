package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.TournamentTeam;
import bo.edu.ucb.betebackend.persistence.entity.TournamentTeamEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        TeamMapper.class,
        TournamentMapper.class,
        MatchMapper.class
})
public interface TournamentTeamMapper {
    TournamentTeam toTournamentTeam(TournamentTeamEntity tournamentTeamEntity);
    List<TournamentTeam> toListTournamentTeam(List<TournamentTeamEntity> tournamentTeamEntities);

    @InheritInverseConfiguration
    @Mapping(target = "matchEntityList", ignore = true)
    TournamentTeamEntity toTournamentEntity(TournamentTeam tournamentTeam);
}
