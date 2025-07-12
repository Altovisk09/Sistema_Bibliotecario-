package com.library.model;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String authorName;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    private int totalCopies;
    private int availableCopies;

    protected Book() {}

    public Book(String name, String authorName, BookCategory category, int totalCopies) {
        this.name = name;
        this.authorName = authorName;
        this.category = category;
        this.totalCopies = totalCopies;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
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
