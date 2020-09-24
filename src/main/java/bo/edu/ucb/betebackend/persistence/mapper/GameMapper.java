package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Game;
import bo.edu.ucb.betebackend.persistence.entity.GameEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TournamentMapper.class})
public interface GameMapper {
    @Mappings({
            @Mapping(source = "tournamentEntityList", target = "tournamentList")
    })
    Game toGame(GameEntity gameEntity);
    List<Game> toGameList(List<GameEntity> gameEntities);

    @InheritInverseConfiguration
    GameEntity toGameEntity(Game game);
}
