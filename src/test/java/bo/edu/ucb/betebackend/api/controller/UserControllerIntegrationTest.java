package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMv;

    @Autowired
    private BeteUserDetailsService userDetailsService;

    @Test
    void getUserById() {
    }

    @Test
    void processRegistration() {
    }

    @Test
    void patchOrder() {
    }

    @Test
    void changeUserPassword() {
    }

    @Test
    void updateRolesUser() {
    }
}