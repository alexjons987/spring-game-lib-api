package se.alexjons.gamelib.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.gamelib.dto.GameRequestDTO;
import se.alexjons.gamelib.dto.GameResponseDTO;
import se.alexjons.gamelib.entity.Game;
import se.alexjons.gamelib.entity.Publisher;
import se.alexjons.gamelib.repository.PublisherRepository;

@Component
public class GameMapper {

    private final PublisherRepository publisherRepository;

    public GameMapper(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public GameResponseDTO toResponseDTO(Game game) {
        if (game == null) return null;

        return new GameResponseDTO(
                game.getTitle(),
                game.getGenre(),
                game.getRating(),
                game.getReleaseDate(),
                game.getPublisher()
        );
    }

    public Game toEntity(GameRequestDTO gameRequestDTO) {
        if (gameRequestDTO == null) return null;

        Publisher publisher = publisherRepository.findById(gameRequestDTO.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Publisher not found with ID: " + gameRequestDTO.getPublisherId()
                ));

        return new Game(
                gameRequestDTO.getTitle(),
                gameRequestDTO.getGenre(),
                gameRequestDTO.getRating(),
                gameRequestDTO.getRelease(),
                publisher
        );
    }
}
