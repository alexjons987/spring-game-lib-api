package se.alexjons.gamelib.dto;

public class AppUserResponseDTO {

    private String username;

    public AppUserResponseDTO() {
    }

    public AppUserResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
