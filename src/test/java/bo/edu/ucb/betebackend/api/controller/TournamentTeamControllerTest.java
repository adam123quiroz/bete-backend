package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.*;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import bo.edu.ucb.betebackend.domain.service.TournamentTeamService;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfIsFinishTournament;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TournamentTeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    @MockBean
    private TeamService teamService;

    @MockBean
    private TournamentTeamService tournamentTeamService;

    @Test
    @DisplayName("Test enroll a team on tournament - POST /tournament-team/enroll")
    void testEnrollATeamOnTournamentPost() throws Exception {
        //Prepare mock user
        Game game = new Game(1, "Dota", 1);
        Region mockRegion = new Region(1, "North America");
        User mockUser = new User(1, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, mockRegion);
        Organizer organizer = new Organizer(1, "A123ASNM5", 10, mockUser);
        Tournament tournament = new Tournament(1, "International", "Dota International 2020", 1, new Date(2019), new Date(2020),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        Team team = new Team(1, "G2", "UCB", 1);

        TournamentTeam tournamentTeam = new TournamentTeam(1, 1, team, tournament, 1);


        //Prepare mock service method
        Mockito.when(tournamentService.getTournamentById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(tournament));
        Mockito.when(teamService.getTeamById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(team));
        Mockito.when(tournamentTeamService.saveTournamentTeam(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(tournamentTeam);

        //Validate POST request
        mockMvc.perform(post("/tournament-team/enroll")
                //add parameters
                .param("idTeam", "1")
                .param("idTournament", "2"))
                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idTournamentTeam", is(1)))
                .andExpect(jsonPath("$.response.phase", is(1)))
                .andExpect(jsonPath("$.response.status", is(1)));
    }
}