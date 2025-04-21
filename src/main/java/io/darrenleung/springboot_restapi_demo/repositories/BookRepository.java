package io.darrenleung.springboot_restapi_demo.repositories;

import io.darrenleung.springboot_restapi_demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
} 