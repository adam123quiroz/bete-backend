package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.persistence.dao.TeamRepository;
import bo.edu.ucb.betebackend.persistence.mapper.TeamMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamServiceTest {
    final static Logger logger = LoggerFactory.getLogger(TeamServiceTest.class);


    @Autowired
    TeamService teamService;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamMapper teamMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void whenSaveTeam_thenReturnTheTeamSaved() {
        Team team = new Team();
        team.setTeamName("GWR@$4");
        team.setOrganization("UCB");
        team.setStatus(1);

        Team teamSaved = teamService.saveTeam(team);
        assertThat(teamSaved, is(new Team(23, "GWR@$4", "UCB", 1)));
        teamRepository.delete(teamMapper.toTeamEntity(teamSaved));
    }

    @Test
    void whenGetTeamById_thenReturnNotNull() throws Exception {
        Integer idTeam = 2;
        Team team = teamService.getTeamById(idTeam).orElseThrow(Exception::new);
//        logger.info(String.valueOf(team.getUserTeamList().size()));
        logger.info(String.valueOf(team.getTeamName()));
        assertNotNull(team);
    }

}