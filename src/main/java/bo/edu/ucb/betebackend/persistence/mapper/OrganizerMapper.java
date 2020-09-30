package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizerMapper {

    Organizer toOrganizer(OrganizerEntity organizerEntity);
    List<Organizer> toOrganizerList(List<OrganizerEntity> organizerEntities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "tournamentEntityList", ignore = true),
            @Mapping(target = "reviewEntityList", ignore = true),
    })
    OrganizerEntity toOrganizerEntity(Organizer organizer);
}
