package bo.edu.ucb.betebackend.persistence;

import bo.edu.ucb.betebackend.domain.Organizer;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.repository.IOrganizerRepository;
import bo.edu.ucb.betebackend.persistence.dao.OrganizerRepository;
import bo.edu.ucb.betebackend.persistence.dao.UserRepository;
import bo.edu.ucb.betebackend.persistence.entity.BeteUserEntity;
import bo.edu.ucb.betebackend.persistence.entity.OrganizerEntity;
import bo.edu.ucb.betebackend.persistence.mapper.OrganizerMapper;
import bo.edu.ucb.betebackend.persistence.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrganizerDao implements IOrganizerRepository {
    final private OrganizerRepository organizerRepository;
    final private OrganizerMapper organizerMapper;
    final private UserMapper userMapper;

    public OrganizerDao(UserMapper userMapper, OrganizerRepository organizerRepository, OrganizerMapper organizerMapper) {
        this.userMapper = userMapper;
        this.organizerRepository = organizerRepository;
        this.organizerMapper = organizerMapper;
    }

    @Override
    public Optional<Organizer> getOrganizerByUser(User user) {
        BeteUserEntity userEntity = userMapper.toUserEntity(user);
        OrganizerEntity organizerEntity = organizerRepository.findByIdUser(userEntity);
        return Optional.ofNullable(organizerMapper.toOrganizer(organizerEntity));
    }

    @Override
    public Optional<List<Organizer>> getAllOrganizers() {
        List<OrganizerEntity> organizerEntities = organizerRepository.findAll();
        return Optional.ofNullable(organizerMapper.toOrganizerList(organizerEntities));
    }

    @Override
    public Optional<Organizer> getOrganizerById(Integer id) {
        return organizerRepository.findById(id).map(organizerMapper::toOrganizer);
    }

    @Override
    public Organizer saveOrganizer(Organizer organizer) {
        return organizerMapper.toOrganizer(organizerRepository.save(organizerMapper.toOrganizerEntity(organizer)));
    }

    @Override
    public void removeOrganizer(Integer id) {
        organizerRepository.deleteById(id);
    }
}
