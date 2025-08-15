package com.library.controller;

import com.library.dto.book.BookDTO;
import com.library.dto.book.CreateBookDTO;
import com.library.dto.book.UpdateBookDTO;
import com.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookDTO dto){
        BookDTO book = service.createBook(dto);
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookById(@PathVariable Long id, @RequestBody UpdateBookDTO dto){
        BookDTO book = service.updateBookById(id, dto);
        return ResponseEntity.status(200).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
        service.deleteBookById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id){
        BookDTO book = service.findBookById(id);
        return ResponseEntity.status(200).body(book);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = service.listAllBooks();
        return ResponseEntity.status(200).body(books);
    }
}
