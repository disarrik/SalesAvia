package ru.disarra.clientservice.dto;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ArticleWithoutContentDTO {
    private String title;
    private String shortDescription;
    private String author;
    private String imageUrl;
    private ZonedDateTime posted;

    public ArticleWithoutContentDTO(String title, String shortDescription, String content, String author, String imageUrl, ZonedDateTime posted) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.imageUrl = imageUrl;
        this.posted = posted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ZonedDateTime getPosted() {
        return posted;
    }

    public void setPosted(ZonedDateTime posted) {
        this.posted = posted;
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
