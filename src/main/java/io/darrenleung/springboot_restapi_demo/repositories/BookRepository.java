package io.darrenleung.springboot_restapi_demo.repositories;

import io.darrenleung.springboot_restapi_demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByPublished(boolean published);
    List<Book> findByAuthorAndPublished(String author, boolean published);
} 