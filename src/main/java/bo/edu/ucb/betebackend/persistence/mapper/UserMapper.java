package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface UserMapper {
    @Mappings({
            @Mapping(source = "idUser", target = "idUser"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastname", target = "lastname"),
            @Mapping(source = "countryCode", target = "countryCode"),
            @Mapping(source = "cellphoneNumber", target = "cellphoneNumber"),
            @Mapping(source = "isPlayer", target = "isPlayer"),
            @Mapping(source = "isOrganizer", target = "isOrganizer"),
            @Mapping(source = "isGambler", target = "isGambler"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "region", target = "region")
    })
    User toUser(UserEntity userEntity);

    @InheritInverseConfiguration
    @Mapping(target = "userTeamEntityList", ignore = true)
    @Mapping(target = "notificationEntityList", ignore = true)
    @Mapping(target = "organizerEntityList", ignore = true)
    @Mapping(target = "reviewEntityList", ignore = true)
    @Mapping(target = "gamblerEntityList", ignore = true)
    UserEntity toUserEntity(User user);
}
