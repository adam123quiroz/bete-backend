package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.User;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BeteUserDetailsServiceTest {
    @Autowired
    BeteUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadUserByUsername() {

        String username = "rodolfo";
        User user = userDetailsService.loadUserByUsername(username);
        assertThat(username, CoreMatchers.is(user.getUsername()));
    }

    @Test
    void registerNewUserAccount() {
    }

    @Test
    void getGameById() {
    }

    @Test
    void getAllUsers() {

        List<User>  userList = userDetailsService.findAllUsers();
        assertTrue(userList.size() > 0);
    }
}

