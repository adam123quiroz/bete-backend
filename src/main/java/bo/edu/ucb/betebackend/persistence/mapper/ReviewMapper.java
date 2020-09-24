package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Review;
import bo.edu.ucb.betebackend.persistence.entity.ReviewEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        OrganizerMapper.class,
        UserMapper.class
})
public interface ReviewMapper {
    Review toReview(ReviewEntity reviewEntity);

    @InheritInverseConfiguration
    ReviewEntity toRegionEntity(Review review);
}
