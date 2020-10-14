package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.ChangePasswordRequest;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BeteUserDetailsService userDetailsService;

    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("user");
        user.setPassword("$2a$10$Vgs9vHlLXhT8dgFe6MpqZOBHEc9xWmYV6pQHs14VKBW.yozBSca8u");
        user.setIdUser(2);

        Integer idUser = 2;

        Mockito.when(userDetailsService.getUserById(idUser))
                .thenReturn(java.util.Optional.ofNullable(user));
    }

    @Test
    void givenIdUser_whenGetUserId_thenReturnJsonUser() throws Exception {
        Integer idUser = 2;

        getResultActions(idUser);
    }


    @Test
    void givenUserForm_whenPostUser_thenReturnJsonNewUser() throws Exception {

        RegistrationUserForm form = new RegistrationUserForm();
        form.setUsername("adam123quiroz");
        form.setPassword("1234");

        Mockito.when(userDetailsService.registerNewUserAccount(
                Mockito.any(RegistrationUserForm.class),
                Mockito.any(PasswordEncoder.class)
        ))
                .thenReturn(user);

        getResultActions(user, post("/user/registration"), asJsonString(form));
    }

    @Test
    void givenIdUserPathAndUser_whenPatchUser_thenReturnJsonUserModified() throws Exception {
        User userModified = new User();
        userModified.setUsername("adam1234quiroz");

        int idUser = 2;
        getResultActions(userModified, patch("/user/" + idUser), asJsonString(userModified));
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
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.response.password", is(request.getNewPassword())));

    }

    @Test
    void updateRolesUser() {
    }

    private void getResultActions(User userModified, MockHttpServletRequestBuilder patch, String s) throws Exception {
        mockMvc.perform(patch
                .content(s)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.username", is(userModified.getUsername())));
    }

    private void getResultActions(Integer idUser) throws Exception {
        mockMvc.perform(get("/user/" + idUser)
                .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.username", is(user.getUsername())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}