package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Region;
import bo.edu.ucb.betebackend.persistence.entity.RegionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper {
/*    @Mappings({
            @Mapping(source = "beteUserEntityList", target = "userList")
    })*/
    Region toRegion(RegionEntity regionEntity);
    List<Region> toRegionList(List<RegionEntity> regionEntities);

    @InheritInverseConfiguration
    @Mapping(target = "status", ignore = true)
    RegionEntity toRegionEntity(Region region);
}
