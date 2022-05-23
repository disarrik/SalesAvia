package ru.disarra.clientservice.dto;

import ru.disarra.clientservice.entity.Article;
import ru.disarra.clientservice.entity.Author;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ArticleDTO {
    private final String title;
    private final String shortDescription;
    private final String content;
    private final AuthorDTO author;
    private final String imageUrl;
    private final ZonedDateTime posted;

    public ArticleDTO(String title,
                      String shortDescription,
                      String content,
                      AuthorDTO author,
                      String imageUrl,
                      ZonedDateTime posted) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.author = author;
        this.imageUrl = imageUrl;
        this.posted = posted;
    }

    public static ArticleDTO of(Article article) {
        return new ArticleDTO(
                article.getTitle(),
                article.getShortDescription(),
                article.getContent(),
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

    public String getContent() {
        return content;
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
