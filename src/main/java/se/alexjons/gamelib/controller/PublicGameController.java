package se.alexjons.gamelib.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.GameResponseDTO;
import se.alexjons.gamelib.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/public/games")
public class PublicGameController {

    private final GameService gameService;

    public PublicGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> getGame(@PathVariable int id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GameResponseDTO>> searchGames(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float maxRating,
            @RequestParam(required = false) String publisher) {
        if (title == null && genre == null && minRating == null && maxRating == null && publisher == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(501).build(); // TODO: Implement
        }
    }
}
