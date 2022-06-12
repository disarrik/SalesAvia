package ru.disarra.clientservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.disarra.clientservice.entity.Author;

import java.util.Objects;

public class AuthorDTO {
    private final String name;
    private final String surname;

    @JsonCreator
    public AuthorDTO(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static AuthorDTO of(Author author) {
        return new AuthorDTO(
                author.getName(),
                author.getSurname()
        );
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(name, authorDTO.name) && Objects.equals(surname, authorDTO.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
