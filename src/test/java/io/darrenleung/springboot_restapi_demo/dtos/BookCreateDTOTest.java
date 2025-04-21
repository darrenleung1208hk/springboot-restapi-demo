package io.darrenleung.springboot_restapi_demo.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class BookCreateDTOTest {
    private final Validator validator;

    public BookCreateDTOTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Title validation tests
    @Test
    void whenTitleIsEmpty_thenValidationFails() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("");
        dto.setAuthor("Valid Author");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        // The message can be either one since both validations will fail
        assertTrue(violations.stream()
            .anyMatch(v -> v.getMessage().equals("Title is required") || 
                         v.getMessage().equals("Title must contain only alphanumeric characters and spaces")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Title@123", "Title#123", "Title$123"})
    void whenTitleContainsSpecialCharacters_thenValidationFails(String invalidTitle) {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle(invalidTitle);
        dto.setAuthor("Valid Author");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Title must contain only alphanumeric characters and spaces", 
                    violations.iterator().next().getMessage());
    }

    @Test
    void whenTitleIsOnlySpaces_thenValidationFails() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("   ");
        dto.setAuthor("Valid Author");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Valid Title", "Title 123", "Book Title 2024"})
    void whenTitleIsValid_thenValidationPasses(String validTitle) {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle(validTitle);
        dto.setAuthor("Valid Author");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    // Author validation tests
    @Test
    void whenAuthorIsEmpty_thenValidationFails() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor("");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        // The message can be either one since both validations will fail
        assertTrue(violations.stream()
            .anyMatch(v -> v.getMessage().equals("Author is required") || 
                         v.getMessage().equals("Author must contain only alphanumeric characters and spaces")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Author@123", "Author#123", "Author$123"})
    void whenAuthorContainsSpecialCharacters_thenValidationFails(String invalidAuthor) {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor(invalidAuthor);
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Author must contain only alphanumeric characters and spaces", 
                    violations.iterator().next().getMessage());
    }

    @Test
    void whenAuthorIsOnlySpaces_thenValidationFails() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor("   ");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"John Doe", "Author Name", "Writer 123"})
    void whenAuthorIsValid_thenValidationPasses(String validAuthor) {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor(validAuthor);
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    // Published field validation tests
    @Test
    void whenPublishedIsNull_thenValidationFails() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor("Valid Author");
        dto.setPublished(null);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertEquals("Published status is required and must be a boolean value (true or false)", 
                    violations.iterator().next().getMessage());
    }

    @Test
    void whenPublishedIsTrue_thenValidationPasses() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor("Valid Author");
        dto.setPublished(true);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenPublishedIsFalse_thenValidationPasses() {
        BookCreateDTO dto = new BookCreateDTO();
        dto.setTitle("Valid Title");
        dto.setAuthor("Valid Author");
        dto.setPublished(false);
        
        Set<ConstraintViolation<BookCreateDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }
} 