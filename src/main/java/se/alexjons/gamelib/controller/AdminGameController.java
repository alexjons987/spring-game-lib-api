package se.alexjons.gamelib.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.GameDTO;
import se.alexjons.gamelib.service.GameService;

@Validated
@RestController
@RequestMapping("/api/admin/games")
public class AdminGameController {

    private final GameService gameService;

    public AdminGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDTO> addNewGame(@Valid @RequestBody GameDTO gameDTO) {
        return ResponseEntity.ok(gameService.addNewGame(gameDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable int id, @RequestBody GameDTO newGameDetailsDTO) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }
}
