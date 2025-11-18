package se.alexjons.gamelib.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.gamelib.dto.GameDTO;
import se.alexjons.gamelib.entity.Game;
import se.alexjons.gamelib.entity.Publisher;
import se.alexjons.gamelib.repository.PublisherRepository;

@Component
public class GameMapper {

    private final PublisherRepository publisherRepository;

    public GameMapper(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public GameDTO toDTO(Game game) {
        if (game == null) return null;

        return new GameDTO(
                game.getTitle(),
                game.getGenre(),
                game.getRating(),
                game.getReleaseYear(),
                game.getPublisher().getPublisherId()
        );
    }

    public Game toEntity(GameDTO gameDTO) {
        if (gameDTO == null) return null;

        Publisher publisher = publisherRepository.findById(gameDTO.getPublisherId())
                .orElseThrow(() -> new IllegalArgumentException("Publisher not found with ID: " + gameDTO.getPublisherId()
                ));

        return new Game(
                gameDTO.getTitle(),
                gameDTO.getGenre(),
                gameDTO.getRating(),
                gameDTO.getReleaseYear(),
                publisher
        );
    }
}
