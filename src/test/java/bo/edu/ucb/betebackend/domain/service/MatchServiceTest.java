package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Bet;
import bo.edu.ucb.betebackend.domain.Match;
import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.Tournament;
import bo.edu.ucb.betebackend.domain.repository.IBetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @MockBean
    private IBetRepository betRepository;

    @Test
    @DisplayName("Test method that given the except")
    void testMethodThatGivenTheExcept() {
        Team team1 = new Team(1, "GG", "UCB", 1);
        Team team2 = new Team(2, "GG2", "UCB", 1);
        Tournament tournament = new Tournament(1, "Dota", "GG dota", 1, null,
                null, 0, null, null);
        Match match = new Match(1, 0, 1, new Date(), new Date(), 0, null,
                1, team1, team2, tournament);
        Match match2 = new Match(2, 0, 1, new Date(), new Date(), 0, null,
                1, team1, team2, tournament);
        List<Match> matches = Arrays.asList(match, match2);

        Bet bet = new Bet(1, 100, 1, new Date(), null, match, team1);
        Bet bet2 = new Bet(2, 100, 2, new Date(), null, match, team2);
        Bet bet3 = new Bet(3, 100, 1, new Date(), null, match, team1);
        Bet bet4 = new Bet(4, 100, 2, new Date(), null, match, team2);
        Bet bet5 = new Bet(5, 100, 1, new Date(), null, match, team1);

        List<Bet> bets = Arrays.asList(bet, bet2, bet3, bet4, bet5);

        Mockito.when(betRepository.getAllBetsByMatch(ArgumentMatchers.any()))
                .thenReturn(Optional.of(bets));
        List<?> list = matchService.getListMatchWithOutcomeForecast(matches).orElse(Collections.emptyList());

        assertNotNull(list);
    }
}