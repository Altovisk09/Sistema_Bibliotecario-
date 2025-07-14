package com.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_table")
public class Book {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String authorName;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    private int totalCopies;
    private int availableCopies;

    public Book() {
    }

    public Book(String name, String authorName, BookCategory category, int totalCopies) {
        this.name = name;
        this.authorName = authorName;
        this.category = category;
        this.totalCopies = totalCopies;
    }

    @Override
    public String toString() {
        return String.format(
                "Livro {ID: %d, Título: \"%s\", Autor: %s, Categoria: %s, Cópias: %d (Disponíveis: %d)}",
                id,
                name,
                authorName,
                category.getDisplayName(),
                totalCopies,
                availableCopies
        );
    }
}
