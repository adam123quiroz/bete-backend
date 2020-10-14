package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.UserTeam;
import bo.edu.ucb.betebackend.domain.dto.TeamAndUserByCapitanResponse;
import bo.edu.ucb.betebackend.domain.repository.IUserTeamRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTeamServiceTest {
    final static Logger logger = LoggerFactory.getLogger(UserTeamServiceTest.class);


    @Autowired
    UserTeamService userTeamService;

    @Autowired
    TeamService teamService;

    @Autowired
    BeteUserDetailsService userDetailsService;

    @Test
    void whenSaveUserTeam_thenReturnUserTeamSavedNotNull() throws Exception {
        User user = userDetailsService.getUserById(1).orElseThrow(Exception::new);
        Team team = teamService.getTeamById(1).orElseThrow(Exception::new);

        UserTeam newUserTeam = new UserTeam();
        newUserTeam.setIsCaptain(1);
        newUserTeam.setTeam(team);
        newUserTeam.setUser(user);
        newUserTeam.setStatus(1);

        UserTeam userTeam = userTeamService.saveUserTeam(newUserTeam);
        assertNotNull(userTeam);
    }

    @Test
    void whenGetUserByTypeOfCapitan_thenReturnListUserTeam() {

        User user = new User();
        user.setIdUser(2);

        List<UserTeam> userTeamList = Arrays.asList(
                new UserTeam(1, 2,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(2, 1,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(3, 2,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(4, 2,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(5, 0,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(6, 0,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(7, -1,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(8, 2,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(9, 3,
                        new Team(1, "G2", "UCB", 1), user, 1),
                new UserTeam(10, 0,
                        new Team(1, "G2", "UCB", 1), user, 1)
        );

        IUserTeamRepository userTeamRepository = Mockito.mock(IUserTeamRepository.class);
        UserTeamService service = new UserTeamService(userTeamRepository);

        Mockito.when(service.getAllTeamUserByUserAndNotAnswer(Mockito.any())).thenReturn(userTeamList);

        List<Integer> integerList = service.getAllTeamUserByUserAndNotAnswer(user)
                .stream()
                .map(UserTeam::getIdUserTeam)
                .collect(Collectors.toList());

        List<Integer> idsList = Arrays.asList(5, 6, 10);

        assertEquals(integerList, idsList);

    }

    @Test
    void whenGetUserTeamById_thenReturnNotNull() throws Exception {
        Integer idUserTeam = 2;
        Optional<UserTeam> userTeamOptional = userTeamService.getUserTeamById(idUserTeam);

        assertNotNull(userTeamOptional.orElseThrow(Exception::new));

    }

    @Test
    void whenGetAllUserTeamByTeam_thenReturnListUserTeam() {
        Team team = new Team();
        team.setIdTeam(1);
        team.setTeamName("G2");
        team.setOrganization("UCB");
        team.setStatus(1);

        List<UserTeam> userTeams = userTeamService.getAllUserTeamByTeam(team);
        assertTrue(userTeams.size() > 0);
    }


    @Test
    void whenGetAllTeamAndUsersByCapitanUser_thenReturnTeamList() {
        User user = new User();
        user.setIdUser(2);

        List<TeamAndUserByCapitanResponse> teamList = userTeamService.getAllTeamAndUsersByCapitanUser(user);
        assertTrue(teamList.size() > 0);
    }

    @Test
    void whenChangeCapitan_thenChange() {
        Team team = new Team();
        team.setIdTeam(1);
        team.setTeamName("G2");
        team.setOrganization("UCB");
        team.setStatus(1);

        userTeamService.changeCapitan(team, 5,1);


    }

}