package se.alexjons.gamelib.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.PublisherDTO;
import se.alexjons.gamelib.service.PublisherService;

import java.util.List;

@RestController
@RequestMapping("/api/public/publishers")
public class PublicPublisherController {

    private final PublisherService publisherService;

    public PublicPublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable int id) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }
}
