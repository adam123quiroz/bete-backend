package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Region;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationRequest;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeteUserDetailsService userDetailsService;
    @MockBean
    private AuthenticationManager authenticationManager;

    private User mockUser;
    private Region mockRegion;

    @BeforeEach
    void setUp() {
        mockRegion = new Region(1, "North America");
        mockUser = new User(2, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, mockRegion);
    }

    @Test
    @DisplayName("Given a AuthRequest then return Auth Response - POST /auth/authenticate")
    void givenAAuthRequestThenReturnAuthResponse() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "contrasenia1");

        Mockito.when(userDetailsService.loadUserByUsername(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);

        //Validate POST request
        mockMvc.perform(post("/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(authenticationRequest)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.jwt").isNotEmpty())
                .andExpect(jsonPath("$.response.user.idUser", is(2)))
                .andExpect(jsonPath("$.response.user.username", is("adam")))
                .andExpect(jsonPath("$.response.user.email", is("adam@example.com")))
                .andExpect(jsonPath("$.response.user.name", is("Adam")))
                .andExpect(jsonPath("$.response.user.countryCode", is(1)))
                .andExpect(jsonPath("$.response.user.cellphoneNumber", is(75258550)))
                .andExpect(jsonPath("$.response.user.isPlayer", is(1)))
                .andExpect(jsonPath("$.response.user.isOrganizer", is(0)))
                .andExpect(jsonPath("$.response.user.isGambler", is(0)))
                .andExpect(jsonPath("$.response.user.password", is("adam1234")))
                .andExpect(jsonPath("$.response.user.status", is(1)))
                .andExpect(jsonPath("$.response.user.region.idRegion", is(1)));
    }

}