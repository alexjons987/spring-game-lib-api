package se.alexjons.gamelib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GameRequestDTO {

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
    LocalDate release;

    @Positive(message = "publisherId must be positive")
    Integer publisherId;

    public GameRequestDTO() {
    }

    public GameRequestDTO(String title, String genre, Float rating, LocalDate release, Integer publisherId) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.release = release;
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

    public LocalDate getRelease() {
        return release;
    }

    public Integer getPublisherId() {
        return publisherId;
    }
}
