package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.library.model.Book;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //Listar
    public List<Book> getAll(){return bookRepository.findAll();}
    //Criar
    public Book save(Book book) {return bookRepository.save(book);}
}
