package io.darrenleung.springboot_restapi_demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.darrenleung.springboot_restapi_demo.dtos.BookCreateDTO;
import io.darrenleung.springboot_restapi_demo.models.Book;
import io.darrenleung.springboot_restapi_demo.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private BookCreateDTO validBookCreateDTO;
    private Book mockBook;

    @BeforeEach
    void setUp() {
        // Setup test data
        validBookCreateDTO = new BookCreateDTO();
        validBookCreateDTO.setTitle("Test Book");
        validBookCreateDTO.setAuthor("Test Author");
        validBookCreateDTO.setPublished(true);

        mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("Test Book");
        mockBook.setAuthor("Test Author");
        mockBook.setPublished(true);

        // Setup ObjectMapper
        objectMapper = new ObjectMapper();

        // Setup MockMvc with validation
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
        
        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setValidator(validator)
                .build();
    }

    @Nested
    class CreateBookTests {
        @Test
        void whenValidBookCreateDTO_thenCreateBookSuccessfully() throws Exception {
            // Arrange
            when(bookService.createBook(any(BookCreateDTO.class))).thenReturn(mockBook);

            // Act & Assert
            mockMvc.perform(post("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validBookCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockBook.getId()))
                .andExpect(jsonPath("$.title").value(mockBook.getTitle()))
                .andExpect(jsonPath("$.author").value(mockBook.getAuthor()))
                .andExpect(jsonPath("$.published").value(mockBook.isPublished()));

            verify(bookService, times(1)).createBook(any(BookCreateDTO.class));
        }

        @Test
        void whenInvalidBookCreateDTO_thenReturnBadRequest() throws Exception {
            // Arrange
            BookCreateDTO invalidBookCreateDTO = new BookCreateDTO();
            invalidBookCreateDTO.setTitle(""); // Empty title
            invalidBookCreateDTO.setAuthor("Test Author");
            invalidBookCreateDTO.setPublished(true);

            // Act & Assert
            mockMvc.perform(post("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(invalidBookCreateDTO)))
                .andExpect(status().isBadRequest());

            verify(bookService, never()).createBook(any(BookCreateDTO.class));
        }
    }

    @Nested
    class GetAllBooksTests {
        @Test
        void whenNoFilters_thenReturnAllBooks() throws Exception {
            // Arrange
            List<Book> mockBooks = Arrays.asList(mockBook);
            when(bookService.getAllBooks()).thenReturn(mockBooks);

            // Act & Assert
            mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(mockBook.getId()))
                .andExpect(jsonPath("$[0].title").value(mockBook.getTitle()))
                .andExpect(jsonPath("$[0].author").value(mockBook.getAuthor()))
                .andExpect(jsonPath("$[0].published").value(mockBook.isPublished()));

            verify(bookService, times(1)).getAllBooks();
            verify(bookService, never()).getBooksByAuthor(any());
            verify(bookService, never()).getBooksByPublishedStatus(anyBoolean());
            verify(bookService, never()).getBooksByAuthorAndPublishedStatus(any(), anyBoolean());
        }

        @Test
        void whenAuthorFilter_thenReturnBooksByAuthor() throws Exception {
            // Arrange
            String author = "Test Author";
            List<Book> mockBooks = Arrays.asList(mockBook);
            when(bookService.getBooksByAuthor(author)).thenReturn(mockBooks);

            // Act & Assert
            mockMvc.perform(get("/api/books")
                    .param("author", author))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(mockBook.getId()))
                .andExpect(jsonPath("$[0].title").value(mockBook.getTitle()))
                .andExpect(jsonPath("$[0].author").value(mockBook.getAuthor()))
                .andExpect(jsonPath("$[0].published").value(mockBook.isPublished()));

            verify(bookService, times(1)).getBooksByAuthor(author);
            verify(bookService, never()).getAllBooks();
        }

        @Test
        void whenPublishedFilter_thenReturnBooksByPublishedStatus() throws Exception {
            // Arrange
            boolean published = true;
            List<Book> mockBooks = Arrays.asList(mockBook);
            when(bookService.getBooksByPublishedStatus(published)).thenReturn(mockBooks);

            // Act & Assert
            mockMvc.perform(get("/api/books")
                    .param("published", String.valueOf(published)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(mockBook.getId()))
                .andExpect(jsonPath("$[0].title").value(mockBook.getTitle()))
                .andExpect(jsonPath("$[0].author").value(mockBook.getAuthor()))
                .andExpect(jsonPath("$[0].published").value(mockBook.isPublished()));

            verify(bookService, times(1)).getBooksByPublishedStatus(published);
            verify(bookService, never()).getAllBooks();
        }

        @Test
        void whenAuthorAndPublishedFilters_thenReturnBooksByBoth() throws Exception {
            // Arrange
            String author = "Test Author";
            boolean published = true;
            List<Book> mockBooks = Arrays.asList(mockBook);
            when(bookService.getBooksByAuthorAndPublishedStatus(author, published)).thenReturn(mockBooks);

            // Act & Assert
            mockMvc.perform(get("/api/books")
                    .param("author", author)
                    .param("published", String.valueOf(published)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(mockBook.getId()))
                .andExpect(jsonPath("$[0].title").value(mockBook.getTitle()))
                .andExpect(jsonPath("$[0].author").value(mockBook.getAuthor()))
                .andExpect(jsonPath("$[0].published").value(mockBook.isPublished()));

            verify(bookService, times(1)).getBooksByAuthorAndPublishedStatus(author, published);
            verify(bookService, never()).getAllBooks();
        }
    }

    @Nested
    class DeleteBookTests {
        @Test
        void whenValidBookId_thenDeleteBookSuccessfully() throws Exception {
            // Arrange
            Long bookId = 1L;
            doNothing().when(bookService).deleteBook(bookId);

            // Act & Assert
            mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent());

            verify(bookService, times(1)).deleteBook(bookId);
        }

        @Test
        void whenNonExistentBookId_thenDeleteBookSuccessfully() throws Exception {
            // Arrange
            Long nonExistentBookId = 999L;
            doNothing().when(bookService).deleteBook(nonExistentBookId);

            // Act & Assert
            mockMvc.perform(delete("/api/books/{id}", nonExistentBookId))
                .andExpect(status().isNoContent());

            verify(bookService, times(1)).deleteBook(nonExistentBookId);
        }
    }
} 