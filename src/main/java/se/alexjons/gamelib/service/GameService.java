package se.alexjons.gamelib.service;

import org.springframework.stereotype.Service;
import se.alexjons.gamelib.dto.GameRequestDTO;
import se.alexjons.gamelib.dto.GameResponseDTO;
import se.alexjons.gamelib.dto.PublisherDTO;
import se.alexjons.gamelib.entity.Game;
import se.alexjons.gamelib.entity.Publisher;
import se.alexjons.gamelib.exception.ResourceNotFoundException;
import se.alexjons.gamelib.mapper.GameMapper;
import se.alexjons.gamelib.repository.GameRepository;
import se.alexjons.gamelib.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

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

    public List<GameResponseDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();

        if (!games.isEmpty()) {
            return games.stream()
                    .map(gameMapper::toResponseDTO)
                    .toList();
        } else {
            throw new ResourceNotFoundException("No games were found");
        }
    }

    public GameResponseDTO getGameById(int id) {
        return gameRepository.findById(id)
                .map(gameMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find game with ID " + id));
    }

    public GameResponseDTO addNewGame(GameRequestDTO gameRequestDTO) {
        Publisher publisher = publisherRepository.findById(gameRequestDTO.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Publisher not found with ID: " + gameRequestDTO.getPublisherId()
                ));

        Game game = gameRepository.save(
                new Game(
                        gameRequestDTO.getTitle(),
                        gameRequestDTO.getGenre(),
                        gameRequestDTO.getRating(),
                        gameRequestDTO.getRelease(),
                        publisher
                ));

        return gameMapper.toResponseDTO(game);
    }

    public GameResponseDTO updateGame(int id, GameRequestDTO newGameDetailsDTO) {
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            if (newGameDetailsDTO.getTitle() != null) {
                game.get().setTitle(newGameDetailsDTO.getTitle());
            }
            if (newGameDetailsDTO.getGenre() != null) {
                game.get().setGenre(newGameDetailsDTO.getGenre());
            }
            if (newGameDetailsDTO.getRating() != null) {
                game.get().setRating(newGameDetailsDTO.getRating());
            }
            if (newGameDetailsDTO.getRelease() != null) {
                game.get().setReleaseDate(newGameDetailsDTO.getRelease());
            }
            if (newGameDetailsDTO.getPublisherId() != null) {
                Optional<Publisher> publisher = publisherRepository.findById(newGameDetailsDTO.getPublisherId());
                publisher.ifPresent(p -> game.get().setPublisher(p));
            }

            gameRepository.save(game.get());
            return gameMapper.toResponseDTO(game.get());
        }
        return null;
    }

    public boolean deleteGameById(int id) {
        if (gameRepository.existsById(id)) {
            gameRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
