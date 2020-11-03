package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.security.JwtSecondUtil;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationRequest;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationResponse;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@CrossOrigin("http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
    final private AuthenticationManager authenticationManager;
    final private BeteUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, BeteUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @CrossOrigin
    @PostMapping("/authenticate")
    @ApiOperation("authentication for registered users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<AuthenticationResponse>> createToken(
            @Valid @NotNull @RequestBody AuthenticationRequest request
    ) throws BadCredentialsException, UsernameNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userDetailsService.loadUserByUsername(request.getUsername());
        String jwt = JwtSecondUtil.addAuthentication(user.getUsername());
        return ResponseEntity
                .ok()
                .body(new FormatResponse<>(new AuthenticationResponse(jwt, user)));
    }
}