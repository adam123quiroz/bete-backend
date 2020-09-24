package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Region;
import bo.edu.ucb.betebackend.persistence.entity.RegionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RegionMapper {
    @Mappings({
            @Mapping(source = "userEntityList", target = "userList")
    })
    Region toRegion(RegionEntity regionEntity);

    @InheritInverseConfiguration
    RegionEntity toRegionEntity(Region region);
}
