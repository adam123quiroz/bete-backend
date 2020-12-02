package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.*;
import bo.edu.ucb.betebackend.domain.dto.request.TournamentPostRequest;
import bo.edu.ucb.betebackend.domain.dto.request.TournamentRequestUpdate;
import bo.edu.ucb.betebackend.domain.service.GameService;
import bo.edu.ucb.betebackend.domain.service.OrganizerService;
import bo.edu.ucb.betebackend.domain.service.TournamentService;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfIsFinishTournament;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TournamentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    @MockBean
    private OrganizerService organizerService;

    @MockBean
    private GameService gameService;

    @Test
    @DisplayName("Test api create save tournament - POST /tournament/create")
    void testApiCreateSaveTournamentPost() throws Exception {
        //Prepare mock user
        TournamentPostRequest request = new TournamentPostRequest("International", "Dota International 2020"
                , "2020-11-03", "2020-12-05", 1, 1);

        Game game = new Game(1, "Dota", 1);
        Region mockRegion = new Region(1, "North America");
        User mockUser = new User(2, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, mockRegion);
        Organizer organizer = new Organizer(1, "A123ASNM5", 10, mockUser);
        Tournament tournament = new Tournament(1, "International", "Dota International 2020", 1, new Date(2019), new Date(2020),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        //Prepare mock service method
        Mockito.when(tournamentService.saveTournament(ArgumentMatchers.any())).thenReturn(tournament);
        Mockito.when(organizerService.getOrganizerById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(organizer));
        Mockito.when(gameService.getGameById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(game));

        //Validate POST request
        mockMvc.perform(post("/tournament/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(request)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idTournament", is(1)))
                .andExpect(jsonPath("$.response.name", is("International")))
                .andExpect(jsonPath("$.response.description", is("Dota International 2020")))
                .andExpect(jsonPath("$.response.status", is(1)));
    }

    @Test
    @DisplayName("Test get tournaments by user GET /tournament/all/user/1")
    void testGetTournamentsByUserGet() throws Exception {
        //Prepare mock user
        Game game = new Game(1, "Dota", 1);
        Region mockRegion = new Region(1, "North America");
        User mockUser = new User(1, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, mockRegion);
        Organizer organizer = new Organizer(1, "A123ASNM5", 10, mockUser);
        Tournament tournament = new Tournament(1, "International", "Dota International 2020", 1, new Date(2019), new Date(2020),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        Tournament secondTournament = new Tournament(2, "International - Latam", "Dota International 2020", 1, new Date(2019), new Date(2020),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        List<Tournament> tournaments = Arrays.asList(tournament, secondTournament);

        //Prepare mock service method
        Mockito.when(organizerService.getOrganizerByUser(ArgumentMatchers.anyInt())).thenReturn(Optional.of(organizer));
        Mockito.when(tournamentService.getTournamentsByOrganizer(ArgumentMatchers.any(Organizer.class))).thenReturn(Optional.of(tournaments));

        //Validate POST request
        mockMvc.perform(get("/tournament/all/user/{idUser}", 1))
                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response[0].idTournament", is(1)))
                .andExpect(jsonPath("$.response[1].idTournament", is(2)))
                .andExpect(jsonPath("$.response.[0].organizer.idUser.idUser", is(1)))
                .andExpect(jsonPath("$.response.[1].organizer.idUser.idUser", is(1)));
    }

    @Test
    @DisplayName("Test update tournament - PATCH /tournament/1/update")
    void testUpdateTournamentPatch() throws Exception {
        //Prepare mock user
        TournamentRequestUpdate requestUpdate = new TournamentRequestUpdate();
        requestUpdate.setGame(4);

        Game game = new Game(1, "Dota", 1);
        Region mockRegion = new Region(1, "North America");
        User mockUser = new User(1, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, mockRegion);
        Organizer organizer = new Organizer(1, "A123ASNM5", 10, mockUser);
        Tournament tournament = new Tournament(1, "International", "Dota International 2020", 1, new Date(2019), new Date(2020),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        Tournament tournamentUpdated = new Tournament(1, "International", "Dota International 2021", 1, new Date(2019), new Date(2020),
                TypeOfIsFinishTournament.TournamentNotFinished.getTypeOfIsFinishTournament(), game, organizer);

        //Prepare mock service method
        Mockito.when(tournamentService.getTournamentById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(tournament));
        Mockito.when(tournamentService.updateTournament(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(tournamentUpdated);

        //Validate POST request
        mockMvc.perform(patch("/tournament/{idTournament}/update", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(requestUpdate)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idTournament", is(1)))
                .andExpect(jsonPath("$.response.description", is("Dota International 2021")))
                .andExpect(jsonPath("$.response.organizer.idUser.idUser", is(1)));

    }

    @Test
    @DisplayName("Test ")
    void test() {


    }

}