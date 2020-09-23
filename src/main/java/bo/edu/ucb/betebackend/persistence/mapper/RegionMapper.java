package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Region;
import bo.edu.ucb.betebackend.persistence.entity.RegionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    @Mappings({
            @Mapping(source = "idRegion", target = "idRegion"),
            @Mapping(source = "regionName", target = "regionName")
    })
    Region toRegion(RegionEntity regionEntity);

    @InheritInverseConfiguration
    @Mapping(target = "userEntityList", ignore = true)
    RegionEntity toRegionEntity(Region region);
}
