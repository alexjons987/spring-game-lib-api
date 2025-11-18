package se.alexjons.gamelib.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.alexjons.gamelib.dto.GameDTO;
import se.alexjons.gamelib.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/public/games")
public class PublicGameController {

    // TODO: Add the following endpoints
    // GET /games/{id} - get game by id

    private final GameService gameService;

    public PublicGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable int id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }
}
