package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.api.exception.RegionNotFoundException;
import bo.edu.ucb.betebackend.api.exception.RoleNotFoundException;
import bo.edu.ucb.betebackend.domain.*;
import bo.edu.ucb.betebackend.domain.dto.ChangeRoleUserRequest;
import bo.edu.ucb.betebackend.domain.dto.RegistrationUserForm;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import bo.edu.ucb.betebackend.domain.repository.IOrganizerRepository;
import bo.edu.ucb.betebackend.domain.repository.IRegionRepository;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import bo.edu.ucb.betebackend.domain.typeof.TypeOfUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger logger = LoggerFactory.getLogger(BeteUserDetailsService.class);


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
        User userWithRegion = regionRepository.getRegionById(newUser.getRegion())
                .map(region -> getUser(user, region))
                .orElseThrow(() -> new RegionNotFoundException(newUser.getRegion()));
        return userRepository.save(userWithRegion);
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
        } else if (role == TypeOfUsers.Organizer.getStatus()) {
            initOrganizer(user, bankCard);
        } else {
            throw new RoleNotFoundException(role);
        }
        return user;
    }

    public Optional<User> updateUser(User user) {
        return userRepository.getUserById(user.getIdUser())
                .map(existingUser -> getUser(user, existingUser));
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.getAllUsers().orElse(Collections.emptyList());
    }

    private User getUser(User user, User existingUser) {
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setCellphoneNumber(user.getCellphoneNumber());
        return existingUser;
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

    private User getUser(User user, Region region) {
        user.setRegion(region);
        return user;
    }


}
