package se.alexjons.gamelib.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer publisherId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    LocalDate founded;

    public Publisher() {
    }

    public Publisher(String name, LocalDate founded) {
        this.name = name;
        this.founded = founded;
    }

    public Publisher(Integer publisherId, String name, LocalDate founded) {
        this.publisherId = publisherId;
        this.name = name;
        this.founded = founded;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }
}
