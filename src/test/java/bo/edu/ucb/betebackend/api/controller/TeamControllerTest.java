package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.TeamFormUpdateRequest;
import bo.edu.ucb.betebackend.domain.service.TeamService;
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

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    @DisplayName("Test updating team data - PATCH /team/1/updating")
    void testUpdatingTeamData() throws Exception {
        Team team = new Team(1, "G2", "UCB", 1);
        Team teamUpdating = new Team(1, "OG", "Dota Teams", 1);
        TeamFormUpdateRequest formUpdateRequest = new TeamFormUpdateRequest("OG", "Dota Teams");

        Mockito.when(teamService.getTeamById(1))
                .thenReturn(Optional.of(team));
        Mockito.when(teamService.updateTeam(ArgumentMatchers.any(Team.class), ArgumentMatchers.any(TeamFormUpdateRequest.class)))
                .thenReturn(teamUpdating);

        mockMvc.perform(patch("/team/{idTeam}/updating", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(formUpdateRequest)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idTeam", is(1)))
                .andExpect(jsonPath("$.response.teamName", is("OG")))
                .andExpect(jsonPath("$.response.organization", is("Dota Teams")))
                .andExpect(jsonPath("$.response.status", is(1)));
    }

    @Test
    @DisplayName("Test null request - PATCH /team/1/updating")
    void testNullRequest() throws Exception {
        Team team = new Team(1, "G2", "UCB", 1);
        Team teamUpdating = new Team(1, "OG", "Dota Teams", 1);

        Mockito.when(teamService.getTeamById(1))
                .thenReturn(Optional.of(team));
        Mockito.when(teamService.updateTeam(ArgumentMatchers.any(Team.class), ArgumentMatchers.any(TeamFormUpdateRequest.class)))
                .thenReturn(teamUpdating);

        mockMvc.perform(patch("/team/{idTeam}/updating", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString("formUpdateRequest")))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.error").isNotEmpty());

    }

    @Test
    @DisplayName("Test not found user - PATCH /team/1/updating")
    void testNotFoundUser() throws Exception {
        TeamFormUpdateRequest formUpdateRequest = new TeamFormUpdateRequest("OG", "Dota Teams");

        Mockito.when(teamService.getTeamById(1))
                .thenReturn(Optional.empty());

        mockMvc.perform(patch("/team/{idTeam}/updating", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(formUpdateRequest)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.error").isNotEmpty());
    }
}