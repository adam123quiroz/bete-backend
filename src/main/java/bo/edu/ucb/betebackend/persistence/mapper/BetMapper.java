package bo.edu.ucb.betebackend.persistence.mapper;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.persistence.entity.BetEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = {
        GamblerMapper.class,
        MatchMapper.class,
        TeamMapper.class
})
public interface BetMapper {
    Bet toBet(BetEntity betEntity);
    List<Bet> toBetList(List<BetEntity> betEntity);

    @InheritInverseConfiguration
    BetEntity toBetEntity(Bet bet);
}
