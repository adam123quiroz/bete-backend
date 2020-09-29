package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    final private BeteUserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<FormatResponse<User>> getUserById(
            @PathVariable("idUser") Integer idUser) {
        try {
            User user = userDetailsService.getUserById(idUser).orElseThrow(Exception::new);
            return new ResponseEntity<>(new FormatResponse<>(null, user), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new FormatResponse<>(
                    HttpStatus.BAD_REQUEST.toString(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/registration")
    @ApiOperation("Registration for new users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public HttpEntity<FormatResponse<User>> processRegistration(
            @RequestBody RegistrationUserForm form) {
        try {
            User user = userDetailsService.registerNewUserAccount(form, passwordEncoder);
            return new ResponseEntity<>(new FormatResponse<>(null, user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new FormatResponse<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PatchMapping("/{userId}")
    @ApiOperation("Registration for new users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public HttpEntity<FormatResponse<User>> patchOrder(
            @PathVariable("userId") Integer id,
            @RequestBody User patch) {
        try {
            User user = userDetailsService.getUserById(id).orElseThrow(Exception::new);
            if (patch.getUsername() != null) {
                user.setUsername(patch.getUsername());
            }
            if (patch.getEmail() != null) {
                user.setEmail(patch.getEmail());
            }
            if (patch.getCellphoneNumber() != 0) {
                user.setCellphoneNumber(patch.getCellphoneNumber());
            }
            if (patch.getIsPlayer() != -1) {
                user.setIsPlayer(patch.getIsPlayer());
            }
            if (patch.getIsOrganizer() != -1) {
                user.setIsOrganizer(patch.getIsOrganizer());
            }
            if (patch.getIsGambler() != -1) {
                user.setIsGambler(patch.getIsGambler());
            }
            return new ResponseEntity<>(new FormatResponse<>(null, user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new FormatResponse<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PatchMapping("/updatePassword")
    @ApiOperation("Updating password from users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<User>> changeUserPassword() {
        return null;
    }

    @CrossOrigin
    @PatchMapping("/role-update/{idUser}")
    @ApiOperation("Registration for new users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<User>> updateRolesUser(
            @PathVariable("idUser") Integer idUser,
            @RequestBody User user) {
        return null;
    }
}
