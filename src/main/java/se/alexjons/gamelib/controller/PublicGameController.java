package se.alexjons.gamelib.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.GameDTO;
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
    public ResponseEntity<List<GameDTO>> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGame(@PathVariable int id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }
}
