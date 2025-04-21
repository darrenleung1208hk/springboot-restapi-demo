package io.darrenleung.springboot_restapi_demo.dtos;

import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class BookCreateDTO {
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Title must contain only alphanumeric characters and spaces")
    private String title;
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Author must contain only alphanumeric characters and spaces")
    private String author;
    
    private LocalDate published;

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

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }
} 