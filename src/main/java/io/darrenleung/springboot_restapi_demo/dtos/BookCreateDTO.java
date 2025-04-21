package io.darrenleung.springboot_restapi_demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

public class BookCreateDTO {
    
    @NotNull(message = "Title is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Title must contain only alphanumeric characters and spaces")
    @JsonProperty("title")
    private String title;
    
    @NotNull(message = "Author is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Author must contain only alphanumeric characters and spaces")
    @JsonProperty("author")
    private String author;
    
    @NotNull(message = "Published status is required")
    @JsonProperty("published")
    @JsonDeserialize(using = BooleanDeserializer.class)
    private Boolean published;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean isPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
} 