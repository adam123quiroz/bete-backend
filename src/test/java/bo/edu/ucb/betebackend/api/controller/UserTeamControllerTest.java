package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.service.UserTeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserTeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserTeamService userDetailsService;

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

}