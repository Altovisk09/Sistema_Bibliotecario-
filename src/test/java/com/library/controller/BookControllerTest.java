package com.library.controller;

import com.library.dto.book.BookDTO;
import com.library.dto.book.CreateBookDTO;
import com.library.dto.book.UpdateBookDTO;
import com.library.model.BookCategory;
import com.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setName("Clean Code");
        bookDTO.setAuthorName("Robert C. Martin");
        bookDTO.setCategory(BookCategory.SCIENCE);
    }

    @Test
    void shouldCreateBook() {
        CreateBookDTO createDto = new CreateBookDTO();
        createDto.setName("Clean Code");
        createDto.setAuthorName("Robert C. Martin");
        createDto.setCategory(BookCategory.SCIENCE);
        createDto.setTotalCopies(5);

        when(bookService.createBook(createDto)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.createBook(createDto);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(bookDTO.getName(), response.getBody().getName());
        verify(bookService, times(1)).createBook(createDto);
    }

    @Test
    void shouldUpdateBook() {
        UpdateBookDTO updateDto = new UpdateBookDTO();
        updateDto.setName("Clean Architecture");

        bookDTO.setName("Clean Architecture");
        when(bookService.updateBookById(1L, updateDto)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.updateBookById(1L, updateDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Clean Architecture", response.getBody().getName());
        verify(bookService, times(1)).updateBookById(1L, updateDto);
    }

    @Test
    void shouldDeleteBook() {
        doNothing().when(bookService).deleteBookById(1L);

        ResponseEntity<Void> response = bookController.deleteBookById(1L);

        assertEquals(204, response.getStatusCode());
        verify(bookService, times(1)).deleteBookById(1L);
    }

    @Test
    void shouldFindBookById() {
        when(bookService.findBookById(1L)).thenReturn(bookDTO);

        ResponseEntity<BookDTO> response = bookController.findBookById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookDTO.getName(), response.getBody().getName());
        verify(bookService, times(1)).findBookById(1L);
    }

    @Test
    void shouldGetAllBooks() {
        BookDTO book2 = new BookDTO();
        book2.setId(2L);
        book2.setName("Refactoring");
        book2.setAuthorName("Martin Fowler");
        book2.setCategory(BookCategory.SCIENCE);

        when(bookService.listAllBooks()).thenReturn(List.of(bookDTO, book2));

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(bookService, times(1)).listAllBooks();
    }
}
