package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.Team;
import bo.edu.ucb.betebackend.domain.dto.ChangeCaptainRequest;
import bo.edu.ucb.betebackend.domain.dto.RequestUpdateIsCapitan;
import bo.edu.ucb.betebackend.domain.dto.TeamUserRequest;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfIsCapitanUserTeam;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserTeamControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenTeamUserRequest_whenPostSaveTeamWithUsers_theReturnStatusOk() throws Exception {
        Team newTeam = new Team();
        newTeam.setTeamName("G3");
        newTeam.setOrganization("UCB");
        newTeam.setStatus(1);

        List<Integer> idsUserList = Arrays.asList(1,2);

        TeamUserRequest teamUserRequest = new TeamUserRequest();
        teamUserRequest.setTeam(newTeam);
        teamUserRequest.setIsCapitan(TypeOfIsCapitanUserTeam.TheCapitan.getStatus());
        teamUserRequest.setIdsUserList(idsUserList);

        mockMvc.perform(post("/team-user/creating")
                .content(asJsonString(teamUserRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is("200 OK")));

    }

    @Test
    void givenIdUserPath_whenGetRequestNotAnswer_thenReturnJsonResponseNotAnswer() throws Exception {
        int idUser = 2;

        mockMvc.perform(get("/team-user/"+idUser+"/not-answer")
                .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void givenRequestUpdateIsCapitan_whenPatchUpdateIsCapitan_thenReturnStatusOk() throws Exception {
        RequestUpdateIsCapitan request = new RequestUpdateIsCapitan(3, 1);

        mockMvc.perform(patch("/team-user/update")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void givenIdUserPath_whenGetTheUsersAndTeamByCapitan_thenReturnJsonTeamAndUserByCapitanResponse() throws Exception {
        int idUser = 2;

        mockMvc.perform(get("/team-user/capitan/"+idUser)
                .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void givenChangeCaptainRequest_whenPostChangeCaptainUserTeam_thenReturnStatusOk() throws Exception {
        ChangeCaptainRequest request = new ChangeCaptainRequest();
        request.setIdTeam(1);
        request.setIdUserOldCaptainInteger(5);
        request.setIdUserNewCaptainInteger(1);

        mockMvc.perform(post("/team-user/change-captain/")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk());

    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}