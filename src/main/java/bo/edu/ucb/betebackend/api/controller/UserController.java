package bo.edu.ucb.betebackend.api.controller;

import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.service.BeteUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    final private BeteUserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;

    public UserController(BeteUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public String processRegistration(@RequestBody RegistrationUserForm form) {
        System.out.println(form);
        userDetailsService.registerNewUserAccount(form, passwordEncoder);
        return "redirect:/login";
    }
}
