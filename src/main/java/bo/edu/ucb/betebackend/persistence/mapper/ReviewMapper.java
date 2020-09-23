package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Review;
import bo.edu.ucb.betebackend.persistence.entity.ReviewEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        OrganizerMapper.class,
        UserMapper.class
})
public interface ReviewMapper {
    @Mappings({
            @Mapping(source = "idReview", target = "idReview"),
            @Mapping(source = "stars", target = "stars"),
            @Mapping(source = "organizer", target = "organizer"),
            @Mapping(source = "user", target = "user"),
    })
    Review toReview(ReviewEntity reviewEntity);

    @InheritInverseConfiguration
    ReviewEntity toRegionEntity(Review review);
}
