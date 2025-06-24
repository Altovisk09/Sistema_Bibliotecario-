package com.library.model;

public enum BookCategory {
    TECHNOLOGY("Tecnologia e Computação"),
    LITERATURE("Literatura e Poesia"),
    HISTORY("Estudos Históricos"),
    SCIENCE("Conhecimento Científico"),
    FICTION("Ficção e Romances"),
    ART("Artes Visuais e Performáticas"),
    BIOGRAPHY("Biografias e Memórias"),
    SELF_HELP("Autoajuda e Motivação"),
    EDUCATION("Educação e Ensino"),
    PHILOSOPHY("Filosofia e Pensamento");

    private final String description;

    BookCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
