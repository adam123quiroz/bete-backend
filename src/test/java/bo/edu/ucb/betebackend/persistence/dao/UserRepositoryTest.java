package bo.edu.ucb.betebackend.persistence.dao;

import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUsername() {
        BeteUserEntity userEntity = new BeteUserEntity(
                6, "martin", "$2a$10$FG1gNRHOAiw.uTWeZ2OZb.uLAK3K3mmXYU3TiEH9AwnW1V7mwq8O.",
                "martin@gmail.com", "Martin", "Campuzano", 1, 591,
                71560936, 0, 0, 0
        );
        String username = "martin";
        BeteUserEntity usernameTest = userRepository.findByUsername(username);
//        assertThat(userEntity, is(usernameTest));
    }

    @Test
    void whenFindById_thenReturnUser() throws Exception {
        BeteUserEntity userEntity = new BeteUserEntity();
        userEntity.setIdUser(3);
        entityManager.persist(userEntity);
        entityManager.flush();

        Optional<BeteUserEntity> optional = userRepository.findById(userEntity.getIdUser());
        BeteUserEntity found = optional.orElseThrow(Exception::new);

        assertThat(found.getName())
                .isEqualTo(userEntity.getName());
    }

}