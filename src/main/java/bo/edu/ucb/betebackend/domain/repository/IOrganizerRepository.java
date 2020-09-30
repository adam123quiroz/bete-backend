package bo.edu.ucb.betebackend.domain.repository;

import bo.edu.ucb.betebackend.domain.Organizer;

import java.util.List;
import java.util.Optional;

public interface IOrganizerRepository {
    Optional<List<Organizer>> getAllOrganizers();
    Optional<Organizer> getOrganizerById(Integer id);
    Organizer saveOrganizer(Organizer organizer);
    void removeOrganizer(Integer id);
}
