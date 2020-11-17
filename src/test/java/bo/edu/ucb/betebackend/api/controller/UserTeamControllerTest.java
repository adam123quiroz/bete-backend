package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.TeamWithUsersResponse;
import bo.edu.ucb.betebackend.domain.dto.model.UserResponse;
import bo.edu.ucb.betebackend.domain.service.TeamService;
import bo.edu.ucb.betebackend.domain.service.UserTeamService;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserTeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserTeamService userTeamService;
    @MockBean
    private TeamService teamService;

    @Test
    @DisplayName("Test for delete a user from a team - DELETE /team-user/{idUser}/remove ")
    void testForDeleteAUserFromATeam() throws Exception {

        //Validate DELETE request
        mockMvc.perform(delete("/team-user/{idUser}/remove", 1))
                //Validate 200 ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response").isNotEmpty())
                .andExpect(jsonPath("S.response").isNumber());
    }

    @Test
    @DisplayName("Test team's user not found - DELETE /team-user/{idUser}/remove")
    void testTeamSUserNotFound() throws Exception {

        //Validate DELETE request
        mockMvc.perform(delete("/team-user/{idUser}/remove", 1))
                //Validate 400 Not Found and JSON response type received
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.response").isNotEmpty())
                .andExpect(jsonPath("$.response").isNumber());

    }

    @Test
    @DisplayName("Test when get team return response JSON - GET /user-team/team/1")
    void testWhenGetTeamReturnResponseJson() throws Exception {
        Team team = new Team(1, "OG", "Dotas E-Sports", 1);
        UserResponse userResponse = new UserResponse(1, "adam", 2);
        UserResponse userResponseSecond = new UserResponse(2, "alda", 0);
        UserResponse userResponseThird = new UserResponse(3, "mau", 0);
        TeamWithUsersResponse response = new TeamWithUsersResponse(
                1,
                "OG",
                "Dotas E-Sports",
                Arrays.asList(userResponse, userResponseSecond, userResponseThird)
        );

        Mockito.when(teamService.getTeamById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(team));
        Mockito.when(userTeamService.getTeamWithUsersById(ArgumentMatchers.any(Team.class)))
                .thenReturn(response);

        mockMvc.perform(get("/team-user/team/{idTeam}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.response.idTeam", is(1)))
                .andExpect(jsonPath("$.response.name", is("OG")))
                .andExpect(jsonPath("$.response.organization", is("Dotas E-Sports")))
                .andExpect(jsonPath("$.response.userResponseList[0].idUser", is(1)))
                .andExpect(jsonPath("$.response.userResponseList[1].idUser", is(2)))
                .andExpect(jsonPath("$.response.userResponseList[2].idUser", is(3)));
    }
}