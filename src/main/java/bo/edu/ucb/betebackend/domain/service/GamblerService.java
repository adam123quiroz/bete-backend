package bo.edu.ucb.betebackend.domain.service;

import bo.edu.ucb.betebackend.domain.Gambler;
import bo.edu.ucb.betebackend.domain.User;
import bo.edu.ucb.betebackend.domain.dto.request.GamblerRequest;
import bo.edu.ucb.betebackend.domain.repository.IGamblerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GamblerService {
    private final IGamblerRepository gamblerRepository;

    public GamblerService(IGamblerRepository gamblerRepository) {
        this.gamblerRepository = gamblerRepository;
    }

    public Optional<Gambler> getGamblerByUser(User user) {
        return gamblerRepository.getGamblerByUser(user);
    }

    public Gambler buyGambler(User user, GamblerRequest request) {
        Gambler gambler = getGambler(user, request);
        return gamblerRepository.saveGambler(gambler);
    }

    public Optional<Gambler> getGamblerById(Integer id) {
        return gamblerRepository.getGamblerById(id);
    }

    private Gambler getGambler(User user, GamblerRequest request) {
        Gambler gambler = new Gambler();
        gambler.setIdUser(user);
        gambler.setCoins(request.getCoins());
        gambler.setBankCard(request.getCardBank());
        return gambler;
    }
}
