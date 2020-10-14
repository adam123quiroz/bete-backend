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

import java.util.Collections;
import java.util.List;
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
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
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

    public User changeUserRole(ChangeRoleUserRequest changeRoleUserRequest, User user) {
        Integer role = changeRoleUserRequest.getTypeOf();
        String bankCard = changeRoleUserRequest.getPayment();
        if (role == TypeOfUsers.Gambler.getStatus()) {
            initGambler(user, bankCard);
        }
        if (role == TypeOfUsers.Organizer.getStatus()) {
            initOrganizer(user, bankCard);
        }
        return user;
    }

    private void initOrganizer(User user, String bankCard) {
        user.setIsOrganizer(1);
        Organizer organizer = new Organizer();
        organizer.setBankCard(bankCard);
        organizer.setReputation(0);
        organizer.setIdUser(user);
        organizerRepository.saveOrganizer(organizer);
    }

    private void initGambler(User user, String bankCard) {
        user.setIsGambler(1);
        Gambler gambler = new Gambler();
        gambler.setBankCard(bankCard);
        gambler.setCoins(0);
        gambler.setIdUser(user);
        gamblerRepository.saveGambler(gambler);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.getAllUsers().orElse(Collections.emptyList());
    }
}
