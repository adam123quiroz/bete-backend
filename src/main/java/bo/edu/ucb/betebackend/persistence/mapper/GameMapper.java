package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.persistence.entity.GameEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mappings({
            @Mapping(source = "idGame", target = "idGame"),
            @Mapping(source = "name", target = "name"),
    })
    Game toGame(GameEntity gameEntity);

    @InheritInverseConfiguration
    @Mapping(target = "tournamentEntityList", ignore = true)
    GameEntity toGameEntity(Game game);
}
