package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.repository.IRegionRepository;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeteUserDetailsService implements UserDetailsService {
    final private IUserRepository userRepository;
    final private IRegionRepository regionRepository;

    public BeteUserDetailsService(IUserRepository userRepository, IRegionRepository regionRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public User registerNewUserAccount(RegistrationUserForm newUser, PasswordEncoder passwordEncoder) {
        User user = newUser.toUser(passwordEncoder);
        regionRepository
                .getRegionById(newUser.getRegion())
                .ifPresent(region -> {
                    user.setRegion(region);
                    userRepository.save(user);
                });
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
}
