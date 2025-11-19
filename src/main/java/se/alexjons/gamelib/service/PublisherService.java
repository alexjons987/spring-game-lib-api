package se.alexjons.gamelib.service;

import org.springframework.stereotype.Service;
import se.alexjons.gamelib.dto.PublisherDTO;
import se.alexjons.gamelib.entity.Publisher;
import se.alexjons.gamelib.exception.ResourceNotFoundException;
import se.alexjons.gamelib.mapper.PublisherMapper;
import se.alexjons.gamelib.repository.PublisherRepository;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherMapper publisherMapper, PublisherRepository publisherRepository) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();

        if (!publishers.isEmpty()) {
            return publishers.stream()
                    .map(publisherMapper::toDTO)
                    .toList();
        } else {
            throw new ResourceNotFoundException("No publishers were found");
        }
    }

    public PublisherDTO addPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherRepository.save(
                new Publisher(publisherDTO.getName(), publisherDTO.getFounded())
        );

        return publisherMapper.toDTO(publisher);
    }
}
