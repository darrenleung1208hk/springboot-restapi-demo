package io.darrenleung.springboot_restapi_demo.services;

import io.darrenleung.springboot_restapi_demo.dtos.BookCreateDTO;
import io.darrenleung.springboot_restapi_demo.models.Book;
import io.darrenleung.springboot_restapi_demo.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookCreateDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublished(bookDTO.isPublished());
        
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByPublishedStatus(boolean published) {
        return bookRepository.findByPublished(published);
    }

    public List<Book> getBooksByAuthorAndPublishedStatus(String author, boolean published) {
        return bookRepository.findByAuthorAndPublished(author, published);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
} 