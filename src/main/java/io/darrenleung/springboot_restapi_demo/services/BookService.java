package io.darrenleung.springboot_restapi_demo.services;

import io.darrenleung.springboot_restapi_demo.dtos.BookCreateDTO;
import io.darrenleung.springboot_restapi_demo.models.Book;
import io.darrenleung.springboot_restapi_demo.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
} 