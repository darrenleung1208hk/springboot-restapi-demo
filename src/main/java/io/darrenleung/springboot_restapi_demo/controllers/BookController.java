package io.darrenleung.springboot_restapi_demo.controllers;

import io.darrenleung.springboot_restapi_demo.dtos.BookCreateDTO;
import io.darrenleung.springboot_restapi_demo.dtos.BookResponseDTO;
import io.darrenleung.springboot_restapi_demo.models.Book;
import io.darrenleung.springboot_restapi_demo.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookCreateDTO bookDTO) {
        Book createdBook = bookService.createBook(bookDTO);
        return new ResponseEntity<>(new BookResponseDTO(createdBook), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Boolean published) {
        
        List<Book> books;
        if (author != null && published != null) {
            books = bookService.getBooksByAuthorAndPublishedStatus(author, published);
        } else if (author != null) {
            books = bookService.getBooksByAuthor(author);
        } else if (published != null) {
            books = bookService.getBooksByPublishedStatus(published);
        } else {
            books = bookService.getAllBooks();
        }

        List<BookResponseDTO> responseDTOs = books.stream()
                .map(BookResponseDTO::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responseDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
} 