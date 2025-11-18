package se.alexjons.gamelib.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PublisherDTO {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotNull(message = "founded cannot be null")
    private LocalDate founded;

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
}
