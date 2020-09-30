package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.persistence.entity.GamblerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BetMapper.class})
public interface GamblerMapper {

    Gambler toGambler(GamblerEntity gamblerEntity);
    List<Gambler> toGamblerList(List<GamblerEntity> gamblerEntity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "betEntityList", ignore = true),

    })
    GamblerEntity toGamblerEntity(Gambler gambler);
}
