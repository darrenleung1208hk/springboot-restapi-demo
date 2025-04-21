package io.darrenleung.springboot_restapi_demo.controllers;

import io.darrenleung.springboot_restapi_demo.dtos.BookCreateDTO;
import io.darrenleung.springboot_restapi_demo.models.Book;
import io.darrenleung.springboot_restapi_demo.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookCreateDTO bookDTO) {
        Book createdBook = bookService.createBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Boolean published) {
        
        if (author != null && published != null) {
            return ResponseEntity.ok(bookService.getBooksByAuthorAndPublishedStatus(author, published));
        } else if (author != null) {
            return ResponseEntity.ok(bookService.getBooksByAuthor(author));
        } else if (published != null) {
            return ResponseEntity.ok(bookService.getBooksByPublishedStatus(published));
        } else {
            return ResponseEntity.ok(bookService.getAllBooks());
        }
    }
} 