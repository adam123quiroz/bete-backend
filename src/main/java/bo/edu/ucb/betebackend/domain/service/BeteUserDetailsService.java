package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.ChangeRoleUserRequest;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import bo.edu.ucb.betebackend.domain.repository.IOrganizerRepository;
import bo.edu.ucb.betebackend.domain.repository.IRegionRepository;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfUsers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BeteUserDetailsService implements UserDetailsService {
    final private IUserRepository userRepository;
    final private IRegionRepository regionRepository;
    final private IGamblerRepository gamblerRepository;
    final private IOrganizerRepository organizerRepository;

    public BeteUserDetailsService(IUserRepository userRepository, IRegionRepository regionRepository, IGamblerRepository gamblerRepository, IOrganizerRepository organizerRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.gamblerRepository = gamblerRepository;
        this.organizerRepository = organizerRepository;
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

    public User changePassword(User user, String newPassword, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User changeUserRole(ChangeRoleUserRequest changeRoleUserRequest, User user) {
        Integer role = changeRoleUserRequest.getTypeOf();
        String bankCard = changeRoleUserRequest.getPayment();
        if (role == TypeOfUsers.Gambler.getStatus()) {
            user.setIsGambler(1);
            Gambler gambler = new Gambler();
            gambler.setBankCard(bankCard);
            gambler.setCoins(0);
            gambler.setIdUser(user);
            gamblerRepository.saveGambler(gambler);
        }
        if (role == TypeOfUsers.Organizer.getStatus()) {
            user.setIsOrganizer(1);
            Organizer organizer = new Organizer();
            organizer.setBankCard(bankCard);
            organizer.setReputation(0);
            organizer.setIdUser(user);
            organizerRepository.saveOrganizer(organizer);
        }
        return user;
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
}
