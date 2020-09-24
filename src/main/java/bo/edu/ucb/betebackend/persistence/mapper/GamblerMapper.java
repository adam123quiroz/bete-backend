package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.persistence.entity.GamblerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        BetMapper.class

})
public interface GamblerMapper {

    @Mappings({
            @Mapping(source = "betEntityList", target = "betList")
    })
    Gambler toGambler(GamblerEntity gamblerEntity);

    @InheritInverseConfiguration
    GamblerEntity toGamblerEntity(Gambler gambler);
}
