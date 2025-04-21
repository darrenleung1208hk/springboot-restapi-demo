package io.darrenleung.springboot_restapi_demo.dtos;

import jakarta.validation.constraints.Pattern;

public class BookCreateDTO {
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Title must contain only alphanumeric characters and spaces")
    private String title;
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Author must contain only alphanumeric characters and spaces")
    private String author;
    
    private boolean published;

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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
} 