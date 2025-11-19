package se.alexjons.gamelib.service;

import org.springframework.stereotype.Service;
import se.alexjons.gamelib.dto.PublisherDTO;
import se.alexjons.gamelib.entity.Publisher;
import se.alexjons.gamelib.exception.PublisherHasReferencedGamesException;
import se.alexjons.gamelib.exception.ResourceNotFoundException;
import se.alexjons.gamelib.mapper.PublisherMapper;
import se.alexjons.gamelib.repository.GameRepository;
import se.alexjons.gamelib.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;
    private final GameRepository gameRepository;

    public PublisherService(PublisherMapper publisherMapper, PublisherRepository publisherRepository, GameRepository gameRepository) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
        this.gameRepository = gameRepository;
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

    public PublisherDTO getPublisherById(int id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find publisher with ID " + id));
    }

    public PublisherDTO addPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherRepository.save(
                new Publisher(publisherDTO.getName(), publisherDTO.getFounded())
        );

        return publisherMapper.toDTO(publisher);
    }

    public PublisherDTO updatePublisher(int publisherId, PublisherDTO newPublisherDetailsDTO) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);

        if (publisher.isPresent()) {
            if (newPublisherDetailsDTO.getName() != null) {
                publisher.get().setName(newPublisherDetailsDTO.getName());
            }
            if (newPublisherDetailsDTO.getFounded() != null) {
                publisher.get().setFounded(newPublisherDetailsDTO.getFounded());
            }

            publisherRepository.save(publisher.get());
            return publisherMapper.toDTO(publisher.get());
        }
        return null;
    }

    public boolean deletePublisherById(int id) {
        if (gameRepository.existsByPublisherPublisherId(id)) {
            throw new PublisherHasReferencedGamesException("Cannot delete publisher with referenced games");
        }

        if (publisherRepository.existsById(id)) {
            publisherRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
