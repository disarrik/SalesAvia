package ru.disarra.clientservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.time.ZonedDateTime;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "title")
)
public class Article{
    @Id
    private Long id;
    @Size(max = 70)
    @NotBlank
    private String title;

    @NotBlank
    @Size(max = 200)
    private String shortDescription;
    private String content;

    @Size(max = 70)
    private String author;

    @NotBlank
    private String imageUrl;

    @NotNull
    private ZonedDateTime posted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
