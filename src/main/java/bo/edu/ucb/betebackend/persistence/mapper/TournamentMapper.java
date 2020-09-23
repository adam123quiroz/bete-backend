package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        GameMapper.class,
        OrganizerMapper.class
})
public interface TournamentMapper {
    @Mappings({
            @Mapping(source = "idTournament", target = "idTournament"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "startDate", target = "startDate"),
            @Mapping(source = "endDate", target = "endDate"),
            @Mapping(source = "isFinished", target = "isFinished"),
            @Mapping(source = "game", target = "game"),
            @Mapping(source = "organizer", target = "organizer"),
    })
    Tournament toTournament(TournamentEntity tournamentEntity);

    @InheritInverseConfiguration
    @Mapping(target = "tournamentTeamEntityList", ignore = true)
    TournamentEntity toTournamentEntity(Tournament tournament);
}
