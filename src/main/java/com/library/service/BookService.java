package com.library.service;

import com.library.dto.book.BookDTO;
import com.library.dto.book.CreateBookDTO;
import com.library.dto.book.UpdateBookDTO;
import com.library.exceptions.InvalidFieldException;
import com.library.exceptions.NotFoundException;
import com.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.library.model.Book;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookDTO createBook(CreateBookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthorName(dto.getAuthorName());
        book.setCategory(dto.getCategory());
        if (dto.getTotalCopies() < 0) {
            throw new InvalidFieldException("O total de cópias não pode ser negativo.");
        }
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getTotalCopies());

        bookRepository.save(book);
        return new BookDTO(book);
    }

    public BookDTO findBookById(Long id) {
        Book book = getBookOrThrow(id);
        return new BookDTO(book);
    }

    @Transactional
    public BookDTO updateBookById(Long id, UpdateBookDTO dto) {
        Book book = getBookOrThrow(id);

        if (dto.getName() != null && !dto.getName().isBlank()) {
            book.setName(dto.getName());
        }
        if (dto.getAuthorName() != null && !dto.getAuthorName().isBlank()) {
            book.setAuthorName(dto.getAuthorName());
        }
        if (dto.getCategory() != null) {
            book.setCategory(dto.getCategory());
        }
        if (dto.getTotalCopies() != null) {
            if (dto.getTotalCopies() < 0) {
                throw new InvalidFieldException("O total de cópias não pode ser negativo.");
            }
            if (dto.getTotalCopies() >= book.getAvailableCopies()) {
                book.setTotalCopies(dto.getTotalCopies());
            } else {
                throw new InvalidFieldException("O total de livros não pode ser menor que os disponíveis.");
            }
        }
        bookRepository.save(book);
        return new BookDTO(book);
    }

    public void deleteBookById(Long id) {
        Book book = getBookOrThrow(id);
        bookRepository.delete(book);
    }

    public List<BookDTO> listAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookDTO::new)
                .toList();
    }

    private boolean bookExistsById(Long id) {
        return bookRepository.existsById(id);
    }

    public Book getBookOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Livro com o id:%d não encontrado.", id)));
    }
    @Transactional
    public void decrementAvailableCopies(Long bookId) {
        Book book = getBookOrThrow(bookId);
        int available = book.getAvailableCopies();
        if (available <= 0) {
            throw new InvalidFieldException("Não há cópias disponíveis para empréstimo.");
        }
        book.setAvailableCopies(available - 1);
        bookRepository.save(book);
    }

    @Transactional
    public void incrementAvailableCopies(Long bookId) {
        Book book = getBookOrThrow(bookId);
        int available = book.getAvailableCopies();
        int total = book.getTotalCopies();
        if (available >= total) {
            throw new InvalidFieldException("Não é possível aumentar cópias disponíveis além do total.");
        }
        book.setAvailableCopies(available + 1);
        bookRepository.save(book);
    }

}
