package se.alexjons.gamelib.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.GameRequestDTO;
import se.alexjons.gamelib.dto.GameResponseDTO;
import se.alexjons.gamelib.service.GameService;

@Validated
@RestController
@RequestMapping("/api/admin/games")
public class AdminGameController {

    private final GameService gameService;

    public AdminGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping()
    public ResponseEntity<GameResponseDTO> addNewGame(@Valid @RequestBody GameRequestDTO gameRequestDTO) {
        return ResponseEntity.ok(gameService.addNewGame(gameRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameRequestDTO> updateGame(@PathVariable int id, @RequestBody GameRequestDTO newGameDetailsDTO) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        return ResponseEntity.status(501).build(); // TODO: Implement
    }
}
