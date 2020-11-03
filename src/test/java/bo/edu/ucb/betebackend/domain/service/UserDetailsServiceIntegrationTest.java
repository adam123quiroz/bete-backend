package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserDetailsServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When get user then return json user - GET /user/1")
    void whenGetUserThenReturnJsonUser() throws Exception {

        //Perform GET request
        mockMvc.perform(get("/user/{idUser}", 1))
                //Validate 200 OK and JSON response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.idUser", is(1)))
                .andExpect(jsonPath("$.response.username", is("admin")))
                .andExpect(jsonPath("$.response.email", is("admin@example.com")));
    }

    @Test
    @DisplayName("Test get all users - GET /user/all")
    void testGetAllUsersGetUserAll() throws Exception {
        //Perform GET request
        mockMvc.perform(get("/user/all"))
                //Validate 200 OK and JSON response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate response body
                .andExpect(jsonPath("$.response.[0].username", is("admin")))
                .andExpect(jsonPath("$.response.[1].username", is("test")))
                .andExpect(jsonPath("$.response.[2].username", is("t1ncho")));
    }

}