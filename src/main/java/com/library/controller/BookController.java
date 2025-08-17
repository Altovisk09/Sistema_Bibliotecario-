package com.library.controller;

import com.library.dto.book.BookDTO;
import com.library.dto.book.CreateBookDTO;
import com.library.dto.book.UpdateBookDTO;
import com.library.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookDTO dto){
        logger.info("Recebida requisição para criar livro: {}", dto.getName());
        BookDTO book = service.createBook(dto);
        logger.info("Livro criado com sucesso: id={}, title={}", book.getId(), book.getName());
        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookById(@PathVariable Long id, @RequestBody UpdateBookDTO dto){
        logger.info("Recebida requisição para atualizar livro id={}", id);
        BookDTO book = service.updateBookById(id, dto);
        logger.info("Livro atualizado com sucesso: id={}, title={}", book.getId(), book.getName());
        return ResponseEntity.status(200).body(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
        logger.info("Recebida requisição para deletar livro id={}", id);
        service.deleteBookById(id);
        logger.info("Livro deletado com sucesso: id={}", id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id){
        logger.info("Buscando livro pelo id={}", id);
        BookDTO book = service.findBookById(id);
        logger.info("Livro encontrado: id={}, title={}", book.getId(), book.getName());
        return ResponseEntity.status(200).body(book);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        logger.info("Buscando todos os livros");
        List<BookDTO> books = service.listAllBooks();
        logger.info("Total de livros encontrados: {}", books.size());
        return ResponseEntity.status(200).body(books);
    }
}
