package com.library.model;

import com.library.exceptions.BookUnavailableException;

public class Book {
    private final int id;
    private String name;
    private String autorName;
    private BookCategory category;
    private int totalCopies;
    private int availableCopies;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
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

    public Book(int id, String name, String autorName, BookCategory category, int totalCopies) {
        this.id = id;
        this.name = name;
        this.autorName = autorName;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public void lendBook() throws BookUnavailableException {
        if (getAvailableCopies() <= 0) {
            throw new BookUnavailableException("Quantidade disponível do livro '" + getName() + "' insuficiente.");
        }
        setAvailableCopies(getAvailableCopies() - 1);
    }

    @Override
    public String toString() {
        return String.format(
                "Livro {ID: %d, Título: \"%s\", Autor: %s, Categoria: %s, Cópias: %d (Disponíveis: %d)}",
                id,
                name,
                autorName,
                category.getName(),
                totalCopies,
                availableCopies
        );
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }
}
