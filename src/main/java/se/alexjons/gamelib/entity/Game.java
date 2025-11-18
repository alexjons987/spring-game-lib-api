package se.alexjons.gamelib.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String genre;

    @Column(nullable = false)
    Float rating;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate release;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    Publisher publisher;

    public Game() {
    }

    public Game(String title, String genre, Float rating, LocalDate release, Publisher publisher) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.release = release;
        this.publisher = publisher;
    }

    public Game(Integer id, String title, String genre, Float rating, LocalDate release, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.release = release;
        this.publisher = publisher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
