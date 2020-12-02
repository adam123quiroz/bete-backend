package bo.edu.ucb.betebackend.integration;

import bo.edu.ucb.betebackend.domain.dto.request.TournamentPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TournamentServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("test create a new tournament POST")
    void testCreateANewTournamentPost() throws Exception {
        //Prepare request
        TournamentPostRequest request = new TournamentPostRequest("International", "Dota International 2020"
                , "2020-11-03", "2020-12-05", 1, 1);

        //Validate POST request
        mockMvc.perform(post("/tournament/create")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(request)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response").isNotEmpty());

    }

    @Test
    @DisplayName("test get list tournament by user GET - /tournament/all/user/2")
    void testGetListTournamentByUserGet() throws Exception {
        //Validate GET request
        mockMvc.perform(get("/tournament/all/user/{idUser}", 2))
                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response[1]").isNotEmpty())
                .andExpect(jsonPath("$.response").isArray())
                .andExpect(jsonPath("$.response.[0].organizer.idUser.idUser", is(2)))
                .andExpect(jsonPath("$.response.[1].organizer.idUser.idUser", is(2)));

    }

    @Test
    @DisplayName("Test get list tournament that are coming - GET /tournament/coming")
    void testGetListTournamentThatAreComingGet() throws Exception {
        //Validate GET request
        mockMvc.perform(get("/tournament/coming"))
                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response[1]").isNotEmpty())
                .andExpect(jsonPath("$.response").isArray())
                .andExpect(jsonPath("$.response.[0].organizer.idUser.idUser", is(2)))
                .andExpect(jsonPath("$.response.[1].organizer.idUser.idUser", is(2)));

    }
}