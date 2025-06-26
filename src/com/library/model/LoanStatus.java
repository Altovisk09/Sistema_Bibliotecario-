package com.library.model;

public enum LoanStatus {
    ACTIVE("Ativo"),
    RETURNED("Devolvido"),
    LATE("Atrasado");

    private final String description;

    LoanStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
