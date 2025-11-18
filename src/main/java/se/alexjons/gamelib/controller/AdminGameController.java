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
    // TODO: Add the following endpoints
    // POST /games - add new game
    // PUT /games/{id} - update existing game
    // DELETE /games/{id} - delete game

    private final GameService gameService;

    public AdminGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDTO> addNewGame(@Valid @RequestBody GameDTO gameDTO) {
        return ResponseEntity.ok(gameService.addNewGame(gameDTO));
    }
}
