package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.persistence.entity.GamblerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class
})
public interface GamblerMapper {

    @Mappings({
            @Mapping(source = "idGambler", target = "idGambler"),
            @Mapping(source = "bankCard", target = "bankCard"),
            @Mapping(source = "coins", target = "coins"),
            @Mapping(source = "idUser", target = "idUser")
    })
    Gambler toGambler(GamblerEntity gamblerEntity);

    @InheritInverseConfiguration
    @Mapping(target = "betEntityList", ignore = true)
    GamblerEntity toGamblerEntity(Gambler gambler);
}
