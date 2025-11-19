package se.alexjons.gamelib.dto;

import se.alexjons.gamelib.entity.Publisher;

import java.time.LocalDate;

public class GameResponseDTO {

    String title;
    String genre;
    Float rating;
    LocalDate release;
    Publisher publisher;

    public GameResponseDTO() {
    }

    public GameResponseDTO(String title, String genre, Float rating, LocalDate release, Publisher publisher) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.release = release;
        this.publisher = publisher;
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

    public Publisher getPublisher() {
        return publisher;
    }
}
