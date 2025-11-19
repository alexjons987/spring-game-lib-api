package se.alexjons.gamelib.dto;

public class AppUserRequestDTO {

    private String username;
    private String password;

    public AppUserRequestDTO() {
    }

    public AppUserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
