package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.*;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfIsFinishTournament;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ITournamentRepositoryTest {
    @Autowired
    private ITournamentRepository tournamentRepository;

    @Test
    @DisplayName("Test tournament return the tournament saved")
    void testTournamentReturnTheTournamentSaved() throws ParseException {
        Game game = new Game(1, "Dota", 1);
        Region mockRegion = new Region(1, "North America");
        User mockUser = new User(2, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, mockRegion);
        Organizer organizer = new Organizer(1, "A123ASNM5", 10, mockUser);
        Tournament tournament = new Tournament("International", "Dota International 2020", 1, this.getDate("2020-12-16"),
                this.getDate("2020-12-20"),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        Tournament tournamentSaved = tournamentRepository.saveTournament(tournament);

        assertEquals(tournamentSaved.getName(), tournament.getName());
        assertEquals(tournamentSaved.getDescription(), tournament.getDescription());
        assertEquals(tournamentSaved.getStatus(), tournament.getStatus());
        assertEquals(tournamentSaved.getGame().getName(), tournament.getGame().getName());
        assertEquals(tournamentSaved.getOrganizer().getIdUser().getUsername(), tournament.getOrganizer().getIdUser().getUsername());
    }

    private Date getDate(String startDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return format.parse(startDate);
    }
}