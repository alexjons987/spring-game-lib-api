package se.alexjons.gamelib.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.PublisherDTO;
import se.alexjons.gamelib.service.PublisherService;

@Validated
@RestController
@RequestMapping("/api/admin/publishers")
public class AdminPublisherController {

    private final PublisherService publisherService;

    public AdminPublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<PublisherDTO> addNewPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        return ResponseEntity.ok(publisherService.addPublisher(publisherDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable int id, @RequestBody PublisherDTO newPublisherDetailsDTO) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }
}
