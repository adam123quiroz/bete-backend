package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.exception.RegionNotFoundException;
import bo.edu.ucb.betebackend.api.exception.RoleNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.api.exception.UserPasswordNotEqualsException;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.ChangePasswordRequest;
import bo.edu.ucb.betebackend.domain.dto.ChangeRoleUserRequest;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    final private BeteUserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(BeteUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin
    @GetMapping("/{idUser}")
    @ApiOperation("Get user by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<User>> getUserById(@PathVariable String idUser) throws NumberFormatException {
        return userDetailsService.getUserById(Integer.valueOf(idUser))
                .map(user -> ResponseEntity
                        .ok()
                        .body(new FormatResponse<>(user)))
                .orElseThrow(() -> new UserNotFoundException(Integer.valueOf(idUser)));
    }

    @CrossOrigin
    @GetMapping("/all")
    @ApiOperation("get all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<List<User>>> getAllUsers() {
        List<User> users = userDetailsService.findAllUsers();
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(users));
    }

    @CrossOrigin
    @PostMapping("/registration")
    @ApiOperation("Registration for new users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<User>> processRegistration(
            @Valid @NotNull @RequestBody RegistrationUserForm form) throws RegionNotFoundException {
        User user = userDetailsService.registerNewUserAccount(form, passwordEncoder);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new FormatResponse<>(user));
    }

    @CrossOrigin
    @PatchMapping("/{userId}")
    @ApiOperation("Updating the user that already exists")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<User>> patchUser(
            @PathVariable("userId") String id,
            @Valid @NotNull @RequestBody User user
    ) throws NumberFormatException {
        Integer idInt = Integer.valueOf(id);
        User existingUser = userDetailsService.getUserById(idInt).orElseThrow(() -> new UserNotFoundException(idInt));
        logger.info("Updating user with name:{}", existingUser.getUsername());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setCellphoneNumber(user.getCellphoneNumber());
        existingUser = userDetailsService.updateUser(existingUser).orElseThrow(() -> new UserNotFoundException(idInt));
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(existingUser));
    }

    @CrossOrigin
    @PatchMapping("/{idUser}/update-password")
    @ApiOperation("Updating password from users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<? extends FormatResponse<?>> changeUserPassword(
            @PathVariable("idUser") String idUser,
            @Valid @NotNull @RequestBody ChangePasswordRequest changePasswordRequest
    ) throws NumberFormatException {
        Integer idInt = Integer.valueOf(idUser);
        return userDetailsService.getUserById(idInt).map(user -> {
            if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
                throw new UserPasswordNotEqualsException(idInt);
            }
            String newPassword = changePasswordRequest.getNewPassword();
            User userWithNewPassword = userDetailsService.changePassword(user, newPassword, passwordEncoder);
            return ResponseEntity
                    .ok()
                    .body(new FormatResponse<>(userWithNewPassword));
        }).orElseThrow(() -> new UserNotFoundException(idInt));
    }

    @CrossOrigin
    @PatchMapping("/{idUser}/role-update")
    @ApiOperation("Registration for new users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<User>> updateRolesUser(
            @PathVariable("idUser") String idUser,
            @Valid @RequestBody ChangeRoleUserRequest userRequest)
            throws NumberFormatException, RoleNotFoundException {

        Integer id = Integer.valueOf(idUser);
        User user = userDetailsService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        User userNewRole = userDetailsService.changeUserRole(userRequest, user);
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(userNewRole));
    }
}
