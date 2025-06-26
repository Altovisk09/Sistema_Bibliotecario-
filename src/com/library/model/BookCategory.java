package com.library.model;

public enum BookCategory {
    TECHNOLOGY("Tecnologia", "Tecnologia e Computação"),
    LITERATURE("Literatura", "Literatura e Poesia"),
    HISTORY("História", "Estudos Históricos"),
    SCIENCE("Ciência", "Conhecimento Científico"),
    FICTION("Ficção", "Ficção e Romances"),
    ART("Arte", "Artes Visuais e Performáticas"),
    BIOGRAPHY("Biografia", "Biografias e Memórias"),
    SELF_HELP("Autoajuda", "Autoajuda e Motivação"),
    EDUCATION("Educação", "Educação e Ensino"),
    PHILOSOPHY("Filosofia", "Filosofia e Pensamento");

    private final String name;
    private final String description;

    BookCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
