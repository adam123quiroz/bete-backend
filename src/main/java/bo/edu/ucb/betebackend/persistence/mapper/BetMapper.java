package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.persistence.entity.BetEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        GamblerMapper.class,
        MatchMapper.class,
        TeamMapper.class
})
public interface BetMapper {
    @Mappings({
            @Mapping(source = "idBet", target = "idBet"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "team", target = "team"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "gambler", target = "gambler"),
            @Mapping(source = "match", target = "match"),
            @Mapping(source = "teamIdTeam", target = "teamIdTeam"),
    })
    Bet toBet(BetEntity betEntity);
    List<Bet> toBetList(List<BetEntity> betEntity);

    @InheritInverseConfiguration
    BetEntity toBetEntity(Bet bet);
}
