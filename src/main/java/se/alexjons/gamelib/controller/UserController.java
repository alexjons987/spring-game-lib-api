package se.alexjons.gamelib.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.alexjons.gamelib.dto.AppUserRequestDTO;
import se.alexjons.gamelib.dto.AppUserResponseDTO;
import se.alexjons.gamelib.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<AppUserResponseDTO> addNewUser(@Valid @RequestBody AppUserRequestDTO appUserRequestDTO) {
        return ResponseEntity.status(501).build();
    }
}
