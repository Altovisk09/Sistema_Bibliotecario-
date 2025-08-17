package com.library.service;

import com.library.dto.book.BookDTO;
import com.library.dto.book.CreateBookDTO;
import com.library.dto.book.UpdateBookDTO;
import com.library.exceptions.InvalidFieldException;
import com.library.exceptions.NotFoundException;
import com.library.model.Book;
import com.library.model.BookCategory;
import com.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldCreateBookSuccessfully() {
        CreateBookDTO dto = new CreateBookDTO();
        dto.setName("Refactoring");
        dto.setAuthorName("Martin Fowler");
        dto.setCategory(BookCategory.SCIENCE);
        dto.setTotalCopies(3);

        Book savedBook = new Book("Refactoring", "Martin Fowler", BookCategory.SCIENCE, 3);
        savedBook.setAvailableCopies(3);

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        BookDTO result = bookService.createBook(dto);

        assertNotNull(result);
        assertEquals("Refactoring", result.getName());
        assertEquals("Martin Fowler", result.getAuthorName());
        assertEquals(BookCategory.SCIENCE, result.getCategory());

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void shouldThrowExceptionWhenCreatingBookWithNegativeCopies() {
        CreateBookDTO dto = new CreateBookDTO();
        dto.setTotalCopies(-1);

        assertThrows(InvalidFieldException.class, () -> bookService.createBook(dto));
    }

    @Test
    void shouldFindBookById() {
        Book book = new Book("Clean Code", "Robert C. Martin", BookCategory.SCIENCE, 5);
        book.setAvailableCopies(5);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO result = bookService.findBookById(1L);

        assertEquals("Clean Code", result.getName());
        assertEquals("Robert C. Martin", result.getAuthorName());
    }

    @Test
    void shouldThrowNotFoundWhenBookNotExist() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookService.findBookById(99L));
    }

    @Test
    void shouldUpdateBookSuccessfully() {
        Book existing = new Book("Old Title", "Old Author", BookCategory.HISTORY, 2);
        existing.setAvailableCopies(2);

        UpdateBookDTO dto = new UpdateBookDTO();
        dto.setName("New Title");
        dto.setAuthorName("New Author");
        dto.setCategory(BookCategory.SCIENCE);
        dto.setTotalCopies(4);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(existing)).thenReturn(existing);

        BookDTO result = bookService.updateBookById(1L, dto);

        assertEquals("New Title", result.getName());
        assertEquals("New Author", result.getAuthorName());
        assertEquals(BookCategory.SCIENCE, result.getCategory());

        verify(bookRepository, times(1)).save(existing);
    }

    @Test
    void shouldDeleteBook() {
        Book book = new Book("DDD", "Eric Evans", BookCategory.TECHNOLOGY, 1);
        book.setAvailableCopies(1);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBookById(1L);

        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void shouldListAllBooks() {
        Book book1 = new Book("Book1", "Author1", BookCategory.SCIENCE, 3);
        Book book2 = new Book("Book2", "Author2", BookCategory.HISTORY, 2);

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        List<BookDTO> result = bookService.listAllBooks();

        assertEquals(2, result.size());
        assertEquals("Book1", result.get(0).getName());
        assertEquals("Book2", result.get(1).getName());
    }

    @Test
    void shouldDecrementAvailableCopies() {
        Book book = new Book("Book1", "Author1", BookCategory.SCIENCE, 2);
        book.setAvailableCopies(2);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.decrementAvailableCopies(1L);

        assertEquals(1, book.getAvailableCopies());
        verify(bookRepository).save(book);
    }

    @Test
    void shouldThrowWhenDecrementUnavailable() {
        Book book = new Book("Book1", "Author1", BookCategory.SCIENCE, 2);
        book.setAvailableCopies(0);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(InvalidFieldException.class, () -> bookService.decrementAvailableCopies(1L));
    }

    @Test
    void shouldIncrementAvailableCopies() {
        Book book = new Book("Book1", "Author1", BookCategory.SCIENCE, 2);
        book.setAvailableCopies(1);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.incrementAvailableCopies(1L);

        assertEquals(2, book.getAvailableCopies());
        verify(bookRepository).save(book);
    }

    @Test
    void shouldThrowWhenIncrementBeyondTotal() {
        Book book = new Book("Book1", "Author1", BookCategory.SCIENCE, 2);
        book.setAvailableCopies(2);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(InvalidFieldException.class, () -> bookService.incrementAvailableCopies(1L));
    }
}
