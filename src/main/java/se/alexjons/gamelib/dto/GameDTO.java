package se.alexjons.gamelib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import se.alexjons.gamelib.entity.Publisher;

import java.time.LocalDate;

public class GameDTO {

    @NotBlank(message = "title cannot be empty")
    String title;

    @NotBlank(message = "genre cannot be empty")
    String genre;

    @Positive(message = "rating must be positive")
    @Min(0)
    @Max(100)
    Float rating;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate releaseYear;

    @Positive(message = "publisherId must be positive")
    Integer publisherId;

    public GameDTO() {
    }

    public GameDTO(String title, String genre, Float rating, LocalDate releaseYear, Integer publisherId) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.publisherId = publisherId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Float getRating() {
        return rating;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public Integer getPublisherId() {
        return publisherId;
    }
}
