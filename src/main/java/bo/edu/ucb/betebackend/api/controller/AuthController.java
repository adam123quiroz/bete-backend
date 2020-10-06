package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.security.JWTUtil;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationRequest;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationResponse;
import bo.edu.ucb.betebackend.domain.dto.FormatResponse;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
    final private AuthenticationManager authenticationManager;
    final private BeteUserDetailsService userDetailsService;
    final private JWTUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, BeteUserDetailsService userDetailsService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @CrossOrigin
    @PostMapping("/authenticate")
    @ApiOperation("authentication for registered users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found"),
    })
    public ResponseEntity<FormatResponse<AuthenticationResponse>> createToken(
            @RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userDetailsService.loadUserByUsername(request.getUsername());
//            System.out.println(userDetails);
            String jwt = jwtUtil.generateToken(user);
            return new ResponseEntity<>(new FormatResponse<>(null, new AuthenticationResponse(jwt, user)), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new FormatResponse<>(HttpStatus.FORBIDDEN.toString(), null),HttpStatus.FORBIDDEN);
        }
    }
}