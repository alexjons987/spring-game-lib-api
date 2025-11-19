package se.alexjons.gamelib.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import se.alexjons.gamelib.entity.Game;

import java.time.LocalDate;
import java.util.Set;

public class PublisherDTO {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "founded cannot be null")
    private LocalDate founded;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Game> gamesPublished;

    public PublisherDTO() {
    }

    public PublisherDTO(String name, LocalDate founded) {
        this.name = name;
        this.founded = founded;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public Set<Game> getGamesPublished() {
        return gamesPublished;
    }
}
