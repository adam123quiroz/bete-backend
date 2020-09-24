package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Notification;
import bo.edu.ucb.betebackend.persistence.entity.NotificationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class
})
public interface NotificationMapper {
    @Mappings({
            @Mapping(source = "mesage", target = "message")
    })
    Notification toNotification(NotificationEntity notificationEntity);

    @InheritInverseConfiguration
    NotificationEntity toNotificationEntity(Notification notification);
}
