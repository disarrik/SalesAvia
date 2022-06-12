package ru.disarra.clientservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.disarra.clientservice.entity.Article;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ArticleWithoutContentDTO {
    private final String title;
    private final String shortDescription;
    private final AuthorDTO author;
    private final String imageUrl;
    private final ZonedDateTime posted;

    @JsonCreator
    public ArticleWithoutContentDTO(@JsonProperty("title") String title,
                                    @JsonProperty("shortDescription") String shortDescription,
                                    @JsonProperty("author") AuthorDTO author,
                                    @JsonProperty("imageUrl") String imageUrl,
                                    @JsonProperty("posted") ZonedDateTime posted) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleWithoutContentDTO that = (ArticleWithoutContentDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(author, that.author) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(posted, that.posted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, shortDescription, author, imageUrl, posted);
    }
}
