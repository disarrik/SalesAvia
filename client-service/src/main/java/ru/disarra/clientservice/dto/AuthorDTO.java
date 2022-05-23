package ru.disarra.clientservice.dto;

import ru.disarra.clientservice.entity.Author;

public class AuthorDTO {
    private final String name;
    private final String surname;

    public AuthorDTO(String name, String surname) {
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
}
