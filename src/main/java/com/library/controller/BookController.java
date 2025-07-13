package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> getAll(){return bookService.getAll();}

    @PostMapping
    public ResponseEntity<String> registerBook(@RequestBody Book book){
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Livro cadastrado com sucesso");
    }
}
