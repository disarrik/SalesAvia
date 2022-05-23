package ru.disarra.clientservice.dto;

import ru.disarra.clientservice.entity.Article;
import ru.disarra.clientservice.entity.Author;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ArticleWithoutContentDTO {
    private final String title;
    private final String shortDescription;
    private final AuthorDTO author;
    private final String imageUrl;
    private final ZonedDateTime posted;

    public ArticleWithoutContentDTO(String title,
                                    String shortDescription,
                                    AuthorDTO author,
                                    String imageUrl,
                                    ZonedDateTime posted) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.imageUrl = imageUrl;
        this.posted = posted;
    }

    public static ArticleWithoutContentDTO of(Article article) {
        return new ArticleWithoutContentDTO(
                article.getTitle(),
                article.getShortDescription(),
                AuthorDTO.of(article.getAuthor()),
                article.getImageUrl(),
                article.getPosted()
        );
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ZonedDateTime getPosted() {
        return posted;
    }
}
