package se.alexjons.gamelib.service;

import org.springframework.stereotype.Service;
import se.alexjons.gamelib.dto.GameDTO;
import se.alexjons.gamelib.entity.Game;
import se.alexjons.gamelib.mapper.GameMapper;
import se.alexjons.gamelib.repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    public GameService(GameMapper gameMapper, GameRepository gameRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }

    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();

        if (!games.isEmpty()) {
            return games.stream()
                    .map(gameMapper::toDTO)
                    .toList();
        } else {
            // TODO: Throw ResourceNotFoundException
        }

        return null; // TODO: Delete
    }


}
