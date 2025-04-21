package io.darrenleung.springboot_restapi_demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Title must contain only alphanumeric characters and spaces")
    private String title;
    
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Author must contain only alphanumeric characters and spaces")
    private String author;
    
    private boolean published;

    // Default constructor
    public Book() {
    }

    // Constructor with parameters
    public Book(String title, String author, boolean published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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