package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Review;
import bo.edu.ucb.betebackend.persistence.entity.ReviewEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        OrganizerMapper.class,
        UserMapper.class
})
public interface ReviewMapper {
    Review toReview(ReviewEntity reviewEntity);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    ReviewEntity toRegionEntity(Review review);
}
