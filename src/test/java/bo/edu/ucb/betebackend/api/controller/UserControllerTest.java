package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Region;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.ChangePasswordRequest;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BeteUserDetailsService userDetailsService;

    @Test
    @DisplayName("Test user found - GET /user/2")
    void givenIdUserPath_whenGetUserId_thenReturnJsonUser() throws Exception {
        // Prepare mock users
        Region region = new Region(1, "North America");
        User user = new User(2, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, region);

        //Prepare mock service method
        Mockito.when(userDetailsService.getUserById(2)).thenReturn(Optional.of(user));

        //Validate GET request
        mockMvc.perform(get("/user/{idUser}", 2))
                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idUser", is(2)))
                .andExpect(jsonPath("$.response.username", is("adam")))
                .andExpect(jsonPath("$.response.email", is("adam@example.com")))
                .andExpect(jsonPath("$.response.name", is("Adam")))
                .andExpect(jsonPath("$.response.countryCode", is(1)))
                .andExpect(jsonPath("$.response.cellphoneNumber", is(75258550)))
                .andExpect(jsonPath("$.response.isPlayer", is(1)))
                .andExpect(jsonPath("$.response.isOrganizer", is(0)))
                .andExpect(jsonPath("$.response.isGambler", is(0)))
                .andExpect(jsonPath("$.response.password", is("adam1234")))
                .andExpect(jsonPath("$.response.status", is(1)))
                .andExpect(jsonPath("$.response.region.idRegion", is(1)));
    }

    @Test
    @DisplayName("Test all users found - GET /user/all")
    void whenGetAllUsers_thenReturnJsonListUsers() throws Exception {
        //Prepare mock users
        Region region = new Region(1, "North America");
        User firstUser = new User(1, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, region);
        User secondUser = new User(2, "marco", "marco@example.com", "Marco", "Quiroz",
                1, 75158450, 1, 0, 0, "marco1234", 1, region);

        List<User> users = new ArrayList<>();
        users.add(firstUser);
        users.add(secondUser);

        //Prepare mock service method
        Mockito.when(userDetailsService.findAllUsers()).thenReturn(users);

        //Validate GET request
        mockMvc.perform(get("/user/all"))
                //Validate 200 Ok and JSON response type received
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response[0].username", is("adam")))
                .andExpect(jsonPath("$.response[1].username", is("marco")));
    }

    @Test
    void givenUserForm_whenPostUser_thenReturnJsonNewUser() throws Exception {
        //Prepare mock user
        RegistrationUserForm form = new RegistrationUserForm();
        form.setUsername("adam");
        form.setPassword("adam1234");
        Region region = new Region(1, "North America");
        User mockUser = new User(1, "adam", "adam@example.com", "Adam", "Quiroz",
                1, 75258550, 1, 0, 0, "adam1234", 1, region);

        //Prepare mock service method
        Mockito.when(userDetailsService.registerNewUserAccount(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(mockUser);

        //Validate POST request
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(form)))

                //Validate 200 Ok and JSON response type received
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idUser", is(1)))
                .andExpect(jsonPath("$.response.username", is("adam")))
                .andExpect(jsonPath("$.response.email", is("adam@example.com")))
                .andExpect(jsonPath("$.response.name", is("Adam")))
                .andExpect(jsonPath("$.response.countryCode", is(1)))
                .andExpect(jsonPath("$.response.cellphoneNumber", is(75258550)))
                .andExpect(jsonPath("$.response.isPlayer", is(1)))
                .andExpect(jsonPath("$.response.isOrganizer", is(0)))
                .andExpect(jsonPath("$.response.isGambler", is(0)))
                .andExpect(jsonPath("$.response.password", is("adam1234")))
                .andExpect(jsonPath("$.response.status", is(1)))
                .andExpect(jsonPath("$.response.region.idRegion", is(1)));
    }

    @Test
    void givenIdUserPathAndUser_whenPatchUser_thenReturnJsonUserModified() throws Exception {
        //Prepare mock user
        User userModified = new User();
        userModified.setUsername("adam1234quiroz");

        //Prepare mock service method
        mockMvc.perform(patch("/user/all")
                .content(new ObjectMapper().writeValueAsString(userModified))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.username", is(userModified.getUsername())));
    }

    @Test
    void givenIdUserPathAndChangePasswordRequest_whenPatchUserPassword_thenReturnJsonUser() throws Exception {
        int idUser = 2;

        User userPasswordChanged = new User();
        userPasswordChanged.setPassword("12345");

        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setNewPassword("12345");
        request.setOldPassword("contrasenia1");


        Mockito.when(userDetailsService.changePassword(
                Mockito.any(User.class),
                Mockito.any(String.class),
                Mockito.any(PasswordEncoder.class)
        ))
                .thenReturn(userPasswordChanged);

        mockMvc.perform(patch("/user/"+idUser+"/update-password")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.response.password", is(request.getNewPassword())));

    }

    @Test
    void updateRolesUser() {
    }
}