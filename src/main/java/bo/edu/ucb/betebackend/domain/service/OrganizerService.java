package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.api.exception.UserNotFoundException;
import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.repository.IOrganizerRepository;
import bo.edu.ucb.betebackend.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {
    private final IOrganizerRepository organizerRepository;
    private final IUserRepository userRepository;

    public OrganizerService(IOrganizerRepository organizerRepository, IUserRepository userRepository) {
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
    }

    public Optional<Organizer> getOrganizerById(Integer id) {
        return organizerRepository.getOrganizerById(id);
    }

    public Optional<Organizer> getOrganizerByUser(Integer idUser) {
        User user = userRepository.getUserById(idUser)
                .orElseThrow(() -> new UserNotFoundException(idUser));
        return organizerRepository.getOrganizerByUser(user);
    }
}

