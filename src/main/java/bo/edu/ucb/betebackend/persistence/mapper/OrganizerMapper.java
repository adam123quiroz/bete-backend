package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        TournamentMapper.class,
        ReviewMapper.class
})
public interface OrganizerMapper {
    @Mappings({
            @Mapping(source = "tournamentEntityList", target = "tournamentList"),
            @Mapping(source = "reviewEntityList", target = "reviewList")
    })
    Organizer toOrganizer(OrganizerEntity organizerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    OrganizerEntity toOrganizerEntity(Organizer organizer);
}
