package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        GameMapper.class,
        OrganizerMapper.class,
        TournamentTeamMapper.class
})
public interface TournamentMapper {

    Tournament toTournament(TournamentEntity tournamentEntity);
    List<Tournament> toListTournament(List<TournamentEntity> tournamentEntities);

    @InheritInverseConfiguration
    @Mapping(target = "tournamentTeamEntityList", ignore = true)
    TournamentEntity toTournamentEntity(Tournament tournament);
}
