package se.alexjons.gamelib.service;

import org.springframework.stereotype.Service;
import se.alexjons.gamelib.dto.GameDTO;
import se.alexjons.gamelib.entity.Game;
import se.alexjons.gamelib.entity.Publisher;
import se.alexjons.gamelib.exception.ResourceNotFoundException;
import se.alexjons.gamelib.mapper.GameMapper;
import se.alexjons.gamelib.repository.GameRepository;
import se.alexjons.gamelib.repository.PublisherRepository;

import java.util.List;

@Service
public class GameService {

    private final GameMapper gameMapper;
    private final GameRepository gameRepository;
    private final PublisherRepository publisherRepository;

    public GameService(GameMapper gameMapper, GameRepository gameRepository, PublisherRepository publisherRepository) {
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();

        if (!games.isEmpty()) {
            return games.stream()
                    .map(gameMapper::toDTO)
                    .toList();
        } else {
            throw new ResourceNotFoundException("No games were found");
        }
    }

    public GameDTO getGameById(int id) {
        return gameRepository.findById(id)
                .map(gameMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find game with ID " + id));
    }

    public GameDTO addNewGame(GameDTO gameDTO) {
        Publisher publisher = publisherRepository.findById(gameDTO.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Publisher not found with ID: " + gameDTO.getPublisherId()
                ));

        Game game = gameRepository.save(
                new Game(
                        gameDTO.getTitle(),
                        gameDTO.getGenre(),
                        gameDTO.getRating(),
                        gameDTO.getRelease(),
                        publisher
                ));

        return gameMapper.toDTO(game);
    }
}
