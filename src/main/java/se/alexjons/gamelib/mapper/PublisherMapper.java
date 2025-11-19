package se.alexjons.gamelib.mapper;

import org.springframework.stereotype.Component;
import se.alexjons.gamelib.dto.PublisherDTO;
import se.alexjons.gamelib.entity.Publisher;

@Component
public class PublisherMapper {

    public PublisherDTO toDTO(Publisher publisher) {
        if (publisher == null) return null;

        return new PublisherDTO(
                publisher.getName(),
                publisher.getFounded()
        );
    }

    public Publisher toEntity(PublisherDTO publisherDTO) {
        if (publisherDTO == null) return null;

        return new Publisher(
                publisherDTO.getName(),
                publisherDTO.getFounded()
        );
    }
}
