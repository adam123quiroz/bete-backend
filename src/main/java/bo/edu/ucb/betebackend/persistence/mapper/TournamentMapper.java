package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        GameMapper.class,
        OrganizerMapper.class,
        TournamentTeamMapper.class
})
public interface TournamentMapper {
    @Mappings({
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "tournamentTeamEntityList", target = "tournamentTeamList")
    })
    Tournament toTournament(TournamentEntity tournamentEntity);

    @InheritInverseConfiguration
    TournamentEntity toTournamentEntity(Tournament tournament);
}
