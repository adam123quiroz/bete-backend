package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.TournamentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TournamentRepositoryTest {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Test
    @DisplayName("Test repository get list tournament by date start")
    void testRepositoryGetListTournamentByDateStart() {
        List<TournamentEntity> allByStartDateAfter = tournamentRepository.findAllByStartDateAfter(new Date());
        Integer[] tournamentIds = {4, 5};
        assertArrayEquals(allByStartDateAfter.stream().map(TournamentEntity::getIdTournament).toArray(), tournamentIds);
    }

}