package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        RegionMapper.class,
        TeamMapper.class,
        UserTeamMapper.class,
        NotificationMapper.class,
        OrganizerMapper.class,
        ReviewMapper.class,
        GamblerMapper.class
})
public interface UserMapper {
/*    @Mappings({
            @Mapping(source = "userTeamEntityList", target = "userTeamList"),
            @Mapping(source = "notificationEntityList", target = "notificationList"),
            @Mapping(source = "organizerEntityList", target = "organizerList"),
            @Mapping(source = "reviewEntityList", target = "reviewList"),
            @Mapping(source = "gamblerEntityList", target = "gamblerList"),
    })*/
    @Mapping(target = "region", ignore = true)
    User toUser(BeteUserEntity userEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "userTeamEntityList", ignore = true),
            @Mapping(target = "region", ignore = true),
            @Mapping(target = "notificationEntityList", ignore = true),
            @Mapping(target = "organizerEntityList", ignore = true),
            @Mapping(target = "reviewEntityList", ignore = true),
            @Mapping(target = "gamblerEntityList", ignore = true),
    })
    BeteUserEntity toUserEntity(User user);
}
