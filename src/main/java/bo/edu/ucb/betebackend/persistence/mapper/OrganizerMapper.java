package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrganizerMapper {
    @Mappings({
            @Mapping(source = "idOrganizer", target = "idOrganizer"),
            @Mapping(source = "bankCard", target = "bankCard"),
            @Mapping(source = "reputation", target = "reputation"),
            @Mapping(source = "idUser", target = "idUser"),
    })
    Organizer toOrganizer(OrganizerEntity organizerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "tournamentEntityList", ignore = true)
    @Mapping(target = "reviewEntityList", ignore = true)
    OrganizerEntity toOrganizerEntity(Organizer organizer);
}
