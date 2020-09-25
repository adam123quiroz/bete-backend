package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.api.security.JWTUtil;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationRequest;
import bo.edu.ucb.betebackend.domain.dto.AuthenticationResponse;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
            System.out.println(request.getUsername()+ request.getPassword());
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
